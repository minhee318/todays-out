package com.softsquared.todaysout.src.emergency.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.emergency.models.Day;
import com.softsquared.todaysout.src.emergency.models.EmptyDay;

import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter {
    private final int HEADER_TYPE = 0;
    private final int EMPTY_TYPE = 1;
    private final int DAY_TYPE = 2;
    String gu;


    private List<Object> mCalendarList;

    public CalendarAdapter(List<Object> calendarList,String gu){
        mCalendarList = calendarList;
        this.gu = gu;
    }

    public void setCalendarList(List<Object> calendarList){
        mCalendarList = calendarList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) { //뷰타입 나누기
        Object item =  mCalendarList.get(position);
        if(item instanceof Long){ //날짜 타입
            return HEADER_TYPE;
        }else if(item instanceof String){ //비어있는 일자 타입
            return EMPTY_TYPE;
        }else {
            return DAY_TYPE; //일자 타입
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == HEADER_TYPE) {

            HeaderViewHolder viewHolder = new HeaderViewHolder(inflater.inflate(R.layout.head_item, parent, false));

            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams)viewHolder.itemView.getLayoutParams();
            params.setFullSpan(true); //Span을 하나로 통합하기
            viewHolder.itemView.setLayoutParams(params);

            return viewHolder;

            //비어있는 일자 타입
        } else if (viewType == EMPTY_TYPE) {
            return new EmptyViewHolder(inflater.inflate(R.layout.day_empty_item, parent, false));

        }
        // 일자 타입
        else {
            return new DayViewHolder(inflater.inflate(R.layout.day_item, parent, false));

        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if(viewType == DAY_TYPE){
            DayViewHolder holder1 =  (DayViewHolder) holder;
            Object item = mCalendarList.get(position);
            Day model = new Day();
            if(item instanceof Calendar){

               model.setCalendar((Calendar)item);

            }

            holder1.bind(model,gu);

        }else if(viewType == EMPTY_TYPE){
            EmptyViewHolder holder1 = (EmptyViewHolder) holder;
            EmptyDay model = new EmptyDay();
            holder1.bind(model);
        }else if(viewType == HEADER_TYPE){

            HeaderViewHolder holder1 = (HeaderViewHolder) holder;
            Object item = mCalendarList.get(position);
            CalendarHeader model = new CalendarHeader();

            // long type의 현재시간
            if (item instanceof Long) {
                // 현재시간 넣으면, 2017년 7월 같이 패턴에 맞게 model에 데이터들어감.
                model.setHeader((Long) item);
            }
            // view에 표시하기
            holder1.bind(model);
        }




//        if(dTime.equals(year+"가"+month+"나"+day)){
//            item_layout.setBackgroundResource(R.drawable.today_rectangle);
//            view16.setVisibility(View.GONE);
//        }


    }

    @Override
    public int getItemCount() {
        if(mCalendarList != null){
            return mCalendarList.size();
        }
        return 0;
    }
}
