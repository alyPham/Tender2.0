package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class LoginOrSignUp extends AppCompatActivity {

    private Button user_Login, user_SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_sign_up);

        user_Login = (Button) findViewById(R.id.button_login);
        user_SignUp = (Button) findViewById(R.id.button_signup);

        user_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginOrSignUp.this, Login_alex.class);
                startActivity(intent);
                finish();
            }
        });

        user_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginOrSignUp.this, SignUp_alex.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
