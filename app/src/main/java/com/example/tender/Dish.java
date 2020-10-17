package com.example.tender;

import android.os.Parcel;
import android.os.Parcelable;

public class Dish implements Parcelable{
    private byte[] bytes;
    private String name;
    private String blurb;
    private Restaurant restaurant;
    private String priceAndDistance;

    public Dish(){
        bytes = new byte[1];
        name = "default name";
        blurb = "default blurb";
        priceAndDistance = "default price";
        restaurant = new Restaurant();
    }

    public Dish(byte[] bytes, String name, String blurb, String priceAndDistance, Restaurant restaurant){
        this.bytes = bytes;
        this.name = name;
        this.blurb = blurb;
        this.priceAndDistance = priceAndDistance;
        this.restaurant = restaurant;
    }

    protected Dish(Parcel in) {
        bytes = in.createByteArray();
        name = in.readString();
        blurb = in.readString();
        restaurant = in.readParcelable(Restaurant.class.getClassLoader());
        priceAndDistance = in.readString();
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    public void setBytes(byte[] bytes){
        this.bytes = bytes;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBlurb(String blurb){
        this.blurb = blurb;
    }

    public void setPriceAndDistance(String priceAndDistance){this.priceAndDistance = priceAndDistance;}
    public void setRestaurant(Restaurant restaurant){this.restaurant = restaurant;}

    public String getPriceAndDistance(){return priceAndDistance;}

    public byte[] getBytes(){
        return bytes;
    }

    public String getName(){
        return name;
    }

    public String getBlurb(){
        return blurb;
    }

    public Restaurant getRestaurant(){
        return restaurant;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(bytes);
        dest.writeString(name);
        dest.writeString(blurb);
        dest.writeParcelable(restaurant, flags);
        dest.writeString(priceAndDistance);
    }
}
