package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_welcome);
    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        if (touchEvent.getAction() == MotionEvent.ACTION_UP){
            Intent i = new Intent(WelcomePage.this, NewUserPage.class);
            startActivity(i);
        }
        return false;
    }

}