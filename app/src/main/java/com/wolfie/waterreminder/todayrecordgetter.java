package com.wolfie.waterreminder;

public class todayrecordgetter {
String id;
String volume;
String date;

    public todayrecordgetter(String id, String volume, String date) {
        this.id = id;
        this.volume = volume;
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getVolume() {
        return volume;
    }

    public String getDate() {
        return date;
    }
}
