package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainProfile extends AppCompatActivity {
    float x1, x2, y1, y2;

    ImageView dishImageView;
    TextView nameTextView, dishDescriptionView, distanceView;

    List<Dish> dishList;
    DishManager dishManager;
    Map<String, ArrayList<String>> dishMap;

//    Dish dish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Dish");


        setContentView(R.layout.food_profile);

        dishImageView = findViewById(R.id.dishimage);
        nameTextView = findViewById(R.id.dishname);
        dishDescriptionView = findViewById(R.id.dishdescription);
        distanceView = findViewById(R.id.distance);

        dishList = new ArrayList<>();
        dishManager = new DishManager();
        dishMap = new HashMap<>();

        setProfile(dishManager.generateSingleDishObject());
//        initializeDishList();
//        initializeDefaultProfilePage();
    }


    // Test only


    /**
     * Sets up this page with the information from the dish object
     *
     * @param dish displayed on the profile page
     */
    public void setProfile(Dish dish) {
        dishImageView.setImageResource(dish.getImgID());
        nameTextView.setText(dish.getName());
        dishDescriptionView.setText(dish.getDescription());
        distanceView.setText(dish.getDistance());
    }

    /**
     * Hard-coding the dishList list. Reads in data from all files in res directory
     */
    //TODO: Should read in data from firebase and
    //      generates lists in random orders.
    public void initializeDishList() {
        // hardcoded for now but will be changed into random auto-generated lists
//        dishList.add(new Dish(R.drawable.countryfriedsteak,
//                R.string.country_fried_steak_and_egg,
//                R.string.CFSE_description,
//                R.string.distance,
//                new Restaurant()));
//
//        dishList.add(new Dish(R.drawable.chickenshawarma,
//                R.string.ChickenShawarma,
//                R.string.ChickenShawarmaDescription,
//                R.string.distance,
//                new Restaurant())
//        );
    }

    /**
     * Initializes the default profile page.
     */
    //TODO: Should display a random dish selected from dishList
    public void initializeDefaultProfilePage() {
        Dish dish = dishList.get(0);
        setProfile(dish);
    }

    /**
     * listens to any touch event on the screen.
     * Swiping left means disliking the current dish and will give a new food profile page.
     * Swiping right means liking the current dish and will redirect to a page with matching information.
     *
     * @param touchEvent on the screen that will be responded with different actions.
     * @return false for default
     */
    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();

                if (x1 > x2) { // swiping left
                    setProfile(dishList.get(1));
                } else if (x1 < x2) { // swiping right
                    Intent i = new Intent(MainProfile.this, MatchPage.class);
                    //TODO: set up new MatchPage UI & Match List page
                    startActivity(i);
                }

                break;
        }
        return false;
    }
}
