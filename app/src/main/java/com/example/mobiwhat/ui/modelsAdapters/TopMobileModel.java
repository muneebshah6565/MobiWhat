package com.example.mobiwhat.ui.modelsAdapters;

public class TopMobileModel {
    int id;
    String image;
    String name;
    String desc;
    int price;
    int ram;
    int storage;
    int Battery;
    int cameraMain;
    int cameraFront;
    String dimensions;

    public TopMobileModel(int id, String image, String name, String desc, int price, int ram, int storage, int battery, int cameraMain, int cameraFront, String dimensions) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.ram = ram;
        this.storage = storage;
        Battery = battery;
        this.cameraMain = cameraMain;
        this.cameraFront = cameraFront;
        this.dimensions = dimensions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getBattery() {
        return Battery;
    }

    public void setBattery(int battery) {
        Battery = battery;
    }

    public int getCameraMain() {
        return cameraMain;
    }

    public void setCameraMain(int cameraMain) {
        this.cameraMain = cameraMain;
    }

    public int getCameraFront() {
        return cameraFront;
    }

    public void setCameraFront(int cameraFront) {
        this.cameraFront = cameraFront;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
}
