package com.ccf.presentation.login.factory;

import com.ccf.logic.interactor.UseCase;
import com.ccf.android.presentation.login.standard.LoginUseCaseFactory;
import com.ccf.presentation.login.intaractor.ReturnExceptionUseCaseMock;
import com.ccf.presentation.login.intaractor.ReturnUserUseCaseMock;

public class LoginUseCaseFactoryPictureExceptionOnlyTest implements LoginUseCaseFactory{
    @Override
    public UseCase getInitUseCase(Object context) {
        return new ReturnUserUseCaseMock();
    }

    @Override
    public UseCase getUserUseCase(String login) {
        return new ReturnUserUseCaseMock();
    }

    @Override
    public UseCase getPictureUseCase(String login) {
        return new ReturnExceptionUseCaseMock();
    }

    @Override
    public UseCase checkUserPasswordUseCase(String login, String password) {
        return null;
    }
}
