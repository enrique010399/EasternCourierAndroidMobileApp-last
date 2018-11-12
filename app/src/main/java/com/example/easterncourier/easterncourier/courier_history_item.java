package com.example.easterncourier.easterncourier;

public class courier_history_item {
    int courierHistoryClientPicture;
    String courierHistoryClientName,courierHistoryDeliveredDate;

    public courier_history_item(int courierHistoryClientPicture, String courierHistoryClientName, String courierHistoryDeliveredDate) {
        this.courierHistoryClientPicture = courierHistoryClientPicture;
        this.courierHistoryClientName = courierHistoryClientName;
        this.courierHistoryDeliveredDate = courierHistoryDeliveredDate;
    }

    public int getCourierHistoryClientPicture() {
        return courierHistoryClientPicture;
    }

    public String getCourierHistoryClientName() {
        return courierHistoryClientName;
    }

    public String getCourierHistoryDeliveredDate() {
        return courierHistoryDeliveredDate;
    }

    public void setCourierHistoryClientPicture(int courierHistoryClientPicture) {
        this.courierHistoryClientPicture = courierHistoryClientPicture;
    }

    public void setCourierHistoryClientName(String courierHistoryClientName) {
        this.courierHistoryClientName = courierHistoryClientName;
    }

    public void setCourierHistoryDeliveredDate(String courierHistoryDeliveredDate) {
        this.courierHistoryDeliveredDate = courierHistoryDeliveredDate;
    }
}
