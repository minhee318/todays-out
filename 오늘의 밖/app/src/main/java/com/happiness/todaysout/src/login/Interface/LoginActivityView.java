package com.happiness.todaysout.src.login.Interface;

import com.happiness.todaysout.src.login.models.LoginResponse;
import com.happiness.todaysout.src.login.models.SignInResponse;

public interface LoginActivityView {

    void validateKakaoSuccess(LoginResponse response);

    void validateAutoLoginSuccess(int code);

    void validateSignInSuccess(SignInResponse response);

    void validateFailure(String message);
}
