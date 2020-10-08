package com.example.tender;

public class Dish {
    private Integer ImgID;
    private int name;
    private int description;
    int distance;
    private Restaurant restaurant;

    public Dish(int ImgID, int name, int description, int distance, Restaurant restaurant){
        this.ImgID = ImgID;
        this.name = name;
        this.description = description;
        this.distance = distance;
        this.restaurant = restaurant;
    }

    public int getImgID(){
        return ImgID;
    }

    public int getName(){
        return name;
    }

    public int getDescription(){
        return description;
    }

    public int getDistance(){
        return distance;
    }

    public Restaurant getRestaurant(){
        return restaurant;
    }
}
