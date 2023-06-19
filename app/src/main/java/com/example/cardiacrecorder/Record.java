package com.example.cardiacrecorder;

public class Record {
    private String date;
    private String time;
    private String sys_press;
    private String dia_press;
    private String heart_rt;
    private String comment;


    public Record() {
    }

    public Record(String date, String time, String sys_press, String dia_press, String heart_rt, String comment) {
        this.date = date;
        this.time = time;
        this.sys_press = sys_press;
        this.dia_press = dia_press;
        this.heart_rt = heart_rt;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSys_press() {
        return sys_press;
    }

    public String getDia_press() {
        return dia_press;
    }

    public String getHeart_rt() {
        return heart_rt;
    }

    public String getComment() {
        return comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSys_press(String sys_press) {
        this.sys_press = sys_press;
    }

    public void setDia_press(String dia_press) {
        this.dia_press = dia_press;
    }

    public void setHeart_rt(String heart_rt) {
        this.heart_rt = heart_rt;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}


