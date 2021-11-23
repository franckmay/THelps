package com.os.humanhelpss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.os.humanhelpss.data.HelpDBHelper;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    private EditText mail;
    private EditText pwd;
    private Button con;
    private HelpDBHelper hdb;
    private Intent intent;
    private Cursor cursor;
    private LottieAnimationView lott;
    private static final String pass_regex = "^([A-Za-z]{3,40})([ \\t][A-Za-z]{3,40})*$";
    private static final String email_regex = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);
        intent = getIntent();
        hdb = new HelpDBHelper(this);
        mail = findViewById(R.id.et_log_email);
        pwd = findViewById(R.id.et_log_pass);
        con = findViewById(R.id.btn_log_con);
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connecter(v);
            }
        });

    }

    public void onRegisClick(View View) {
        startActivity(new Intent(this, InscriptionActivity.class));

    }

    private void connecter(View v) {

        if (champsCorrect() != 2)
            Snackbar.make(v, "Verifiez les valeurs entrées et essayez encore.", Snackbar.LENGTH_LONG).setAction("", null).show();

        else {
            String m = mail.getText().toString();
            String p = pwd.getText().toString();
            boolean res = hdb.checkUser(m, p);

            if (res) {
                OpenHomePage();
            } else {
                Snackbar.make(v, "identifiant ou mot de passe incorrects.", Snackbar.LENGTH_LONG).setAction("", null).show();

            }
        }


    }

    public void OpenHomePage() {

        startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("email", mail.getText().toString()));
//                startActivity(new Intent(LoginActivity.this, StartingPageActivity.class).putExtra("email", mail.getText().toString()));
//                finish();

    }

    public int champsCorrect() {
        int number_of_matches = 0;
        if (Pattern.matches(pass_regex, pwd.getText().toString()))
            number_of_matches++;
        if (Pattern.matches(email_regex, mail.getText().toString()))
            number_of_matches++;

        return number_of_matches;
    }
}