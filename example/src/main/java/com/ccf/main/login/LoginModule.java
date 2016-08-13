package com.ccf.main.login;

import android.app.Activity;

import com.ccf.android.ui.utils.impl.BitmapUtilsImpl;
import com.ccf.android.view.login.activity.LoginActivityListener;
import com.ccf.android.view.login.activity.LoginActivity_;
import com.ccf.android.view.login.fragment.LoginFragment_;
import com.ccf.android.view.login.presenter.LoginFragmentPresenter;
import com.ccf.android.view.login.presenter.LoginFragmentPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module(injects = {LoginActivity_.class, LoginFragment_.class})
public class LoginModule {
    @Provides
    LoginFragmentPresenter provideLoginPresenter() {
        return new LoginFragmentPresenterImpl(new LoginUseCaseFactoryImpl(), new BitmapUtilsImpl());
    }

    @Provides
    LoginActivityListener provideStartPresenter() {
        return new LoginActivityListener() {
            @Override
            public void onLoginCorrect(Activity activity) {
                activity.finish();
            }
        };
    }
}
