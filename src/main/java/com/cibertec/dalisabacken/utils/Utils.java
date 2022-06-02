package com.cibertec.dalisabacken.utils;


import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    private static Date date = new Date();


    public static String getDateCurrent() {
        return dateFormat.format(date).concat(" - ");
    }

    public static String getTimeCurrent() {
        return timeFormat.format(date);
    }

    public static String[] getSplitDate() {
        return dateFormat.format(date).split("/");
    }

    public static Date getDate() {
        return date;
    }

}
