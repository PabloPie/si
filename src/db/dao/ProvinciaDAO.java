package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.PaisVO;
import db.vo.ProvinciaVO;

public class ProvinciaDAO {

    public static void insertProvincia(ProvinciaVO provincia, Connection connection) {
	try {
	    /* Create "preparedStatement". */
	    String query = "INSERT INTO provincia(idpais,idprovincia,nombreprov) VALUES (?,?,?)";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);

	    /* Fill "preparedStatement". */
	    preparedStatement.setInt(1, provincia.getIdProvincia());
	    preparedStatement.setInt(2, provincia.getPais().getIdPais());
	    preparedStatement.setString(3, provincia.getNombreProvincia());

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
     * public void modifyProvincia(ProvinciaVO provincia){
     * 
     * }
     */
    public static ProvinciaVO obtenerProvincia(int idProvincia, Connection connection) {
	ProvinciaVO provinciaVO = null;
        PaisVO paisVO = null;
	try {
	    /* Create "preparedStatement". */
	    String queryString = "SELECT idpais,nombreprov FROM pais WHERE idprovincia = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(queryString);

	    /* Fill "preparedStatement". */
	    preparedStatement.setInt(1, idProvincia);

	    /* Execute query. */
	    ResultSet resultSet = preparedStatement.executeQuery();

	    if (!resultSet.first()) {
		throw new SQLException("Busqueda fallida.");
	    }

	    /* Execute query. */
	    int idPais = resultSet.getInt(1);
	    String nombreProvincia = resultSet.getString(2);

	    // Llamada para obtener el objeto PaisVO
	    paisVO = PaisDAO.obtenerPais(idPais, connection);

	    // Creamos el objeto a devolver
	    provinciaVO = new ProvinciaVO(idProvincia, nombreProvincia, paisVO);

	} catch (Exception e) {
	    e.printStackTrace(System.err);
	}
	return provinciaVO;
    }
    
    public static List<ProvinciaVO> obtenerTodasLasProvincias(Connection connection) {
        List<ProvinciaVO> provincias = new ArrayList<>();
        String queryString = "SELECT idpais,idprovincia,nombreprov FROM provincia";
        try {
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idPais = resultSet.getInt(1);
				int idProvincia = resultSet.getInt(2);
				String nombreProv = resultSet.getString(3);
				provincias.add(new ProvinciaVO(idProvincia, nombreProv, PaisDAO.obtenerPais(idPais, connection)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return provincias;
    }

}
