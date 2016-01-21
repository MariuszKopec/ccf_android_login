package com.ccf.logic.view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import com.ccf.android.ui.base.BaseFragment;
import com.ccf.login.view.R;
import com.ccf.android.presentation.login.LoginFragmentPresenter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.mrengineer13.snackbar.SnackBar;
import com.nineoldandroids.animation.Animator;

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

    private static enum State {USER_STATE, PASSWORD_SATE}

    ;

    @ViewById
    android.support.v7.widget.CardView card_view;
    @ViewById
    EditText login_name;
    @ViewById
    EditText login_password;
    @ViewById
    TextView user_name_lastname;
    @ViewById
    com.rey.material.widget.Button next_button;
    @ViewById
    com.rey.material.widget.Button login_button;
    @ViewById
    com.rey.material.widget.ProgressView progress;
    @ViewById
    TextView logging_text;
    @ViewById
    View login_layout;
    @ViewById
    View password_layout;
    @ViewById
    View back_arrow;
    @ViewById
    de.hdodenhof.circleimageview.CircleImageView profile_image;
    @Inject
    LoginFragmentPresenter presenter;
    private LoginFragmentListener listener;
    private SnackBar snackBar;
    private State state = State.USER_STATE;

    @AfterViews
    void init() {
        listener = (LoginFragmentListener) getActivity();
        presenter.setView(this);
        presenter.init(getContext());
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
            profile_image.animate().alpha(0).setDuration(ANIMATION_TIME).setInterpolator(new AccelerateInterpolator()).withEndAction(new Runnable() {
                @Override
                public void run() {
                    if (state == State.USER_STATE)
                        profile_image.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_account_circle));
                    else {
                        profile_image.setImageDrawable(drawable);
                    }
                    profile_image.animate().alpha(1).setDuration(ANIMATION_TIME).setInterpolator(new DecelerateInterpolator()).start();
                }
            }).start();
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
        setEnabled(true);
        stopProgressBar();
        animateToUserState();
        setUserPicture(null);
        state = State.USER_STATE;
    }

    @Override
    @UiThread
    public void setPasswordState(String name) {
        user_name_lastname.setText(name);
        stopProgressBar();
        animateToPasswordState();
        setEnabled(true);
        state = State.PASSWORD_SATE;
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

    private void animateToUserState() {
        if (state == State.PASSWORD_SATE) {
            YoYo.with(Techniques.SlideInLeft).duration(ANIMATION_TIME).playOn(login_layout);
            YoYo.with(Techniques.FadeIn).duration(ANIMATION_TIME).playOn(login_layout);
            YoYo.with(Techniques.FadeOut).duration(ANIMATION_TIME).playOn(password_layout);
            YoYo.with(Techniques.FadeOut).duration(ANIMATION_TIME).playOn(back_arrow);
            YoYo.with(Techniques.SlideOutRight).withListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    password_layout.setVisibility(View.INVISIBLE);
                    login_name.requestFocus();
                    login_name.selectAll();
                    showKeyboard(login_name);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            }).duration(ANIMATION_TIME).playOn(password_layout);
        }
    }

    private void animateToPasswordState() {
        if (state == State.USER_STATE) {
            YoYo.with(Techniques.SlideOutLeft).duration(ANIMATION_TIME).playOn(login_layout);
            YoYo.with(Techniques.FadeOut).duration(ANIMATION_TIME).playOn(login_layout);
            YoYo.with(Techniques.FadeIn).duration(ANIMATION_TIME).playOn(password_layout);
            YoYo.with(Techniques.SlideInRight).duration(ANIMATION_TIME).playOn(password_layout);
            password_layout.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.FadeIn).duration(ANIMATION_TIME).playOn(back_arrow);
            back_arrow.setVisibility(View.VISIBLE);
            login_password.requestFocus();
            login_password.selectAll();
            showKeyboard(login_password);
        }
    }

    private void startProgressBar() {
        if (snackBar != null)
            snackBar.clear(true);
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
        if (snackBar != null)
            snackBar.clear(true);
        snackBar = new SnackBar.Builder(getActivity()).withMessageId(messageId)
                .withActionMessageId(R.string.retry).withOnClickListener(new SnackBar.OnMessageClickListener() {
                    @Override
                    public void onMessageClick(Parcelable token) {
                        nextButtonClicked();
                    }
                })
                .withDuration(SnackBar.PERMANENT_SNACK).withBackgroundColorId(android.R.color.holo_red_light).show();
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
