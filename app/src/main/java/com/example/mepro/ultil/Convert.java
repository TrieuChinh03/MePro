package com.example.mepro.ultil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert {
    public static String convertTimeFormat(String inputTimeStr) {
        String inputFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        String outputFormat = "HH:mm',' dd 'tháng' MM',' yyyy";
        
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
    
    public static String getDate(String inputTimeStr) {
        String inputFormat = "HH:mm',' dd 'tháng' MM',' yyyy";
        String outputFormat = "dd-MM-yyyy";
        
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
}
