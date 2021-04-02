package com.happiness.todaysout.src.main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.GuideActivity;
import com.happiness.todaysout.src.emergency.EmergencyActivity;
import com.happiness.todaysout.src.main.EditTownActivity;
import com.happiness.todaysout.src.main.models.MainInfo;
import com.happiness.todaysout.src.weather.WeatherActivity;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.SliderViewHolder> {
    private ArrayList<MainInfo> list;
    private ViewPager2 viewPager2;
    private Context context;

    public MainAdapter(Context context, ArrayList<MainInfo> list, ViewPager2 viewPager2) {
        this.context = context;
        this.list = list;
        this.viewPager2 = viewPager2;
    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.main_slide_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setData(list.get(position));

        holder.LL_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent weather = new Intent(context, WeatherActivity.class);
                Log.d("확인","list.get(position).getAddressIdx():"+list.get(position).getAddressIdx());
                weather.putExtra("addressIdx",list.get(position).getAddressIdx());
                context.startActivity(weather);

            }
        });

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(context, EditTownActivity.class);
                context.startActivity(main);
            }
        });

        holder.LL_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alarm = new Intent(context, EmergencyActivity.class);
                alarm.putExtra("gu",list.get(position).getNameGu());
                context.startActivity(alarm);
            }
        });


        holder.img_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(context, GuideActivity.class);
                context.startActivity(info);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{

        private TextView text_mainOndo;
        private TextView text_mainUpOndo;
        private TextView text_mainDownOndo;
        private TextView text_weather_ment;
        private TextView text_first_ment;
        private TextView text_heart_number;
        private TextView text_alarm_number;
        private TextView text_alarm_first_ment;
        private TextView text_alarm_heart_number;
        private TextView text_main_gu;
        private ImageView btn_add;
        private LinearLayout LL_weather;
        private LinearLayout LL_alarm;
        private ImageView img_dust;
        private ImageView img_weather_icon;
        private LinearLayout LL_weatherheart;
        private ImageView img;
        private LinearLayout LL_dheart;
        private ImageView img_info;


        MainInfo mainInfo;


        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            text_mainOndo = itemView.findViewById(R.id.text_mainOndo);
            text_mainUpOndo = itemView.findViewById(R.id.text_mainUpOndo);
            text_mainDownOndo = itemView.findViewById(R.id.text_mainDownOndo);
            text_weather_ment = itemView.findViewById(R.id.text_weather_ment);
            text_first_ment = itemView.findViewById(R.id.text_first_ment);
            text_heart_number = itemView.findViewById(R.id.text_heart_number);
            text_alarm_number = itemView.findViewById(R.id.text_alarm_number);
            text_alarm_first_ment = itemView.findViewById(R.id.text_alarm_first_ment);
            text_alarm_heart_number = itemView.findViewById(R.id.text_alarm_heart_number);
            text_main_gu = itemView.findViewById(R.id.text_main_gu);
            btn_add = itemView.findViewById(R.id.btn_add);
            img_weather_icon = itemView.findViewById(R.id.img_weather_icon);
            LL_weather = itemView.findViewById(R.id.LL_weather);
            LL_alarm = itemView.findViewById(R.id.LL_alarm);
            img_dust = itemView.findViewById(R.id.img_dust);
            LL_weatherheart = itemView.findViewById(R.id.LL_weatherheart);
            img = itemView.findViewById(R.id.img);
            LL_dheart = itemView.findViewById(R.id.LL_dheart);
            img_info = itemView.findViewById(R.id.img_info);



        }

        public void setData(MainInfo mainInfo) {
            this.mainInfo = mainInfo;

            text_mainOndo.setText(mainInfo.getWeatherOndo());

            text_mainUpOndo.setText(mainInfo.getUp());

            text_mainDownOndo.setText(mainInfo.getDown());


            if(mainInfo.getWeatherHeartNumber() != null){
                text_first_ment.setText(mainInfo.getWeatherFirstMent());
            }else{
                text_first_ment.setText("아직 올라온 글이 없어요");
            }


            Log.d("확인","mainInfo.getAlarmFirstMent()"+mainInfo.getAlarmFirstMent());
            if(mainInfo.getAlarmFirstMent() != null){
                text_alarm_first_ment.setText(mainInfo.getAlarmFirstMent());
            }else{
                text_alarm_first_ment.setText("아직 올라온 글이 없어요");
            }



            text_weather_ment.setText(mainInfo.getWeatherMent());



            if(mainInfo.getWeatherHeartNumber() != null){
                LL_weatherheart.setVisibility(View.VISIBLE);
                text_heart_number.setText((mainInfo.getWeatherHeartNumber()));
            }else{
                LL_weatherheart.setVisibility(View.GONE);
            }


            text_alarm_number.setText(Integer.toString(mainInfo.getAlarmNumber()));








            if(mainInfo.getAlarmHeartNumber() != null){
                LL_dheart.setVisibility(View.VISIBLE);
                text_alarm_heart_number.setText((mainInfo.getAlarmHeartNumber()));
            }else{
                LL_dheart.setVisibility(View.GONE);
            }



            text_main_gu.setText(mainInfo.getNameGu());

            if(mainInfo.getDust().equals("1")){
                img_dust.setImageResource(R.drawable.dust_good);
            }else if(mainInfo.getDust().equals("2")){
                img_dust.setImageResource(R.drawable.usually);
            }else if(mainInfo.getDust().equals("3")){
                img_dust.setImageResource(R.drawable.little_bad);
            }else if(mainInfo.getDust().equals("4")){
                img_dust.setImageResource(R.drawable.dust_bad);
            }

            if(mainInfo.getWeatherPicture() != null) {


                if (mainInfo.getWeatherPicture().equals("1")) {
                    img_weather_icon.setImageResource(R.drawable.testsun);
                } else if (mainInfo.getWeatherPicture().equals("2")) {
                    img_weather_icon.setImageResource(R.drawable.one2);
                } else if (mainInfo.getWeatherPicture().equals("3")) {
                    img_weather_icon.setImageResource(R.drawable.one3);
                } else if (mainInfo.getWeatherPicture().equals("4")) {
                    img_weather_icon.setImageResource(R.drawable.one4);
                } else if (mainInfo.getWeatherPicture().equals("5")) {
                    img_weather_icon.setImageResource(R.drawable.one5);
                } else if (mainInfo.getWeatherPicture().equals("6")) {
                    img_weather_icon.setImageResource(R.drawable.one6);
                } else if (mainInfo.getWeatherPicture().equals("7")) {
                    img_weather_icon.setImageResource(R.drawable.one7);
                } else if (mainInfo.getWeatherPicture().equals("8")) {
                    img_weather_icon.setImageResource(R.drawable.one8);
                } else if (mainInfo.getWeatherPicture().equals("9")) {
                    img_weather_icon.setImageResource(R.drawable.one9);
                } else if (mainInfo.getWeatherPicture().equals("10")) {
                    img_weather_icon.setImageResource(R.drawable.one10);
                } else if (mainInfo.getWeatherPicture().equals("11")) {
                    img_weather_icon.setImageResource(R.drawable.one11);
                } else if (mainInfo.getWeatherPicture().equals("12")) {
                    img_weather_icon.setImageResource(R.drawable.one12);
                }
            }else img_weather_icon.setImageResource(R.drawable.one12);


          if(mainInfo.getAlarmPicture() != null)  {

              if(mainInfo.getAlarmPicture().equals("지진")){
                  img.setImageResource(R.drawable.earthquake);

              }else if(mainInfo.getAlarmPicture().equals("태풍")){
                  img.setImageResource(R.drawable.typhoon2);

              }else if(mainInfo.getAlarmPicture().equals("해일")){
                  img.setImageResource(R.drawable.natural3);

              }else if(mainInfo.getAlarmPicture().equals("홍수")){
                  img.setImageResource(R.drawable.flood);

              }else if(mainInfo.getAlarmPicture().equals("호우")){
                  img.setImageResource(R.drawable.downpour);

              }else if(mainInfo.getAlarmPicture().equals("강풍")){
                  img.setImageResource(R.drawable.gale);

              }else if(mainInfo.getAlarmPicture().equals("대설")){
                  img.setImageResource(R.drawable.heavy_snow);

              }else if(mainInfo.getAlarmPicture().equals("한파")){
                  img.setImageResource(R.drawable.cold);

              }else if(mainInfo.getAlarmPicture().equals("폭염")){
                  img.setImageResource(R.drawable.hot);

              }else if(mainInfo.getAlarmPicture().equals("건조")){
                  img.setImageResource(R.drawable.dry);

              }else if(mainInfo.getAlarmPicture().equals("황사")){
                  img.setImageResource(R.drawable.dust_storm);

              }else if(mainInfo.getAlarmPicture().equals("민방공")){
                  img.setImageResource(R.drawable.war);

              }else if(mainInfo.getAlarmPicture().equals("테러")){
                  img.setImageResource(R.drawable.terror);

              }else if(mainInfo.getAlarmPicture().equals("방사능")){
                  img.setImageResource(R.drawable.radiation);

              }else if(mainInfo.getAlarmPicture().equals("감염병")){
                  img.setImageResource(R.drawable.virus_four);

              }else if(mainInfo.getAlarmPicture().equals("미세먼지")){
                  img.setImageResource(R.drawable.dust);

              }else if(mainInfo.getAlarmPicture().equals("화재")){
                  img.setImageResource(R.drawable.fire);

              }else if(mainInfo.getAlarmPicture().equals("수질")){
                  img.setImageResource(R.drawable.water);

              }else if(mainInfo.getAlarmPicture().equals("위험물")){
                  img.setImageResource(R.drawable.danger);

              }else if(mainInfo.getAlarmPicture().equals("붕괴")){
                  img.setImageResource(R.drawable.collapse);

              }else if(mainInfo.getAlarmPicture().equals("교통사고")){
                  img.setImageResource(R.drawable.traffic_accident);

              }else if(mainInfo.getAlarmPicture().equals("현장사고")){
                  img.setImageResource(R.drawable.construct_accident);

              }else if(mainInfo.getAlarmPicture().equals("자원수급")){
                  img.setImageResource(R.drawable.resource);

              }else if(mainInfo.getAlarmPicture().equals("통신")){
                  img.setImageResource(R.drawable.communication);

              }else if(mainInfo.getAlarmPicture().equals("우주")){
                  img.setImageResource(R.drawable.space);

              }

          }else{
              Log.d("확인","null입니다.");
          }




        }





    }
}
