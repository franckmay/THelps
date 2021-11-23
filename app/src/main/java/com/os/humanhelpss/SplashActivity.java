package com.os.humanhelpss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.os.humanhelpss.R;

public class SplashActivity extends AppCompatActivity {
    private static int TIME_OUT = 8000;

    private ImageView logo;
    TextView name;
    private LottieAnimationView lott, lot_load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.im_logo);
        name = findViewById(R.id.tv_logo);
        lott = findViewById(R.id.lottie);
        lot_load = findViewById(R.id.lottie_load);


        logo.animate().translationX(1400).setDuration(1300).setStartDelay(2500);
        name.animate().translationX(-1400).setDuration(1000).setStartDelay(3000);
        lott.animate().translationX(1400).setDuration(1000).setStartDelay(4800);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                lot_load.setVisibility(View.VISIBLE);
            }
        },6000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent home = new Intent(SplashActivity.this,AccueilActivity.class);
                startActivity(home);
                finish();
            }
        },TIME_OUT);

    }
}