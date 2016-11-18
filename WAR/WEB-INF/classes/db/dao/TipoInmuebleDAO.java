package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.TipoInmuebleVO;

public class TipoInmuebleDAO {

    public static TipoInmuebleVO getTipoInmuebleById(int idTipo,
            Connection connection) {
        String query = "SELECT idTipo, nombreTipo FROM TipoInmueble WHERE idTipo = ?";
        TipoInmuebleVO tipoInmuebleVO = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(query);
            preparedStatement.setInt(1, idTipo);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            int idTipoAux = resultSet.getInt(Tablas.TipoInmueble.ID_TIPO);
            String nombreTipo = resultSet
                    .getString(Tablas.TipoInmueble.NOMBRE_TIPO);
            tipoInmuebleVO = new TipoInmuebleVO(idTipoAux, nombreTipo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoInmuebleVO;
    }

    public static List<TipoInmuebleVO> getAllTipoInmueble(
            Connection connection) {
        String query = "SELECT idTipo, nombreTipo FROM TipoInmueble";
        List<TipoInmuebleVO> tiposInmebles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                int idTipo = resultSet.getInt(Tablas.TipoInmueble.ID_TIPO);
                String nombreTipo = resultSet
                        .getString(Tablas.TipoInmueble.NOMBRE_TIPO);
                tiposInmebles.add(new TipoInmuebleVO(idTipo, nombreTipo));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposInmebles;
    }

}
