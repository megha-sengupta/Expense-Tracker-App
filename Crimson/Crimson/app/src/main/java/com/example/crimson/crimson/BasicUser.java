package com.example.crimson.crimson;

public class BasicUser implements User{

    public BasicUser(){

    }

    @Override
    public String createRegisteredUser() {

        return "Registered User";
    }
}
