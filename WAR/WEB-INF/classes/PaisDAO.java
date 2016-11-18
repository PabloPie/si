import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaisDAO {

    public static void insertPais(PaisVO pais, Connection connection) {
	try {
	    /* Create "preparedStatement". */
	    String query = "INSERT INTO pais(idpais,nombrepais) VALUES (?,?)";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);

	    /* Fill "preparedStatement". */
	    preparedStatement.setInt(1, pais.getIdPais());
	    preparedStatement.setString(2, pais.getNombrePais());

	    /* Execute query. */
	    int insertedRows = preparedStatement.executeUpdate();

	    if (insertedRows != 1) {
		throw new SQLException("Insercion con problemas.");
	    }
	} catch (Exception e) {
	    e.printStackTrace(System.err);
	}
    }

    /*
     * public static void updatePais(PaisVO pais){
     * 
     * }
     */
    public static PaisVO obtenerPais(int idPais, Connection connection) {
	PaisVO paisVO = null;
	try {
	    /* Create "preparedStatement". */
	    String queryString = "SELECT nombrepais FROM pais WHERE idpais = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(queryString);

	    /* Fill "preparedStatement". */
	    preparedStatement.setInt(1, idPais);

	    /* Execute query. */
	    ResultSet resultSet = preparedStatement.executeQuery();

	    if (!resultSet.first()) {
		throw new SQLException("Busqueda fallida.");
	    }

	    /* Execute query. */
	    String nombrePais = resultSet.getString(1);

	    paisVO = new PaisVO(idPais, nombrePais);

	} catch (Exception e) {
	    e.printStackTrace(System.err);
	}
	return paisVO;
    }
}
