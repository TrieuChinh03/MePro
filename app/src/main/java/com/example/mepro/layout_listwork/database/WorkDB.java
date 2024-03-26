package com.example.mepro.layout_listwork.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.example.mepro.layout_listwork.model.Category;
import com.example.mepro.layout_listwork.model.Work;
import com.example.mepro.util.Convert;

import java.util.ArrayList;

public class WorkDB extends SQLiteOpenHelper {
    private static final String nameDB = "Work1";
    private static final int version = 1;
    private SQLiteDatabase sql;
    public WorkDB(Context context) {
        super(context, nameDB, null, version);
        this.sql = this.getWritableDatabase();
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS tblWorkDB(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TITLEWORK TEXT, EXTRAWORK TEXT, NOTEWORK TEXT, STARTTIME TEXT, ENDTIME TEXT, IMPROTANT TEXT, COMPLETED TEXT, CATEGORY INTERGER)";
        db.execSQL(query);
        System.out.println("Đã tạo bảng tblWorkDB");
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    
    }
    
    public void insertData(Work work) {
        String query = "INSERT INTO tblWorkDB(TITLEWORK, EXTRAWORK, NOTEWORK, STARTTIME, ENDTIME, IMPROTANT, COMPLETED, CATEGORY) VALUES (?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindString(1, work.getTitleWork());
        statement.bindString(2, work.getExtraWork().toString());
        statement.bindString(3, work.getNoteWork());
        statement.bindString(4, work.getStartTime());
        statement.bindString(5, work.getEndTime());
        statement.bindString(6, String.valueOf(work.isImportant()));
        statement.bindString(7, String.valueOf(work.isCompleted()));
        statement.bindLong(8, work.getCategory());
        statement.executeInsert();
        sql.close();
    }
    
    public void updateData(Work work) {
        String query = "UPDATE tblWorkDB SET TITLEWORK = ?, EXTRAWORK = ?, NOTEWORK = ?, STARTTIME = ?, ENDTIME = ?, IMPROTANT = ?, COMPLETED = ?, CATEGORY = ? WHERE ID = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindString(1, work.getTitleWork());
        statement.bindString(2, work.getExtraWork().toString());
        statement.bindString(3, work.getNoteWork());
        statement.bindString(4, work.getStartTime());
        statement.bindString(5, work.getEndTime());
        statement.bindString(6, String.valueOf(work.isImportant()));
        statement.bindString(7, String.valueOf(work.isCompleted()));
        statement.bindLong(8, work.getCategory());
        statement.bindLong(9, work.getId());
        statement.executeUpdateDelete();
        sql.close();
    }
    
    
    public void deleteData(Work work) {
        String query = "DELETE FROM tblWorkDB WHERE ID = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindLong(1, work.getId());
        statement.executeUpdateDelete();
        sql.close();
    }
    
    public void deleteData(Category category) {
        String query = "DELETE FROM tblWorkDB WHERE CATEGORY = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindLong(1, category.getId());
        statement.executeUpdateDelete();
        sql.close();
    }
    
    public void deleteAll() {
        String query = "DELETE FROM tblWorkDB";
        sql.execSQL(query);
        sql.close();
    }
    
    
    public ArrayList<Work> getListWork(int idCategory) {
        Cursor c;
        if(idCategory == 0)
            c = sql.rawQuery("SELECT * FROM tblWorkDB" ,null);
        else {
            String[] selectionArgs = {String.valueOf(idCategory)};
            c = sql.rawQuery("SELECT * FROM tblWorkDB WHERE CATEGORY = ?"  ,selectionArgs);
        }
        Work work;
        ArrayList<Work> listWork = new ArrayList<>();
        if(c.moveToLast()) {
            do {
                int id = c.getInt(0);
                String titleWork = c.getString(1);
                String extraWork = c.getString(2);
                String noteWork = c.getString(3);
                String startTime = c.getString(4);
                String endTime = c.getString(5);
                String important = c.getString(6);
                String completed = c.getString(7);
                int category = c.getInt(8);
    
                work = new Work();
                work.setId(id);
                work.setTitleWork(titleWork);
                work.setExtraWork(Convert.stringToList(extraWork));
                work.setNoteWork(noteWork);
                work.setStartTime(startTime);
                work.setEndTime(endTime);
                work.setImportant(Convert.stringToBoolean(important));
                work.setCompleted(Convert.stringToBoolean(completed));
                work.setCategory(category);
                listWork.add(work);
            } while (c.moveToPrevious() && !c.isBeforeFirst());
            sql.close();
            return listWork;
        }
        sql.close();
        return null;
    }}

