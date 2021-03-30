package com.happiness.todaysout.src.mypage.Interface;

import com.happiness.todaysout.src.mypage.model.MyDongResponse;
import com.happiness.todaysout.src.mypage.model.MyResponse;
import com.happiness.todaysout.src.mypage.model.PatchProfileResponse;
import com.happiness.todaysout.src.weather.models.ReportResponse;

public interface MyView {

    void validateMySuccess(MyResponse response);

    void validatePatchProfileSuccess(PatchProfileResponse response);

    void validateMyDongSuccess(MyDongResponse response);

    void validateMyDong2Success(MyDongResponse response);

    void validateMyPostDongSuccess(ReportResponse response);

    void validateMyPostDong2Success(ReportResponse response);

    void validateByeSuccess(ReportResponse response);

    void validateFailure(String message);
}