package com.ccf.android.view.login.presenter.intaractor;

import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.User;

import rx.Subscriber;

public class ReturnUserUseCaseMock implements UseCase {
    @Override
    public void execute(Subscriber subscriber) {
        subscriber.onNext(new User(1, "name", "last name", "login", "password"));
    }

    @Override
    public void unsubscribe() {

    }
}
