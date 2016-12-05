package db.vo;

import db.dao.Tablas;

public class ExtrasVO {
    private int idExtra;
    private String nombre;

    public ExtrasVO() {

    }

    public ExtrasVO(int idExtra, String nombre) {
        this.idExtra = idExtra;
        this.nombre = nombre;
    }

    public int getIdExtra() {
        return idExtra;
    }

    public void setIdExtra(int idExtra) {
        this.idExtra = idExtra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
