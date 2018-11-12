package com.example.easterncourier.easterncourier;

public class admin_messages_item {
    int accountImage;
    String accountName,accountLMessages;

    public admin_messages_item(int accountImage, String accountName, String accountLMessages) {
        this.accountImage = accountImage;
        this.accountName = accountName;
        this.accountLMessages = accountLMessages;
    }

    public int getAccountImage() {
        return accountImage;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountLMessages() {
        return accountLMessages;
    }

    public void setAccountImage(int accountImage) {
        this.accountImage = accountImage;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountLMessages(String accountLMessages) {
        this.accountLMessages = accountLMessages;
    }
}
