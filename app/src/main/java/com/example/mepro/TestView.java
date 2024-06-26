package com.example.mepro;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mepro.util.FragmentTest;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;

public class TestView extends AppCompatActivity {
    private BarData barData;
    private BarDataSet barDataSet;
    private Button nhan;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_saving);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,
                new FragmentTest()).commit();
/*
        PieChart pieChart = findViewById(R.id.piechartMoney);
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(18.5f, "Ăn uống"));
        entries.add(new PieEntry(26.7f, "Tiền nhà"));
        entries.add(new PieEntry(24.0f, "Mua sắm"));

        PieDataSet dataSet = new PieDataSet(entries, "Tiền (đv: đồng)");
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieChart.setCenterText("88.8 Tr");
        pieChart.animateY(1000, Easing.EaseInOutCubic);
        pieChart.invalidate();


 */




    /*
        nhan = findViewById(R.id.nhan);
        BarChart bieudo = findViewById(R.id.bieudo);
        // Tìm kiếm biểu đồ thanh trong layout
    
        ArrayList<BarEntry> visitors = new ArrayList<>();
    

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

     */
    
    }

}

