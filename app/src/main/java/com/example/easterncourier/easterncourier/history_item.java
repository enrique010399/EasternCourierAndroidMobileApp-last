package com.example.easterncourier.easterncourier;

public class history_item {
    String historyType,historyTime;
    int historyImage;

    public history_item(String historyType, String historyTime, int historyImage) {
        this.historyType = historyType;
        this.historyTime = historyTime;
        this.historyImage = historyImage;
    }

    public String getHistoryType() {
        return historyType;
    }

    public String getHistoryTime() {
        return historyTime;
    }

    public int getHistoryImage() {
        return historyImage;
    }

    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }

    public void setHistoryTime(String historyTime) {
        this.historyTime = historyTime;
    }

    public void setHistoryImage(int historyImage) {
        this.historyImage = historyImage;
    }
}
