package com.example.mepro.layout_saving.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.mepro.R;
import com.example.mepro.layout_saving.model.Category;

import java.util.List;

public class AdapterCategory extends BaseAdapter {
    private Context mContext;
    private List<Category> categories;
    private int selected = -1;
    
    public AdapterCategory(Context context, List<Category> categories1) {
        mContext = context;
        categories = categories1;
    }
    
    
    @Override
    public int getCount() {
        return categories.size();
    }
    
    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    public void setSelection(int position) {selected = position;}
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imgIcon;
        TextView tvName;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.item_saving_category, parent, false);
        imgIcon = convertView.findViewById(R.id.imgIcon);
        tvName = convertView.findViewById(R.id.tvName);
        
        Category category = categories.get(position);
        imgIcon.setImageResource(category.getIcon());
        tvName.setText(category.getName());
        
        if(position == selected) {
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorLightPink));
        }
        
        return convertView;
    }
    
}
