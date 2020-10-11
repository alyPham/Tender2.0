package com.example.tender;

import android.os.Parcel;
import android.os.Parcelable;

public class Dish implements Parcelable {
    private Integer ImgID;
    private String name;
    private String description;
    String distance;
    private Restaurant restaurant;

    public Dish(){
        ImgID = R.drawable.countryfriedsteak;
        name = "default name";
        description = "default description";
        distance = "default distance";
        restaurant = new Restaurant();
    };

    public Dish(int ImgID, String name, String description, String distance, Restaurant restaurant){
        this.ImgID = ImgID;
        this.name = name;
        this.description = description;
        this.distance = distance;
        this.restaurant = restaurant;
    }


    protected Dish(Parcel in) {
        if (in.readByte() == 0) {
            ImgID = null;
        } else {
            ImgID = in.readInt();
        }
        name = in.readString();
        description = in.readString();
        distance = in.readString();
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

    public void setDescription(String description){
        this.name = name;
    }

    public void setDistance(String distance){
        this.distance = distance;
    }

    public int getImgID(){
        return ImgID;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getDistance(){
        return distance;
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
        dest.writeString(description);
        dest.writeString(distance);
    }
}
