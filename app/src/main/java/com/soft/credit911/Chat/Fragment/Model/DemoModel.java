package com.soft.credit911.Chat.Fragment.Model;



public class DemoModel {
    private int imageView;
    private String userName, messageDescription, time, messageCount;


    public DemoModel(int imageView, String userName, String messageDescription, String time, String messageCount) {
        this.imageView = imageView;
        this.userName = userName;
        this.messageDescription = messageDescription;
        this.time = time;
        this.messageCount = messageCount;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }
}
