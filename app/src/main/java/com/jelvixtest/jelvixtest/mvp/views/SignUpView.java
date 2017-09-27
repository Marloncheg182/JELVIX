package com.jelvixtest.jelvixtest.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Oleg Romanenchuk on 27.09.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SignUpView extends MvpView{

    void startSignUp();

    void finishSignUp();

    void failedSignUp(String message);

    void hideFormError();

    void hideError();

    void showFormError(Integer emailError, Integer passwordError);

    @StateStrategyType(SkipStrategy.class)
    void successSignUp();

}
