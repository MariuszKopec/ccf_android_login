package com.ccf.android.view.login.password_card;

import android.graphics.Bitmap;

import com.ccf.android.ui.base.BasePresenter;
import com.ccf.android.ui.utils.BitmapUtils;
import com.ccf.logic.interactor.DefaultSubscriber;
import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.Picture;

public class LoginPasswordPresenterImpl extends BasePresenter implements LoginPasswordPresenter {
    private final LoginPasswordUseCaseFactory useCaseFactory;
    private final BitmapUtils bitmapUtils;
    private LoginPasswordPresenter.View view;

    public LoginPasswordPresenterImpl(LoginPasswordUseCaseFactory useCaseFactory, BitmapUtils bitmapUtils) {
        this.useCaseFactory = useCaseFactory;
        this.bitmapUtils = bitmapUtils;
    }

    @Override
    public void setView(LoginPasswordPresenter.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        getUserPicture();
    }

    private void getUserPicture() {
        UseCase useCase = useCaseFactory.getPictureUseCase();
        useCase.execute(new GetUserPictureSubscriber());
        registerUseCase(useCase);
    }

    private void onPictureReceived(Picture picture) {
        Bitmap bitmap = bitmapUtils.decodeBitmapFromBase64(picture.getPicture());
        view.setUserPicture(bitmap);
    }

    @Override
    public void loginButtonClicked(String password) {
        view.disablePasswordEdit();
        view.disableBackButton();
        checkUserPassword(password);
    }

    private void checkUserPassword(String password) {
        UseCase useCase = useCaseFactory.checkUserPasswordUseCase(password);
        useCase.execute(new CheckUserPasswordSubscriber());
        registerUseCase(useCase);
    }

    private void onUserPasswordChecked(boolean isCorrect) {
        if (isCorrect) {
            //view.hideLoginCard();
            //view.onLoginCorrect();
        }
        else {
            //view.stopProgressBar();
            view.enablePasswordEdit();
            view.enableLoginButton();
            view.enableBackButton();
        }
    }

    @Override
    public void backButtonClicked() {
        // todo
    }

    private final class GetUserPictureSubscriber extends DefaultSubscriber<Picture> {
        @Override
        public void onError(Throwable e) {
            //view.showUnknownExceptionMessage(e);
        }

        @Override
        public void onNext(Picture picture) {
            onPictureReceived(picture);
        }
    }

    private final class CheckUserPasswordSubscriber extends DefaultSubscriber<Boolean> {
        @Override
        public void onError(Throwable e) {
            //view.showUnknownExceptionMessage(e);
           // view.stopProgressBar();
            view.enablePasswordEdit();
            view.enableLoginButton();
            view.enableBackButton();
        }

        @Override
        public void onNext(Boolean isCorrect) {
            onUserPasswordChecked(isCorrect);
        }
    }
}