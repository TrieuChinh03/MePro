package com.example.mepro.layout_saving.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mepro.R;
import com.example.mepro.layout_saving.database.CategoryDB;
import com.example.mepro.layout_saving.model.Category;
import com.example.mepro.layout_saving.model.FluctuatingBalancesHistory;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_Saving_Report extends Fragment {
    private View viewSavingReport;
    private TabLayout tablayoutTime;
    private ImageButton imgbtBefore, imgbtLater;
    private TextView tvTime;
    private TextView tvSpent, tvIncome, tvMainBalances;
    private ImageView imgPieOrBar;
    private PieChart piechartBalances;
    private ListView lvListOfBalanceChanges;
    private int time = 0;
    private Calendar calendar = Calendar.getInstance();
    private Category category;
    private CategoryDB categoryDB;
    private ArrayList<FluctuatingBalancesHistory> listFluctuatingBalances;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saving_report, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewSavingReport = view;
        mappingId();
        event();

    }

    //===   Ánh xạ view     ===
    private void mappingId() {
        tablayoutTime = viewSavingReport.findViewById(R.id.tablayoutTime);
        imgbtBefore = viewSavingReport.findViewById(R.id.imgbtBefore);
        imgbtLater = viewSavingReport.findViewById(R.id.imgbtLater);
        tvTime = viewSavingReport.findViewById(R.id.tvTime);
        tvSpent = viewSavingReport.findViewById(R.id.tvSpent);
        tvIncome = viewSavingReport.findViewById(R.id.tvIncome);
        tvMainBalances = viewSavingReport.findViewById(R.id.tvMainBalances);
        imgPieOrBar = viewSavingReport.findViewById(R.id.imgPieOrBar);
        piechartBalances = viewSavingReport.findViewById(R.id.piechartBalances);
        lvListOfBalanceChanges = viewSavingReport.findViewById(R.id.lvListOfBalanceChanges);
    }

    //===   Sự kiện     ===
    private void event() {
        //===   Sự kiện khi chọn tab thời gian  ===
        tablayoutTime.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
               @Override
               public void onTabSelected(TabLayout.Tab tab) {
                    time = tab.getPosition();
                    calendar = Calendar.getInstance();
                    setTime();
               }

               @Override
               public void onTabUnselected(TabLayout.Tab tab) {

               }

               @Override
               public void onTabReselected(TabLayout.Tab tab) {

               }
           });

        //===   Sự kiện khi chọn img thời gian trước ===
        imgbtBefore.setOnClickListener(view -> {
            if(time == 0)
                calendar.add(Calendar.DAY_OF_MONTH, -1);

            else if(time == 1)
                calendar.add(Calendar.WEEK_OF_YEAR, -1);

            else if(time == 2)
                calendar.add(Calendar.MONTH, -1);

            else if(time == 3)
                calendar.add(Calendar.YEAR, -1);

            setTime();
        });

        //===   Sự kiện khi chọn img thời gian sau ===
        imgbtLater.setOnClickListener(view -> {
            if(time == 0)
                calendar.add(Calendar.DAY_OF_MONTH, +1);

            else if(time == 1)
                calendar.add(Calendar.WEEK_OF_YEAR, +1);

            else if(time == 2)
                calendar.add(Calendar.MONTH, +1);

            else if(time == 3)
                calendar.add(Calendar.YEAR, +1);

            setTime();
        });
    }

    //===   Set thời gian lên textview  ===
    private void setTime() {
        if(time == 0)
            tvTime.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));
        else if(time == 1)
            tvTime.setText("Tuần " + calendar.get(Calendar.WEEK_OF_YEAR));
        else if(time == 2)
            tvTime.setText(calendar.get(Calendar.MONTH) + 1 + "/" + calendar.get(Calendar.YEAR));
        else if(time == 3)
            tvTime.setText("" + calendar.get(Calendar.YEAR));
    }

}
