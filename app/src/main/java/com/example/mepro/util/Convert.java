package com.example.mepro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Convert {
    public static String convertTime(String inputTimeStr) {
        String inputFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        String outputFormat = "HH:mm:ss',' dd 'tháng' MM',' yyyy";
        
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputFormatter = new SimpleDateFormat(outputFormat);
            Date inputDate = inputFormatter.parse(inputTimeStr);
            String outputDateStr = outputFormatter.format(inputDate);
            return outputDateStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getDateStr(String inputTimeStr) {
        String inputFormat = "HH:mm:ss',' dd 'tháng' MM',' yyyy";
        String outputFormat = "dd-MM-yyyy";
        
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputFormatter = new SimpleDateFormat(outputFormat);
            Date inputDate = inputFormatter.parse(inputTimeStr);
            String outputDateStr = outputFormatter.format(inputDate);
            return outputDateStr;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Date getTime(String inputTimeStr) {
        String inputFormat = "HH:mm:ss',' dd 'tháng' MM',' yyyy";
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat(inputFormat);
            Date outputDate = inputFormatter.parse(inputTimeStr);
            return outputDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<String> stringToList(String str) {
        str = str.substring(1, str.length() - 1);
        List<String> list = Arrays.asList(str.split(", "));
        return list;
    }
    
    public static boolean stringToBoolean(String strBoolean) {
        boolean b = Boolean.parseBoolean(strBoolean);
        return b;
    }
}
