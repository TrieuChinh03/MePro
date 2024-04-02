package com.example.mepro.layout_saving.adapter;

import android.app.Activity;
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

import java.util.ArrayList;

public class AdapterFluctuatingBalances extends ArrayAdapter<FluctuatingBalancesHistory> {
    private Activity context;
    private int idLayout;
    private ArrayList<FluctuatingBalancesHistory> listFluctuatingBalances;
    private CategoryDB categoryDB;
    private ArrayList<Category> listCategory = new ArrayList<>();

    public AdapterFluctuatingBalances(Activity context, int idLayout, ArrayList<FluctuatingBalancesHistory> list) {
        super(context, idLayout, list);
        this.context = context;
        this.idLayout = idLayout;
        this.listFluctuatingBalances = list;
        categoryDB = new CategoryDB(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);

        FluctuatingBalancesHistory history = listFluctuatingBalances.get(position);

        ImageView imgIconCategory = convertView.findViewById(R.id.imgIconCategory);
        TextView tvNameCategory = convertView.findViewById(R.id.tvNameCategory);
        TextView tvPercent = convertView.findViewById(R.id.tvPercent);
        TextView tvFluctuatingBalance = convertView.findViewById(R.id.tvFluctuatingBalance);

        Category category = categoryDB.getCategory(listFluctuatingBalances.get(position).getIdCategory());

        return convertView;
    }
}
