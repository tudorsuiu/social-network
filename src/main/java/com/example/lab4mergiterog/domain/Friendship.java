package com.example.lab4mergiterog.domain;

public class Friendship extends Entity {
    private Integer firstUserId;
    private Integer secondUserId;

//    private String status;

    public Friendship(Integer id, Integer firstUserID, Integer secondUserID) {
        super(id);
        this.firstUserId = firstUserID;
        this.secondUserId = secondUserID;
    }

    public Friendship(Integer firstUserID, Integer secondUserID) {
        this.firstUserId = firstUserID;
        this.secondUserId = secondUserID;
    }

    public Integer getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserID(Integer firstUserID) {
        this.firstUserId = firstUserID;
    }

    public Integer getSecondUserId() {
        return secondUserId;
    }

    public void setSecondUserID(Integer secondUserID) {
        this.secondUserId = secondUserID;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", firstUserID=" + firstUserId +
                ", secondUserID=" + secondUserId +
                '}';
    }
}
