package com.ccf.presentation.login;

import android.graphics.Bitmap;

import com.ccf.android.presentation.login.LoginFragmentPresenter;
import com.ccf.presentation.login.factory.LoginUseCaseFactoryExceptionTest;
import com.ccf.presentation.login.factory.LoginUseCaseFactoryNullTest;
import com.ccf.presentation.login.factory.LoginUseCaseFactoryPictureExceptionOnlyTest;
import com.ccf.presentation.login.factory.LoginUseCaseFactorySuccessTest;
import com.ccf.android.presentation.login.standard.LoginFragmentPresenterImpl;
import com.ccf.android.presentation.login.standard.LoginUseCaseFactory;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LoginFragmentPresenterTest {
    private static final String LOGIN_NAME = "Sample login name";
    private static final String PASSWORD = "Sample password";

    @Mock
    LoginFragmentPresenter.LoginView view;

    private LoginFragmentPresenterImpl getLoginFragmentPresenter(LoginUseCaseFactory useCaseFactory) {
        LoginFragmentPresenterImpl presenter = new LoginFragmentPresenterImpl(useCaseFactory);
        presenter.setView(view);
        return presenter;
    }

    @Test
    public void testShouldSetBusyStateWhenNextButtonClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).setBusyState();
    }

    @Test
    public void testShouldShowErrorMessageWhenLoginNull() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(null);

        Mockito.verify(view).showIncorrectLoginNameMassage();
    }

    @Test
    public void testShouldShowErrorMessageWhenLoginEmpty() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked("");

        Mockito.verify(view).showIncorrectLoginNameMassage();
    }

    @Test
    public void testShouldShowErrorMessageWhenLoginSpaces() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked("   ");

        Mockito.verify(view).showIncorrectLoginNameMassage();
    }

    @Test
    public void testShouldShowNoUserMessageWhenReceivedUserIsNull() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showNoUserMessage();
    }

    @Test
    public void testShouldSetUserStateWhenReceivedUserIsNull() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showNoUserMessage();
    }

    @Test
    public void testShouldShowPasswordStateWhenUserReceived() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).setPasswordState(Matchers.any(String.class));
    }

    @Test
    public void testShouldShowUnknownExceptionMessageWhenUserResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showUnknownExceptionMessage(Matchers.any(Exception.class));
    }

    @Test
    public void testShouldSetUserStateWhenUserResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).setUserState();
    }

    @Test
    public void testShouldShowPicture() throws Exception {
        Mockito.when(view.isPasswordState()).thenReturn(true);
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).setUserPicture(Matchers.any(Bitmap.class));
    }

    @Test
    public void testShouldShowErrorMessageWhenUserPictureResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryPictureExceptionOnlyTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showUnknownExceptionMessage(Matchers.any(Exception.class));
    }

    @Test
    public void testShouldReturnFalseWhenIsNotPasswordState() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());
        Mockito.when(view.isPasswordState()).thenReturn(false);

        boolean result = presenter.backButtonClicked();

        assertFalse(result);
    }

    @Test
    public void testShouldReturnTrueWhenIsPasswordState() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());
        Mockito.when(view.isPasswordState()).thenReturn(true);

        boolean result = presenter.backButtonClicked();

        assertTrue(result);
    }

    @Test
    public void testShouldShowPasswordStateWhenInitUserReceived() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.init(LOGIN_NAME);

        Mockito.verify(view).setPasswordState(Matchers.any(String.class));
    }

    @Test
    public void testShouldShowUnknownExceptionMessageWhenInitUserResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.init(LOGIN_NAME);

        Mockito.verify(view).showUnknownExceptionMessage(Matchers.any(Exception.class));
    }

    @Test
    public void testShouldSetUserStateWhenInitUserResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.init(LOGIN_NAME);

        Mockito.verify(view).setUserState();
    }

    @Test
    public void testShouldSetBusyState() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).setBusyState();
    }
}
