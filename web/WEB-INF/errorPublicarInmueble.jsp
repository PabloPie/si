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
                    <% HashMap<String, String> errores = (HashMap<String, String>) request.getAttribute("errores");%>
                    <div class="form-group <%=errores.containsKey("precio") ? "has-error has-feedback" : ""%> ">
                        <label class="col-md-4 control-label" for="precio">Precio:</label>
                        <div class="col-md-5">
                            <input name="precio" id="precio" type="number" class="form-control"
                                   placeholder="Precio en euros" value="<%=request.getParameter("precio")%>">
                            <% if (errores.containsKey("precio")) {
                            %>
                            <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                            <span class="help-block"><%=errores.get("precio")%></span>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <div class="form-group <%=errores.containsKey("superficie") ? "has-error has-feedback" : ""%> ">
                        <label class="col-md-4 control-label" for="superficie">Superficie:</label>
                        <div class="col-md-5">
                            <input name="superficie" id="superficie" type="number" class="form-control"
                                   placeholder="Superficie en metros cuadrados"
                                   value="<%=request.getParameter("superficie")%>">
                            <% if (errores.containsKey("superficie")) {
                            %>
                            <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                            <span class="help-block"><%=errores.get("superficie")%></span>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <div class="form-group <%=errores.containsKey("num-habitaciones") ?"has-error has-feedback" : ""%> ">
                        <label class="col-md-4 control-label" for="num-habitaciones">Numero de habitaciones:</label>
                        <div class="col-md-5">
                            <input name="num-habitaciones" id="num-habitaciones" type="number" class="form-control"
                                   placeholder="Numero de habitaciones"
                                   value="<%=request.getParameter("num-habitaciones")%>">
                            <% if (errores.containsKey("num-habitaciones")) {
                            %>
                            <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                            <span class="help-block"><%=errores.get("num-habitaciones")%></span>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <div class="form-group <%=errores.containsKey("num-bagnos") ? "has-error has-feedback" : ""%> ">
                        <label class="col-md-4 control-label" for="num-bagnos">Numero de baños:</label>
                        <div class="col-md-5">
                            <input name="num-bagnos" id="num-bagnos" type="number" class="form-control"
                                   placeholder="Numero de baños" value="<%=request.getParameter("num-bagnos")%>">
                            <% if (errores.containsKey("num-bagnos")) {
                            %>
                            <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                            <span class="help-block"><%=errores.get("num-bagnos")%></span>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <div class="form-group <%=errores.containsKey("planta") ? "has-error has-feedback" : ""%> ">
                        <label class="col-md-4 control-label" for="planta">Planta:</label>
                        <div class="col-md-5">
                            <input name="planta" id="planta" type="number" class="form-control" placeholder="Planta"
                                   value="<%=request.getParameter("planta")%>">
                            <% if (errores.containsKey("planta")) {
                            %>
                            <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                            <span class="help-block"><%=errores.get("planta")%></span>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <div class="form-group <%=errores.containsKey("tipo-inmueble") ? "has-error has-feedback" : ""%> ">
                        <label class="col-md-4 control-label" for="tipo-inmueble">Tipo de inmueble:</label>
                        <div class="col-md-5">
                            <select name="tipo-inmueble" class="form-control" id="tipo-inmueble">
                                <% List<TipoInmuebleVO> tiposDeInmueble = TipoInmuebleDAO.
                                        getAllTipoInmueble(GestorDeConexionesBD.getConnection());
                                    for (TipoInmuebleVO t : tiposDeInmueble) {
                                        String sel = "";
                                        if (!errores.containsKey("tipo-inmueble") &&
                                                String.valueOf(t.getIdTipo()).equals(request.getParameter("tipo-inmueble"))) {
                                            sel = "selected";
                                        }
                                %>
                                <option <%=sel%> value="<%=t.getIdTipo()%>">
                                    <%=t.getNombreTipo()%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group <%=errores.containsKey("vendeAlquila") ? "has-error has-feedback" : ""%> ">
                        <label class="col-md-4 control-label">Que:</label>
                        <div class="col-md-5">
                            <label class="radio-inline"><input type="radio" name="vendeAlquila" value="vende"
                                <%= !errores.containsKey("vendeAlquila") &&
                                request.getParameter("vendeAlquila").equals("vende") ? " checked" : ""%>>Se
                                vende</label>
                            <label class="radio-inline"><input type="radio" name="vendeAlquila" value="alquila"
                                <%= !errores.containsKey("vendeAlquila") &&
                                request.getParameter("vendeAlquila").equals("alquila") ? " checked" : ""%>>Se
                                alquila</label>
                            <% if (errores.containsKey("vendeAlquila")) {
                            %>
                            <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                            <span class="help-block"><%=errores.get("vendeAlquila")%></span>
                            <%
                                }
                            %>
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
                        <div class="form-group <%=errores.containsKey("pais") ? "has-error has-feedback" : ""%> ">
                            <label class="col-md-4 control-label" for="pais">Pais:</label>
                            <div class="col-md-5">
                                <select name="pais" class="form-control" id="pais">
                                    <% List<PaisVO> paises = PaisDAO.
                                            obtenerTodoLosPaises(GestorDeConexionesBD.getConnection());
                                        for (PaisVO pais : paises) {
                                            String sel = "";
                                            if (!errores.containsKey("pais") &&
                                                    String.valueOf(pais.getIdPais()).equals(request.getParameter("pais"))) {
                                                sel = "selected";
                                            }
                                    %>
                                    <option <%=sel%> value="<%=pais.getIdPais()%>">
                                        <%=pais.getNombrePais()%>
                                    </option>
                                    <%
                                        }
                                    %>
                                </select>
                                <% if (errores.containsKey("pais")) {
                                %>
                                <span class="glyphicon glyphicon-remove form-control-feedback"></span>;
                                <span class="help-block"><%=errores.get("pais")%></span>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <div class="form-group <%=errores.containsKey("provincia") ? "has-error has-feedback" : ""%> ">
                            <label class="col-md-4 control-label" for="provincia">Provincia:</label>
                            <div class="col-md-5">
                                <select name="provincia" class="form-control" id="provincia">
                                    <% List<ProvinciaVO> provincias = ProvinciaDAO.
                                            obtenerTodasLasProvincias(GestorDeConexionesBD.getConnection());
                                        for (ProvinciaVO t : provincias) {
                                            String sel = "";
                                            if (!errores.containsKey("provincia")
                                                    && String.valueOf(t.getIdProvincia()).equals(request.getParameter("provincia"))) {
                                                sel = "selected";
                                            }
                                            out.println("<option " + sel + " value=\"" + t.getIdProvincia() + "\">" +
                                                    t.getNombreProvincia() + "</option>");
                                        }
                                    %>
                                </select>
                                <% if (errores.containsKey("provincia")) {
                                %>
                                <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                <span class="help-block"><%=errores.get("provincia")%></span>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <div class="form-group <%=errores.containsKey("poblacion") ?"has-error has-feedback" : ""%> ">
                            <label class="col-md-4 control-label" for="poblacion">Poblacion:</label>
                            <div class="col-md-5">
                                <input name="poblacion" class="form-control" type="text" id="poblacion"
                                       placeholder="Poblacion" <%="value=\"" + request.getParameter("poblacion") + "\""%>>
                                <% if (errores.containsKey("poblacion")) {
                                %>
                                <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                <span class="help-block"><%=errores.get("poblacion")%></span>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <div class="form-group <%=errores.containsKey("codigoPostal") ?"has-error has-feedback" : ""%> ">
                            <label class="col-md-4 control-label" for="codigoPostal">Codigo postal:</label>
                            <div class="col-md-5">
                                <input name="codigoPostal" class="form-control" type="number" id="codigoPostal"
                                       placeholder="Codigo postal" <%="value=\"" + request.getParameter("codigoPostal") + "\""%>>
                                <% if (errores.containsKey("codigoPostal")) {
                                %>
                                <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                <span class="help-block"><%=errores.get("codigoPostal")%></span>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <div class="form-group <%=errores.containsKey("tipo-via") ?"has-error has-feedback" : ""%> ">
                            <label class="col-md-4 control-label" for="tipo-via">Tipo de via:</label>
                            <div class="col-md-5">
                                <select name="tipo-via" class="form-control" id="tipo-via">
                                    <% List<TiposDeViaVO> tiposDeVia = TiposDeViaDAO.
                                            obtenerTodosTiposDeVia(GestorDeConexionesBD.getConnection());
                                        for (TiposDeViaVO t : tiposDeVia) {
                                            String sel = "";
                                            if (String.valueOf(t.getIdVia()).equals(request.getParameter("tipo-via"))) {
                                                sel = "selected";
                                            }
                                    %>
                                    <option <%=sel%> value="<%=t.getIdVia()%>">
                                        <%=t.getNombreTipo()%>
                                    </option>
                                    <%
                                        }
                                    %>
                                </select>
                                <% if (errores.containsKey("tipo-via")) {
                                %>
                                <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                <span class="help-block"><%=errores.get("tipo-via")%></span>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <div class="form-group <%=errores.containsKey("nombre-calle") ?"has-error has-feedback" : ""%> ">
                            <label class="col-md-4 control-label" for="nombre-calle">Nombre calle:</label>
                            <div class="col-md-5">
                                <input name="nombre-calle" class="form-control" type="text" id="nombre-calle"
                                       placeholder="Nombre de la calle" <%="value=\"" + request.getParameter("nombre-calle") + "\""%>>
                                <% if (errores.containsKey("nombre-calle")) {
                                %>
                                <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                <span class="help-block"><%=errores.get("nombre-calle")%></span>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <div class="form-group <%=errores.containsKey("numero-calle") ?"has-error has-feedback" : ""%> ">
                            <label class="col-md-4 control-label" for="numero-calle">Numero:</label>
                            <div class="col-md-5">
                                <input name="numero-calle" class="form-control" type="number" id="numero-calle"
                                       placeholder="Numero de la via" <%="value=\"" + request.getParameter("numero-calle") + "\""%>>
                                <% if (errores.containsKey("numero-calle")) {
                                %>
                                <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                <span class="help-block"><%=errores.get("numero-calle")%></span>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Extras</legend>
                        <div class="form-group col-md-12 <%=errores.containsKey("extras") ?"has-error has-feedback" : ""%> ">
                            <% List<ExtrasVO> extras = ExtrasDAO.
                                    getAllExtras(GestorDeConexionesBD.getConnection());
                                String values[] = request.getParameterValues("extras");
                                for (ExtrasVO e : extras) {
                                    String sel = "";
                                    if (values != null) {
                                        boolean enc = false;
                                        for (int i = 0; i < values.length && !enc; i++) {
                                            if (String.valueOf(e.getIdExtra()).equals(values[i])) {
                                                enc = true;
                                                sel = "checked";
                                            }
                                        }
                                    }
                            %>
                            <label class="checkbox-inline">
                                <input name="extras" <%=sel%> type="checkbox" value="<%=e.getIdExtra()%>">
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
                            <div class="form-group <%=errores.containsKey("descripcion") ?"has-error has-feedback" : ""%> ">
                                <textarea name="descripcion" class="form-control" id="id-mensaje"
                                          rows="3"><%=request.getParameter("descripcion")%></textarea>
                                <% if (errores.containsKey("descripcion")) {
                                %>
                                <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                <span class="help-block"><%=errores.get("descripcion")%></span>
                                <%
                                    }
                                %>
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
