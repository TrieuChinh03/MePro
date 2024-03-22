package com.example.mepro.layout_note.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mepro.R;
import com.example.mepro.layout_note.database.NoteDB;
import com.example.mepro.layout_note.model.Note;
import com.example.mepro.ultil.Convert;

import java.time.LocalDateTime;

public class LayoutNoteBook extends AppCompatActivity {
    private EditText btBack;
    private TextView tvTime;
    private ScrollView scvContent;
    private EditText edtContent;
    private ImageView imgDelete, imgNew, imgBold, imgItalic, imgUnderline;
    private Note note;
    private NoteDB db;
    private boolean click = true;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_note_book);
        db = new NoteDB(this);
        mappingId();
        getNote();
        event();
    }
    
    @Override
    public void onBackPressed() {
        saveData();
        db.close();
        super.onBackPressed();
    }
    
    //===   Ánh xạ view     ===
    private void mappingId() {
        btBack = findViewById(R.id.btBack);
        tvTime = findViewById(R.id.tvTime);
        scvContent = findViewById(R.id.scvContent);
        edtContent = findViewById(R.id.edtContent);
        imgDelete = findViewById(R.id.imgDelete);
        imgNew = findViewById(R.id.imgNew);
        imgBold = findViewById(R.id.imgBold);
        imgItalic = findViewById(R.id.imgItalic);
        imgUnderline = findViewById(R.id.imgUnderline);
    }
    
    //===   Sự kiện     ===
    private void event() {
        
        //===   Sự kiện back    ===
        btBack.setOnClickListener(view ->{
            if(click) {
                saveData();
                db.close();
                finish();
            }
        });

        //===   Sự kiện giữ tilte   ===
        btBack.setOnLongClickListener(view ->{
            btBack.setFocusableInTouchMode(true);
            btBack.requestFocus();
            showKeyBoard(btBack);
            return click = false;
        });

        //===   Sự kiện khi trạng thái tilte thay đổi   ===
        btBack.setOnFocusChangeListener((view, b) -> {
            if (b) {
                btBack.setSelection(btBack.getText().length());
            } else {
                if(btBack.getText().toString().equals(""))
                    btBack.setHint("Không có tiêu đề");
                btBack.setFocusableInTouchMode(false);
                click = true;
            }
        });

        //===   Sự kiện khi vuốt nội dung   ===
        edtContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                edtContent.clearFocus();
                return false;
            }
        });

        //===   Sự kiện delete  ===
        imgDelete.setOnClickListener(view ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xác nhận");
            builder.setIcon(R.drawable.ic_hoicham);
            builder.setMessage("Bạn có chắc chắn muốn xóa không ?");
            builder.setPositiveButton("OK", (dialog, which) -> {
                if(note!=null)
                    db.deleteData(note);
                finish();
            });
            builder.setNegativeButton("Hủy", (dialog, which) -> {
                dialog.dismiss();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        
        //===   Sự kiện tạo mới     ===
        imgNew.setOnClickListener(view ->{
            if(edtContent.getText().toString().equals("") && note == null)
                return;
            saveData();
            note = null;
            db = new NoteDB(this);
            btBack.setHint("Không có tiêu đề");
            edtContent.setText("" );
            edtContent.setHint("Nhập ghi chú");
            tvTime.setText(currentTime());
    
        });
    }
    
    //===   Lấy data    ===
    private void getNote() {
        Intent intent = getIntent();
        note = (Note) intent.getSerializableExtra("note");
        if(note != null) {
            String tilte = note.getTilte();
            String content = note.getContent();
            if(tilte.equals(""))
                btBack.setHint("Không có tiêu đề");
            else
                btBack.setText(note.getTilte());
            if(content.equals(""))
                edtContent.setHint("Nhập ghi chú");
            else
                edtContent.setText(note.getContent());
            tvTime.setText(note.getTime());
        } else {
            btBack.setHint("Không có tiêu đề");
            edtContent.setHint("Nhập ghi chú");
            tvTime.setText(currentTime());
        }
    }
    
    //===   Lưu data    ===
    private void saveData() {
        if(note != null && note.getId() != null) {
            tvTime.setText(currentTime());
            note.setTilte(btBack.getText().toString());
            note.setContent(edtContent.getText().toString());
            note.setTime(tvTime.getText().toString());
            db.updateData(note);
            
        } else if(!edtContent.getText().toString().equals("")){
            tvTime.setText(currentTime());
            Note newNote = new Note(btBack.getText().toString(), edtContent.getText().toString(), tvTime.getText().toString());
            db.insertData(newNote);
        }
    }
    
    //===   Lấy thời gian hiện tại  ===
    private String currentTime() {
        LocalDateTime ldTime = LocalDateTime.now();
        return Convert.convertTimeFormat(ldTime.toString());
    }

    private void showKeyBoard(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }
}