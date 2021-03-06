package com.ccf.android.view.login.presenter.intaractor;

import com.ccf.logic.interactor.UseCase;

import rx.Subscriber;

public class ReturnExceptionUseCaseMock implements UseCase {
    @Override
    public void execute(Subscriber subscriber) {
        subscriber.onError(new RuntimeException());
    }

    @Override
    public void unsubscribe() {

    }
}
