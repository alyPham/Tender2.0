package com.example.tender;

import android.graphics.drawable.Drawable;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainProfileTest {

    @Test
    public void initializeDishList() {
        MainProfile mainProfile = new MainProfile();
        mainProfile.initializeDishList();
    }
}