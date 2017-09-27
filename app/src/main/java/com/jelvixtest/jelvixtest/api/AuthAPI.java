package com.jelvixtest.jelvixtest.api;


import com.jelvixtest.jelvixtest.mvp.models.auth.AuthResponse;
import com.jelvixtest.jelvixtest.mvp.models.auth.login.SignInRequest;
import com.jelvixtest.jelvixtest.mvp.models.auth.signup.SignUpRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPI {

    /**
     * Sign up request
     *
     * @param signUpRequest {@link SignUpRequest} sign up request body
     * @return {@link AuthResponse#getToken()} in app user token
     */

    @POST("register")
    Observable<AuthResponse> signUp(@Body SignUpRequest signUpRequest);


    /**
     * Sign in request
     *
     * @param signInRequest {@link SignUpRequest} sign in request body
     * @return {@link AuthResponse#getToken()} in app user token
     */

    @POST("login")
    Observable<AuthResponse> signIn(@Body SignInRequest signInRequest);

}
