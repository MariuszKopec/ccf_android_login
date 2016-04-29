package com.ccf.android.repository.login.webservice.picture;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class PictureEntityRequest extends SpringAndroidSpiceRequest<PictureEntity> {
private final String login;

    public PictureEntityRequest(String login) {
        super(PictureEntity.class);
        this.login = login;
    }

    public PictureEntity loadDataFromNetwork() throws Exception {
        String url = String.format("http://192.168.1.130:8090/picture/%s", login);
        return getRestTemplate().getForObject(url, PictureEntity.class);
    }

    public String createCacheKey() {
        return "user.picture." + login;
    }
}
