package com.example.easterncourier.easterncourier;

import android.media.Image;
import android.net.Uri;

import java.util.Date;

public class BookRequest {
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



    public BookRequest(){

    }

    public BookRequest(String requestId, String senderLocationLatitude, String senderLocationLongitude, String receiverLocationLatitude, String receiverLocationLongitude, String receiverName, String requestDescription, String clientUserName, String clientFullName, String clientDateRequested, String clientImage, String requestAssignedCourierId, String requestAssignedCourierUserName, String requestAssignedCourierFullName) {
        this.requestId = requestId;
        this.senderLocationLatitude = senderLocationLatitude;
        this.senderLocationLongitude = senderLocationLongitude;
        this.receiverLocationLatitude = receiverLocationLatitude;
        this.receiverLocationLongitude = receiverLocationLongitude;
        this.receiverName = receiverName;
        this.requestDescription = requestDescription;
        this.clientUserName = clientUserName;
        this.clientFullName= clientFullName;
        this.clientDateRequested =clientDateRequested;
        this.clientPackageImage=clientImage;
        this.requestAssignedCourierId = requestAssignedCourierId;
        this.requestAssignedCourierUserName = requestAssignedCourierUserName;
        this.requestAssignedCourierFullName = requestAssignedCourierFullName;
        this. requestFinish = requestFinish;
    }



    public String getRequestId() {
        return requestId;
    }

    public String getSenderLocationLatitude() {
        return senderLocationLatitude;
    }

    public String getSenderLocationLongitude() {
        return senderLocationLongitude;
    }

    public String getReceiverLocationLatitude() {
        return receiverLocationLatitude;
    }

    public String getReceiverLocationLongitude() {
        return receiverLocationLongitude;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public String getClientDateRequested() {
        return clientDateRequested;
    }

    public String getClientPackageImage() {
        return clientPackageImage;
    }

    public String getRequestAssignedCourierId() {
        return requestAssignedCourierId;
    }

    public String getRequestAssignedCourierUserName() {
        return requestAssignedCourierUserName;
    }

    public String getRequestAssignedCourierFullName() {
        return requestAssignedCourierFullName;
    }

    public String getRequestFinish() {
        return requestFinish;
    }
}
