package com.softsquared.todaysout.src.emergency.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.emergency.models.ViewModel;

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    TextView itemHeaderTitle;

    public HeaderViewHolder(@NonNull View itemView) {
        super(itemView);

        initView(itemView);
    }


    public void initView(View v){

        itemHeaderTitle = (TextView)v.findViewById(R.id.item_header_title);

    }

    public void bind(ViewModel model){

        // 일자 값 가져오기
        String header = ((CalendarHeader)model).getHeader();

        // header에 표시하기, ex : 2018년 8월
        itemHeaderTitle.setText(header);


    };
}

