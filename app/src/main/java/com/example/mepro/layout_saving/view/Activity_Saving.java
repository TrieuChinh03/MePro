package com.example.mepro.layout_saving.view;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mepro.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity_Saving extends AppCompatActivity {
    private ImageButton imgbtBack;
    private BottomNavigationView bottom_navigation;
    private final int[] menuBottomNavigation = {R.id.menu_saving_add, R.id.menu_saving_report, R.id.menu_saving};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_saving);

        mappingId();
        event();
        setFragment(new Fragment_Saving_Add());
        setBottomNavigation();
    }

    //===   Ánh xạ view     ===
    private void mappingId() {
        imgbtBack = findViewById(R.id.imgbtBack);
        bottom_navigation = findViewById(R.id.bottom_navigation);
    }

    //===   Sự kiện     ===
    private void event() {
        imgbtBack.setOnClickListener(view ->{
            finish();
        });
    }

    //===   set fragment   ===
    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, fragment).commit();
    }

    //===   Set bottom  ===
    private void setBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            item.setChecked(true);

            if(id == menuBottomNavigation[0])
                setFragment(new Fragment_Saving_Add());

            else if(id == menuBottomNavigation[1])
                setFragment(new Fragment_Saving_Report());

            else
                setFragment(new Fragment_Saving());

            return false;
        });
    }
}
