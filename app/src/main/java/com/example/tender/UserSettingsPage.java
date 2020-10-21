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
import java.util.function.Function;


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
//        try {
//            retrieveUserData((l)->{
//    //            System.out.println(l.getV());
//    //            if (currentUserInfo.getV() != null) {
//    //                System.out.println("--------------------------v " + v);
//    //            }
//                return null;
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public UserInfo retrieveUserData(final Function<UserInfo, Void> function) throws IOException {
        File file = new File(UserSettingsPage.this.getFilesDir(), "text");
        if (!file.exists()) {
            file.mkdir();
        }
        File gpxfile = new File(file, "sample");
        FileWriter write = new FileWriter(gpxfile);
        DatabaseReference vRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("dietary restrictions").child("v");
//        DatabaseReference vRef = mDatabase.getRef();
        vRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                v = snapshot.getValue(String.class);
                try {
                    write.append("v: ").append(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentUserInfo.setV(v);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference vgRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("dietary restrictions").child("vg");
        vgRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vg = snapshot.getValue(String.class);
                try {
                    write.append("\nvg: ").append(vg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentUserInfo.setVg(vg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference gfRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("dietary restrictions").child("gf");
        gfRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gf = snapshot.getValue(String.class);
                try {
                    write.append("\ngf: ").append(gf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentUserInfo.setGf(gf);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference dfRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("dietary restrictions").child("df");
        dfRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                df = snapshot.getValue(String.class);
                try {
                    write.append("\ndf: ").append(df);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentUserInfo.setDf(df);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference hookUpRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("relationship").child("hookUp");
        hookUpRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hookUp = snapshot.getValue(String.class);
                try {
                    write.append("\nhookUp: ").append(hookUp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentUserInfo.setHookUp(hookUp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference longTermRef = mDatabase.child("profile").child(Objects.requireNonNull(FirebaseAuth.getInstance().
                getCurrentUser()).getUid()).child("relationship").child("longTerm");
        longTermRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                longTerm = snapshot.getValue(String.class);
                try {
                    write.append("\nlongTerm: ").append(longTerm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentUserInfo.setLongTerm(longTerm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        write.flush();
        write.close();

        function.apply(currentUserInfo);
        System.out.println("CurrentUserInfo: \n" + currentUserInfo);
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
