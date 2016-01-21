package com.ccf.logic.restclient.webservice.picture;

import com.ccf.logic.login.Picture;

public class PictureEntityMapper {
    public Picture transform(PictureEntity pictureEntity) {
        if(pictureEntity == null)
            return null;
        else
            return new Picture(pictureEntity.getPicture());
    }
}
