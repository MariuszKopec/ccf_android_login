package com.ccf.android.view.login.password_card;

import com.ccf.logic.interactor.UseCase;

public interface LoginPasswordUseCaseFactory {
    UseCase getPictureUseCase();
    UseCase checkUserPasswordUseCase(String password);
}
