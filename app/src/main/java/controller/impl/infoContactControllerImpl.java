package controller.impl;

import android.content.Context;
import android.graphics.Bitmap;

import controller.infoContactController;
import models.Contactos;
import view.infoContactView;

public class infoContactControllerImpl implements infoContactController {
    private infoContactView view;

    public infoContactControllerImpl(Context context){
        this.view = (infoContactView) context;
    }

    @Override
    public void getData(Contactos e) {
        Bitmap bm = utils.ImagesConvert.convertBitmap(e.getFoto());
        this.view.fillData(e, bm);
    }
}
