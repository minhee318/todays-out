package com.happiness.todaysout.src.weather.Adapter.Interface;

import com.happiness.todaysout.src.weather.models.ReportResponse;

public interface WeatherAdapterView {

    void validateDeleteSuccess(ReportResponse response);

    void validateDeleteCommentSuccess(ReportResponse response);

    void validatePostReportCommentSuccess(ReportResponse response);

    void validateFailure(String message);
}