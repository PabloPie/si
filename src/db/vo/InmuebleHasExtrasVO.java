package db.vo;

public class InmuebleHasExtrasVO {
    private int idInmueble;
    private int idExtras;

    public InmuebleHasExtrasVO(int idInmueble, int idExtras) {
        this.idInmueble = idInmueble;
        this.idExtras = idExtras;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public int getIdExtras() {
        return idExtras;
    }

    public void setIdExtras(int idExtras) {
        this.idExtras = idExtras;
    }
}
