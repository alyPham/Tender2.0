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

    /**
     * Handles when the Long Term Button is pressed.
     * @param view
     */
    public void setLongTerm(View view){
        if(!longTerm && !hookUp) { //if both buttons are not pressed
            longTerm = true;
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                    getCurrentUser()).getUid()).child("relationship").child("longTerm").setValue("y");
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                    getCurrentUser()).getUid()).child("relationship").child("hookUp").setValue("n");
        }
        else if(longTerm && !hookUp){ //undoes pressing the Long Term button
            longTerm = false;
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                    getCurrentUser()).getUid()).child("relationship").child("longTerm").setValue("n");
        }
        else { //if the Hook-Up button is already pressed, undo it, and then have the Long Term button be pressed
            longTerm = true;
            hookUp = false;
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                    getCurrentUser()).getUid()).child("relationship").child("longTerm").setValue("y");
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                    getCurrentUser()).getUid()).child("relationship").child("hookUp").setValue("n");
        }
    }

    /**
     * Handles when the Hook-Up Button is pressed.
     * @param view
     */
    public void setShortTerm(View view){
        if(!longTerm && !hookUp) { //if both buttons are not pressed
            hookUp = true;
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                    getCurrentUser()).getUid()).child("relationship").child("hookUp").setValue("y");
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                    getCurrentUser()).getUid()).child("relationship").child("longTerm").setValue("n");
        }
        else if(!longTerm){ //undoes pressing the Hook-Up button
            hookUp = false;
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                    getCurrentUser()).getUid()).child("relationship").child("hookUp").setValue("n");
        }
        else { //if the Long Term button is already pressed, undo it, and then have the Hook-Up button be pressed
            hookUp = true;
            longTerm = false;
            shortButton.setBackground(getResources().getDrawable(R.drawable.rounded_button_2));
            longButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                    getCurrentUser()).getUid()).child("relationship").child("longTerm").setValue("n");
            mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                    getCurrentUser()).getUid()).child("relationship").child("hookUp").setValue("y");
        }
    }

    public void goBack(View view){
        Intent i = new Intent(Relationship.this, NewUserPage.class);
        startActivity(i);
    }
}
