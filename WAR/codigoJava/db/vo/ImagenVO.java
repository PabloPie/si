package db.vo;

public class ImagenVO {
    private int idInmueble;
    private int idImagen;
    private String ruta;

    public ImagenVO(int idInmueble, int idImagen, String ruta) {
        this.idInmueble = idInmueble;
        this.idImagen = idImagen;
        this.ruta = ruta;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
