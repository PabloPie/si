package db.vo;

public class TiposDeViaVO {
    private int idVia = 0;
    private String nombreTipo;

    public TiposDeViaVO(int idVia, String nombreTipo) {
        this.idVia = idVia;
        this.nombreTipo = nombreTipo;
    }

    public void setIdVia(int idVia) {
        this.idVia = idVia;
    }

    public int getIdVia() {
        return this.idVia;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getNombreTipo() {
        return this.nombreTipo;
    }
}