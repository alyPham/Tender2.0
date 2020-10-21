package com.example.tender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MatchDisplay extends AppCompatActivity {

    private static final String TAG = "TAG";
    ImageButton home;
    ImageButton back;
    MainActivity mainActivity;
    String names;
    TextView dish1, dish2, dish3, dish4;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_page);
        Toast.makeText(this, "matched!", Toast.LENGTH_SHORT).show();
        home = findViewById(R.id.home_matched);
        back = findViewById(R.id.back_button4);

//        if (getIntent().hasExtra("matchedList")) { // prevents NullPointerException
//            getIntent().getExtra
//        }

        dish1 = findViewById(R.id.Dish1);
        dish2 = findViewById(R.id.Dish2);
        dish3 = findViewById(R.id.Dish3);
        dish4 = findViewById(R.id.Dish4);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent openMainActivity = new Intent(MatchDisplay.this, UserSettingsPage.class);
                startActivity(openMainActivity);
            }
        }
        );

    }

    public void setText(){

    }

    public void goBack(View view){
//        Intent i = new Intent(MatchDisplay.this, MainActivity.class);
        finish();

    }

    public void displayDish(Dish dish) {
    }
}