package com.example.tamdang.restaurantguide;

import android.support.annotation.NonNull;

public class Restaurant {

    private long id;
    private String name;
    private String address;
    private String phone;
    private String description;
    private String tag;

    public Restaurant(String name, String address, String phone, String description, String tag) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.tag = tag;
    }
    public Restaurant(long id, String name, String address, String phone, String description, String tag) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.tag = tag;
    }

    public String getAddress() {
        return address;

    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {

        return name;

    }

    public void setName(String name) {
        this.name = name;
    }
}
