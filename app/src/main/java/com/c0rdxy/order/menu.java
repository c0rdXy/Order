package com.c0rdxy.order;

public class menu {
    private  String name;
    private  int price;
    private  int flavour;
    private  int imgPath;

    public menu(String name, int price, int flavour, int imgPath) {
        this.name = name;
        this.price = price;
        this.flavour = flavour;
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFlavour() {
        return flavour;
    }

    public void setFlavour(int flavour) {
        this.flavour = flavour;
    }

    public int getImgPath() {
        return imgPath;
    }

    public void setImgPath(int imgPath) {
        this.imgPath = imgPath;
    }
}
