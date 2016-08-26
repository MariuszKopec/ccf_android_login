package com.ccf.android.view.login.presenter.factory;

import com.ccf.android.view.login.login.presenter.LoginUseCaseFactory;
import com.ccf.logic.interactor.UseCase;
import com.ccf.android.view.login.presenter.intaractor.ReturnPictureUseCaseMock;
import com.ccf.android.view.login.presenter.intaractor.ReturnTrueUseCaseMock;
import com.ccf.android.view.login.presenter.intaractor.ReturnUserUseCaseMock;

public class LoginUseCaseFactorySuccessTest implements LoginUseCaseFactory {

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
        return new ReturnPictureUseCaseMock();
    }

    @Override
    public UseCase checkUserPasswordUseCase(String login, String password) {
        return new ReturnTrueUseCaseMock();
    }
}
