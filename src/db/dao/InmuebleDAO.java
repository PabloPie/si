package db.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.vo.*;

public class InmuebleDAO {

    public static int insertarInmueble(InmuebleVO inmueble, Connection connection) {
        String query = "INSERT INTO inmueble(precio,superficie, planta,num_habitaciones,num_bagnos,descripcion,sevende,"
                + "sealquila,idTipo,idusuario,idpais,idprovincia,poblacion,nombredir,numerodir,idvia) VALUES" +
                " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            LocalizacionDAO.insertLocalizacion(inmueble.getLocalizacion(), connection);
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, inmueble.getPrecio());
            preparedStatement.setInt(2, inmueble.getSuperficie());
            preparedStatement.setInt(3, inmueble.getPlanta());
            preparedStatement.setInt(4, inmueble.getNumHabitaciones());
            preparedStatement.setInt(5, inmueble.getNumBagnos());
            preparedStatement.setString(6, inmueble.getDescripcion());
            preparedStatement.setBoolean(7, inmueble.seVende());
            preparedStatement.setBoolean(8, inmueble.seAlquila());
            preparedStatement.setInt(9, inmueble.getTipoInmueble().getIdTipo());
            preparedStatement.setString(10, inmueble.getUsuario().getidusuario());
            preparedStatement.setInt(11, inmueble.getLocalizacion().getProvincia().getPais().getIdPais());
            preparedStatement.setInt(12, inmueble.getLocalizacion().getProvincia().getIdProvincia());
            preparedStatement.setString(13, inmueble.getLocalizacion().getPoblacion());
            preparedStatement.setString(14, inmueble.getLocalizacion().getNombreDir());
            preparedStatement.setInt(15, inmueble.getLocalizacion().getNumeroDir());
            preparedStatement.setInt(16, inmueble.getLocalizacion().getTipoVia().getIdVia());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int idInm = 0;
            if (rs.next()) {
                idInm = rs.getInt(1);
            }
            List<InmuebleHasExtrasVO> inmuebleHasExtrasVOS = new ArrayList<>();
            for (ExtrasVO extra : inmueble.getExtras()) {
                inmuebleHasExtrasVOS.add(new InmuebleHasExtrasVO(idInm, extra.getIdExtra()));
            }
            InmuebleHasExtrasDAO.insertar(inmuebleHasExtrasVOS, connection);
            for (ImagenVO i : inmueble.getImagenes()) {
                i.setIdInmueble(idInm);
            }
            ImagenDAO.insertarImagenes(inmueble.getImagenes(), connection);
            return idInm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static InmuebleVO getInmuebles(int idInm, Connection connection) {
        InmuebleVO listaInmuebles = null;
        String query = "SELECT * FROM inmueble WHERE idInmueble = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idInm);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idInmueble = resultSet.getInt(Tablas.Inmueble.ID_INMUEBLE);
                double precio = resultSet.getDouble(Tablas.Inmueble.PRECIO);
                int superficie = resultSet.getInt(Tablas.Inmueble.SUPERFICIE);
                int planta = resultSet.getInt(Tablas.Inmueble.PLANTA);
                int numHabitaciones = resultSet.getInt(Tablas.Inmueble.NUM_HABITACIONES);
                int numBagnos = resultSet.getInt(Tablas.Inmueble.NUM_BAGNOS);
                String descripcion = resultSet.getString(Tablas.Inmueble.DESCRIPCION);
                boolean seVende = resultSet.getBoolean(Tablas.Inmueble.SE_VENDE);
                boolean seAlquila = resultSet.getBoolean(Tablas.Inmueble.SE_ALQUILA);
                int idTipoInmueble = resultSet.getInt(Tablas.Inmueble.ID_TIPO_INMUEBLE);
                String idUsuario = resultSet.getString(Tablas.Inmueble.ID_USUARIO);
                int idPais = resultSet.getInt(Tablas.Inmueble.ID_PAIS);
                int idProvincia = resultSet.getInt(Tablas.Inmueble.ID_PROVINCIA);
                String poblacion = resultSet.getString(Tablas.Inmueble.POBLACION);
                String nombreDir = resultSet.getString(Tablas.Inmueble.NOMBRE_DIR);
                int numeroDir = resultSet.getInt(Tablas.Inmueble.NUMERO_DIR);
                int idVia = resultSet.getInt(Tablas.Inmueble.ID_VIA);
                List<ExtrasVO> extras = ExtrasDAO.getExtrasDeUnInmuelbe(idInmueble, connection);
                TipoInmuebleVO tipoInmuebleVO = TipoInmuebleDAO.getTipoInmuebleById(idTipoInmueble, connection);
                UsuarioRegistradoVO usuarioRegistradoVO = UsuarioRegistradoDAO.encontrarDatosUsuario(idUsuario,
                        connection);
                LocalizacionVO localizacionVO = LocalizacionDAO.obtenerLocalizacion(idPais, idProvincia, poblacion,
                        nombreDir, numeroDir, idVia, connection);
                List<ImagenVO> imagenesVO = ImagenDAO.getImagenes(idInm, connection);
                listaInmuebles = new InmuebleVO(idInmueble, precio, superficie, planta, numHabitaciones, numBagnos,
                        descripcion, seVende, seAlquila, tipoInmuebleVO, usuarioRegistradoVO, localizacionVO, extras,
                        imagenesVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaInmuebles;
    }

    public static List<InmuebleVO> getInmuebles(String idUsuario, Connection connection) {
        List<InmuebleVO> listaInmuebles = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT idInmueble, precio, superficie, planta, num_habitaciones, num_bagnos, descripcion, " +
                "sevende, sealquila, idTipo, idusuario, idpais, idprovincia, poblacion, nombredir, numerodir, idvia FROM " +
                "inmueble WHERE idusuario == ?");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(stringBuffer.toString());
            preparedStatement.setString(1, idUsuario);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int idInmueble = resultSet.getInt(Tablas.Inmueble.ID_INMUEBLE);
                double precio = resultSet.getDouble(Tablas.Inmueble.PRECIO);
                int superficie = resultSet.getInt(Tablas.Inmueble.SUPERFICIE);
                int planta = resultSet.getInt(Tablas.Inmueble.PLANTA);
                int numHabitaciones = resultSet.getInt(Tablas.Inmueble.NUM_HABITACIONES);
                int numBagnos = resultSet.getInt(Tablas.Inmueble.NUM_BAGNOS);
                String descripcion = resultSet.getString(Tablas.Inmueble.DESCRIPCION);
                boolean sevende = resultSet.getBoolean(Tablas.Inmueble.SE_VENDE);
                boolean sealquila = resultSet.getBoolean(Tablas.Inmueble.SE_ALQUILA);
                int idTipo = resultSet.getInt(Tablas.Inmueble.ID_TIPO_INMUEBLE);
                String idusuario = resultSet.getString(Tablas.Inmueble.ID_USUARIO);
                int idPais = resultSet.getInt(Tablas.Inmueble.ID_PAIS);
                int idProvincia = resultSet.getInt(Tablas.Inmueble.ID_PROVINCIA);
                String poblacion = resultSet.getString(Tablas.Inmueble.POBLACION);
                String nombreDir = resultSet.getString(Tablas.Inmueble.NOMBRE_DIR);
                int numeroDir = resultSet.getInt(Tablas.Inmueble.NUMERO_DIR);
                int idVia = resultSet.getInt(Tablas.Inmueble.ID_VIA);
                TipoInmuebleVO tipoInmuebleVO = TipoInmuebleDAO.getTipoInmuebleById(idTipo, connection);
                LocalizacionVO localizacionVO = LocalizacionDAO.obtenerLocalizacion(idPais, idProvincia, poblacion,
                        nombreDir, numeroDir, idVia, connection);
                UsuarioRegistradoVO usuarioVO = UsuarioRegistradoDAO.encontrarDatosUsuario(idusuario, connection);
                List<ExtrasVO> listaExtrasVO = ExtrasDAO.getExtrasDeUnInmuelbe(idInmueble, connection);
                List<ImagenVO> listaImagenesVO = ImagenDAO.getImagenes(idInmueble, connection);
                InmuebleVO inmuebleVO = new InmuebleVO(idInmueble, precio, superficie, planta, numHabitaciones, numBagnos,
                        descripcion, sevende, sealquila, tipoInmuebleVO, usuarioVO, localizacionVO, listaExtrasVO, listaImagenesVO);
                listaInmuebles.add(inmuebleVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaInmuebles;
    }



    public static List<InmuebleVO> getInmuebles(boolean seAlquilan, boolean seVenden, String palabraClave, int precioDesde,
                                                int precioHasta, int supDesde, int supHasta, Connection connection) {
        List<InmuebleVO> listaInmuebles = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT idInmueble, precio, superficie, planta, num_habitaciones, num_bagnos, descripcion, " +
                "sevende, sealquila, idTipo, idusuario, idpais, idprovincia, poblacion, nombredir, numerodir, idvia FROM " +
                "inmueble NATURAL JOIN localizacion NATURAL JOIN provincia NATURAL JOIN pais WHERE sevende = ? AND sealquila = ?");
        boolean filtrosQueAparecen[] = new boolean[5];
        if (palabraClave != null) {
            stringBuffer.append(" AND (nombrepais LIKE ? OR nombreprov LIKE ? OR poblacion LIKE ? OR nombredir LIKE ?)");
            filtrosQueAparecen[0] = true;
        }

        if (precioDesde >= 0) {
            stringBuffer.append(" AND precio >= ?");
            filtrosQueAparecen[1] = true;
        }

        if (precioHasta >= 0) {
            stringBuffer.append(" AND precio <= ?");
            filtrosQueAparecen[2] = true;
        }

        if (supDesde >= 0) {
            stringBuffer.append(" AND superficie >= ?");
            filtrosQueAparecen[3] = true;
        }

        if (supHasta >= 0) {
            stringBuffer.append(" AND superficie <= ?");
            filtrosQueAparecen[4] = true;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(stringBuffer.toString());
            preparedStatement.setBoolean(1, seVenden);
            preparedStatement.setBoolean(2, seAlquilan);
            int i = 3;
            if (palabraClave != null) {
                preparedStatement.setString(i++, "%" + palabraClave + "%");
                preparedStatement.setString(i++, "%" + palabraClave + "%");
                preparedStatement.setString(i++, "%" + palabraClave + "%");
                preparedStatement.setString(i++, "%" + palabraClave + "%");
            }
            if (precioDesde >= 0) {
                preparedStatement.setInt(i++, precioDesde);
            }
            if (precioHasta >= 0) {
                preparedStatement.setInt(i++, precioHasta);
            }
            if (supDesde >= 0) {
                preparedStatement.setInt(i++, supDesde);
            }
            if (supHasta >= 0) {
                preparedStatement.setInt(i, supHasta);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idInmueble = resultSet.getInt(Tablas.Inmueble.ID_INMUEBLE);
                double precio = resultSet.getDouble(Tablas.Inmueble.PRECIO);
                int superficie = resultSet.getInt(Tablas.Inmueble.SUPERFICIE);
                int planta = resultSet.getInt(Tablas.Inmueble.PLANTA);
                int numHabitaciones = resultSet.getInt(Tablas.Inmueble.NUM_HABITACIONES);
                int numBagnos = resultSet.getInt(Tablas.Inmueble.NUM_BAGNOS);
                String descripcion = resultSet.getString(Tablas.Inmueble.DESCRIPCION);
                boolean sevende = resultSet.getBoolean(Tablas.Inmueble.SE_VENDE);
                boolean sealquila = resultSet.getBoolean(Tablas.Inmueble.SE_ALQUILA);
                int idTipo = resultSet.getInt(Tablas.Inmueble.ID_TIPO_INMUEBLE);
                String idusuario = resultSet.getString(Tablas.Inmueble.ID_USUARIO);
                int idPais = resultSet.getInt(Tablas.Inmueble.ID_PAIS);
                int idProvincia = resultSet.getInt(Tablas.Inmueble.ID_PROVINCIA);
                String poblacion = resultSet.getString(Tablas.Inmueble.POBLACION);
                String nombreDir = resultSet.getString(Tablas.Inmueble.NOMBRE_DIR);
                int numeroDir = resultSet.getInt(Tablas.Inmueble.NUMERO_DIR);
                int idVia = resultSet.getInt(Tablas.Inmueble.ID_VIA);
                TipoInmuebleVO tipoInmuebleVO = TipoInmuebleDAO.getTipoInmuebleById(idTipo, connection);
                LocalizacionVO localizacionVO = LocalizacionDAO.obtenerLocalizacion(idPais, idProvincia, poblacion,
                        nombreDir, numeroDir, idVia, connection);
                UsuarioRegistradoVO usuarioVO = UsuarioRegistradoDAO.encontrarDatosUsuario(idusuario, connection);
                List<ExtrasVO> listaExtrasVO = ExtrasDAO.getExtrasDeUnInmuelbe(idInmueble, connection);
                List<ImagenVO> listaImagenesVO = ImagenDAO.getImagenes(idInmueble, connection);
                InmuebleVO inmuebleVO = new InmuebleVO(idInmueble, precio, superficie, planta, numHabitaciones, numBagnos,
                        descripcion, sevende, sealquila, tipoInmuebleVO, usuarioVO, localizacionVO, listaExtrasVO, listaImagenesVO);
                listaInmuebles.add(inmuebleVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaInmuebles;
    }

}
