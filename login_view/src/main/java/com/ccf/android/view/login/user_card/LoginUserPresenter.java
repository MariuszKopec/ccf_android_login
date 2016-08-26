package com.ccf.android.view.login.user_card;

public interface LoginUserPresenter {

    void init(Object args);
    void setView(LoginUserPresenterView view);
    void nextButtonClicked(String user);

    interface LoginUserPresenterView {
        void enableLoginEdit();
        void enableNextButton();
        void disableNextButton();
        void disableLoginEdit();
    }
}
