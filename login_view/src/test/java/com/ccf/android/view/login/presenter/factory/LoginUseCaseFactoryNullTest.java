package com.ccf.android.view.login.presenter.factory;

import com.ccf.android.view.login.presenter.LoginUseCaseFactory;
import com.ccf.logic.interactor.UseCase;
import com.ccf.android.view.login.presenter.intaractor.ReturnNullUseCaseMock;

public class LoginUseCaseFactoryNullTest implements LoginUseCaseFactory {
    @Override
    public UseCase getInitUseCase(Object context) {
        return new ReturnNullUseCaseMock();
    }

    @Override
    public UseCase getUserUseCase(String login) {
        return new ReturnNullUseCaseMock();
    }

    @Override
    public UseCase getPictureUseCase(String login) {
        return new ReturnNullUseCaseMock();
    }

    @Override
    public UseCase checkUserPasswordUseCase(String login, String password) {
        return new ReturnNullUseCaseMock();
    }
}
