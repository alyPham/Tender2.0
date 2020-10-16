package com.example.tender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    DishManager dishManager;
    List<Dish> generalDishes;
    List<Dish> likedDishes;
    List<Dish> dislikedDishes;
    Dish currentDish;
    Restaurant currentRestaurant;

    FragmentManager fragmentManager;
    Fragment foodProfileFragment;
    Fragment moreInfoFragment;

    ImageButton homeButton;
    ImageButton matchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        foodProfileFragment = new FoodProfileFragment();
        moreInfoFragment = new MoreInfoFragment();

        fragmentTransaction.add(R.id.fragment_container, foodProfileFragment);
        fragmentTransaction.commit();

        homeButton = findViewById(R.id.homebutton_fragment);
        matchButton = findViewById(R.id.matchbutton_fragment);

        dishManager = new DishManager();
        generalDishes = new ArrayList<>();
        likedDishes = new ArrayList<>();
        dislikedDishes = new ArrayList<>();

//        generalDishes = dishManager.getDishesTest();
//        System.out.println("--——---——------------ general dishes: " + generalDishes);

        dishManager.getDishes((l)->{ // l is the dishList generated by get single random dish
            Random rand = new Random();
            generalDishes = l;
//
////            System.out.println(" -------------------------------Shuffled general dishes");
            Collections.shuffle(generalDishes);
//            for (Dish i :generalDishes){
//                System.out.println(i.getName());
//                }

            int randomNum = rand.nextInt(generalDishes.size());
//            System.out.println("---------------------random number generated: " + randomNum);
//            currentDish = generalDishes.get(randomNum);
//
//            System.out.println("---------------------currentDish in MainActivity: "
//                    + currentDish.getName());
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("currentDish", currentDish);
//            foodProfileFragment.setArguments(bundle);


            return null;
        });

        System.out.println("------------------------general dishes outside: " + generalDishes);

    }



}