package service.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import controller.impl.infoContactControllerImpl;
import models.Contactos;
import repository.ConnectDB;
import repository.entity.DB_VALUES;
import service.infoContactService;

public class infoContactServiceImpl implements infoContactService {
    private infoContactControllerImpl controller;
    private final ConnectDB connect;
    private SQLiteDatabase db;
    public infoContactServiceImpl(Context context) {
        this.controller = new infoContactControllerImpl(context);
        this.connect = new ConnectDB(context, DB_VALUES.DB_NAME, null, DB_VALUES.VERSION);
    }

    @Override
    public void getData(int id) {
        db = this.connect.getReadableDatabase();
        Cursor cursor = db.rawQuery(repository.entity.TBL_CONTACTOS.GET_BY_ID, new String[]{String.valueOf(id)});
        Contactos e = new Contactos();
        while(cursor.moveToNext()){
            e.setId(cursor.getInt(0));
            e.setNombre(cursor.getString(1));
            e.setPais(cursor.getString(2));
            e.setTelefono(cursor.getString(3));
            e.setNota(cursor.getString(4));
            e.setFoto(cursor.getString(5));
        }
        cursor.close();
        db.close();
        this.controller.getData(e);
    }
}
