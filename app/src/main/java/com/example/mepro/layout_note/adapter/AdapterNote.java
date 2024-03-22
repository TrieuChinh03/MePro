package com.example.mepro.layout_note.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.mepro.R;
import com.example.mepro.layout_note.database.NoteDB;
import com.example.mepro.layout_note.model.Note;
import com.example.mepro.ultil.Convert;

import java.util.ArrayList;

public class AdapterNote extends ArrayAdapter<Note> {
     Activity context;
     int idLayout;
     ArrayList<Note> noteList;
    
    
    public AdapterNote(Activity context, int idLayout, ArrayList<Note> noteList) {
        super(context, idLayout, noteList);
        this.context = context;
        this.idLayout = idLayout;
        this.noteList = noteList;
    }
    
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = context.getLayoutInflater();
        convertView = myInflater.inflate(idLayout, null);
        
        Note note = noteList.get(position);
        TextView tvTilte = convertView.findViewById(R.id.tvTilte);
        TextView tvContent = convertView.findViewById(R.id.tvContent);
        TextView tvTime = convertView.findViewById(R.id.tvTime);
        ImageView imgDelete = convertView.findViewById(R.id.imgDelete);

        String tilte = note.getTilte();
        String content = note.getContent();
        if(tilte.equals(""))
            tvTilte.setText("Không có tiêu đề");
        else
            tvTilte.setText(note.getTilte());
        if(content.equals(""))
            tvContent.setHint("Chưa có nội dung");
        else
            tvContent.setText(note.getContent());
        tvTime.setText(Convert.getDate(note.getTime()));
        imgDelete.setOnClickListener(view ->{
            if(note!=null && note.getId()!=null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận");
                builder.setIcon(R.drawable.ic_hoicham);
                builder.setMessage("Bạn có chắc chắn muốn xóa không ?");
                builder.setPositiveButton("OK", (dialog, which) -> {
                    NoteDB noteDB = new NoteDB(context);
                    noteDB.deleteData(note);
                    noteList.remove(note);
                    notifyDataSetChanged();
                    noteDB.close();
                });
                builder.setNegativeButton("Hủy", (dialog, which) -> {
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        
        return convertView;
    }
}
