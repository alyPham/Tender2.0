package com.example.tender;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


public class UserSettingsPage extends AppCompatActivity {
    String v, vg, df, gf, hookUp, longTerm;
    private DatabaseReference mDatabase;
    private UserInfo currentUserInfo;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_new_user);
        currentUserInfo = new UserInfo();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        try {
            retrieveUserData((l)-> null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public UserInfo retrieveUserData(final java.util.function.Function<UserInfo, Void> function)
            throws IOException {
        String userID = mDatabase.child("profile").
                child(Objects.requireNonNull(FirebaseAuth.getInstance().
                        getCurrentUser()).getUid()).toString();
        String ID = userID.substring(userID.length() - 28);
        System.out.println("------------------------------userID " + ID);
        File userInfo = new File(UserSettingsPage.this.getFilesDir(), ID);
        if (!userInfo.exists()){
            userInfo.mkdir();
        }



        DatabaseReference vRef = mDatabase.child("profile").
                    child(Objects.requireNonNull(FirebaseAuth.getInstance().
                            getCurrentUser()).getUid()).child("dietary restrictions").child("v");
            vRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    v = snapshot.getValue(String.class);
                    try {
                        File gpxfile = new File(userInfo, "sample");
                        FileWriter writer = new FileWriter(gpxfile);
                        writer.append("v: " + v);
                        writer.flush();
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

//            DatabaseReference vgRef = mDatabase.child("profile").
//                    child(Objects.requireNonNull(FirebaseAuth.getInstance().
//                            getCurrentUser()).getUid()).child("dietary restrictions").child("vg");
//            vgRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    vg = snapshot.getValue(String.class);
//                    try {
//                        writer.append("\nvg: " + vg);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                }
//            });
//
//            DatabaseReference gfRef = mDatabase.child("profile").
//                    child(Objects.requireNonNull(FirebaseAuth.getInstance().
//                            getCurrentUser()).getUid()).child("dietary restrictions").child("gf");
//            gfRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    gf = snapshot.getValue(String.class);
//                    try {
//                        writer.append("\ngf: " + gf);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                }
//            });
//
//            DatabaseReference dfRef = mDatabase.child("profile").
//                    child(Objects.requireNonNull(FirebaseAuth.getInstance().
//                            getCurrentUser()).getUid()).child("dietary restrictions").child("df");
//            dfRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    df = snapshot.getValue(String.class);
//                    try {
//                        writer.append("\ndf: " + df);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                }
//            });
//
//            DatabaseReference hookUpRef = mDatabase.child("profile").
//                    child(Objects.requireNonNull(FirebaseAuth.getInstance().
//                            getCurrentUser()).getUid()).child("relationship").child("hookUp");
//            hookUpRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    hookUp = snapshot.getValue(String.class);
//                    try {
//                        writer.write("\nhookUp: " + hookUp);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                }
//            });
//
//            DatabaseReference longTermRef = mDatabase.child("profile").
//                    child(Objects.requireNonNull(FirebaseAuth.getInstance().
//                            getCurrentUser()).getUid()).child("relationship").child("longTerm");
//            longTermRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    longTerm = snapshot.getValue(String.class);
//                    try {
//                        writer.write("\nlongTerm: " + longTerm);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                }
//            });
//            writer.flush();
//            writer.close();

        function.apply(currentUserInfo);
        return currentUserInfo;
    }

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
