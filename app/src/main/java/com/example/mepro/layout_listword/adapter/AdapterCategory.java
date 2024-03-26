package com.example.mepro.layout_listword.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mepro.R;
import com.example.mepro.layout_listword.model.Category;
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
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.buttonItem);

            button.setOnClickListener(v -> {
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
        holder.button.setText(category.getNameCategory());
        holder.button.setSelected(selectedPosition == position);
        if (selectedPosition == position) {
            // Nếu đây là Button được chọn, thay đổi kích thước
            holder.button.setScaleX(1.05f);
            holder.button.setScaleY(1.1f);
        } else {
            // Nếu không, sử dụng kích thước mặc định
            holder.button.setScaleX(1.0f);
            holder.button.setScaleY(1.0f);
        }    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }
}
