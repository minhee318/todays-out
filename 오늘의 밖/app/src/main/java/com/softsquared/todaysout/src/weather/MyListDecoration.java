package com.softsquared.todaysout.src.weather;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MyListDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            //기본 아이템은 공백이 없는데 마지막이 아닌 경우 공백을 30
            outRect.right = 30;
        }
    }
}
