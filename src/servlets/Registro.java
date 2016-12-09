package servlets;

import db.WebFacade;
import db.dao.UsuarioRegistradoDAO;
import db.vo.LocalizacionVO;
import db.vo.UsuarioRegistradoVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;


public class Registro extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String password = request.getParameter("Pass");
        String usuario = request.getParameter("user");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellido");
        String mail = request.getParameter("Mail");
        HashMap<String, String> errores = new HashMap<>();

        if(WebFacade.existeUsuario(usuario)){
            response.sendRedirect("ErrorRegistro.jsp");
        }
        else{
            UsuarioRegistradoVO userVO = new UsuarioRegistradoVO(usuario, nombre,
                    apellidos, password, 0,
            mail, null, null);
            WebFacade.crearUsuario(userVO);
            response.sendRedirect("index.jsp");
        }





    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request,response);
    }
}
