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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DishManager {
    final long ONE_MEGABYTE = 1024*1024*5;
    FirebaseFirestore db;
    private List<Dish> dishes;
    StorageReference storageReference;

    public DishManager(){
        db = FirebaseFirestore.getInstance();
        StorageReference url = FirebaseStorage.getInstance().
                getReferenceFromUrl("gs://tender2-74c0e.appspot.com/test_alex/Cookie and Cream Milkshake.jpg");
        dishes = new ArrayList<>();
    }

    /**
     * gets a list of dish objects from firebase
     * @param function
     * @return
     */
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
                    System.out.println("-------------------dish toString()" + dish.toString());
                    dishes.add(dish);
                    function.apply(dishes);
                }
            }
        });
        return dishes;
    }

    public void setDishImage(final Dish dish, StorageReference storageReference) {
        storageReference.getBytes(ONE_MEGABYTE)
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
