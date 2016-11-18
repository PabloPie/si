package db.vo;

import java.time.LocalDate;

public class UsuarioRegistradoVO {
    private String idusuario = "";
    private String nombre = "";
    private String apellidos = "";
    private LocalDate fecha;
    private String claveEncriptada = "";
    private int telefono;
    private String email = "";
    private LocalizacionVO location;

    public UsuarioRegistradoVO(String idusuario, String nombre,
            String apellido, String claveEncriptada, int telefono,
            String email, LocalDate fecha, LocalizacionVO location) {
        if (idusuario != null)
            this.idusuario = idusuario;
        if (nombre != null)
            this.nombre = nombre;
        if (apellidos != null)
            this.apellidos = apellido;
        if (claveEncriptada != null)
            this.claveEncriptada = claveEncriptada;
        if (telefono > 600000000)
            this.telefono = telefono;
        if (email != null)
            this.email = email;
        if (location != null)
            this.location = location;
        if (fecha != null)
            this.fecha = fecha;

    }

    public String getidusuario() {
        return this.idusuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(int dia, int mes, int ano) {
        fecha = LocalDate.of(ano, mes, dia);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getClaveEncriptada() {
        return this.claveEncriptada;
    }

    public void setClaveEncriptada(String claveEncriptada) {
        this.claveEncriptada = claveEncriptada;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalizacionVO getLocation() {
        return location;
    }

    public void setLocation(LocalizacionVO location) {
        this.location = location;
    }

}
