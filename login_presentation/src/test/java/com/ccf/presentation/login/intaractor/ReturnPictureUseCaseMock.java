package com.ccf.presentation.login.intaractor;

import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.Picture;

import rx.Subscriber;

public class ReturnPictureUseCaseMock implements UseCase {
    @Override
    public void execute(Subscriber subscriber) {
        String base64 = "123";
        subscriber.onNext(new Picture(base64));
    }

    @Override
    public void unsubscribe() {
    }
}
