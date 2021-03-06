package com.jelvixtest.jelvixtest.mvp.views;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SignInView extends MvpView{

    void startSignIn();

    void finishSignIn();

    void failedSignIn(String message);

    void hideError();

    void hideFormError();

    void showFormError(Integer emailError, Integer passwordError);

    @StateStrategyType(SkipStrategy.class)
    void successSignIn();
}
