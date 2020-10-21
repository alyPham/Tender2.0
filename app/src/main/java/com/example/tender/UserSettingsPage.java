package com.example.tender;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserSettingsPage extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String df, gf, v, vg;
    private String hookUp, longTerm;
    private UserInfo currentUserInfo;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_new_user);
        currentUserInfo = new UserInfo();
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        retrieveUserData((l)->{
//            System.out.println(l.getV());
//            if (currentUserInfo.getDf() != null)
//            System.out.println("--------------------------v " + v);
//            return null;
//        });
    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public UserInfo retrieveUserData(final Function<UserInfo, Void> function){
////        DatabaseReference vRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
////                getCurrentUser()).getUid()).child("dietary restrictions").child("v");
//        DatabaseReference vRef = mDatabase.getRef();
//        vRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                v = snapshot.getValue(String.class);
//                currentUserInfo.setV(v);
//                System.out.println(v);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//        DatabaseReference vgRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
//                getCurrentUser()).getUid()).child("dietary restrictions").child("vg");
//        vgRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                vg = snapshot.getValue(String.class);
//                currentUserInfo.setVg(vg);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//        DatabaseReference gfRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
//                getCurrentUser()).getUid()).child("dietary restrictions").child("gf");
//        gfRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                gf = snapshot.getValue(String.class);
//                currentUserInfo.setGf(gf);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//        DatabaseReference dfRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
//                getCurrentUser()).getUid()).child("dietary restrictions").child("df");
//        dfRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                df = snapshot.getValue(String.class);
//                currentUserInfo.setDf(df);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//        DatabaseReference hookUpRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
//                getCurrentUser()).getUid()).child("relationship").child("hookUp");
//        hookUpRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                hookUp = snapshot.getValue(String.class);
//                currentUserInfo.setHookUp(hookUp);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//        DatabaseReference longTermRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
//                getCurrentUser()).getUid()).child("relationship").child("longTerm");
//        longTermRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                longTerm = snapshot.getValue(String.class);
//                currentUserInfo.setLongTerm(longTerm);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//        function.apply(currentUserInfo);
//        return currentUserInfo;
//    }

    public void dietPage(View view){
        Intent i = new Intent(UserSettingsPage.this, DietaryPage.class);
        startActivity(i);
    }

    public void locationPage(View view) {
        Intent i = new Intent(UserSettingsPage.this, GettingStartedPage.class);
        startActivity(i);
    }

    public void relationshipPage(View view) {
        Intent i = new Intent(UserSettingsPage.this, Relationship.class);
        startActivity(i);
    }

    public void goToFood(View view) {
        Intent i = new Intent(UserSettingsPage.this, MainActivity.class);
        startActivity(i);
    }
}
