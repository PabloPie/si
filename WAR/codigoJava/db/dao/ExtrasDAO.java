package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.ExtrasVO;

public class ExtrasDAO {

	public static List<ExtrasVO> getExtras(int idExtra, Connection connection) {
		List<ExtrasVO> listaExtras = new ArrayList<ExtrasVO>();
		String query = "SELECT nombre  FROM extras WHERE idExtras = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idExtra);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idExt = resultSet.getInt(Tablas.Extras.ID_EXTRAS);
				String nombreExtra = resultSet.getString(Tablas.Extras.NOMBRE);
				listaExtras.add(new ExtrasVO(idExt, nombreExtra));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaExtras;
	}

	public static List<ExtrasVO> getAllExtras(Connection connection) {
		List<ExtrasVO> listaExtras = new ArrayList<ExtrasVO>();
		String query = "SELECT idExtras,nombre FROM extras";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idExt = resultSet.getInt(Tablas.Extras.ID_EXTRAS);
				String nombreExtra = resultSet.getString(Tablas.Extras.NOMBRE);
				listaExtras.add(new ExtrasVO(idExt, nombreExtra));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaExtras;
	}
}
