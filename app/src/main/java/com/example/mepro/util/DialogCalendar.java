package com.example.mepro.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;

import com.example.mepro.R;

import java.time.LocalDateTime;


public class DialogCalendar {
    private static String time = "";
    
    public interface DialogCalendarListener {
        void onTimeSelected(String time);
    }
    
    public static void getTimeStr(Context context, DialogCalendarListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_calendar, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        Button btOK = dialogView.findViewById(R.id.btOK);
        EditText edtDay = dialogView.findViewById(R.id.edtDay);
        EditText edtMonth = dialogView.findViewById(R.id.edtMonth);
        EditText edtYear = dialogView.findViewById(R.id.edtYear);
        EditText edtHour = dialogView.findViewById(R.id.edtHour);
        EditText edtMin = dialogView.findViewById(R.id.edtMin);
        CheckBox cbNotTime = dialogView.findViewById(R.id.cbNotTime);
        LinearLayout layoutTime = dialogView.findViewById(R.id.layoutTime);
        dialog.show();
    
        LocalDateTime localDateTime = LocalDateTime.now();
        edtDay.setText(String.valueOf(localDateTime.getDayOfMonth()));
        edtMonth.setText(String.valueOf(localDateTime.getMonthValue()));
        edtYear.setText(String.valueOf(localDateTime.getYear()));
        edtHour.setText(String.valueOf(localDateTime.getHour()));
        edtMin.setText(String.valueOf(localDateTime.getMinute()));
        
        cbNotTime.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b)
                layoutTime.setEnabled(false);
            else
                layoutTime.setEnabled(true);
        });
        
        btOK.setOnClickListener(v -> {
            if(cbNotTime.isChecked()) {
                time = "Không có thời gian";
                listener.onTimeSelected(time);
                dialog.dismiss();
            }
            else {
                try {
                    int day = Integer.parseInt(edtDay.getText().toString().trim());
                    int month = Integer.parseInt(edtMonth.getText().toString().trim());
                    int year = Integer.parseInt(edtYear.getText().toString().trim());
                    int hour = Integer.parseInt(edtHour.getText().toString().trim());
                    int min = Integer.parseInt(edtMin.getText().toString().trim());
                    
                    if(isValidDate(day, month, year)) {
                        if(isValidTime(hour, min)) {
                            listener.onTimeSelected(creatTime(hour, min, day, month, year));
                            dialog.dismiss();
                        } else {
                            Dialog_Message.showDialogErrorMessage(context, R.drawable.sticker_error,"Lỗi rồi","Lỗi định dạng giờ, phút!");
                        }
                    } else {
                        Dialog_Message.showDialogErrorMessage(context, R.drawable.sticker_error,"Lỗi rồi","Lỗi định dạng ngày, tháng, năm!");
                    }
                    
                } catch (Exception e) {
                    Dialog_Message.showDialogErrorMessage(context,R.drawable.sticker_error,"Lỗi rồi", "Không được nhập kí tự đặc biệt");
                }
            }

        });
    }
    
    private static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12)
            return false;
        
        int maxDays;
        if (month == 2) {
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) { // Năm nhuận
                maxDays = 29;
            } else {
                maxDays = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDays = 30;
        } else {
            maxDays = 31;
        }
    
        if (day < 1 || day > maxDays)
            return false;
        
        if(year < 2000 && year > 2100)
            return false;
        return true;
    }
    
    private static boolean isValidTime(int hour, int min) {
        if(hour > 24 || hour < 0)
            return false;
        if(min > 60 || min < 0)
            return false;
        return true;
    }
    
    private static String creatTime(int hour, int min, int day, int month, int year) {
        StringBuilder timeBuilder = new StringBuilder();
    
        if(hour > 10)
            timeBuilder.append(hour + ":");
        else
            timeBuilder.append("0" + hour + ":");
        
        if(min > 10)
            timeBuilder.append(min + ":00, ");
        else
            timeBuilder.append("0" + min + ":00, ");
        
        if(day > 10)
            timeBuilder.append(day + " tháng ");
        else
            timeBuilder.append("0" +  day + " tháng ");
        
        if(month > 10)
            timeBuilder.append(month + ", ");
        else
            timeBuilder.append("0" +  month + ", ");
        
        timeBuilder.append(year);
        
        return timeBuilder.toString();
    }
    
}
