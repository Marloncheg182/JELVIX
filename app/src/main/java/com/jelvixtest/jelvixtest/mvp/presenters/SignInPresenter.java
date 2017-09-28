package com.jelvixtest.jelvixtest.mvp.presenters;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.jelvixtest.jelvixtest.R;
import com.jelvixtest.jelvixtest.api.JelvixRepository;
import com.jelvixtest.jelvixtest.application.JelvixApplication;
import com.jelvixtest.jelvixtest.common.SchedulersUtils;
import com.jelvixtest.jelvixtest.mvp.models.auth.login.SignInRequest;
import com.jelvixtest.jelvixtest.mvp.views.SignInView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

@InjectViewState
public class SignInPresenter extends BasePresenter<SignInView> {

    @Inject
    JelvixRepository mJelvixRepository;

    public SignInPresenter() {
        JelvixApplication.getAuthComponent().inject(this);
    }

    public void signIn(String email, String password) {
        Integer emailError = null;
        Integer passwordError = null;

        getViewState().hideFormError();

        if (TextUtils.isEmpty(email)) {
            emailError = R.string.empty_email_error;
        }
        if (TextUtils.isEmpty(password)) {
            passwordError = R.string.empty_password_error;
        }

        if (emailError != null || passwordError != null) {
            getViewState().showFormError(emailError, passwordError);
            return;
        }

        getViewState().startSignIn();

        Disposable disposable = mJelvixRepository.signIn(new SignInRequest(email, password))
                .compose(SchedulersUtils.applySchedulers())
                .subscribe(authResponse -> {
                    getViewState().finishSignIn();
                    getViewState().successSignIn();
                }, throwable -> {
                    getViewState().finishSignIn();
                    getViewState().failedSignIn(errorBodyToMessage(throwable));
                });

        subscribeDisposable(disposable);
    }

    public void onErrorCancel(){
        getViewState().hideError();
    }
}
