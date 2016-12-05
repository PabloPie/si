package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.InmuebleHasExtrasVO;

public class InmuebleHasExtrasDAO {

    public static List<InmuebleHasExtrasVO> getExtrasInmueble(int idInmueble, Connection connection) {
        List<InmuebleHasExtrasVO> listaIdExtras = new ArrayList<InmuebleHasExtrasVO>();
        String query = "SELECT idInmueble, idExtras FROM inmueble_has_extras WHERE idInmueble = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idInmueble);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idInm = resultSet.getInt(Tablas.InmuebleHasExtras.ID_INMUEBLE);
                int idExtra = resultSet.getInt(Tablas.InmuebleHasExtras.ID_EXTRAS);
                listaIdExtras.add(new InmuebleHasExtrasVO(idInm, idExtra));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaIdExtras;
    }

    public static void insertar(List<InmuebleHasExtrasVO> lista, Connection connection) {
        String query = "INSERT INTO inmueble_has_extras(idInmueble,idExtras) VALUES(?,?)";
        for (InmuebleHasExtrasVO aux : lista) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, aux.getIdInmueble());
                preparedStatement.setInt(2, aux.getIdExtras());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
