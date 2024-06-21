package controller;

import java.util.ArrayList;

import models.Contactos;

public interface listController {
    void getData(ArrayList<Contactos> List);
    void deleteContact(Long status);
}
