package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.ExtrasVO;
import db.vo.InmuebleHasExtrasVO;
import db.vo.InmuebleVO;

public class InmuebleDAO {

    public static void insertarInmueble(InmuebleVO inmueble,
        List<String> imagenes, Connection connection) {
    String query = "INSERT INTO inmueble(precio,superficie,"
        + "planta,num_habitaciones,num_bagnos,descripcion,sevende,"
        + "sealquila,idTipoInmueble,idusuario,idpais,idprovincia,"
        + "poblacion,nombredir,numerodir,idvia) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    try {
        PreparedStatement preparedStatement = connection
            .prepareStatement(query);
        preparedStatement.setDouble(1, inmueble.getPrecio());
        preparedStatement.setInt(2, inmueble.getSuperficie());
        preparedStatement.setInt(3, inmueble.getPlanta());
        preparedStatement.setInt(4, inmueble.getNumHabitaciones());
        preparedStatement.setInt(5, inmueble.getNumBagnos());
        preparedStatement.setString(6, inmueble.getDescripcion());
        preparedStatement.setBoolean(7, inmueble.seVende());
        preparedStatement.setBoolean(8, inmueble.seAlquila());
        preparedStatement.setInt(9, inmueble.getIdTipoInmueble()
            .getIdTipo());
        preparedStatement.setString(10, inmueble.getIdUsuario()
            .getidusuario());
        preparedStatement.setInt(11, inmueble.getLocalizacion()
            .getProvincia().getPais().getIdPais());
        preparedStatement.setInt(12, inmueble.getLocalizacion()
            .getProvincia().getIdProvincia());
        preparedStatement.setString(13, inmueble.getLocalizacion()
            .getPoblacion());
        preparedStatement.setString(14, inmueble.getLocalizacion()
            .getNombreDir());
        preparedStatement.setInt(15, inmueble.getLocalizacion()
            .getNumeroDir());
        preparedStatement.setInt(16, inmueble.getLocalizacion()
            .getNumeroDir());
        preparedStatement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    }

    public static InmuebleVO getInmuebles(int idInm, Connection connection) {
    InmuebleVO listaInmuebles = null;
    String query = "SELECT * FROM inmueble WHERE idInmueble = ?";
    try {
        PreparedStatement preparedStatement = connection
            .prepareStatement(query);
        preparedStatement.setInt(1, idInm);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        int idInmueble = resultSet.getInt(Tablas.Inmueble.ID_INMUEBLE);
        double precio = resultSet.getDouble(Tablas.Inmueble.PRECIO);
        int superficie = resultSet.getInt(Tablas.Inmueble.SUPERFICIE);
        int planta = resultSet.getInt(Tablas.Inmueble.PLANTA);
        int numHabitaciones = resultSet
            .getInt(Tablas.Inmueble.NUM_HABITACIONES);
        int numBagnos = resultSet.getInt(Tablas.Inmueble.NUM_BAGNOS);
        String descripcion = resultSet
            .getString(Tablas.Inmueble.DESCRIPCION);
        boolean seVende = resultSet
            .getBoolean(Tablas.Inmueble.SE_VENDE);
        boolean seAlquila = resultSet
            .getBoolean(Tablas.Inmueble.SE_ALQUILA);
        int idTipoInmueble = resultSet
            .getInt(Tablas.Inmueble.ID_TIPO_INMUEBLE);
        String idUsuario = resultSet
            .getString(Tablas.Inmueble.ID_USUARIO);
        int idPais = resultSet.getInt(Tablas.Inmueble.ID_PAIS);
        int idProvincia = resultSet
            .getInt(Tablas.Inmueble.ID_PROVINCIA);
        String poblacion = resultSet
            .getString(Tablas.Inmueble.POBLACION);
        String nombreDir = resultSet
            .getString(Tablas.Inmueble.NOMBRE_DIR);
        int numeroDir = resultSet.getInt(Tablas.Inmueble.NUMERO_DIR);
        int idVia = resultSet.getInt(Tablas.Inmueble.ID_VIA);
        List<InmuebleHasExtrasVO> inmExtras = InmuebleHasExtrasDAO.getExtrasInmueble(idInm,connection);
            List<ExtrasVO> extras = new ArrayList<>();
            for(InmuebleHasExtrasVO e: inmExtras){
                extras.add(ExtrasDAO.getExtras(e.getIdExtras(),connection));
            }
            listaInmuebles = new InmuebleVO(idInmueble, precio,
            superficie, planta, numHabitaciones, numBagnos,
            descripcion, seVende, seAlquila, TipoInmuebleDAO
                .getTipoInmuebleById(idTipoInmueble, connection),
            UsuarioRegistradoDAO.encontrarDatosUsuario(idUsuario,
                connection), LocalizacionDAO
                .obtenerLocalizacion(idPais, idProvincia,
                    poblacion, nombreDir, numeroDir, idVia,
                    connection),extras);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaInmuebles;
    }
    /**
    public static List<InmuebleVO> getInmuebles(String palabraBusqueda,
        int precioMin, int precioMax, int superficieMin, int superficieMax,
        Connection connection) {
    List<InmuebleVO> listaInmuebles = new ArrayList<InmuebleVO>();
    String query = "SELECT * FROM inmueble WHERE ";
    try {
        PreparedStatement preparedStatement = connection
            .prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        int idInmueble = resultSet.getInt(Tablas.Inmueble.ID_INMUEBLE);
        double precio = resultSet.getDouble(Tablas.Inmueble.PRECIO);
        int superficie = resultSet.getInt(Tablas.Inmueble.SUPERFICIE);
        int planta = resultSet.getInt(Tablas.Inmueble.PLANTA);
        int numHabitaciones = resultSet
            .getInt(Tablas.Inmueble.NUM_HABITACIONES);
        int numBagnos = resultSet.getInt(Tablas.Inmueble.NUM_BAGNOS);
        String descripcion = resultSet
            .getString(Tablas.Inmueble.DESCRIPCION);
        boolean seVende = resultSet
            .getBoolean(Tablas.Inmueble.SE_VENDE);
        boolean seAlquila = resultSet
            .getBoolean(Tablas.Inmueble.SE_ALQUILA);
        int idTipoInmueble = resultSet
            .getInt(Tablas.Inmueble.ID_TIPO_INMUEBLE);
        String idUsuario = resultSet
            .getString(Tablas.Inmueble.ID_USUARIO);
        int idPais = resultSet.getInt(Tablas.Inmueble.ID_PAIS);
        int idProvincia = resultSet
            .getInt(Tablas.Inmueble.ID_PROVINCIA);
        String poblacion = resultSet
            .getString(Tablas.Inmueble.POBLACION);
        String nombreDir = resultSet
            .getString(Tablas.Inmueble.NOMBRE_DIR);
        int numeroDir = resultSet.getInt(Tablas.Inmueble.NUMERO_DIR);
        int idVia = resultSet.getInt(Tablas.Inmueble.ID_VIA);
        listaInmuebles.add(new InmuebleVO(idInmueble, precio,
            superficie, planta, numHabitaciones, numBagnos,
            descripcion, seVende, seAlquila, TipoInmuebleDAO
                .getTipoInmuebleById(idTipoInmueble, connection),
            UsuarioRegistradoDAO.encontrarDatosUsuario(idUsuario,
                connection), LocalizacionDAO
                .obtenerLocalizacion(idPais, idProvincia,
                    poblacion, nombreDir, numeroDir, idVia,
                    connection)));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaInmuebles;
    }
     **/
}
