package com.ccf.android.view.login.user_card;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccf.android.ui.card.CcfCard;
import com.ccf.android.ui.widget.Button;
import com.ccf.login.view.R;

public class LoginUserCard implements CcfCard, LoginUserPresenter.LoginUserPresenterView {
    private final LoginUserPresenter presenter;
    private final int cardId;
    private Activity activity;
    private LoginViewHolder viewHolder;

    public LoginUserCard(LoginUserPresenter presenter, int cardId) {
        this.presenter = presenter;
        this.cardId = cardId;
    }

    @Override
    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void setViewHolder(RecyclerView.ViewHolder viewHolder) {
        this.viewHolder = (LoginViewHolder) viewHolder;
        this.viewHolder.setProfileImage();
        this.presenter.setView(this);
        this.presenter.init(activity);
    }

    @Override
    public int getCardType() {
        return cardId;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_login, parent, false);
        return new LoginViewHolder(v);
    }

    private void onNextButtonClicked() {
        String login = viewHolder.login_name.getText().toString();
        presenter.nextButtonClicked(login);
    }

    @Override
    public void disableLoginEdit() {
        viewHolder.login_name.setEnabled(false);
    }

    @Override
    public void disableNextButton() {
        viewHolder.next_button.setEnabledWithAnimation(false);
    }

    @Override
    public void enableLoginEdit() {
        viewHolder.login_name.setEnabled(true);
    }

    @Override
    public void enableNextButton() {
        viewHolder.next_button.setEnabledWithAnimation(true);
    }

    private class LoginViewHolder extends RecyclerView.ViewHolder {
        EditText login_name;
        Button next_button;
        ImageView profile_image;

        public LoginViewHolder(View view) {
            super(view);

            login_name = (EditText) view.findViewById(R.id.login_name);
            login_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        onNextButtonClicked();
                        return true;
                    }
                    return false;
                }
            });
            next_button = (Button) view.findViewById(R.id.next_button);
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNextButtonClicked();
                }
            });
            profile_image = (ImageView) view.findViewById(R.id.profile_image);
        }

        public void setProfileImage() {
            profile_image.setColorFilter(ContextCompat.getColor(activity, R.color.colorSecondary), PorterDuff.Mode.MULTIPLY);
        }
    }
}