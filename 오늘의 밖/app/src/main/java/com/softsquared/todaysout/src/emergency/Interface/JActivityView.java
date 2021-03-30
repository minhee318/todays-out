package com.softsquared.todaysout.src.emergency.Interface;

import com.softsquared.todaysout.src.emergency.models.JResponse;
import com.softsquared.todaysout.src.main.models.AddressGetResponse;
import com.softsquared.todaysout.src.main.models.HomeWeatherResponse;
import com.softsquared.todaysout.src.main.models.MainResponse;

public interface JActivityView {

    void validateJSuccess(JResponse response);

    void validateFailure(String message);
}
