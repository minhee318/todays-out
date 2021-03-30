package com.happiness.todaysout.src.weather.interfaces;

import com.happiness.todaysout.src.weather.models.BoardPostResponse;
import com.happiness.todaysout.src.weather.models.BoardResponse;
import com.happiness.todaysout.src.weather.models.CommentResponse;
import com.happiness.todaysout.src.weather.models.DetailResponse;
import com.happiness.todaysout.src.weather.models.DongResponse;
import com.happiness.todaysout.src.weather.models.DustResponse;
import com.happiness.todaysout.src.weather.models.HeartPostResponse;
import com.happiness.todaysout.src.weather.models.NowResponse;
import com.happiness.todaysout.src.weather.models.PatchResponse;
import com.happiness.todaysout.src.weather.models.ReportResponse;
import com.happiness.todaysout.src.weather.models.TodayResponse;
import com.happiness.todaysout.src.weather.models.UpDownResponse;
import com.happiness.todaysout.src.weather.models.WeekResponse;

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
