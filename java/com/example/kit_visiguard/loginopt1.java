package com.example.kit_visiguard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class loginopt1 extends AppCompatActivity {
    RelativeLayout admin,security,staff1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginopt1);
        hideSystemUI();
         admin=findViewById(R.id.admin);
        security=findViewById(R.id.security);
        staff1=findViewById(R.id.staff1);
        staff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tostaflog();
            }
        });
        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toseclog();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toadlog();

            }
        });

    }
    public void toadlog()
    {
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }
    public void toseclog()
    {
        Intent i=new Intent(this,AdminLogin.class);
        startActivity(i);
    }
    public void tostaflog()
    {
        Intent i=new Intent(this,staff.class);
        startActivity(i);
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