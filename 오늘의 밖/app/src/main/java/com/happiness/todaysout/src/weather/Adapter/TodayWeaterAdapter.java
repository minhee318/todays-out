package com.happiness.todaysout.src.weather.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.weather.models.NowInfo;

import java.util.ArrayList;

public class TodayWeaterAdapter extends RecyclerView.Adapter<TodayWeaterAdapter.SliderViewHolder> {
    private ArrayList<NowInfo> todayWeatherlist;
    private Context context;

    public TodayWeaterAdapter(Context context, ArrayList<NowInfo> todayWeatherlist) {
        this.context = context;
        this.todayWeatherlist = todayWeatherlist;

    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.today_weather_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setData(todayWeatherlist.get(position));
    }

    @Override
    public int getItemCount() {
        return todayWeatherlist.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{

        private TextView todayTime;
        private ImageView imgTodayWeather;
        private TextView todayTemperature;



        NowInfo nowInfo;


        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            todayTime = itemView.findViewById(R.id.todayTime);
            imgTodayWeather = itemView.findViewById(R.id.imgTodayWeather);
            todayTemperature = itemView.findViewById(R.id.todayTemperature);


        }

        public void setData(NowInfo nowInfo) {
            this.nowInfo = nowInfo;

            todayTime.setText(nowInfo.getTime());

            if (nowInfo.getSky().equals("1") && nowInfo.getRain().equals("0")) {

                imgTodayWeather.setImageResource(R.drawable.small1);
            } else if (nowInfo.getSky().equals("3") && nowInfo.getRain().equals("0")) {


                imgTodayWeather.setImageResource(R.drawable.small3);
            } else if (nowInfo.getSky().equals("3") && nowInfo.getRain().equals("1")) {

                imgTodayWeather.setImageResource(R.drawable.small3);
            } else if (nowInfo.getSky().equals("3") && nowInfo.getRain().equals("2")) {

                imgTodayWeather.setImageResource(R.drawable.small5);
            } else if (nowInfo.getSky().equals("3") && nowInfo.getRain().equals("3")) {
                imgTodayWeather.setImageResource(R.drawable.small6);
            } else if (nowInfo.getSky().equals("3") && nowInfo.getRain().equals("4")) {

                imgTodayWeather.setImageResource(R.drawable.small8);
            } else if (nowInfo.getSky().equals("3") && nowInfo.getRain().equals("5")) {
                //text_ment1.setText("????????? ??????\n????????????\n????????????");

                imgTodayWeather.setImageResource(R.drawable.small5);
            } else if (nowInfo.getSky().equals("3") && nowInfo.getRain().equals("6")) {

               // text_ment2.setText("????????? ??????\n???????????????\n???????????? ?????????");
                imgTodayWeather.setImageResource(R.drawable.small9);
            } else if (nowInfo.getSky().equals("3") && nowInfo.getRain().equals("7")) {
//                text_ment1.setText("????????? ??????\n????????????\n?????????");
//                text_ment2.setText("????????? ??????\n????????????\n?????????");
                imgTodayWeather.setImageResource(R.drawable.small6);
            } else if (nowInfo.getSky().equals("4") && nowInfo.getRain().equals("0")) {
//                text_ment1.setText("?????????");
//                text_ment2.setText("?????????");
                imgTodayWeather.setImageResource(R.drawable.small4);
            } else if (nowInfo.getSky().equals("4") && nowInfo.getRain().equals("1")) {
//                text_ment1.setText("?????????\n?????? ??????");
//                text_ment2.setText("?????????\n?????? ??????");
                imgTodayWeather.setImageResource(R.drawable.small5);
            } else if (nowInfo.getSky().equals("4") && nowInfo.getRain().equals("2")) {
//                text_ment1.setText("?????????\n??????\n?????? ??????");
//                text_ment2.setText("?????????\n??????\n?????? ??????");
                imgTodayWeather.setImageResource(R.drawable.small9);
            } else if (nowInfo.getSky().equals("4") && nowInfo.getRain().equals("3")) {
//                text_ment1.setText("?????????\n?????? ??????");
//                text_ment2.setText("?????????\n?????? ??????");

                imgTodayWeather.setImageResource(R.drawable.one6);
            } else if (nowInfo.getSky().equals("4") && nowInfo.getRain().equals("4")) {
//                text_ment1.setText("?????????\n???????????? ??????");
//                text_ment2.setText("?????????\n???????????? ??????");
                imgTodayWeather.setImageResource(R.drawable.small8);
            } else if (nowInfo.getSky().equals("4") && nowInfo.getRain().equals("5")) {
//                text_ment1.setText("?????????\n????????????\n????????????");
//                text_ment2.setText("?????????\n????????????\n????????????");
                imgTodayWeather.setImageResource(R.drawable.small5);
            } else if (nowInfo.getSky().equals("4") && nowInfo.getRain().equals("6")) {
//                text_ment1.setText("?????????\n???????????????\n???????????? ?????????");
//                text_ment2.setText("?????????\n???????????????\n???????????? ?????????");
                imgTodayWeather.setImageResource(R.drawable.small9);
            } else if (nowInfo.getSky().equals("4") && nowInfo.getRain().equals("7")) {
//                text_ment1.setText("?????????\n????????????\n?????????");
//                text_ment2.setText("?????????\n????????????\n?????????");
                imgTodayWeather.setImageResource(R.drawable.small6);
            }


            todayTemperature.setText(nowInfo.getOndo());

        }



    }
}
