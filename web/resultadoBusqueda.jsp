<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="db.vo.*,db.dao.*,java.util.*" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Comprar inmuebles <%=request.getParameter("Buscar")%>
    </title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
          crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/resultadoBusqueda.css"/>

    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#mi-navbar"
                    aria-expanded="false">
                <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Jaus</a>
        </div>
        <div class="collapse navbar-collapse" id="mi-navbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span>
                    Mi cuenta</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container principal">
    <div class="page-header">
        <h1>Inmuebles en <%=request.getParameter("Buscar")%>
        </h1>
    </div>
    <div class="row">
        <div class="col-md-3 col-sm-12">
            <div class="row">
                <div class="col-md-12 col-sm-12 sidebar opciones-filtrado">
                    <form class="nav nav-sidebar" method="post" action="http://localhost:8080/resultadoBusqueda.jsp">
                        <input type="hidden" name="Buscar" value="<%=request.getParameter("Buscar")%>">
                        <input type="hidden" name="Selector" value="<%=request.getParameter("Selector")%>">
                        <fieldset>
                            <legend>Opciones de filtro</legend>
                            <fieldset>
                                <legend>Precio</legend>
                                <label for="dinero-desde">Desde: </label>
                                <div class="input-group">
                                    <span class="input-group-addon">€</span> <input
                                        id="dinero-desde" type="number" class="form-control" name="dinero-desde"
                                        value="<%= request.getParameter("dinero-desde") != null ? request.getParameter("dinero-desde") : "" %>"
                                        placeholder="Desde"> <span class="input-group-addon">,00</span>
                                </div>
                                <label for="dinero-hasta">Hasta: </label>
                                <div class="input-group">
                                    <span class="input-group-addon">€</span> <input
                                        id="dinero-hasta" type="number" class="form-control" name="dinero-hasta"
                                        value="<%= request.getParameter("dinero-hasta") != null ? request.getParameter("dinero-hasta") : "" %>"
                                        placeholder="Hasta"> <span class="input-group-addon">,00</span>
                                </div>
                            </fieldset>
                            <fieldset>
                                <legend>Superficie</legend>
                                <label for="sup-desde">Desde: </label>
                                <div class="input-group">
                                    <span class="input-group-addon">m<sup>2</sup></span> <input
                                        id="sup-desde" type="number" class="form-control" name="sup-desde"
                                        value="<%= request.getParameter("sup-desde") != null ? request.getParameter("sup-desde") : "" %>"
                                        placeholder="Desde"> <span class="input-group-addon">,00</span>
                                </div>
                                <label for="sup-hasta">Hasta: </label>
                                <div class="input-group">
                                    <span class="input-group-addon">m<sup>2</sup></span> <input
                                        id="sup-hasta" type="number" class="form-control" name="sup-hasta"
                                        value="<%= request.getParameter("sup-hasta") != null ? request.getParameter("sup-hasta") : "" %>"
                                        placeholder="Hasta"> <span class="input-group-addon">,00</span>
                                </div>
                            </fieldset>
                            <div class="center">
                                <button class="btn col-md-12 btn-md btn-default" type="submit">Filtrar</button>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div class="center col-md-9 col-sm-12">
            <div class="row">
                <%
                    String vendeAlquila = request.getParameter("Selector");
                    boolean seVenden = false;
                    boolean seAlquilan = false;
                    if (vendeAlquila != null) {
                        if (vendeAlquila.equals("seVenden")) {
                            seVenden = true;
                        }
                        if (vendeAlquila.equals("seAlquilan")) {
                            seAlquilan = true;
                        }
                    }
                    String dineroDesde = request.getParameter("dinero-desde");
                    String dineroHasta = request.getParameter("dinero-hasta");
                    String supDesde = request.getParameter("sup-desde");
                    String supHasta = request.getParameter("sup-hasta");
                    int dDesde = -1;
                    int dHasta = -1;
                    int sDesde = -1;
                    int sHasta = -1;
                    if (dineroDesde != null && !dineroDesde.isEmpty()) {
                        dDesde = Integer.parseInt(dineroDesde);
                    }
                    if (dineroHasta != null && !dineroHasta.isEmpty()) {
                        dHasta = Integer.parseInt(dineroHasta);
                    }
                    if (supDesde != null && !supDesde.isEmpty()) {
                        sDesde = Integer.parseInt(supDesde);
                    }
                    if (supHasta != null && !supHasta.isEmpty()) {
                        sHasta = Integer.parseInt(supHasta);
                    }
                    String palabraBusqueda = request.getParameter("Buscar");
                    List<InmuebleVO> inmuebleVOList = InmuebleDAO.getInmuebles(seAlquilan, seVenden, palabraBusqueda,
                            dDesde, dHasta, sDesde, sHasta, GestorDeConexionesBD.getConnection());
                    for (InmuebleVO inmuebleVO : inmuebleVOList) {
                        out.println("<form method=\"get\" action=\"http://localhost:8080/infoInmueble.jsp\">");
                        out.println("<input id=\"idInmueble\" type=\"hidden\" name=\"idInmueble\" value=\""
                                + inmuebleVO.getIdInmueble() + "\">");
                        out.println("<div class=\"col-md-4 col-sm-4\">");
                        out.println("<button type=\"submit\" class=\"caja-piso\" >");
                        out.println("<img src=\"" + inmuebleVO.getImagenes().get(0).getRuta()
                                + "\" alt=\"placeholder\"/>");
                        out.println("<div class=\"caption\">");
                        out.println("<p>" + inmuebleVO.getPrecio() + " €<p>");
                        out.println("<p>" + inmuebleVO.getNumHabitaciones() + " habitaciones</p>");
                        out.println("<p>" + inmuebleVO.getSuperficie() + " m<sup>2</sup></p>");
                        out.println("<p>" + inmuebleVO.getLocalizacion().getTipoVia().getNombreTipo() + " "
                                + inmuebleVO.getLocalizacion().getNombreDir() + "</p>");
                        out.println("<p>" + inmuebleVO.getLocalizacion().getPoblacion() + "</p>");
                        out.println("</button>");
                        out.println("</div>");
                        out.println("</form>");
                    }
                %>
            </div>
        </div>
    </div>
    <footer class="container text-center">
        <p>Informacion de contacto y ayuda</p>
    </footer>
</div>
</body>
</html>
