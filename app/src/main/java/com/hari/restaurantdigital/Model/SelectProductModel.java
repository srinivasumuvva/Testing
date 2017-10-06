package com.hari.restaurantdigital.Model;

/**
 * Created by MUVVASR on 10/5/2017.
 */

public class SelectProductModel {
    private String productId="";
    private String productTitle="";
    private float productUnitPrice=0.0f;

    public SelectProductModel(String productTitle, float productUnitPrice, int productQuantity, String productPriceSymbol) {
        this.productTitle = productTitle;
        this.productUnitPrice = productUnitPrice;
        this.productQuantity = productQuantity;
        this.productPriceSymbol = productPriceSymbol;
    }

    private String productImgUrl="";
    private float productTotalPrice=0.0f;
    private int productQuantity=0;
    private String productPriceSymbol=" rs ";

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public float getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(float productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public float getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(float productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductPriceSymbol() {
        return productPriceSymbol;
    }

    public void setProductPriceSymbol(String productPriceSymbol) {
        this.productPriceSymbol = productPriceSymbol;
    }



}
