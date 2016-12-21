package db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import db.dao.*;
import db.vo.*;

public class WebFacade {
    public static void insertarLocalizacion(LocalizacionVO location) {
        Connection connection = null;
        System.out.println("Inicio inserción localización...");
        try {
            connection = GestorDeConexionesBD.getConnection();
            LocalizacionDAO.insertLocalizacion(location, connection);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean existeLocalizacion(LocalizacionVO loc) {
        Connection connection = null;
        System.out.println("Inicio inserción localización...");
        try {
            connection = GestorDeConexionesBD.getConnection();
            LocalizacionDAO.insertLocalizacion(loc, connection);
            connection.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static LocalizacionVO obtenerLocalizacion(LocalizacionVO loc) {
        Connection connection = null;
        System.out.println("Inicio inserción localización...");
        LocalizacionVO location = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
            location = LocalizacionDAO.obtenerLocalizacion(loc.getProvincia().getPais().getIdPais(),
                    loc.getProvincia().getIdProvincia(),loc.getPoblacion(),loc.getNombreDir(),loc.getNumeroDir(),
                    loc.getTipoVia().getIdVia(), connection);
            connection.close();
        }catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return location;
    }






    public static UsuarioRegistradoVO encontrarDatosUsuario(String usuario){
        Connection connection = null;
        UsuarioRegistradoVO userVO = null;
        System.out.println("Inicio busqueda datos usuario...");
        try{
            connection = GestorDeConexionesBD.getConnection();
            userVO = UsuarioRegistradoDAO.encontrarDatosUsuario(usuario,connection);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userVO;
    }

     public static void borrarUsuario(String usuario){
         Connection connection = null;
         System.out.println("Inicio borrado usuario...");
         try{
             connection = GestorDeConexionesBD.getConnection();
             UsuarioRegistradoDAO.borrarUsuario(usuario,connection);
             connection.close();
         } catch (Exception e) {
             e.printStackTrace(System.err);
         } finally{
             try {
                 connection.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }

	public static boolean existeUsuario(String usuario){
        Connection connection = null;
        boolean encontrado = false;
        System.out.println("Inicio comprobacion existe usuario...");
        try{
            connection = GestorDeConexionesBD.getConnection();
            encontrado = UsuarioRegistradoDAO.existeUsuario(usuario,connection);
            System.err.println(encontrado);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return encontrado;
    }

	public static boolean comprobarUsuario(String usuario, String password){
        Connection connection = null;
        boolean encontrado = false;
        System.out.println("Inicio comprobacion usuario...");
        try{
            connection = GestorDeConexionesBD.getConnection();
            encontrado = UsuarioRegistradoDAO.validarUsuario(usuario,WebFacade.hashPass(password),connection);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return encontrado;
    }

	public static void crearUsuario(UsuarioRegistradoVO usuario) {
		Connection connection = null;
		System.out.println("Inicio creación de usuario...");		
	    try{
		   connection = GestorDeConexionesBD.getConnection();
		   UsuarioRegistradoDAO.insertarUsuario(usuario,connection);  
	       connection.close();  
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	public static void actualizarUsuario(UsuarioRegistradoVO usuario){
        Connection connection = null;
        System.out.println("Inicio modificación de usuario...");
        try{
            connection = GestorDeConexionesBD.getConnection();
            UsuarioRegistradoDAO.actualizarUsuario(usuario,connection);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	public static void crearPais(PaisVO pais) throws SQLException{
		Connection connection = null;
		System.out.println("Inicio creación de pais...");
		try{
			connection = GestorDeConexionesBD.getConnection();
			PaisDAO paisDAO = new PaisDAO();
			PaisDAO.insertPais(pais,connection);
		} catch (Exception e){
			e.printStackTrace(System.err);
		} finally{
			connection.close();
		}
	}

    public static String hashPass(String password){
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}

