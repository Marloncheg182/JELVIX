package com.jelvixtest.jelvixtest.mvp.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jelvixtest.jelvixtest.R;
import com.jelvixtest.jelvixtest.mvp.presenters.SignInPresenter;
import com.jelvixtest.jelvixtest.mvp.ui.activities.AuthActivity;
import com.jelvixtest.jelvixtest.mvp.ui.activities.FeedActivity_;
import com.jelvixtest.jelvixtest.mvp.ui.dialogs.JelvixProgressDialog;
import com.jelvixtest.jelvixtest.mvp.views.SignInView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_sign_in)
public class SignInFragment extends MvpAppCompatFragment implements SignInView {
    private static final String LOG_TAG = "SignInFragment";
    @ViewById(R.id.sign_in_email_et)
    TextInputEditText mEmailEt;
    @ViewById(R.id.sign_in_email_input_layout)
    TextInputLayout mEmailInputLayout;
    @ViewById(R.id.sign_in_password_et)
    TextInputEditText mPasswordEt;
    @ViewById(R.id.sign_in_password_input_layout)
    TextInputLayout mPasswordInputLayout;
    @ViewById(R.id.sign_in_register)
    TextView mSignUpBtn;
    @ViewById(R.id.sign_in_button)
    Button mSignIngBtn;
    private JelvixProgressDialog mProgressDialog;
    private AlertDialog mErrorDialog;
    private AuthActivity mActivity;

    @InjectPresenter
    SignInPresenter mSignInPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(LOG_TAG, "onAttach");
        mActivity = (AuthActivity) getActivity();
    }

    @AfterViews
    public void initProgressDialog() {
        Log.d(LOG_TAG, "initProgressDialog");
        mProgressDialog = new JelvixProgressDialog(getActivity(), R.style.JelvixProgressDialog);
    }

    @Click(R.id.sign_in_button)
    public void signIn() {
        Log.d(LOG_TAG, "signIn");
        mSignInPresenter.signIn(mEmailEt.getText().toString(), mPasswordEt.getText().toString());
    }

    @Click(R.id.sign_in_register)
    public void registerUser() {
        Log.d(LOG_TAG, "registerUser");
        mActivity.replaceFragment(SignUpFragment_.builder().build());
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
    public void startSignIn() {
        Log.d(LOG_TAG, "startSignIn");
        toggleProgress(true);
    }

    @Override
    public void finishSignIn() {
        Log.d(LOG_TAG, "finishSignIn");
        toggleProgress(false);
    }

    @Override
    public void failedSignIn(String message) {
        Log.d(LOG_TAG, "failedSignIn - " + message);
        mErrorDialog = new AlertDialog.Builder(mActivity, R.style.JelvixErrorDialog)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> dialog.dismiss())
                .setOnCancelListener(dialog -> mSignInPresenter.onErrorCancel())
                .show();
    }

    @Override
    public void hideError() {
        Log.d(LOG_TAG, "hideError");
        if (mErrorDialog != null && mErrorDialog.isShowing()){
            mErrorDialog.cancel();
        }
    }

    @Override
    public void hideFormError() {
        Log.d(LOG_TAG, "hideFormError");
        mEmailInputLayout.setError(null);
        mPasswordInputLayout.setError(null);
    }

    @Override
    public void showFormError(Integer emailError, Integer passwordError) {
        Log.d(LOG_TAG, "showFormError");
        mEmailInputLayout.setError(emailError == null ? null : getString(emailError));
        mPasswordInputLayout.setError(passwordError == null ? null : getString(passwordError));
    }

    @Override
    public void successSignIn() {
        Log.d(LOG_TAG, "successSignIn");
        FeedActivity_.intent(this).start();
    }
}
