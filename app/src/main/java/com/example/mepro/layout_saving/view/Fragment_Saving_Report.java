package com.example.mepro.layout_saving.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mepro.R;
import com.example.mepro.layout_saving.adapter.AdapterFluctuatingBalances;
import com.example.mepro.layout_saving.database.CategoryDB;
import com.example.mepro.layout_saving.database.FluctuatingBalancesHistoryDB;
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
    private FluctuatingBalancesHistoryDB historyDB;
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
        setData();

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
            if (time == 0)
                calendar.add(Calendar.DAY_OF_MONTH, -1);

            else if (time == 1)
                calendar.add(Calendar.WEEK_OF_YEAR, -1);

            else if (time == 2)
                calendar.add(Calendar.MONTH, -1);

            else if (time == 3)
                calendar.add(Calendar.YEAR, -1);
    
            setTime();
        });
    
        //===   Sự kiện khi chọn img thời gian sau ===
        imgbtLater.setOnClickListener(view -> {
            if (time == 0)
                calendar.add(Calendar.DAY_OF_MONTH, +1);

            else if (time == 1)
                calendar.add(Calendar.WEEK_OF_YEAR, +1);

            else if (time == 2)
                calendar.add(Calendar.MONTH, +1);

            else if (time == 3)
                calendar.add(Calendar.YEAR, +1);
    
            setTime();
        });
    
        //===   Sự kiện khi nhấn image  ===
        imgPieOrBar.setOnClickListener(view ->{
            categoryDB = new CategoryDB(viewSavingReport.getContext());
            //categoryDB.insertData(new Category("Con cặc", 2, 2));
            ArrayList<Category> categories = categoryDB.getData();
            category = categories.get(2);
            historyDB = new FluctuatingBalancesHistoryDB(viewSavingReport.getContext());
            FluctuatingBalancesHistory history = new FluctuatingBalancesHistory(category.getId());
            history.setFluctuatingBalances(20000);
            history.setTime("12/2/2001");
            history.setName("Mua con cặc");
            historyDB.insertData(history);
            Toast.makeText(viewSavingReport.getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
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
    
    //===   Set data các thay đổi số dư     ===
    private void setData() {
        historyDB = new FluctuatingBalancesHistoryDB(viewSavingReport.getContext());
        listFluctuatingBalances = historyDB.getData(3,"2024-04");
        
        if (listFluctuatingBalances != null) {
            ArrayList<Integer> idCategorys = new ArrayList<>();
            ArrayList<FluctuatingBalancesHistory> histories = new ArrayList<>();
            
            int length = listFluctuatingBalances.size();
            for (int i = 0; i < length; i++) {
                int idCategory = listFluctuatingBalances.get(i).getIdCategory();
                boolean idAlreadyExist = false;
                for (Integer id : idCategorys) {
                    if (id == idCategory) {
                        idAlreadyExist = true;
                        break;
                    }
                }
                if (!idAlreadyExist)
                    idCategorys.add(idCategory);
            }
    
            for (int i = 0; i < idCategorys.size(); i++) {
                int idCategory = idCategorys.get(i);
                FluctuatingBalancesHistory history = new FluctuatingBalancesHistory(idCategory);
                for(int j = 0; j < length; j++) {
                    FluctuatingBalancesHistory history1 = listFluctuatingBalances.get(j);
                    if(idCategory == history1.getIdCategory()) {
                        history.setFluctuatingBalances(history.getFluctuatingBalances() + history1.getFluctuatingBalances());
                    }
                }
                histories.add(history);
            }
            
            
            AdapterFluctuatingBalances adapter = new AdapterFluctuatingBalances(viewSavingReport.getContext(), R.layout.item_saving_history , histories);
            lvListOfBalanceChanges.setAdapter(adapter);
            
        }
        
    }
}
