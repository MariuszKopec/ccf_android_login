package com.ccf.android.view.login.user_card;

import com.ccf.logic.login.User;

public interface LoginUserPresenterListener {
    void onNoUserReceived();
    void onUserReceived(User user);
    void onError(Throwable e);
}
