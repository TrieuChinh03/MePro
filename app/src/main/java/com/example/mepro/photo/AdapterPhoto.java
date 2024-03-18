package com.example.mepro.photo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mepro.R;

import java.util.List;

public class AdapterPhoto extends RecyclerView.Adapter<AdapterPhoto.PhotoViewHolder> {
    
    private List<Photo> listPhoto;
    
    public AdapterPhoto(List<Photo> listPhoto) {
        this.listPhoto = listPhoto;
    }
    
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = listPhoto.get(position);
        if(photo == null)
            return;
        holder.imgPhoto.setImageResource(photo.getResourceId());
    }
    
    @Override
    public int getItemCount() {
        if(listPhoto != null)
            return listPhoto.size();
        return 0;
    }
    
    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        
        private ImageView imgPhoto;
    
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imgItem);
        }
    }
    
}
