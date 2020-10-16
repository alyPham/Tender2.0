package com.example.tender;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;


public class NewUserPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_new_user);
    }

    public void dietPage(View view){
        Intent i = new Intent(NewUserPage.this, DietaryPage.class);
        startActivity(i);
    }

    public void locationPage(View view) {
        Intent i = new Intent(NewUserPage.this, GettingStartedPage.class);
        startActivity(i);
    }

    public void relationshipPage(View view) {
        Intent i = new Intent(NewUserPage.this, Relationship.class);
        startActivity(i);
    }

    public void goToFood(View view) {
        Intent i = new Intent(NewUserPage.this, MainActivity.class);
        startActivity(i);
    }
}
