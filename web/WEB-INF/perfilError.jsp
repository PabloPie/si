<%@ page import="db.vo.UsuarioRegistradoVO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="db.vo.TiposDeViaVO" %>
<%@ page import="java.util.List" %>
<%@ page import="db.vo.PaisVO" %>
<%@ page import="db.vo.ProvinciaVO" %>
<%@ page import="db.dao.*" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Perfil Usuario</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/perfil.css"/>

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
<%

    String usuario = (String) session.getAttribute("currentSessionUser");
    HashMap<String, String> errores = (HashMap<String, String>) request.getAttribute("errores");
    UsuarioRegistradoVO user = null;
    try {
        user = UsuarioRegistradoDAO.encontrarDatosUsuario(usuario, GestorDeConexionesBD.getConnection());
    } catch (SQLException e) {
        e.printStackTrace();
    }
    String fecha = "";
    String dir = "";
    String poblacion = "";
    String provincia = "";
    String pais = "";
    int idprovincia = 0;
    int idpais = 0;
    int cp = 0;
    int tlf = 0;
    int idvia = 0;
    int numdir = 0;
    if (user != null) {
        if (user.getFecha() != null) {
            fecha = user.getFecha().toString();
        }
        if (user.getTelefono() != 0) {
            tlf = user.getTelefono();
        }
        if (user.getLocation() != null) {
            idvia = user.getLocation().getTipoVia().getIdVia();
            dir = user.getLocation().getNombreDir();
            numdir = user.getLocation().getNumeroDir();
            poblacion = user.getLocation().getPoblacion();
            idprovincia = user.getLocation().getProvincia().getIdProvincia();
            provincia = user.getLocation().getProvincia().getNombreProvincia();
            idpais = user.getLocation().getProvincia().getPais().getIdPais();
            pais = user.getLocation().getProvincia().getPais().getNombrePais();
            cp = user.getLocation().getCodigoPostal();
        }

    }
%>
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


