package com.softsquared.todaysout.src.emergency.Adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.todaysout.src.emergency.models.ViewModel;

public class EmptyViewHolder extends RecyclerView.ViewHolder {
    public EmptyViewHolder(@NonNull View itemView) {
        super(itemView);

        initView(itemView);
    }

    public void initView(View v){

    }

    public void bind(ViewModel model){


    };


}
