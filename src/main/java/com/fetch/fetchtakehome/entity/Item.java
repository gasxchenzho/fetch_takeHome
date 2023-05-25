package com.fetch.fetchtakehome.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Item POJO Plain Old Java Object, a straightforward type with no references to any particular frameworks.
 */
public class Item {

    // annotation for serialization or deserialization
    @JsonProperty("shortDescription")
    private String shortDescription;

    @JsonProperty("price")
    private String price;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String toString() {
        return "Item{" +
                "shortDescription='" + shortDescription + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