<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <form class="form-horizontal" role="form">
            <fieldset>

                <!-- Info personal -->
                <legend>Información Personal</legend>

                <!-- Nombre-->
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="nombre">Nombre</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="Pepito" value="<%=user.getNombre()%>" id="nombre"
                               class="form-control" name="nombre">

                    </div>
                </div>

                <!-- Apellidos-->
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="apellidos">Apellidos</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="García García" value="<%=user.getApellidos()%>" id="apellidos"
                               class="form-control" name="apellidos">
                    </div>
                </div>

                <!-- Correo-->
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="email">Email</label>
                    <div class="col-sm-10">
                        <input type="email" value="<%=user.getEmail()%>" placeholder="pepito@correopepito.com"
                               id="email" class="form-control" name="email">
                    </div>
                </div>

                <!-- Fecha de nacimiento-->
                <div class="form-group">
                    <label class="control-label col-sm-2" for="fecha">Fecha de Nacimiento</label>
                    <div class="col-sm-10">
                        <input type="date" value="<%=fecha%>" id="fecha" class="form-control" name="fecha">
                    </div>
                </div>

                <!-- Telefono-->
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="telefono">Teléfono</label>
                    <div class="col-sm-10">
                        <input type="tel" <%if(tlf>0) out.println("value=\"" + tlf + "\"");%>
                               id="telefono" class="form-control" name="telefono">
                    </div>
                </div>

                <!-- contrasena-->
                <div class="form-group <%=errores.containsKey("passError") ? "has-error has-feedback" : ""%>">
                    <label class="col-sm-2 control-label" for="password">Contraseña</label>
                    <div class="col-sm-10">
                        <input type="password" placeholder="**********" id="password" class="form-control"
                               name="password">
                    </div>
                </div>

                <!-- Confirmar contrasena-->
                <div class="form-group <%=errores.containsKey("passError") ? "has-error has-feedback" : ""%>">
                    <label class="col-sm-2 control-label" for="REPassword">Repetir Contraseña</label>
                    <div class="col-sm-10">
                        <input type="password" placeholder="**********" id="REPassword" class="form-control"
                               name="repassword">
                        <% if (errores.containsKey("passError")) {
                            out.println("<span class=\"glyphicon glyphicon-remove form-control-feedback\" ></span >");
                            out.println("<span class=\"help-block\" >" + errores.get("passError") + "</span >");
                        }
                        %>
                    </div>
                </div>


                <!-- Direccion -->
                <legend>Dirección</legend>

                <!-- Dir 1-->
                <div class="form-group <%=(errores.containsKey("direccion")||
         errores.containsKey("invalidLocation")) ? "has-error has-feedback" : ""%>">
                    <label class="col-sm-2 control-label" for="direccion">Dirección</label>
                    <div class="col-sm-10">
                        <input type="text" value="<%=dir%>" placeholder="Dirección" id="direccion"
                               class="form-control" name="direccion">
                        <% if (errores.containsKey("direccion")) {
                            out.println("<span class=\"glyphicon glyphicon-remove form-control-feedback\" ></span >");
                            out.println("<span class=\"help-block\" >" + errores.get("direccion") + "</span >");
                        }
                        %>
                    </div>
                </div>

                <div class="form-group <%=(errores.containsKey("numdir") || errores.containsKey("tipo-via") ||
         errores.containsKey("invalidLocation")) ? "has-error has-feedback" : ""%>">
                    <label class="col-sm-2 control-label" for="numdir">Número</label>
                    <div class="col-sm-4">
                        <input type="text" <%if(cp>0) out.println("value=\"" + numdir + "\"");%> placeholder="Número"
                               id="numdir"
                               class="form-control" name="numdir">
                        <% if (errores.containsKey("numdir")) {
                            out.println("<span class=\"glyphicon glyphicon-remove form-control-feedback\" ></span >");
                            out.println("<span class=\"help-block\" >" + errores.get("numdir") + "</span >");
                        }
                        %>
                    </div>

                    <label class="col-sm-2 control-label" for="tipo-via">Tipo de Vía</label>
                    <div class="col-sm-4">
                        <select name="tipo-via" class="form-control" id="tipo-via">
                            <% List<TiposDeViaVO> tiposDeVia = TiposDeViaDAO.
                                    obtenerTodosTiposDeVia(GestorDeConexionesBD.getConnection());
                                for (TiposDeViaVO t : tiposDeVia) {
                                    if (t.getIdVia() == idvia) {
                                        out.println("<option selected" + "value=\"" + t.getIdVia() + "\">" +
                                                t.getNombreTipo() + "</option>");
                                    } else {
                                        out.println("<option " + "value=\"" + t.getIdVia() + "\">" +
                                                t.getNombreTipo() + "</option>");
                                    }
                                }%>
                        </select>
                    </div>
                </div>


                <!-- Poblacion-->
                <div class="form-group <%=(errores.containsKey("poblacion")
                        || errores.containsKey("invalidLocation")) ? "has-error has-feedback" : ""%>">
                    <label class="col-sm-2 control-label" for="poblacion">Población</label>
                    <div class="col-sm-10">
                        <input type="text" value="<%=poblacion%>" placeholder="Población"
                               id="poblacion" class="form-control" name="poblacion">
                        <% if (errores.containsKey("poblacion")) {
                            out.println("<span class=\"glyphicon glyphicon-remove form-control-feedback\" ></span >");
                            out.println("<span class=\"help-block\" >" + errores.get("poblacion") + "</span >");
                        }
                        %>
                    </div>
                </div>

                <!-- Provincia-->
                <div class="form-group <%=(errores.containsKey("provincia") || errores.containsKey("cp") ||
                errores.containsKey("invalidLocation")) ? "has-error has-feedback" : ""%>">
                    <label class="col-sm-2 control-label" for="provincia">Provincia</label>
                    <div class="col-sm-4">
                        <select name="provincia" class="form-control" id="provincia">
                            <% List<ProvinciaVO> provincias = ProvinciaDAO.
                                    obtenerTodasLasProvincias(GestorDeConexionesBD.getConnection());
                                for (ProvinciaVO t : provincias) {
                                    if (t.getIdProvincia() == idprovincia) {
                                        out.println("<option selected" + "value=\"" + t.getIdProvincia() + "\">" +
                                                t.getNombreProvincia() + "</option>");
                                    } else {
                                        out.println("<option " + "value=\"" + t.getIdProvincia() + "\">" +
                                                t.getNombreProvincia() + "</option>");
                                    }
                                }%>
                        </select>
                    </div>

                    <!-- CP-->
                    <label class="col-sm-2 control-label" for="codpost">CP</label>
                    <div class="col-sm-4">
                        <input type="text" <%if(cp>0) out.println("value=\"" + cp + "\"");%> placeholder="Código Postal"
                               id="codpost" class="form-control" name="codigopostal">
                        <% if (errores.containsKey("cp")) {
                            out.println("<span class=\"glyphicon glyphicon-remove form-control-feedback\" ></span >");
                            out.println("<span class=\"help-block\" >" + errores.get("cp") + "</span >");
                        }
                        %>
                    </div>
                </div>


                <!-- Pais-->
                <div class="form-group <%=(errores.containsKey("pais")
                        || errores.containsKey("invalidLocation")) ? "has-error has-feedback" : ""%>">
                    <label class="col-sm-2 control-label" for="pais">País</label>
                    <div class="col-sm-10">
                        <select name="pais" class="form-control" id="pais">
                            <% List<PaisVO> paises = PaisDAO.
                                    obtenerTodoLosPaises(GestorDeConexionesBD.getConnection());
                                for (PaisVO pais1 : paises) {
                                    if (pais1.getIdPais() == idpais) {
                                        out.println("<option selected" + "value=\"" + pais1.getIdPais() + "\">" +
                                                pais1.getNombrePais() + "</option>");
                                    } else {
                                        out.println("<option " + "value=\"" + pais1.getIdPais() + "\">" +
                                                pais1.getNombrePais() + "</option>");
                                    }
                                }%>
                        </select>
                        <p></p>
                        <% if (errores.containsKey("invalidLocation")) {
                            out.println("<span class=\"glyphicon glyphicon-remove form-control-feedback\" ></span >");
                            out.println("<span class=\"help-block\" >" + errores.get("invalidLocation") + "</span >");
                        }
                        %>
                    </div>
                </div>

                <!-- Botones envio y borrar cuenta-->
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="pull-right">
                            <button type="submit" name="envio" value="actualiza" class="btn btn-primary form-control">
                                Save
                            </button>
                        </div>
                        <div class="pull-left">
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#ModalDelete">
                                Borrar cuenta
                            </button>
                        </div>
                    </div>
                </div>

            </fieldset>
        </form>
    </div><!-- /.col-lg-12 -->
</div><!-- /.row -->


<!-- Modal -->
<div id="ModalDelete" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Confirmar eliminación de cuenta</h4>
            </div>
            <div class="modal-body">
                <p>¿Seguro?</p>
            </div>
            <div class="modal-footer">
                <form action="<%=request.getContextPath()%>/UpdateUserdata.do" method="POST">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" name="envio" value="delete" class="btn btn-danger">Confirmar</button>
                </form>
            </div>
        </div>

    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
<script type="text/javascript">
    $(function () {
        $('#datetimepicker1').datetimepicker({
            language: 'es'
        });
    });
</script>
</body>
</html>