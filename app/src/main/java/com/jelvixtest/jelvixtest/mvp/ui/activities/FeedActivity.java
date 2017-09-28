package com.jelvixtest.jelvixtest.mvp.ui.activities;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.jelvixtest.jelvixtest.R;
import com.jelvixtest.jelvixtest.mvp.ui.fragments.UsersFeedFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_feed)
public class FeedActivity extends MvpAppCompatActivity {
    private static final String LOG_TAG = "FeedActivity";
    @ViewById(R.id.feed_main_layout)
    LinearLayout mMainLayout;

    @AfterViews
    public void placeSignInFragment(){
        Log.d(LOG_TAG, "placeSignInFragment");
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_feed_container, UsersFeedFragment_.builder().build()).commit();
    }

    public void replaceFragment(MvpAppCompatFragment fragment){
        Log.d(LOG_TAG, "replaceFragment");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_feed_container, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        Log.d(LOG_TAG, "onBackPressed");
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(mMainLayout,
                message,
                Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorDefaultWhite));
        snackbar.show();
    }

}
