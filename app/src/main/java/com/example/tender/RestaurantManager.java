package com.example.tender;

import androidx.annotation.Nullable;

import com.google.common.base.Function;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {
    FirebaseFirestore db;
    List<Restaurant> restaurants;

    public RestaurantManager(){
        db = FirebaseFirestore.getInstance();
        restaurants = new ArrayList<>();
    }

    public List<Restaurant> getRestaurants(final Function<List<Restaurant>, Void> function){

        db.collection("Dish").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot snapshot : value){
                    Restaurant restaurant = new Restaurant();
                    restaurant.setName(snapshot.get("name").toString());
                    restaurant.setDaysAndHours(snapshot.get("price").toString());
                    restaurants.add(restaurant);
                    function.apply(restaurants);

                }
            }
        });
        return restaurants;
    }
}
