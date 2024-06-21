package models;

public class Contactos {
    private int id;
    private String Pais;
    private String Nombre;
    private String Telefono;
    private String nota;
    private String foto;

    public Contactos(){}

    public Contactos(String pais, String nombre, String telefono, String nota, String foto) {
        this.Pais = pais;
        this.Nombre = nombre;
        this.Telefono = telefono;
        this.nota = nota;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
