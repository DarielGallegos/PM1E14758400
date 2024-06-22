package controller;

import models.Contactos;

public interface mainController {
    void insertContact(Long status);
    void findById(Contactos e);
    void updateContact(int status);
}
