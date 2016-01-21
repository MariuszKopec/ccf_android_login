package com.ccf.main.login;

import com.ccf.logic.view.LoginActivity_;
import com.ccf.logic.view.LoginFragment_;
import com.ccf.android.presentation.login.LoginActivityPresenter;
import com.ccf.android.presentation.login.LoginFragmentPresenter;
import com.ccf.android.presentation.login.standard.LoginFragmentPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module(injects = {LoginActivity_.class, LoginFragment_.class})
public class LoginModule {
    @Provides
    LoginFragmentPresenter provideLoginPresenter() {
        return new LoginFragmentPresenterImpl(new LoginUseCaseFactoryImpl());
    }

    @Provides
    LoginActivityPresenter provideStartPresenter() {
        return new LoginActivityPresenter();
    }
}
