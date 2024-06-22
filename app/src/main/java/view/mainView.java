package view;

import android.graphics.Bitmap;

import models.Contactos;

public interface mainView {
    void insertContact(String msg);
    void findById(Contactos e, Bitmap bm);
    void updateContact(String msg);
}
