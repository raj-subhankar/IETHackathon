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
    private String thumbnail;

    public Category(String name, int numOfItems, String thumbnail) {
        this.name = name;
        this.numOfItems = numOfItems;
        this.thumbnail = thumbnail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
