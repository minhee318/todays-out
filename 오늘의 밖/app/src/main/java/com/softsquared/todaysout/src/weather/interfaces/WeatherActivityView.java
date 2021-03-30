package com.softsquared.todaysout.src.weather.interfaces;

import com.softsquared.todaysout.src.main.models.HomeWeatherResponse;
import com.softsquared.todaysout.src.main.models.MainResponse;
import com.softsquared.todaysout.src.weather.models.BoardPostResponse;
import com.softsquared.todaysout.src.weather.models.BoardResponse;
import com.softsquared.todaysout.src.weather.models.CommentResponse;
import com.softsquared.todaysout.src.weather.models.DetailResponse;
import com.softsquared.todaysout.src.weather.models.DongResponse;
import com.softsquared.todaysout.src.weather.models.DustResponse;
import com.softsquared.todaysout.src.weather.models.HeartPostResponse;
import com.softsquared.todaysout.src.weather.models.NowResponse;
import com.softsquared.todaysout.src.weather.models.PatchResponse;
import com.softsquared.todaysout.src.weather.models.ReportResponse;
import com.softsquared.todaysout.src.weather.models.TodayResponse;
import com.softsquared.todaysout.src.weather.models.UpDownResponse;
import com.softsquared.todaysout.src.weather.models.WeekResponse;

public interface WeatherActivityView {

    void validateUpDownSuccess(UpDownResponse response);

    void validateDustSuccess(DustResponse response);

    void validateTodaySuccess(TodayResponse response);

    void validateDetailSuccess(DetailResponse response);

    void validateCommentSuccess(CommentResponse response);

    void validateHeartSuccess(HeartPostResponse response);

    void validateHeartGetSuccess(HeartPostResponse response);

    void validateContentSuccess(BoardPostResponse response);

    void validateReportSuccess(ReportResponse response);

    void validatePostCommentSuccess(ReportResponse response);

    void validatePatchSuccess(PatchResponse response);

    void validateDongSuccess(DongResponse response);


    void validateWeekSuccess(WeekResponse response);

    void validateHeartSuccess(BoardResponse response);

    void validateRegisterSuccess(ReportResponse response);


    void validateBoardSuccess(BoardResponse response);

    void validateWeatherSuccess(NowResponse response);

    void validateFailure(String message);
}
