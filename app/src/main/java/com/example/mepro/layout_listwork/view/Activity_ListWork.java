package com.example.mepro.layout_listwork.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mepro.R;
import com.example.mepro.layout_listwork.adapter.AdapterCategory;
import com.example.mepro.layout_listwork.adapter.AdapterViewPage;
import com.example.mepro.layout_listwork.database.CategoryDB;
import com.example.mepro.layout_listwork.database.WorkDB;
import com.example.mepro.layout_listwork.model.Category;
import com.example.mepro.layout_listwork.model.Work;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Activity_ListWork extends AppCompatActivity {
    private FloatingActionButton btAdd;
    private ViewPager2 viewPager;
    private AdapterViewPage adapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();;
    private CategoryDB dbCategory;
    private Category category;
    private RecyclerView rccvListCategory;
    private List<Category> listCategory;
    private WorkDB dbWork;
    private Toolbar tbListWork;
    private Spinner spnTimeFilter;
    private final String[] timeFilter = {"Hôm nay", "Ngày mai", "Tuần này", "Tháng này"};
    private int filer = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_work_list);
        mappingId();
        event();
        setSpinnerTimeFilter();
        setSupportActionBar(tbListWork);
        setCategory();
        
    }
    
    @Override
    protected void onRestart() {
        super.onRestart();
        setCategory();
    }
    
    //===   Ánh xạ ===
    private void mappingId() {
        btAdd = findViewById(R.id.btAdd);
        spnTimeFilter = findViewById(R.id.spnTimeFilter);
        rccvListCategory = findViewById(R.id.rccvListCategory);
        viewPager = findViewById(R.id.vp2FragmentListWork);
        tbListWork = findViewById(R.id.tbListWork);
    
    }
    
    //===   Sự kiện     ===
    private void event() {
        //===   Sự kiện khi nhấn nút add    ===
        btAdd.setOnClickListener(view  ->{
            if(listCategory.size() > 1) {
                Work work = new Work();
                Intent intent = new Intent(this, Activity_Work.class);
                intent.putExtra("work", work);
                startActivity(intent);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Ồh không!");
                builder.setIcon(R.drawable.ic_hoathinh_hoicham);
                builder.setMessage("Bạn phải tạo trước 1 danh mục");
                builder.setPositiveButton("OK", (dialogInterface, i) -> {
                    Intent intent = new Intent(this, Activity_Category.class);
                    startActivity(intent);
                });
                builder.show();
            }
        });
        
        //===   Sự kiện khi chọn spinner lọc  thời gian ===
        spnTimeFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filer = i;
                setFragment();
            }
    
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
        
            }
        });
    }
    
    //===   Set dữ liệu cho danh mục    ===
    private void setCategory() {
        //===   Set dạng hiển thị ngang     ===
        rccvListCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dbCategory = new CategoryDB(this);
        listCategory = dbCategory.getListCategory(0);
        
        //===   Lấy  dữ liệu    ===
        if(listCategory == null || listCategory.size() == 0) {
            listCategory = new ArrayList<>();
        }
        
        //===   Tạo danh mục tất cả     ===
        listCategory.add(0,new Category("Tất cả",0));
    
    
        //===   Tạo đối tượng adapter và set sự kiện khi nhấn ===
        AdapterCategory adapter = new AdapterCategory(listCategory);
        adapter.setOnItemClickListener(position -> {
            if(position != adapter.getSelectedPosition()) {
                adapter.setSelectedPosition(position);
                category = listCategory.get(position);
                setFragment();
            }
        });
        
        //===   Khởi tạo chọn danh mục đầu tiên khi chạy    ===
        adapter.setSelectedPosition(0);
        category = listCategory.get(0);
        setFragment();
        rccvListCategory.setAdapter(adapter);
    }
    
    //===   Set thời gian lọc   ===
    private void setSpinnerTimeFilter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeFilter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTimeFilter.setAdapter(adapter);
    }
    
    //===   Set fragment listview   ===
    private void setFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_ListWork(this,category,filer));
        adapter = new AdapterViewPage(this, fragmentList);
        viewPager.setAdapter(adapter);
    }
    
    //===   Tạo menu 3 chấm   ===
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout_work_list, menu);
        return true;
    }
    
    //====== Xử lý sự kiện các menu đã tạo ở dấu 3 chấm ======
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_action_setting_category) {
            Intent intent = new Intent(this, Activity_Category.class);
            startActivity(intent);
        }
        return true;
    }
    
}
