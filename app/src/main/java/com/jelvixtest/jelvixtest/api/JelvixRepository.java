package com.jelvixtest.jelvixtest.api;

import com.jelvixtest.jelvixtest.mvp.models.auth.AuthResponse;
import com.jelvixtest.jelvixtest.mvp.models.auth.login.SignInRequest;
import com.jelvixtest.jelvixtest.mvp.models.auth.signup.SignUpRequest;

import io.reactivex.Observable;

public class JelvixRepository {

    private AuthAPI mAuthAPI;

    public JelvixRepository(AuthAPI authAPI){
        mAuthAPI = authAPI;
    }

    public Observable<AuthResponse> signIn(SignInRequest signInRequest){
        return mAuthAPI.signIn(signInRequest);
    }

    public Observable<AuthResponse> signUp(SignUpRequest signUpRequest){
        return mAuthAPI.signUp(signUpRequest);
    }
}
