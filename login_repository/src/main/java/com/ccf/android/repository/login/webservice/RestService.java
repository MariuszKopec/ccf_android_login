package com.ccf.android.repository.login.webservice;

public class RestService {
//    private SpiceManager spiceManager = new SpiceManager(JacksonSpringAndroidSpiceService.class);
//
//    public void init(Object context, final LoginEntityResponder responder) {
//        if(spiceManager.isStarted() == false)
//            spiceManager.start((Context) context);
//        else
//            spiceManager.addListenerIfPending(UserData.class, null, new PendingRequestListener<UserData>() {
//                @Override
//                public void onRequestNotFound() {
//
//                }
//
//                @Override
//                public void onRequestFailure(SpiceException spiceException) {
//                    responder.onRequestFailure(spiceException);
//                }
//
//                @Override
//                public void onRequestSuccess(UserData userData) {
//                    if (userData != null && responder != null) {
//                        User user = getUserEntity(userData);
//                        responder.onRequestSuccess(user);
//                    }
//                }
//            });
//    }
//
//    public void getLoginEntity(final String user, final LoginEntityResponder responder) {
//        UserDataRequest request = new UserDataRequest();
//        spiceManager.execute(request, new RequestListener<UserData>() {
//            @Override
//            public void onRequestFailure(SpiceException spiceException) {
//                responder.onRequestFailure(spiceException);
//            }
//
//            @Override
//            public void onRequestSuccess(UserData userData) {
//                if (userData != null && responder != null) {
//                    User user = getUserEntity(userData);
//                    responder.onRequestSuccess(user);
//                }
//            }
//        });
//    }
//
//    private User getUserEntity(UserData userData) {
//        User user = new User(userData.id);
//        user.setName(userData.name);
//        user.setLogin(userData.login);
//        user.setLastName(userData.lastName);
//        user.setPassword(userData.password);
//        return user;
//    }
//
//    public void stop() {
//        if(spiceManager.isStarted())
//            spiceManager.shouldStop();
//    }
}
