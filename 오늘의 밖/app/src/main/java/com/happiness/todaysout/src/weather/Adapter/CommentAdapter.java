package com.happiness.todaysout.src.weather.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.happiness.todaysout.R;
import com.happiness.todaysout.src.mypage.MyPageActivity;
import com.happiness.todaysout.src.weather.Adapter.Interface.WeatherAdapterView;
import com.happiness.todaysout.src.weather.NoticeBoardDetailActivity;
import com.happiness.todaysout.src.weather.models.CommentInfo;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.SliderViewHolder> implements WeatherAdapterView {
    private ArrayList<CommentInfo> commentlist;
    private Context context;
    Long msgIdx;
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public CommentAdapter(Context context, ArrayList<CommentInfo> commentlist,Long msgIdx) {
        this.context = context;
        this.commentlist = commentlist;
        this.msgIdx = msgIdx;
    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.comment_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setData(commentlist.get(position));

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("확인", "삭제" + commentlist.get(position).getCommentIdx());
//                removeItem(position);
                tryDeleteInfo(msgIdx,commentlist.get(position).getCommentIdx());
                removeItem(position);
            }
        });

        holder.img_dotcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog editDialog = new BottomSheetDialog(
                        context, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(context)
                        .inflate(
                                R.layout.dialog_comment,
                                ((NoticeBoardDetailActivity) context).findViewById(R.id.bottomSheetContainer)
                        );


                TextView youprofile = (TextView) bottomSheetView.findViewById(R.id.btn_0);

                youprofile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent youprofile = new Intent(context, MyPageActivity.class);
                        youprofile.putExtra("userIdx",commentlist.get(position).getUserIdx());
                        context.startActivity(youprofile);

                        editDialog.cancel();
                    }
                });

                TextView report = (TextView) bottomSheetView.findViewById(R.id.btn_1);

                report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       tryPostReportCommentInfo(commentlist.get(position).getCommentIdx());
                        editDialog.cancel();
                    }
                });


                editDialog.setContentView(bottomSheetView);
                editDialog.show();
            }
        });

    }

    public void removeItem(int position) {
        commentlist.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, commentlist.size());

    }

    private void tryDeleteInfo(Long msgIdx,Long commentIdx) {

        final WeatherAdapterService weatherAdapterService = new WeatherAdapterService(this);
        weatherAdapterService.deleteComment(msgIdx,commentIdx,sSharedPreferences.getLong("USER_IDX", -1));
    }

    private void tryPostReportCommentInfo(Long commentIdx) {

        final WeatherAdapterService weatherAdapterService = new WeatherAdapterService(this);
        weatherAdapterService.postReportComment(commentIdx);
    }


    @Override
    public int getItemCount() {
        return commentlist.size();
    }

    @Override
    public void validateDeleteSuccess(ReportResponse response) {

        Log.d("확인","댓글삭제성공");

    }

    @Override
    public void validateDeleteCommentSuccess(ReportResponse response) {
        Log.d("확인","댓글삭제성공");
    }

    @Override
    public void validatePostReportCommentSuccess(ReportResponse response) {
        Log.d("확인","댓글 신고 성공");
    }

    @Override
    public void validateFailure(String message) {

    }

    class SliderViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView imgProfile;
        private TextView textNickName;
        private TextView textDong;
        private TextView textTime;
        private TextView textContent;
        private TextView btn_delete;
        private ImageView img_dotcomment;


        CommentInfo commentInfo;


        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imgProfile);
            textNickName = itemView.findViewById(R.id.textNickName);
            textDong = itemView.findViewById(R.id.textDong);
            textTime = itemView.findViewById(R.id.textTime);
            textContent = itemView.findViewById(R.id.textContent);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            img_dotcomment =itemView.findViewById(R.id.img_dotcomment);

        }

        public void setData(CommentInfo commentInfo) {
            this.commentInfo = commentInfo;


            Glide
                    .with(context)
                    .load(commentInfo.getProfile())
                    .into(imgProfile);



            textNickName.setText(commentInfo.getNickName());
            textDong.setText(commentInfo.getDong());

            textContent.setText(commentInfo.getMsg());


            Log.d("확인","userIdx 잘 저장 되어있나"+sSharedPreferences.getLong("USER_IDX", -1));
            if(commentInfo.getUserIdx() != sSharedPreferences.getLong("USER_IDX", -1)){
                btn_delete.setVisibility(View.INVISIBLE);
                img_dotcomment.setVisibility(View.VISIBLE);
            }else{
                btn_delete.setVisibility(View.VISIBLE);
                img_dotcomment.setVisibility(View.INVISIBLE);
            }

            try {

                Date date = sdfNow.parse(commentInfo.getDate());

                if (calculateTime(date).equals("hi")) {
                    String day = commentInfo.getDate().substring(0, 10);
                    textTime.setText(day);
                } else {
                    textTime.setText(calculateTime(date));
                }
            }catch (ParseException e) {
                e.printStackTrace();
            }


        }



    }

    private static class TIME_MAXIMUM
    {
        public static final int SEC = 60;
        public static final int MIN = 60;
        public static final int HOUR = 24;
        public static final int DAY = 30;
        public static final int MONTH = 12;
    }

    public String calculateTime(Date date)
    {

        long curTime = System.currentTimeMillis();
        long regTime = date.getTime();
        long diffTime = (curTime - regTime) / 1000;

        String msg = null;

        if (diffTime < TIME_MAXIMUM.SEC)
        {
            // sec
            msg = diffTime + "초전";
        }
        else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN)
        {
            // min
            System.out.println(diffTime);

            msg = diffTime + "분전";
        }
        else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR)
        {
            // hour
            msg = (diffTime ) + "시간전";
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
        else
        {
//            msg = (diffTime) + "년전";
            msg = "hi";

        }

        return msg;
    }


}
