package com.example.easterncourier.easterncourier;

public class addCourierAccountItem {

    String courierId,courierFirstName,courierLastName,courierAddress,courierBirthDate,courierPhoneNumber,
            courierLocationLatitude,courierLocationLongitude,courierClientList,courierImage
            ,courierUserName,courierPassword;



    public addCourierAccountItem(){

    }

    public addCourierAccountItem(String courierId, String courierFirstName, String courierLastName, String courierAddress, String courierBirthDate, String courierPhoneNumber, String courierLocationLatitude, String courierLocationLongitude, String courierClientList, String courierImage
    ,String courierUserName,String courierPassword) {
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

    public String getCourierFirstName() {
        return courierFirstName;
    }

    public String getCourierLastName() {
        return courierLastName;
    }

    public String getCourierAddress() {
        return courierAddress;
    }

    public String getCourierBirthDate() {
        return courierBirthDate;
    }

    public String getCourierPhoneNumber() {
        return courierPhoneNumber;
    }

    public String getCourierLocationLatitude() {
        return courierLocationLatitude;
    }

    public String getCourierLocationLongitude() {
        return courierLocationLongitude;
    }

    public String getCourierClientList() {
        return courierClientList;
    }

    public String getCourierImage() {
        return courierImage;
    }

    public String getCourierUserName() {
        return courierUserName;
    }

    public String getCourierPassword() {
        return courierPassword;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public void setCourierFirstName(String courierFirstName) {
        this.courierFirstName = courierFirstName;
    }

    public void setCourierLastName(String courierLastName) {
        this.courierLastName = courierLastName;
    }

    public void setCourierAddress(String courierAddress) {
        this.courierAddress = courierAddress;
    }

    public void setCourierBirthDate(String courierBirthDate) {
        this.courierBirthDate = courierBirthDate;
    }

    public void setCourierPhoneNumber(String courierPhoneNumber) {
        this.courierPhoneNumber = courierPhoneNumber;
    }

    public void setCourierLocationLatitude(String courierLocationLatitude) {
        this.courierLocationLatitude = courierLocationLatitude;
    }

    public void setCourierLocationLongitude(String courierLocationLongitude) {
        this.courierLocationLongitude = courierLocationLongitude;
    }

    public void setCourierClientList(String courierClientList) {
        this.courierClientList = courierClientList;
    }

    public void setCourierImage(String courierImage) {
        this.courierImage = courierImage;
    }

    public void setCourierUserName(String courierUserName) {
        this.courierUserName = courierUserName;
    }

    public void setCourierPassword(String courierPassword) {
        this.courierPassword = courierPassword;
    }
}
