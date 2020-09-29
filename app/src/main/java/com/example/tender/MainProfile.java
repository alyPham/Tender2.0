package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class MainProfile extends AppCompatActivity {
    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_profile);
    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        System.out.println("OnTouchEvent detected");
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (Math.abs(x1 - x2) < Math.abs(y1 - y2)) { // swiping more in the y direction - up/down
                    if (y1 > y2) { // swipe up
                        Intent i = new Intent(MainProfile.this, MoreInfo.class);
                        startActivity(i);
                    } else if (y1 < y2) { // swipe down: ??
//                        Intent i = new Intent(MainProfile.this, SwipeDown.class);
//                        startActivity(i);
                    }
                }
                else { // swiping more in the x direction - left or right
                    if (x1 > x2){
                        Intent i = new Intent(MainProfile.this, SwipeLeft.class);
                        startActivity(i);
                    } else if (x1 < x2) {
                        Intent i = new Intent(MainProfile.this, MatchPage.class);
                        startActivity(i);
                    }
                }
                break;
        }
        return false;
    }
}
