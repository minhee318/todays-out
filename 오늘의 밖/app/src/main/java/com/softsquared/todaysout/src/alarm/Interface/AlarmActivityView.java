package com.softsquared.todaysout.src.alarm.Interface;

import com.softsquared.todaysout.src.main.models.AddressGetResponse;
import com.softsquared.todaysout.src.main.models.HomeWeatherResponse;
import com.softsquared.todaysout.src.main.models.MainResponse;
import com.softsquared.todaysout.src.weather.models.ReportResponse;

public interface AlarmActivityView {

    void validateAlarmSuccess(ReportResponse response);

    void validateSwitchSuccess(ReportResponse response);

    void validateDongSuccess(AddressGetResponse response);

    void validateFailure(String message);
}
