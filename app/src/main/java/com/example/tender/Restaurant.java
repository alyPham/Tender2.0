package com.example.tender;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    String name;
    String daysAndHours;
    String dineIn;
    String takeOut;
    String delivery;
    String website;
    String phoneNum;
    String distance;

    public Restaurant(){
        name = "default name";
        daysAndHours = "default days and hours";
        dineIn = "default dine in";
        takeOut = "default takeout";
        delivery = "default delivery";
        website = "default website";
        phoneNum = "default phone number";
        distance = "default distance";
    }

    public Restaurant(String name,
                      String daysAndHours,
                      String dineIn,
                      String takeOut,
                      String delivery,
                      String website,
                      String phoneNum,
                      String distance){
        this.name = name;
        this.daysAndHours = daysAndHours;
        this.dineIn = dineIn;
        this.takeOut = takeOut;
        this.delivery = delivery;
        this.website = website;
        this.phoneNum = phoneNum;
        this.distance = distance;
    }

    protected Restaurant(Parcel in) {
        name = in.readString();
        daysAndHours = in.readString();
        dineIn = in.readString();
        takeOut = in.readString();
        delivery = in.readString();
        website = in.readString();
        phoneNum = in.readString();
        distance = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(daysAndHours);
        dest.writeString(dineIn);
        dest.writeString(takeOut);
        dest.writeString(delivery);
        dest.writeString(website);
        dest.writeString(phoneNum);
        dest.writeString(distance);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getDistance(){return distance;}
    public void setDistance(String distance){this.distance = distance;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDaysAndHours() {
        return daysAndHours;
    }

    public void setDaysAndHours(String daysAndHours) {
        this.daysAndHours = daysAndHours;
    }

    public String getDineIn() {
        return dineIn;
    }

    public void setDineIn(String dineIn) {
        this.dineIn = dineIn;
    }

    public String getTakeOut() {
        return takeOut;
    }

    public void setTakeOut(String takeOut) {
        this.takeOut = takeOut;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
