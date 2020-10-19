package com.example.tender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DietaryPage extends AppCompatActivity{

        private DatabaseReference mDatabase;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dietary_restrictions);

            mDatabase = FirebaseDatabase.getInstance().getReference();

        }

        public void onCheckboxClicked(View view){
            boolean checked  = ((CheckBox) view).isChecked();

            switch(view.getId()){
                case R.id.vegetarianBox:
                    if(checked){
                       mDatabase.child("profile").child("user1").child("dietary restrictions").child("v").setValue("y");
                    } else {
                        mDatabase.child("profile").child("user1").child("dietary restrictions").child("v").setValue("n");
                    }
                    break;
                case R.id.veganBox:
                    if(checked) {
                        mDatabase.child("profile").child("user1").child("dietary restrictions").child("vg").setValue("y");
                    } else {
                        mDatabase.child("profile").child("user1").child("dietary restrictions").child("vg").setValue("n");
                    }
                    break;
                case R.id.dairyBox:
                    if(checked){
                        mDatabase.child("profile").child("user1").child("dietary restrictions").child("df").setValue("y");
                    } else {
                        mDatabase.child("profile").child("user1").child("dietary restrictions").child("df").setValue("n");
                    }
                    break;
                case R.id.glutenBox:
                    if(checked){
                        mDatabase.child("profile").child("user1").child("dietary restrictions").child("gf").setValue("y");
                    } else {
                        mDatabase.child("profile").child("user1").child("dietary restrictions").child("gf").setValue("n");
                    }
                    break;
            }
        }
        public void goBack(View view){
            Intent i = new Intent(DietaryPage.this, NewUserPage.class);
            startActivity(i);
        }
}
