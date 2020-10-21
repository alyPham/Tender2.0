package com.example.tender;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.base.Function;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final Integer LOAD_SIZE = 15;
    final long ONE_MEGABYTE = 1024*1024*5;
    double x1, y1, x2, y2;
    private String v, vg, gf, df, hookUp, longTerm;
    private UserInfo currentUserInfo;
    DishManager dishManager;
    RestaurantManager restaurantManager;
    List<Dish> generalDishes;
    String[] likedDishes;
    List<Dish> dislikedDishes;
    List<Dish> customizedDishes;
    Dish currentDish;

    FragmentManager fragmentManager;
    FoodProfileFragment foodProfileFragment;
    Fragment currentFragment;

    ImageButton homeButton;
    ImageButton matchButton;

    DietaryPage dietaryPage;
    SharedPreferences sharedPref;

    Random rand;
    private DatabaseReference mDatabase;
    StorageReference storageReference;
    FirebaseFirestore db;

    TextView dish1, dish2, dish3, dish4;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        foodProfileFragment = new FoodProfileFragment();
        currentUserInfo = new UserInfo();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, foodProfileFragment);
        fragmentTransaction.commit();
        currentFragment = foodProfileFragment;

        homeButton = findViewById(R.id.homebutton);
        matchButton = findViewById(R.id.matchbutton);

        dishManager = new DishManager();
        restaurantManager = new RestaurantManager();
        generalDishes = new ArrayList<>();
        likedDishes = new String[4];
        dislikedDishes = new ArrayList<>();

        dietaryPage = new DietaryPage();
        sharedPref =getSharedPreferences("preferences", MODE_PRIVATE);
        rand = new Random();

        dish1 = findViewById(R.id.Dish1);
        dish2 = findViewById(R.id.Dish2);
        dish3 = findViewById(R.id.Dish3);
        dish4 = findViewById(R.id.Dish4);

        editDietaryRestrictions();
        System.out.println(sharedPref);

        dishManager.getDishes((l)->{ // l is the dishList generated by get single random dish
            generalDishes = l;
            //TODO: customize generalDishes according to user settings
            Collections.shuffle(generalDishes);

            if (generalDishes.size() == LOAD_SIZE && currentDish == null){
//                editDietaryRestrictions();
                updateCurrentDish();
                passCurrentDishProfile(foodProfileFragment); // display dish data in food profile fragment
            }
            return null;
        });

//        System.out.println("------------------general dishes outside " + generalDishes.size());
//        System.out.println("------------df is " + df);
    }


//    LongTermRelationship(){
//        for (Dish i:generalDishes){
//           if  i.getRestaurant().getDineIn().equals("no") {
//                generalDishes.remove(i);
//            }
//        }
//    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public UserInfo retrieveUserData(final java.util.function.Function<UserInfo, Void> function){
        DatabaseReference vRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("dietary restrictions").child("v");
//        DatabaseReference vRef = mDatabase.getRef();
        vRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                v = snapshot.getValue(String.class);
                currentUserInfo.setV(v);
                System.out.println(v);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference vgRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("dietary restrictions").child("vg");
        vgRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vg = snapshot.getValue(String.class);
                currentUserInfo.setVg(vg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference gfRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("dietary restrictions").child("gf");
        gfRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gf = snapshot.getValue(String.class);
                currentUserInfo.setGf(gf);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference dfRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("dietary restrictions").child("df");
        dfRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                df = snapshot.getValue(String.class);
                currentUserInfo.setDf(df);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference hookUpRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("relationship").child("hookUp");
        hookUpRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hookUp = snapshot.getValue(String.class);
                currentUserInfo.setHookUp(hookUp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference longTermRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("relationship").child("longTerm");
        longTermRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                longTerm = snapshot.getValue(String.class);
                currentUserInfo.setLongTerm(longTerm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        function.apply(currentUserInfo);
        return currentUserInfo;
    }

    public void editDietaryRestrictions(){
        for (Dish dish:generalDishes){
            System.out.println(sharedPref.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
                    getInstance().getCurrentUser()).getUid()) + ":vegetarian", true));
            if (sharedPref.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
                    getInstance().getCurrentUser()).getUid()) + ":vegetarian", true)){
                deleteNonVegetarian(dish);
            }
