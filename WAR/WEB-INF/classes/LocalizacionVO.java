public class LocalizacionVO {
    private int numeroDir, codigoPostal = 0;
    private String poblacion, nombreDir;
    private TiposDeViaVO tipoVia;
    private ProvinciaVO provincia;

    public LocalizacionVO(int numeroDir, int codigoPostal, String poblacion, String nombreDir, TiposDeViaVO tipoVia,
	    ProvinciaVO provincia) {
	this.numeroDir = numeroDir;
	this.codigoPostal = codigoPostal;
	this.poblacion = poblacion;
	this.nombreDir = nombreDir;
	this.tipoVia = tipoVia;
	this.provincia = provincia;
    }

    public void setNumeroDir(int numeroDir) {
	this.numeroDir = numeroDir;
    }

    public int getNumeroDir() {
	return this.numeroDir;
    }

    public void setCodigoPostal(int codigoPostal) {
	this.codigoPostal = codigoPostal;
    }

    public int getCodigoPostal() {
	return this.codigoPostal;
    }

    public void setPoblacion(String poblacion) {
	this.poblacion = poblacion;
    }

    public String getPoblacion() {
	return this.poblacion;
    }

    public void setNombreDir(String nombreDir) {
	this.nombreDir = nombreDir;
    }

    public String getNombreDir() {
	return this.nombreDir;
    }

    public void setTipoVia(TiposDeViaVO tipoVia) {
	this.tipoVia = tipoVia;
    }

    public TiposDeViaVO getTipoVia() {
	return this.tipoVia;
    }

    public void setProvincia(ProvinciaVO provincia) {
	this.provincia = provincia;
    }

    public ProvinciaVO getProvincia() {
	return this.provincia;
    }

}
