package com.softsquared.todaysout.src.weather.Adapter.Interface;

import com.softsquared.todaysout.src.weather.models.ReportResponse;

public interface WeatherAdapterView {

    void validateDeleteSuccess(ReportResponse response);

    void validateDeleteCommentSuccess(ReportResponse response);

    void validatePostReportCommentSuccess(ReportResponse response);

    void validateFailure(String message);
}