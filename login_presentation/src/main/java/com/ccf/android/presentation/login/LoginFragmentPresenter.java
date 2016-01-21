package com.ccf.android.presentation.login;

import android.graphics.Bitmap;

public interface LoginFragmentPresenter {
    void init(Object args);

    void setView(LoginView view);
    void nextButtonClicked(String user);
    boolean backButtonClicked();
    void loginButtonClicked(String user, String pass);
    void onDestroy();

    interface LoginView {
        void showIncorrectLoginNameMassage();
        void showNoUserMessage();
        void showUnknownExceptionMessage(Throwable throwable);
        void setPasswordState(String userName);
        void setUserState();
        void setBusyState();
        void setUserPicture(Bitmap picture);
        void onLoginCorrect();
        boolean isPasswordState();
    }
}
