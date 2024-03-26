package com.example.mepro.util;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class Dialog_ErrorMessage {
    public static void showDialogErrorMessage(Context context,int icon, String title, String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(errorMessage);
        builder.setIcon(icon);
        builder.show();
    }
}
