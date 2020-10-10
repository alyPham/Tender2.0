package com.example.tender;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DishManager {
    final long ONE_MEGABYTE = 1024*1024;
    FirebaseFirestore db;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    public DishManager(){
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("test_alex");
        databaseReference = FirebaseDatabase.getInstance("https://tender2-74c0e.firebaseio.com/").
                getReference().child("Dish");
    };


    public Dish generateSingleDishObject(){
        Dish i = new Dish();
//        setDishText(i, db);
        System.out.println("----------------------name in the other method" + i.getName());
        return i;
    }

    public List<QueryDocumentSnapshot> getDishes(){
        final CollectionReference ref = db.collection("dish");
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot
            }
        });
        return null;
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
