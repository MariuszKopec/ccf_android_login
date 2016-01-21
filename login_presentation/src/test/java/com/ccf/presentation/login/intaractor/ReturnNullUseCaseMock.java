package com.ccf.presentation.login.intaractor;

import com.ccf.logic.interactor.UseCase;

import rx.Subscriber;

public class ReturnNullUseCaseMock implements UseCase {

    @Override
    public void execute(Subscriber subscriber) {
        subscriber.onNext(null);
    }

    @Override
    public void unsubscribe() {

    }
}
