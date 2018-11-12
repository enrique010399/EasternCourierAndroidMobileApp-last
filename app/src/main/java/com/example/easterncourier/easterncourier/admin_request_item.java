package com.example.easterncourier.easterncourier;

public class admin_request_item {
    String requestId;
    String senderLocationLatitude;
    String senderLocationLongitude;
    String receiverLocationLatitude;
    String receiverLocationLongitude;
    String receiverName;
    String requestDescription;
    String clientUserName;
    String clientFullName;
    String clientDateRequested;
    String clientPackageImage;
    String requestAssignedCourierId;
    String requestAssignedCourierUserName;
    String requestAssignedCourierFullName;
    String requestFinish;
    String receiverContactNumber;
    String requestBill;
    String requestChange;
    String requestCash;

    public admin_request_item(){

    }

    public admin_request_item(String requestId, String senderLocationLatitude, String senderLocationLongitude, String receiverLocationLatitude, String receiverLocationLongitude, String receiverName, String requestDescription, String clientUserName, String clientFullName, String clientDateRequested, String clientPackageImage, String requestAssignedCourierId, String requestAssignedCourierUserName, String requestAssignedCourierFullName, String requestFinish, String receiverContactNumber, String requestBill, String requestChange, String requestCash) {
        this.requestId = requestId;
        this.senderLocationLatitude = senderLocationLatitude;
        this.senderLocationLongitude = senderLocationLongitude;
        this.receiverLocationLatitude = receiverLocationLatitude;
        this.receiverLocationLongitude = receiverLocationLongitude;
        this.receiverName = receiverName;
        this.requestDescription = requestDescription;
        this.clientUserName = clientUserName;
        this.clientFullName = clientFullName;
        this.clientDateRequested = clientDateRequested;
        this.clientPackageImage = clientPackageImage;
        this.requestAssignedCourierId = requestAssignedCourierId;
        this.requestAssignedCourierUserName = requestAssignedCourierUserName;
        this.requestAssignedCourierFullName = requestAssignedCourierFullName;
        this.requestFinish = requestFinish;
        this.receiverContactNumber = receiverContactNumber;
        this.requestBill = requestBill;
        this.requestChange = requestChange;
        this.requestCash = requestCash;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSenderLocationLatitude() {
        return senderLocationLatitude;
    }

    public void setSenderLocationLatitude(String senderLocationLatitude) {
        this.senderLocationLatitude = senderLocationLatitude;
    }

    public String getSenderLocationLongitude() {
        return senderLocationLongitude;
    }

    public void setSenderLocationLongitude(String senderLocationLongitude) {
        this.senderLocationLongitude = senderLocationLongitude;
    }

    public String getReceiverLocationLatitude() {
        return receiverLocationLatitude;
    }

    public void setReceiverLocationLatitude(String receiverLocationLatitude) {
        this.receiverLocationLatitude = receiverLocationLatitude;
    }

    public String getReceiverLocationLongitude() {
        return receiverLocationLongitude;
    }

    public void setReceiverLocationLongitude(String receiverLocationLongitude) {
        this.receiverLocationLongitude = receiverLocationLongitude;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public String getClientDateRequested() {
        return clientDateRequested;
    }

    public void setClientDateRequested(String clientDateRequested) {
        this.clientDateRequested = clientDateRequested;
    }

    public String getClientPackageImage() {
        return clientPackageImage;
    }

    public void setClientPackageImage(String clientPackageImage) {
        this.clientPackageImage = clientPackageImage;
    }

    public String getRequestAssignedCourierId() {
        return requestAssignedCourierId;
    }

    public void setRequestAssignedCourierId(String requestAssignedCourierId) {
        this.requestAssignedCourierId = requestAssignedCourierId;
    }

    public String getRequestAssignedCourierUserName() {
        return requestAssignedCourierUserName;
    }

    public void setRequestAssignedCourierUserName(String requestAssignedCourierUserName) {
        this.requestAssignedCourierUserName = requestAssignedCourierUserName;
    }

    public String getRequestAssignedCourierFullName() {
        return requestAssignedCourierFullName;
    }

    public void setRequestAssignedCourierFullName(String requestAssignedCourierFullName) {
        this.requestAssignedCourierFullName = requestAssignedCourierFullName;
    }

    public String getRequestFinish() {
        return requestFinish;
    }

    public void setRequestFinish(String requestFinish) {
        this.requestFinish = requestFinish;
    }

    public String getReceiverContactNumber() {
        return receiverContactNumber;
    }

    public void setReceiverContactNumber(String receiverContactNumber) {
        this.receiverContactNumber = receiverContactNumber;
    }

    public String getRequestBill() {
        return requestBill;
    }

    public void setRequestBill(String requestBill) {
        this.requestBill = requestBill;
    }

    public String getRequestChange() {
        return requestChange;
    }

    public void setRequestChange(String requestChange) {
        this.requestChange = requestChange;
    }

    public String getRequestCash() {
        return requestCash;
    }

    public void setRequestCash(String requestCash) {
        this.requestCash = requestCash;
    }
}
