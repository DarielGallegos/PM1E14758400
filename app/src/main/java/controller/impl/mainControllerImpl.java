package controller.impl;

import android.content.Context;
import android.graphics.Bitmap;

import controller.mainController;
import models.Contactos;
import view.mainView;

public class mainControllerImpl implements mainController {
    private final mainView view;
    public mainControllerImpl(Context context){
        this.view = (mainView) context;
    }

    @Override
    public void insertContact(Long status) {
        String msg = (status != -1) ? "Contacto Insertado" : "No se pudo insertar el contacto";
        this.view.insertContact(msg);
    }

    @Override
    public void findById(Contactos e) {
        Bitmap bm = utils.ImagesConvert.convertBitmap(e.getFoto());
        this.view.findById(e, bm);
    }

    @Override
    public void updateContact(int status) {
        this.view.updateContact((status != -1) ? "Contacto Actualizado" : "No se pudo actualizar el contacto");
    }
}
