package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

import db.vo.LocalizacionVO;
import db.vo.PaisVO;
import db.vo.ProvinciaVO;
import db.vo.TiposDeViaVO;
import db.vo.UsuarioRegistradoVO;

public class UsuarioRegistradoDAO {

    public static void insertarUsuario(UsuarioRegistradoVO usuario,
            Connection connection) {
        try {
            /* Create "preparedStatement". */
            String queryString = "INSERT INTO usuario_registrado "
                    + "(idusuario, nombre, apellidos, email, telefono, contraseña, fecha, idpais,"
                    + "idprovincia, poblacion, nombredir, numerodir, idvia) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection
                    .prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, usuario.getidusuario());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            Date date = Date.valueOf(usuario.getFecha());
            preparedStatement.setDate(4, date);
            preparedStatement.setString(5, usuario.getClaveEncriptada());
            preparedStatement.setInt(6, usuario.getTelefono());
            preparedStatement.setString(7, usuario.getEmail());
            LocalizacionVO loc = usuario.getLocation();
            ProvinciaVO provincia = loc.getProvincia();
            int idprovincia = provincia.getIdProvincia();
            PaisVO pais = provincia.getPais();
            int idpais = pais.getIdPais();
            String poblacion = loc.getPoblacion();
            String nombredir = loc.getNombreDir();
            int numerodir = loc.getNumeroDir();
            TiposDeViaVO via = loc.getTipoVia();
            int idvia = via.getIdVia();
            preparedStatement.setInt(8, idpais);
            preparedStatement.setInt(9, idprovincia);
            preparedStatement.setString(10, poblacion);
            preparedStatement.setString(11, nombredir);
            preparedStatement.setInt(12, numerodir);
            preparedStatement.setInt(13, idvia);

            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException("Problemas insertando usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void actualizarUsuario(UsuarioRegistradoVO usuario,
            Connection connection) {
        try {
            /* Create "preparedStatement". */
            String queryString = "UPDATE usuario "
                    + "SET nombre = ?, apellidos = ?, email = ?, telefono = ?, "
                    + " contraseña = ?, fecha = ?, idpais = ?, idprovincia = ?, poblacion = ?, nombredir = ?,"
                    + "numerodir = ?, idvia = ? WHERE  idusuario = ?)";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidos());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setInt(4, usuario.getTelefono());
            preparedStatement.setString(5, usuario.getClaveEncriptada());
            Date date = Date.valueOf(usuario.getFecha());
            preparedStatement.setDate(6, date);
            LocalizacionVO loc = usuario.getLocation();
            ProvinciaVO provincia = loc.getProvincia();
            int idprovincia = provincia.getIdProvincia();
            PaisVO pais = provincia.getPais();
            int idpais = pais.getIdPais();
            String poblacion = loc.getPoblacion();
            String nombredir = loc.getNombreDir();
            int numerodir = loc.getNumeroDir();
            TiposDeViaVO via = loc.getTipoVia();
            int idvia = via.getIdVia();
            preparedStatement.setInt(7, idpais);
            preparedStatement.setInt(8, idprovincia);
            preparedStatement.setString(9, poblacion);
            preparedStatement.setString(10, nombredir);
            preparedStatement.setInt(11, numerodir);
            preparedStatement.setInt(12, idvia);
            preparedStatement.setString(13, usuario.getidusuario());

            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException("Problemas actualizando usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static UsuarioRegistradoVO encontrarDatosUsuario(
            String idusuarioUsuario, Connection connection) {
        UsuarioRegistradoVO usuarioVO = null;
        try {
            /* Create "preparedStatement". */
            String queryString = "SELECT nombre, apellidos, email, telefono, contraseña, fecha, idpais,"
                    + "idprovincia, poblacion, nombredir, numerodir, idvia WHERE  idusuario = ?";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, idusuarioUsuario);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.first()) {
                throw new SQLException("Usuario no encontrado.");
            }

            /* Execute query. */
            String nombre = resultSet.getString(1);
            String apellidos = resultSet.getString(2);
            String email = resultSet.getString(3);
            int telefono = resultSet.getInt(4);
            String claveEncriptada = resultSet.getString(5);
            Date fecha = resultSet.getDate(6);
            LocalDate date = fecha.toLocalDate();
            int idpais = resultSet.getInt(7);
            int idprovincia = resultSet.getInt(8);
            String poblacion = resultSet.getString(9);
            String nombredir = resultSet.getString(10);
            int numerodir = resultSet.getInt(11);
            int idvia = resultSet.getInt(12);

            LocalizacionVO location = LocalizacionDAO.obtenerLocalizacion(
                    idpais, idprovincia, poblacion, nombredir, numerodir,
                    idvia, connection);

            usuarioVO = new UsuarioRegistradoVO(idusuarioUsuario, nombre,
                    apellidos, claveEncriptada, telefono, email, date,
                    location);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return usuarioVO;
    }
}
