package com.ccf.logic.view;


import com.ccf.android.ui.base.BaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import javax.inject.Inject;

@EActivity(resName = "login_activity_layout")
public class LoginActivity extends BaseActivity implements LoginFragmentListener {
    @FragmentById
    LoginFragment login_fragment;
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
