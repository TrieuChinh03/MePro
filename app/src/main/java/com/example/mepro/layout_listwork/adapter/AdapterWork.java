package com.example.mepro.layout_listwork.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mepro.R;
import com.example.mepro.layout_listwork.database.CategoryDB;
import com.example.mepro.layout_listwork.model.Category;
import com.example.mepro.layout_listwork.model.Work;

import java.util.List;

public class AdapterWork extends RecyclerView.Adapter<AdapterWork.WorkViewHolder> {
    private List<Work> listWork;
    private Context context;
    private OnClickListenerCheckBox onClickListenerCheckBox;
    private OnItemClickListener mListener;
    public AdapterWork(Context context1) {
        context = context1;
    }
    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work, parent, false);
        return new WorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position) {
        Work work = listWork.get(position);
        if(work == null) return;
        holder.cbCompleted.setChecked(work.isCompleted());
        holder.cbCompleted.setText(work.getTitleWork());
        holder.tvNoteWork.setText(work.getNoteWork());
        
        //===   Set cờ     ===
        CategoryDB categoryDB = new CategoryDB(context);
        List<Category> categorys = categoryDB.getListCategory(work.getCategory());
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_flag);
        PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(categorys.get(0).getColor(), PorterDuff.Mode.SRC_IN);
        drawable.setColorFilter(colorFilter);
        holder.imgFlag.setImageDrawable(drawable);
        
        //===   Set quan trọng  ===
        if(work.isImportant())
            holder.imgImportant.setVisibility(View.VISIBLE);
        
        //===   Sự kiện     ===
        holder.cbCompleted.setOnClickListener(view ->{
            onClickListenerCheckBox.onClickCheckBox(work, position);
        });
    
        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listWork != null)
            return listWork.size();
        return 0;
    }

    public void setData(List<Work> listWork, OnClickListenerCheckBox listenerCheckBox) {
        this.listWork = listWork;
        this.onClickListenerCheckBox = listenerCheckBox;
        notifyDataSetChanged();
    }

    public interface OnClickListenerCheckBox {
        void onClickCheckBox(Work work, int position);
    }
    
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public class WorkViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbCompleted;
        private TextView tvNoteWork;
        private ImageView imgFlag, imgImportant;

        public WorkViewHolder(@NonNull View itemView) {
            super(itemView);
            
            cbCompleted = itemView.findViewById(R.id.cbCompleted);
            tvNoteWork = itemView.findViewById(R.id.tvNoteWork);
            imgFlag = itemView.findViewById(R.id.imgFlag);
            imgImportant = itemView.findViewById(R.id.imgImportant);
            
        }
    }
}
