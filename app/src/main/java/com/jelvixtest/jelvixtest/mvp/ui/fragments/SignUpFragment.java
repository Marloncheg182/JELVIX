package com.jelvixtest.jelvixtest.mvp.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jelvixtest.jelvixtest.R;
import com.jelvixtest.jelvixtest.mvp.presenters.SignUpPresenter;
import com.jelvixtest.jelvixtest.mvp.ui.activities.AuthActivity;
import com.jelvixtest.jelvixtest.mvp.ui.activities.FeedActivity_;
import com.jelvixtest.jelvixtest.mvp.ui.dialogs.JelvixProgressDialog;
import com.jelvixtest.jelvixtest.mvp.views.SignUpView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Oleg Romanenchuk on 27.09.2017.
 */

@EFragment(R.layout.fragment_sign_up)
public class SignUpFragment extends MvpAppCompatFragment implements SignUpView{
    public static final String LOG_TAG = "SignUpFragment";
    @ViewById(R.id.sign_up_email_et)
    TextInputEditText mEmailEt;
    @ViewById(R.id.sign_up_email_input_layout)
    TextInputLayout mEmailInputLayout;
    @ViewById(R.id.sign_up_password_et)
    TextInputEditText mPasswordEt;
    @ViewById(R.id.sign_up_password_input_layout)
    TextInputLayout mPasswordInputLayout;
    @ViewById(R.id.sign_up_button)
    Button mSignUpBtn;
    @ViewById(R.id.sign_up_back_btn)
    ImageButton mBackBtn;
    private JelvixProgressDialog mProgressDialog;
    private AlertDialog mErrorDialog;
    private AuthActivity mActivity;

    @InjectPresenter
    SignUpPresenter mSignUpPresenter;

    @Override
    public void onAttach(Context context) {
        Log.d(LOG_TAG, "onAttach");
        mActivity = (AuthActivity) getActivity();
        super.onAttach(context);
    }

    @AfterViews
    public void initProgressDialog(){
        Log.d(LOG_TAG, "initProgressDialog");
        mProgressDialog = new JelvixProgressDialog(getActivity(), R.style.JelvixProgressDialog);
    }

    private void toggleProgress(final boolean isShown) {
        Log.d(LOG_TAG, "toggleProgress - " + String.valueOf(isShown));
        if (isShown) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    @Click(R.id.sign_up_back_btn)
    public void actionBack(){
        Log.d(LOG_TAG, "actionBack");
        mActivity.onBackPressed();
    }

    @Click(R.id.sign_up_button)
    public void registerUser() {
        Log.d(LOG_TAG, "registerUser");
        mSignUpPresenter.signUp(mEmailEt.getText().toString(), mPasswordEt.getText().toString());
    }

    @Override
    public void startSignUp() {
        Log.d(LOG_TAG, "startSignUp");
        toggleProgress(true);
    }

    @Override
    public void finishSignUp() {
        Log.d(LOG_TAG, "finishSignUp");
        toggleProgress(false);
    }

    @Override
    public void failedSignUp(String message) {
        Log.d(LOG_TAG, "failedSignUp - " + message);
        mErrorDialog = new AlertDialog.Builder(mActivity, R.style.JelvixErrorDialog)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> dialog.dismiss())
                .setOnCancelListener(dialog -> mSignUpPresenter.onErrorCancel())
                .show();
    }

    @Override
    public void hideFormError() {
        Log.d(LOG_TAG, "hideFormError");
        mEmailInputLayout.setError(null);
        mPasswordInputLayout.setError(null);
    }

    @Override
    public void hideError() {
        Log.d(LOG_TAG, "hideError");
        if (mErrorDialog != null && mErrorDialog.isShowing()) {
            mErrorDialog.cancel();
        }
    }

    @Override
    public void showFormError(Integer emailError, Integer passwordError) {
        Log.d(LOG_TAG, "showFormError");
        mEmailInputLayout.setError(emailError == null ? null : getString(emailError));
        mPasswordInputLayout.setError(passwordError == null ? null : getString(passwordError));
    }

    @Override
    public void successSignUp() {
        Log.d(LOG_TAG, "successSignUp");
        FeedActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK).start();
    }
}
