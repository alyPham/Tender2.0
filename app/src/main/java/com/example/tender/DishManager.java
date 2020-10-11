package com.example.tender;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
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


public class DishManager {
    final long ONE_MEGABYTE = 1024*1024;
    FirebaseFirestore db;
    private List<Dish> dishes;
    StorageReference storageReference;
    DatabaseReference databaseReference;

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

    public List<Dish> getDishes(){

        db.collection("dish").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot snapshot : value){
                    Dish dish = new Dish();
                    System.out.println(snapshot.get("name").toString());
                    System.out.println(snapshot.get("description").toString());
                    System.out.println(snapshot.get("distance").toString());

                    dish.setName(snapshot.get("name").toString());
                    dish.setDescription(snapshot.get("description").toString());
                    dish.setDistance(snapshot.get("distance").toString());
                    dishes.add(dish);
                    System.out.println("----------new dish added: \n" + dishes);
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

}
