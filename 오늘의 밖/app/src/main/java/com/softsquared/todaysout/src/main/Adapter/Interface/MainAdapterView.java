package com.softsquared.todaysout.src.main.Adapter.Interface;

import com.softsquared.todaysout.src.weather.models.ReportResponse;

public interface MainAdapterView {

    void validateDeleteGuSuccess(ReportResponse response);
    void validatePatchGuSuccess(ReportResponse response);

    void validateFailure(String message);
}