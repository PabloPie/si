package db.dao;

import java.sql.*;
import java.time.LocalDate;

import db.WebFacade;
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
                    + "(idusuario, nombre, apellidos, email, telefono, contrasena, fecha, idpais,"
                    + "idprovincia, poblacion, nombredir, numerodir, idvia) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection
                    .prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, usuario.getidusuario());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setNull(5,Types.INTEGER);
            preparedStatement.setString(6, usuario.getClaveEncriptada());
            preparedStatement.setNull(7, Types.DATE);
            preparedStatement.setNull(8, Types.INTEGER);
            preparedStatement.setNull(9, Types.INTEGER);
            preparedStatement.setNull(10, Types.VARCHAR);
            preparedStatement.setNull(11, Types.VARCHAR);
            preparedStatement.setNull(12, Types.INTEGER);
            preparedStatement.setNull(13, Types.INTEGER);


            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException("Problemas insertando usuario.");
            }else{
                System.err.println("Insertado "+usuario.getidusuario()+" en usuario_registrado");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static boolean validarUsuario(String usuario, String password, Connection connection){
        boolean encontrado=false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select idusuario,contrasena from usuario_registrado where idusuario=? and contrasena=?");
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, password);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                encontrado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encontrado;
    }

    public static boolean existeUsuario(String usuario, Connection connection){
        boolean encontrado=false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select idusuario from usuario_registrado where idusuario=?");
            preparedStatement.setString(1, usuario);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                encontrado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encontrado;
    }


    public static void actualizarUsuario(UsuarioRegistradoVO usuario,
            Connection connection) {
        try {
            /* Create "preparedStatement". */
            String queryString = "UPDATE usuario_registrado "
                    + "SET nombre = ?, apellidos = ?, email = ?, telefono = ?, "
                    + " contrasena = ?, fecha = ?, idpais = ?, idprovincia = ?, poblacion = ?, nombredir = ?,"
                    + "numerodir = ?, idvia = ? WHERE  idusuario = ?";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(13, usuario.getidusuario());
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidos());
            Date date=null;
            if(usuario.getFecha()!=null) {
                date = Date.valueOf(usuario.getFecha());
                preparedStatement.setDate(6, date);
            }else{
                preparedStatement.setNull(6, Types.DATE);
            }
            preparedStatement.setString(5, usuario.getClaveEncriptada());
            if(usuario.getTelefono()!=0){
                preparedStatement.setInt(4, usuario.getTelefono());
            }else{
                preparedStatement.setNull(4,Types.INTEGER);
            }
            preparedStatement.setString(3, usuario.getEmail());
            LocalizacionVO loc;
            if(usuario.getLocation()!=null) {
                loc = usuario.getLocation();
                WebFacade.insertarLocalizacion(loc);
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
            }else{
                preparedStatement.setNull(7, Types.INTEGER);
                preparedStatement.setNull(8, Types.INTEGER);
                preparedStatement.setNull(9, Types.VARCHAR);
                preparedStatement.setNull(10, Types.VARCHAR);
                preparedStatement.setNull(11, Types.INTEGER);
                preparedStatement.setNull(12, Types.INTEGER);
            }

            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException("Problemas actualizando usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void borrarUsuario(String usuario, Connection connection){
        try{
            String queryString = "DELETE FROM usuario_registrado WHERE idusuario = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, usuario);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO: CORREGIR TODOS LOS ACCESOS A BASE DE DATOS POR SI HAY NULLS
    public static UsuarioRegistradoVO encontrarDatosUsuario(
            String idusuarioUsuario, Connection connection) {
        UsuarioRegistradoVO usuarioVO = null;
        try {
            /* Create "preparedStatement". */
            String queryString = "SELECT nombre, apellidos, email, telefono, contrasena, fecha, idpais,"
                    + "idprovincia, poblacion, nombredir, numerodir, idvia from usuario_registrado WHERE  idusuario = ?";
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
            LocalDate date=null;
            if (fecha!=null) {
                date = fecha.toLocalDate();
            }
            int idpais = resultSet.getInt(7);
            int idprovincia = resultSet.getInt(8);
            String poblacion = resultSet.getString(9);
            String nombredir = resultSet.getString(10);
            int numerodir = resultSet.getInt(11);
            int idvia = resultSet.getInt(12);

            LocalizacionVO location=null;

            if(idpais!=0 && idprovincia!=0 && poblacion!=null && nombredir!=null
                    && numerodir!=0 && idvia!=0) {
                location = LocalizacionDAO.obtenerLocalizacion(
                        idpais, idprovincia, poblacion, nombredir, numerodir,
                        idvia, connection);
            }

            usuarioVO = new UsuarioRegistradoVO(idusuarioUsuario, nombre,
                    apellidos, claveEncriptada, telefono, email, date,
                    location);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return usuarioVO;
    }
}
