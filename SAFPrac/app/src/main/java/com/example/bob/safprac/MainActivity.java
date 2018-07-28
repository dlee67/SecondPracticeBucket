package com.example.bob.safprac;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.provider.DocumentFile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import static android.support.v4.provider.DocumentFile.fromTreeUri;

public class MainActivity extends AppCompatActivity {

    final int READ_REQUEST_CODE = 1;
    DocumentFile usbRoot; // DocumentFile is not an Uri.
    String[] listOfFiles;
    File imagesDir;
    File[] images;
    ArrayList<Uri> listOfImagesUri = new ArrayList<Uri>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("dhl", "Asking for permission.");
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            Log.i("dhl", "Invoking onRequestPermissionsResult.");
            /*
                I also have to declare uses-permission in the manifest;
                if I don't, there won't be any dialogs being shown to ask for user permissions.
                onRequestPermission() will be invoked non-the less, but will fail the
                condition checking for immediately because dialog is what's returning
                the permission granted.

                https://productforums.google.com/forum/#!topic/phone-by-google/2QWiNZehcBY
                The later problem I've encountered was that /Pictures was already empty.
                Pixel automatically saves every pictures in this thing called Google photo...
                and moving around pictures requires File explorer.
             */
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    READ_REQUEST_CODE);
        } else{
            populateImages();

            //Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivityForResult(intent, READ_REQUEST_CODE);
            //startActivityForResult(intent, READ_REQUEST_CODE);
        }
    }

    public void populateImages(){
        imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.i("dhl", "Fetching directory: " + imagesDir.getAbsolutePath());
        listOfFiles = imagesDir.list();
        if(listOfFiles == null){
            Log.i("dhl", "List of files are null. Returning out of the method.");
            return;
        }

        images = imagesDir.listFiles();
        for(File str : images) {
            listOfImagesUri.add(Uri.fromFile(str));
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        //usbRoot = fromTreeUri(this, data.getData());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            grantUriPermission(this.getPackageName(), data.getData(),
                    Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            getContentResolver().takePersistableUriPermission(data.getData(),
                    Intent.FLAG_GRANT_READ_URI_PERMISSION);
            getContentResolver().takePersistableUriPermission(data.getData(),
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }

        PrintWriter pw = null;
        //Create an output stream, which writes text files.
        try {
            Log.i("dhl", "Carrying out the alternative.");
            pw = new PrintWriter(getContentResolver()
                    .openOutputStream
                    //(baseUri.withAppendedPath(baseUri, "some_text.txt")));
                            (data.getData()));
        } catch (Exception e) {
            Log.i("dhl", e.toString());
            return;
        }

        pw.write("In regards to the institution we criticize.");
//https://stackoverflow.com/questions/42043114/how-to-write-to-documentfile-in-android-programmatically
        pw.flush();
        pw.close();

        //Log.i("dhl", "Calling writeToTextFile()");
        //writeToTextFile();
        //writeImageFiles();
    }

    /*
        Problem at the moment is to finguring out a way to write things to my text file,
        it seems like that entire operation needs to be done via through ContentProvider
        and document provider, DoncumentFile itself doesn't have enough power to
        write things to anything.

        The class ContentResolver seems to hold the most water for me at the moment.
        It has a method like openInputStream() and openOutputStream().

        In fact:
        https://stackoverflow.com/questions/42043114/how-to-write-to-documentfile-in-android-programmatically
        that seems to be the most pragmatic choice at the moment.
     */
    public void writeToTextFile(){
        Log.i("dhl", "Current Uri can: " + usbRoot.canRead());
        Log.i("dhl", "Current Uri can: " + usbRoot.canWrite());
        Uri baseUri = usbRoot.getUri();
        Log.i("dhl", "Uri of the usbRoot at the moment: " + usbRoot.getUri().getPath());
        usbRoot.createFile("text/plain", "some_text.txt");
        DocumentFile textFileInSomeFolder = usbRoot.listFiles()[0];
        PrintWriter pw = null;
        //Create an output stream, which writes text files.
        try {
            pw = new PrintWriter(getContentResolver()
                    .openOutputStream
                            //(baseUri.withAppendedPath(baseUri, "some_text.txt")));
                                    (textFileInSomeFolder.getUri()));
        } catch (Exception e) {
            Log.i("dhl", e.toString());
            return;
        }
        pw.write("In regards to institution we criticize.");
//https://stackoverflow.com/questions/42043114/how-to-write-to-documentfile-in-android-programmatically
        pw.flush();
        pw.close();
    }

    //I have to film a Shaolin movie with DocumentFile with this one.
    public void writeImageFiles() {
        //If the usbRoot doesn't have an Uri path to the ./bug_report, then make it so.
        //Uri imgDirUri = Uri.withAppendedPath(usbRoot.getUri(), "./bug_report");
        try {
            /*
            Just like the things I've learned about in the OS course, the below block of code
            should be able to stream the data from the intended directory from OutPutStream
            to InputStream.
             */
            usbRoot.createFile("image/png", "some_image");
            Uri uriToImg = Uri.withAppendedPath(usbRoot.getUri(), "some_image.png");
            Log.i("dhl", "uriToImg's uri is: " + uriToImg.getPath());
            OutputStream out = getContentResolver().openOutputStream(uriToImg);
            InputStream in = getContentResolver().openInputStream(listOfImagesUri.get(0)); //Grabs the very first element of the listOfImagesUri.
            Log.i("dhl", "From listOfImagesUri: " + listOfImagesUri.get(0).toString());
            byte[] buffers = new byte[1024];
            int read = 0;
            while((read = in.read(buffers)) != -1){
                out.write(buffers, 0, read);
            }
        } catch (FileNotFoundException e) {
            Log.i("dhl", e.toString());
            return;
        } catch (IOException e) {
            Log.i("dhl", e.toString());
            return;
        }
        //ArrayList<OutputStream> listOfOutputStream = new ArrayList<OutputStream>();
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.i("dhl", "In the block of code for onRequestPermission.");
        switch (requestCode) {
            case READ_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    populateImages();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.i("dhl", "Permission denied.");
                }
                return;
            }
        }
    }
}