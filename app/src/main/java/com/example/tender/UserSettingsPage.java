package com.example.tender;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;


public class UserSettingsPage extends AppCompatActivity {

    public void dietPage(View view){
        Intent i = new Intent(UserSettingsPage.this, DietaryPage.class);
        startActivity(i);
    }

    public void locationPage(View view) {
        Intent i = new Intent(UserSettingsPage.this, GettingStartedPage.class);
        startActivity(i);
    }

    public void relationshipPage(View view) {
        Intent i = new Intent(UserSettingsPage.this, Relationship.class);
        startActivity(i);
    }

    public void goToFood(View view) {
        Intent i = new Intent(UserSettingsPage.this, MainActivity.class);
        startActivity(i);
    }
}
