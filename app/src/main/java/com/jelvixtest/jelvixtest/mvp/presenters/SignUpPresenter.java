package com.jelvixtest.jelvixtest.mvp.presenters;


import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.jelvixtest.jelvixtest.R;
import com.jelvixtest.jelvixtest.api.JelvixRepository;
import com.jelvixtest.jelvixtest.application.JelvixApplication;
import com.jelvixtest.jelvixtest.common.SchedulersUtils;
import com.jelvixtest.jelvixtest.mvp.models.auth.signup.SignUpRequest;
import com.jelvixtest.jelvixtest.mvp.views.SignUpView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class SignUpPresenter extends BasePresenter<SignUpView> {

    @Inject
    JelvixRepository mJelvixRepository;

    public SignUpPresenter(){
        JelvixApplication.getAuthComponent().inject(this);
    }

    public void signUp(String email, String password){
        Integer emailError = null;
        Integer passwordError = null;

        getViewState().hideFormError();

        if (TextUtils.isEmpty(email)){
            emailError = R.string.empty_email_error;
        }

        if (TextUtils.isEmpty(password)){
            passwordError = R.string.empty_password_error;
        }

        if (emailError != null || passwordError != null){
            getViewState().showFormError(emailError, passwordError);
            return;
        }

        getViewState().startSignUp();

        Disposable disposable = mJelvixRepository.signUp(new SignUpRequest(email, password))
                .compose(SchedulersUtils.applySchedulers())
                .subscribe(authResponse -> {
                    getViewState().finishSignUp();
                    getViewState().successSignUp();
                },throwable -> {
                    getViewState().finishSignUp();
                    getViewState().failedSignUp(errorBodyToMessage(throwable));
                });
        subscribeDisposable(disposable);
    }

    public void onErrorCancel(){
        getViewState().hideError();
    }
}
