package com.example.mepro.layout_saving.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mepro.R;
import com.example.mepro.layout_saving.database.BalancesDB;
import com.example.mepro.layout_saving.model.Balance;

import java.text.DecimalFormat;

public class Fragment_Saving extends Fragment {
    private View viewSaving;
    private TextView tvMainBalances, tvSavingsBalances;
    private LinearLayout layoutMainBalances, layoutSavingsBalances;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saving, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewSaving = view;
        mappingId();
        init();
    }

    //===   Ánh xạ view     ===
    private void mappingId() {
        tvMainBalances = viewSaving.findViewById(R.id.tvMainBalances);
        tvSavingsBalances = viewSaving.findViewById(R.id.tvSavingsBalances);
        layoutMainBalances = viewSaving.findViewById(R.id.layoutMainBalances);
        layoutSavingsBalances = viewSaving.findViewById(R.id.layoutSavingsBalances);
    }

    //===   Sự kiện     ===
    private void event() {

    }

    //===   Khởi tạo dữ liệu    ===
    private void init() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        BalancesDB db = new BalancesDB(viewSaving.getContext());
        Balance balance = db.getBalances();
        balance.setMainBalance(balance.getMainBalance() + 3460);
        balance.setSavingsBalance(balance.getSavingsBalance() + 4590);
        db.updateData(balance);
        Balance balance1 = db.getBalances();
        tvMainBalances.setText(decimalFormat.format(balance1.getMainBalance()) + " đ");
        tvSavingsBalances.setText(decimalFormat.format(balance1.getSavingsBalance()) + " đ");
        db.close();
    }
}
