package com.example.mepro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mepro.ultil.Convert;

import java.time.LocalDateTime;

public class TestView extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
    
        LocalDateTime l = LocalDateTime.now();
        String a = Convert.convertTimeFormat(l.toString());
        Toast.makeText(this, l.toString()+"\n"+a, Toast.LENGTH_SHORT).show();
    }
}