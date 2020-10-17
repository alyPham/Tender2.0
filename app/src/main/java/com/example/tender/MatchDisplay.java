package com.example.tender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MatchDisplay extends AppCompatActivity {

    private static final String TAG = "TAG";
    ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_page);
        Toast.makeText(this, "matched!", Toast.LENGTH_SHORT).show();
        home = findViewById(R.id.home_matched);

        if (getIntent().hasExtra("matchedList")) { // prevents NullPointerException
            getIntent().getExtra

        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent openMainActivity = new Intent(MatchDisplay.this, MainActivity.class);
                openMainActivity.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(openMainActivity);
            }
        }
        );

    }

    public void displayDish(Dish dish) {
    }
}