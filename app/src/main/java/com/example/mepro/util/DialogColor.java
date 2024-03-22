package com.example.mepro.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.example.mepro.R;

import java.util.ArrayList;
import java.util.List;

public class DialogColor {

    private int click;
    private static int colorSelected;
    private Callback callback;
    private Context context;
    private List<Integer> listClick;
    
    public DialogColor(Context context) {
        this.context = context;
    }
    
    public interface Callback {
        void onColorSelected(int color);
    }
    
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
    
    //===   Được gọi khi 1 màu được chọn    ===
    private void colorSelected(int color) {
        if (callback != null) {
            callback.onColorSelected(color);
        }
    }
    
    //===   Show dialog chọn màu    ===
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_color, null);
        builder.setView(dialogView);
    
        final AlertDialog dialog = builder.create();
        listClick = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            View colorView = dialogView.findViewById(viewId(i));
            int colorId = colorId(i);
            colorView.setOnClickListener(v -> {
                int color = ContextCompat.getColor(context, colorId);
                colorSelected(color);
                dialog.dismiss();
            });
        }
        dialog.show();
    }
    
    //===   Lấy id view     ===
    private int viewId(int i) {
        int[] viewId = new int[20];
        viewId[0] = R.id.colorRed;
        viewId[1] = R.id.colorOrange;
        viewId[2] = R.id.colorYellow;
        viewId[3] = R.id.colorGreen;
        viewId[4] = R.id.colorBlue;
        viewId[5] = R.id.colorPurple;
        viewId[6] = R.id.colorPink;
        viewId[7] = R.id.colorCyan;
        viewId[8] = R.id.colorDarkOrange;
        viewId[9] = R.id.colorLightPink;
        viewId[10] = R.id.colorDarkGoldenrod;
        viewId[11] = R.id.colorLime;
        viewId[12] = R.id.colorDarkMagenta;
        viewId[13] = R.id.colorBrown;
        viewId[14] = R.id.colorDarkRed;
        viewId[15] = R.id.colorIndigo;
        viewId[16] = R.id.colorGray;
        viewId[17] = R.id.colorDarkViolet;
        viewId[18] = R.id.colorTeal;
        viewId[19] = R.id.colorBlack;
        return viewId[i];
    }
    
    //===   Lấy id màu  ===
    private int colorId(int i) {
        int[] colorId = new int[20];
        colorId[0] = R.color.colorRed;
        colorId[1] = R.color.colorOrange;
        colorId[2] = R.color.colorYellow;
        colorId[3] = R.color.colorGreen;
        colorId[4] = R.color.colorBlue;
        colorId[5] = R.color.colorPurple;
        colorId[6] = R.color.colorPink;
        colorId[7] = R.color.colorCyan;
        colorId[8] = R.color.colorDarkOrange;
        colorId[9] = R.color.colorLightPink;
        colorId[10] = R.color.colorDarkGoldenrod;
        colorId[11] = R.color.colorLime;
        colorId[12] = R.color.colorDarkMagenta;
        colorId[13] = R.color.colorBrown;
        colorId[14] = R.color.colorDarkRed;
        colorId[15] = R.color.colorIndigo;
        colorId[16] = R.color.colorGray;
        colorId[17] = R.color.colorDarkViolet;
        colorId[18] = R.color.colorTeal;
        colorId[19] = R.color.colorBlack;
        return colorId[i];
    }
    
}
