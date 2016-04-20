package com.ccf.logic.view;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
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
import com.ccf.android.ui.animation.interporator.EaseCubicInInterpolator;
import com.ccf.android.ui.animation.interporator.EaseCubicOutInterpolator;
import com.ccf.android.ui.base.BaseFragment;
import com.ccf.android.ui.widget.SnackBar;
import com.ccf.login.view.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EFragment(resName = "login_fragment_layout")
public class LoginFragment extends BaseFragment implements LoginFragmentPresenter.LoginView {
    private LoginFragmentListener listener;
    private com.ccf.android.ui.widget.SnackBar snackBar;

    @ViewById android.support.v7.widget.CardView card_view;
    @ViewById EditText login_name;
    @ViewById com.ccf.android.ui.widget.PasswordEditText login_password;
    @ViewById TextView user_name_lastname;
    @ViewById com.ccf.android.ui.widget.Button next_button;
    @ViewById com.ccf.android.ui.widget.Button login_button;
    @ViewById com.ccf.android.ui.widget.ProgressView progress;
    @ViewById TextView logging_text;
    @ViewById View login_layout;
    @ViewById View password_layout;
    @ViewById com.ccf.android.ui.widget.BackImageView back_arrow;
    @ViewById com.ccf.android.ui.widget.CircleImageView profile_image;
    @Inject LoginFragmentPresenter presenter;

    @AfterViews
    void init() {
        listener = (LoginFragmentListener) getActivity();
        presenter.setView(this);
        presenter.init(getContext());
        snackBar = new SnackBar(getActivity());
    }

    public void showLoginCard() {
        Animation bottomUp = AnimationUtils.loadAnimation(getContext(),
                R.anim.slide_in_bottom);
        bottomUp.setInterpolator(new EaseCubicOutInterpolator());
        card_view.startAnimation(bottomUp);
        card_view.setVisibility(View.VISIBLE);
    }

    @Click
    void nextButtonClicked() {
        String login = login_name.getText().toString();
        presenter.nextButtonClicked(login);
    }

    @Override
    @UiThread
    public void startProgressBar() {
        snackBar.dismiss();
        progress.start();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void disableLoginEdit() {
        login_name.setEnabled(false);
    }

    @Override
    public void disableNextButton() {
        next_button.setEnabledWithAnimation(false);
    }

    @Override
    public void enableLoginEdit() {
        login_name.setEnabled(true);
    }

    @Override
    public void enableNextButton() {
        next_button.setEnabledWithAnimation(true);
    }

    @Override
    public void stopProgressBar() {
        progress.stop();
    }

    @Override
    public void clearPassword() {
        login_password.setText("");
    }

    @Override
    public void setUserName(String userName) {
        user_name_lastname.setText(userName);
    }

    @Override
    public void setDefaultUserPicture() {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_account_circle);
        drawable.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        setUserPicture(drawable, true);
    }

    @Override
    public void showBackButton() {
        back_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
    }

    @Override
    public void hideLoginLayout() {
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left);
        anim.setAnimationListener(new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                login_layout.setVisibility(View.INVISIBLE);
            }
        });
        login_layout.startAnimation(anim);
    }

    @Override
    public void showPasswordLayout() {
        password_layout.setVisibility(View.VISIBLE);
        password_layout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right));
    }

    @UiThread
    @Override
    public void setUserPicture(final Bitmap bitmap) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
        setUserPicture(bitmapDrawable, false);
    }

    private void setUserPicture(final Drawable drawable, final boolean isColorFilter) {
        Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        fadeOut.setAnimationListener(new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                profile_image.setImageDrawable(drawable);
                if(isColorFilter)
                    profile_image.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorSecondary), PorterDuff.Mode.MULTIPLY);
                else
                    profile_image.setColorFilter(null);
                profile_image.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
            }
        });
        profile_image.startAnimation(fadeOut);
    }

    @Click
    void loginButtonClicked() {
        String login = login_name.getText().toString();
        String pass = login_password.getText().toString();
        presenter.loginButtonClicked(login, pass);
    }

    @Override
    public void disablePasswordEdit() {
        login_password.setEnabled(false);
    }

    @Override
    public void disableLoginButton() {
        login_button.setEnabledWithAnimation(false);
    }

    @Override
    public void disableBackButton() {
        back_arrow.setEnabledWithAnimation(false);
    }

    public void hideLoginCard() {
        Animation bottomUp = AnimationUtils.loadAnimation(getContext(),
                R.anim.slide_out_bottom);
        bottomUp.setInterpolator(new EaseCubicInInterpolator());
        bottomUp.setAnimationListener(new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                card_view.setVisibility(View.GONE);
            }
        });
        card_view.startAnimation(bottomUp);
    }

    @Override
    public void enablePasswordEdit() {
        login_password.setEnabled(true);
    }

    @Override
    public void enableLoginButton() {
        login_button.setEnabledWithAnimation(true);
    }

    @Override
    public void enableBackButton() {
        back_arrow.setEnabledWithAnimation(true);
    }

    @Click
    void backArrowClicked() {
        presenter.backButtonClicked();
    }

    @Override
    public void hidePasswordLayout() {
        Animation slideOutRight = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_right);
        slideOutRight.setAnimationListener(new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                password_layout.setVisibility(View.INVISIBLE);
            }
        });
        password_layout.startAnimation(slideOutRight);
    }

    @Override
    public void showLoginLayout() {
        login_layout.setVisibility(View.VISIBLE);
        login_layout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left));
    }

    @Override
    public void hideBackButton() {
        back_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out));
    }

    @Override
    @UiThread
    public void showIncorrectLoginNameMassage() {
        showToastMessage(R.string.invalid_username);
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

    @UiThread
    public void showToastMessage(int messageId) {
        snackBar.applyStyle(R.style.SnackBarError);
        snackBar.actionText(R.string.retry);
        snackBar.text(messageId).actionClickListener(new SnackBar.OnActionClickListener() {
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

//    public boolean onBackPressed() {
//        return presenter.backButtonClicked();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
