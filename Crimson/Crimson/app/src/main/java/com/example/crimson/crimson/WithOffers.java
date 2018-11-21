package com.example.crimson.crimson;

public class WithOffers extends UserDecorator {

    public WithOffers(User newUser) {
        super(newUser);
    }

    public String createRegisteredUser(){
        return tempUser.createRegisteredUser()+" , With Offers";
    }

}
