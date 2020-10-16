package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

public class GettingStartedPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_started);

    }
        public void goBack(View view){
            Intent i = new Intent(GettingStartedPage.this, NewUserPage.class);
            startActivity(i);
        }
    }
