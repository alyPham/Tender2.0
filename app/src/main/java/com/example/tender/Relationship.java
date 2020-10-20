package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Relationship extends AppCompatActivity {

    private DatabaseReference mDatabase;

    boolean longTerm = false;
    boolean hookUp = false;
    Button longButton;
    Button shortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relationship);

        longButton = findViewById(R.id.longButton);
        shortButton = findViewById(R.id.shortButton);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void setLongTerm(View view){
        if(!longTerm && !hookUp) {
            longTerm = true;
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("relationship").child("longTerm").setValue("y");
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("relationship").child("hookUp").setValue("n");
        }
        else if(longTerm && !hookUp){
            longTerm = false;
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("relationship").child("longTerm").setValue("n");
        }
        else {
            longTerm = true;
            hookUp = false;
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("relationship").child("longTerm").setValue("y");
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("relationship").child("hookUp").setValue("n");
        }
    }

    public void setShortTerm(View view){
        if(!longTerm && !hookUp) {
            hookUp = true;
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("relationship").child("hookUp").setValue("y");
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("relationship").child("longTerm").setValue("n");
        }
        else if(!longTerm){
            hookUp = false;
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("relationship").child("hookUp").setValue("n");
        }
        else {
            hookUp = true;
            longTerm = false;
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("relationship").child("longTerm").setValue("n");
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("relationship").child("hookUp").setValue("y");
        }
    }

    public void goBack(View view){
        Intent i = new Intent(Relationship.this, NewUserPage.class);
        startActivity(i);
    }
}
