package db.vo;

public class PaisVO {
    private int idPais = 0;
    private String nombrePais;

    public PaisVO() {

    }

    public PaisVO(int idPais, String nombrePais) {
        this.idPais = idPais;
        this.nombrePais = nombrePais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getIdPais() {
        return this.idPais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNombrePais() {
        return this.nombrePais;
    }
}