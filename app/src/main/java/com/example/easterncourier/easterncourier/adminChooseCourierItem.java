package com.example.easterncourier.easterncourier;

public class adminChooseCourierItem {
    String courierId;
    String courierFirstName;
    String courierLastName;
    String courierAddress;
    String courierBirthDate;
    String courierPhoneNumber;
    String courierLocationLatitude;
    String courierLocationLongitude;
    String courierClientList;
    String courierImage;
    String courierUserName;
    String courierPassword;


    public adminChooseCourierItem(){

    }

    public adminChooseCourierItem(String courierId, String courierFirstName, String courierLastName, String courierAddress, String courierBirthDate, String courierPhoneNumber, String courierLocationLatitude, String courierLocationLongitude, String courierClientList, String courierImage, String courierUserName, String courierPassword) {
        this.courierId = courierId;
        this.courierFirstName = courierFirstName;
        this.courierLastName = courierLastName;
        this.courierAddress = courierAddress;
        this.courierBirthDate = courierBirthDate;
        this.courierPhoneNumber = courierPhoneNumber;
        this.courierLocationLatitude = courierLocationLatitude;
        this.courierLocationLongitude = courierLocationLongitude;
        this.courierClientList = courierClientList;
        this.courierImage = courierImage;
        this.courierUserName = courierUserName;
        this.courierPassword = courierPassword;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public String getCourierFirstName() {
        return courierFirstName;
    }

    public void setCourierFirstName(String courierFirstName) {
        this.courierFirstName = courierFirstName;
    }

    public String getCourierLastName() {
        return courierLastName;
    }

    public void setCourierLastName(String courierLastName) {
        this.courierLastName = courierLastName;
    }

    public String getCourierAddress() {
        return courierAddress;
    }

    public void setCourierAddress(String courierAddress) {
        this.courierAddress = courierAddress;
    }

    public String getCourierBirthDate() {
        return courierBirthDate;
    }

    public void setCourierBirthDate(String courierBirthDate) {
        this.courierBirthDate = courierBirthDate;
    }

    public String getCourierPhoneNumber() {
        return courierPhoneNumber;
    }

    public void setCourierPhoneNumber(String courierPhoneNumber) {
        this.courierPhoneNumber = courierPhoneNumber;
    }

    public String getCourierLocationLatitude() {
        return courierLocationLatitude;
    }

    public void setCourierLocationLatitude(String courierLocationLatitude) {
        this.courierLocationLatitude = courierLocationLatitude;
    }

    public String getCourierLocationLongitude() {
        return courierLocationLongitude;
    }

    public void setCourierLocationLongitude(String courierLocationLongitude) {
        this.courierLocationLongitude = courierLocationLongitude;
    }

    public String getCourierClientList() {
        return courierClientList;
    }

    public void setCourierClientList(String courierClientList) {
        this.courierClientList = courierClientList;
    }

    public String getCourierImage() {
        return courierImage;
    }

    public void setCourierImage(String courierImage) {
        this.courierImage = courierImage;
    }

    public String getCourierUserName() {
        return courierUserName;
    }

    public void setCourierUserName(String courierUserName) {
        this.courierUserName = courierUserName;
    }

    public String getCourierPassword() {
        return courierPassword;
    }

    public void setCourierPassword(String courierPassword) {
        this.courierPassword = courierPassword;
    }
}
