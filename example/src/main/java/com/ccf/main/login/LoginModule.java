package com.ccf.main.login;

import android.app.Activity;

import com.ccf.android.presentation.login.LoginFragmentPresenter;
import com.ccf.android.presentation.login.standard.LoginFragmentPresenterImpl;
import com.ccf.android.view.login.LoginActivityListener;
import com.ccf.android.presentation.utils.impl.BitmapUtilsImpl;
import com.ccf.android.view.login.LoginActivity_;
import com.ccf.android.view.login.LoginFragment_;

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
