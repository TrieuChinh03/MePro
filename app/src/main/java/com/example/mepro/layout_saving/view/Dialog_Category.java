package com.example.mepro.layout_saving.view;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.example.mepro.R;
import com.example.mepro.layout_saving.database.CategoryDB;
import com.example.mepro.layout_saving.model.Category;
import com.example.mepro.util.Dialog_Message;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Dialog_Category {
    private AlertDialog dialog;
    private Context context;
    private View dialogView;
    private createCategory create;
    
    private int type = 1;
    private ArrayList<Integer> listIcons;
    private int iconSelected = 0;
    
    private CategoryDB categoryDB;
    private Category category;
    private AdapterIcon adapter;
    
    private TabLayout tablayoutSpentIncome;
    private EditText edtNameCategory;
    private GridView gridViewIcons;
    private Button btOK;
    
    public Dialog_Category(Context context, Category category) {
        this.context = context;
        this.category = category;
    }
    
    //===   Mở dialog   ===
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        dialogView = inflater.inflate(R.layout.dialog_saving_category_manager, null);
        builder.setView(dialogView);
        dialog = builder.create();
        categoryDB = new CategoryDB(context);
        mappingId();
        event();
        
        if(category != null) {
            edtNameCategory.setText(category.getName());
            type = category.getType();
            iconSelected = category.getIcon();
            btOK.setText("Sửa");
        }
        
        TabLayout.Tab tab = tablayoutSpentIncome.getTabAt(type-1);
        tab.select();
        
        setData();
        dialog.show();
    }
    
    //===   Ánh xạ view     ===
    private void mappingId() {
        tablayoutSpentIncome = dialogView.findViewById(R.id.tablayoutSpentIncome);
        edtNameCategory = dialogView.findViewById(R.id.edtNameCategory);
        gridViewIcons = dialogView.findViewById(R.id.gridViewIcons);
        btOK = dialogView.findViewById(R.id.btOK);
    }
    
    //===   Sự kiện     ===
    private void event() {
        tablayoutSpentIncome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0)
                    type = 1;
                else
                    type = 2;
                setData();
            }
        
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            
            }
        
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            
            }
        });
    
        gridViewIcons.setOnItemClickListener((adapterView, view, i, l) -> {
            iconSelected = listIcons.get(i);
            adapter.notifyDataSetChanged();
        });
    
        btOK.setOnClickListener(view -> {
            String name = edtNameCategory.getText().toString().trim();
            if(!name.equals("") && iconSelected != 0) {
                if(category == null) {
                    category = new Category(name, iconSelected, type);
                    categoryDB.insertData(category);
                    Toast.makeText(context, "Đã thêm danh mục", Toast.LENGTH_SHORT).show();
        
                } else {
                    category.setIcon(iconSelected);
                    category.setName(name);
                    category.setType(type);
                    categoryDB.updateData(category);
                    Toast.makeText(context, "Đã sửa danh mục", Toast.LENGTH_SHORT).show();
                }
                create.created(category);
                dialog.dismiss();
            
            } else {
                Dialog_Message.showDialogErrorMessage(context, R.drawable.ic_hoicham, "Lỗi rồi", "Dữ liệu danh mục chưa đầy đủ");
            }
            
        });
    }
    
    //===   Set dữ liệu     ===
    private void setData() {
        listIcons = new ArrayList<>();
        if(type == 1) {
            for (int i = 1; i <= 5; i++) {
                int drawableId = context.getResources().getIdentifier("ic_saving_" + i, "drawable", context.getPackageName());
                listIcons.add(drawableId);
            }
        
        } else {
            for (int i = 6; i <= 8; i++) {
                int drawableId = context.getResources().getIdentifier("ic_saving_" + i, "drawable", context.getPackageName());
                listIcons.add(drawableId);
            }
        }
        adapter = new AdapterIcon(context, listIcons);
        gridViewIcons.setAdapter(adapter);
    }
    
    //===   interface   ===
    public interface createCategory {
        void created(Category category);
    }
    
    //===   set interface   ===
    public void setCreateCategory(createCategory create) {
        this.create = create;
        show();
    }
    
    //===   Lớp adapter     ===
    private class AdapterIcon extends BaseAdapter {
        private Context mContext;
        private ArrayList<Integer> mIconIds;
        
        public AdapterIcon(Context context, ArrayList<Integer> iconIds) {
            mContext = context;
            mIconIds = iconIds;
        }
        
        @Override
        public int getCount() {
            return mIconIds.size();
        }
        
        @Override
        public Object getItem(int position) {
            return mIconIds.get(position);
        }
        
        @Override
        public long getItemId(int position) {
            return position;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setImageResource(mIconIds.get(position));
            
            if(mIconIds.get(position) == iconSelected) {
                imageView.setBackgroundColor(Color.parseColor("#009900"));
            }
            
            return imageView;
        }
    }


}
