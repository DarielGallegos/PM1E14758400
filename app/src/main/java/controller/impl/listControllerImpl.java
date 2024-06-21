package controller.impl;

import static java.util.stream.Collectors.toList;

import android.content.Context;

import java.util.ArrayList;

import controller.listController;
import models.Contactos;
import view.listView;

public class listControllerImpl implements listController {

    private final listView view;

    public listControllerImpl(Context context){
        this.view = (listView) context;
    }

    @Override
    public void getData(ArrayList<Contactos> List) {
        ArrayList<String> list = (ArrayList<String>) List.stream().map(x -> x.getId() + " - " + x.getNombre() + " - " + x.getTelefono()).collect(toList());
        this.view.fillList(list);
    }

    @Override
    public void deleteContact(Long status) {
        this.view.deleteContact((status != -1) ? "Contacto eliminado" : "Error al eliminar contacto");
    }
}
