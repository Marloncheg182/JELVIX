package com.jelvixtest.jelvixtest.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.jelvixtest.jelvixtest.api.JelvixRepository;
import com.jelvixtest.jelvixtest.application.JelvixApplication;
import com.jelvixtest.jelvixtest.common.SchedulersUtils;
import com.jelvixtest.jelvixtest.mvp.views.UsersFeedView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class FeedPresenter extends BasePresenter<UsersFeedView> {

    @Inject
    JelvixRepository mJelvixRepository;

    public FeedPresenter() {
        JelvixApplication.getAuthComponent().inject(this);
    }

    public void getUsersFeed(int page) {

        getViewState().startLoadFeed();

        Disposable disposable = mJelvixRepository.getAllUsers(page).cache()
                .compose(SchedulersUtils.applySchedulers())
                .subscribe(feedResponse -> {
                    getViewState().finishLoadFeed();
                    getViewState().successLoadFeed(feedResponse);
                }, throwable -> {
                    getViewState().finishLoadFeed();
                    getViewState().failedLoadFeed(errorBodyToMessage(throwable));
                });
        subscribeDisposable(disposable);
    }

    public void updateFeed(int page) {
        Disposable disposable = mJelvixRepository.getAllUsers(page).cache()
                .compose(SchedulersUtils.applySchedulers())
                .subscribe(feedResponse -> {
                    getViewState().finishLoadFeed();
                    getViewState().successUpdateFeed(feedResponse);
                }, throwable -> {
                    getViewState().finishLoadFeed();
                    getViewState().failedLoadFeed(errorBodyToMessage(throwable));
                });
        subscribeDisposable(disposable);
    }
}