package com.example.kit_visiguard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.kit_visiguard.AdminLogin;
import com.example.kit_visiguard.helpers.InputValidation;
import com.example.kit_visiguard.sql.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private ConstraintLayout constraintLayout;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private Button btnLogin;

    private TextView tvLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hideSystemUI();
        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textInputLayoutEmail = findViewById(R.id.til_email);
        textInputLayoutPassword = findViewById(R.id.til_password);

        textInputEditTextEmail = findViewById(R.id.et_email);
        textInputEditTextPassword = findViewById(R.id.et_password);

        btnLogin = findViewById(R.id.btn_login);
        tvLinkRegister = findViewById(R.id.tv_linkRegister);

        // Set OnClickListener for tvLinkRegister
        tvLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), outpassreg.class);
                startActivity(intentRegister);
            }
        });
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        btnLogin.setOnClickListener(this);
        tvLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     *
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            verifyFromSQLite();
        } else if (v.getId() == R.id.tv_linkRegister) {
            // Navigate to RegisterActivity
            Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intentRegister);
        }
    }


    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(),
                textInputEditTextPassword.getText().toString().trim())) {
            Intent intent = new Intent(activity,outpassreg.class);
            intent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(intent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(constraintLayout, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

    public void Click_Me(View v) {
        Intent i = new Intent(this, AdminLogin.class);
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
