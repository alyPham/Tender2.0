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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodProfileFragment extends Fragment {
    private Dish currentDish;
    private Fragment moreInfoFragment;
    private ImageView imageView;
    private ImageButton moreInfo;
    private TextView dishName;
    private TextView priceAndDistance;
    private TextView blurb;


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

        dishName = view.findViewById(R.id.dishname_fragment);
        moreInfo = view.findViewById(R.id.moreInfo_Fragment);
        priceAndDistance = view.findViewById(R.id.priceNDistance);
        blurb = view.findViewById(R.id.DishDescription);
        imageView = view.findViewById(R.id.dishImage_fragment);

        moreInfoFragment = new MoreInfoFragment();

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, moreInfoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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
            setFoodProfile();
        }
    }

    public void setFoodProfile(){
        dishName.setText(currentDish.getName());
        blurb.setText(currentDish.getBlurb());
        priceAndDistance.setText(currentDish.getPriceAndDistance());
        if (currentDish.getBytes().length > 1) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(currentDish.getBytes(), 0, currentDish.getBytes().length);
            imageView.setImageBitmap(bitmap);
        }
    }

}