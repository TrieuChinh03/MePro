package com.example.mepro;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mepro.layout_listword.adapter.AdapterCategory;
import com.example.mepro.layout_listword.database.CategoryDB;
import com.example.mepro.layout_listword.model.Category;

import java.util.ArrayList;
import java.util.List;

public class TestView extends AppCompatActivity {

    private List<Category> buttonList;
    private AdapterCategory adapter;
    private CategoryDB db;
    private RecyclerView recyclerView;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);

        db = new CategoryDB(this);
        buttonList = db.getListCategory();
        if(buttonList == null) {
            buttonList = new ArrayList<>();
        }
        adapter = new AdapterCategory(buttonList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            // Thêm một mục mới vào RecyclerView khi nút được nhấn
            Category category = new Category("hả");
            db = new CategoryDB(TestView.this);
            db.insertData(category);
            buttonList.add(category);
            adapter.notifyItemInserted(buttonList.size() - 1);
        });

        adapter.setOnItemClickListener(position -> {
            adapter.setSelectedPosition(position);
            Toast.makeText(this, "Danh mục " +  buttonList.get(position).getId(), Toast.LENGTH_SHORT).show();
        });
    }
}
