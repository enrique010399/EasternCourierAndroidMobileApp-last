package com.example.easterncourier.easterncourier;

public class admin_couriers_item {
    String courierAddress;
    String courierBirthDate;
    String courierClientList;
    String courierFirstName;
    String courierId;
    String courierImage;
    String courierLastName;
    String courierLocationLatitude;
    String courierLocationLongitude;
    String courierPhoneNumber;
    String courierUserName;
    String courierPassword;

    public admin_couriers_item(){

    }

    public admin_couriers_item(String courierAddress, String courierBirthDate, String courierClientList, String courierFirstName, String courierId, String courierImage, String courierLastName, String courierLocationLatitude, String courierLocationLongitude, String courierPhoneNumber, String courierUserName, String courierPassword) {
        this.courierAddress = courierAddress;
        this.courierBirthDate = courierBirthDate;
        this.courierClientList = courierClientList;
        this.courierFirstName = courierFirstName;
        this.courierId = courierId;
        this.courierImage = courierImage;
        this.courierLastName = courierLastName;
        this.courierLocationLatitude = courierLocationLatitude;
        this.courierLocationLongitude = courierLocationLongitude;
        this.courierPhoneNumber = courierPhoneNumber;
        this.courierUserName = courierUserName;
        this.courierPassword = courierPassword;
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

    public String getCourierClientList() {
        return courierClientList;
    }

    public void setCourierClientList(String courierClientList) {
        this.courierClientList = courierClientList;
    }

    public String getCourierFirstName() {
        return courierFirstName;
    }

    public void setCourierFirstName(String courierFirstName) {
        this.courierFirstName = courierFirstName;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public String getCourierImage() {
        return courierImage;
    }

    public void setCourierImage(String courierImage) {
        this.courierImage = courierImage;
    }

    public String getCourierLastName() {
        return courierLastName;
    }

    public void setCourierLastName(String courierLastName) {
        this.courierLastName = courierLastName;
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

    public String getCourierPhoneNumber() {
        return courierPhoneNumber;
    }

    public void setCourierPhoneNumber(String courierPhoneNumber) {
        this.courierPhoneNumber = courierPhoneNumber;
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
