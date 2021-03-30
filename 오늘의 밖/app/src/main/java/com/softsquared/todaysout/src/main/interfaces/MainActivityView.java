package com.softsquared.todaysout.src.main.interfaces;

import com.softsquared.todaysout.src.main.models.AddressGetResponse;
import com.softsquared.todaysout.src.main.models.DisasterResponse;
import com.softsquared.todaysout.src.main.models.HomeWeatherResponse;
import com.softsquared.todaysout.src.main.models.MainPostResponse;
import com.softsquared.todaysout.src.main.models.MainResponse;

public interface MainActivityView {

    void validateHomeSuccess(MainResponse response);
    void validateDHomeSuccess(MainResponse response); //재난 게시판

    void validateDSuccess(DisasterResponse response);

    void validatePostSuccess(MainPostResponse response);

    void validateWeatherSuccess(HomeWeatherResponse response);

    void validateAddressSuccess(AddressGetResponse response);

    void validateFailure(String message);
}
