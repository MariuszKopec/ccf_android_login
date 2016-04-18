package com.ccf.android.presentation.login.standard;

import android.graphics.Bitmap;
import android.util.Log;

import com.ccf.android.presentation.base.BasePresenter;
import com.ccf.android.presentation.login.LoginFragmentPresenter;
import com.ccf.android.presentation.utils.BitmapUtils;
import com.ccf.logic.interactor.DefaultSubscriber;
import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.Picture;
import com.ccf.logic.login.User;

public class LoginFragmentPresenterImpl extends BasePresenter implements LoginFragmentPresenter {
    private final LoginUseCaseFactory useCaseFactory;
    private final BitmapUtils bitmapUtils;
    private LoginFragmentPresenter.LoginView view;

    public LoginFragmentPresenterImpl(LoginUseCaseFactory useCaseFactory, BitmapUtils bitmapUtils) {
        this.useCaseFactory = useCaseFactory;
        this.bitmapUtils = bitmapUtils;
    }

    @Override
    public void setView(LoginFragmentPresenter.LoginView view) {
        this.view = view;
    }

    @Override
    public void init(Object context) {
        getUserFromInit(context);
        view.setDefaultUserPicture();
    }

    private void getUserFromInit(Object context) {
        UseCase useCase = useCaseFactory.getInitUseCase(context);
        useCase.execute(new GetUserFromInitSubscriber());
        registerUseCase(useCase);
    }

    @Override
    public void nextButtonClicked(String login) {
        unsubscribe();
        if (login == null || login.trim().length() == 0)
            view.showIncorrectLoginNameMassage();
        else {
            view.startProgressBar();
            view.disableNextButton();
            view.disableLoginEdit();
            getUser(login);
        }
    }

    private void getUser(String login) {
        UseCase useCase = useCaseFactory.getUserUseCase(login);
        useCase.execute(new GetUserSubscriber());
        registerUseCase(useCase);
    }

    private void onUserReceived(User user) {
        if (user == null) {
            view.showNoUserMessage();
            view.enableLoginEdit();
            view.enableNextButton();
        } else {
            view.stopProgressBar();
            view.clearPassword();
            view.setUserName(user.getName() + " " + user.getLastName());
            view.showBackButton();
            view.hideLoginLayout();
            view.showPasswordLayout();
            getUserPicture(user.getLogin());
        }
    }

    private void getUserPicture(String login) {
        UseCase useCase = useCaseFactory.getPictureUseCase(login);
        useCase.execute(new GetUserPictureSubscriber());
        registerUseCase(useCase);
    }

    private void onPictureReceived(Picture picture) {
        Bitmap bitmap = bitmapUtils.decodeBitmapFromBase64(picture.getPicture());
        view.setUserPicture(bitmap);
    }

    @Override
    public void loginButtonClicked(String login, String pass) {
        view.startProgressBar();
        view.disablePasswordEdit();
        view.disableLoginButton();
        view.disableBackButton();
        checkUserPassword(login, pass);
    }

    private void checkUserPassword(String login, String pass) {
        UseCase useCase = useCaseFactory.checkUserPasswordUseCase(login, pass);
        useCase.execute(new CheckUserPasswordSubscriber());
        registerUseCase(useCase);
    }

    private void onUserPasswordChecked(boolean isCorrect) {
        if (isCorrect)
            view.onLoginCorrect();
        else {
            view.stopProgressBar();
            view.enablePasswordEdit();
            view.enableLoginButton();
            view.enableBackButton();
        }
    }

    @Override
    public void backButtonClicked() {
        unsubscribe();
        view.hidePasswordLayout();
        view.showLoginLayout();
        view.hideBackButton();
        view.enableLoginEdit();
        view.enableNextButton();
        view.setDefaultUserPicture();
    }

    private final class GetUserFromInitSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onError(Throwable e) {
            view.showUnknownExceptionMessage(e);
            view.enableLoginEdit();
            view.enableNextButton();
        }

        @Override
        public void onNext(User user) {
            if (user != null)
                onUserReceived(user);
        }
    }

    private final class GetUserSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onError(Throwable e) {
            view.showUnknownExceptionMessage(e);
            view.enableLoginEdit();
            view.enableNextButton();
        }

        @Override
        public void onNext(User user) {
            onUserReceived(user);
        }
    }

    private final class GetUserPictureSubscriber extends DefaultSubscriber<Picture> {
        @Override
        public void onError(Throwable e) {
            view.showUnknownExceptionMessage(e);
        }

        @Override
        public void onNext(Picture picture) {
            onPictureReceived(picture);
        }
    }

    private final class CheckUserPasswordSubscriber extends DefaultSubscriber<Boolean> {
        @Override
        public void onError(Throwable e) {
            view.showUnknownExceptionMessage(e);
            view.stopProgressBar();
            view.enablePasswordEdit();
            view.enableLoginButton();
            view.enableBackButton();
        }

        @Override
        public void onNext(Boolean isCorrect) {
            onUserPasswordChecked(isCorrect != null && isCorrect);
        }
    }
}