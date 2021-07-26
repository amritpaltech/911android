package com.soft.credit911.Chat.Fragment.Model;


public abstract class ChatObject {
    public static final int SEND_MESSAGE = 0;
    public static final int RECEIVED_MESSAGE = 1;


    private String text ,userTime;
    private int Image;

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public String getText() {
        return text;
    }

    public void setText( String text) {
        this.text = text;
    }

    public abstract int getType();
}
