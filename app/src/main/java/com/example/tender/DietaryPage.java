package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class DietaryPage extends AppCompatActivity{

        boolean isVegetarian = false;
        boolean isVegan = false;
        boolean hasDairyAllergy = false;
        boolean hasNutAllergy = false;
        boolean hasGlutenAllergy = false;
        boolean hasFishAllergy = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dietary_restrictions);
        }

        public void onCheckboxClicked(View view){
            boolean checked  = ((CheckBox) view).isChecked();

            switch(view.getId()){
                case R.id.vegetarianBox:
                    if(checked){
                       isVegetarian = true;
                    }
                case R.id.veganBox:
                    if(checked) {
                        isVegan = true;
                    }
                case R.id.dairyBox:
                    if(checked){
                        hasDairyAllergy = true;
                    }
                case R.id.nutBox:
                    if(checked){
                        hasNutAllergy = true;
                    }
                case R.id.glutenBox:
                    if(checked){
                        hasGlutenAllergy = true;
                    }
                case R.id.fishBox:
                    if(checked){
                        hasFishAllergy = true;
                    }
            }
        }
        public void goBack(View view){
            Intent i = new Intent(DietaryPage.this, NewUserPage.class);
            startActivity(i);
        }
}
