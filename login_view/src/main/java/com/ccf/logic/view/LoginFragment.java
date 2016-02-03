package com.ccf.logic.view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.ccf.android.presentation.login.LoginFragmentPresenter;
import com.ccf.android.ui.animation.DefaultAnimationListener;
import com.ccf.android.ui.base.BaseFragment;
import com.ccf.android.ui.widget.CircleImageView;
import com.ccf.login.view.R;
import com.rey.material.widget.SnackBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EFragment(resName = "login_fragment_layout")
public class LoginFragment extends BaseFragment implements LoginFragmentPresenter.LoginView {
    private static final int ANIMATION_TIME = 200;
    private static enum State {USER_STATE, PASSWORD_SATE};
    private LoginFragmentListener listener;
    private com.rey.material.widget.SnackBar snackBar;
    private State state = State.USER_STATE;

    @ViewById android.support.v7.widget.CardView card_view;
    @ViewById EditText login_name;
    @ViewById EditText login_password;
    @ViewById TextView user_name_lastname;
    @ViewById com.rey.material.widget.Button next_button;
    @ViewById com.rey.material.widget.Button login_button;
    @ViewById com.rey.material.widget.ProgressView progress;
    @ViewById TextView logging_text;
    @ViewById View login_layout;
    @ViewById View password_layout;
    @ViewById View back_arrow;
    @ViewById CircleImageView profile_image;
    @Inject LoginFragmentPresenter presenter;

    @AfterViews
    void init() {
        listener = (LoginFragmentListener) getActivity();
        presenter.setView(this);
        presenter.init(getContext());
        snackBar = new com.rey.material.widget.SnackBar(getActivity());
    }

    @Click
    void nextButtonClicked() {
        String login = login_name.getText().toString();
        presenter.nextButtonClicked(login);
    }

    @Override
    @UiThread
    public void showIncorrectLoginNameMassage() {
        showToastMessage(R.string.invalid_username);
    }

    @Override
    @UiThread
    public void setBusyState() {
        startProgressBar();
        setEnabled(false);
    }

    @UiThread
    @Override
    public void setUserPicture(final Bitmap bitmap) {
        final Drawable drawable = bitmap == null ? ContextCompat.getDrawable(getContext(), R.drawable.ic_account_circle) : new BitmapDrawable(getResources(), bitmap);
        if (profile_image.getDrawable().getConstantState().equals(drawable.getConstantState()) == false) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fadeOut.setAnimationListener(new DefaultAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    if (state == State.USER_STATE)
                        profile_image.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_account_circle));
                    else
                        profile_image.setImageDrawable(drawable);
                    profile_image.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
                }
            });
            profile_image.startAnimation(fadeOut);
        }
    }

    @Override
    public boolean isPasswordState() {
        return state == State.PASSWORD_SATE;
    }

    @Override
    @UiThread
    public void onLoginCorrect() {
        listener.onLoginCorrect();
    }

    @Override
    @UiThread
    public void showNoUserMessage() {
        showToastMessage(R.string.no_user);
    }

    @Override
    @UiThread
    public void setUserState() {
        if (state == State.PASSWORD_SATE) {
            login_layout.setVisibility(View.VISIBLE);
            login_layout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left));
            back_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out));
            stopProgressBar();
            setUserPicture(null);
            Animation slideOutRight = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_right);
            slideOutRight.setAnimationListener(new DefaultAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    setEnabled(true);
                    password_layout.setVisibility(View.INVISIBLE);
                    login_name.requestFocus();
                    login_name.selectAll();
                    showKeyboard(login_name);
                    state = State.USER_STATE;
                }
            });
            password_layout.startAnimation(slideOutRight);
        }
    }

    @Override
    @UiThread
    public void setPasswordState(final String name) {
        if (state == State.USER_STATE) {
            user_name_lastname.setText(name);
            password_layout.setVisibility(View.VISIBLE);
            login_layout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left));
            back_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
            setEnabled(true);
            stopProgressBar();
            Animation slideInRight = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);
            slideInRight.setAnimationListener(new DefaultAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    back_arrow.setVisibility(View.VISIBLE);
                    login_layout.setVisibility(View.INVISIBLE);
                    login_password.requestFocus();
                    login_password.selectAll();
                    showKeyboard(login_password);
                    state = State.PASSWORD_SATE;
                }
            });
            password_layout.startAnimation(slideInRight);
        }
    }

    @Override
    public void setPasswordState() {
        setPasswordState(user_name_lastname.getText().toString());
    }

    @Click
    void backArrowClicked() {
        presenter.backButtonClicked();
    }

    @Click
    void loginButtonClicked() {
        String login = login_name.getText().toString();
        String pass = login_password.getText().toString();
        presenter.loginButtonClicked(login, pass);
    }

    @EditorAction(resName = "login_name")
    void loginNameDoneAction() {
        nextButtonClicked();
    }

    @EditorAction(resName = "login_password")
    void loginPasswordDoneAction() {
        loginButtonClicked();
    }

    @Override
    public void showUnknownExceptionMessage(Throwable throwable) {
        showToastMessage(R.string.login_exception);
        Log.e(getClass().getName(), "onLoginResponse_Failure", throwable);
    }

    private void startProgressBar() {
        snackBar.dismiss();
        progress.start();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void stopProgressBar() {
        progress.stop();
    }

    private void setEnabled(boolean isEnabled) {
        login_name.setEnabled(isEnabled);
        login_password.setEnabled(isEnabled);
        login_button.setEnabled(isEnabled);
        back_arrow.setEnabled(isEnabled);
        next_button.setEnabled(isEnabled);
    }

    @UiThread
    public void showToastMessage(int messageId) {
        snackBar.applyStyle(R.style.SnackBarError);
        snackBar.actionText(R.string.retry);
        snackBar.text(messageId)
                .actionClickListener(new SnackBar.OnActionClickListener() {
                    @Override
                    public void onActionClick(com.rey.material.widget.SnackBar sb, int actionId) {
                        nextButtonClicked();
                    }
                });
        if(snackBar.getState() == SnackBar.STATE_DISMISSED)
            snackBar.show(getActivity());
        else {
            snackBar.dismiss();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    snackBar.show(getActivity());
                }
            }, 500);
        }
    }

    public boolean onBackPressed() {
        return presenter.backButtonClicked();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
