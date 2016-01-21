package com.ccf.logic.restclient.webservice;

import android.content.Context;

import com.ccf.logic.login.Picture;
import com.ccf.logic.login.User;
import com.ccf.logic.login.repository.LoginRepository;
import com.ccf.logic.restclient.webservice.picture.PictureEntity;
import com.ccf.logic.restclient.webservice.picture.PictureEntityMapper;
import com.ccf.logic.restclient.webservice.picture.PictureEntityRequest;
import com.ccf.logic.restclient.webservice.user.UserEntity;
import com.ccf.logic.restclient.webservice.user.UserEntityMapper;
import com.ccf.logic.restclient.webservice.user.UserEntityRequest;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.PendingRequestListener;
import com.octo.android.robospice.request.listener.RequestListener;

import rx.Observable;

public class LoginRepositoryRest implements LoginRepository {
    private SpiceManager spiceManager = new SpiceManager(JacksonSpringAndroidSpiceService.class);
    private UserEntityMapper userEntityMapper = new UserEntityMapper();
    private PictureEntityMapper pictureEntityMapper = new PictureEntityMapper();

    @Override
    public Observable<User> init(Object context) {
        return rx.Observable.create(subscriber -> {
            if (spiceManager.isStarted() == false) {
                spiceManager.start((Context) context);
                subscriber.onCompleted();
            } else {
                spiceManager.addListenerIfPending(UserEntity.class, null, new PendingRequestListener<UserEntity>() {
                    @Override
                    public void onRequestNotFound() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onRequestFailure(SpiceException spiceException) {
                        subscriber.onError(spiceException);
                    }

                    @Override
                    public void onRequestSuccess(UserEntity userEntity) {
                        User user = userEntityMapper.transform(userEntity);
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }

    @Override
    public Observable<User> getUser(String login) {
        return rx.Observable.create(subscriber -> {
            UserEntityRequest request = new UserEntityRequest(login);
            spiceManager.execute(request, new RequestListener<UserEntity>() {
                @Override
                public void onRequestFailure(SpiceException spiceException) {
                    subscriber.onError(spiceException);
                    subscriber.onCompleted();
                }

                @Override
                public void onRequestSuccess(UserEntity userEntity) {
                    User user = userEntityMapper.transform(userEntity);
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            });
        });
    }

    @Override
    public Observable<Picture> getUserPicture(String login) {
        return rx.Observable.create(subscriber -> {
            PictureEntityRequest request = new PictureEntityRequest(login);
            spiceManager.execute(request, new RequestListener<PictureEntity>() {
                @Override
                public void onRequestFailure(SpiceException spiceException) {
                    subscriber.onError(spiceException);
                    subscriber.onCompleted();
                }

                @Override
                public void onRequestSuccess(PictureEntity pictureEntity) {
                    Picture picture = pictureEntityMapper.transform(pictureEntity);
                    subscriber.onNext(picture);
                    subscriber.onCompleted();
                }
            });
        });
    }
}
