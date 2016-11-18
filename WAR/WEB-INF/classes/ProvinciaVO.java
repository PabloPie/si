public class ProvinciaVO {
    private int idProvincia = 0;
    private String nombreProvincia;
    private PaisVO pais;

    public ProvinciaVO(int idProvincia, String nombreProvincia, PaisVO pais) {
	this.idProvincia = idProvincia;
	this.nombreProvincia = nombreProvincia;
	this.pais = pais;
    }

    public void setIdProvincia(int idProvincia) {
	this.idProvincia = idProvincia;
    }

    public int getIdProvincia() {
	return this.idProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
	this.nombreProvincia = nombreProvincia;
    }

    public String getNombreProvincia() {
	return this.nombreProvincia;
    }

    public void setPais(PaisVO pais) {
	this.pais = pais;
    }

    public PaisVO getPais() {
	return this.pais;
    }
}