package com.happiness.todaysout.src.weather.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.happiness.todaysout.R;
import com.happiness.todaysout.src.mypage.MyPageActivity;
import com.happiness.todaysout.src.weather.Adapter.Interface.WeatherAdapterView;
import com.happiness.todaysout.src.weather.NoticeBoardDetailActivity;
import com.happiness.todaysout.src.weather.WeatherActivity;
import com.happiness.todaysout.src.weather.models.BoardInfo;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.SliderViewHolder> implements WeatherAdapterView {
    private ArrayList<BoardInfo> noticeBoardlist;
    private Context context;
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Long addressIdx;



//    Long userIdx2 = sSharedPreferences.getLong("USER_IDX", -1);


    public NoticeBoardAdapter(Context context, ArrayList<BoardInfo> noticeBoardlist,Long addressIdx) {
        this.context = context;
        this.noticeBoardlist = noticeBoardlist;
        this.addressIdx = addressIdx;


    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.notice_board_item,
                        parent,
                        false
                )
        );


    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setData(noticeBoardlist.get(position));

        holder.LL_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent notice = new Intent(context, NoticeBoardDetailActivity.class);
                notice.putExtra("msgIdx", noticeBoardlist.get(position).getBoardIdx());
                notice.putExtra("addressIdx", addressIdx);
                Log.d("??????","?????????"+addressIdx);
                context.startActivity(notice);

            }
        });

        holder.imgDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (noticeBoardlist.get(position).getUserIdx() != sSharedPreferences.getLong("USER_IDX", -1)) { // ?????? ????????? ?????????.
                    final BottomSheetDialog profileDialog = new BottomSheetDialog(
                            context, R.style.BottomSheetDialogTheme
                    );
                    View bottomSheetView = LayoutInflater.from(context)
                            .inflate(
                                    R.layout.dialog_detail,
                                    ((WeatherActivity) context).findViewById(R.id.bottomSheetContainer)
                            );


                    final TextView youProfile = (TextView) bottomSheetView.findViewById(R.id.btn_0);

                    youProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myprofile = new Intent(context, MyPageActivity.class);
                            myprofile.putExtra("userIdx",noticeBoardlist.get(position).getUserIdx());
                            context.startActivity(myprofile);

                            profileDialog.cancel();
                        }
                    });

                    final TextView report = (TextView) bottomSheetView.findViewById(R.id.btn_1);

                    report.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tryPostReportContentInfo(noticeBoardlist.get(position).getBoardIdx());
                            profileDialog.cancel();
                        }
                    });


                    profileDialog.setContentView(bottomSheetView);
                    profileDialog.show();
                } else {
                    final BottomSheetDialog profileDialog = new BottomSheetDialog(
                            context, R.style.BottomSheetDialogTheme
                    );
                    View bottomSheetView = LayoutInflater.from(context)
                            .inflate(
                                    R.layout.dialog_mydetail,
                                    ((WeatherActivity) context).findViewById(R.id.bottomSheetContainer)
                            );


                    final TextView delete = (TextView) bottomSheetView.findViewById(R.id.btn_0);

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                             tryDeleteInfo(noticeBoardlist.get(position).getBoardIdx());
                            removeItem(position);
                            profileDialog.cancel();
                        }
                    });

                    final TextView myprofile = (TextView) bottomSheetView.findViewById(R.id.btn_1);

                    myprofile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myprofile = new Intent(context, MyPageActivity.class);
                            myprofile.putExtra("userIdx",sSharedPreferences.getLong("USER_IDX", -1));
                            context.startActivity(myprofile);


                            profileDialog.cancel();
                        }
                    });


                    profileDialog.setContentView(bottomSheetView);
                    profileDialog.show();
                }


            }
        });


    }

    public void removeItem(int position) {
        noticeBoardlist.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, noticeBoardlist.size());

    }

    private void tryDeleteInfo(Long msgIdx) {

        final WeatherAdapterService weatherAdapterService = new WeatherAdapterService(this);
        weatherAdapterService.deleteContent(msgIdx);

    }

    private void tryPostReportContentInfo(Long messageBoardIdx) {

        final WeatherAdapterService weatherAdapterService = new WeatherAdapterService(this);
        weatherAdapterService.postReportContent(messageBoardIdx);
    }

    @Override
    public int getItemCount() {
        return noticeBoardlist.size();
    }

    @Override
    public void validateDeleteSuccess(ReportResponse response) {
        Log.d("??????", "????????? ??????" + response.getCode());
        if (response.getIsSuccess()) {

            switch (response.getCode()) {
                case 1214:
                    Log.d("??????", response.getMessage());
                    break;

            }
        } else {
            switch (response.getCode()) {
                case 3205:
                    Log.d("??????", response.getMessage());
                    break;
                case 3206:
                    Log.d("??????", response.getMessage());
                    break;
                case 3010:
                    Log.d("??????", response.getMessage());
                    break;

            }
        }
    }

    @Override
    public void validateDeleteCommentSuccess(ReportResponse response) {

    }

    @Override
    public void validatePostReportCommentSuccess(ReportResponse response) {

    }

    @Override
    public void validatePostReportContentSuccess(ReportResponse response) {
        Toast.makeText(context, "???????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
        Log.d("??????","????????? ?????? ??????");
    }

    @Override
    public void validateFailure(String message) {

    }


    class SliderViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgProfile;
        private TextView textNickName;
        private TextView textDong;
        private TextView textTime;
        private TextView textContent;
        private TextView textComment;
        private TextView textHeart;
        private ImageView imgDot;
        private LinearLayout LL_comment;
        private LinearLayout LL_heart;
        ImageView fullheart;
        ImageView emptyheart;


        BoardInfo boardInfo;


        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imgProfile);
            textNickName = itemView.findViewById(R.id.textNickName);
            textDong = itemView.findViewById(R.id.textDong);
            textTime = itemView.findViewById(R.id.textTime);
            textContent = itemView.findViewById(R.id.textContent);
            textComment = itemView.findViewById(R.id.textComment);
            textHeart = itemView.findViewById(R.id.textHeart);
            imgDot = itemView.findViewById(R.id.imgDot);
            fullheart = itemView.findViewById(R.id.fullheart);
            emptyheart = itemView.findViewById(R.id.emptyheart);
            LL_heart = itemView.findViewById(R.id.LL_heart);
            LL_comment = itemView.findViewById(R.id.LL_comment);


