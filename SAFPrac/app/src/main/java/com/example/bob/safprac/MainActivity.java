/*
    Links used at the moment:
    https://developer.android.com/reference/android/support/v4/provider/DocumentFile
    https://developer.android.com/reference/android/provider/DocumentsProvider
    https://developer.android.com/reference/android/content/ContentResolver
    https://stackoverflow.com/questions/559902/android-how-can-i-convert-android-net-uri-object-to-java-net-uri-object
    https://docs.oracle.com/javase/7/docs/api/java/nio/file/FileSystem.html
    https://docs.oracle.com/javase/7/docs/api/java/nio/file/FileSystems.html#getFileSystem(java.net.URI)
    https://docs.oracle.com/javase/7/docs/api/java/nio/file/Path.html
    https://docs.oracle.com/javase/7/docs/api/java/io/File.html
    https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html
    https://docs.oracle.com/javase/7/docs/technotes/guides/io/fsp/zipfilesystemprovider.html
 */
package com.example.bob.safprac;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.provider.DocumentFile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static android.support.v4.provider.DocumentFile.fromSingleUri;

public class MainActivity extends AppCompatActivity {

    final int READ_REQUEST_CODE = 2;
    final int CREATE_ZIP = 0;
    DocumentFile zipFile;
    DocumentFile usbRoot; // DocumentFile is not an Uri.

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
                    CREATE_ZIP);
        } else{

            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.setType("application/zip");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivityForResult(intent, CREATE_ZIP);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        if(requestCode == CREATE_ZIP){
            Log.i("dhl", "Creating zip archive.");
            zipFile = fromSingleUri(this, data.getData());
            try {
                ZipOutputStream zipDest = new ZipOutputStream(getContentResolver().openOutputStream(data.getData()));
                byte[] buffer = new byte[1024];
                File newFile = File.createTempFile("MockFile", ".txt");
                FileInputStream newFileInputStream = new FileInputStream(newFile);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(newFileInputStream);
                ZipEntry zipEntry = new ZipEntry("MockFile");
                zipDest.putNextEntry(zipEntry);
                int count = 0;
                while((count = bufferedInputStream.read(buffer, 0, 1024)) != -1){
                    zipDest.write(buffer, 0, count);
                }
                bufferedInputStream.close();
                zipDest.close();
                Log.i("dhl", "Right before the invocation of appendFileToZip().");
                appendFileToZip();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                Log.d("dhl", e.toString());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void appendFileToZip(){
        Log.i("dhl", "In the block for appending Zip file.");
        BufferedInputStream bis = null;
        try {
            File newFile = File.createTempFile("title_and_desc", ".txt");
            PrintWriter pw = new PrintWriter(newFile);
            pw.write("Regarding institution we criticize.");
            pw.close();
            Path appendThis = newFile.toPath();
            Files.copy(appendThis, getContentResolver().openOutputStream(zipFile.getUri()));
            Log.i("dhl", "Finished appending text file.");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("dhl", e.toString());
        } catch (Exception e){
            Log.i("dhl", e.toString());
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
                    Log.i("dhl", "Permission granted, please restart the application.");
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