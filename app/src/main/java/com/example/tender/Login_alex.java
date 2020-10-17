package com.example.tender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login_alex extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_alex);
        editTextUsername = findViewById(R.id.EmailLoginPage);
        editTextPassword = findViewById(R.id.passwordLoginPage);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(this, MainActivity.class));
            }
        });
    }
}