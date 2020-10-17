package com.example.tender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final Integer LOAD_SIZE = 15;
    double x1, y1, x2, y2;
    DishManager dishManager;
    RestaurantManager restaurantManager;
    List<Dish> generalDishes;
    List<Dish> likedDishes = new ArrayList<>();
    List<Dish> dislikedDishes;

    Dish currentDish;

    FragmentManager fragmentManager;
    Fragment foodProfileFragment;
    Fragment moreInfoFragment;
    Fragment currentFragment;

    ImageButton homeButton;
    ImageButton matchButton;
    ImageButton moreInfo;

    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodProfileFragment = new FoodProfileFragment();
        moreInfoFragment = new MoreInfoFragment();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, foodProfileFragment);
        fragmentTransaction.commit();
        currentFragment = foodProfileFragment;

        homeButton = findViewById(R.id.homebutton);
        matchButton = findViewById(R.id.matchbutton);
//        moreInfo = findViewById(R.id.MoreInfo);
//        moreInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentFragment == foodProfileFragment){
//                    moreInfo.set
//                }
//            }
//        });

        dishManager = new DishManager();
        restaurantManager = new RestaurantManager();
        generalDishes = new ArrayList<>();
//        likedDishes = new ArrayList<>();
        dislikedDishes = new ArrayList<>();

        rand = new Random();

        dishManager.getDishes((l)->{ // l is the dishList generated by get single random dish
            generalDishes = l;
            Collections.shuffle(generalDishes);
            if (generalDishes.size() == LOAD_SIZE && currentDish == null){
                updateCurrentDish();
                passCurrentDishProfile(foodProfileFragment);
            }
            return null;
        });
    }

    //TODO: Implement this method and all the other dietary restrictions
    public void cleanGeneralDishesForDiaryFree(){
        for (Dish i:generalDishes){
//            if (!i.getDF()){
//                generalDishes.remove(i);
//            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent touchEvent) {
        if (currentDish != null) {
            switch (touchEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x1 = touchEvent.getX();
                    y1 = touchEvent.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    x2 = touchEvent.getX();
                    y2 = touchEvent.getY();
                    if (Math.abs(x1 - x2) > 30) {
                        if (x1 > x2) { // swiping left
                            onSwipeLeft();
                        } else if (x1 < x2) { // swiping right
                            onSwipeRight();
                        }
                    }
                    break;
            }
        }
        return false;
    }

    public void passCurrentDishProfile(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putParcelable("currentDish", currentDish);
        System.out.println("---------------------current dish in main activity" + currentDish);
        fragment.setArguments(bundle);
    }

    public void updateCurrentDish(){
        int randomNum = rand.nextInt(generalDishes.size());
        while (dislikedDishes.contains(generalDishes.get(randomNum))) {
            randomNum = rand.nextInt(generalDishes.size());
        }
        currentDish = generalDishes.get(randomNum);
    }

    public void onSwipeRight(){
        likedDishes.add(currentDish);
        System.out.println("------------------------------liked dishes " + likedDishes);
        updateCurrentDish();
        Intent i = new Intent(MainActivity.this, MatchDisplay.class);
//        i.putExtra("matchedList", likedDishes);
        startActivity(i);

    }

    public void onSwipeLeft(){
        dislikedDishes.add(currentDish);
        updateCurrentDish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}