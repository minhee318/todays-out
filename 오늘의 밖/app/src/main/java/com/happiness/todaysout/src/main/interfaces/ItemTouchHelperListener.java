package com.happiness.todaysout.src.main.interfaces;

public interface ItemTouchHelperListener {
    boolean onItemMove(int from_position, int to_position);

    void onItemSwipe(int position);


}
