package com.example.tender;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tender.R;

public class FoodProfileFragment extends Fragment {
    private Dish currentDish;
    private Restaurant restaurant;
    private ImageButton moreInfo;
    private Fragment moreInfoFragment;

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

        moreInfoFragment = new MoreInfoFragment();
        moreInfo = view.findViewById(R.id.moreInfo_Fragment);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, moreInfoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

//        System.out.println("-------------------------received" + currentDish.getName());

        // Inflate the layout for this fragment
        return view;
    }
}