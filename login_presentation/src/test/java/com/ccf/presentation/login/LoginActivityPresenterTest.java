package com.ccf.presentation.login;

import com.ccf.android.presentation.login.LoginActivityPresenter;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginActivityPresenterTest {
    @Test
    public void testShouldShowMainActivityWhenLoginIsCorrect() {
        LoginActivityPresenter.LoginActivityView view = Mockito.mock(LoginActivityPresenter.LoginActivityView.class);
        LoginActivityPresenter presenter = new LoginActivityPresenter();
        presenter.setView(view);

        presenter.onLoginCorrect();

        Mockito.verify(view).showMainActivity();
    }
}
