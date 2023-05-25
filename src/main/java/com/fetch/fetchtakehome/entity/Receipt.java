package com.fetch.fetchtakehome.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

/**
 * Receipt POJO
 */
public class Receipt {
    @JsonProperty("retailer")
    private String retailer;

    @JsonProperty("purchaseDate")
    private String purchaseDate;

    @JsonProperty("purchaseTime")
    private String purchaseTime;

    @JsonProperty("items")
    private Item[] item;

    @JsonProperty("total")
    private String total;

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "retailer='" + retailer + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", purchaseTime='" + purchaseTime + '\'' +
                ", items=" + item +
                ", total='" + total + '\'' +
                '}';
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Item[] getItem() {
        return item;
    }

    public void setItem(Item[] item) {
        this.item = item;
    }

    // Java reflection mechanism to get info from compiled byteCode
    // Java reflection, which allows us to inspect and/or modify runtime
    // attributes of classes, interfaces, fields and methods.
    public boolean checkNull() {
        try {
            for (Field f: this.getClass().getDeclaredFields()) {
                if (f.get(this) == null) {
                    // todo log null based on specific field
                    return true;
                }
            }
        } catch (Exception e) {
            // todo change to securityException
            e.printStackTrace();
        }
        return false;
    }
}
