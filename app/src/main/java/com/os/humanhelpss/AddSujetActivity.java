package com.os.humanhelpss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.os.humanhelpss.data.HelpDBHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddSujetActivity extends AppCompatActivity  {

   private static final int GALLERY_REQUEST_CODE = 123;

    ImageView imageView;
    private EditText nom, prenom, description,age, titre;

    int SELECT_IMAGE_CODE = 1;
    private Button btImage, btVideo, btn_cnx;
    private HelpDBHelper emdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sujetctivity);

        //initializing views
        emdb = new HelpDBHelper(this);

        nom = findViewById(R.id.name);
        prenom = findViewById(R.id.prenom);
        age = findViewById(R.id.age);
        titre = findViewById(R.id.titre);
        description = findViewById(R.id.description);
        imageView = findViewById(R.id.myImageview);
        btImage = findViewById(R.id.bt_image);
        btn_cnx = findViewById(R.id.btn_cnx);

        btImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "choisir une image"), SELECT_IMAGE_CODE);

//                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//                photoPickerIntent.setType("image/*");
//                startActivityForResult(photoPickerIntent, GALLERY_REQUEST_CODE);
//
            }
        });

        btn_cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (emdb.addSujet(nom.getText().toString(), prenom.getText().toString(), Integer.valueOf(age.getText().toString()), titre.getText().toString(), description.getText().toString(),imgViewToByte( imageView))) {
                            Log.i("user ---", "USER ENTERED SUCCESSFULLY");
                            Snackbar.make(v, "Sujet ENREGISTRE AVEC SUCCES.", Snackbar.LENGTH_LONG).setAction("", null).show();
                            Intent home = new Intent(AddSujetActivity.this,MainActivity.class);
                            startActivity(home);
                            finish();
                        } else {
                            Snackbar.make(v, "FAILED TO ENTER CURRENT SUBJECT. TRY AGAIN LATER.", Snackbar.LENGTH_LONG).setAction("", null).show();
                        }

                    }
                },1500);
//
            }
        });



    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (reqCode == 1) {
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }else {
            Toast.makeText(AddSujetActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            if (requestCode == 1) {

            } else {

            }
        } else {
            Toast.makeText(getApplicationContext(), "permissions", Toast.LENGTH_SHORT).show();
        }
    }

    public void backbutton(View view){
        Intent intent = new Intent(AddSujetActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }


private byte[] imgViewToByte(ImageView imagev){

        Bitmap bitmap = ((BitmapDrawable) imagev.getDrawable()).getBitmap();
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
    byte[] bytearray  = stream.toByteArray();
    return bytearray;
}

}
