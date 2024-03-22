package com.example.mepro;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mepro.util.DialogCalendar;
import com.example.mepro.util.DialogColor;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class TestView extends AppCompatActivity {
    private BarData barData;
    private BarDataSet barDataSet;
    private Button nhan;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
    
        nhan = findViewById(R.id.nhan);
        BarChart bieudo = findViewById(R.id.bieudo);
        // Tìm kiếm biểu đồ thanh trong layout
    
        ArrayList<BarEntry> visitors = new ArrayList<>();
    
    
        nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visitors.clear();
                visitors.add(new BarEntry(1, 420));
                visitors.add(new BarEntry(2, 475));
                visitors.add(new BarEntry(3, 999));
                visitors.add(new BarEntry(4, 520));
                visitors.add(new BarEntry(5, 334));
                visitors.add(new BarEntry(6, 588));
                visitors.add(new BarEntry(7, 555));
                visitors.add(new BarEntry(8, 630));
                visitors.add(new BarEntry(9, 222));
                visitors.add(new BarEntry(10, 351));
// Tạo bộ dữ liệu
                barDataSet = new BarDataSet(visitors, "Tiền");


// Tùy chỉnh bộ dữ liệu
                barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(10f);

// Tạo dữ liệu cho biểu đồ
                barData = new BarData(barDataSet);

// Hiển thị biểu đồ
                bieudo.setData(barData);

// Tùy chỉnh biểu đồ
                bieudo.setFitBars(true);
                bieudo.getDescription().setText("Biểu đồ tiền");
    
    
                bieudo.animateX(1000);
                bieudo.animateY(1500);        }
        });
// Khởi tạo danh sách dữ liệu

// Thêm dữ liệu vào danh sách
        visitors.add(new BarEntry(1, 420));
        visitors.add(new BarEntry(2, 475));
        visitors.add(new BarEntry(3, 500));
        visitors.add(new BarEntry(4, 520));
        visitors.add(new BarEntry(5, 600));
        visitors.add(new BarEntry(6, 588));
        visitors.add(new BarEntry(7, 470));
        visitors.add(new BarEntry(8, 630));
        visitors.add(new BarEntry(9, 351));
        visitors.add(new BarEntry(10, 902));
        visitors.add(new BarEntry(11, 503));
        visitors.add(new BarEntry(12, 503));



// Tạo bộ dữ liệu
        barDataSet = new BarDataSet(visitors, "Tiền");


// Tùy chỉnh bộ dữ liệu
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(10f);

// Tạo dữ liệu cho biểu đồ
        barData = new BarData(barDataSet);

// Hiển thị biểu đồ
        bieudo.setData(barData);

// Tùy chỉnh biểu đồ
        bieudo.setFitBars(true);
        bieudo.getDescription().setText("Biểu đồ tiền");
        
        
        bieudo.animateX(1000);
        bieudo.animateY(1500);
    
    }

}

