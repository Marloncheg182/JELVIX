package com.jelvixtest.jelvixtest.mvp.ui.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jelvixtest.jelvixtest.R;
import com.jelvixtest.jelvixtest.mvp.models.feed.Data;
import com.jelvixtest.jelvixtest.mvp.models.feed.FeedResponse;
import com.jelvixtest.jelvixtest.mvp.presenters.FeedPresenter;
import com.jelvixtest.jelvixtest.mvp.ui.activities.FeedActivity;
import com.jelvixtest.jelvixtest.mvp.ui.adapters.EndlessScrollListener;
import com.jelvixtest.jelvixtest.mvp.ui.adapters.UsersAdapter;
import com.jelvixtest.jelvixtest.mvp.ui.common.VerticalSpaceItemDecoration;
import com.jelvixtest.jelvixtest.mvp.ui.dialogs.JelvixProgressDialog;
import com.jelvixtest.jelvixtest.mvp.views.UsersFeedView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_users_feed)
public class UsersFeedFragment extends MvpAppCompatFragment implements UsersFeedView {
    public static final String LOG_TAG = "UsersFeedFragment";
    @ViewById(R.id.feed_logout)
    ImageButton mFeedLogoutBtn;
    @ViewById(R.id.feed_recycler_view)
    RecyclerView mUsersRecyclerView;
    private FeedActivity mActivity;
    private LinearLayoutManager mLayoutManager;
    private JelvixProgressDialog mProgressDialog;
    private UsersAdapter mUsersAdapter;

    @InjectPresenter
    FeedPresenter mFeedPresenter;

    @Override
    public void onAttach(Context context) {
        Log.d(LOG_TAG, "onAttach");
        mActivity = (FeedActivity) getActivity();
        super.onAttach(context);
    }

    @AfterViews
    public void getAllUsers() {
        Log.d(LOG_TAG, "getAllUsers");
        mProgressDialog = new JelvixProgressDialog(getActivity(), R.style.JelvixProgressDialog);
        mLayoutManager = new LinearLayoutManager(getContext());
        mUsersRecyclerView.setLayoutManager(mLayoutManager);
        mUsersRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));
        mFeedPresenter.getUsersFeed(1);
    }

    @Click(R.id.feed_logout)
    public void actionLogout() {
        Log.d(LOG_TAG, "actionBack");
        mActivity.finish();
    }

    @Override
    public void startLoadFeed() {
        Log.d(LOG_TAG, "startLoadFeed");
        toggleProgress(true);
    }

    private void toggleProgress(final boolean isShown) {
        Log.d(LOG_TAG, "toggleProgress - " + String.valueOf(isShown));
        if (isShown) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void finishLoadFeed() {
        Log.d(LOG_TAG, "finishLoadFeed");
        toggleProgress(false);
    }

    @Override
    public void failedLoadFeed(String message) {
        Log.d(LOG_TAG, "failedLoadFeed");
        mActivity.showSnackBar(message);
    }

    @Override
    public void successLoadFeed(FeedResponse feedResponse) {
        Log.d(LOG_TAG, "successLoadFeed");
        mUsersAdapter = new UsersAdapter(feedResponse.getData(), getContext(), new UsersAdapter.UserSelectListener() {
            @Override
            public void selectedUser(Data user) {
                UserInfoFragment fragment = UserInfoFragment_.builder().userData(user).build();
                mActivity.replaceFragment(fragment);
            }
        });
        mUsersRecyclerView.setAdapter(mUsersAdapter);

        mUsersRecyclerView.addOnScrollListener(new EndlessScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mFeedPresenter.updateFeed(page);
            }
        });
    }

    @Override
    public void successUpdateFeed(FeedResponse feedResponse) {
        mUsersAdapter.insert(feedResponse);
    }
}
