package com.example.tender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MoreInfo extends AppCompatActivity {
    ImageButton backToMain;
    Restaurant currentRestaurant;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info);

        backToMain = findViewById(R.id.BackToMain);

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreInfo.this, MainProfile.class);
                startActivity(i);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        float y1 = 0,y2;
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                y2 = touchEvent.getY();
                if (y1 < y2) { // swipe down
                    Intent i = new Intent(MoreInfo.this, MainProfile.class);
                    startActivity(i);
                    return false;
                }
                break;
        }
        return false;
    }
}
