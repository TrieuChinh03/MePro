package com.example.mepro.layout_listword.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mepro.R;
import com.example.mepro.layout_listword.model.Work;

import java.util.List;

public class AdapterWork extends RecyclerView.Adapter<AdapterWork.WorkViewHolder> {
    private List<Work> listWork;
    private OnClickListenerCheckBox onClickListenerCheckBox;
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
        holder.cbCompleted.setChecked(work.isComplete());
        holder.tvNoteWork.setText(work.getNoteWork());

        holder.cbCompleted.setOnClickListener(view ->{
            onClickListenerCheckBox.onClickCheckBox(work, position);
        });
    }

    @Override
    public int getItemCount() {
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
