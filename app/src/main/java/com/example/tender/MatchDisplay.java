package com.example.tender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchDisplay extends AppCompatActivity {

    private static final String TAG = "TAG";

    TextView dishName1;
    ImageView dishImage1;
    ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_page);
        Toast.makeText(this, "matched!", Toast.LENGTH_SHORT).show();

//        dishName1 = findViewById(R.id.DishName1);
//        dishImage1 = findViewById(R.id.DishImage1);

        if (getIntent().hasExtra("matched_dish")) { // prevents NullPointerException
            Dish dish = getIntent().getParcelableExtra("matched_dish");
            System.out.println(dish);
            displayDish(dish);
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MatchDisplay.this, MainActivity.class);
                startActivity(i);
            }
        }
        );

    }

    public void displayDish(Dish dish) {
        dishName1.setText(dish.getName());
        dishImage1.setImageResource(dish.getImgID());
    }
}