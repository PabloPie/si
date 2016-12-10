package servlets;

import db.WebFacade;
import db.dao.UsuarioRegistradoDAO;
import db.vo.LocalizacionVO;
import db.vo.UsuarioRegistradoVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;


public class Registro extends HttpServlet {

    public Registro() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        UsuarioRegistradoVO userVO;
        userVO = comprobarErrores(request);
        if (userVO!=null){
            WebFacade.crearUsuario(userVO);
            response.sendRedirect("index.html");
        }
        else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/ErrorRegistro.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private UsuarioRegistradoVO comprobarErrores(HttpServletRequest request) {
        String password = request.getParameter("Pass");
        String usuario = request.getParameter("user");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellido");
        String mail = request.getParameter("Mail");
        String remail = request.getParameter("REMail");
        String repassword = request.getParameter("REPass");
        boolean errores = false;
        System.out.println(password);
        System.out.println(usuario);
        System.out.println(nombre);
        System.out.println(apellidos);
        System.out.println(mail);
        System.out.println(remail);
        System.out.println(repassword);

        if(!repassword.equals(password)){
            System.err.println("Error contras.");
            request.setAttribute("mismatchP", "Contraseña no coincide");
            errores = true;
        }
        if(!mail.equals(remail)){
            System.err.println("Error mails.");
            request.setAttribute("mismatchM", "Mail no coincide.");
            errores = true;
        }
        if(WebFacade.existeUsuario(usuario)){
            request.setAttribute("usuarioExiste", "Usuario ya existente.");
            errores = true;
        }
        if(!errores){
            System.err.println("Registra usuario");
            return new UsuarioRegistradoVO(usuario, nombre,
                    apellidos, password, 0,
                    mail, null, null);
        }else {
            return null;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request,response);
    }
}
