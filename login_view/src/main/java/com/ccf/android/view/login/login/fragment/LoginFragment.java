package com.ccf.android.view.login.login.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.ccf.android.ui.list.CcfCardListFragment;
import com.ccf.android.ui.presenter.CcfCardListPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import javax.inject.Inject;

@EFragment(resName = "ccf_cards_fragment")
public class LoginFragment extends CcfCardListFragment {

    @Inject
    public CcfCardListPresenter presenter;

    @AfterViews
    public void init() {
        presenter.setView(this);
        presenter.init();
    }
}
//    private LoginFragmentListener listener;
//
//    @ViewById
//    ProgressView progress;
//
//    @Inject
//    LoginPresenter presenter;
//
//    @AfterViews
//    void init() {
//        listener = (LoginFragmentListener) getActivity();
//    }
//
//    @UiThread
//    public void startProgressBar() {
//        progress.start();
//    }
//    @UiThread
//    public void onLoginCorrect() {
//        listener.onLoginCorrect();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        presenter.onDestroy();
//    }
//}

//    @Override
//    public void stopProgressBar() {
//        //TODO:
//        //progress.stop();
//    }

//    @Override
//    public void startProgressBar() {
//        //TODO:
//        snackBar.dismiss();
//        //progress.start();
//        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//    }

//@Override
//    public void showIncorrectLoginNameMassage() {
//        showToastMessage(R.string.invalid_username);
//    }
//
//    @Override
//    public void showNoUserMessage() {
//        showToastMessage(R.string.no_user);
//    }
//
//    @Override
//    public void showUnknownExceptionMessage(Throwable throwable) {
//        showToastMessage(R.string.login_exception);
//    }


//    public void showToastMessage(int messageId) {
//        snackBar.applyStyle(R.style.SnackBarError);
//        snackBar.actionText(R.string.retry);
//        snackBar.text(messageId).actionClickListener(new SnackBar.OnActionClickListener() {
//            @Override
//            public void onActionClick(com.rey.material.widget.SnackBar sb, int actionId) {
//                onNextButtonClicked();
//            }
//        });
//        if (snackBar.getState() == SnackBar.STATE_DISMISSED)
//            snackBar.show(activity);
//        else {
//            snackBar.dismiss();
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    snackBar.show(activity);
//                }
//            }, 500);
//        }
//    }
//