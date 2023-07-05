package com.example.cardiacrecorder;
import static org.junit.Assert.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

public class UnitTest {
    /**
     * checks if
     */
    @Test
    public void testAdd() throws InterruptedException {

        Record dataList = new Record("1","02/03/2021","12:15","110","75","71","daily checkup");
        assertEquals("02/03/2021",dataList.getDate());
        assertEquals("12:15",dataList.getTime());
        assertEquals("110",dataList.getSys_press());
        assertEquals("75",dataList.getDia_press());
        assertEquals("71",dataList.getHeart_rt());
        assertEquals("daily checkup",dataList.getComment());
    }
}
