package com.jelvixtest.jelvixtest.mvp.ui.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jelvixtest.jelvixtest.R;
import com.jelvixtest.jelvixtest.mvp.models.feed.Data;
import com.jelvixtest.jelvixtest.mvp.ui.activities.FeedActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_user_info)
public class UserInfoFragment extends MvpAppCompatFragment {
    public static final String LOG_TAG = "UserInfoFragment";

    private FeedActivity mActivity;
    @FragmentArg
    Data userData;
    @ViewById(R.id.user_info_back_btn)
    Button mUserInfoBack;
    @ViewById(R.id.user_name_text_view)
    TextView mUserName;
    @ViewById(R.id.user_avatar_image_view)
    ImageView mAvaterView;
    @ViewById(R.id.avatar_progress_bar)
    ProgressBar mPreloader;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(LOG_TAG, "onAttach");
        mActivity = (FeedActivity) getActivity();
    }

    @AfterViews
    public void loadUser(){
        Log.d(LOG_TAG, "loadUser");
        Glide.with(getContext()).load(userData.getAvatar()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                mPreloader.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                mPreloader.setVisibility(View.GONE);
                return false;
            }
        }).apply(RequestOptions.circleCropTransform()).into(mAvaterView);
        mUserName.setText(userData.getFirst_name() + " " + userData.getLast_name());
    }

    @Click(R.id.user_info_back_btn)
    public void actionBack(){
        Log.d(LOG_TAG, "actionBack");
        mActivity.onBackPressed();
    }
}
