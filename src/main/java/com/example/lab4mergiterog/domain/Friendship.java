package com.example.lab4mergiterog.domain;

public class Friendship extends Entity {
    private Integer firstUserId;
    private Integer secondUserId;

    private String status;

    public Friendship(Integer id, Integer firstUserID, Integer secondUserID, String status) {
        super(id);
        this.firstUserId = firstUserID;
        this.secondUserId = secondUserID;
        this.status = status;
    }

    public Friendship(Integer firstUserID, Integer secondUserID, String status) {
        this.firstUserId = firstUserID;
        this.secondUserId = secondUserID;
        this.status = status;
    }

    public Friendship(Friendship friendship) {
        this.id = friendship.getId();
        this.firstUserId = friendship.getFirstUserId();
        this.secondUserId = friendship.getSecondUserId();
        this.status = friendship.getStatus();
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

    public String getStatus() { return status;}

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", firstUserID=" + firstUserId +
                ", secondUserID=" + secondUserId +
                ", status=" + status +
                '}';
    }
}
