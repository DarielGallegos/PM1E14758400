package controller.impl;

import android.content.Context;

import controller.mainController;
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
}
