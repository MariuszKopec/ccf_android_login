package com.ccf.android.view.login.presenter;

import android.graphics.Bitmap;

public interface LoginFragmentPresenter {
    void init(Object args);

    void setView(LoginView view);
    void nextButtonClicked(String user);
    void backButtonClicked();
    void loginButtonClicked(String user, String pass);
    void onDestroy();


    interface LoginView {
        void showLoginCard();
        void startProgressBar();
        void disableLoginEdit();
        void disableNextButton();
        void enableLoginEdit();
        void enableNextButton();
        void stopProgressBar();
        void clearPassword();
        void setUserName(String userName);
        void setDefaultUserPicture();
        void showBackButton();
        void hideLoginLayout();
        void showPasswordLayout();
        void setUserPicture(Bitmap picture);

        void disablePasswordEdit();
        void disableLoginButton();
        void disableBackButton();
        void hideLoginCard();
        void onLoginCorrect();
        void enablePasswordEdit();
        void enableLoginButton();
        void enableBackButton();

        void hidePasswordLayout();
        void showLoginLayout();
        void hideBackButton();

        void showIncorrectLoginNameMassage();
        void showNoUserMessage();
        void showUnknownExceptionMessage(Throwable throwable);
    }
}
