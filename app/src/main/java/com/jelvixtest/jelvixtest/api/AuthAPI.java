package com.jelvixtest.jelvixtest.api;


import com.jelvixtest.jelvixtest.mvp.models.auth.AuthResponse;
import com.jelvixtest.jelvixtest.mvp.models.auth.login.SignInRequest;
import com.jelvixtest.jelvixtest.mvp.models.auth.signup.SignUpRequest;
import com.jelvixtest.jelvixtest.mvp.models.feed.FeedResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    /**
     * Get all users
     *
     * @param page requested page
     * @return {@link FeedResponse#getData()} list with users models
     */
    @GET("users")
    Observable<FeedResponse> getAllUsers(@Query("page") int page);
}
