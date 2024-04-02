package com.example.mepro.util;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mepro.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FragmentTest extends Fragment {
    private boolean pieOrChart = true;

    public FragmentTest() {
        // Constructor mặc định
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout cho Fragment này
        return inflater.inflate(R.layout.fragment_saving, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
       ImageView imgPieOrBar = view.findViewById(R.id.imgPieOrBar);
        PieChart pieChart = view.findViewById(R.id.piechartMoney);
        BarChart bieudo = view.findViewById(R.id.barchartStatistical);
        imgPieOrBar.setOnClickListener(view1 -> {
            if(pieOrChart) {
                imgPieOrBar.animate().rotationBy(360).setDuration(300).start();
                pieChart.setVisibility(View.GONE);
                bieudo.setVisibility(View.VISIBLE);
                pieOrChart = false;
            } else {
                imgPieOrBar.animate().rotationBy(360).setDuration(300).start();
                pieChart.setVisibility(View.VISIBLE);
                bieudo.setVisibility(View.GONE);
                pieOrChart = true;
            }
        });

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
        BarDataSet barDataSet = new BarDataSet(visitors, "Tiền");


// Tùy chỉnh bộ dữ liệu
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(10f);

// Tạo dữ liệu cho biểu đồ
        BarData barData = new BarData(barDataSet);

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
