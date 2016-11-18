package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.TiposDeViaVO;

public class TiposDeViaDAO {

	public static void insertTiposDeVia(TiposDeViaVO via, Connection connection) {
		try {
			/* Create "preparedStatement". */
			String query = "INSERT INTO tipos_de_via(idvia,nombretipo) VALUES (?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			/* Fill "preparedStatement". */
			preparedStatement.setInt(1, via.getIdVia());
			preparedStatement.setString(2, via.getNombreTipo());

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
	 * public static void updateTipoDeVia(TiposDeViaVO via, Connection
	 * connection){
	 * 
	 * }
	 */
	public static TiposDeViaVO obtenerTiposDeVia(int idVia, Connection connection) {
		TiposDeViaVO viaVO = null;
		try {
			/* Create "preparedStatement". */
			String queryString = "SELECT nombreTipo FROM tipos_de_via WHERE idvia = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			preparedStatement.setInt(1, idVia);

			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.first()) {
				throw new SQLException("Busqueda fallida.");
			}

			/* Execute query. */
			String nombreTipo = resultSet.getString(1);

			viaVO = new TiposDeViaVO(idVia, nombreTipo);

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return viaVO;
	}

	public static List<TiposDeViaVO> obtenerTodosTiposDeVia(Connection connection) {
		List<TiposDeViaVO> tiposDeVia = new ArrayList<>();
		try {
			String queryString = "SELECT idvia,nombreTipo FROM tipos_de_via";
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idVia = resultSet.getInt(1);
				String nombreTipo = resultSet.getString(2);
				tiposDeVia.add(new TiposDeViaVO(idVia, nombreTipo));

			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return tiposDeVia;
	}
}
