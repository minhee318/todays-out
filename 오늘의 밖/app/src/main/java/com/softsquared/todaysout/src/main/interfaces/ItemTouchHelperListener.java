package com.softsquared.todaysout.src.main.interfaces;

import androidx.recyclerview.widget.RecyclerView;

public interface ItemTouchHelperListener {
    boolean onItemMove(int from_position, int to_position);

    void onItemSwipe(int position);


}
