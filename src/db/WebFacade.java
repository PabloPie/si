package db;

import java.sql.Connection;
import java.sql.SQLException;
import db.dao.*;
import db.vo.*;

public class WebFacade {



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

	public static void main (String[] args) {
	   	WebFacade fachada = new WebFacade();
		//UsuarioRegistradoVO usuario = new UsuarioRegistradoVO("Elblopo","Caca","Futi","qwerty",976000000,"emilio@emilio",LocalDate.now(),null);
		PaisVO pais = new PaisVO(1,"España");

		try{
			//fachada.crearClienteo(usuario);
			fachada.crearPais(pais);
	    }catch (Exception e){
			e.printStackTrace(System.err);
		}
	}
}

