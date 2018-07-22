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
import java.io.FileNotFoundException;
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

            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivityForResult(intent, READ_REQUEST_CODE);
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

        for(File str : images) {
            listOfImagesUri.add(Uri.fromFile(str));
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        usbRoot = fromTreeUri(this, data.getData());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            grantUriPermission(this.getPackageName(), data.getData(),
                    Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            getContentResolver().takePersistableUriPermission(data.getData(),
                    Intent.FLAG_GRANT_READ_URI_PERMISSION);
            getContentResolver().takePersistableUriPermission(data.getData(),
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        writeToTextFile();
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
        usbRoot.createFile("text/plain", "some_text.txt");
        PrintWriter pw = null;
        //Create an output stream, which writes text files.
        try {
            pw = new PrintWriter(getContentResolver()
                    .openOutputStream
                            (baseUri.withAppendedPath(baseUri, "./some_text.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.write("In regards to institution we criticize.");
        pw.close();
    }

    //I have to film a Shaolin movie with DocumentFile with this one.
    public void writeImageFiles(){

        //ArrayList<OutputStream> listOfOutputStream = new ArrayList<OutputStream>();
        for(int counter = 0; counter < listOfImagesUri.size(); counter++){
            try {
                //I have to be clever about the below, and somehow chuck in ImageFiles
                //into the USB.
                getContentResolver().openOutputStream(listOfImagesUri.get(counter));
            } catch (FileNotFoundException e) {
                Log.i("dhl", e.toString());
                return;
            }
        }

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