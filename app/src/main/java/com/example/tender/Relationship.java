package com.example.tender;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Relationship extends AppCompatActivity {

    boolean longTerm = false;
    boolean hookUp = false;

    Button longButton = (Button) findViewById(R.id.longButton);
    Button shortButton = (Button) findViewById(R.id.shortButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relationship);
    }

    public void setLongTerm(View view){
        if(!longTerm && !hookUp){
            longTerm = true;
            longButton.setBackgroundColor(Color.parseColor("#F86868"));
        }
        if(longTerm){
            longTerm = false;
            longButton.setBackgroundColor(Color.parseColor("#FF8B8B"));
        }
    }

    public void setShortTerm(View view){
        if(!longTerm && !hookUp){
            hookUp = true;
            shortButton.setBackgroundColor(Color.parseColor("#F86868"));
        }
        if(hookUp){
            hookUp = false;
            shortButton.setBackgroundColor(Color.parseColor("#FF8B8B"));
        }
    }

    public void goBack(View view){
        Intent i = new Intent(Relationship.this, NewUserPage.class);
        startActivity(i);
    }
}
