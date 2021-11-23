package com.os.humanhelpss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class AccueilActivity extends AppCompatActivity {

    String[] permissions = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE
    };
    public static final int MULTIPLE_PERMISSIONS = 122;

    Button log, ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        checkPermissions();

        log = findViewById(R.id.btn_login);
        ins = findViewById(R.id.btn_inscription);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccueilActivity.this, LoginActivity.class));
            }
        });

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccueilActivity.this, InscriptionActivity.class));

            }
        });

    }





    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getApplicationContext(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {  // permissions granted.
                    //       getCallDetails(); // Now you call here what ever you want :)
                    //    creatAlfolder(this);
                    // initialie();
                } else {
                    String perStr = "";
                    for (String per : permissions) {
                        perStr += "\n" + per;
                    }   // permissions list of don't granted permission
                }
                return;
            }
        }
    }
}