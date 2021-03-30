package com.softsquared.todaysout.src.main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.main.AddTownActivity;
import com.softsquared.todaysout.src.main.EditTownActivity;
import com.softsquared.todaysout.src.main.interfaces.ItemTouchHelperListener;
import com.softsquared.todaysout.src.main.models.AddressInfo;
import com.softsquared.todaysout.src.main.models.GuInfo;

import java.util.ArrayList;

public class EditAdapter extends RecyclerView.Adapter<EditAdapter.ViewHolder> {
    private ArrayList<AddressInfo> guList;
    private Context context;
    protected ItemListener rListener;
    String nickname;


    public EditAdapter(Context context, ArrayList<AddressInfo> guList,String nickname) {
        this.context = context;
        this.guList = guList;
        this.nickname = nickname;
        //rListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.my_town_item, parent, false);

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

        }


    }

    @Override
    public int getItemCount() {
        return guList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public LinearLayout btn_nowlocation;
        public TextView text_editGuName;
        public TextView text_editProfile;
        public TextView text_editNumber;
        public TextView text_first;
        public TextView text_second;


        AddressInfo addressInfo;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            btn_nowlocation = itemView.findViewById(R.id.btn_nowlocation);
            text_editGuName = itemView.findViewById(R.id.text_editGuName);
            text_editProfile = itemView.findViewById(R.id.text_editProfile);
            text_editNumber = itemView.findViewById(R.id.text_editNumber);
            text_first = itemView.findViewById(R.id.text_first);
            text_second = itemView.findViewById(R.id.text_second);

        }

        public void setData(AddressInfo addressInfo) {
            this.addressInfo = addressInfo;

            text_editGuName.setText(addressInfo.getGu());

            text_editProfile.setText(nickname);


            text_editNumber.setText(Integer.toString(getAdapterPosition()+1));
        }



        @Override
        public void onClick(View view) {
            if(rListener != null){
                rListener.onItemClick(addressInfo);
            }
        }
    }

    public interface ItemListener{
        void onItemClick(AddressInfo addressInfo);
    }
}
