package com.ccf.main.login;

import com.ccf.logic.executor.PostExecutionThread;
import com.ccf.logic.executor.ThreadExecutor;
import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.interactor.CheckUserPasswordUseCase;
import com.ccf.logic.login.interactor.GetUserPictureUseCase;
import com.ccf.logic.login.interactor.GetUserUseCase;
import com.ccf.logic.login.interactor.InitUseCase;
import com.ccf.logic.login.repository.LoginRepository;
import com.ccf.android.repository.login.memory.LoginRepositoryMemory;
import com.ccf.main.application.CcfApplication;
import com.ccf.android.presentation.login.standard.LoginUseCaseFactory;

public class LoginUseCaseFactoryImpl implements LoginUseCaseFactory {
    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private LoginRepository repository;

    public LoginUseCaseFactoryImpl() {
        threadExecutor = CcfApplication.getInstance().getThreadExecutor();
        postExecutionThread = CcfApplication.getInstance().getPostExecutionThread();
        repository = new LoginRepositoryMemory();
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
    public UseCase getPictureUseCase(String login) {
        return new GetUserPictureUseCase(login, repository, threadExecutor, postExecutionThread);
    }

    @Override
    public UseCase checkUserPasswordUseCase(String login, String password) {
        return new CheckUserPasswordUseCase(login, password, repository, threadExecutor, postExecutionThread);
    }
}