//            imgDot.setOnClickListener(this);
//            LL_comment.setOnClickListener(this);

        }


        public void setData(BoardInfo boardInfo) {
            this.boardInfo = boardInfo;


            Glide
                    .with(context)
                    .load(boardInfo.getPicture())
                    .centerCrop()
                    .thumbnail(0.1f) //10????????? ???????????? ???????????? ????????????.
                    .into(imgProfile);

            try {

                Date date = sdfNow.parse(boardInfo.getDate());

                if (calculateTime(date).equals("hi")) {
                    String day = boardInfo.getDate().substring(0, 10);
                    textTime.setText(day);
                } else {
                    textTime.setText(calculateTime(date));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


            textNickName.setText(boardInfo.getNickName());
            textDong.setText(boardInfo.getDong());
            textContent.setText(boardInfo.getMsg());
            textHeart.setText(boardInfo.getHeartNum());
            textComment.setText(boardInfo.getCommentNum());


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
            msg = diffTime + "??????";
        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
            // min
            System.out.println(diffTime);

            msg = diffTime + "??????";
        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
            // hour
            msg = (diffTime) + "?????????";
        }
//        else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY)
//        {
//            // day
//            msg = (diffTime ) + "??????";
//        }
//        else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH)
//        {
//            // day
//            msg = (diffTime ) + "??????";
//        }
        else {
//            msg = (diffTime) + "??????";
            msg = "hi";

        }

        return msg;
    }


}
