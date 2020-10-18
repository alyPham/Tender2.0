package com.example.tender;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodProfileFragment extends Fragment {
    private Dish currentDish;

    private ImageView imageView;
    private TextView dishName;
    private TextView priceAndDistance;
    private TextView restaurant;
    private TextView blurb;
    private TextView hours;
    private TextView dineIn;
    private TextView takeOut;
    private TextView delivery;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null){
            currentDish = bundle.getParcelable("currentDish");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_profile, container, false);
        dishName = view.findViewById(R.id.dishname);
        blurb = view.findViewById(R.id.blurb);
        imageView = view.findViewById(R.id.dishimage);
        restaurant = view.findViewById(R.id.restaurant);
        priceAndDistance = view.findViewById(R.id.price_and_distance);
        hours = view.findViewById(R.id.hours);
        dineIn = view.findViewById(R.id.dine_in_option);
        takeOut = view.findViewById(R.id.take_out_option);
        delivery = view.findViewById(R.id.delivery_option);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if (args != null){
            currentDish = args.getParcelable("currentDish");
            setFoodProfile();
        }
    }

    public void setFoodProfile(){
        dishName.setText(currentDish.getName());
        blurb.setText(currentDish.getBlurb());
        priceAndDistance.setText(currentDish.getPriceAndDistance());
        restaurant.setText(currentDish.getRestaurant().getName());
        hours.setText(currentDish.getRestaurant().getDaysAndHours());
        dineIn.setText(currentDish.getRestaurant().getDineIn());
        System.out.println(currentDish.getRestaurant().getTakeOut());
        takeOut.setText(currentDish.getRestaurant().getTakeOut());
        delivery.setText(currentDish.getRestaurant().getDelivery());

        if (currentDish.getBytes().length > 1) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(currentDish.getBytes(),
                    0, currentDish.getBytes().length);
            imageView.setImageBitmap(bitmap);
        }
    }

}