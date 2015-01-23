package com.afonseca.myapplication;

/**
 * Created by afonseca on 1/23/2015.
 */
public class Msg {
    private long id;
    private String user_from;
    private String user_to;
    private Integer timestamp;
    private String status;
    private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_from() {
        return user_from;
    }

    public void setUser_from(String user_from) {
        this.user_from = user_from;
    }

    public String getUser_to() {
        return user_to;
    }

    public void setUser_to(String user_to) {
        this.user_to = user_to;
    }


    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getAll() {
        String result = message + "|" + user_from + ">" + user_to + "@" + Integer.toString(timestamp) + " is " + status;
        return result;
    }


    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        String result = message + "|" + user_from + ">" + user_to + "@" + Integer.toString(timestamp) + " is " + status;
        return result;
        //return message;
    }

}