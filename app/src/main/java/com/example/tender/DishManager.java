package com.example.tender;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.base.Function;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DishManager {
    final long ONE_MEGABYTE = 1024*1024;
    FirebaseFirestore db;
    private List<Dish> dishes;
    StorageReference storageReference;

    public DishManager(){
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("test_alex");

        dishes = new ArrayList<>();
    };


    public Dish generateSingleDishObject(){
        Dish i = new Dish();
//        setDishText(i, db);
        System.out.println("----------------------name in the other method" + i.getName());
        return i;
    }


    public List<Dish> getDishesTest(){
        db.collection("Dish").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot snapshot : value){
                    Dish dish = new Dish();
                    System.out.println(snapshot.get("name").toString());
////                    System.out.println(snapshot.get("blurb").toString());
//                    System.out.println(snapshot.get("price").toString());

                    dish.setName(snapshot.get("name").toString());
//                    dish.setBlurb(snapshot.get("blurb").toString());
                    dish.setPrice(snapshot.get("price").toString());
                    dishes.add(dish);
                }
            }
        });
        return null;
    }

    public List<Dish> getDishes(final Function<List<Dish>, Void> function){

        db.collection("Dish").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot snapshot : value){
                    Dish dish = new Dish();
//                    System.out.println(snapshot.get("name").toString());
////                    System.out.println(snapshot.get("blurb").toString());
//                    System.out.println(snapshot.get("price").toString());

                    dish.setName(snapshot.get("name").toString());
//                    dish.setBlurb(snapshot.get("blurb").toString());
                    dish.setPrice(snapshot.get("price").toString());
                    dishes.add(dish);

                    Random rand = new Random();
//                    System.out.println("---------------------random dish generated within the method "
//                            + dishes.get(rand.nextInt(dishes.size())).getName());

                    function.apply(dishes);

                }
            }
        });
        return dishes;
    }

    public void setDishImage(final Dish dish, StorageReference storageReference) throws IOException {
        File localFile = File.createTempFile("", "jpg");
        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    public Dish getRandomDish(List<Dish> dishes){
        Random rand = new Random();
        return dishes.get(rand.nextInt(dishes.size()));
    }

}
