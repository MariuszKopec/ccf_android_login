package com.ccf.android.login.example.login;

import com.ccf.android.login.example.application.CcfApplication;
import com.ccf.android.view.login.password_card.LoginPasswordUseCaseFactory;
import com.ccf.android.view.login.user_card.LoginUserPresenterUseCaseFactory;
import com.ccf.logic.executor.PostExecutionThread;
import com.ccf.logic.executor.ThreadExecutor;
import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.interactor.CheckUserPasswordUseCase;
import com.ccf.logic.login.interactor.GetUserPictureUseCase;
import com.ccf.logic.login.interactor.GetUserUseCase;
import com.ccf.logic.login.interactor.InitUseCase;
import com.ccf.logic.login.repository.LoginRepository;

public class LoginUseCaseFactoryImpl implements LoginUserPresenterUseCaseFactory, LoginPasswordUseCaseFactory {
    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private LoginRepository repository;

    public LoginUseCaseFactoryImpl() {
        threadExecutor = CcfApplication.getInstance().getThreadExecutor();
        postExecutionThread = CcfApplication.getInstance().getPostExecutionThread();
        repository = (LoginRepository) CcfApplication.getInstance().getRepository();
    }

    @Override
    public UseCase getInitUseCase(Object context) {
        return new InitUseCase(context, repository, threadExecutor, postExecutionThread);
    }

    @Override
    public UseCase getUserUseCase(String login) {
        return new GetUserUseCase(login, repository, threadExecutor,  postExecutionThread);
    }

    @Override
    public UseCase getPictureUseCase() {
        return new GetUserPictureUseCase("", repository, threadExecutor, postExecutionThread);
    }

    @Override
    public UseCase checkUserPasswordUseCase(String password) {
        return new CheckUserPasswordUseCase("", password, repository, threadExecutor, postExecutionThread);
    }
}
