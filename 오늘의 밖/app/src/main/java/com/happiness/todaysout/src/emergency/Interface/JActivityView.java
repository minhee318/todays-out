package com.happiness.todaysout.src.emergency.Interface;

import com.happiness.todaysout.src.emergency.models.JResponse;

public interface JActivityView {

    void validateJSuccess(JResponse response);

    void validateFailure(String message);
}
