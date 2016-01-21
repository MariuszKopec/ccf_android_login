package com.ccf.android.presentation.login.standard;

import android.graphics.Bitmap;

import com.ccf.android.presentation.login.LoginFragmentPresenter;
import com.ccf.logic.interactor.DefaultSubscriber;
import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.Picture;
import com.ccf.logic.login.User;
import com.ccf.android.presentation.base.BitmapUtils;

public class LoginFragmentPresenterImpl implements LoginFragmentPresenter {
    private final LoginUseCaseFactory useCaseFactory;
    private LoginFragmentPresenter.LoginView view;
    private UseCase getUserFromInitUseCase;
    private UseCase getUserUseCase;
    private UseCase getUserPictureUseCase;

    public LoginFragmentPresenterImpl(LoginUseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @Override
    public void setView(LoginFragmentPresenter.LoginView view) {
        this.view = view;
    }

    @Override
    public void init(Object context) {
        getUserFromInitUseCase = useCaseFactory.getInitUseCase(context);
        getUserFromInitUseCase.execute(new GetUserFromInitSubscriber());
    }

    @Override
    public void nextButtonClicked(String login) {
        if (login == null || login.trim().length() == 0)
            view.showIncorrectLoginNameMassage();
        else {
            view.setBusyState();
            getUserUseCase = useCaseFactory.getUserUseCase(login);
            getUserUseCase.execute(new GetUserSubscriber());
        }
    }

    private void onUserReceived(User user) {
        if (user == null) {
            view.showNoUserMessage();
            view.setUserState();
        } else {
            view.setPasswordState(user.getName() + " " + user.getLastName());
            startGettingUserPicture(user.getLogin());
        }
    }

    private void startGettingUserPicture(String login) {
        getUserPictureUseCase = useCaseFactory.getPictureUseCase(login);
        getUserPictureUseCase.execute(new GetUserPictureSubscriber());
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

    @Override
    public void onDestroy() {
        unsubscribe();
    }

    private void unsubscribe() {
        if(getUserFromInitUseCase != null)
            getUserFromInitUseCase.unsubscribe();
        if(getUserFromInitUseCase != null)
            getUserFromInitUseCase.unsubscribe();
        if(getUserPictureUseCase != null)
            getUserPictureUseCase.unsubscribe();
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
}