package com.example.easterncourier.easterncourier;

public class courier_clients_item {
    int courierClientPicture;
    String courierClientName,courierClientTimeAssigned;

    public courier_clients_item(int courierClientPicture, String courierClientName, String courierClientTimeAssigned) {
        this.courierClientPicture = courierClientPicture;
        this.courierClientName = courierClientName;
        this.courierClientTimeAssigned = courierClientTimeAssigned;
    }

    public int getCourierClientPicture() {
        return courierClientPicture;
    }

    public String getCourierClientName() {
        return courierClientName;
    }

    public String getCourierClientTimeAssigned() {
        return courierClientTimeAssigned;
    }

    public void setCourierClientPicture(int courierClientPicture) {
        this.courierClientPicture = courierClientPicture;
    }

    public void setCourierClientName(String courierClientName) {
        this.courierClientName = courierClientName;
    }

    public void setCourierClientTimeAssigned(String courierClientTimeAssigned) {
        this.courierClientTimeAssigned = courierClientTimeAssigned;
    }
}
