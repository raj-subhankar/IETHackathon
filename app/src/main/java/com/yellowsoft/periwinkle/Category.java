package com.yellowsoft.periwinkle;

import com.google.gson.annotations.SerializedName;

/**
 * Created by subhankar on 11/6/2016.
 */

public class Category {
    @SerializedName("user")
    private User user;
    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("numofItems")
    private int numOfItems;
    @SerializedName("thumbnail")
    private int thumbnail;

    public Category(String name, int numOfItems, int thumbnail) {
        this.name = name;
        this.numOfItems = numOfItems;
        this.thumbnail = thumbnail;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
