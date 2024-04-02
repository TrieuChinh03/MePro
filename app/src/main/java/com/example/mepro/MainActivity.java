package com.example.mepro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mepro.layout_listwork.view.Activity_ListWork;
import com.example.mepro.layout_note.view.Activity_ListNote;
import com.example.mepro.layout_saving.view.Activity_Saving;
import com.example.mepro.photo.AdapterPhoto;
import com.example.mepro.photo.Photo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {
    private TextView tvHello;
    private ImageView imgHello, imgNote, imgSetting, imgWorkList, imgSaving;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappingId();
        event();
        createSlideImage();
        setTextViewHello();
    }
    
    private void mappingId() {
        tvHello = findViewById(R.id.tvHello);
        imgHello = findViewById(R.id.imgHello);
        imgNote = findViewById(R.id.imgNote);
        imgSetting = findViewById(R.id.imgSetting);
        imgWorkList = findViewById(R.id.imgWorkList);
        imgSaving = findViewById(R.id.imgSaving);
    }
    
    private void event() {
        //===   Sự kiện mở activity note    ===
        imgNote.setOnClickListener(view ->{
            Intent intent = new Intent(this, Activity_ListNote.class);
            startActivity(intent);
        });
        
        //===   Sự kiện mở công việc ===
        imgWorkList.setOnClickListener(view ->{
            Intent intent = new Intent(this, Activity_ListWork.class);
            startActivity(intent);
        });

        //===   Sự kiện mở sổ tiết kiệm     ===
        imgSaving.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity_Saving.class);
            startActivity(intent);
        });
    
        imgSetting.setOnClickListener(view ->{
            Intent intent = new Intent(this, TestView.class);
            startActivity(intent);
        });
    }
    
    //===   Khởi tạo photo cho slide image ===
    private void createSlideImage() {
        ViewPager2 vp2 = findViewById(R.id.vp2);
        CircleIndicator3 ci3 = findViewById(R.id.ci3);
        
        //===   Lấy danh sách photo ===
        List<Photo> listPhoto = new ArrayList<>();
        listPhoto.add(new Photo(R.drawable.photo_1));
        listPhoto.add(new Photo(R.drawable.photo_2));
        listPhoto.add(new Photo(R.drawable.photo_3));
    
        AdapterPhoto adapterPhoto = new AdapterPhoto(listPhoto);
        vp2.setAdapter(adapterPhoto);
        ci3.setViewPager(vp2);
    
        //===   Tự chạy slide   ===
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(vp2.getCurrentItem() == listPhoto.size() - 1)
                    vp2.setCurrentItem(0);
                else
                    vp2.setCurrentItem(vp2.getCurrentItem() + 1);
                
            }
        };
        
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
    }
    
    //===   Set lời chào   ===
    private void setTextViewHello() {
        int hour = LocalDateTime.now().getHour();
        if(hour >= 6 && hour <= 11) {
            tvHello.setText("Xin chào buổi sáng");
            imgHello.setImageResource(R.drawable.ic_morning);
        } else if(hour > 11 && hour < 13) {
            tvHello.setText("Xin chào buổi trưa");
            imgHello.setImageResource(R.drawable.ic_morning);
        } else if(hour >= 13 && hour < 19) {
            tvHello.setText("Xin chào buổi chiều");
            imgHello.setImageResource(R.drawable.ic_afternoon);
        } else if(hour >= 19 || hour < 6) {
            tvHello.setText("Xin chào buổi tối");
            imgHello.setImageResource(R.drawable.ic_night);
        }
    }
}