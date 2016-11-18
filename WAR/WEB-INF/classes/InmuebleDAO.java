import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InmuebleDAO {

    private static class TablaInmueble {
	public static final int ID_INMUEBLE = 1;
	public static final int PRECIO = 2;
	public static final int SUPERFICIE = 3;
	public static final int PLANTA = 4;
	public static final int NUM_HABITACIONES = 5;
	public static final int NUM_BAGNOS = 6;
	public static final int DESCRIPCION = 7;
	public static final int SE_VENDE = 8;
	public static final int SE_ALQUILA = 9;
	public static final int ID_TIPO_INMUEBLE = 10;
	public static final int ID_USUARIO = 11;
	public static final int ID_PAIS = 12;
	public static final int ID_PROVINCIA = 13;
	public static final int POBLACION = 14;
	public static final int NOMBRE_DIR = 15;
	public static final int NUMERO_DIR = 16;
	public static final int ID_VIA = 17;
    }

    public static void insertarInmueble(InmuebleVO inmueble, List<String> imagenes, Connection connection) {
	String query = "INSERT INTO inmueble(precio,superficie,"
		+ "planta,num_habitaciones,num_bagnos,descripcion,sevende,"
		+ "sealquila,idTipoInmueble,idusuario,idpais,idprovincia,"
		+ "poblacion,nombredir,numerodir,idvia) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setDouble(1, inmueble.getPrecio());
	    preparedStatement.setInt(2, inmueble.getSuperficie());
	    preparedStatement.setInt(3, inmueble.getPlanta());
	    preparedStatement.setInt(4, inmueble.getNumHabitaciones());
	    preparedStatement.setInt(5, inmueble.getNumBagnos());
	    preparedStatement.setString(6, inmueble.getDescripcion());
	    preparedStatement.setBoolean(7, inmueble.seVende());
	    preparedStatement.setBoolean(8, inmueble.seAlquila());
	    preparedStatement.setInt(9, inmueble.getIdTipoInmueble().getIdTipo());
	    preparedStatement.setString(10, inmueble.getIdUsuario().getidusuario());
	    preparedStatement.setInt(11, inmueble.getLocalizacion().getProvincia().getPais().getIdPais());
	    preparedStatement.setInt(12, inmueble.getLocalizacion().getProvincia().getIdProvincia());
	    preparedStatement.setString(13, inmueble.getLocalizacion().getPoblacion());
	    preparedStatement.setString(14, inmueble.getLocalizacion().getNombreDir());
	    preparedStatement.setInt(15, inmueble.getLocalizacion().getNumeroDir());
	    preparedStatement.setInt(16, inmueble.getLocalizacion().getNumeroDir());
	    preparedStatement.executeUpdate();

	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    public static List<InmuebleVO> getInmuebles(String palabraBusqueda, int precioMin, int precioMax, int superficieMin,
	    int superficieMax, Connection connection) {
	List<InmuebleVO> listaInmuebles = new ArrayList<InmuebleVO>();
	String query = "SELECT * FROM inmueble WHERE ";
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
		int idInmueble = resultSet.getInt(TablaInmueble.ID_INMUEBLE);
		double precio = resultSet.getDouble(TablaInmueble.PRECIO);
		int superficie = resultSet.getInt(TablaInmueble.SUPERFICIE);
		int planta = resultSet.getInt(TablaInmueble.PLANTA);
		int numHabitaciones = resultSet.getInt(TablaInmueble.NUM_HABITACIONES);
		int numBagnos = resultSet.getInt(TablaInmueble.NUM_BAGNOS);
		String descripcion = resultSet.getString(TablaInmueble.DESCRIPCION);
		boolean seVende = resultSet.getBoolean(TablaInmueble.SE_VENDE);
		boolean seAlquila = resultSet.getBoolean(TablaInmueble.SE_ALQUILA);
		int idTipoInmueble = resultSet.getInt(TablaInmueble.ID_TIPO_INMUEBLE);
		String idUsuario = resultSet.getString(TablaInmueble.ID_USUARIO);
		int idPais = resultSet.getInt(TablaInmueble.ID_PAIS);
		int idProvincia = resultSet.getInt(TablaInmueble.ID_PROVINCIA);
		String poblacion = resultSet.getString(TablaInmueble.POBLACION);
		String nombreDir = resultSet.getString(TablaInmueble.NOMBRE_DIR);
		int numeroDir = resultSet.getInt(TablaInmueble.NUMERO_DIR);
		int idVia = resultSet.getInt(TablaInmueble.ID_VIA);
		listaInmuebles.add(new InmuebleVO(idInmueble, precio, superficie, planta, numHabitaciones, numBagnos,
			descripcion, seVende, seAlquila, TipoInmuebleDAO.getTipoInmueble(idTipoInmueble, connection),
			UsuarioRegistradoDAO.encontrarDatosUsuario(idUsuario, connection),
			LocalizacionDAO.obtenerLocalizacion(idPais, idProvincia, poblacion, nombreDir, numeroDir, idVia,
				connection)));

	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return listaInmuebles;
    }
}
