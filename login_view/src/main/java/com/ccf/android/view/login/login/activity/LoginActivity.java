package com.ccf.android.view.login.login.activity;


import com.ccf.android.ui.base.BaseActivity;
import com.ccf.android.view.login.login.fragment.LoginFragment;
import com.ccf.android.view.login.login.fragment.LoginFragmentListener;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import javax.inject.Inject;

@EActivity(resName = "activity_login")
public class LoginActivity extends BaseActivity implements LoginFragmentListener {
    @FragmentById
    LoginFragment login_fragment;
    @Inject
    LoginActivityListener listener;

    @Override
    public void onLoginCorrect() {
        listener.onLoginCorrect(this);
    }

//    @Override
//    public void onBackPressed() {
//        if (login_fragment.onBackPressed() == false)
//            super.onBackPressed();
//    }
}
