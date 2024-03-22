package com.example.mepro.layout_listwork.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mepro.R;
import com.example.mepro.layout_listwork.adapter.AdapterCategoryManager;
import com.example.mepro.layout_listwork.database.CategoryDB;
import com.example.mepro.layout_listwork.model.Category;

import java.util.ArrayList;
import java.util.List;

public class Activity_Category extends AppCompatActivity {
    private ImageButton imgbtBack;
    private Button btAdd;
    private RecyclerView rccvListCategoryManager;
    private Category category;
    private CategoryDB categoryDB;
    private List<Category> listCategory;
    private AdapterCategoryManager adapterCategory;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_work_category);
        
        mappingId();
        setData();
        event();
        
    }
    
    
    //===   Ánh xạ id   ===
    private void mappingId() {
        imgbtBack = findViewById(R.id.imgbtBack);
        rccvListCategoryManager = findViewById(R.id.rccvListCategoryManager);
        btAdd = findViewById(R.id.btAdd);
    
    }
    
    //===   Sự kiện     ===
    private void event() {
        //===   Sự kiện nhấn thêm   ===
        btAdd.setOnClickListener(view -> {
            category = new Category(-1,"Danh mục chưa xác định", R.color.colorDarkMagenta);
            listCategory.add(category);
            adapterCategory.notifyDataSetChanged();
        });
        
        //===   Sự kiện khi nhấn back   ===
        imgbtBack.setOnClickListener(view -> finish());
    }
    
    //===   Set dữ liệu lên recyclerview    ===
    private void setData() {
        rccvListCategoryManager.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getData();
        
        if(listCategory == null)
            listCategory = new ArrayList<>();
        
        adapterCategory = new AdapterCategoryManager(listCategory,this);
        rccvListCategoryManager.setAdapter(adapterCategory);
    }
    
    //===   Lấy dữ liệu     ===
    private void getData() {
        categoryDB = new CategoryDB(this);
        listCategory = categoryDB.getListCategory(0);
    }
}
