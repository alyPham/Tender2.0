package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GettingStartedPage extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    List<Integer> imageList;
    List<String> explanationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_started);

        imageView = (ImageView) findViewById(R.id.tutorial_img);
        textView = (TextView) findViewById(R.id.explanation);

        imageList = new ArrayList<>();
        explanationList = new ArrayList<>();

        addImages();
        addExplanations();
    }

    /**
     * Allows the user to click through the tutorial's images and explanations.
     * @param view
     */
    public void nextSlide(View view) {

        int temp = imageList.get(0);
        String temp2 = explanationList.get(0);

        imageView.setImageResource(temp);
        textView.setText(temp2);

        moveToBack(temp, temp2);
    }




    public void goBack(View view) {
        Intent i = new Intent(GettingStartedPage.this, UserSettingsPage.class);
        startActivity(i);
    }

    public void addImages() {
        imageList.add(R.drawable.food_profile_info_example);
        imageList.add(R.drawable.food_profile_home_example);
        imageList.add(R.drawable.food_profile_match_example);
        imageList.add(R.drawable.food_profile_swipe_example);
    }

    public void addExplanations() {
        explanationList.add("Dish and corresponding restaurant information.");
        explanationList.add("Click to return to home screen/user settings.");
        explanationList.add("Click to view all dishes you've matched with.\n Caution! You can only swipe 4 dishes!");
        explanationList.add("Swipe left to dislike, swipe right to like.");
    }

    /**
     * Once a user moves on to the next image-explanation pair, it removes the previous pair and
     * re-adds it to the back of the list to ensure a cycle.
     * @param temp
     * @param temp2
     */
    public void moveToBack(int temp, String temp2) {
        imageList.remove(0);
        explanationList.remove(0);
        imageList.add(temp);
        explanationList.add(temp2);
    }
}
