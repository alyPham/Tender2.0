package com.example.tender;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DietaryPage extends AppCompatActivity{

        private DatabaseReference mDatabase;

        CheckBox vegetarianBox;
        CheckBox veganBox;
        CheckBox dairyBox;
        CheckBox glutenBox;

        SharedPreferences getPrefs;
        SharedPreferences.Editor editor;

        @SuppressLint("CommitPrefEdits")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dietary_restrictions);

            mDatabase = FirebaseDatabase.getInstance().getReference();

            vegetarianBox = findViewById(R.id.vegetarianBox);
            veganBox = findViewById(R.id.veganBox);
            dairyBox = findViewById(R.id.dairyBox);
            glutenBox = findViewById(R.id.glutenBox);

           getPrefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);
           editor = getPrefs.edit();

           setReturningChecks();

        }

        public void onCheckboxClicked(View view){
            boolean checked  = ((CheckBox) view).isChecked();

            switch(view.getId()){
                case R.id.vegetarianBox:
                    if(checked){
                       mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("dietary restrictions").child("v").setValue("y");
                       vegetarianBox.setSelected(true);
                       editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":vegetarian", true);
                       editor.commit();
                    } else {
                        mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("dietary restrictions").child("v").setValue("n");
                        vegetarianBox.setSelected(false);
                        editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":vegetarian", false);
                        editor.commit();
                    }
                    break;
                case R.id.veganBox:
                    if(checked) {
                        mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("dietary restrictions").child("vg").setValue("y");
                        veganBox.setSelected(true);
                        editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":vegan", true);
                        editor.commit();
                    } else {
                        mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("dietary restrictions").child("vg").setValue("n");
                        veganBox.setSelected(false);
                        editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":vegan", false);
                        editor.commit();
                    }
                    break;
                case R.id.dairyBox:
                    if(checked){
                        mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("dietary restrictions").child("df").setValue("y");
                        dairyBox.setSelected(true);
                        editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":dairy", true);
                        editor.commit();
                    } else {
                        mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("dietary restrictions").child("df").setValue("n");
                        dairyBox.setSelected(false);
                        editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":dairy", false);
                        editor.commit();
                    }
                    break;
                case R.id.glutenBox:
                    if(checked){
                        mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("dietary restrictions").child("gf").setValue("y");
                        glutenBox.setSelected(true);
                        editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":gluten", true);
                        editor.commit();
                    } else {
                        mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("dietary restrictions").child("gf").setValue("n");
                        glutenBox.setSelected(false);
                        editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":gluten", false);
                        editor.commit();
                    }
                    break;
            }
        }


        public void setReturningChecks(){
           if (getPrefs.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":vegetarian", true)){
               vegetarianBox.setChecked(true);
           }
           if (getPrefs.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":vegan", true)){
               veganBox.setChecked(true);
           }
           if(getPrefs.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":dairy", true)){
               dairyBox.setChecked(true);
           }
           if(getPrefs.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":gluten", true)){
               glutenBox.setChecked(true);
           }
        }
        public void goBack(View view){
            Intent i = new Intent(DietaryPage.this, NewUserPage.class);
            startActivity(i);
        }
}
