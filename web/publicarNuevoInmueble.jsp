<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="db.vo.*,db.dao.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
    <title>Publicar nuevo inmueble</title>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/infoInmueble.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
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
            <a class="navbar-brand" href="<%=request.getContextPath()%>/index.html">Jaus</a>
        </div>
        <div class="collapse navbar-collapse" id="mi-navbar">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Mi cuenta <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<%=request.getContextPath()%>/publicarNuevoInmueble.jsp">Publicar inmueble</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="<%=request.getContextPath()%>/perfilActividad.jsp">Ver Actividad</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="<%=request.getContextPath()%>/perfil.jsp">Modificar Perfil</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="<%=request.getContextPath()%>/cerrarSersion.do">Cerrar sesión</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="page-header">
                <h1>Publicar nuevo anuncio</h1>
            </div>
            <form class="form-horizontal" action="<%=request.getContextPath()%>/insertarInmueble.do"
                  enctype="multipart/form-data" method="post">
                <fieldset>
                    <legend>Informacion del piso</legend>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="precio">Precio:</label>
                        <div class="col-md-5">
                            <input name="precio" id="precio" type="number" class="form-control"
                                   placeholder="Precio en euros">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="superficie">Superficie:</label>
                        <div class="col-md-5">
                            <input name="superficie" id="superficie" type="number" class="form-control"
                                   placeholder="Superficie en metros cuadrados">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="num-habitaciones">Numero de habitaciones:</label>
                        <div class="col-md-5">
                            <input name="num-habitaciones" id="num-habitaciones" type="number" class="form-control"
                                   placeholder="Numero de habitaciones">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="num-bagnos">Numero de baños:</label>
                        <div class="col-md-5">
                            <input name="num-bagnos" id="num-bagnos" type="number" class="form-control"
                                   placeholder="Numero de baños">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="planta">Planta:</label>
                        <div class="col-md-5">
                            <input name="planta" id="planta" type="number" class="form-control" placeholder="Planta">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="tipo-inmueble">Tipo de inmueble:</label>
                        <div class="col-md-5">
                            <select name="tipo-inmueble" class="form-control" id="tipo-inmueble">
                                <%
                                    List<TipoInmuebleVO> tiposDeInmueble = TipoInmuebleDAO.
                                            getAllTipoInmueble(GestorDeConexionesBD.getConnection());
                                    for (TipoInmuebleVO t : tiposDeInmueble) {
                                %>
                                <option value="<%=t.getIdTipo()%>">
                                    <%=t.getNombreTipo()%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Que:</label>
                        <div class="col-md-5">
                            <label class="radio-inline"><input type="radio" name="vendeAlquila" value="vende">Se
                                vende</label>
                            <label class="radio-inline"><input type="radio" name="vendeAlquila" value="alquila">Se
                                alquila</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Imagenes:</label>
                        <div class="col-md-5">
                            <input name="imagen" type="file" id="input" multiple="multiple">
                        </div>
                    </div>
                    <fieldset>
                        <legend>Localizacion del piso</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="pais">Pais:</label>
                            <div class="col-md-5">
                                <select name="pais" class="form-control" id="pais">
                                    <% List<PaisVO> paises = PaisDAO.
                                            obtenerTodoLosPaises(GestorDeConexionesBD.getConnection());
                                        for (PaisVO pais : paises) {
                                    %>
                                    <option value="<%=pais.getIdPais()%>">
                                        <%=pais.getNombrePais()%>
                                    </option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="provincia">Provincia:</label>
                            <div class="col-md-5">
                                <select name="provincia" class="form-control" id="provincia">
                                    <% List<ProvinciaVO> provincias = ProvinciaDAO.
                                            obtenerTodasLasProvincias(GestorDeConexionesBD.getConnection());
                                        for (ProvinciaVO t : provincias) {
                                    %>
                                    <option value="<%=t.getIdProvincia()%>">
                                        <%=t.getNombreProvincia()%>
                                    </option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="poblacion">Poblacion:</label>
                            <div class="col-md-5">
                                <input name="poblacion" class="form-control" type="text" id="poblacion"
                                       placeholder="Poblacion">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="codigoPostal">Codigo postal:</label>
                            <div class="col-md-5">
                                <input name="codigoPostal" class="form-control" type="number" id="codigoPostal"
                                       placeholder="Codigo postal">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="tipo-via">Tipo de via:</label>
                            <div class="col-md-5">
                                <select name="tipo-via" class="form-control" id="tipo-via">
                                    <% List<TiposDeViaVO> tiposDeVia = TiposDeViaDAO.
                                            obtenerTodosTiposDeVia(GestorDeConexionesBD.getConnection());
                                        for (TiposDeViaVO t : tiposDeVia) {
                                    %>
                                    <option value="<%=t.getIdVia()%>">
                                        <%=t.getNombreTipo()%>
                                    </option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="nombre-calle">Nombre calle:</label>
                            <div class="col-md-5">
                                <input name="nombre-calle" class="form-control" type="text" id="nombre-calle"
                                       placeholder="Nombre de la calle">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="numero-calle">Numero:</label>
                            <div class="col-md-5">
                                <input name="numero-calle" class="form-control" type="number" id="numero-calle"
                                       placeholder="Numero de la via">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Extras</legend>
                        <div class="form-group col-md-12">
                            <%
                                List<ExtrasVO> extras = ExtrasDAO.getAllExtras(GestorDeConexionesBD.getConnection());
                                for (ExtrasVO e : extras) {
                            %>
                            <label class="checkbox-inline">
                                <input name="extras" type="checkbox" value="<%=e.getIdExtra()%>">
                                <%=e.getNombre()%>
                            </label>
                            <%
                                }
                            %>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Descripcion</legend>
                        <div class="col-md-12">
                            <div class="form-group">
                                <textarea name="descripcion" class="form-control" id="id-mensaje" rows="3"></textarea>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="form-control" id="id-publicar">
                            </div>
                        </div>
                    </fieldset>
                </fieldset>
            </form>
        </div>
    </div>
    <footer class="container text-center">
        <p>Informacion de contacto y ayuda</p>
    </footer>
</div>
</body>
</html>
