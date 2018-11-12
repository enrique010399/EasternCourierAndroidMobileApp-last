package com.example.easterncourier.easterncourier;

public class admin_transaction_history_item {
    int transactionHistoryClientImage;
    String transactionHistoryClientName,transactionHistoryDate;

    public admin_transaction_history_item(int transactionHistoryClientImage, String transactionHistoryClientName, String transactionHistoryDate) {
        this.transactionHistoryClientImage = transactionHistoryClientImage;
        this.transactionHistoryClientName = transactionHistoryClientName;
        this.transactionHistoryDate = transactionHistoryDate;

    }

    public int getTransactionHistoryImage() {
        return transactionHistoryClientImage;
    }

    public String getTransactionHistoryClientName() {
        return transactionHistoryClientName;
    }

    public String getTransactionHistoryDate() {
        return transactionHistoryDate;
    }

    public void setTransactionHistoryImage(int transactionHistoryImage) {
        this.transactionHistoryClientImage = transactionHistoryImage;
    }

    public void setTransactionHistoryClientName(String transactionHistoryClientName) {
        this.transactionHistoryClientName = transactionHistoryClientName;
    }

    public void setTransactionHistoryDate(String transactionHistoryDate) {
        this.transactionHistoryDate = transactionHistoryDate;
    }
}
