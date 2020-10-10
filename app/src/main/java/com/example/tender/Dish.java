package com.example.tender;

public class Dish {
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
}
