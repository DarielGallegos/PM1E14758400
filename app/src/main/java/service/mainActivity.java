package service;

import models.Contactos;

public interface mainActivity {
    void insertContact(Contactos e);
    void findById(int id);
    void updateContact(Contactos e, int id);
}
