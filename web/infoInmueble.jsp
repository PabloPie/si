<%@ page import="db.dao.GestorDeConexionesBD" %>
<%@ page import="db.dao.InmuebleDAO" %>
<%@ page import="db.vo.InmuebleVO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="es">
<head>
    <title>Jaus</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--<link rel="stylesheet" href="assets/css/main.css" />-->
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
    <link rel="stylesheet" href="assets/css/infoInmueble.css">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<%
    int idInmueble = Integer.parseInt(request.getParameter("idInmueble"));
    InmuebleVO inmueble = null;
    try {
        inmueble = InmuebleDAO.getInmuebles(idInmueble, GestorDeConexionesBD.getConnection());
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
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
<div class="container">
    <div class="page-header">
        <h1>
            <%= inmueble.getLocalizacion().getPoblacion()%>
            <small><%= inmueble.getLocalizacion().getNombreDir()%>
            </small>
        </h1>
    </div>
    <div class="row">
        <div class="col-md-4">
            <div class="informacion">
                <ul class="centrar-v">
                    <li><span class="titulos">Precio:</span>
                        <p><%=inmueble.getPrecio()%>
                        </p></li>
                    <li><span class="titulos">Tipo de inmueble:</span>
                        <p><%=inmueble.getTipoInmueble().getNombreTipo()%>
                        </p></li>
                    <li><span class="titulos">Planta:</span>
                        <p><%=inmueble.getPlanta()%>
                        </p></li>
                    <li><span class="titulos">Metros cuadrados:</span>
                        <p><%=inmueble.getSuperficie()%><sup>2</sup></p></li>
                    <li><span class="titulos">Número de habitaciones:</span>
                        <p><%=inmueble.getNumHabitaciones()%>
                        </p></li>
                    <li><span class="titulos">Número de baños:</span>
                        <p><%=inmueble.getNumBagnos()%>
                        </p></li>
                    <%
                        String listaExtras = "";
                        for (int i = 0; i < inmueble.getExtras().size() - 1; i++) {
                            listaExtras += inmueble.getExtras().get(i).getNombre() + ", ";
                        }
                        listaExtras += inmueble.getExtras().get(inmueble.getExtras().size() - 1).getNombre();
                    %>
                    <li><span class="titulos">Extras:</span>
                        <p><%= listaExtras%>
                        </p></li>
                </ul>
            </div>
        </div>
        <div class="col-md-8">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img class="centrar-v img-responsive"
                             src="assets/images/pic01.jpg">
                    </div>
                    <div class="item">
                        <img class="centrar-v img-responsive"
                             src="assets/images/pic02.jpg">
                    </div>
                    <div class="centrar-v item">
                        <img class="centrar-v img-responsive"
                             src="assets/images/pic03.jpg">
                    </div>
                    <div class="centrar-v item">
                        <img class="centrar-v img-responsive"
                             src="assets/images/pic04.jpg">
                    </div>
                </div>

                <!-- Left and right controls -->
                <a class="left carousel-control" href="#myCarousel" role="button"
                   data-slide="prev"> <span
                        class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Anterior</span>
                </a> <a class="right carousel-control" href="#myCarousel" role="button"
                        data-slide="next"> <span
                    class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Siguiente</span>
            </a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="page-header">
                <h2>Descripción</h2>
            </div>
            <p><%= inmueble.getDescripcion()%>
            </p>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="page-header">
                <h2>Información de contacto</h2>
            </div>
            <span class="titulos glyphicon glyphicon-earphone"
                  aria-hidden="true">976555555</span>
        </div>
    </div>
    <div class="page-header">
        <h3>Deje un mensaje al anunciante</h3>
    </div>
    <div class="row">
        <div class="col-md-3 col-md-offset-3">
            <div class="form-group">
                <label for="id-nombre">Nombre:</label> <input type="text"
                                                              class="form-control" id="id-nombre">
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <label for="id-email">Email:</label> <input type="email"
                                                            class="form-control" id="id-email">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="form-group">
                <label for="id-mensaje">Mensaje:</label>
                <textarea class="form-control" id="id-mensaje" rows="3"></textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 col-md-offset-4">
            <div class="form-group">
                <input type="submit" class="form-control" id="id-someter">
            </div>
        </div>
        <div class="col-md-2">
            <div class="form-group">
                <input type="reset" class="form-control" id="id-someter2">
            </div>
        </div>
    </div>
    <footer class="container text-center">
        <p>Informacion de contacto y ayuda</p>
    </footer>
</div>
</body>
</html>
