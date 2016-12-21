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
            throws ServletException, IOException {
        UsuarioRegistradoVO userVO;
        userVO = comprobarErrores(request);
        if (userVO != null) {
            WebFacade.crearUsuario(userVO);
            response.sendRedirect(request.getContextPath() + "/index.html");
        } else {
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

        boolean err = false;

        if (password.equals("") || usuario.equals("") || nombre.equals("") || apellidos.equals("") || mail.equals("") || remail.equals("") ||
                repassword.equals("")) {
            request.setAttribute("CampoVacio", "ERROR: TODOS LOS CAMPOS DEBEN SER RELLENADOS");
            err = true;
        }

        if (!repassword.equals(password)) {
            request.setAttribute("mismatchP", "Contraseña no coincide");
            err = true;
        }
        if (!mail.equals(remail)) {
            request.setAttribute("mismatchM", "Mail no coincide.");
            err = true;
        }
        if (WebFacade.existeUsuario(usuario)) {
            request.setAttribute("usuarioExiste", "Usuario ya existente.");
            err = true;
        }
        if (!err) {
            password = WebFacade.hashPass(password);
            return new UsuarioRegistradoVO(usuario, nombre,
                    apellidos, password, 0,
                    mail, null, null);
        } else {
            return null;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
