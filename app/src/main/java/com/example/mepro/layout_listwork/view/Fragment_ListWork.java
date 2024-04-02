package com.example.mepro.layout_listwork.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mepro.R;
import com.example.mepro.layout_listwork.adapter.AdapterWork;
import com.example.mepro.layout_listwork.database.WorkDB;
import com.example.mepro.layout_listwork.model.Category;
import com.example.mepro.layout_listwork.model.Work;
import com.example.mepro.layout_note.model.Note;
import com.example.mepro.util.Convert;
import com.example.mepro.util.DateComparator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Fragment_ListWork extends Fragment {
    private RecyclerView rccvListWork, rccvListWorkCompleted;
    private TextView tvCountCompleted;
    private ImageView imgExpand;
    private LinearLayout workNull, workNotNull;
    private View view1;
    private Context context;
    private Category category;
    private int filer;
    
    private LinearLayout workComplete;
    private List<Work> listWork,listWorkFilter, listWorkNotComplete, listWorkCompleted;
    private AdapterWork adapterWork1, adapterWork2;
    private WorkDB dbWork;
    private boolean isExpand = true;
    private int countNotComplete, countCompleted;
    
    public Fragment_ListWork(Context context1, Category category1, int filer1) {
        context = context1;
        category = category1;
        filer = filer1;
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_work_list, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view1 = view;
        mappingId();
        event();
        getData(category);
        setDataListWorkNotComplete();
        setDataListWorkCompleted();
        setCountComplete();
        hideOrShowImageNull();
    }
    
    //===   Ánh xạ view     ===
    private void mappingId() {
        rccvListWork = view1.findViewById(R.id.rccvListWork);
        rccvListWorkCompleted = view1.findViewById(R.id.rccvListWorkCompleted);
        tvCountCompleted = view1.findViewById(R.id.tvCountCompleted);
        workComplete = view1.findViewById(R.id.workComplete);
        imgExpand = view1.findViewById(R.id.imgExpand);
        workNull = view1.findViewById(R.id.workNull);
        workNotNull = view1.findViewById(R.id.workNotNull);
    }
    
    //===   Sự kiện     ===
    private void event() {
        //===   Sự kiện khi hiển thị hoặc không công việc hoàn thành  ===
        workComplete.setOnClickListener(view ->{
            if(isExpand) {
                isExpand = false;
                rccvListWorkCompleted.setVisibility(View.GONE);
                imgExpand.setImageResource(R.drawable.ic_right);
            } else {
                isExpand = true;
                rccvListWorkCompleted.setVisibility(View.VISIBLE);
                imgExpand.setImageResource(R.drawable.ic_down);
            }
        });
        
    }
    
    //===   set dữ liệu chưa hoàn thành     ===
    private void setDataListWorkNotComplete() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view1.getContext());
        rccvListWork.setLayoutManager(linearLayoutManager);
        adapterWork1 = new AdapterWork(context);
        adapterWork1.setData(listWorkNotComplete, (work, position) -> {
            listWorkNotComplete.remove(position);
            adapterWork1.notifyItemRemoved(position);
            adapterWork1.notifyItemRangeRemoved(position, listWorkNotComplete.size());
            work.setCompleted(true);
            dbWork = new WorkDB(view1.getContext());
            dbWork.updateData(work);
            listWorkCompleted.add(work);
            adapterWork2.notifyDataSetChanged();
            setCountComplete();
            hideOrShowImageNull();
        });
        adapterWork1.setOnItemClickListener(position -> {
            Intent intent = new Intent(context, Activity_Work.class);
            intent.putExtra("work", listWorkNotComplete.get(position));
            startActivity(intent);
        });
        rccvListWork.setAdapter(adapterWork1);
    }
    
    //===   set data công việc đã hoàn thành    ===
    private void setDataListWorkCompleted() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view1.getContext());
        rccvListWorkCompleted.setLayoutManager(linearLayoutManager);
        adapterWork2 = new AdapterWork(context);
        adapterWork2.setData(listWorkCompleted, (work, position) -> {
            listWorkCompleted.remove(position);
            adapterWork2.notifyItemRemoved(position);
            adapterWork2.notifyItemRangeRemoved(position, listWorkCompleted.size());
            work.setCompleted(false);
            dbWork = new WorkDB(view1.getContext());
            dbWork.updateData(work);
            listWorkNotComplete.add(work);
            adapterWork1.notifyDataSetChanged();
            setCountComplete();
            hideOrShowImageNull();
        });
        adapterWork2.setOnItemClickListener(position -> {
            Intent intent = new Intent(context, Activity_Work.class);
            intent.putExtra("work", listWorkCompleted.get(position));
            startActivity(intent);
        });
        rccvListWorkCompleted.setAdapter(adapterWork2);
    }
    
    //===   Set chỉ số hoàn thành   ===
    private void setCountComplete() {
        countNotComplete = listWorkCompleted.size();
        countCompleted = listWorkNotComplete.size() + listWorkCompleted.size();
        tvCountCompleted.setText(countNotComplete+"/"+countCompleted);
    }
    
    public void getData(Category category) {
        //===   Lấy dữ liệu   ===
        dbWork = new WorkDB(context);
        listWork = dbWork.getListWork(category.getId());
        listWorkNotComplete = new ArrayList<>();
        listWorkCompleted = new ArrayList<>();
        if(listWork != null && listWork.size() > 0) {
            filterData();
            sortData();
            if(listWorkFilter != null && listWorkFilter.size() > 0) {
                for(Work work1 : listWorkFilter) {
                    if(work1.isCompleted())
                        listWorkCompleted.add(work1);
                    else
                        listWorkNotComplete.add(work1);
                }
            }
        }
    }
    
    //===   Lọc dữ liệu theo thời gian  ===
    public void filterData() {
        listWorkFilter = new ArrayList<>();
        Calendar time = Calendar.getInstance();
        if(filer == 0) {
            for(int i=0 ;i<listWork.size(); i++) {
                boolean isToday = DateUtils.isToday(Convert.getTime(listWork.get(i).getStartTime()).getTime());
                if(isToday)
                    listWorkFilter.add(listWork.get(i));
            }
        } else if(filer == 1) {
            for(int i=0 ;i<listWork.size(); i++) {
                boolean isTomorrow  = DateUtils.isToday(Convert.getTime(listWork.get(i).getStartTime()).getTime()-86400000);
                if(isTomorrow)
                    listWorkFilter.add(listWork.get(i));
            }
        } else if(filer == 2) {
            for(int i=0 ;i<listWork.size(); i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(Convert.getTime(listWork.get(i).getStartTime()));
                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                Date startOfWeek = calendar.getTime();
                calendar.add(Calendar.DAY_OF_WEEK, 6);
                Date endOfWeek = calendar.getTime();
                if (Convert.getTime(listWork.get(i).getStartTime()).after(startOfWeek) && Convert.getTime(listWork.get(i).getStartTime()).before(endOfWeek)) {
                    listWorkFilter.add(listWork.get(i));
                }
            }
        }
    }
    
    //===   Sắp xếp dữ liệu     ===
    private void sortData() {
        List<Date> dates = new ArrayList<>();
        ArrayList<Work> works = new ArrayList<>();
        for(int i=0 ;i<listWorkFilter.size(); i++) {
            dates.add(Convert.getTime(listWorkFilter.get(i).getStartTime()));
        }
        Collections.sort(dates, new DateComparator(true));
        for(int i=0 ;i<dates.size(); i++) {
            for(int j=0; j<listWorkFilter.size(); j++) {
                if(Convert.getTime(listWorkFilter.get(j).getStartTime()).equals(dates.get(i))) {
                    works.add(listWorkFilter.get(j));
                    listWorkFilter.remove(j);
                }
            }
        }
        listWorkFilter = works;
    }
    
    private void hideOrShowImageNull() {
        if(listWorkNotComplete.size() == 0) {
            workNull.setVisibility(View.VISIBLE);
        } else {
            workNull.setVisibility(View.GONE);
        }
    }
}
