package com.ccf.android.repository.login.memory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.ccf.logic.login.Picture;
import com.ccf.logic.login.User;
import com.ccf.logic.login.repository.LoginRepository;
import com.ccf.login.restclient.R;

import org.springframework.util.support.Base64;

import java.io.ByteArrayOutputStream;

import rx.Observable;

public class LoginRepositoryMemory implements LoginRepository {
    private Context context;

    @Override
    public Observable<User> init(Object o) {
        context = (Context) o;
        return rx.Observable.create(subscriber -> {
           // simulateDownload(1000);
           // User user = new User(1L, "Jack", "Sparrow", "login", "password");
           // subscriber.onNext(user);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<User> getUser(String s) {
        return rx.Observable.create(subscriber -> {
            simulateDownload(1000);
            User user = new User(1L, "Jack", "Sparrow", "login", "password");
            subscriber.onNext(user);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<Picture> getUserPicture(String s) {
        return rx.Observable.create(subscriber -> {
            simulateDownload(2000);
            BitmapDrawable drawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.sparrow);
            Bitmap bitmap = drawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            String base64 = Base64.encodeBytes(stream.toByteArray());
            Picture picture = new Picture(base64);
            subscriber.onNext(picture);
            subscriber.onCompleted();
        });
    }

    private void simulateDownload(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
