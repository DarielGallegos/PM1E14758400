package service.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import controller.impl.listControllerImpl;
import models.Contactos;
import repository.ConnectDB;
import service.listService;
import repository.entity.DB_VALUES;

public class listServiceImpl implements listService {
    private listControllerImpl controller;
    private final ConnectDB connect;
    private SQLiteDatabase db;

    public listServiceImpl(Context context){
        this.controller = new listControllerImpl(context);
        this.connect = new ConnectDB(context, DB_VALUES.DB_NAME, null, DB_VALUES.VERSION);
    }


    @Override
    public void getData() {
        db = this.connect.getReadableDatabase();
        Cursor cursor = db.rawQuery(repository.entity.TBL_CONTACTOS.GET_ALL, null);
        ArrayList<Contactos> list = new ArrayList<>();
        Contactos e;
        while(cursor.moveToNext()){
            e = new Contactos();
            e.setId(cursor.getInt(0));
            e.setNombre(cursor.getString(1));
            e.setPais(cursor.getString(2));
            e.setTelefono(cursor.getString(3));
            e.setNota(cursor.getString(4));
            e.setFoto(cursor.getString(5));
            list.add(e);
        }
        cursor.close();
        db.close();
        this.controller.getData(list);
    }
}
