package com.ccf.logic.view;

import com.ccf.android.presentation.login.LoginActivityPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import com.ccf.android.ui.base.BaseActivity;
import javax.inject.Inject;

@EActivity(resName = "login_activity_layout")
public class LoginActivity extends BaseActivity implements LoginFragmentListener, LoginActivityPresenter.LoginActivityView {
//    @FragmentById
//    LoginFragment_ login_fragment;
    @Inject
    LoginActivityPresenter presenter;

    @AfterViews
    public void init() {
        presenter.setView(this);
    }

    @Override
    public void onLoginCorrect() {
        presenter.onLoginCorrect();
    }

    @Override
    public void showMainActivity() {
       // Navigator.navigateToMain(this);
    }

    @Override
    public void onBackPressed() {
//        if (login_fragment.onBackPressed() == false)
//            super.onBackPressed();
    }
}
