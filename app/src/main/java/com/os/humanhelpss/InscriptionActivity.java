package com.os.humanhelpss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.os.humanhelpss.data.HelpDBHelper;

import java.util.regex.Pattern;

public class InscriptionActivity extends AppCompatActivity {

    private Button save;
    private EditText user_name, user_email, user_phone, user_pass;
    private static final String name_regex = "^([A-Za-z]{3,40})([ \\t][A-Za-z]{3,40})*$";
    private static final String email_regex = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";
    private static final String phone_regex = "^[0-9]{7,}$";
    private HelpDBHelper emdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);


        emdb = new HelpDBHelper(this);

        user_name = findViewById(R.id.editTextName);
        user_email = findViewById(R.id.editTextEmail);
        user_pass = findViewById(R.id.editTextPass);
        user_phone = findViewById(R.id.editTextPhone);
        save = findViewById(R.id.btn_inscription);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (champsCorrects() != 3)
                    Snackbar.make(v, "Verifiez les valeurs entrées et essayez encore.", Snackbar.LENGTH_LONG).setAction("", null).show();
                else {

                    boolean res = emdb.checkUser(user_email.getText().toString());

                    if (res) {
                        Snackbar.make(v, "Un utilsateur est déja enregistré avec cet email.", Snackbar.LENGTH_LONG).setAction("", null).show();
                    } else {
                        if (emdb.addUser(user_name.getText().toString(), user_email.getText().toString(), user_pass.getText().toString(), user_phone.getText().toString())) {
                            Log.i("user ---", "USER ENTERED SUCCESSFULLY");
                            Snackbar.make(v, "UTILISATEUR ENREGISTRE AVEC SUCCES.", Snackbar.LENGTH_LONG).setAction("", null).show();
                            onLoginClick(v);
                        } else {
                            Snackbar.make(v, "FAILED TO ENTER CURRENT USER. TRY AGAIN LATER.", Snackbar.LENGTH_LONG).setAction("", null).show();
                        }
                    }
                }
            }
        });

    }


    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));

    } public int champsCorrects() {
        int number_of_matches = 0;
        if (Pattern.matches(name_regex, user_name.getText().toString()))
            number_of_matches++;
        if (Pattern.matches(email_regex, user_email.getText().toString()))
            number_of_matches++;
        if (Pattern.matches(phone_regex, user_phone.getText().toString()))
            number_of_matches++;

        Log.i("opps ---", "done bro :)" + number_of_matches);
        return number_of_matches;
    }
}