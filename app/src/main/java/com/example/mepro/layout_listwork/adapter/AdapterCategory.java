package com.example.mepro.layout_listwork.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mepro.R;
import com.example.mepro.layout_listwork.model.Category;
import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    private List<Category> listCategory;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private OnItemClickListener mListener;

    public AdapterCategory(List<Category> listCategory) {
        this.listCategory = listCategory;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdapterCategory.OnItemClickListener listener) {
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            btCategory = itemView.findViewById(R.id.btCategory);
    
            btCategory.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    if (mListener != null) {
                        mListener.onItemClick(position);
                    }
                }
            });
            
            
        }
        
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_button_category, parent, false);
        return new ViewHolder(view);
    }

    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = listCategory.get(position);
        holder.btCategory.setText(category.getNameCategory());
        holder.btCategory.setSelected(selectedPosition == position);
        
        //===   Thay đổi kích thước khi được chọn   ===
        if (selectedPosition == position) {
            holder.btCategory.setScaleX(1.15f);
            holder.btCategory.setScaleY(1.15f);
        } else {
            holder.btCategory.setScaleX(1.0f);
            holder.btCategory.setScaleY(1.0f);
        }
    }
    
    //===   Lấy số lượng item   ===
    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    //===   Set vị trí được chọn và thông báo thay đổi   ===
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }
    
    //===   Lấy vị trí được chọn    ===
    public int getSelectedPosition() {
        return  selectedPosition;
    }
}
