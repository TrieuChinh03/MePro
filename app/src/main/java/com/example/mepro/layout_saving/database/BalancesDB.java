package com.example.mepro.layout_saving.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.mepro.layout_saving.model.Balance;

public class BalancesDB extends SQLiteOpenHelper {
    private static final String nameDB = "BalancesDB";
    private static final int version = 1;
    private SQLiteDatabase sql;
    public BalancesDB(Context context) {
        super(context, nameDB, null, version);
        this.sql = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS tblBanlances(MAINBLANCES REAL, SAVINGSBALANCES REAL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(Balance balance) {
        String query = "INSERT INTO tblBanlances(MAINBLANCES, SAVINGSBALANCES) VALUES (?, ?)";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindDouble(1, balance.getMainBalance());
        statement.bindDouble(2, balance.getSavingsBalance());
        statement.executeInsert();
    }

    public void updateData(Balance balance) {
        String query = "UPDATE tblBanlances SET MAINBLANCES = ?, SAVINGSBALANCES = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindDouble(1, balance.getMainBalance());
        statement.bindDouble(2, balance.getSavingsBalance());
        statement.executeUpdateDelete();
    }

    public Balance getBalances() {
        Cursor c = sql.rawQuery("SELECT * FROM tblBanlances" ,null);
        if(c.moveToFirst()) {
            Balance balance = new Balance(c.getDouble(0), c.getDouble(1));
            return balance;
        }
        return null;
    }}
