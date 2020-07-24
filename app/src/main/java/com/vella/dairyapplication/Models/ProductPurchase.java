package com.vella.dairyapplication.Models;

import java.io.Serializable;

public class ProductPurchase implements Serializable {
    private String productName;
    private String price;
    private String confirmAddress;
    private Purchase purchase;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public String getConfirmAddress() {
        return confirmAddress;
    }

    public void setConfirmAddress(String confirmAddress) {
        this.confirmAddress = confirmAddress;
    }
}
