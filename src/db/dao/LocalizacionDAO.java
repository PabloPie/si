package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.vo.LocalizacionVO;
import db.vo.ProvinciaVO;
import db.vo.TiposDeViaVO;

public class LocalizacionDAO {

    public static void insertLocalizacion(LocalizacionVO localizacion, Connection connection) {
        try {
            /* Create "preparedStatement". */
            String query = "INSERT INTO localizacion(idpais,idprovincia,poblacion, nombredir,numerodir,codigopostal," +
                    "idvia) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            /* Fill "preparedStatement". */
            preparedStatement.setInt(1, localizacion.getProvincia().getPais().getIdPais());
            preparedStatement.setInt(2, localizacion.getProvincia().getIdProvincia());
            preparedStatement.setString(3, localizacion.getPoblacion());
            preparedStatement.setString(4, localizacion.getNombreDir());
            preparedStatement.setInt(5, localizacion.getNumeroDir());
            preparedStatement.setInt(6, localizacion.getCodigoPostal());
            preparedStatement.setInt(7, localizacion.getTipoVia().getIdVia());

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
     * public void updateLocalizacion(ProvinciaVO provincia){
     * 
     * }
     */
    public static LocalizacionVO obtenerLocalizacion(int idPais, int idProvincia, String poblacion, String nombreDir,
                                                     int numeroDir, int idVia, Connection connection) {
        LocalizacionVO localizacionVO = null;
        ProvinciaVO provinciaVO = null;
        TiposDeViaVO tiposDeViaVO = null;
        try {
            /* Create "preparedStatement". */
            String queryString = "SELECT codigopostal FROM localizacion WHERE idpais = ? AND idprovincia = ? AND " +
                    "poblacion = ? AND nombredir = ? AND numerodir = ? AND idvia = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setInt(1, idPais);
            preparedStatement.setInt(2, idProvincia);
            preparedStatement.setString(3, poblacion);
            preparedStatement.setString(4, nombreDir);
            preparedStatement.setInt(5, numeroDir);
            preparedStatement.setInt(6, idVia);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.first()) {
                throw new SQLException("Busqueda fallida.");
            }

            /* Execute query. */
            int codigoPostal = resultSet.getInt(1);

            // Llamada para obtener el objeto TiposDeViaVO
            tiposDeViaVO = TiposDeViaDAO.obtenerTiposDeVia(idVia, connection);

            // Llamada para obtener el objeto ProvinciaVO
            provinciaVO = ProvinciaDAO.obtenerProvincia(idProvincia, connection);

            // Creamos el objeto a devolver
            localizacionVO = new LocalizacionVO(numeroDir, codigoPostal, poblacion, nombreDir, tiposDeViaVO,
                    provinciaVO);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return localizacionVO;
    }
}
