package com.example.tender;

import android.annotation.SuppressLint;
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

public class DietaryPage extends AppCompatActivity {

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

        getPrefs = getSharedPreferences("preferences", MODE_PRIVATE); //SharedPreferences allows you to store values that you can access later after exiting the activity
        editor = getPrefs.edit();

        setReturningChecks();

    }

    /**
     * Manages all checkbox actions and stores the dietary restrictions of the current user
     * accordingly in both Firebase and within SharedPreferences.
     * @param view
     */
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.vegetarianBox:
                if (checked) {
                    mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                            getCurrentUser()).getUid()).child("dietary restrictions").child("v").setValue("y");
                    vegetarianBox.setSelected(true);
                    editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
                            getInstance().getCurrentUser()).getUid()) + ":vegetarian", true);
                    editor.apply();
                } else {
                    mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                            getCurrentUser()).getUid()).child("dietary restrictions").child("v").setValue("n");
                    vegetarianBox.setSelected(false);
                    editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
                            getInstance().getCurrentUser()).getUid()) + ":vegetarian", false);
                    editor.apply();
                }
                break;
            case R.id.veganBox:
                if (checked) {
                    mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                            getCurrentUser()).getUid()).child("dietary restrictions").child("vg").setValue("y");
                    veganBox.setSelected(true);
                    editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
                            getInstance().getCurrentUser()).getUid()) + ":vegan", true);
                    editor.apply();
                } else {
                    mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                            getCurrentUser()).getUid()).child("dietary restrictions").child("vg").setValue("n");
                    veganBox.setSelected(false);
                    editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
                            getInstance().getCurrentUser()).getUid()) + ":vegan", false);
                    editor.apply();
                }
                break;
            case R.id.dairyBox:
                if (checked) {
                    mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                            getCurrentUser()).getUid()).child("dietary restrictions").child("df").setValue("y");
                    dairyBox.setSelected(true);
                    editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
                            getInstance().getCurrentUser()).getUid()) + ":dairy", true);
                    editor.apply();
                } else {
                    mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                            getCurrentUser()).getUid()).child("dietary restrictions").child("df").setValue("n");
                    dairyBox.setSelected(false);
                    editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
                            getInstance().getCurrentUser()).getUid()) + ":dairy", false);
                    editor.apply();
                }
                break;
            case R.id.glutenBox:
                if (checked) {
                    mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                            getCurrentUser()).getUid()).child("dietary restrictions").child("gf").setValue("y");
                    glutenBox.setSelected(true);
                    editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
                            getInstance().getCurrentUser()).getUid()) + ":gluten", true);
                    editor.apply();
                } else {
                    mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                            getCurrentUser()).getUid()).child("dietary restrictions").child("gf").setValue("n");
                    glutenBox.setSelected(false);
                    editor.putBoolean(mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.
                            getInstance().getCurrentUser()).getUid()) + ":gluten", false);
                    editor.apply();
                }
                break;
        }
    }


    /**
     * Ensures that when the user returns to the Dietary Restrictions page, all boxes that had been
     * checked off previously remain.
     */
    public void setReturningChecks() {
        if (getPrefs.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull
                (FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":vegetarian", true)) {
            vegetarianBox.setChecked(true);
        }
        if (getPrefs.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull
                (FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":vegan", true)) {
            veganBox.setChecked(true);
        }
        if (getPrefs.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull
                (FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":dairy", true)) {
            dairyBox.setChecked(true);
        }
        if (getPrefs.getBoolean(mDatabase.child("profile").child(Objects.requireNonNull
                (FirebaseAuth.getInstance().getCurrentUser()).getUid()) + ":gluten", true)) {
            glutenBox.setChecked(true);
        }
    }

    public void goBack(View view) {
        Intent i = new Intent(DietaryPage.this, UserSettingsPage.class);
        startActivity(i);
    }

    public SharedPreferences getSharedPrefs(){
        return getPrefs;
    }
}

