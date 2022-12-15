package com.example.lab4mergiterog.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.sql.Date;

public class Friendship extends Entity {
    private Integer firstUserId;
    private Integer secondUserId;
    private String status;
    private Date date;


    public Friendship(Integer id, Integer firstUserID, Integer secondUserID, String status, Date date) {
        super(id);
        this.firstUserId = firstUserID;
        this.secondUserId = secondUserID;
        this.status = status;
        this.date = date;
    }

    public Friendship(Integer firstUserID, Integer secondUserID, String status) {
        this.firstUserId = firstUserID;
        this.secondUserId = secondUserID;
        this.status = status;
        this.date = new Date(System.currentTimeMillis());
    }

    public Friendship(Friendship friendship) {
        this.id = friendship.getId();
        this.firstUserId = friendship.getFirstUserId();
        this.secondUserId = friendship.getSecondUserId();
        this.status = friendship.getStatus();
        this.date = friendship.getDate();
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

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", firstUserID=" + firstUserId +
                ", secondUserID=" + secondUserId +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
