package com.softsquared.todaysout.src.weather.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.weather.models.TodayWeatherInfo;
import com.softsquared.todaysout.src.weather.models.WeekInfo;
import com.softsquared.todaysout.src.weather.models.WeekWeatherInfo;

import java.util.ArrayList;

public class WeekWeaterAdapter extends RecyclerView.Adapter<WeekWeaterAdapter.SliderViewHolder> {
    private ArrayList<WeekInfo> weekWeatherlist;
    private Context context;

    public WeekWeaterAdapter(Context context, ArrayList<WeekInfo> weekWeatherlist) {
        this.context = context;
        this.weekWeatherlist = weekWeatherlist;

    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.week_weather_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setData(weekWeatherlist.get(position));
    }

    @Override
    public int getItemCount() {
        return weekWeatherlist.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{

        private TextView week;
        private TextView today;
        private ImageView imgWeather;
        private TextView rain;
        private TextView best;
        private TextView lowest;



        WeekInfo weekInfo;


        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            week = itemView.findViewById(R.id.week);
            today = itemView.findViewById(R.id.today);
            imgWeather = itemView.findViewById(R.id.imgWeather);
            rain = itemView.findViewById(R.id.rain);
            best = itemView.findViewById(R.id.best);
            lowest = itemView.findViewById(R.id.lowest);


        }

        public void setData(WeekInfo weekInfo) {
            this.weekInfo = weekInfo;

            week.setText(weekInfo.getWeek());

            if(weekInfo.getWeather().equals("맑음")){
                imgWeather.setImageResource(R.drawable.small1);
            }else if(weekInfo.getWeather().equals("구름많음")){
                imgWeather.setImageResource(R.drawable.small3);
            }else if(weekInfo.getWeather().equals("구름많고 비")){
                imgWeather.setImageResource(R.drawable.small5);
            }else if(weekInfo.getWeather().equals("구름많고 눈")){
                imgWeather.setImageResource(R.drawable.small6);
            }else if(weekInfo.getWeather().equals("구름많고 소나기")){
                imgWeather.setImageResource(R.drawable.small8);
            }else if(weekInfo.getWeather().equals("흐림")){
                imgWeather.setImageResource(R.drawable.small4);
            }else if(weekInfo.getWeather().equals("흐리고 비")){
                imgWeather.setImageResource(R.drawable.small5);
            }else if(weekInfo.getWeather().equals("흐리고 눈")){
                imgWeather.setImageResource(R.drawable.small6);
            }else if(weekInfo.getWeather().equals("흐리고 소나기")){
                imgWeather.setImageResource(R.drawable.small8);
            }else{
                imgWeather.setImageResource(R.drawable.small9);
            }


            today.setText(weekInfo.getDay());

            rain.setText(weekInfo.getRain());
            best.setText(weekInfo.getUp());
            lowest.setText(weekInfo.getDown());

        }



    }
}
