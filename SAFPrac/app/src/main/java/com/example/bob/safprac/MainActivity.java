package com.example.bob.safprac;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.support.v4.provider.DocumentFile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.FileNotFoundException;

import static android.support.v4.provider.DocumentFile.fromTreeUri;

public class MainActivity extends AppCompatActivity {

    final int READ_REQUEST_CODE = 777;
    DocumentFile treeUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        treeUri = fromTreeUri(this, data.getData());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            grantUriPermission(this.getPackageName(), data.getData(),
                    Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            getContentResolver().takePersistableUriPermission(data.getData(),
                    Intent.FLAG_GRANT_READ_URI_PERMISSION);
            getContentResolver().takePersistableUriPermission(data.getData(),
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        doSomething();
    }

    public void doSomething(){
        Log.i("dhl", "Current Uri can: " + treeUri.canRead());
        Log.i("dhl", "Current Uri can: " + treeUri.canWrite());
        treeUri.createFile("text/plain", "some_text.txt");
    }

}