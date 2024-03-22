package com.example.mepro.layout_listword.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.example.mepro.layout_listword.model.Category;
import java.util.ArrayList;

public class CategoryDB extends SQLiteOpenHelper {
    private static final String nameDB = "Work";
    private static final int version = 1;
    private SQLiteDatabase sql;
    public CategoryDB(Context context) {
        super(context, nameDB, null, version);
        this.sql = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS tblCategoryDB(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMECATEGORY TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(Category category) {
        String query = "INSERT INTO tblCategoryDB(NAMECATEGORY) VALUES (?)";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindString(1, category.getNameCategory());
        statement.executeInsert();
        sql.close();
    }

    public void updateData(Category category) {
        String query = "UPDATE tblCategoryDB SET NAMECATEGORY = ? WHERE ID = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindString(1, category.getNameCategory());
        statement.bindLong(4, category.getId());
        statement.executeUpdateDelete();
        sql.close();
    }


    public void deleteData(Category category) {
        String query = "DELETE FROM tblCategoryDB WHERE ID = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindLong(1, category.getId());
        statement.executeUpdateDelete();
        sql.close();
    }

    public void deleteAll() {
        String query = "DELETE FROM tblCategoryDB";
        sql.execSQL(query);
        sql.close();
    }

    public ArrayList<Category> getListCategory() {
        Cursor c = sql.rawQuery("SELECT * FROM tblCategoryDB" ,null);
        Category category;
        ArrayList<Category> listCategory = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String nameCategory = c.getString(1);

                category = new Category(id,nameCategory);
                listCategory.add(category);
            } while (c.moveToNext() && !c.isAfterLast());
            sql.close();
            return listCategory;
        }
        sql.close();
        return null;
    }}

