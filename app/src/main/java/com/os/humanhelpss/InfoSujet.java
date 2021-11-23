package com.os.humanhelpss;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class InfoSujet extends AppCompatActivity {

    private Button button;
    private byte[] img;
    private  String title, desc;
    private TextView titre, description;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_sujet);


        Intent intent = getIntent();
        img = intent.getExtras().getByteArray("image");
        title = intent.getExtras().getString("title");
        desc = intent.getExtras().getString("description");


        image = findViewById(R.id.imageViewimg);
        titre = findViewById(R.id.textViewtitre);
        description = findViewById(R.id.textViewdescription);


        titre.setText(title);
        description.setText(desc);

        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        image.setImageBitmap(bitmap);

        button=findViewById(R.id.donbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ine= new Intent(InfoSujet.this, DonActivity.class);
                startActivity(ine);
            }
        });


    }

    public void sujetBackbutton(View view){
        Intent intent = new Intent(InfoSujet.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}