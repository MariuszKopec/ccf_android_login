package com.ccf.android.presentation.login.standard;

import android.graphics.Bitmap;

import com.ccf.android.presentation.base.BasePresenter;
import com.ccf.android.presentation.login.LoginFragmentPresenter;
import com.ccf.logic.interactor.DefaultSubscriber;
import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.Picture;
import com.ccf.logic.login.User;
import com.ccf.android.presentation.base.BitmapUtils;

import rx.Subscriber;

public class LoginFragmentPresenterImpl extends BasePresenter implements LoginFragmentPresenter {
    private final LoginUseCaseFactory useCaseFactory;
    private LoginFragmentPresenter.LoginView view;

    public LoginFragmentPresenterImpl(LoginUseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @Override
    public void setView(LoginFragmentPresenter.LoginView view) {
        this.view = view;
    }

    @Override
    public void init(Object context) {
        getUserFromInit(context);
    }

    private void getUserFromInit(Object context) {
        UseCase useCase = useCaseFactory.getInitUseCase(context);
        useCase.execute(new GetUserFromInitSubscriber());
        registerUseCase(useCase);
    }

    @Override
    public void nextButtonClicked(String login) {
        if (login == null || login.trim().length() == 0)
            view.showIncorrectLoginNameMassage();
        else {
            view.setBusyState();
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
            view.setUserState();
        } else {
            view.setPasswordState(user.getName() + " " + user.getLastName());
            getUserPicture(user.getLogin());
        }
    }

    private void getUserPicture(String login) {
        UseCase useCase = useCaseFactory.getPictureUseCase(login);
        useCase.execute(new GetUserPictureSubscriber());
        registerUseCase(useCase);
    }

    private void onPictureReceived(Picture picture) {
        if (view.isPasswordState()) {
            Bitmap bitmap = BitmapUtils.decodeBitmapFromBase64(picture.getPicture());
            view.setUserPicture(bitmap);
        }
    }

    @Override
    public void loginButtonClicked(String login, String pass) {
        view.setBusyState();
        checkUserPassword(login, pass);
    }

    private void checkUserPassword(String login, String pass) {
        UseCase useCase = useCaseFactory.checkUserPasswordUseCase(login, pass);
        useCase.execute(new CheckUserPasswordSubscriber());
        registerUseCase(useCase);
    }

    private void onUserPasswordChecked(boolean isCorrect) {
        if(isCorrect)
            view.onLoginCorrect();
        else
            view.setPasswordState();
    }

    @Override
    public boolean backButtonClicked() {
        if (view.isPasswordState()) {
            view.setUserState();
            unsubscribe();
            return true;
        }
        return false;
    }

    private final class GetUserFromInitSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onError(Throwable e) {
            view.showUnknownExceptionMessage(e);
            view.setUserState();
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
            view.setUserState();
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
            view.setUserState();
        }

        @Override
        public void onNext(Boolean isCorrect) {
            onUserPasswordChecked(isCorrect);
        }
    }
}