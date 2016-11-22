package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.*;

/**
 * Servlet implementation class InsertarInmueble
 */
public class InsertarInmueble extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InsertarInmueble() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String precio = request.getParameter("precio");
        String superficie = request.getParameter("superficie");
        String numHabitaciones = request.getParameter("num-habitaciones");
        String numBagnos = request.getParameter("num-bagnos");
        String planta = request.getParameter("planta");
        String tipoInmueble = request.getParameter("tipo-inmueble");
        String vendeAlquila = request.getParameter("vendeAlquila");

        String pais = request.getParameter("pais");
        String provincia = request.getParameter("provincia");
        String poblacion = request.getParameter("poblacion");
        String tipoVia = request.getParameter("tipo-via");
        String nombreCalle = request.getParameter("nombre-calle");
        String numeroCalle = request.getParameter("numero-calle");
        String extras[] = request.getParameterValues("extras");
        String descripcion = request.getParameter("descripcion");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "precio: " + extras[0] + extras[1]);
    }

}
