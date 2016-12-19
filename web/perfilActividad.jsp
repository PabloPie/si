<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="db.vo.*,db.dao.*,java.util.*" %>
<%@ page import="java.sql.SQLException" %>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Actividad del usuario</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/perfil.css"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
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
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#mi-navbar"
                    aria-expanded="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">Jaus</a>
        </div>
        <div class="collapse navbar-collapse" id="mi-navbar">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Mi cuenta <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="publicarNuevoInmueble.jsp">Publicar inmueble</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="perfilActividad.jsp">Ver Actividad</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="perfil.jsp">Modificar Perfil</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="cerrarSersion.do">Cerrar sesión</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container principal">
    <div class="page-header">
        <h1>Mi historial:</h1>
    </div>
    <div class="row">
        <div class="center col-md-9 col-sm-12">
            <%
                String idUser = (String) session.getAttribute("currentSessionUser");

                List<InmuebleVO> inmuebleVOList = null;
                try {
                    inmuebleVOList = InmuebleDAO.getInmuebles(idUser, GestorDeConexionesBD.getConnection());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (InmuebleVO inmuebleVO : inmuebleVOList) {

            %>

            <div class="col-md-4 col-sm-4">
                <form method="get" action="http://localhost:8080/infoInmueble.jsp">
                    <input id="idInmueble" type="hidden" name="idInmueble" value="<%=inmuebleVO.getIdInmueble()%>">
                    <button type="submit" class="caja-piso">
                        <img src="<%=inmuebleVO.getImagenes().get(0).getRuta()%>" alt="Foto inmueble"/>
                        <p><%=inmuebleVO.getPrecio()%> €<p>
                        <p><%=inmuebleVO.getNumHabitaciones()%> habitaciones</p>
                        <p><%=inmuebleVO.getSuperficie()%>  m<sup>2</sup></p>
                        <p><%=inmuebleVO.getLocalizacion().getTipoVia().getNombreTipo()%> <%=inmuebleVO.getLocalizacion().getNombreDir()%></p>
                        <p><%=inmuebleVO.getLocalizacion().getPoblacion()%></p>
                    </button>
                </form>
            </div>

            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
