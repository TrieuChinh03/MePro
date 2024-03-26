package com.example.mepro.layout_note.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.example.mepro.R;
import com.example.mepro.layout_note.database.NoteDB;
import com.example.mepro.layout_note.adapter.AdapterNote;
import com.example.mepro.layout_note.model.Note;
import com.example.mepro.util.Convert;
import com.example.mepro.util.DateComparator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Activity_ListNote extends AppCompatActivity {
    private ListView lvListNote;
    private FloatingActionButton btAdd;
    private EditText edtSearch;
    private LinearLayout noteNull;
    private ArrayList<Note> listNote;
    private ArrayList<Note> searchNote;
    private static final int REQUEST_CODE = 15;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_note_list);
        mappingId();
        getData();
        event();
        
    }
    
    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }
    
    //===   Ánh xạ view ===
    private void mappingId() {
        lvListNote = findViewById(R.id.lvListNote);
        btAdd = findViewById(R.id.btAdd);
        edtSearch = findViewById(R.id.edtSearch);
        noteNull = findViewById(R.id.noteNull);
    }
    
    //===   Lấy dữ liệu ===
    private void getData() {
        NoteDB db = new NoteDB(this);
        listNote = db.getListNote();
        String search = edtSearch.getText().toString();
        if(listNote == null) {
            listNote = new ArrayList<>();
            noteNull.setVisibility(View.VISIBLE);
        } else {
            noteNull.setVisibility(View.GONE);
        }
        searchNote = new ArrayList<>();
        if(search.equals("")) {
            searchNote = listNote;
        } else {
            for(Note node : listNote) {
                if(node.getTilte().toLowerCase().contains(search.toLowerCase())) {
                    searchNote.add(node);
                }
            }
        }
        sortData();
        AdapterNote adapterNote = new AdapterNote(Activity_ListNote.this, R.layout.item_note, searchNote);
        lvListNote.setAdapter(adapterNote);
    }
    
    //===   Sự kiện ===
    private void event() {
        
        //===   Sự kiện nhấn vào item   ===
        lvListNote.setOnItemClickListener((adapterView, view, i, l) -> {
            Note note = searchNote.get(i);
            Intent intent = new Intent(Activity_ListNote.this, Activity_Note.class);
            intent.putExtra("note", note);
            startActivity(intent);
        });
        
        //===   Sự kiện nhấn button add     ===
        btAdd.setOnClickListener(view ->{
            Note note = null;
            Intent intent = new Intent(this, Activity_Note.class);
            intent.putExtra("note", note);
            startActivity(intent);
        });
        
        //===   Sự kiện tìm kiếm    ===
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        
            }
    
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getData();
            }
    
            @Override
            public void afterTextChanged(Editable editable) {
            
            }
        });
        
    }
    
    //===   Sắp xếp dữ liệu     ===
    private void sortData() {
        if(listNote == null || listNote.size() == 0)
            return;
        List<Date> dates = new ArrayList<>();
        ArrayList<Note> listNote1 = new ArrayList<>();
        for(int i=0 ;i<searchNote.size(); i++) {
            dates.add(Convert.getTime(searchNote.get(i).getTime()));
        }
        Collections.sort(dates, new DateComparator(false));
        for(int i=0 ;i<dates.size(); i++) {
            for(int j=0; j<searchNote.size(); j++) {
                if(Convert.getTime(searchNote.get(j).getTime()).equals(dates.get(i))) {
                    listNote1.add(searchNote.get(j));
                    searchNote.remove(j);
                }
            }
        }
        searchNote = listNote1;
    }
    
}