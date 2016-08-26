package com.ccf.android.view.login.user_card;

import com.ccf.logic.interactor.UseCase;

public interface LoginUserPresenterUseCaseFactory {
    UseCase getInitUseCase(Object context);
    UseCase getUserUseCase(String login);
}
