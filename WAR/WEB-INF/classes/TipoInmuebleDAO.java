import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoInmuebleDAO {

    private static class TablaTipoInmueble {
	public static final int ID_TIPO = 1;
	public static final int NOMBRE_TIPO = 2;

    }

    public static TipoInmuebleVO getTipoInmueble(int idTipo, Connection connection) {
	String query = "SELECT idTipo, nombreTipo FROM TipoInmueble WHERE idTipo = ?";
	TipoInmuebleVO tipoInmuebleVO = null;
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, idTipo);
	    ResultSet resultSet = preparedStatement.executeQuery(query);
	    int idTipoAux = resultSet.getInt(TablaTipoInmueble.ID_TIPO);
	    String nombreTipo = resultSet.getString(TablaTipoInmueble.NOMBRE_TIPO);
	    tipoInmuebleVO = new TipoInmuebleVO(idTipoAux, nombreTipo);

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return tipoInmuebleVO;
    }
}
