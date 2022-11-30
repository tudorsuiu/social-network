package com.example.lab4mergiterog.domain;

public class Friendship extends Entity {
    private Integer firstUserID;
    private Integer secondUserID;

    public Friendship(Integer id, Integer firstUserID, Integer secondUserID) {
        super(id);
        this.firstUserID = firstUserID;
        this.secondUserID = secondUserID;
    }

    public Integer getFirstUserID() {
        return firstUserID;
    }

    public void setFirstUserID(Integer firstUserID) {
        this.firstUserID = firstUserID;
    }

    public Integer getSecondUserID() {
        return secondUserID;
    }

    public void setSecondUserID(Integer secondUserID) {
        this.secondUserID = secondUserID;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", firstUserID=" + firstUserID +
                ", secondUserID=" + secondUserID +
                '}';
    }
}
