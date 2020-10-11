package com.example.tender;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.grpc.internal.JsonUtil;

public class DishManager {
    final long ONE_MEGABYTE = 1024*1024;
    FirebaseFirestore db;
    private CollectionReference dishRef;
    private List<String> dishes;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    public DishManager(){
        db = FirebaseFirestore.getInstance();
        dishRef = db.collection("dish");
        storageReference = FirebaseStorage.getInstance().getReference().child("test_alex");
        databaseReference = FirebaseDatabase.getInstance("https://tender2-74c0e.firebaseio.com/").
                getReference().child("Dish");

        dishes = new ArrayList<>();
    };


    public Dish generateSingleDishObject(){
        Dish i = new Dish();
//        setDishText(i, db);
        System.out.println("----------------------name in the other method" + i.getName());
        return i;
    }

    public List<String> getDishes(){
        db.collection("dish").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot snapshot:value){
                    dishes.add(snapshot.getString("name"));
                }
            }
        });
        System.out.println(dishes);
        return dishes;
    }

//    public Dish setDishText(final Dish dish, FirebaseFirestore db){
//        //asynchronously retrieve multiple documents
//        ApiFuture<QuerySnapshot> dishes =
//                db.collection("dish").get();
//        // future.get() blocks on response
//        List<QueryDocumentSnapshot> documents = dishes.;
//        for (DocumentSnapshot document : documents) {
//            System.out.println(document.getId() + " => " + document.toObject(City.class));
//        }
//        return dish;
//    }

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
