package com.example.mepro.layout_saving.view;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.mepro.R;
import com.example.mepro.layout_saving.adapter.AdapterCategory;
import com.example.mepro.layout_saving.database.CategoryDB;
import com.example.mepro.layout_saving.database.FluctuatingBalancesHistoryDB;
import com.example.mepro.layout_saving.model.Category;
import com.example.mepro.layout_saving.model.FluctuatingBalancesHistory;
import com.example.mepro.util.Dialog_Message;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Fragment_Saving_Add extends Fragment {
    private View viewSavingAdd;
    private TabLayout tablayoutSpentIncome;
    private EditText edtAmountOfMoney, edtNameWork;
    private GridView gridviewCategorys;
    private Button btToday, btYesterday, bt2DayBefore, btAdd;
    private ImageView imgDate;
    
    private Category category;
    private CategoryDB categoryDB;
    private ArrayList<Category> listCategories;
    private ArrayList<Category> listCategories1;
    private AdapterCategory adapterCategory;
    private FluctuatingBalancesHistory balancesHistory;
    private FluctuatingBalancesHistoryDB historyDB;
    private boolean tabSpent = true;
    
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saving_add, container, false);
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        viewSavingAdd = view;
        categoryDB = new CategoryDB(viewSavingAdd.getContext());
        historyDB = new FluctuatingBalancesHistoryDB(viewSavingAdd.getContext());
    
        mappingId();
        event();
        
        getData();
        setData();
        registerForContextMenu(gridviewCategorys);
    
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_layout_work_category_manager, menu);

    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        
        if(id == R.id.action_edit) {
            Dialog_Category dialog = new Dialog_Category(viewSavingAdd.getContext(), (Category) adapterCategory.getItem(position));
            dialog.setCreateCategory(category -> {
                listCategories1.remove(position);
                listCategories1.add(position, category);
                adapterCategory.notifyDataSetChanged();
            });
            return true;
            
        } else if(id == R.id.action_delete) {
            historyDB.deleteData(listCategories1.get(position).getId());
            categoryDB.deleteData(listCategories1.get(position));
            listCategories1.remove(position);
            adapterCategory.notifyDataSetChanged();
            return true;
        }
        
        return false;
    }
    
    
    //===   Ánh xạ view     ===
    private void mappingId() {
        tablayoutSpentIncome = viewSavingAdd.findViewById(R.id.tablayoutSpentIncome);
        edtAmountOfMoney = viewSavingAdd.findViewById(R.id.edtAmountOfMoney);
        edtNameWork = viewSavingAdd.findViewById(R.id.edtNameWork);
        gridviewCategorys = viewSavingAdd.findViewById(R.id.gridviewCategorys);
        btToday = viewSavingAdd.findViewById(R.id.btToday);
        btYesterday = viewSavingAdd.findViewById(R.id.btYesterday);
        bt2DayBefore = viewSavingAdd.findViewById(R.id.bt2DayBefore);
        imgDate = viewSavingAdd.findViewById(R.id.imgDate);
        btAdd = viewSavingAdd.findViewById(R.id.btAdd);
    }
    
    //===   Sự kiện     ===
    private void event() {
        
        //===   Sự kiện tab chi tiêu thu nhập   ===
        tablayoutSpentIncome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0)
                    tabSpent = true;
                else
                    tabSpent = false;
                category = null;
                setData();
            }
    
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
        
            }
    
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
        
            }
        });
        
        //===   Sự kiện khi nhấn vào gridview   ===
        gridviewCategorys.setOnItemClickListener((adapterView, view, i, l) -> {
            gridviewCategorys.setSelection(i);
            adapterCategory.setSelection(i);
            category = (Category) adapterCategory.getItem(i);
            adapterCategory.notifyDataSetChanged();
        });
        
        //===   Sự kiện khi nhấn nút thêm   ===
        btAdd.setOnClickListener(view -> {
            String amountOfMoneyStr = edtAmountOfMoney.getText().toString().trim();
            double amountOfMoney = 0;
            if(amountOfMoneyStr.equals("")) {
                Dialog_Message.showDialogErrorMessage(viewSavingAdd.getContext(), R.drawable.sticker_error, "Lỗi rồi", "Bạn chưa nhập số dư");
                return;
            }
            
            try {
                amountOfMoney = Double.parseDouble(amountOfMoneyStr);
            } catch (Exception e) {
                Dialog_Message.showDialogErrorMessage(viewSavingAdd.getContext(), R.drawable.sticker_error, "Lỗi rồi", "Số dư không đúng định dạnh");
                return;
            }
            
            if(category != null) {
                balancesHistory = new FluctuatingBalancesHistory(category.getId());
                balancesHistory.setTime("2024-04-05 00:00");
                balancesHistory.setName("22");
                balancesHistory.setFluctuatingBalances(amountOfMoney);
                historyDB.insertData(balancesHistory);
                Toast.makeText(viewSavingAdd.getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                
            } else {
                Dialog_Message.showDialogErrorMessage(viewSavingAdd.getContext(), R.drawable.ic_hoicham, "Lỗi rồi", "Bạn chưa chọn danh mục nào");
            }
        });
        
        btToday.setOnClickListener(view ->{
            Category category1 = new Category(edtNameWork.getText().toString(), 222, 1);
            categoryDB.insertData(category1);
            getData();
            setData();
        });
        
        btYesterday.setOnClickListener(view -> {
            Dialog_Category dialog = new Dialog_Category(viewSavingAdd.getContext(), null);
            dialog.setCreateCategory(category -> {
                getData();
                setData();
            });
        });
        
    }
    
    //===   set dữ liệu liêu danh mục    ===
    private void setData() {
        listCategories1 = new ArrayList<>();
        if(listCategories != null) {
            if(tabSpent) {
                for(Category category1 : listCategories) {
                    if(category1.getType() == 1)
                        listCategories1.add(category1);
                }
                
            } else {
                for(Category category1 : listCategories) {
                    if(category1.getType() == 2)
                        listCategories1.add(category1);
                }
            }
        }
        adapterCategory = new AdapterCategory(viewSavingAdd.getContext(), listCategories1);
        gridviewCategorys.setAdapter(adapterCategory);
    }
    
    //===   get dữ liệu danh mục    ===
    private void getData() {
        listCategories = categoryDB.getData();
    }
    
}
