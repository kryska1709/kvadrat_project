package com.example.kvadrat;

public class MyItem {
    private String imageResId;
    private String vidSdelki;
    private String VidNedvizh;
    private String ploshad;
    private String price;
    private String adress;
    private String phone;
    private int id;

    public MyItem() {
    }

    public MyItem(String imageResId, String vidSdelki, String VidNedvizh, String ploshad, String price, String adress, String  phone, int id) {
        this.imageResId = imageResId;
        this.vidSdelki = vidSdelki;
        this.VidNedvizh = VidNedvizh;
        this.ploshad  = ploshad;
        this.price = price;
        this.adress = adress;
        this.phone=phone;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageResId() {
        return imageResId;
    }

    public void setImageResId(String imageResId) {
        this.imageResId = imageResId;
    }

    public String getVidSdelki() {
        return vidSdelki;
    }

    public String getVidNedvizh() {
        return VidNedvizh;
    }

    public String getPloshad() {
        return ploshad;
    }

    public String getPrice() {
        return price;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone(){return  phone;}

}

