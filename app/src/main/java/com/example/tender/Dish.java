package com.example.tender;

import android.os.Parcel;
import android.os.Parcelable;

public class Dish implements Parcelable {
    private Integer ImgID;
    private String name;
    private String blurb;
    private Restaurant restaurant;
    private String price;

    public Dish(){
        ImgID = R.drawable.default_dish_image;
        name = "default name";
        blurb = "default blurb";
        price = "default price";
        restaurant = new Restaurant();
    }

    public Dish(int ImgID, String name, String blurb, String price, Restaurant restaurant){
        this.ImgID = ImgID;
        this.name = name;
        this.blurb = blurb;
        this.price = price;
        this.restaurant = restaurant;
    }


    protected Dish(Parcel in) {
        if (in.readByte() == 0) {
            ImgID = null;
        } else {
            ImgID = in.readInt();
        }
        name = in.readString();
        blurb = in.readString();
        price = in.readString();
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

    public void setImgID(int ImgID){
        this.ImgID = ImgID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBlurb(String blurb){
        this.name = name;
    }

    public void setPrice(String price){this.price = price;}

    public String getPrice(){return price;}

    public int getImgID(){
        return ImgID;
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
        if (ImgID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(ImgID);
        }
        dest.writeString(name);
        dest.writeString(blurb);
        dest.writeString(price);
    }
}
