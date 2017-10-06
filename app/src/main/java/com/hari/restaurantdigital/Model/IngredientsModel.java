package com.hari.restaurantdigital.Model;

/**
 * Created by MUVVASR on 10/6/2017.
 */

public class IngredientsModel {
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String quantity;
    private String title;

    public IngredientsModel(String quantity, String title) {
        this.quantity = quantity;
        this.title = title;
    }
}
