package com.example.easterncourier.easterncourier;

public class admin_accounts_item {
    int accountPicture;
    String accountName,accountType;

    public admin_accounts_item(int accountPicture, String accountName, String accountType) {
        this.accountPicture = accountPicture;
        this.accountName = accountName;
        this.accountType = accountType;
    }

    public int getAccountPicture() {
        return accountPicture;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountPicture(int accountPicture) {
        this.accountPicture = accountPicture;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
