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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mRegister;
    private EditText mEmail, mPassword;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        mAuth = FirebaseAuth.getInstance();

        mRegister = (Button) findViewById(R.id.register_button);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mProgressbar = (ProgressBar) findViewById(R.id.progressbar_signup);

        /**
         * If the user press Regiter button, update the id and password to Firebase Authentication,
         * then add & update user info into the realtime database.
         */
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();

                if (email.isEmpty()){ //Check whether a user provided email
                    mEmail.setError("Email is required");
                    mEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ //Check whether email is in valid form
                    mEmail.setError("Please provide valid a email address");
                    mEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()){ //Check whether a user provided password
                    mPassword.setError("Password is required");
                    mPassword.requestFocus();
                    return;
                }

                if (password.length() < 6){ //Minimum characters for Firebase password are 6
                    mPassword.setError("At least 6 characters required");
                    mPassword.requestFocus();
                    return;
                }

                mProgressbar.setVisibility(View.VISIBLE); //Progressbar is shown while the app is registering user into Firebase

                // This method creates a user account based on the email address and password
                // Then it saves the email and password in UserInfo class which is used to update Realtime Database
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            UserInfo user = new UserInfo(email, password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                // This method allows the app to move to new user page after the registration is complete
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUp.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                        mProgressbar.setVisibility(View.GONE);

                                        Intent intent = new Intent(SignUp.this, NewUserPage.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        Toast.makeText(SignUp.this, "Sign Up Error", Toast.LENGTH_SHORT);
                                        mProgressbar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(SignUp.this, "Sign Up Error", Toast.LENGTH_SHORT).show();
                            mProgressbar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

    }
}