//            if (sharedPref.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
//                    getInstance().getCurrentUser()).getUid()) + ":vegan", true)){
//                deleteNonVegan(dish);
//            }
//            if(sharedPref.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
//                    getInstance().getCurrentUser()).getUid()) + ":dairy", true)){
//                deleteDairy(dish);
//            }
//            if(sharedPref.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
//                    getInstance().getCurrentUser()).getUid()) + ":gluten", true)){
//                deleteGluten(dish);
//            }
        }
    }

    public void retrieveUserInfo(){

    }

    //TODO: Implement this method and all the other dietary restrictions
    public void deleteGluten(Dish dish){
        if (dish.getGlutenFree().equals("n")){
            generalDishes.remove(dish);
        }
    }

    public void deleteDairy(Dish dish){
        if (dish.getDairyFree().equals("n")){
            generalDishes.remove(dish);
        }
    }

    public void deleteNonVegetarian(Dish dish){
        if (dish.getVegetarian().equals("n")){
            generalDishes.remove(dish);
        }
    }

    public void deleteNonVegan(Dish dish){
        if (dish.getVegan().equals("n")){
            generalDishes.remove(dish);
        }
    }

    public List<Dish> getDishes(final Function<List<Dish>, Void> function){

        db.collection("Dish").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot snapshot : value){
                    Dish dish = new Dish();
                    dish.setName(snapshot.get("name").toString());
                    Restaurant restaurant = new Restaurant();
                    if (snapshot.get("restaurant") instanceof Map) {
                        String distance = ((Map<String, String>) snapshot.get("restaurant")).
                                get("distance");
                        String hours = ((Map<String, String>) snapshot.get("restaurant")).
                                get("hours");
                        String name = ((Map<String, String>) snapshot.get("restaurant")).
                                get("name");
                        String delivery = ((Map<String, String>) snapshot.get("restaurant")).
                                get("delivery");
                        String dineIn = ((Map<String, String>) snapshot.get("restaurant")).
                                get("dine in");
                        String pickup = ((Map<String, String>) snapshot.get("restaurant")).
                                get("takeout");
                        String website = ((Map<String, String>) snapshot.get("restaurant")).
                                get("website");
                        String phoneNum = ((Map<String, String>) snapshot.get("restaurant")).
                                get("phone number");
                        restaurant.setPhoneNum(phoneNum);
                        restaurant.setWebsite(website);
                        restaurant.setDineIn(dineIn);
                        restaurant.setDelivery(delivery);
                        restaurant.setTakeOut(pickup);
                        restaurant.setDistance(distance);
                        restaurant.setName(name);
                        restaurant.setDaysAndHours(hours);
                        dish.setRestaurant(restaurant);
                        dish.setPriceAndDistance("$" + snapshot.get("price").toString() + " | " + distance);
                    }
                    dish.setBlurb(snapshot.get("blurb").toString());
                    dish.setGlutenFree(snapshot.get("gf").toString());
                    dish.setDairyFree(snapshot.get("df").toString());
                    dish.setVegetarian(snapshot.get("v").toString());
                    dish.setVegan(snapshot.get("vg").toString());
//                    System.out.println("-----------------------Dish Name: " + dish.getName());
                    String ref = "public/" + dish.getName() + ".png";
//                    System.out.println("------------------------reference: " + ref);
                    storageReference = FirebaseStorage.getInstance().getReference().child(ref);

                    setDishImage(dish, storageReference);
                    generalDishes.add(dish);
                    function.apply(generalDishes);
                }
            }
        });
        return generalDishes;
    }

    public void setDishImage(final Dish dish, StorageReference storageReference) {
        storageReference.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        dish.setBytes(bytes);
//                        foodProfileFragment.setFoodProfile();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    /**
     * Detects the swiping gestures on the screen. Responds to a left swipe and a right swipe.
     * @param touchEvent been detected. Either a left swipe or a right swipe. Has to be
     *                   beyond a certain value to confirm that the user has a strong tendency
     *                   to swipe and it's not just a touch by mistake.
     * @return false
     */
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

    /**
     * Pass current dish object to the fragment
     * @param fragment to receive current dish object
     */
    public void passCurrentDishProfile(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putParcelable("currentDish", currentDish);
        fragment.setArguments(bundle);
    }

    /**
     * Choose a random dish from unsorted dish list which hasn't been disliked by the user.
     */
    public void updateCurrentDish(){
        int randomNum = rand.nextInt(generalDishes.size());
        while (dislikedDishes.contains(generalDishes.get(randomNum))) {
            randomNum = rand.nextInt(generalDishes.size());
        }
        currentDish = generalDishes.get(randomNum);
    }

    /**
     * adds currentDish to likedDishes list, updates a random currentDish
     * and starts the MatchDisplay activity.
     */
    public void onSwipeRight(){

        likedDishes[counter] = currentDish.getName();
        counter++;
//        String temp = currentDish.getName();
//        if(counter == 1){
//            dish1.setText(temp);
//        }
//        if(counter == 2){
//            dish2.setText(temp);
//        }
//        if(counter == 3){
//            dish3.setText(temp);
//        }
//        if(counter == 4){
//            dish4.setText(temp);
//            counter = 0;
//        }
        updateCurrentDish();
        Toast.makeText(this, "MATCH!", Toast.LENGTH_SHORT).show();
        passCurrentDishProfile(foodProfileFragment);
    }

//    public String getLikedDishes(){
//        StringBuilder str = new StringBuilder();
//        for (Dish dish: likedDishes){
//            str.append(dish.getName());
//        }
//        return str.toString();
//    }

//    public List<Dish> getLikedDishes(){
//        return likedDishes;
//    }


    /**
     * add currentDish to dislikedDishes list, updates a random currentDish,
     * and pass it to foodProfileFragment to display.
     */
    public void onSwipeLeft(){
        dislikedDishes.add(currentDish);
        updateCurrentDish();
        Toast.makeText(this, "DISLIKE!", Toast.LENGTH_SHORT).show();
        passCurrentDishProfile(foodProfileFragment);
    }

    public void goBackHome(View view){
        Intent i = new Intent(MainActivity.this, UserSettingsPage.class);
        startActivity(i);
    }

    public void goToMatch(View view){
        Intent i = new Intent(MainActivity.this, MatchDisplay.class);

        i.putExtra("key", likedDishes);

//        StringBuilder str = new StringBuilder();
//        for (Dish dish: likedDishes){
//
//            str.append("dish" + i + dish.getName());
//        }
        startActivity(i);
    }
}