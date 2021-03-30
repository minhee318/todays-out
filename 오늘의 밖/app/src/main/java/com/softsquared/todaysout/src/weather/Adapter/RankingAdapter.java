package com.softsquared.todaysout.src.weather.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.weather.NoticeBoardDetailActivity;
import com.softsquared.todaysout.src.weather.models.BoardInfo;
import com.softsquared.todaysout.src.weather.models.NoticeBoardInfo;
import com.softsquared.todaysout.src.weather.models.RankingInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.SliderViewHolder> {
    private ArrayList<BoardInfo> rankinglist;
    private Context context;
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public RankingAdapter(Context context, ArrayList<BoardInfo> rankinglist) {
        this.context = context;
        this.rankinglist = rankinglist;
    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.heart_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setData(rankinglist.get(position));


        if(position == 0){
            holder.LL_rank.setBackground(ContextCompat.getDrawable(context, R.drawable.heart_bg2));
            holder.textRank2.setTextColor(Color.parseColor("#ffffff"));
            holder.textRank.setTextColor(Color.parseColor("#ffffff"));
            holder.textHeart.setTextColor(Color.parseColor("#ffffff"));
            holder.imgHeart.setImageResource(R.drawable.heart_icon2);
        }


    }

    @Override
    public int getItemCount() {
        return rankinglist.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CircleImageView imgProfile;
        private TextView textNickName;
        private TextView textDong;
        private TextView textTime;
        private TextView textContent;
        private TextView textHeart;
        private  ImageView imgDot;
        private LinearLayout LL_rank;
        private TextView textRank2;
        private TextView textRank;
        private ImageView imgHeart;




        BoardInfo boardInfo;


        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imgProfile);
            textNickName = itemView.findViewById(R.id.textNickName);
            textDong = itemView.findViewById(R.id.textDong);
            textTime = itemView.findViewById(R.id.textTime);
            textContent = itemView.findViewById(R.id.textContent);

            textHeart = itemView.findViewById(R.id.textHeart);
            imgDot = itemView.findViewById(R.id.imgDot);
            LL_rank = itemView.findViewById(R.id.LL_rank);

            textRank = itemView.findViewById(R.id.textRank);

            textRank2 = itemView.findViewById(R.id.textRank2);

            imgHeart = itemView.findViewById(R.id.imgHeart);

            imgDot.setOnClickListener(this);


        }

        public void setData(BoardInfo boardInfo) {
            this.boardInfo = boardInfo;

            textRank.setText(Integer.toString(getAdapterPosition()+1));

            Glide
                    .with(context)
                    .load(boardInfo.getPicture())
                    .centerCrop()
                    .thumbnail(0.1f) //10프로만 로딩되도 흐릿하게 보여준다.
                    .into(imgProfile);


            textNickName.setText(boardInfo.getNickName());
            textDong.setText(boardInfo.getDong());


                if ( Integer.parseInt(boardInfo.getDate().substring(11, 13)) > 12) {

                    textTime.setText(boardInfo.getDate().substring(11, 16)+" PM");
                } else {

                    textTime.setText(boardInfo.getDate().substring(11, 16)+" AM");
                }

            textContent.setText(boardInfo.getMsg());

            textHeart.setText(boardInfo.getHeartNum());


        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgDot:

                    Log.d("확인","눌리나?");

                    break;

            }

        }
    }





}
