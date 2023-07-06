package com.example.cardiacrecorder;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Record implements Parcelable {
    private String key;
    private String date;
    private String time;
    private String sys_press;
    private String dia_press;
    private String heart_rt;
    private String comment;

    private List<Record> record_list = new ArrayList<>();

    public Record() {
    }

    /**
     * Constructor for setting values
     * @param key
     * @param date
     * @param time
     * @param sys_press
     * @param dia_press
     * @param heart_rt
     * @param comment
     */

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

    /**
     * Retuns the key
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * Retuns the date
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Retuns the time
     * @return
     */

    public String getTime() {
        return time;
    }

    /**
     * Retuns the systolic pressure
     * @return
     */

    public String getSys_press() {
        return sys_press;
    }

    /**
     * Retuns the diastolic press
     * @return
     */
    public String getDia_press() {
        return dia_press;
    }

    /**
     * Retuns the heart rate
     * @return
     */
    public String getHeart_rt() {
        return heart_rt;
    }

    /**
     * Retuns the comment
     * @return
     */

    public String getComment() {
        return comment;
    }



    /**
     * sets the key value
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * sets the date value
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * sets the time value
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * sets the systolic pressure value
     * @param sys_press
     */
    public void setSys_press(String sys_press) {
        this.sys_press = sys_press;
    }

    /**
     * sets the diastolic pressure value
     * @param dia_press
     */
    public void setDia_press(String dia_press) {
        this.dia_press = dia_press;
    }

    /**
     * sets the heart rate value
     * @param heart_rt
     */
    public void setHeart_rt(String heart_rt) {
        this.heart_rt = heart_rt;
    }

    /**
     * sets the comment
     * @param comment
     */
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

    public boolean shouldShowAlert() {
        return (Integer.parseInt(sys_press) <= 90 || Integer.parseInt(sys_press) >= 140) ||
                (Integer.parseInt(dia_press) <= 60 || Integer.parseInt(dia_press) >= 90);
    }

    /**
     * returns the size of the list
     * @return
     */
    public int count()
    {
        return record_list.size();
    }

    /**
     * if data already exists, throws exception else adds data
     * @param data
     * data is the parameter
     */

    public void addUserData(Record data)
    {
        if(record_list.contains(data))
        {
            throw new IllegalArgumentException();
        }
        record_list.add(data);
    }

    /**
     * returns the datalist
     * @return
     *  returns inserted recorded list
     */
    public List<Record> getData()
    {
        List<Record>datalist = record_list;
        return datalist;
    }

    /**
     * deletes data if exist otherwise throws exception
     * @param data
     * is the parameter
     */
    public void deleteUserData(Record data)
    {
        List<Record> datalist = record_list;
        if(datalist.contains(data))
        {
            record_list.remove(data);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}


