package com.ccf.android.view.login.presenter;

import android.graphics.Bitmap;

import com.ccf.android.ui.utils.BitmapUtils;
import com.ccf.android.view.login.login.presenter.LoginPresenter;
import com.ccf.android.view.login.login.presenter.LoginPresenterImpl;
import com.ccf.android.view.login.login.presenter.LoginUseCaseFactory;
import com.ccf.android.view.login.presenter.factory.LoginUseCaseFactoryExceptionTest;
import com.ccf.android.view.login.presenter.factory.LoginUseCaseFactoryFailureTest;
import com.ccf.android.view.login.presenter.factory.LoginUseCaseFactoryNullTest;
import com.ccf.android.view.login.presenter.factory.LoginUseCaseFactoryPictureExceptionOnlyTest;
import com.ccf.android.view.login.presenter.factory.LoginUseCaseFactorySuccessTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    private static final String LOGIN_NAME = "Sample login name";
    private static final String PASSWORD = "Sample password";

    @Mock
    LoginPresenter.LoginView view;

    @Mock
    BitmapUtils bitmapUtils;

    private LoginPresenterImpl getLoginFragmentPresenter(LoginUseCaseFactory useCaseFactory) {
        LoginPresenterImpl presenter = new LoginPresenterImpl(useCaseFactory, bitmapUtils);
        presenter.setView(view);
        return presenter;
    }

    @Test
    public void testShouldShowLoginCardWhenInit() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.init(null);

        Mockito.verify(view).showLoginCard();
    }

    @Test
    public void testShouldSetBusyStateWhenNextButtonClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).startProgressBar();
    }

    @Test
    public void testShouldDisableLoginWhenNextButtonClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).disableLoginEdit();
    }

    @Test
    public void testShouldDisableNextButtonWhenNextButtonClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).disableNextButton();
    }

    @Test
    public void testShouldShowErrorMessageWhenLoginNull() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(null);

        Mockito.verify(view).showIncorrectLoginNameMassage();
    }

    @Test
    public void testShouldShowErrorMessageWhenLoginEmpty() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked("");

        Mockito.verify(view).showIncorrectLoginNameMassage();
    }

    @Test
    public void testShouldShowErrorMessageWhenLoginSpaces() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked("   ");

        Mockito.verify(view).showIncorrectLoginNameMassage();
    }

    @Test
    public void testShouldShowNoUserMessageWhenReceivedUserIsNull() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showNoUserMessage();
    }

    @Test
    public void testShouldEnableLoginEditWhenReceivedUserIsNull() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).enableLoginEdit();
    }

    @Test
    public void testShouldEnableNextButtonWhenReceivedUserIsNull() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).enableNextButton();
    }

    @Test
    public void testShouldShowUnknownExceptionMessageWhenUserResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showUnknownExceptionMessage(Matchers.any(Exception.class));
    }

    @Test
    public void testShouldEnableLoginEditWhenUserResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).enableLoginEdit();
    }

    @Test
    public void testShouldEnableNextButtonWhenUserResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).enableNextButton();
    }

    @Test
    public void testShouldStopProgressBarWhenUserReceived() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).stopProgressBar();
    }

    @Test
    public void testShouldClearPasswordWhenUserReceived() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).clearPassword();
    }

    @Test
    public void testShouldSetUserNameWhenUserReceived() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).setUserName(Matchers.any(String.class));
    }

    @Test
    public void testShouldSetUserPictureWhenUserReceived() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).setUserPicture(null);
    }

    @Test
    public void testShouldShowBackButtonWhenUserReceived() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showBackButton();
    }

    @Test
    public void testShouldHideLoginLayoutWhenUserReceived() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).hideLoginLayout();
    }

    @Test
    public void testShouldShowHideLoginLayoutWhenUserReceived() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).hideLoginLayout();
    }

    @Test
    public void testShouldShowPasswordLayoutWhenUserReceived() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showPasswordLayout();
    }

    @Test
    public void testShouldShowPicture() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).setUserPicture(Matchers.any(Bitmap.class));
    }

    @Test
    public void testShouldShowErrorMessageWhenUserPictureResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryPictureExceptionOnlyTest());

        presenter.nextButtonClicked(LOGIN_NAME);

        Mockito.verify(view).showUnknownExceptionMessage(Matchers.any(Exception.class));
    }

    @Test
    public void testShouldShowUnknownExceptionMessageWhenInitUserResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.init(LOGIN_NAME);

        Mockito.verify(view).showUnknownExceptionMessage(Matchers.any(Exception.class));
    }

    @Test
    public void testShouldEnableLoginEditWhenInitUserResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.init(LOGIN_NAME);

        Mockito.verify(view).enableLoginEdit();
    }

    @Test
    public void testShouldEnableNextButtonWhenInitUserResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.init(LOGIN_NAME);

        Mockito.verify(view).enableNextButton();
    }

    @Test
    public void testShouldDisablePasswordEditWhenLoginButtonHasClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).disablePasswordEdit();
    }

    @Test
    public void testShouldDisableLoginButtonWhenLoginButtonHasClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).disableLoginButton();
    }

    @Test
    public void testShouldStartProgressBarWhenLoginButtonHasClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).startProgressBar();
    }

    @Test
    public void testShouldDisableBackButtonWhenLoginButtonHasClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).disableBackButton();
    }

    @Test
    public void testShouldHideLoginCardWhenPasswordIsCorrect() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).hideLoginCard();
    }

    @Test
    public void testShouldRaiseOnLoginCorrectWhenPasswordIsCorrect() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).onLoginCorrect();
    }

    @Test
    public void testShouldStopProgressBarWhenPasswordCheckResponseIsFalse() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryFailureTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).stopProgressBar();
    }

    @Test
    public void testShouldEnablePasswordEditTestWhenPasswordCheckResponseIsFalse() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryFailureTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enablePasswordEdit();
    }

    @Test
    public void testShouldEnableLoginButtonTestWhenPasswordCheckResponseIsFalse() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryFailureTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableLoginButton();
    }

    @Test
    public void testShouldEnableBackButtonTestWhenPasswordCheckResponseIsFalse() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryFailureTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableBackButton();
    }

    @Test
    public void testShouldStopProgressBarWhenPasswordCheckResponseIsNull() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).stopProgressBar();
    }

    @Test
    public void testShouldEnablePasswordEditTestWhenPasswordCheckResponseIsNull() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enablePasswordEdit();
    }

    @Test
    public void testShouldEnableLoginButtonWhenPasswordCheckResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableLoginButton();
    }

    @Test
    public void testShouldEnableBackButtonTestWhenPasswordCheckResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableBackButton();
    }

    @Test
    public void testShouldStopProgressBarWhenPasswordCheckResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).stopProgressBar();
    }

    @Test
    public void testShouldEnablePasswordEditTestWhenPasswordCheckResponseIsException() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryExceptionTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enablePasswordEdit();
    }

    @Test
    public void testShouldEnableLoginButtonTestWhenPasswordCheckResponseIsNull() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableLoginButton();
    }

    @Test
    public void testShouldEnableBackButtonTestWhenPasswordCheckResponseIsNull() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.loginButtonClicked(LOGIN_NAME, PASSWORD);

        Mockito.verify(view).enableBackButton();
    }

    @Test
    public void testShouldHidePasswordLayoutWhenBackButtonClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.backButtonClicked();

        Mockito.verify(view).hidePasswordLayout();
    }

    @Test
    public void testShouldShowLoginLayoutWhenBackButtonClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.backButtonClicked();

        Mockito.verify(view).showLoginLayout();
    }

    @Test
    public void testShouldHideBackButtonWhenBackButtonClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.backButtonClicked();

        Mockito.verify(view).hideBackButton();
    }

    @Test
    public void testShouldEnableLoginEditWhenBackButtonClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.backButtonClicked();

        Mockito.verify(view).enableLoginEdit();
    }

    @Test
    public void testShouldEnableNextButtonWhenBackButtonClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.backButtonClicked();

        Mockito.verify(view).enableNextButton();
    }

    @Test
    public void testShouldSetDefaultUserPictureWhenBackButtonClicked() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactoryNullTest());

        presenter.backButtonClicked();

        Mockito.verify(view).setDefaultUserPicture();
    }

    @Test
    public void testShouldSetDefaultUserPictureWhenInit() throws Exception {
        LoginPresenterImpl presenter = getLoginFragmentPresenter(new LoginUseCaseFactorySuccessTest());

        presenter.init(null);

        Mockito.verify(view).setDefaultUserPicture();
    }
}
