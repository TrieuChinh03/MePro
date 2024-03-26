package com.example.mepro.util;

import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<Date> {
    private boolean b;
    
    public DateComparator(boolean b) {
        this.b = b;
    }
    @Override
    public int compare(Date date1, Date date2) {
        //===   Chuyển đổi Date thành long  ===
        long time1 = date1.getTime();
        long time2 = date2.getTime();
    
        //===   So sánh giá trị long của hai date (đảo ngược vị trí của date2 và date1) ===
        if(b)
            return Long.compare(time1, time2);
        else
            return Long.compare(time2, time1);
    }
}
