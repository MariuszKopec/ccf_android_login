package com.ccf.presentation.login.factory;

import com.ccf.logic.interactor.UseCase;
import com.ccf.android.presentation.login.standard.LoginUseCaseFactory;
import com.ccf.presentation.login.intaractor.ReturnExceptionUseCaseMock;

public class LoginUseCaseFactoryExceptionTest  implements LoginUseCaseFactory{
    @Override
    public UseCase getInitUseCase(Object context) {
        return new ReturnExceptionUseCaseMock();
    }

    @Override
    public UseCase getUserUseCase(String login) {
        return new ReturnExceptionUseCaseMock();
    }

    @Override
    public UseCase getPictureUseCase(String login) {
        return new ReturnExceptionUseCaseMock();
    }

    @Override
    public UseCase checkUserPasswordUseCase(String login, String password) {
        return new ReturnExceptionUseCaseMock();
    }
}
