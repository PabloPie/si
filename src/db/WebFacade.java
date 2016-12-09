package db;

import java.sql.Connection;
import java.sql.SQLException;
import db.dao.*;
import db.vo.*;
import sun.java2d.UnixSurfaceManagerFactory;

public class WebFacade {


	public static boolean comprobarUsuario(String usuario, String password){
        Connection connection = null;
        boolean encontrado = false;
        System.out.println("Inicio comprobacion usuario...");
        try{
            connection = GestorDeConexionesBD.getConnection();
            encontrado = UsuarioRegistradoDAO.validarUsuario(usuario,password,connection);
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

	public void crearCliente(UsuarioRegistradoVO usuario)throws SQLException {    
		Connection connection = null;
		System.out.println("Inicio creación de usuario...");		
	    try{
		   connection = GestorDeConexionesBD.getConnection();
	       UsuarioRegistradoDAO usuarioDAO = new UsuarioRegistradoDAO();
		   UsuarioRegistradoDAO.insertarUsuario(usuario,connection);  
	       connection.close();  
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    } finally{
			connection.close();
		}                
	}

	public void crearPais(PaisVO pais) throws SQLException{
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

}

