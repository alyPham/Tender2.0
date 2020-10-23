package com.example.tender;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * @Author Alex Li
 * Stores dish data as individual strings, the image as a byte array，
 * and stores the restaurant information as a restaurant object.
 */
public class Dish implements Parcelable{
    private byte[] bytes;
    private String name;
    private String blurb;
    private Restaurant restaurant;
    private String priceAndDistance;
    private String glutenFree;
    private String dairyFree;
    private String vegetarian;
    private String vegan;

    public Dish(){
        bytes = new byte[1];
        name = "default name";
        blurb = "default blurb";
        priceAndDistance = "default price";
        restaurant = new Restaurant();
        glutenFree = "n";
        dairyFree = "n";
        vegetarian = "n";
        vegan = "n";
    }


    public Dish(byte[] bytes, String name, String blurb, String priceAndDistance, Restaurant restaurant,
                String glutenFree, String dairyFree, String vegetarian, String vegan){
        this.bytes = bytes;
        this.name = name;
        this.blurb = blurb;
        this.priceAndDistance = priceAndDistance;
        this.restaurant = restaurant;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
    }

    protected Dish(Parcel in) {
        bytes = in.createByteArray();
        name = in.readString();
        blurb = in.readString();
        restaurant = in.readParcelable(Restaurant.class.getClassLoader());
        priceAndDistance = in.readString();
        glutenFree = in.readString();
        dairyFree = in.readString();
        vegetarian = in.readString();
        vegan = in.readString();
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

    public String getDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(String dairyFree) {
        this.dairyFree = dairyFree;
    }

    public String getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(String vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getVegan() {
        return vegan;
    }

    public void setVegan(String vegan) {
        this.vegan = vegan;
    }

    public void setGlutenFree(String glutenFree){this.glutenFree = glutenFree;}
    public String getGlutenFree(){return glutenFree;}

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

    @NonNull
    @Override
    public String toString() {
        return "name: " + this.name
                + "\n priceAndDistance: " + this.priceAndDistance
                + "\n blurb: " + this.blurb
                + "\n glutenFree: " + glutenFree
                + "\n vegan: " + vegan
                + "\n vegetarian: " + vegetarian
                + "\n dairyFree: " + dairyFree
                + "\n glutenFree: "+ glutenFree
                + "\n restaurantName: " + restaurant.getName()
                + "\n restaurantHours: " + restaurant.getDaysAndHours()
                + "\n restaurantDineIn: " + restaurant.getDineIn();
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
        dest.writeString(glutenFree);
        dest.writeString(dairyFree);
        dest.writeString(vegetarian);
        dest.writeString(vegan);
    }
}
