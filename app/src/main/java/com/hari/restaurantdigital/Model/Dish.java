package com.hari.restaurantdigital.Model;

/**
 * Created by CHAITANYA on 9/17/2017.
 */

public class Dish {

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected;

    private int dishId;
    private String dishName="";

    public Dish(int dishId, int dishImage, String dishEstimatedTime, String dishPrice, String dishName) {
        this.dishId = dishId;
        this.dishImage = dishImage;
        this.dishEstimatedTime = dishEstimatedTime;
        this.dishPrice = dishPrice;
        this.dishName = dishName;
    }

    private String dishPrice="";
    private String dishEstimatedTime="";
    private int dishImage;

    public int getDishImage() {
        return dishImage;
    }

    public void setDishImage(int dishImage) {
        this.dishImage = dishImage;
    }



    public String getDishEstimatedTime() {
        return dishEstimatedTime;
    }

    public void setDishEstimatedTime(String dishEstimatedTime) {
        this.dishEstimatedTime = dishEstimatedTime;
    }



}

