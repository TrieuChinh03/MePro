package com.example.mepro.layout_saving.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mepro.R;
import com.example.mepro.layout_saving.database.CategoryDB;
import com.example.mepro.layout_saving.model.Category;
import com.example.mepro.layout_saving.model.FluctuatingBalancesHistory;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterFluctuatingBalances extends ArrayAdapter<FluctuatingBalancesHistory> {
    private Context context;
    private int idLayout;
    private ArrayList<FluctuatingBalancesHistory> histories;
    private CategoryDB categoryDB;
    private ArrayList<Category> listCategory = new ArrayList<>();
    private double sum = 0;

    public AdapterFluctuatingBalances(Context context, int idLayout, ArrayList<FluctuatingBalancesHistory> list) {
        super(context, idLayout, list);
        this.context = context;
        this.idLayout = idLayout;
        this.histories = list;
        categoryDB = new CategoryDB(context);
        setSum();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(idLayout,parent, false);

        FluctuatingBalancesHistory history = histories.get(position);
        double percent = (history.getFluctuatingBalances()/sum)*100;

        ImageView imgIconCategory = convertView.findViewById(R.id.imgIconCategory);
        TextView tvNameCategory = convertView.findViewById(R.id.tvNameCategory);
        TextView tvPercent = convertView.findViewById(R.id.tvPercent);
        TextView tvFluctuatingBalance = convertView.findViewById(R.id.tvFluctuatingBalance);
        
        Category category = categoryDB.getCategory(histories.get(position).getIdCategory());
        imgIconCategory.setImageResource(category.getIcon());
        
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        tvPercent.setText(decimalFormat.format(percent) + "%");
        tvNameCategory.setText(category.getName());
        
        DecimalFormat decimalFormat1 = new DecimalFormat("#,###");
        if(category.getType() == 1) {
            tvFluctuatingBalance.setText(" - " + decimalFormat1.format(history.getFluctuatingBalances()) + " đ");
            tvFluctuatingBalance.setTextColor(Color.RED);
        }
        else {
            tvFluctuatingBalance.setText(" + " + decimalFormat1.format(history.getFluctuatingBalances()) + " đ");
            tvFluctuatingBalance.setTextColor(Color.GREEN);
        }
    
        return convertView;
    }
    
    private void setSum() {
        int length = histories.size();
        for(int i = 0; i < length; i++) {
            sum += histories.get(i).getFluctuatingBalances();
        }
    }
}
