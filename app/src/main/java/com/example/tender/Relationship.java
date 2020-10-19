package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
            mDatabase.child("profile").child("user1").child("relationship").child("longTerm").setValue("y");
        }
        else if(longTerm && !hookUp){
            longTerm = false;
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child("user1").child("relationship").child("longTerm").setValue("n");
        }
        else if(hookUp){
            longTerm = true;
            hookUp = false;
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child("user1").child("relationship").child("longTerm").setValue("y");
            mDatabase.child("profile").child("user1").child("relationship").child("hookUp").setValue("n");
        }
    }

    public void setShortTerm(View view){
        if(!longTerm && !hookUp) {
            hookUp = true;
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            mDatabase.child("profile").child("user1").child("relationship").child("hookUp").setValue("y");
        }
        else if(!longTerm && hookUp){
            hookUp = false;
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child("user1").child("relationship").child("hookUp").setValue("n");
        }
        else if(longTerm){
            hookUp = true;
            longTerm = false;
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child("user1").child("relationship").child("longTerm").setValue("n");
            mDatabase.child("profile").child("user1").child("relationship").child("hookUp").setValue("y");
        }
    }

    public void goBack(View view){
        Intent i = new Intent(Relationship.this, NewUserPage.class);
        startActivity(i);
    }
}
