package com.ccf.android.view.login.presenter;

import com.ccf.logic.interactor.UseCase;

public interface LoginUseCaseFactory {
    UseCase getInitUseCase(Object context);
    UseCase getUserUseCase(String login);
    UseCase getPictureUseCase(String login);
    UseCase checkUserPasswordUseCase(String login, String password);
}
