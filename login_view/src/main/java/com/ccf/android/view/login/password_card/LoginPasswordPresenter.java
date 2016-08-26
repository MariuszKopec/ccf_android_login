package com.ccf.android.view.login.password_card;

import android.graphics.Bitmap;

public interface LoginPasswordPresenter {
    void setView(LoginPasswordPresenter.View view);
    void init();
    void loginButtonClicked(String password);
    void backButtonClicked();

    interface View {
        void disablePasswordEdit();
        void disableBackButton();
        void enablePasswordEdit();
        void enableLoginButton();
        void enableBackButton();
        void setUserPicture(Bitmap bitmap);
    }
}
