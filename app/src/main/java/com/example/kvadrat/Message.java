package com.example.kvadrat;

public class Message {
    public String email;
    public String message;

    // Пустой конструктор для Firebase
    public Message() {
    }

    public Message(String email, String message) {
        this.email = email;
        this.message = message;
    }
}

