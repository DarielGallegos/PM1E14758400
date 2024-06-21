package repository.entity;

public class TBL_CONTACTOS {
    public static final String TABLE_NAME = "contactos";
    public static final String ID = "id";
    public static final String NOMBRES = "nombres";
    public static final String PAIS = "pais";
    public static final String TELEFONO = "telefono";
    public static final String NOTA = "nota";
    public static final String FOTO = "foto";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            NOMBRES + " TEXT NOT NULL,"+
            PAIS + " TEXT NOT NULL,"+
            TELEFONO + " TEXT NOT NULL,"+
            NOTA + " TEXT NOT NULL,"+
            FOTO + " BLOB NOT NULL)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String GET_ALL = "SELECT * FROM " + TABLE_NAME;
}
