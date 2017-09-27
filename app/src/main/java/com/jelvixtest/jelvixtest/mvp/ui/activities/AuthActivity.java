package com.jelvixtest.jelvixtest.mvp.ui.activities;

import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.jelvixtest.jelvixtest.R;
import com.jelvixtest.jelvixtest.mvp.ui.fragments.SignInFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_auth)
public class AuthActivity extends MvpAppCompatActivity {
    private static final String LOG_TAG = "AuthActivity";

    @AfterViews
    public void placeSignInFragment(){
        Log.d(LOG_TAG, "placeSignInFragment");
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_auth_container, SignInFragment_.builder().build()).commit();
    }

    public void replaceFragment(MvpAppCompatFragment fragment){
        Log.d(LOG_TAG, "replaceFragment");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_auth_container, fragment).addToBackStack(null).commit();
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
}
