package com.vella.dairyapplication.Models;

import java.io.Serializable;

public class Product implements Serializable {
    private int prodImage;
    private String prodName;
    private String prodPrice;

    public int getProdImage() {
        return prodImage;
    }

    public void setProdImage(int prodImage) {
        this.prodImage = prodImage;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }
}
