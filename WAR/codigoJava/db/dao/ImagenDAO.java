package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.ImagenVO;

public class ImagenDAO {

    public static List<ImagenVO> getImagenes(int idInmueble,
            Connection connection) {
        List<ImagenVO> listaImagenes = new ArrayList<ImagenVO>();
        String query = "SELECT idInmueble, idImagen, ruta  FROM imagen WHERE idInmueble = ?";
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(query);
            preparedStatement.setInt(1, idInmueble);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idInm = resultSet.getInt(Tablas.Imagenes.ID_INMUEBLE);
                int idImg = resultSet.getInt(Tablas.Imagenes.ID_IMAGEN);
                String ruta = resultSet.getString(Tablas.Imagenes.RUTA);
                listaImagenes.add(new ImagenVO(idInm, idImg, ruta));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaImagenes;
    }
}