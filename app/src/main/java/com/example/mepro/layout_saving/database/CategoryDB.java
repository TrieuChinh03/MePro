package com.example.mepro.layout_saving.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.mepro.layout_saving.model.Category;

import java.util.ArrayList;

public class CategoryDB extends SQLiteOpenHelper {
    private static final String nameDB = "CategoryDB1";
    private static final int version = 1;
    private SQLiteDatabase sql;
    public CategoryDB(Context context) {
        super(context, nameDB, null, version);
        this.sql = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS tblCategory(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, ICON INTEGER, TYPE INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(Category category) {
        String query = "INSERT INTO tblCategory(NAME, ICON, TYPE) VALUES (?, ?, ?)";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindString(1, category.getName());
        statement.bindLong(2, category.getIcon());
        statement.bindLong(3, category.getType());
        statement.executeInsert();
    }

    public void updateData(Category category) {
        String query = "UPDATE tblCategory SET NAME = ?, ICON = ?, TYPE = ? WHERE ID = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindString(1, category.getName());
        statement.bindLong(2, category.getIcon());
        statement.bindLong(3, category.getType());
        statement.bindLong(4, category.getId());
        statement.executeUpdateDelete();
    }
    
    public void deleteData(Category category) {
        String query = "DELETE FROM tblCategory WHERE ID = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindLong(1, category.getId());
        statement.executeUpdateDelete();
    }

    public Category getCategory(int id) {
        String[] ids = {String.valueOf(id)};
        Cursor c = sql.rawQuery("SELECT * FROM tblCategory WHERE ID = ?" ,ids);
        c.moveToFirst();
        Category category = new Category(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3));
        return category;
    }

    public ArrayList<Category> getData() {
        Cursor c = sql.rawQuery("SELECT * FROM tblCategory" ,null);
        ArrayList<Category> listCategory = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                int icon = c.getInt(2);
                int type = c.getInt(3);
                Category category = new Category(id, name, icon, type);
                listCategory.add(category);
            } while (c.moveToNext() && !c.isAfterLast());
            return listCategory;
        } else {
            return null;
        }
    }}

