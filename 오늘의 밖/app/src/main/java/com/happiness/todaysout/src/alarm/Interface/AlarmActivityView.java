package com.happiness.todaysout.src.alarm.Interface;

import com.happiness.todaysout.src.main.models.AddressGetResponse;
import com.happiness.todaysout.src.weather.models.ReportResponse;

public interface AlarmActivityView {

    void validateAlarmSuccess(ReportResponse response);

    void validateSwitchSuccess(ReportResponse response);

    void validateDongSuccess(AddressGetResponse response);

    void validateFailure(String message);
}
