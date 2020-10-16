package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainProfile extends AppCompatActivity {
    float x1, x2, y1, y2;

    ImageButton moreInfo;

    ImageView dishImageView;
    TextView nameTextView, dishDescriptionView, distanceView;

    List<Dish> dishListTemp;
    List<Restaurant> restaurantsListTemp;
    List<Dish> swipeLeftDishes; //dishlikes
    List<Dish> likedDishes;

    List<Dish> dishList;
    DishManager dishManager;
    Map<String, ArrayList<String>> dishMap;
    Dish currentDish;
    Restaurant currentRestaurant;

    Restaurant theNeighborhoodCafe;
    Restaurant shish;
    Restaurant indochin;
    Dish countryFriedSteakAndEggs;
    Dish chickenShawarma;
    Dish rareBeefPho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_profile);

        moreInfo = findViewById(R.id.MoreInfo);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(currentRestaurant.getDistance());
                Intent i = new Intent(MainProfile.this, MoreInfo.class);
                i.putExtra("currentDish", currentDish);
                i.putExtra("currentRestaurant", currentRestaurant);
                startActivity(i);
            }

        });

        dishImageView = findViewById(R.id.dishimage);
        nameTextView = findViewById(R.id.dishname);
        dishDescriptionView = findViewById(R.id.dishdescription);
        distanceView = findViewById(R.id.price);

        dishListTemp = new ArrayList<>();
        restaurantsListTemp = new ArrayList<>();

        swipeLeftDishes = new ArrayList<>();
        likedDishes = new ArrayList<>();

        dishList = new ArrayList<>();
        dishManager = new DishManager();
        dishMap = new HashMap<>();

//        dishManager.getDishes((l)->{ // l is the single dish generated by get single random dish
//            Random rand = new Random();
//            Dish d = l.get(rand.nextInt(l.size()));
//            setProfile(d);
//            return null;
//        });

        initializeHardCodeDishNRest();
        System.out.println("---------------------List initialized:" + dishListTemp);
        initializeDefaultProfilePage();
    }


    // Test only


    /**
     * Sets up this page with the information from the dish object
     *
     * @param dish displayed on the profile page
     */
    public void setProfile(Dish dish) {
        currentDish = dish;
        dishImageView.setImageResource(dish.getImgID());
        nameTextView.setText(dish.getName());
        dishDescriptionView.setText(dish.getBlurb());
    }

    public void initializeHardCodeDishNRest() {
        indochin = new Restaurant(
                getString(R.string.indochinVietnameseRestaurantName),
                getString(R.string.indochinVietnameseRestaurantHours),
                convertStrIdtoString(R.string.indochinVietnameseRestaurantDineIn),
                convertStrIdtoString(R.string.indochinVietnameseRestaurantTakeout),
                convertStrIdtoString(R.string.indochinVietnameseRestaurantDelivery),
                convertStrIdtoString(R.string.indochinVietnameseRestaurantWebsite),
                convertStrIdtoString(R.string.indochinVietnameseRestaurantPhoneNum),
                getString(R.string.indochine_distance)
        );
        theNeighborhoodCafe = new Restaurant(
                getString(R.string.NCname),
                convertStrIdtoString(R.string.theNeighborhoodCafeHours),
                convertStrIdtoString(R.string.theNeighborhoodCafeDineIn),
                convertStrIdtoString(R.string.theNeighborhoodCafeTakeout),
                convertStrIdtoString(R.string.theNeighborhoodCafeDelivery),
                convertStrIdtoString(R.string.theNeighborhoodCafeWebsite),
                convertStrIdtoString(R.string.theNeighborhoodCafePhoneNum),
                getString(R.string.theNeighborhoodCafeDistance)
        );
        shish = new Restaurant(
                convertStrIdtoString(R.string.ShishMediterraneanRestaurantName),
                convertStrIdtoString(R.string.ShishMediterraneanRestaurantHours),
                convertStrIdtoString(R.string.ShishMediterraneanRestaurantDineIn),
                convertStrIdtoString(R.string.ShishMediterraneanRestaurantTakeout),
                convertStrIdtoString(R.string.ShishMediterraneanRestaurantDelivery),
                convertStrIdtoString(R.string.ShishMediterraneanRestaurantWebsite),
                convertStrIdtoString(R.string.ShishMediterraneanRestaurantPhoneNum),
                getString(R.string.shishDistance)
        );

        countryFriedSteakAndEggs = new Dish(R.drawable.countryfriedsteak,
                getString(R.string.countryFriedSteakAndEggsName),
                convertStrIdtoString(R.string.countryFriedSteakAndEggsDescription),
                convertStrIdtoString(R.string.countryFriedSteakAndEggsDistance),
                theNeighborhoodCafe);
        chickenShawarma = new Dish(R.drawable.chickenshawarma,
                convertStrIdtoString(R.string.chickenShawarmaName),
                convertStrIdtoString(R.string.chickenShawarmaDescription),
                convertStrIdtoString(R.string.chickenShawarmaDistance),
                shish);
        rareBeefPho = new Dish(R.drawable.chickenshawarma,
                convertStrIdtoString(R.string.chickenShawarmaName),
                convertStrIdtoString(R.string.chickenShawarmaDescription),
                convertStrIdtoString(R.string.chickenShawarmaDistance),
                shish);
        initializeDishListTemp();
        initializeRestaurantListTemp();

    }

    /**
     * creates a default temporary dish list. Test only.
     * TODO: Should be deleted later if firestore is implemented successfully.
     */
    public void initializeDishListTemp() {
        dishListTemp.add(countryFriedSteakAndEggs);
        dishListTemp.add(chickenShawarma);
        dishListTemp.add(rareBeefPho);
    }

    public void initializeRestaurantListTemp() {
        restaurantsListTemp.add(theNeighborhoodCafe);
        restaurantsListTemp.add(shish);
        restaurantsListTemp.add(indochin);

    }

    /**
     * Hard-coding the dishList list. Reads in data from all files in res directory
     */
    //TODO: Should read in data from firebase and
    //      generates lists in random orders.
