package com.example.bob.unpackandpack;

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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static android.support.v4.provider.DocumentFile.fromSingleUri;
import static android.support.v4.provider.DocumentFile.fromTreeUri;

public class MainActivity extends AppCompatActivity {

    final int READ_REQUEST_CODE = 2;
    final int CREATE_ZIP = 0;
    final int GET_ROOT_USB_URI = 1;
    DocumentFile zipFile;
    DocumentFile usbRootUri;

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

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        if(requestCode == CREATE_ZIP){
            Log.i("dhl", "Creating zip archive.");
            zipFile = fromSingleUri(this, data.getData());
            try {
                ZipOutputStream zipDest = new ZipOutputStream(getContentResolver().openOutputStream(data.getData()));
                byte[] buffer = new byte[1024];
                File newFile = File.createTempFile("MockFile", ".txt");
                PrintWriter pw = new PrintWriter(newFile);
                pw.write("Regarding institution we criticize.");
                pw.close();
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
                Log.i("dhl", "Shooting ACTION_OPEN_DOCUMENT_TREE");
                Intent grabUsbRootUri = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                grabUsbRootUri.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                grabUsbRootUri.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                startActivityForResult(grabUsbRootUri, GET_ROOT_USB_URI);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                Log.d("dhl", e.toString());
            }
        }else if(requestCode == GET_ROOT_USB_URI){
            Log.i("dhl", "Fetching usbRootUri.");
            usbRootUri = fromTreeUri(this, data.getData());
            unpackZip();
        }
    }

    //Well, first, we should certainly get some unpackings done, right?
    public void unpackZip(){
        Log.i("dhl", "In the block of unpackZip().");
        try {
            DocumentFile zipFile = usbRootUri.findFile("ZipThis.zip");
            byte[] buffer = new byte[1024];
            ZipInputStream zipIn = new ZipInputStream(getContentResolver().openInputStream(zipFile.getUri()));
            ZipEntry entry = zipIn.getNextEntry();
            while(entry != null){
                String fileName = entry.getName();
                Log.i("dhl", "Getting zip entry: " + entry.getName());
                // iterates over entries in the zip file
                while (entry != null) {
                    if (!entry.isDirectory()) {
                    // if the entry is a file, extracts it
                    OutputStream out = getContentResolver()
                            .openOutputStream(usbRootUri.createFile("*/*", entry.getName()).getUri());
                    extractFile(zipIn, out);
                    } else {
                        // if the entry is a directory, make the directory
                        //File dir = new File(filePath);
                        usbRootUri.createDirectory(entry.getName());
                    }
                    zipIn.closeEntry();
                    entry = zipIn.getNextEntry();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            zipIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            Log.d("dhl", e.toString());
        }
    }

    private void extractFile(ZipInputStream zipIn, OutputStream out) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(out);
        byte[] bytesIn = new byte[1024];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
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
