package com.ccf.android.repository.login.webservice.user;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class UserEntityRequest extends SpringAndroidSpiceRequest<UserEntity> {
    private final String login;

    public UserEntityRequest(String login) {
        super(UserEntity.class);
        this.login = login;
    }

    @Override
    public UserEntity loadDataFromNetwork() throws Exception {
        String url = String.format("http://192.168.1.130:8090/login/%s", login);
        return getRestTemplate().getForObject(url, UserEntity.class);
    }

    public String createCacheKey() {
        return "login." + login;
    }
}
