package com.ccf.android.view.login.presenter.intaractor;

import com.ccf.logic.interactor.UseCase;

import rx.Subscriber;

public class ReturnFalseUseCaseMock implements UseCase{
    @Override
    public void execute(Subscriber subscriber) {
        subscriber.onNext(false);
    }

    @Override
    public void unsubscribe() {

    }
}
