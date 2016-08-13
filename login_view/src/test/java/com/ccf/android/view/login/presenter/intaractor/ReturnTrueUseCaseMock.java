package com.ccf.android.view.login.presenter.intaractor;

import com.ccf.logic.interactor.UseCase;

import rx.Subscriber;

public class ReturnTrueUseCaseMock implements UseCase {
    @Override
    public void execute(Subscriber subscriber) {
        subscriber.onNext(Boolean.TRUE);
    }

    @Override
    public void unsubscribe() {

    }
}
