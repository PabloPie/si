package servlets;

import db.dao.*;
import db.vo.*;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


public class InsertarInmueble extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InsertarInmueble() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HashMap<String, String> errores = new HashMap<>();
        InmuebleVO inmuebleVO = comprobar(request, errores);
        if (inmuebleVO != null) {
            Connection connection = null;
            try {
                connection = GestorDeConexionesBD.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            InmuebleDAO.insertarInmueble(inmuebleVO, connection);
        } else {
            request.setAttribute("errores", errores);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/errorPublicarInmueble.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    /**
     * Metodo que dada una coleccion de Part escribe las imagenes
     *
     * @param parts partes de una peticion multipart/form-data
     * @return lista con los nombres de las imagenes escritas
     */
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

    /**
     * Metodo que comprueba los valores de los distintos parametros y devuleve un InmuebleVO
     *
     * @param request
     * @param errores
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private InmuebleVO comprobar(HttpServletRequest request, HashMap<String, String> errores) throws ServletException,
            IOException {
        UsuarioRegistradoVO usuarioDePrueba = null;
        try {
            usuarioDePrueba = UsuarioRegistradoDAO.encontrarDatosUsuario("paco", GestorDeConexionesBD
                    .getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        double precio = 0;
        try {
            precio = comprobarParametroPrecio(request.getParameter("precio"));
        } catch (IllegalArgumentException e) {
            errores.put("precio", e.getMessage());
        }
        int superficie = 0;
        try {
            superficie = comprobarParametroSuperficie(request.getParameter("superficie"));
        } catch (IllegalArgumentException e) {
            errores.put("superficie", e.getMessage());
        }
        int numHabitaciones = 0;
        try {
            numHabitaciones = comprobarParametroNumHabitaciones(request.getParameter("num-habitaciones"));
        } catch (IllegalArgumentException e) {
            errores.put("num-habitaciones", e.getMessage());
        }
        int numBagnos = 0;
        try {
            numBagnos = comprobarParametroNumBagnos(request.getParameter("num-bagnos"));
        } catch (IllegalArgumentException e) {
            errores.put("num-bagnos", e.getMessage());
        }
        int planta = 0;
        try {
            planta = comprobarParametroPlanta(request.getParameter("planta"));
        } catch (IllegalArgumentException e) {
            errores.put("planta", e.getMessage());
        }
        int idTipoInmueble = 0;
        try {
            idTipoInmueble = comprobarParametroIdTipoInm(request.getParameter("tipo-inmueble"));
        } catch (IllegalArgumentException e) {
            errores.put("tipo-inmueble", e.getMessage());
        }
        boolean seVende = false;
        try {
            seVende = comprobarParametroVendeAlquila(request.getParameter("vendeAlquila"));
        } catch (IllegalArgumentException e) {
            errores.put("vendeAlquila", e.getMessage());
        }

        int idPais = 0;
        try {
            idPais = comprobarParamteroIdPais(request.getParameter("pais"));
        } catch (IllegalArgumentException e) {
            errores.put("pais", e.getMessage());
        }
        int idProvincia = 0;
        try {
            idProvincia = comprobarParamteroIdProvincia(request.getParameter("provincia"));
        } catch (IllegalArgumentException e) {
            errores.put("provincia", e.getMessage());
        }
        String poblacion = null;
        try {
            poblacion = comprobarParametroPoblacion(request.getParameter("poblacion"));
        } catch (IllegalArgumentException e) {
            errores.put("poblacion", e.getMessage());
        }
        int codigoPostal = 0;
        try {
            codigoPostal = comprobarParametroCodigoPostal(request.getParameter("codigoPostal"));
        } catch (IllegalArgumentException e) {
            errores.put("codigoPostal", e.getMessage());
        }
        int idTipoVia = 0;
        try {
            idTipoVia = comprobarParametroIdTipoVia(request.getParameter("tipo-via"));
        } catch (IllegalArgumentException e) {
            errores.put("tipo-via", e.getMessage());
        }
        String nombreCalle = null;
        try {
            nombreCalle = comprobarParametroNombreCalle(request.getParameter("nombre-calle"));
        } catch (IllegalArgumentException e) {
            errores.put("nombre-calle", e.getMessage());
        }
        int numeroCalle = 0;
        try {
            numeroCalle = comprobarParametroNumeroVia(request.getParameter("numero-calle"));
        } catch (IllegalArgumentException e) {
            errores.put("numero-calle", e.getMessage());
        }
        int extras[] = null;
        try {
            extras = comprobarParametroExtras(request.getParameterValues("extras"));
        } catch (IllegalArgumentException e) {
            errores.put("extras", e.getMessage());
        }
        String descripcion = null;
        try {
            descripcion = comprobarParametroDescripcion(request.getParameter("descripcion"));
        } catch (IllegalArgumentException e) {
            errores.put("descripcion", e.getMessage());
        }
        if (errores.isEmpty()) {
            List<String> nombreImagenes = getImagesPartsAndWriteThem(request.getParts());
            InmuebleVO inmuebleVO = new InmuebleVO();
            inmuebleVO.setPrecio(precio);
            inmuebleVO.setSuperficie(superficie);
            inmuebleVO.setPlanta(planta);
            inmuebleVO.setNumHabitaciones(numHabitaciones);
            inmuebleVO.setNumBagnos(numBagnos);
            inmuebleVO.setDescripcion(descripcion);
            inmuebleVO.setSeVende(seVende);
            inmuebleVO.setSeAlquila(!seVende);
            TipoInmuebleVO tipoInmuebleVO = new TipoInmuebleVO();
            tipoInmuebleVO.setIdTipo(idTipoInmueble);
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
            inmuebleVO.setTipoInmueble(tipoInmuebleVO);
            inmuebleVO.setUsuario(usuarioDePrueba);
            inmuebleVO.setLocalizacion(localizacionVO);
            List<ExtrasVO> listaExtrasVO = new ArrayList<>();
            for (Integer i : extras) {
                ExtrasVO e = new ExtrasVO();
                e.setIdExtra(i);
                listaExtrasVO.add(e);
            }
            inmuebleVO.setExtras(listaExtrasVO);
            List<ImagenVO> listaImagenes = new ArrayList<>();
            for (String s : nombreImagenes) {
                ImagenVO img = new ImagenVO();
                img.setRuta(s);
                listaImagenes.add(img);
            }
            inmuebleVO.setImagenes(listaImagenes);
            return inmuebleVO;
        } else {
            return null;
        }
    }

    private double comprobarParametroPrecio(String precio) throws IllegalArgumentException {
        if (precio == null || precio.isEmpty()) {
            throw new IllegalArgumentException("El precio es obligatorio");
        }
        double p = 0;
        try {
            p = Double.parseDouble(precio);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio ha de ser un numero");
        }
        if (p < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        return p;
    }

    private int comprobarParametroSuperficie(String sup) throws IllegalArgumentException {
        if (sup == null || sup.isEmpty()) {
            throw new IllegalArgumentException("La superficie es obligatoria");
        }
        int p = 0;
        try {
            p = Integer.parseInt(sup);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La superficie ha de ser un numero");
        }
        if (p < 0) {
            throw new IllegalArgumentException("La superficie no puede ser negativa");
        }
        return p;
    }

    private int comprobarParametroNumHabitaciones(String numHab) throws IllegalArgumentException {
        if (numHab == null || numHab.isEmpty()) {
            throw new IllegalArgumentException("El numero de habitaciones es obligatorio");
        }
        int p = 0;
        try {
            p = Integer.parseInt(numHab);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El numero de habitaciones ha de ser un numero");
        }
        if (p < 0) {
            throw new IllegalArgumentException("El numero de habitaciones no puede ser negativo");
        }
        return p;
    }

    private int comprobarParametroNumBagnos(String numBagnos) throws IllegalArgumentException {
        if (numBagnos == null || numBagnos.isEmpty()) {
            throw new IllegalArgumentException("El numero de baños es obligatorio");
        }
        int p = 0;
        try {
            p = Integer.parseInt(numBagnos);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El numero de baños ha de ser un numero");
        }
        if (p < 0) {
            throw new IllegalArgumentException("El numero de baños no puede ser negativo");
        }
        return p;
    }

    private int comprobarParametroPlanta(String planta) throws IllegalArgumentException {
        if (planta == null || planta.isEmpty()) {
            throw new IllegalArgumentException("La planta es obligatoria");
        }
        int p = 0;
        try {
            p = Integer.parseInt(planta);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La planta ha de ser un numero");
        }
        if (p < 0) {
            throw new IllegalArgumentException("La planta no puede ser negativa");
        }
        return p;
    }

    private int comprobarParametroIdTipoInm(String tipo) throws IllegalArgumentException {
        // De momento asumimos que este parametro esta bien porque no lo introduce realmente el usuario
        int p = Integer.parseInt(tipo);
        return p;
    }


    private boolean comprobarParametroVendeAlquila(String seVendeOAlquila) throws IllegalArgumentException {
        if (seVendeOAlquila == null) {
            throw new IllegalArgumentException("Este campo es obligatorio");
        } else if (seVendeOAlquila.equals("vende")) {
            return true;
        } else if (seVendeOAlquila.equals("alquila")) {
            return false;
        } else {
            throw new IllegalArgumentException("Valor no valido");
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

    private int[] comprobarParametroExtras(String extras[]) throws IllegalArgumentException {
        // Asumimos que es correcto de momento
        if (extras != null) {
            int idExtras[] = new int[extras.length];
            for (int i = 0; i < extras.length; i++) {
                idExtras[i] = Integer.parseInt(extras[i]);
            }
            return idExtras;
        } else {
            return null;
        }
    }

    private String comprobarParametroDescripcion(String descripcion) throws IllegalArgumentException {
        // Asumimos que es correcto de momento
        return descripcion;
    }

}
