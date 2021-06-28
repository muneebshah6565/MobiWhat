package com.example.mobiwhat.ui.modelsAdapters;

import android.net.Uri;

import java.net.URI;

public class MobileModel {
    String image;
    String name;
    String desc;
    int price;
    int id;

    public MobileModel(String image, String name, String desc, int price) {
        this.image = image;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }
    public int getId(){return id;}

    public void setId(int id){
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
