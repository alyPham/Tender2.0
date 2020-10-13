package com.example.tender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MoreInfo extends AppCompatActivity {
    ImageButton backToMain;
    Restaurant currentRestaurant;
    Dish currentDish;
    ImageView dishImage;
    TextView distance,
            dishName,
            restaurantName,
            hours,
            website,
            dineIn,
            takeOut,
            delivery,
            phoneNum;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info);

        currentRestaurant = getIntent().getParcelableExtra("currentRestaurant");
        currentDish = getIntent().getParcelableExtra("currentDish");

        backToMain = findViewById(R.id.BackToMain);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreInfo.this, MainProfile.class);
                startActivity(i);
            }
        });
        System.out.println(currentRestaurant.getDistance());

        dishImage = findViewById(R.id.dishImage_moreInfo);
        distance = findViewById(R.id.distance_moreinfo);
        dishName = findViewById(R.id.dishname_moreinfo);
        restaurantName = findViewById(R.id.restaurantname);
        hours = findViewById(R.id.hours);
        website = findViewById(R.id.website);
        dineIn = findViewById(R.id.dineInOption);
        takeOut = findViewById(R.id.takeOutOption);
        delivery = findViewById(R.id.deliveryOption);
        phoneNum = findViewById(R.id.phoneNum);
        displayRestaurantInfo();
    }

    public void displayRestaurantInfo(){
        dishImage.setImageResource(currentDish.getImgID());
        distance.setText(currentRestaurant.getDistance());
        dishName.setText(currentDish.getName());
        restaurantName.setText(currentRestaurant.getName());
        hours.setText(currentRestaurant.getDaysAndHours());
        website.setText(currentRestaurant.getWebsite());
        dineIn.setText(currentRestaurant.getDineIn());
        takeOut.setText(currentRestaurant.getTakeOut());
        delivery.setText(currentRestaurant.getDelivery());
        phoneNum.setText(currentRestaurant.getPhoneNum());
    }
}
