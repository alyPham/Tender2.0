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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DishManager {
    final long ONE_MEGABYTE = 1024*1024;
    FirebaseFirestore db;
    private List<Dish> dishes;
    StorageReference storageReference;

    public DishManager(){
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("test_alex");

        dishes = new ArrayList<>();
    }

    public List<Dish> getDishes(final Function<List<Dish>, Void> function){

        db.collection("Dish").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot snapshot : value){
                    Dish dish = new Dish();
                    dish.setName(snapshot.get("name").toString());
                    dish.setPriceAndDistance(snapshot.get("price").toString());
                    Restaurant restaurant = new Restaurant();
                    if (snapshot.get("restaurant") instanceof Map) {
                        // TODO: add more gets to set up a complete restaurant object
                        String distance = ((Map<String, String>) snapshot.get("restaurant")).get("distance");
                        System.out.println(distance);
                        dish.setRestaurant(new Restaurant());
                    }
                    if (snapshot.get("blurb") != null){
                        dish.setBlurb(snapshot.get("blurb").toString());
                    }
                    setDishImage(dish, storageReference);
                    dishes.add(dish);
                    function.apply(dishes);

                }
            }
        });
        return dishes;
    }

    public void setDishImage(final Dish dish, StorageReference storageReference) {
        storageReference.child(dish.getName()).getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                dish.setBytes(bytes);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
}
