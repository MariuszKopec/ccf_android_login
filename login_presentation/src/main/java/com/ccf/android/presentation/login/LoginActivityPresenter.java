package com.ccf.android.presentation.login;

public class LoginActivityPresenter {
    private LoginActivityView view;

    public void onLoginCorrect() {
        view.showMainActivity();
    }

    public void setView(LoginActivityView view) {
        this.view = view;
    }

    public interface LoginActivityView {
        void showMainActivity();
    }
}
