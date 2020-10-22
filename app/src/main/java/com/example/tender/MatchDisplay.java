package com.example.tender;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MatchDisplay extends AppCompatActivity {

    private static final String TAG = "TAG";
    ImageButton home;
    ImageButton back;
    TextView dish1, dish2, dish3, dish4;
    ImageView image1, image2, image3, image4;
    int counter = 0;
    String[] dishNames;
    Map<String, String> map;


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
        map.put("Avocado Toast", "resized_avocado_toast");
        map.put("Bacon Cheese", "resized_bacon_cheese");
        map.put("Blueberry Corn Pancake", "resized_blueberry_corn_pancake");
        map.put("Cookies and Cream Milkshake", "resized_cookies_and_cream_milkshake");
        map.put("Egg & Bacon Croissant", "resized_egg_and_bacon_croissant");
        map.put("Fish & Chips", "resized_fish_and_chips");
        map.put("Fish and Ginger Congee", "resized_fish_and_ginger_congee");
        map.put("Grilled Cheese", "resized_grilled_cheese");
        map.put("House Fried Rice", "resized_house_fried_rice");
        map.put("Lobster & Avocado Toast", "resized_lobster_and_avocado_toast");
        map.put("Mac & Cheese", "resized_mac_and_cheese");
        map.put("Onion Rings", "resized_onion_rings");
        map.put("Rare Beef Pho", "resized_rare_beef_pho");
        map.put("Shrimp Drunken Noodles", "resized_shrimp_drunken_noodles");
        map.put("Spring Roll", "resized_spring_roll");

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

    public void setText() {
        for (String dish : dishNames) {
            counter++;
            if (counter == 1) {
                dish1.setText(dish);
                Resources res = getResources();
                String mDrawableName = map.get(dish);
                int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
                Drawable drawable = res.getDrawable(resID );
                image1.setImageDrawable(drawable);
            }
            if (counter == 2) {
                dish2.setText(dish);
                Resources res = getResources();
                String mDrawableName = map.get(dish);
                int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
                Drawable drawable = res.getDrawable(resID );
                image2.setImageDrawable(drawable);
            }
            if (counter == 3) {
                dish3.setText(dish);
                Resources res = getResources();
                String mDrawableName = map.get(dish);
                int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
                Drawable drawable = res.getDrawable(resID );
                image3.setImageDrawable(drawable);
            }
            if (counter == 4) {
                dish4.setText(dish);
                Resources res = getResources();
                String mDrawableName = map.get(dish);
                int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
                Drawable drawable = res.getDrawable(resID );
                image4.setImageDrawable(drawable);
                counter = 0;
            }
        }

    }

    public void goBack(View view){
//        Intent i = new Intent(MatchDisplay.this, MainActivity.class);
        finish();

    }

    public void displayDish(Dish dish) {
    }
}