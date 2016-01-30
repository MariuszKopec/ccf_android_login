package com.ccf.logic.view;

import com.ccf.android.presentation.login.LoginActivityPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import com.ccf.android.ui.base.BaseActivity;

import javax.inject.Inject;

@EActivity(resName = "login_activity_layout")
public class LoginActivity extends BaseActivity implements LoginFragmentListener {
    @FragmentById
    LoginFragment_ login_fragment;
    @Inject
    LoginActivityListener listener;

    @Override
    public void onLoginCorrect() {
        listener.onLoginCorrect(this);
    }

    @Override
    public void onBackPressed() {
        if (login_fragment.onBackPressed() == false)
            super.onBackPressed();
    }
}
