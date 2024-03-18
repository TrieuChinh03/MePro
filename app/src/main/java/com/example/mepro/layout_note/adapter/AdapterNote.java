package com.example.mepro.layout_note.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mepro.R;
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
        
        tvTilte.setText(note.getTilte());
        tvContent.setText(note.getContent());
        tvTime.setText(Convert.getDate(note.getTime()));
        
        return convertView;
    }
}
