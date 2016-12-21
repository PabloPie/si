package servlets;

import db.WebFacade;
import db.vo.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class UpdateUserdata extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("envio").equals("delete")) {
            String user = (String) request.getSession().getAttribute("currentSessionUser");
            System.err.println("Se va a borrar al usuario" + user);
            WebFacade.borrarUsuario(user);
            request.getSession().invalidate();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
            requestDispatcher.forward(request, response);
        } else if (request.getParameter("envio").equals("actualiza")) {
            System.err.println("Se procede a actualizar los datos del usuario.");
            HashMap<String, String> errores = new HashMap<>();
            UsuarioRegistradoVO userVO;
            userVO = comprobarErrores(request, errores);
            if (userVO != null) {
                if (userVO.getLocation() != null) {
                    if (!WebFacade.existeLocalizacion(userVO.getLocation())) {
                        WebFacade.insertarLocalizacion(userVO.getLocation());
                    } else {
                        userVO.setLocation(WebFacade.obtenerLocalizacion(userVO.getLocation()));
                    }
                }
                WebFacade.actualizarUsuario(userVO);
                response.sendRedirect(request.getContextPath() + "/perfil.jsp");
            } else {
                request.setAttribute("errores", errores);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/perfilError.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
        }
    }

    private UsuarioRegistradoVO comprobarErrores(HttpServletRequest request, HashMap<String, String> errores) throws ServletException,
            IOException {

        String user = (String) request.getSession().getAttribute("currentSessionUser");
        String poblacion = request.getParameter("poblacion");
        String nombredir = request.getParameter("direccion");
        int numerodir = 0;
        if (!request.getParameter("numdir").equals("")) {
            numerodir = Integer.parseInt(request.getParameter("numdir"));
        }
        int cp = 0;
        if (!request.getParameter("cp").equals("")) {
            cp = Integer.parseInt(request.getParameter("cp"));
        }
        LocalizacionVO localizacion = null;
        if (!poblacion.equals("") || !nombredir.equals("") || numerodir != 0 || cp != 0) {
            localizacion = comprobarLocalizacion(request, errores);
            if (localizacion == null) {
                errores.put("invalidLocation", "Si se desea añadir localización se deben rellenar todos los campos.");
                return null;
            }
        }


        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String fecha = request.getParameter("fecha");
        LocalDate date = null;
        if (!fecha.equals("")) {
            fecha = parseFecha(fecha);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date = LocalDate.parse(fecha, dtf);

        }

        UsuarioRegistradoVO userVO = WebFacade.encontrarDatosUsuario(user);
        userVO.setNombre(nombre);
        userVO.setApellidos(apellidos);
        userVO.setFecha(date);
        userVO.setLocation(localizacion);
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");
        if (!pass.equals("")) {
            if (pass.equals(repass)) {
                pass = WebFacade.hashPass(pass);
                userVO.setClaveEncriptada(pass);
            } else {
                errores.put("passError", "Las contraseñas no coinciden.");
            }
        }

        return userVO;
    }

    private String parseFecha(String fecha) {
        String date[] = fecha.split("-");
        return date[2] + "-" + date[1] + "-" + date[0];
    }

    private LocalizacionVO comprobarLocalizacion(HttpServletRequest request, HashMap<String, String> errores) {
        int idPais = 0, idProvincia = 0, codigoPostal = 0, idTipoVia = 0, numeroCalle = 0;
        String poblacion = null, nombreCalle = null;
        try {
            idPais = comprobarParamteroIdPais(request.getParameter("pais"));
        } catch (IllegalArgumentException e) {
            errores.put("pais", e.getMessage());
        }
        try {
            idProvincia = comprobarParamteroIdProvincia(request.getParameter("provincia"));
        } catch (IllegalArgumentException e) {
            errores.put("provincia", e.getMessage());
        }
        try {
            poblacion = comprobarParametroPoblacion(request.getParameter("poblacion"));
        } catch (IllegalArgumentException e) {
            errores.put("poblacion", e.getMessage());
        }
        try {
            codigoPostal = comprobarParametroCodigoPostal(request.getParameter("cp"));
        } catch (IllegalArgumentException e) {
            errores.put("cp", e.getMessage());
        }
        try {
            idTipoVia = comprobarParametroIdTipoVia(request.getParameter("tipo-via"));
        } catch (IllegalArgumentException e) {
            errores.put("tipo-via", e.getMessage());
        }
        try {
            nombreCalle = comprobarParametroNombreCalle(request.getParameter("direccion"));
        } catch (IllegalArgumentException e) {
            errores.put("direccion", e.getMessage());
        }
        try {
            numeroCalle = comprobarParametroNumeroVia(request.getParameter("numdir"));
        } catch (IllegalArgumentException e) {
            errores.put("numdir", e.getMessage());
        }

        if (errores.isEmpty()) {
            LocalizacionVO localizacionVO = new LocalizacionVO();
            PaisVO paisVO = new PaisVO();
            paisVO.setIdPais(idPais);
            ProvinciaVO provinciaVO = new ProvinciaVO();
            provinciaVO.setPais(paisVO);
            provinciaVO.setIdProvincia(idProvincia);
            localizacionVO.setProvincia(provinciaVO);
            localizacionVO.setPoblacion(poblacion);
            localizacionVO.setNombreDir(nombreCalle);
            localizacionVO.setNumeroDir(numeroCalle);
            localizacionVO.setCodigoPostal(codigoPostal);
            TiposDeViaVO tiposDeViaVO = new TiposDeViaVO();
            tiposDeViaVO.setIdVia(idTipoVia);
            localizacionVO.setTipoVia(tiposDeViaVO);
            return localizacionVO;
        } else {
            return null;
        }
    }


    private int comprobarParamteroIdPais(String idPais) throws IllegalArgumentException {
        // Asumimos que es correcto, no lo introduce directamente el usuario
        int p = Integer.parseInt(idPais);
        return p;
    }

    private int comprobarParamteroIdProvincia(String idProvincia) throws IllegalArgumentException {
        // Asumimos que es correcto, no lo introduce directamente el usuario
        int p = Integer.parseInt(idProvincia);
        return p;
    }

    private String comprobarParametroPoblacion(String poblacion) throws IllegalArgumentException {
        if (poblacion == null || poblacion.isEmpty()) {
            throw new IllegalArgumentException("La poblacion es obligatoria");
        }
        return poblacion;
    }

    private int comprobarParametroCodigoPostal(String codigoPostal) throws IllegalArgumentException {
        if (codigoPostal == null || codigoPostal.isEmpty()) {
            throw new IllegalArgumentException("El codigo postal es obligatorio");
        }
        int p = 0;
        try {
            p = Integer.parseInt(codigoPostal);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El codigo postal  ha de ser un numero");
        }
        if (p < 0) {
            throw new IllegalArgumentException("El codigo postal  no puede ser negativo");
        }
        return p;
    }

    private int comprobarParametroIdTipoVia(String id) throws IllegalArgumentException {
        // Asumimos que es correcto de momento
        int p = Integer.parseInt(id);
        return p;
    }

    private String comprobarParametroNombreCalle(String calle) throws IllegalArgumentException {
        if (calle == null || calle.isEmpty()) {
            throw new IllegalArgumentException("La calle es obligatoria");
        }
        return calle;
    }

    private int comprobarParametroNumeroVia(String num) throws IllegalArgumentException {
        if (num == null || num.isEmpty()) {
            throw new IllegalArgumentException("El numero de la calle es obligatorio");
        }
        int p = 0;
        try {
            p = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El numero de la calle no puede ser negativo");
        }
        return p;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