//    public void initializeDishList() {
//        // hardcoded for now but will be changed into random auto-generated lists
////        dishList.add(new Dish(R.drawable.countryfriedsteak,
////                R.string.country_fried_steak_and_egg,
////                R.string.CFSE_description,
////                R.string.distance,
////                new Restaurant()));
////
////        dishList.add(new Dish(R.drawable.chickenshawarma,
////                R.string.ChickenShawarma,
////                R.string.ChickenShawarmaDescription,
////                R.string.distance,
////                new Restaurant())
////        );
//    }

    /**
     * Initializes the default profile page.
     */
    //TODO: Should display a random dish selected from dishList
    public void initializeDefaultProfilePage() {
        //TODO: change dishListTemp to dishList
        Random ran = new Random();
        int i = ran.nextInt(dishListTemp.size());
        Dish dish = dishListTemp.get(i);
        currentDish = dish;
        currentRestaurant = currentDish.getRestaurant();
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
                if (Math.abs(x1 - x2) > 30) {
                    System.out.println("swipe detected");
                    if (x1 > x2) { // swiping left
                        dislikeCurrentDish();
                    } else if (x1 < x2) { // swiping right
                        likeCurrentDish();
                    }
                }

                break;
        }
        return false;
    }

    /**
     * Deletes current dish from dishList, adds current dish to swipeLeftlist,
     * and load a new dish to display
     */
    public void dislikeCurrentDish() {

            swipeLeftDishes.add(currentDish);
            dishListTemp.remove(currentDish);
            if (dishListTemp.size() > 0) {
                setProfile(dishListTemp.get(0));
                System.out.println("--------------------------------Current dish disliked, swiping left");
                System.out.println("----------------------------Original dish list " + dishListTemp);
                System.out.println("----------------------------liked dish list " + likedDishes);
                System.out.println("----------------------------swipe left dish list " + swipeLeftDishes);
            }
    }

    /**
     * Moves to the new activity, add current dish to likedList,
     * and load a new dish to display
     * TODO: feel free to add more features to swiping right as long as you think it's necessary
     */
    public void likeCurrentDish() {

            likedDishes.add(currentDish);
            dishListTemp.remove(currentDish);
        if (dishListTemp.size() > 0) {

            setProfile(dishListTemp.get(0));

            System.out.println("--------------------------------Current dish liked, swiping right");
            System.out.println("----------------------------Original dish list " + dishListTemp);
            System.out.println("----------------------------liked dish list " + likedDishes);
            System.out.println("----------------------------swipe left dish list " + swipeLeftDishes);
        }

        Intent i = new Intent(MainProfile.this, MatchDisplay.class);
        i.putExtra("matched_dish", currentDish);
//        j.putExtra()
        startActivity(i);

    }

    //helper methods
    public String convertStrIdtoString(int ID) {
        return getString(ID);
    }

    public Dish getCurrentDish() {
        return currentDish;
    }
}
