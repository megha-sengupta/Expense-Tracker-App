package com.example.crimson.crimson;

public class WithFreeCredit extends UserDecorator {

    public WithFreeCredit(User newUser) {
        super(newUser);
    }

    public String createRegisteredUser(){

        return tempUser.createRegisteredUser()+" , With Free Credits";
    }
}
