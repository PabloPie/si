package db.vo;

import java.util.List;

/**
 * Clase que representa un inmueble
 */
public class InmuebleVO {
    private int idInmueble;
    private double precio;
    private int superficie;
    private int planta;
    private int numHabitaciones;
    private int numBagnos;
    private String descripcion;
    private boolean seVende;
    private boolean seAlquila;
    private TipoInmuebleVO idTipoInmueble;
    private UsuarioRegistradoVO idUsuario;
    private LocalizacionVO localizacion;
    private List<ExtrasVO> extras;


    public InmuebleVO(int idInmueble, double precio, int superficie,
                      int planta, int numHabitaciones, int numBagnos, String descripcion,
                      boolean seVende, boolean seAlquila, TipoInmuebleVO idTipoInmueble,
                      UsuarioRegistradoVO idUsuario, LocalizacionVO localizacion, List<ExtrasVO> extras) {
        this.idInmueble = idInmueble;
        this.precio = precio;
        this.superficie = superficie;
        this.planta = planta;
        this.numHabitaciones = numHabitaciones;
        this.numBagnos = numBagnos;
        this.descripcion = descripcion;
        this.seVende = seVende;
        this.seAlquila = seAlquila;
        this.idTipoInmueble = idTipoInmueble;
        this.idUsuario = idUsuario;
        this.localizacion = localizacion;
        this.extras = extras;

    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public int getNumBagnos() {
        return numBagnos;
    }

    public void setNumBagnos(int numBagnos) {
        this.numBagnos = numBagnos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean seVende() {
        return seVende;
    }

    public void setSeVende(boolean seVende) {
        this.seVende = seVende;
    }

    public boolean seAlquila() {
        return seAlquila;
    }

    public void setSeAlquila(boolean seAlquila) {
        this.seAlquila = seAlquila;
    }

    public TipoInmuebleVO getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(TipoInmuebleVO idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }

    public UsuarioRegistradoVO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioRegistradoVO idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalizacionVO getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(LocalizacionVO localizacion) {
        this.localizacion = localizacion;
    }

    public List<ExtrasVO> getExtras() { return extras; }

    public void setExtras(List<ExtrasVO> extras) { this.extras = extras; }

}
