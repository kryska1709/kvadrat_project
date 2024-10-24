package com.example.kvadrat;
public class Zaiavka {
    private String message;
    private String userEmail;
    private String id;

    public Zaiavka(String message, String userEmail, String id) {
        this.message = message;
        this.userEmail = userEmail;
        this.id = id;
    }

    // Геттеры
    public String getMessage() {
        return message;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getId() {
        return id;
    }
}

