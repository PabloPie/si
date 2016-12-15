package servlets;

import com.sun.deploy.net.HttpRequest;
import db.WebFacade;
import db.vo.UsuarioRegistradoVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpRetryException;


public class UpdateUserdata extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("envio").equals("delete")){
            String user = (String) request.getSession().getAttribute("currentSessionUser");
            System.err.println("Se va a borrar al usuario"+user);
            WebFacade.borrarUsuario(user);
            request.getSession().invalidate();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
            requestDispatcher.forward(request, response);
        }else if(request.getParameter("envio").equals("actualiza")) {
            UsuarioRegistradoVO userVO;
            userVO = comprobarErrores(request);
            if (userVO != null) {
                WebFacade.actualizarUsuario(userVO);
                response.sendRedirect("perfil.html");
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/perfilError.jsp");
                requestDispatcher.forward(request, response);
            }
        }else{}
    }


    public UsuarioRegistradoVO comprobarErrores(HttpServletRequest request){

        int idpais = request.getParameter("");
        int idprovincia;
        String poblacion;
        String nombredir;
        int numerodir;
        int idvia;



    }



    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doPost(request,response);
    }
}

