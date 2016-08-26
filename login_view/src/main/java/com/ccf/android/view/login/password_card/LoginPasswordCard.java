package com.ccf.android.view.login.password_card;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.ccf.android.ui.animation.DefaultAnimationListener;
import com.ccf.android.ui.card.CcfCard;
import com.ccf.android.ui.widget.BackImageView;
import com.ccf.android.ui.widget.Button;
import com.ccf.android.ui.widget.CircleImageView;
import com.ccf.android.ui.widget.PasswordEditText;
import com.ccf.login.view.R;

public class LoginPasswordCard implements CcfCard, LoginPasswordPresenter.View {
    private final LoginPasswordPresenter presenter;
    private final int cardId;
    private Activity activity;
    private ViewHolder viewHolder;

    public LoginPasswordCard(LoginPasswordPresenter presenter, int cardId) {
        this.presenter = presenter;
        this.cardId = cardId;
    }

    @Override
    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void setViewHolder(RecyclerView.ViewHolder viewHolder) {
        this.viewHolder = (ViewHolder) viewHolder;
        this.viewHolder.setProfileImage();
        this.presenter.setView(this);
        this.presenter.init();
    }

    @Override
    public int getCardType() {
        return cardId;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_password, parent, false);
        return new ViewHolder(v);
    }

    private void onLoginButtonClicked() {
        String password = viewHolder.login_password.getText().toString();
        presenter.loginButtonClicked(password);
    }

    @Override
    public void setUserPicture(Bitmap bitmap) {
        final BitmapDrawable drawable = new BitmapDrawable(activity.getResources(), bitmap);
        Animation fadeOut = AnimationUtils.loadAnimation(activity, R.anim.fade_out);
        fadeOut.setAnimationListener(new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                viewHolder.profile_image.setImageDrawable(drawable);
                viewHolder.profile_image.setColorFilter(null);
                viewHolder.profile_image.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.fade_in));
            }
        });
        viewHolder.profile_image.startAnimation(fadeOut);
    }

    @Override
    public void disablePasswordEdit() {
        viewHolder.login_password.setEnabled(false);
    }

    @Override
    public void disableBackButton() {
        viewHolder.back_arrow.setEnabledWithAnimation(false);
    }

    @Override
    public void enablePasswordEdit() {
        viewHolder.login_password.setEnabled(true);
    }

    @Override
    public void enableLoginButton() {
        viewHolder.login_button.setEnabledWithAnimation(true);
    }

    @Override
    public void enableBackButton() {
        viewHolder.back_arrow.setEnabledWithAnimation(true);
    }

    private void onBackArrowClicked() {
        presenter.backButtonClicked();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        PasswordEditText login_password;
        TextView user_name_lastname;
        Button login_button;
        TextView logging_text;
        BackImageView back_arrow;
        CircleImageView profile_image;

        ViewHolder(View view) {
            super(view);

            login_password = (PasswordEditText) view.findViewById(R.id.login_password);
            login_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        onLoginButtonClicked();
                        return true;
                    }
                    return false;
                }
            });
            user_name_lastname = (TextView) view.findViewById(R.id.user_name_lastname);
            login_button = (Button) view.findViewById(R.id.login_button);
            login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onLoginButtonClicked();
                }
            });
            logging_text = (TextView) view.findViewById(R.id.logging_text);
            back_arrow = (BackImageView) view.findViewById(R.id.back_arrow);
            back_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackArrowClicked();
                }
            });
            profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        }

        public void setProfileImage() {
            profile_image.setColorFilter(ContextCompat.getColor(activity, R.color.colorSecondary), PorterDuff.Mode.MULTIPLY);
        }
    }
}