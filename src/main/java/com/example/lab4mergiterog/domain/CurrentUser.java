package com.example.lab4mergiterog.domain;

public class CurrentUser {
    private User user;
    private static CurrentUser instance = null;
    private CurrentUser() {
    }
    public static CurrentUser getInstance() {
        if(instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
