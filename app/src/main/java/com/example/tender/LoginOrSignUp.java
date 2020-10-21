package com.example.tender;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.function.Function;

public class LoginOrSignUp extends AppCompatActivity {

    private Button user_Login, user_SignUp;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_sign_up);


        user_Login = (Button) findViewById(R.id.button_login);
        user_SignUp = (Button) findViewById(R.id.button_signup);

        user_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginOrSignUp.this, Login.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        user_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginOrSignUp.this, SignUp.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

}
