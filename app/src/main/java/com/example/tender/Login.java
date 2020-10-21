package com.example.tender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mLogin;
    private EditText mEmail, mPassword;
    private ProgressBar mProgressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        mLogin = (Button) findViewById(R.id.login_button);

        mEmail = (EditText) findViewById(R.id.email);

        mPassword = (EditText) findViewById(R.id.password);

        mProgressbar = (ProgressBar) findViewById(R.id.progressbar_login);

        mAuth = FirebaseAuth.getInstance();


        /**
         * When user clicks the login button,
         * check the following criteria and allow login with correct password and email address
         */
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();

                if (email.isEmpty()){ //Check the email address is written or not. Print error statement
                    mEmail.setError("Email is required");
                    mEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ //check the email address is not in a correct format or not (@)
                    mEmail.setError("Please provide valid a email address");
                    mEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()){ //Check password is typed or not. Print error statement
                    mPassword.setError("Password is required");
                    mPassword.requestFocus();
                    return;
                }

                if (password.length() < 6){ //minimum number for password is 6 for Firebase
                    mPassword.setError("Password is at least 6 characters!");
                    mPassword.requestFocus();
                    return;
                }
                mProgressbar.setVisibility(View.VISIBLE); // Progress bar is shown while app is working on the login.


                //Sign in to the app using Firebase builtin method to log in
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(Login.this, UserSettingsPage.class);
                            startActivity(intent);
                            mProgressbar.setVisibility(View.GONE);
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            mProgressbar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

    }
}