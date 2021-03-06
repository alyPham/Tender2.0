package com.example.tender;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Author Alex Li
 * The fragment displayed in the MainActivity fragment container. Receives the currentDish
 * object passed by MainActivity and displays it on screen.
 */

public class FoodProfileFragment extends Fragment {
    private Dish currentDish;
    private String websiteUri;
    private String phoneNum;

    private ImageView imageView;
    private TextView dishName;
    private TextView priceAndDistance;
    private TextView restaurant;
    private TextView blurb;
    private TextView hours;
    private TextView dineIn;
    private TextView takeOut;
    private TextView delivery;
    private ImageButton website;
    private ImageButton phone;


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
        website = view.findViewById(R.id.website_button);
        phone = view.findViewById(R.id.phone_button);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(websiteUri));
                startActivity(intent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+phoneNum));
                startActivity(callIntent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if (args != null){
            currentDish = args.getParcelable("currentDish");
            websiteUri = currentDish.getRestaurant().getWebsite();
            phoneNum = currentDish.getRestaurant().getPhoneNum();
            setFoodProfile();
        }
    }

    /**
     * Displays the currentDish onto the screen. Calls all the view objects and sets its
     * content corresponding to currentDish.
     */
    public void setFoodProfile(){
        dishName.setText(currentDish.getName());
        blurb.setText(currentDish.getBlurb());
        priceAndDistance.setText(currentDish.getPriceAndDistance());
        restaurant.setText(currentDish.getRestaurant().getName());
        hours.setText(currentDish.getRestaurant().getDaysAndHours());
        dineIn.setText(currentDish.getRestaurant().getDineIn());
        takeOut.setText(currentDish.getRestaurant().getTakeOut());
        delivery.setText(currentDish.getRestaurant().getDelivery());

        if (currentDish.getBytes().length > 1) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(currentDish.getBytes(),
                    0, currentDish.getBytes().length);
            imageView.setImageBitmap(bitmap);
        }
    }

}