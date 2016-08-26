package com.ccf.android.login.example.login;

import android.app.Activity;

import com.ccf.android.login.example.application.CcfApplication;
import com.ccf.android.ui.card.CcfCard;
import com.ccf.android.ui.presenter.CcfCardListPresenter;
import com.ccf.android.ui.presenter.CcfCardListPresenterImpl;
import com.ccf.android.ui.utils.impl.BitmapUtilsImpl;
import com.ccf.android.view.login.login.activity.LoginActivityListener;
import com.ccf.android.view.login.login.activity.LoginActivity_;
import com.ccf.android.view.login.password_card.LoginPasswordCard;
import com.ccf.android.view.login.password_card.LoginPasswordPresenterImpl;
import com.ccf.android.view.login.user_card.LoginUserCard;
import com.ccf.android.view.login.login.fragment.LoginFragment_;
import com.ccf.android.view.login.login.presenter.LoginPresenterImpl;
import com.ccf.android.view.login.user_card.LoginUserPresenterImpl;
import com.ccf.android.view.login.user_card.LoginUserPresenterListener;
import com.ccf.android.view.login.user_card.LoginUserPresenterUseCaseFactory;
import com.ccf.logic.executor.PostExecutionThread;
import com.ccf.logic.executor.ThreadExecutor;
import com.ccf.logic.interactor.UseCase;
import com.ccf.logic.login.User;
import com.ccf.logic.login.interactor.GetUserUseCase;
import com.ccf.logic.login.interactor.InitUseCase;
import com.ccf.logic.login.repository.LoginRepository;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module(injects = {LoginActivity_.class, LoginFragment_.class})
public class LoginModule implements LoginUserPresenterListener {
    @Provides
    CcfCardListPresenter provideCcfCardListPresenter() {
        List<CcfCard> ccfCards = new ArrayList<CcfCard>();
        ccfCards.add(getLoginCard(1));
        for (int i  =2; i<100000; i++)
            ccfCards.add(getPasswordCard(i));
        return new CcfCardListPresenterImpl(ccfCards);
    }

    private CcfCard getLoginCard(int id) {
        LoginUserPresenterImpl presenter = new LoginUserPresenterImpl(new LoginUseCaseFactoryImpl(), this);
        LoginUserCard card = new LoginUserCard(presenter, id);
        return card;
    }

    private CcfCard getPasswordCard(int id) {
        LoginPasswordPresenterImpl presenter = new LoginPasswordPresenterImpl(new LoginUseCaseFactoryImpl(), new BitmapUtilsImpl());
        LoginPasswordCard card = new LoginPasswordCard(presenter, id);
        return card;
    }

    @Provides
    LoginActivityListener provideStartPresenter() {
        return new LoginActivityListener() {
            @Override
            public void onLoginCorrect(Activity activity) {
                activity.finish();
            }
        };
    }

    @Override
    public void onNoUserReceived() {

    }

    @Override
    public void onUserReceived(User user) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
