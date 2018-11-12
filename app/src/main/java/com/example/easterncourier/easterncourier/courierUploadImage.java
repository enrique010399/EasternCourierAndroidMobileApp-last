package com.example.easterncourier.easterncourier;

public class courierUploadImage {

    private String mName;
    private String mImageUri;

    public courierUploadImage(String mName, String mImageUri) {
        this.mName = mName;
        this.mImageUri = mImageUri;
    }

    public courierUploadImage(){

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }
}
