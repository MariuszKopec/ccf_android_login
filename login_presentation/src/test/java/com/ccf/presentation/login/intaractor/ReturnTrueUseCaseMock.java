package com.ccf.presentation.login.intaractor;

import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.User;

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
