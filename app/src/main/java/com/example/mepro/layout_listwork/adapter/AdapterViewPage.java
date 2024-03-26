package com.example.mepro.layout_listwork.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class AdapterViewPage extends FragmentStateAdapter {
    private List<Fragment> listFragment;
    
    public AdapterViewPage(FragmentActivity fragmentActivity, List<Fragment> listFragment) {
        super(fragmentActivity);
        this.listFragment = listFragment;
    }
    
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return listFragment.get(position);
    }
    
    @Override
    public int getItemCount() {
        return listFragment.size();
    }
}
