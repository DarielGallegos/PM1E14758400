package service.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import controller.impl.mainControllerImpl;
import models.Contactos;
import repository.ConnectDB;
import repository.entity.DB_VALUES;
import service.mainActivity;
import repository.entity.TBL_CONTACTOS;

public class mainActivityImpl implements mainActivity {
    private mainControllerImpl controller;
    private final ConnectDB connect;
    private static SQLiteDatabase db;
    public mainActivityImpl(Context context) {
        this.controller = new mainControllerImpl(context);
        this.connect = new ConnectDB(context, DB_VALUES.DB_NAME, null, DB_VALUES.VERSION);
    }

    @Override
    public void insertContact(Contactos e) {
        try{
            db = this.connect.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TBL_CONTACTOS.NOMBRES, e.getNombre());
            values.put(TBL_CONTACTOS.TELEFONO, e.getTelefono());
            values.put(TBL_CONTACTOS.PAIS, e.getPais());
            values.put(TBL_CONTACTOS.NOTA, e.getNota());
            values.put(TBL_CONTACTOS.FOTO, e.getFoto());
            Long status = db.insert(TBL_CONTACTOS.TABLE_NAME, null, values);
            this.controller.insertContact(status);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void findById(int id) {
        db = this.connect.getReadableDatabase();
        Contactos e = new Contactos();
        Cursor cursor = db.rawQuery(repository.entity.TBL_CONTACTOS.GET_BY_ID, new String[]{String.valueOf(id)});
        while(cursor.moveToNext()){
            e.setId(cursor.getInt(0));
            e.setNombre(cursor.getString(1));
            e.setPais(cursor.getString(2));
            e.setTelefono(cursor.getString(3));
            e.setNota(cursor.getString(4));
            e.setFoto(cursor.getString(5));
        }
        db.close();
        cursor.close();
        this.controller.findById(e);
    }

    @Override
    public void updateContact(Contactos e, int id) {
        db = this.connect.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TBL_CONTACTOS.NOMBRES, e.getNombre());
        values.put(TBL_CONTACTOS.TELEFONO, e.getTelefono());
        values.put(TBL_CONTACTOS.PAIS, e.getPais());
        values.put(TBL_CONTACTOS.NOTA, e.getNota());
        values.put(TBL_CONTACTOS.FOTO, e.getFoto());
        try{
            int status = db.update(TBL_CONTACTOS.TABLE_NAME, values, TBL_CONTACTOS.ID + " = ?", new String[]{String.valueOf(id)});
            this.controller.updateContact(status);
        }catch(SQLiteException ex){
            throw new SQLiteException(ex.getMessage());
        }
    }
}
