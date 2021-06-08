package com.example.fleemarket;

public class Dictionary {

    private String userName;
    private String Message;

    public String getuserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }


    public Dictionary(String userName, String message) {
        this.userName = userName;
        Message = message;
    }
}