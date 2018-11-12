package com.example.easterncourier.easterncourier;

public class notification_item {
    String notificationType,notificationTime;
    int notificationImage;




    public notification_item() {

    }

    public notification_item(String notificationType, String notificationTime, int notificationImage) {
        this.notificationType = notificationType;
        this.notificationTime=notificationTime;
        this.notificationImage = notificationImage;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public int getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public void setNotificationImage(int notificationImage) {
        this.notificationImage = notificationImage;
    }
}
