package servlets;

import db.WebFacade;
import db.dao.UsuarioRegistradoDAO;
import db.vo.UsuarioRegistradoVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String password = request.getParameter("password");
        String usuario = request.getParameter("user");
        if (WebFacade.comprobarUsuario(usuario, password) && !password.equals("") && !usuario.equals("")) {
            session.setAttribute("currentSessionUser", usuario);
            response.sendRedirect(request.getContextPath() + "/perfil.jsp");
        } else {
            session.invalidate();
            request.setAttribute("errorLogin", "Usuario o password inválido.");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/loginError.jsp");
            rd.forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
