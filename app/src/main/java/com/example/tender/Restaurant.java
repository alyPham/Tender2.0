package com.example.tender;

public class Restaurant {
    String name;
    String daysAndHours;
    String dineIn;
    String takeOut;
    String delivery;
    String website;
    String phoneNum;

    public Restaurant(){
        name = "default name";
        daysAndHours = "default days and hours";
        dineIn = "default dine in";
        takeOut = "default takeout";
        delivery = "default delivery";
        website = "default website";
        phoneNum = "default phone number";
    }

    public Restaurant(String name,
                      String daysAndHours,
                      String dineIn,
                      String takeOut,
                      String delivery,
                      String website,
                      String phoneNum){
        this.name = name;
        this.daysAndHours = daysAndHours;
        this.dineIn = dineIn;
        this.takeOut = takeOut;
        this.delivery = delivery;
        this.website = website;
        this.phoneNum = phoneNum;
    }

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
