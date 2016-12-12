package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.vo.InmuebleHasExtrasVO;

public class InmuebleHasExtrasDAO {

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
