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
                    <form class="nav nav-sidebar">
                        <fieldset>
                            <legend>Opciones de filtro</legend>
                            <fieldset>
                                <legend>Precio</legend>
                                <label for="dinero-desde">Desde: </label>
                                <div class="input-group">
                                    <span class="input-group-addon">&#8364</span> <input
                                        id="dinero-desde" type="text" class="form-control"
                                        placeholder="Desde"> <span class="input-group-addon">,00</span>
                                </div>
                                <label for="dinero-hasta">Hasta: </label>
                                <div class="input-group">
                                    <span class="input-group-addon">&#8364</span> <input
                                        id="dinero-hasta" type="text" class="form-control"
                                        placeholder="Hasta"> <span class="input-group-addon">,00</span>
                                </div>
                            </fieldset>
                            <fieldset>
                                <legend>Superficie</legend>
                                <label for="sup-desde">Desde: </label>
                                <div class="input-group">
                                    <span class="input-group-addon">m<sup>2</sup></span> <input
                                        id="sup-desde" type="text" class="form-control"
                                        placeholder="Desde"> <span class="input-group-addon">,00</span>
                                </div>
                                <label for="sup-hasta">Hasta: </label>
                                <div class="input-group">
                                    <span class="input-group-addon">m<sup>2</sup></span> <input
                                        id="sup-hasta" type="text" class="form-control"
                                        placeholder="Hasta"> <span class="input-group-addon">,00</span>
                                </div>
                            </fieldset>
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
                    String palabraBusqueda = request.getParameter("Buscar");
                    List<InmuebleVO> inmuebleVOList = InmuebleDAO.getInmuebles(seAlquilan, seVenden, palabraBusqueda,
                            -1, -1, -1, -1, GestorDeConexionesBD.getConnection());
                    for (InmuebleVO inmuebleVO : inmuebleVOList) {
                        out.println("<div class=\"col-md-4 col-sm-4\">");
                        out.println("<div class=\"caja-piso thumbnail\">");
                        out.println("<img class=\"img-responsive\" src=\"http://localhost:8080/imgs/" +
                                inmuebleVO.getImagenes().get(0).getRuta() + "\" alt=\"placeholder\" width=\"225\" height=\"300\"/>");
                        out.println("<div class=\"caption\">");
                        out.println("<h3> Piso X </h3>");
                        out.println("<p>Piso en el centro de la ciudad</p>");
                        out.println("</div>");
                        out.println("</div>");
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
