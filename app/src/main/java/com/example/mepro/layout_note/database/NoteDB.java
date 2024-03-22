package com.example.mepro.layout_note.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.example.mepro.layout_note.model.Note;
import java.util.ArrayList;

public class NoteDB extends SQLiteOpenHelper {
    private static final String nameDB = "NoteDB";
    private static final int version = 1;
    private SQLiteDatabase sql;
    public NoteDB(Context context) {
        super(context, nameDB, null, version);
        this.sql = this.getWritableDatabase();
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS tblNote(ID INTEGER PRIMARY KEY AUTOINCREMENT, TILTE TEXT, TIME TEXT, CONTENT TEXT)";
        db.execSQL(query);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    
    }
    
    public void insertData(Note note) {
        String query = "INSERT INTO tblNote(TILTE, TIME, CONTENT) VALUES (?, ?, ?)";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindString(1, note.getTilte());
        statement.bindString(2, note.getTime());
        statement.bindString(3, note.getContent());
        statement.executeInsert();
        sql.close();
    }
    
    public void updateData(Note note) {
        String query = "UPDATE tblNote SET TILTE = ?, TIME = ?, CONTENT = ? WHERE ID = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindString(1, note.getTilte());
        statement.bindString(2, note.getTime());
        statement.bindString(3, note.getContent());
        statement.bindLong(4, note.getId());
        statement.executeUpdateDelete();
        sql.close();
    }

    
    public void deleteData(Note note) {
        String query = "DELETE FROM tblNote WHERE ID = ?";
        SQLiteStatement statement = sql.compileStatement(query);
        statement.bindLong(1, note.getId());
        statement.executeUpdateDelete();
        sql.close();
    }
    
    public void deleteAll() {
        String query = "DELETE FROM tblNote";
        sql.execSQL(query);
        sql.close();
    }
    
    public ArrayList<Note> getListNote() {
        Cursor c = sql.rawQuery("SELECT * FROM tblNote" ,null);
        Note note;
        ArrayList<Note> listNote = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String tilte = c.getString(1);
                String timeStr = c.getString(2);
                String content = c.getString(3);
                
                note = new Note(id,tilte, content, timeStr);
                listNote.add(note);
            } while (c.moveToNext() && !c.isAfterLast());
            sql.close();
            return listNote;
        }
        sql.close();
        return null;
    }}
