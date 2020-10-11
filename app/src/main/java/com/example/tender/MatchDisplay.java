package com.example.tender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchDisplay extends AppCompatActivity {

    private static final String TAG = "TAG";

    TextView dishName1;
    ImageView dishImage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_page);
        Toast.makeText(this, "matched!", Toast.LENGTH_SHORT).show();

        dishName1 = findViewById(R.id.DishName1);
        dishImage1 = findViewById(R.id.DishImage1);

        if (getIntent().hasExtra("matched_dish")){ // prevents NullPointerException
            Dish dish = getIntent().getParcelableExtra("matched_dish");
            System.out.println(dish);
            displayDish(dish);
        }

    }

    public void displayDish(Dish dish){
        dishName1.setText(dish.getName());
        dishImage1.setImageResource(dish.getImgID());
    }
}