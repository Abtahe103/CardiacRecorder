package com.example.cardiacrecorder;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Record implements Parcelable {
    private String key;
    private String date;
    private String time;
    private String sys_press;
    private String dia_press;
    private String heart_rt;
    private String comment;

    public Record() {
    }

    public Record(String key, String date, String time, String sys_press, String dia_press, String heart_rt, String comment) {
        this.key = key;
        this.date = date;
        this.time = time;
        this.sys_press = sys_press;
        this.dia_press = dia_press;
        this.heart_rt = heart_rt;
        this.comment = comment;
    }


    protected Record(Parcel in) {
        key = in.readString();
        date = in.readString();
        time = in.readString();
        sys_press = in.readString();
        dia_press = in.readString();
        heart_rt = in.readString();
        comment = in.readString();
    }

    public static final Creator<Record> CREATOR = new Creator<Record>() {
        @Override
        public Record createFromParcel(Parcel in) {
            return new Record(in);
        }

        @Override
        public Record[] newArray(int size) {
            return new Record[size];
        }
    };

    public String getKey() {
        return key;
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

    public void setKey(String key) {
        this.key = key;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(sys_press);
        dest.writeString(dia_press);
        dest.writeString(heart_rt);
        dest.writeString(comment);
    }
}


