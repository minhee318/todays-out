package com.softsquared.todaysout.src.emergency.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.emergency.DateActivity;
import com.softsquared.todaysout.src.emergency.Interface.JActivityView;
import com.softsquared.todaysout.src.emergency.MonthActivity;
import com.softsquared.todaysout.src.emergency.models.Day;
import com.softsquared.todaysout.src.emergency.models.JResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DayViewHolder extends RecyclerView.ViewHolder implements JActivityView {
    TextView itemDay;
    LinearLayout item_layout;
    View view16;
    String day;
    String month;
    String year;
    String gu;



    ImageView img_icon;


    SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
    Date currentTime = new Date ( );
    String dTime = formatter.format ( currentTime );



    public DayViewHolder(View inflate) {
        super(inflate);









        initView(inflate);

    }

    private void initView(View inflate) {

        img_icon = inflate.findViewById(R.id.img_icon);


        view16 = inflate.findViewById(R.id.view16);
        itemDay = (TextView) inflate.findViewById(R.id.item_day);
        item_layout = inflate.findViewById(R.id.item_layout);
        item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mypage = new Intent(view.getContext(), DateActivity.class);
                mypage.putExtra("date",year+"."+month+"."+day);
                mypage.putExtra("month",month);
                mypage.putExtra("day",day);
                mypage.putExtra("gu",gu);

                view.getContext().startActivity(mypage);
                Log.d("확인",year+"년 "+month+"월 "+day+"일"+gu);
            }



        });


    }

    public void bind(ViewModel model,String gu){
        day = ((Day)model).getDay();
        month = ((Day)model).getMonth();
        year = ((Day)model).getYear();
        this.gu = gu;

        Log.d("확인",year+"년 "+month+"월 "+day+"일");

        Log.d("확인","오늘은:"+dTime);


        if(dTime.equals(year+"."+month+"."+day)){
            item_layout.setBackgroundResource(R.drawable.today_rectangle);
            view16.setVisibility(View.GONE);
        }else{
            item_layout.setBackgroundColor(Color.WHITE);
            view16.setVisibility(View.VISIBLE);
        }


        itemDay.setText(day);
    }


    @Override
    public void validateJSuccess(JResponse response) {

    }

    @Override
    public void validateFailure(String message) {

    }
}
