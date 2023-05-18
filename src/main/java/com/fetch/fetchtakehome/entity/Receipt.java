package com.fetch.fetchtakehome.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

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

    public boolean checkNull() {
        try {
            for (Field f: this.getClass().getDeclaredFields()) {
                if (f.get(this) == null) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
