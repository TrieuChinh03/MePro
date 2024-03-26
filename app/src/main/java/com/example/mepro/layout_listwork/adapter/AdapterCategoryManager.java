package com.example.mepro.layout_listwork.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mepro.R;
import com.example.mepro.layout_listwork.database.CategoryDB;
import com.example.mepro.layout_listwork.database.WorkDB;
import com.example.mepro.layout_listwork.model.Category;
import com.example.mepro.util.DialogColor;

import java.util.List;

public class AdapterCategoryManager extends RecyclerView.Adapter<AdapterCategoryManager.ViewHolder> {
    
    private List<Category> listCategory;
    private Context context;
    
    public AdapterCategoryManager(List<Category> listCategory, Context context) {
        this.listCategory = listCategory;
        this.context = context;
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText edtNameCategory;
        ImageView imgFlag;
        ImageButton imgbtMenuOptions;
        
        public ViewHolder(View itemView) {
            super(itemView);
            edtNameCategory = itemView.findViewById(R.id.edtNameCategory);
            imgFlag = itemView.findViewById(R.id.imgFlag);
            imgbtMenuOptions = itemView.findViewById(R.id.imgbtMenuOptions);
            
            //===   Sự kiện khi nhấn menu   ===
            imgbtMenuOptions.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    PopupMenu popupMenu = new PopupMenu(itemView.getContext(), itemView);
                    popupMenu.inflate(R.menu.menu_work_category_manager);
                    CategoryDB categoryDB = new CategoryDB(itemView.getContext());
                    WorkDB workDB = new WorkDB(itemView.getContext());
                    popupMenu.setOnMenuItemClickListener(item -> {
                        if (item.getItemId() == R.id.action_edit) {
                            Dialog dialog = new Dialog(context);
                            LayoutInflater inflater = LayoutInflater.from(context);
                            
                            View dialogView = inflater.inflate(R.layout.dialog_category_manager, null);
                            Button btCancel = dialogView.findViewById(R.id.btCancel);
                            Button btOK = dialogView.findViewById(R.id.btOK);
                            EditText edtAddNameCategory = dialogView.findViewById(R.id.edtAddNameCategory);
    
                            if(listCategory.get(position).getId() == -1)
                                edtAddNameCategory.setText("");
                            
                            btCancel.setOnClickListener(view -> dialog.dismiss());
                            
                            btOK.setOnClickListener(view -> {
                                String text = edtAddNameCategory.getText().toString().trim();
                                if(!text.equals("")) {
                                    listCategory.get(position).setNameCategory(edtAddNameCategory.getText().toString().trim());
                                    CategoryDB categoryDB1 = new CategoryDB(itemView.getContext());
                                    if((listCategory.get(position).getId() == -1) && !listCategory.get(position).getNameCategory().equals(""))
                                        categoryDB1.insertData(listCategory.get(position));
                                    else
                                        categoryDB1.updateData(listCategory.get(position));
                                    notifyDataSetChanged();
                                }
                                dialog.dismiss();
                            });
    
                            dialog.setContentView(dialogView);
                            dialog.show();
                            
                        } else if (item.getItemId() == R.id.action_delete) {
                            if(position != 0) {
                                categoryDB.deleteData(listCategory.get(position));
                                workDB.deleteData(listCategory.get(position));
                                listCategory.remove(position);
                                notifyDataSetChanged();
                            }
                        }
                        return false;
                    });
                    popupMenu.show();
                }
            });
    
            //===   Sự kiện khi nhấn image flag     ===
            imgFlag.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    DialogColor dialogColor = new DialogColor(itemView.getContext());
                    dialogColor.setCallback(color -> {
                        listCategory.get(position).setColor(color);
                        CategoryDB categoryDB = new CategoryDB(itemView.getContext());
                        categoryDB.updateData(listCategory.get(position));
                        notifyDataSetChanged();
                    });
                    dialogColor.show();
                }
            });
        }
    }
    
    //===   Tạo view item category manager   ===
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_category_manager, parent, false);
        return new ViewHolder(view);
    }
    
    //===   Set item category manager   ===
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(listCategory != null) {
            Category category = listCategory.get(position);
            holder.edtNameCategory.setText(category.getNameCategory());
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_flag);
            PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(category.getColor(), PorterDuff.Mode.SRC_IN);
            drawable.setColorFilter(colorFilter);
            holder.imgFlag.setImageDrawable(drawable);
        }
    }
    
    //===   Lấy số lượng item   ===
    @Override
    public int getItemCount() {
        return listCategory.size();
    }
    
}
