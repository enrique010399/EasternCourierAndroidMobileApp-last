package com.example.easterncourier.easterncourier;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class registerClientRequest  {
    public String accountId,accountFirstName,accountLastName,accountGender
            ,accountBirthDay,accountBirthMonth,accountBirthYear
            ,accountAddressStreet,accountAddressBarangay,accountAddressCity
            ,accountAddressProvince,accountAddressZipCode,accountMobileNumber
            ,accountUserName,accountPassword,accountToken;




    public registerClientRequest(){

    }

    public registerClientRequest(String accountId, String accountFirstName, String accountLastName, String accountGender, String accountBirthDay, String accountBirthMonth, String accountBirthYear, String accountAddressStreet, String accountAddressBarangay, String accountAddressCity, String accountAddressProvince, String accountAddressZipCode, String accountMobileNumber, String accountUserName, String accountPassword, String accountToken) {
        this.accountId = accountId;
        this.accountFirstName = accountFirstName;
        this.accountLastName = accountLastName;
        this.accountGender = accountGender;
        this.accountBirthDay = accountBirthDay;
        this.accountBirthMonth = accountBirthMonth;
        this.accountBirthYear = accountBirthYear;
        this.accountAddressStreet = accountAddressStreet;
        this.accountAddressBarangay = accountAddressBarangay;
        this.accountAddressCity = accountAddressCity;
        this.accountAddressProvince = accountAddressProvince;
        this.accountAddressZipCode = accountAddressZipCode;
        this.accountMobileNumber = accountMobileNumber;
        this.accountUserName = accountUserName;
        this.accountPassword = accountPassword;
        this.accountToken = accountToken;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountFirstName() {
        return accountFirstName;
    }

    public void setAccountFirstName(String accountFirstName) {
        this.accountFirstName = accountFirstName;
    }

    public String getAccountLastName() {
        return accountLastName;
    }

    public void setAccountLastName(String accountLastName) {
        this.accountLastName = accountLastName;
    }

    public String getAccountGender() {
        return accountGender;
    }

    public void setAccountGender(String accountGender) {
        this.accountGender = accountGender;
    }

    public String getAccountBirthDay() {
        return accountBirthDay;
    }

    public void setAccountBirthDay(String accountBirthDay) {
        this.accountBirthDay = accountBirthDay;
    }

    public String getAccountBirthMonth() {
        return accountBirthMonth;
    }

    public void setAccountBirthMonth(String accountBirthMonth) {
        this.accountBirthMonth = accountBirthMonth;
    }

    public String getAccountBirthYear() {
        return accountBirthYear;
    }

    public void setAccountBirthYear(String accountBirthYear) {
        this.accountBirthYear = accountBirthYear;
    }

    public String getAccountAddressStreet() {
        return accountAddressStreet;
    }

    public void setAccountAddressStreet(String accountAddressStreet) {
        this.accountAddressStreet = accountAddressStreet;
    }

    public String getAccountAddressBarangay() {
        return accountAddressBarangay;
    }

    public void setAccountAddressBarangay(String accountAddressBarangay) {
        this.accountAddressBarangay = accountAddressBarangay;
    }

    public String getAccountAddressCity() {
        return accountAddressCity;
    }

    public void setAccountAddressCity(String accountAddressCity) {
        this.accountAddressCity = accountAddressCity;
    }

    public String getAccountAddressProvince() {
        return accountAddressProvince;
    }

    public void setAccountAddressProvince(String accountAddressProvince) {
        this.accountAddressProvince = accountAddressProvince;
    }

    public String getAccountAddressZipCode() {
        return accountAddressZipCode;
    }

    public void setAccountAddressZipCode(String accountAddressZipCode) {
        this.accountAddressZipCode = accountAddressZipCode;
    }

    public String getAccountMobileNumber() {
        return accountMobileNumber;
    }

    public void setAccountMobileNumber(String accountMobileNumber) {
        this.accountMobileNumber = accountMobileNumber;
    }

    public String getAccountUserName() {
        return accountUserName;
    }

    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }
}
