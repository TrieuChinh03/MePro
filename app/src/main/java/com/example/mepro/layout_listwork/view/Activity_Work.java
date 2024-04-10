package com.example.mepro.layout_listwork.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mepro.R;
import com.example.mepro.layout_listwork.database.CategoryDB;
import com.example.mepro.layout_listwork.database.WorkDB;
import com.example.mepro.layout_listwork.model.Category;
import com.example.mepro.layout_listwork.model.Work;
import com.example.mepro.util.Convert;
import com.example.mepro.util.DialogCalendar;
import com.example.mepro.util.Dialog_Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Activity_Work extends AppCompatActivity {
    private ImageView imgBack;
    private Spinner spnCategory;
    private EditText edtTilteWork;
    private Button btComplete;
    private TextView tvStartTime, tvEndTime, tvRepeat, tvNoteWork;
    private LinearLayout layoutStartTime, layoutEndTime, layoutRepeat, layoutNoteWork;
    private Switch swtImportant;
    private Work work;
    private WorkDB workDB;
    private Category category;
    private List<Category> listCategory;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_work);
        mappingId();
        getDataCategory();
        getDataWork();
        event();
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(work.getId() != 0) {
            workDB = new WorkDB(this);
            workDB.updateData(work);
        }
        
    }
    
    //===   Ánh xạ  id  ===
    private void mappingId() {
        edtTilteWork = findViewById(R.id.edtTilteWork);
        btComplete = findViewById(R.id.btComplete);
        spnCategory = findViewById(R.id.spnCategory);
        imgBack = findViewById(R.id.imgBack);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        tvRepeat = findViewById(R.id.tvRepeat);
        tvNoteWork = findViewById(R.id.tvNoteWork);
        swtImportant = findViewById(R.id.swtImportant);
        layoutStartTime = findViewById(R.id.layoutStartTime);
        layoutEndTime = findViewById(R.id.layoutEndTime);
        layoutRepeat = findViewById(R.id.layoutRepeat);
        layoutNoteWork = findViewById(R.id.layoutNoteWork);
    }
    
    //===   Sự kiện     ===
    private void event() {
        //===   Sự kiện khi nhấn button hoàn thành  ===
        btComplete.setOnClickListener(view -> {
            workDB = new WorkDB(Activity_Work.this);
            if(work.getId() == 0) {
                List<String> list = new ArrayList<>();
                work = new Work();
                String tilleWork = edtTilteWork.getText().toString().trim();
                if(tilleWork.equals(""))
                    Dialog_Message.showDialogErrorMessage(this,R.drawable.sticker_error,"Lỗi rồi", "Bạn chưa nhập tên công việc");
                else {
                    work.setTitleWork(tilleWork);
                    work.setExtraWork(list);
                    work.setStartTime(tvStartTime.getText().toString());
                    work.setEndTime(tvEndTime.getText().toString());
                    work.setNoteWork(tvNoteWork.getText().toString());
                    work.setImportant(swtImportant.isChecked());
                    work.setCompleted(false);
                    work.setCategory(category.getId());
                    workDB.insertData(work);
                    finish();
                }
            }
            
            else if(work.isCompleted()) {
                work.setCompleted(false);
                btComplete.setText("Hoàn thành");
                workDB.updateData(work);
            }
            
            else {
                work.setCompleted(true);
                btComplete.setText("Hủy hoàn thành");
                workDB.updateData(work);
            }
        });
        
        //===   Sự kiện back    ===
        imgBack.setOnClickListener(view -> finish());
        
        //===   Sự kiện view start time   ===
        layoutStartTime.setOnClickListener(view -> {
            DialogCalendar.getTimeStr(this, time -> {
                if(!time.equals("Không có thời gian")) {
                    tvStartTime.setText(time);
                    work.setStartTime(time);
                }
            });
        });
    
        //===   Sự kiện view end time   ===
        layoutEndTime.setOnClickListener(view -> {
            DialogCalendar.getTimeStr(this, time -> {
                tvEndTime.setText(time);
                work.setEndTime(time);
            });
        });
        
        //===   Sự kiện view repeat    ===
        layoutRepeat.setOnClickListener(view -> {
        
        });
    
        //===   Sự kiện view note   ===
        layoutNoteWork.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(R.layout.dialog_work_note);
            AlertDialog dialog = builder.create();
            
            dialog.setOnShowListener(dialogInterface -> {
                EditText edtNoteWork = dialog.findViewById(R.id.edtNoteWork);
                if(work.getId() != 0)
                    edtNoteWork.setText(work.getNoteWork());
            });
            
            dialog.setOnDismissListener(dialogInterface -> {
                EditText edtNoteWork = dialog.findViewById(R.id.edtNoteWork);
                String note = edtNoteWork.getText().toString();
                if(!note.equals("")) {
                    work.setNoteWork(edtNoteWork.getText().toString());
                    tvNoteWork.setText(work.getNoteWork());
                }
            });
    
            dialog.show();
    
        });
    
        //===   Sự kiện view important    ===
        swtImportant.setOnClickListener(view -> {
            if(work == null)
                return;
            if(swtImportant.isChecked())
                work.setImportant(true);
            else
                work.setImportant(false);
        });
        
        //===   Sự kiện chọn item spinner danh mục  ===
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = listCategory.get(i);
                work.setCategory(category.getId());
            }
    
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
        
            }
        });

    }
    
    //===   Lấy data danh mục   ===
    private void getDataCategory() {
        CategoryDB categoryDB = new CategoryDB(this);
        listCategory = categoryDB.getData(-1);
        List<String> items = new ArrayList<>();
        for(int i=0; i<listCategory.size(); i++)
            items.add(listCategory.get(i).getNameCategory());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(adapter);
    }
    
    //===   Lấy data    ===
    private void getDataWork() {
        Intent intent = getIntent();
        work = (Work) intent.getSerializableExtra("work");
        if(work.getId() != 0) {
            for(int i = 0; i < listCategory.size(); i++) {
                if(listCategory.get(i).getId() == work.getCategory()) {
                    spnCategory.setSelection(i);
                    break;
                }
            }
            edtTilteWork.setText(work.getTitleWork());
            tvStartTime.setText(work.getStartTime());
            tvEndTime.setText(work.getEndTime());
            tvNoteWork.setText(work.getNoteWork());
            swtImportant.setChecked(work.isImportant());
    
            if(work.isCompleted())
                btComplete.setText("Hủy hoàn thành");
            
        } else {
            LocalDateTime time = LocalDateTime.now();
            tvStartTime.setText(Convert.convertTime(time.toString()));
            tvEndTime.setText("Không có thời gian");
            btComplete.setText("Tạo mới");
        }
    }
    
}
