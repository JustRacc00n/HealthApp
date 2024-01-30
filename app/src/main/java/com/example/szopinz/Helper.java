package com.example.szopinz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Helper {

    public static String addLeadingZero(String number){
        if(number.length()<2){number = "0"+number;}
        return number;
    }

    public static String convertDateFormatOlder(String yyyyMMDD){
        SimpleDateFormat yMDFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {Date date = yMDFormat.parse(yyyyMMDD);
        SimpleDateFormat dMYFormat = new SimpleDateFormat("dd/MM/yyyy");
            assert date != null;
            return dMYFormat.format(date);        }
        catch (ParseException e){e.printStackTrace();}
        return null;
    }

    public static String convertDateToString(Date date){
        SimpleDateFormat dMYFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dMYFormat.format(date);
    }

    public static String convertDatetoDBString(Date date){
        SimpleDateFormat yMDFormat = new SimpleDateFormat("yyyy-MM-dd");
        return yMDFormat.format(date);
    }

    public static Date getCurrentDate() {
        Calendar today = Calendar.getInstance();
        return today.getTime();
    }

}
