package com.ccf.android.view.login.user_card;

import android.text.TextUtils;

import com.ccf.android.ui.base.BasePresenter;
import com.ccf.logic.interactor.DefaultSubscriber;
import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.User;

public class LoginUserPresenterImpl extends BasePresenter implements LoginUserPresenter {
    private final LoginUserPresenterUseCaseFactory useCaseFactory;
    private final LoginUserPresenterListener listener;
    private LoginUserPresenterView view;

    public LoginUserPresenterImpl(LoginUserPresenterUseCaseFactory useCaseFactory, LoginUserPresenterListener listener) {
        this.useCaseFactory = useCaseFactory;
        this.listener = listener;
    }

    @Override
    public void setView(LoginUserPresenterView view) {
        this.view = view;
    }

    @Override
    public void init(Object context) {
        getUserFromInit(context);
    }

    private void getUserFromInit(Object context) {
        UseCase useCase = useCaseFactory.getInitUseCase(context);
        useCase.execute(new GetUserFromInitSubscriber());
        registerUseCase(useCase);
    }

    @Override
    public void nextButtonClicked(String login) {
        unsubscribe();
        if (TextUtils.isEmpty(login)) {
        }
        else {
            view.disableNextButton();
            view.disableLoginEdit();
            getUser(login);
        }
    }

    private void getUser(String login) {
        UseCase useCase = useCaseFactory.getUserUseCase(login);
        useCase.execute(new GetUserSubscriber());
        registerUseCase(useCase);
    }

    private void onUserReceived(User user) {
        if (user == null) {
            view.enableLoginEdit();
            view.enableNextButton();
            listener.onNoUserReceived();
        } else {
            listener.onUserReceived(user);
        }
    }

    private void onError(Throwable e) {
        view.enableLoginEdit();
        view.enableNextButton();
        listener.onError(e);
    }

    private final class GetUserFromInitSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onError(Throwable e) {
            LoginUserPresenterImpl.this.onError(e);
        }

        @Override
        public void onNext(User user) {
            if (user != null)
                onUserReceived(user);
        }
    }

    private final class GetUserSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onError(Throwable e) {
            LoginUserPresenterImpl.this.onError(e);
        }

        @Override
        public void onNext(User user) {
            onUserReceived(user);
        }
    }
}