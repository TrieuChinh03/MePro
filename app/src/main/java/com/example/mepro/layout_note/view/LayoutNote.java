package com.example.mepro.layout_note.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.example.mepro.R;
import com.example.mepro.layout_note.database.NoteDB;
import com.example.mepro.layout_note.adapter.AdapterNote;
import com.example.mepro.layout_note.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LayoutNote extends AppCompatActivity {
    private ListView lvListNote;
    private FloatingActionButton btAdd;
    private EditText edtSearch;
    private ArrayList<Note> listNote;
    private ArrayList<Note> searchNote;
    private static final int REQUEST_CODE = 15;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_note);
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
    }
    
    //===   Lấy dữ liệu ===
    private void getData() {
        NoteDB db = new NoteDB(this);
        listNote = listNote = db.getListNote();
        String search = edtSearch.getText().toString();
        if(listNote == null) {
            listNote = new ArrayList<>();
            Note note = new Note("","","");
            listNote.add(note);
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
        AdapterNote adapterNote = new AdapterNote(LayoutNote.this, R.layout.item_note, searchNote);
        lvListNote.setAdapter(adapterNote);
        
    }
    
    //===   Sự kiện ===
    private void event() {
        
        //===   Sự kiện nhấn vào item   ===
        lvListNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = searchNote.get(i);
                Intent intent = new Intent(LayoutNote.this, LayoutNoteBook.class);
                intent.putExtra("note", note);
                startActivity(intent);
            }
        });
        
        //===   Sự kiện nhấn button add     ===
        btAdd.setOnClickListener(view ->{
            Note note = null;
            Intent intent = new Intent(this, LayoutNoteBook.class);
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
    
}