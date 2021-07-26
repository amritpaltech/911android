package com.soft.credit911.CaseMangement.Model;

public class CashDemoModel {
    private String bankName, mark, status, date, userStatus, agentName, userMessage;
    private int ImageView;

    public CashDemoModel(String bankName, String mark, String status, String date, String userStatus, String agentName, String userMessage, int imageView) {
        this.bankName = bankName;
        this.mark = mark;
        this.status = status;
        this.date = date;
        this.userStatus = userStatus;
        this.agentName = agentName;
        this.userMessage = userMessage;
        this.ImageView = imageView;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public int getImageView() {
        return ImageView;
    }

    public void setImageView(int imageView) {
        ImageView = imageView;
    }
}
