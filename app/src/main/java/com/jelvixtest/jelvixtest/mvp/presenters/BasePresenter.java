package com.jelvixtest.jelvixtest.mvp.presenters;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void subscribeDisposable(@NonNull Disposable disposable){
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    protected String errorBodyToMessage(Throwable throwable){
        String errorMessage = null;
        if (throwable instanceof HttpException) {
            ResponseBody responseBody = ((HttpException)throwable).response().errorBody();
            try {
                errorMessage =  new JSONObject(responseBody.string()).optString("error");
            }catch (JSONException | IOException e){
                e.printStackTrace();
            }
        }else {
            errorMessage = throwable.getMessage();
        }
        return errorMessage;
    }
}
