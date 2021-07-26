package com.soft.credit911.Document.Model;

public class DocumentModel {
    private String userLicence, userStatus;
    private int checkImage;

    public String getUserLicence() {
        return userLicence;
    }

    public void setUserLicence(String userLicence) {
        this.userLicence = userLicence;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public int getCheckImage() {
        return checkImage;
    }

    public void setCheckImage(int checkImage) {
        this.checkImage = checkImage;
    }
}
