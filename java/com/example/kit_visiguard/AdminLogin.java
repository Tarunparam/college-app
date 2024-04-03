package com.example.kit_visiguard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.kit_visiguard.toform1;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {

    private TextInputEditText Name;
    private TextInputEditText Password;
    private TextView noOfAttempts;
    private Button Login;
    private int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        hideSystemUI();
        Name = findViewById(R.id.et_username);
        Password = findViewById(R.id.et_adminPassword);
        noOfAttempts =(TextView)findViewById(R.id.tvInfo);
        Login =(Button)findViewById(R.id.btn_adLogin);

        noOfAttempts.setText("No of Attempts Remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void validate(String userName, String userPassword){
        if ((userName.equals("security")) &&(userPassword.equals("12345")) ){

            Intent intent= new Intent(AdminLogin.this, toform1.class);
            startActivity(intent);

        }else{
            counter--;

            noOfAttempts.setText("No of attempts remaining:  "+String.valueOf(counter));

            if (counter == 0){
                Login.setEnabled(false);
            }

        }
    }
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        }
    }
}






