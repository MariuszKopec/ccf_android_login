package com.ccf.presentation.login.factory;

import com.ccf.android.presentation.login.standard.LoginUseCaseFactory;
import com.ccf.logic.interactor.UseCase;
import com.ccf.presentation.login.intaractor.ReturnFalseUseCaseMock;

public class LoginUseCaseFactoryFailureTest implements LoginUseCaseFactory {
    @Override
    public UseCase getInitUseCase(Object context) {
        return null;
    }

    @Override
    public UseCase getUserUseCase(String login) {
        return null;
    }

    @Override
    public UseCase getPictureUseCase(String login) {
        return null;
    }

    @Override
    public UseCase checkUserPasswordUseCase(String login, String password) {
        return new ReturnFalseUseCaseMock();
    }
}
