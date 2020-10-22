package com.example.tender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MatchDisplay extends AppCompatActivity {

    private static final String TAG = "TAG";
    ImageButton home;
    ImageButton back;
    TextView dish1, dish2, dish3, dish4;
    ImageView image1, image2, image3, image4;
    int counter = 0;
    String[] dishNames;
    Map<String, Integer> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_page);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dishNames = extras.getStringArray("key");
        }

        home = findViewById(R.id.home_matched);
        back = findViewById(R.id.back_button4);

        dish1 = findViewById(R.id.dishName1);
        dish2 = findViewById(R.id.dishName2);
        dish3 = findViewById(R.id.dishName3);
        dish4 = findViewById(R.id.dishName4);

        image1 = findViewById(R.id.dishImage1);
        image2 = findViewById(R.id.dishImage2);
        image3 = findViewById(R.id.dishImage3);
        image4 = findViewById(R.id.dishImage4);

        map = new HashMap<>();
        map.put("Avocado Toast", R.drawable.resized_avocado_toast);
        map.put("Bacon Cheese", R.drawable.resized_bacon_cheese);
        map.put("Blueberry Corn Pancake", R.drawable.resized_blueberry_corn_pancake);
        map.put("Cookies and Cream Milkshake",R.drawable.resized_cookies_and_cream_milkshake);
        map.put("Egg & Bacon Croissant", R.drawable.resized_egg_and_bacon_croissant);
        map.put("Fish & Chips", R.drawable.resized_fish_and_chips);
        map.put("Fish and Ginger Congee", R.drawable.resized_fish_and_ginger_congee);
        map.put("Grilled Cheese", R.drawable.resized_grilled_cheese);
        map.put("House Fried Rice", R.drawable.resized_house_fried_rice);
        map.put("Lobster & Avocado Toast", R.drawable.resized_lobster_and_avocado_toast);
        map.put("Mac & Cheese", R.drawable.resized_mac_and_cheese);
        map.put("Onion Rings", R.drawable.resized_onion_rings);
        map.put("Rare Beef Pho", R.drawable.resized_rare_beef_pho);
        map.put("Shrimp Drunken Noodles", R.drawable.resized_shrimp_drunken_noodles);
        map.put("Spring Roll", R.drawable.resized_spring_roll);

        setText();

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

    /**
     * Shows the dishes the user matched with (image and name only).
     */
    public void setText() {
        for (String dish : dishNames) {
            if (dish != null) {
                if (counter == 0) {
                    dish1.setText(dish);
                    int id = map.get(dish);
                    image1.setImageResource(id);
                    counter++;
                }
                else if (counter == 1) {
                    dish2.setText(dish);
                    int id = map.get(dish);
                    image2.setImageResource(id);
                    counter++;
                }
                else if (counter == 2) {
                    dish3.setText(dish);
                    int id = map.get(dish);
                    image3.setImageResource(id);
                    counter++;
                }
                else if (counter == 3) {
                    dish4.setText(dish);
                    int id = map.get(dish);
                    image4.setImageResource(id);
                    counter = 0;
                }
            }
        }

    }

    public void goBack(View view){
        Intent i = new Intent(MatchDisplay.this, MainActivity.class);
        finish();
    }

}