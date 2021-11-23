package com.os.humanhelpss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class DonActivity extends AppCompatActivity {

    private LottieAnimationView lott, lot_load;
    private Button bnt;
    private EditText mt;
    private TextView tv;
    ProgressDialog progressDialog;
    Dialog dialog1, dialog2;
    private  String codedepayement, codeussdi, MONTANTSCAN, NUMERO= "656832774";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don);

        lott = findViewById(R.id.lottiedon2);
        lot_load = findViewById(R.id.lottiedonSucces);
        bnt= findViewById(R.id.button);
        mt= findViewById(R.id.montant);
        tv= findViewById(R.id.textViewpay);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.waiting));
        dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.custom_dialog_insert);
        dialog2 = new Dialog(this);
        dialog2.setContentView(R.layout.custom_dialog_user);

        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
            }
        });







        //////////////////// procecus de paiement /////////////////////////////////////////////////////


        Button getcode = dialog1.findViewById(R.id.getcode);
        // TextView numeroachille = dialog2.findViewById(R.id.numeroachille);
        //  numeroachille.setText(NUMEROACHILLEMTN);
        EditText code = dialog1.findViewById(R.id.code);
        ImageView sumitcode = dialog1.findViewById(R.id.sumitcode);
        //Log.e("isll", "user " + user.getPrefix());


        getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
                dialog2.show();
            }
        });


        sumitcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Execution du code de payement

                codedepayement = code.getText().toString();
                // code.setText("");
                dialog1.dismiss();
                //progressDialog.show();

                ////////////////////////////

                progressDialog.show();
                suite();

                ///   //////////////////////////////////////////////////////////////////////////////////////////////////////////
//                sendtoActiveuser(userocal.getIdonline(), userocal.getPhone(), userocal.getPrefix(), codedepayement);
                /////////////////////////////////

            }
        });
        LinearLayout paieorange = dialog2.findViewById(R.id.paieorange);
        paieorange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(), getString(R.string.nombreussmax), Toast.LENGTH_LONG).show();
                //String ussd = client.getCodeussd() + "" + client.getNumero() + "" + montant + "#";
                dialog2.dismiss();
                codeussdi = "#150*1*1*" + NUMERO + "*" + MONTANTSCAN + "#";
                suite();
            }
        });
      /*  LinearLayout paiemtn = dialog2.findViewById(R.id.paiemtn);
        paiemtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
                codeussdi = "*126#";
                initCall();
            }
        });*/


        /////////////////////////////////////fin procecus de paiement


    }

    public void donBackbutton(View view){
        Intent intent = new Intent(DonActivity.this,InfoSujet.class);
        startActivity(intent);
        finish();

    }

    private void suite(){
        mt.animate().translationX(-1400).setDuration(1300).setStartDelay(200);
        lott.animate().translationX(1400).setDuration(1000).setStartDelay(500);
        tv.animate().translationX(1400).setDuration(1000).setStartDelay(500);
        progressDialog.dismiss();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                lot_load.setVisibility(View.VISIBLE);
            }
        },1800);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent home = new Intent(DonActivity.this,MainActivity.class);
                startActivity(home);
                finish();
            }
        },5000);
    }
}