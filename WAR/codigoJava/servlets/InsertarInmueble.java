package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Iterator;
import java.util.Collection;


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

    private List<String> getImagesPartsAndWriteThem(Collection<Part> parts) {
        List<String> namesList = new ArrayList<>();
        Iterator<Part> it = parts.iterator();
        ArrayList<Part> imagesParts = new ArrayList<>();
        while (it.hasNext()) {
            Part aux = it.next();
            if (aux.getName().equals("imagen"))
                imagesParts.add(aux);
        }
        int i = 0;
        for (Part p : imagesParts) {
            // TODO: asegurarse que no existe otro fichero con el mismo nombre
            // TODO: añadir extensiones a los ficheros
            String uuid = UUID.randomUUID().toString();
            namesList.add(uuid);
            try {
                p.write(uuid);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return namesList;
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
        List<String> nombreImagenes = getImagesPartsAndWriteThem(request.getParts());
        String pais = request.getParameter("pais");
        String provincia = request.getParameter("provincia");
        String poblacion = request.getParameter("poblacion");
        String tipoVia = request.getParameter("tipo-via");
        String nombreCalle = request.getParameter("nombre-calle");
        String numeroCalle = request.getParameter("numero-calle");
        String extras[] = request.getParameterValues("extras");
        String descripcion = request.getParameter("descripcion");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "precio: ");
    }

}
