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
    public void testAddRecord(){

        Record dataList = new Record("1","02/03/2021","12:15","110","75","71","daily checkup");
        assertEquals("02/03/2021",dataList.getDate());
        assertEquals("12:15",dataList.getTime());
        assertEquals("110",dataList.getSys_press());
        assertEquals("75",dataList.getDia_press());
        assertEquals("71",dataList.getHeart_rt());
        assertEquals("daily checkup",dataList.getComment());
    }


    @Test
    public void testaddUserData()
    {
        Record datalist = new Record("1","02/03/2021","12:15","110","75","71","daily checkup");
        datalist.addUserData(datalist);
        assertEquals(1,datalist.count());

        Record datalist2 = new Record("2","02/03/2021","12:15","110","75","71","daily checkup");
        datalist.addUserData(datalist2);
        assertEquals(2,datalist.count());

        assertTrue(datalist.getData().contains(datalist));
        assertTrue(datalist.getData().contains(datalist2));
    }

    @Test
    public void testdeleteUserData()
    {
        Record datalist = new Record("1","02/03/2021","12:15","110","75","71","daily checkup");
        datalist.addUserData(datalist);
        assertEquals(1,datalist.getData().size());

        Record datalist2 = new Record("2","02/03/2021","12:15","110","75","71","daily checkup");
        datalist.addUserData(datalist2);
        assertEquals(2,datalist.count());

        datalist.deleteUserData(datalist);
        assertEquals(1,datalist.getData().size());
        assertFalse(datalist.getData().contains(datalist));

        datalist.deleteUserData(datalist2);
        assertEquals(0,datalist.getData().size());
        assertFalse(datalist.getData().contains(datalist2));

    }

    @Test
    public void testaddUserDataException()
    {
        Record datalist = new Record("1","02/03/2021","12:15","110","75","71","daily checkup");
        datalist.addUserData(datalist);

        assertThrows(IllegalArgumentException.class,() -> datalist.addUserData(datalist));
    }

    @Test
    public void testCount()
    {
        Record datalist = new Record("1","02/03/2021","12:15","110","75","71","daily checkup");
        datalist.addUserData(datalist);
        assertEquals(1,datalist.count());
    }
}
