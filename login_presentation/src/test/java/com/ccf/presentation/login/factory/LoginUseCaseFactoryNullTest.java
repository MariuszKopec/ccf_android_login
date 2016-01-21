package com.ccf.presentation.login.factory;

import com.ccf.logic.interactor.UseCase;
import com.ccf.android.presentation.login.standard.LoginUseCaseFactory;
import com.ccf.presentation.login.intaractor.ReturnNullUseCaseMock;

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
}
