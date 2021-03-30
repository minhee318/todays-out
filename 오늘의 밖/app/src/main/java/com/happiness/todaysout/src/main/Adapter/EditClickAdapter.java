package com.happiness.todaysout.src.main.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.main.Adapter.Interface.MainAdapterView;
import com.happiness.todaysout.src.main.interfaces.ItemTouchHelperListener;
import com.happiness.todaysout.src.main.models.AddressInfo;
import com.happiness.todaysout.src.main.models.GuInfo;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import java.util.ArrayList;

import static com.happiness.todaysout.src.ApplicationClass.FIRST_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class EditClickAdapter extends RecyclerView.Adapter<EditClickAdapter.ViewHolder> implements ItemTouchHelperListener, MainAdapterView {
    private ArrayList<AddressInfo> guList;
    private Context context;
   // protected ItemListener rListener;
    String nickname;



    public EditClickAdapter(Context context, ArrayList<AddressInfo> guList,String nickname) {
        this.context = context;
        this.guList = guList;
        this.nickname = nickname;
        //rListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.edit_town_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(guList.get(position));

        if(position == 0){
            holder.btn_nowlocation.setBackground(ContextCompat.getDrawable(context, R.drawable.nowlocation));
            holder.text_editGuName.setTextColor(Color.parseColor("#ffffff"));
            holder.text_first.setTextColor(Color.parseColor("#ffffff"));
            holder.text_second.setTextColor(Color.parseColor("#ffffff"));
            holder.text_editNumber.setTextColor(Color.parseColor("#ffffff"));
            holder.text_editProfile.setTextColor(Color.parseColor("#ffffff"));
            holder.img_edit.setImageResource(R.drawable.iconly_edit_white);
            holder.LL_firstItem.setVisibility(View.VISIBLE);
        }else{
            holder.LL_firstItem.setVisibility(View.GONE);
        }


       // guList.get(position).getAddressIdx();
    }

    private void tryDeleteGu(Long addressIdx) {
        Log.d("확인","삭제성공1");
        final MainAdapterService mainAdapterService = new MainAdapterService(this);
        mainAdapterService.deleteGu(addressIdx);
    }

    private void tryPatchGu() {
        final MainAdapterService mainAdapterService = new MainAdapterService(this);
        mainAdapterService.patchAddress();
    }

    @Override
    public int getItemCount() {
        return guList.size();
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        //이동할 객체 저장
        AddressInfo addressInfo = guList.get(from_position);
        //이동할 객체 삭제
        guList.remove(from_position);
        //이동하고 싶은 position에 추가
        guList.add(to_position,addressInfo);


        Log.d("확인","순서보기:"+guList.get(0).getAddressIdx()+guList.get(1).getAddressIdx());

        if(sSharedPreferences.getLong(FIRST_ADDRESSIDX, -1) != guList.get(0).getAddressIdx() ) {

            SharedPreferences.Editor editor = sSharedPreferences.edit();

            editor.putLong(FIRST_ADDRESSIDX, guList.get(0).getAddressIdx());

            editor.putLong(SECOND_ADDRESSIDX, guList.get(1).getAddressIdx());

            editor.apply();


            Log.d("확인", "addressIdx확인:" + guList.get(0).getAddressIdx() + "---" + guList.get(1).getAddressIdx());


            Log.d("확인", "addressIdx확인:" + sSharedPreferences.getLong(FIRST_ADDRESSIDX, -1) + "---" + sSharedPreferences.getLong(SECOND_ADDRESSIDX, -1));


            tryPatchGu();
        }


        //Adapter에 데이터 이동 알림
        notifyItemMoved(from_position,to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        Log.d("확인","삭제"+guList.get(position).getAddressIdx());


        tryDeleteGu(guList.get(position).getAddressIdx());


        guList.remove(position);
        notifyItemRemoved(position);

        Log.d("확인","사이즈:"+guList.size());



    }

    @Override
    public void validateDeleteGuSuccess(ReportResponse response) {

        Log.d("확인","삭제성공");
//
    }

    @Override
    public void validatePatchGuSuccess(ReportResponse response) {

        Log.d("확인","순서변경 성공");


    }

    @Override
    public void validateFailure(String message) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public LinearLayout btn_nowlocation;
        public TextView text_editGuName;
        public TextView text_editProfile;
        public TextView text_editNumber;
        public TextView text_first;
        public TextView text_second;
        public ImageView img_edit;
        public LinearLayout LL_firstItem;


        GuInfo guInfo;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            btn_nowlocation = itemView.findViewById(R.id.btn_nowlocation);
            text_editGuName = itemView.findViewById(R.id.text_editGuName);
            text_editProfile = itemView.findViewById(R.id.text_editProfile);
            text_editNumber = itemView.findViewById(R.id.text_editNumber);
            text_first = itemView.findViewById(R.id.text_first);
            text_second = itemView.findViewById(R.id.text_second);
            img_edit = itemView.findViewById(R.id.img_edit);
            LL_firstItem = itemView.findViewById(R.id.LL_firstItem);

        }

        public void setData(AddressInfo addressInfo) {
            this.guInfo = guInfo;

            text_editGuName.setText(addressInfo.getGu());

            text_editProfile.setText(nickname);


            text_editNumber.setText(Integer.toString(getAdapterPosition()+1));


        }



        @Override
        public void onClick(View view) {
//            if(rListener != null){
//                rListener.onItemClick(addressInfo);
//            }
        }
    }

//    public interface ItemListener{
//        void onItemClick(AddressInfo addressInfo);
//    }
}
