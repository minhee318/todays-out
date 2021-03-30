package com.happiness.todaysout.src.emergency.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.emergency.models.JInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.SliderViewHolder> {
    private ArrayList<JInfo> emergencylist;
    private Context context;
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public EmergencyAdapter(Context context, ArrayList<JInfo> emergencylist) {
        this.context = context;
        this.emergencylist = emergencylist;

    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.emergency_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setData(emergencylist.get(position));
    }

    @Override
    public int getItemCount() {
        return emergencylist.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{

        private TextView content;
        private TextView time;
        private ImageView img_emergency;




        JInfo jInfo;


        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
            img_emergency = itemView.findViewById(R.id.img_emergency);



        }

        public void setData(JInfo jInfo) {
            this.jInfo = jInfo;

            content.setText(jInfo.getMsg());

            try {

                Date date = sdfNow.parse(jInfo.getDate());

                if (calculateTime(date).equals("hi")) {
                    String day = jInfo.getDate().substring(0, 10);
                    time.setText(day);
                } else {
                    time.setText(calculateTime(date));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


            if(jInfo.getKinds().equals("지진")){
                img_emergency.setImageResource(R.drawable.earthquake);
            }else if(jInfo.getKinds().equals("태풍")){
                img_emergency.setImageResource(R.drawable.typhoon2);
            }else if(jInfo.getKinds().equals("해일")){
                img_emergency.setImageResource(R.drawable.natural3);
            }else if(jInfo.getKinds().equals("홍수")){
                img_emergency.setImageResource(R.drawable.flood);
            }else if(jInfo.getKinds().equals("호우")){
                img_emergency.setImageResource(R.drawable.downpour);
            }else if(jInfo.getKinds().equals("강풍")){
                img_emergency.setImageResource(R.drawable.gale);
            }else if(jInfo.getKinds().equals("대설")){
                img_emergency.setImageResource(R.drawable.heavy_snow);
            }else if(jInfo.getKinds().equals("한파")){
                img_emergency.setImageResource(R.drawable.cold);
            }else if(jInfo.getKinds().equals("폭염")){
                img_emergency.setImageResource(R.drawable.hot);
            }else if(jInfo.getKinds().equals("건조")){
                img_emergency.setImageResource(R.drawable.dry);
            }else if(jInfo.getKinds().equals("황사")){
                img_emergency.setImageResource(R.drawable.dust_storm);
            }else if(jInfo.getKinds().equals("민방공")){
                img_emergency.setImageResource(R.drawable.war);
            }else if(jInfo.getKinds().equals("테러")){
                img_emergency.setImageResource(R.drawable.terror);
            }else if(jInfo.getKinds().equals("방사능")){
                img_emergency.setImageResource(R.drawable.radiation);
            }else if(jInfo.getKinds().equals("감염병")){
                img_emergency.setImageResource(R.drawable.virus_four);
            }else if(jInfo.getKinds().equals("미세먼지")){
                img_emergency.setImageResource(R.drawable.dust);
            }else if(jInfo.getKinds().equals("화재")){
                img_emergency.setImageResource(R.drawable.fire);
            }else if(jInfo.getKinds().equals("수질")){
                img_emergency.setImageResource(R.drawable.water);
            }else if(jInfo.getKinds().equals("위험물")){
                img_emergency.setImageResource(R.drawable.danger);
            }else if(jInfo.getKinds().equals("붕괴")){
                img_emergency.setImageResource(R.drawable.collapse);
            }else if(jInfo.getKinds().equals("교통사고")){
                img_emergency.setImageResource(R.drawable.traffic_accident);
            }else if(jInfo.getKinds().equals("현장사고")){
                img_emergency.setImageResource(R.drawable.construct_accident);
            }else if(jInfo.getKinds().equals("자원수급")){
                img_emergency.setImageResource(R.drawable.resource);
            }else if(jInfo.getKinds().equals("통신")){
                img_emergency.setImageResource(R.drawable.communication);
            }else if(jInfo.getKinds().equals("우주")){
                img_emergency.setImageResource(R.drawable.space);
            }







        }



    }

    private static class TIME_MAXIMUM {
        public static final int SEC = 60;
        public static final int MIN = 60;
        public static final int HOUR = 24;
        public static final int DAY = 30;
        public static final int MONTH = 12;
    }

    public String calculateTime(Date date) {

        long curTime = System.currentTimeMillis();
        long regTime = date.getTime();
        long diffTime = (curTime - regTime) / 1000;

        String msg = null;

        if (diffTime < TIME_MAXIMUM.SEC) {
            // sec
            msg = diffTime + "초전";
        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
            // min
            System.out.println(diffTime);

            msg = diffTime + "분전";
        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
            // hour
            msg = (diffTime) + "시간전";
        }
//        else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY)
//        {
//            // day
//            msg = (diffTime ) + "일전";
//        }
//        else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH)
//        {
//            // day
//            msg = (diffTime ) + "달전";
//        }
        else {
//            msg = (diffTime) + "년전";
            msg = "hi";

        }

        return msg;
    }




}
