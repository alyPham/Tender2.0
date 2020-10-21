package com.example.tender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MatchDisplay extends AppCompatActivity {

    private static final String TAG = "TAG";
    ImageButton home;
    ImageButton back;

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

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent openMainActivity = new Intent(MatchDisplay.this, NewUserPage.class);
                startActivity(openMainActivity);
            }
        }
        );

    }

    public void goBack(View view){
//        Intent i = new Intent(MatchDisplay.this, MainActivity.class);
        finish();

    }

    public void displayDish(Dish dish) {
    }
}