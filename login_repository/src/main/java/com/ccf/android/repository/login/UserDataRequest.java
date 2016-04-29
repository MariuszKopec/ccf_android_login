package com.ccf.android.repository.login;


import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class UserDataRequest extends SpringAndroidSpiceRequest<UserData>
{
    public UserDataRequest() {
        super(UserData.class);
    }

    @Override
    public UserData loadDataFromNetwork() throws Exception {
        //TODO: authentication
        String url = "http://192.168.17.1:8091/messenger/webapi/user-service/1";
        UserData result = getRestTemplate().getForObject(url, UserData.class);
        return result;
    }
}
