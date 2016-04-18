package com.ccf.presentation.login;

import android.graphics.Bitmap;

import com.ccf.android.presentation.login.LoginFragmentPresenter;
import com.ccf.android.presentation.login.standard.LoginFragmentPresenterImpl;
import com.ccf.android.presentation.login.standard.LoginUseCaseFactory;
import com.ccf.android.presentation.utils.BitmapUtils;
import com.ccf.presentation.login.factory.LoginUseCaseFactoryExceptionTest;
import com.ccf.presentation.login.factory.LoginUseCaseFactoryFailureTest;
import com.ccf.presentation.login.factory.LoginUseCaseFactoryNullTest;
import com.ccf.presentation.login.factory.LoginUseCaseFactoryPictureExceptionOnlyTest;
import com.ccf.presentation.login.factory.LoginUseCaseFactorySuccessTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginFragmentPresenterTest {
    private static final String LOGIN_NAME = "Sample login name";
    private static final String PASSWORD = "Sample password";

    @Mock
    LoginFragmentPresenter.LoginView view;

    @Mock
    BitmapUtils bitmapUtils;

    private LoginFragmentPresenterImpl getLoginFragmentPresenter(LoginUseCaseFactory useCaseFactory) {
        LoginFragmentPresenterImpl presenter = new LoginFragmentPresenterImpl(useCaseFactory, bitmapUtils);
        presenter.setView(view);
        return presenter;
    }

    @Test
    public void testShouldSetBusyStateWhenNextButtonClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).startProgressBar();
    }

    @Test
    public void testShouldDisableLoginWhenNextButtonClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).disableLoginEdit();
    }

    @Test
    public void testShouldDisableNextButtonWhenNextButtonClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).disableNextButton();
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
    public void testShouldEnableLoginEditWhenReceivedUserIsNull() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).enableLoginEdit();
    }

    @Test
    public void testShouldEnableNextButtonWhenReceivedUserIsNull() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).enableNextButton();
    }

    @Test
    public void testShouldShowUnknownExceptionMessageWhenUserResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showUnknownExceptionMessage(Matchers.any(Exception.class));
    }

    @Test
    public void testShouldEnableLoginEditWhenUserResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).enableLoginEdit();
    }

    @Test
    public void testShouldEnableNextButtonWhenUserResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).enableNextButton();
    }

    @Test
    public void testShouldStopProgressBarWhenUserReceived() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).stopProgressBar();
    }

    @Test
    public void testShouldClearPasswordWhenUserReceived() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).clearPassword();
    }

    @Test
    public void testShouldSetUserNameWhenUserReceived() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).setUserName(Matchers.any(String.class));
    }

    @Test
    public void testShouldSetDefaultUserPictureWhenUserReceived() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).setDefaultUserPicture();
    }

    @Test
    public void testShouldShowBackButtonWhenUserReceived() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showBackButton();
    }

    @Test
    public void testShouldHideLoginLayoutWhenUserReceived() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).hideLoginLayout();
    }

    @Test
    public void testShouldShowHideLoginLayoutWhenUserReceived() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).hideLoginLayout();
    }

    @Test
    public void testShouldShowPasswordLayoutWhenUserReceived() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showPasswordLayout();
    }

    @Test
    public void testShouldShowPicture() throws Exception {
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
    public void testShouldShowUnknownExceptionMessageWhenInitUserResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.init(LOGIN_NAME);

        Mockito.verify(view).showUnknownExceptionMessage(Matchers.any(Exception.class));
    }

    @Test
    public void testShouldEnableLoginEditWhenInitUserResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.init(LOGIN_NAME);

        Mockito.verify(view).enableLoginEdit();
    }

    @Test
    public void testShouldEnableNextButtonWhenInitUserResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.init(LOGIN_NAME);

        Mockito.verify(view).enableNextButton();
    }

    @Test
    public void testShouldDisablePasswordEditWhenLoginButtonHasClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).disablePasswordEdit();
    }

    @Test
    public void testShouldDisableLoginButtonWhenLoginButtonHasClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).disableLoginButton();
    }

    @Test
    public void testShouldStartProgressBarWhenLoginButtonHasClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).startProgressBar();
    }

    @Test
    public void testShouldDisableBackButtonWhenLoginButtonHasClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).disableBackButton();
    }

    @Test
    public void testShouldRaiseOnLoginCorrectWhenPasswordIsCorrect() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).onLoginCorrect();
    }

    @Test
    public void testShouldStopProgressBarWhenPasswordCheckResponseIsFalse() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryFailureTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).stopProgressBar();
    }

    @Test
    public void testShouldEnablePasswordEditTestWhenPasswordCheckResponseIsFalse() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryFailureTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enablePasswordEdit();
    }

    @Test
    public void testShouldEnableLoginButtonTestWhenPasswordCheckResponseIsFalse() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryFailureTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableLoginButton();
    }

    @Test
    public void testShouldEnableBackButtonTestWhenPasswordCheckResponseIsFalse() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryFailureTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableBackButton();
    }

    @Test
    public void testShouldStopProgressBarWhenPasswordCheckResponseIsNull() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).stopProgressBar();
    }

    @Test
    public void testShouldEnablePasswordEditTestWhenPasswordCheckResponseIsNull() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enablePasswordEdit();
    }

    @Test
    public void testShouldEnableLoginButtonTestWhenPasswordCheckResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableLoginButton();
    }

    @Test
    public void testShouldEnableBackButtonTestWhenPasswordCheckResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableBackButton();
    }

    @Test
    public void testShouldStopProgressBarWhenPasswordCheckResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).stopProgressBar();
    }

    @Test
    public void testShouldEnablePasswordEditTestWhenPasswordCheckResponseIsException() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enablePasswordEdit();
    }

    @Test
    public void testShouldEnableLoginButtonTestWhenPasswordCheckResponseIsNull() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableLoginButton();
    }

    @Test
    public void testShouldEnableBackButtonTestWhenPasswordCheckResponseIsNull() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableBackButton();
    }

    @Test
    public void testShouldHidePasswordLayoutWhenBackButtonClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).hidePasswordLayout();
    }

    @Test
    public void testShouldShowLoginLayoutWhenBackButtonClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).showLoginLayout();
    }

    @Test
    public void testShouldHideBackButtonWhenBackButtonClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).hideBackButton();
    }

    @Test
    public void testShouldEnableLoginEditWhenBackButtonClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableLoginEdit();
    }

    @Test
    public void testShouldEnableNextButtonWhenBackButtonClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableNextButton();
    }

    @Test
    public void testShouldSetDefaultUserPictureWhenBackButtonClicked() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).setDefaultUserPicture();
    }

    @Test
    public void testShouldSetDefaultUserPictureWhenInit() throws Exception {
        LoginFragmentPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.init(null);

        Mockito.verify(view).setDefaultUserPicture();
    }
}
