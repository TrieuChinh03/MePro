package com.example.mepro.layout_saving.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.mepro.layout_saving.model.FluctuatingBalancesHistory;

import java.util.ArrayList;

public class FluctuatingBalancesHistoryDB extends SQLiteOpenHelper {
    private static final String nameDB = "FluctuatingBalancesHistoryDB";
    private static final int version = 1;
    private SQLiteDatabase sql;
    public FluctuatingBalancesHistoryDB(Context context) {
        super(context, nameDB, null, version);
        this.sql = this.getWritableDatabase();
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS tblHistory(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, TIME TEXT, FLUCTUATINGBALANCES REAL, IDCATEGORY INTEGER)";
        db.execSQL(query);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    
    }
    
    public void insertData(FluctuatingBalancesHistory history) {
        String query = "INSERT INTO tblHistory(NAME, TIME, FLUCTUATINGBALANCES, IDCATEGORY) VALUES (?, ?, ?, ?)";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindString(1, history.getName());
        statement.bindString(2, history.getTime());
        statement.bindDouble(3, history.getFluctuatingBalances());
        statement.bindLong(4, history.getIdCategory());
        statement.executeInsert();
        System.out.println("Đã thêm");
    }
    
    public void updateData(FluctuatingBalancesHistory history) {
        String query = "UPDATE tblHistory SET NAME = ?, TIME = ?, FLUCTUATINGBALANCES = ?, IDCATEGORY = ? WHERE ID = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindString(1, history.getName());
        statement.bindString(2, history.getTime());
        statement.bindDouble(3, history.getFluctuatingBalances());
        statement.bindLong(4, history.getIdCategory());
        statement.bindLong(5, history.getId());
        statement.executeUpdateDelete();
    }
    
    public void deleteData(FluctuatingBalancesHistory history) {
        String query = "DELETE FROM tblHistory WHERE ID = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindLong(1, history.getId());
        statement.executeUpdateDelete();
    }
    
    public void deleteData(int idCategory) {
        String query = "DELETE FROM tblHistory WHERE IDCATEGORY = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindLong(1, idCategory);
        statement.executeUpdateDelete();
    }
        
        
    public ArrayList<FluctuatingBalancesHistory> getData(int date,String timeString) {
        String[] selectionArgs = new String[] { timeString };
        Cursor c = null;
        switch (date) {
            case 1:
                c = sql.rawQuery("SELECT * FROM tblHistory " +
                        "WHERE strftime('%Y-%m-%d', TIME) = ?;",selectionArgs);
                break;
                
            case 2:
                c = sql.rawQuery("SELECT * FROM tblHistory " +
                        "WHERE strftime('%Y-%W', TIME) = ?;",selectionArgs);
                break;
                
            case 3:
                c = sql.rawQuery("SELECT * FROM tblHistory " +
                        "WHERE strftime('%Y-%m', TIME) = ?;",selectionArgs);
                break;
                
            case 4:
                c = sql.rawQuery("SELECT * FROM tblHistory " +
                        "WHERE strftime('%Y', TIME) = ?;",selectionArgs);
                break;
        }
        
            //    Cursor c = sql.rawQuery("SELECT * FROM tblHistory" ,null);
        ArrayList<FluctuatingBalancesHistory> listHistory = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                String time = c.getString(2);
                double fluctuatingBalances = c.getDouble(3);
                int idCategory = c.getInt(4);
                FluctuatingBalancesHistory history = new FluctuatingBalancesHistory(id, name, time, fluctuatingBalances, idCategory);
                listHistory.add(history);
            } while (c.moveToNext() && !c.isAfterLast());
            System.out.println("Có " + listHistory.size());
            return listHistory;
        } else {
            return null;
        }
    }}

