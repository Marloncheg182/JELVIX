package com.jelvixtest.jelvixtest.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.jelvixtest.jelvixtest.mvp.models.feed.FeedResponse;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface UsersFeedView extends MvpView{

    void startLoadFeed();

    void finishLoadFeed();

    void failedLoadFeed(String message);

    @StateStrategyType(SkipStrategy.class)
    void successLoadFeed(FeedResponse feedResponse);

    void successUpdateFeed(FeedResponse feedResponse);
}
