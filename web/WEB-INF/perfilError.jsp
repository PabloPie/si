<%@ page import="db.vo.UsuarioRegistradoVO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="db.vo.TiposDeViaVO" %>
<%@ page import="java.util.List" %>
<%@ page import="db.vo.PaisVO" %>
<%@ page import="db.vo.ProvinciaVO" %>
<%@ page import="db.dao.*" %>
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
<%

  String usuario = (String) session.getAttribute("currentSessionUser");
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
  int cp = 0;
  int tlf = 0;
  if (user != null) {
    if (user.getFecha() != null) {
      fecha = user.getFecha().toString();
    }
    if(user.getTelefono()!=0){
      tlf = user.getTelefono();
    }
    if (user.getLocation() != null) {
      dir = user.getLocation().getNombreDir() + ", " + user.getLocation().getNumeroDir();
      poblacion = user.getLocation().getPoblacion();
      provincia = user.getLocation().getProvincia().getNombreProvincia();
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
      <a class="navbar-brand" href="#">Jaus</a>
    </div>
    <div class="collapse navbar-collapse" id="mi-navbar">
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
             aria-expanded="false">Mi cuenta <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Ver Actividad</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Modificar Perfil</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Cerrar sesión</a></li>
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
          <label class="col-sm-2 control-label" for="idnombre">Nombre</label>
          <div class="col-sm-10">
            <input type="text" placeholder="Pepito" value="<%=user.getNombre()%>" id="idnombre"
                   class="form-control" name="nombre">
          </div>
        </div>

        <!-- Apellidos-->
        <div class="form-group">
          <label class="col-sm-2 control-label" for="idapellidos">Apellidos</label>
          <div class="col-sm-10">
            <input type="text" placeholder="García García" value="<%=user.getApellidos()%>" id="idapellidos"
                   class="form-control" name="apellidos">
          </div>
        </div>

        <!-- Correo-->
        <div class="form-group">
          <label class="col-sm-2 control-label" for="idemail">Email</label>
          <div class="col-sm-10">
            <input type="email" value="<%=user.getEmail()%>" placeholder="pepito@correopepito.com"
                   id="idemail" class="form-control" name="email">
          </div>
        </div>

        <!-- Fecha de nacimiento-->
        <div class="form-group">
          <label class="control-label col-sm-2" for="input-date">Fecha de Nacimiento</label>
          <div class="col-sm-10">
            <input type="date" value="<%=fecha%>" id="input-date" class="form-control" name="fecha">
          </div>
        </div>

        <!-- Telefono-->
        <div class="form-group">
          <label class="col-sm-2 control-label" for="Telefono">Teléfono</label>
          <div class="col-sm-10">
            <input type="tel" <%if(tlf>0) out.println("value=\"" + tlf +"");%>"
            id="Telefono" class="form-control" name="telefono">
          </div>
        </div>

        <!-- contrasena-->
        <div class="form-group">
          <label class="col-sm-2 control-label" for="Password">contrasena</label>
          <div class="col-sm-10">
            <input type="password" placeholder="**********" id="Password" class="form-control"
                   name="password">
          </div>
        </div>

        <!-- Confirmar contrasena-->
        <div class="form-group">
          <label class="col-sm-2 control-label" for="REPassword">Repetir contrasena</label>
          <div class="col-sm-10">
            <input type="password" placeholder="**********" id="REPassword" class="form-control"
                   name="repassword">
          </div>
        </div>

        <!-- Direccion -->
        <legend>Dirección</legend>

        <!-- Dir 1-->
        <div class="form-group">
          <label class="col-sm-2 control-label" for="linea1">Linea 1</label>
          <div class="col-sm-10">
            <input type="text" value="<%=dir%>" placeholder="Dirección Linea 1" id="linea1"
                   class="form-control" name="direccion1">
          </div>
        </div>

        <!-- Dir 2-->
        <div class="form-group">
          <label class="col-sm-2 control-label" for="tipo-via">Linea 2</label>
          <div class="col-sm-10">
            <select name="tipo-via" class="form-control" id="tipo-via">
              <% List<TiposDeViaVO> tiposDeVia = TiposDeViaDAO.
                      obtenerTodosTiposDeVia(GestorDeConexionesBD.getConnection());
                for (TiposDeViaVO t : tiposDeVia) {
                  out.println("<option " + "value=\"" + t.getIdVia() + "\">" +
                          t.getNombreTipo() + "</option>");
                }%>
            </select>
          </div>
        </div>

        <!-- Ciudad-->
        <div class="form-group">
          <label class="col-sm-2 control-label" for="ciudad">Ciudad</label>
          <div class="col-sm-10">
            <input type="text" value="<%=poblacion%>" placeholder="Ciudad"
                   id="ciudad" class="form-control" name="ciudad">
          </div>
        </div>

        <!-- Provincia-->
        <div class="form-group">
          <label class="col-sm-2 control-label" for="provincia">Provincia</label>
          <div class="col-sm-4">
            <select name="provincia"  class="form-control" id="provincia">
              <% List<ProvinciaVO> provincias = ProvinciaDAO.
                      obtenerTodasLasProvincias(GestorDeConexionesBD.getConnection());
                for (ProvinciaVO t : provincias) {
                  out.println("<option " + "value=\"" + t.getIdProvincia() + "\">" +
                          t.getNombreProvincia() + "</option>");
                }%>
            </select>
          </div>

          <!-- CP-->
          <label class="col-sm-2 control-label" for="codpost">CP</label>
          <div class="col-sm-4">
            <input type="text" <%if(cp>0) out.println("value=\"" + cp + "");%> placeholder="Código Postal"
                   id="codpost" class="form-control" name="codigopostal">
          </div>
        </div>


        <!-- Pais-->
        <div class="form-group">
          <label class="col-sm-2 control-label" for="pais">País</label>
          <div class="col-sm-10">
            <select name="pais" class="form-control" id="pais">
              <% List<PaisVO> paises = PaisDAO.
                      obtenerTodoLosPaises(GestorDeConexionesBD.getConnection());
                for (PaisVO pais1 : paises) {
                  out.println("<option " + "value=\"" + pais1.getIdPais() + "\">" +
                          pais1.getNombrePais() + "</option>");
                }%>
            </select>
          </div>
        </div>

        <!-- Botones envio cancel-->
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <div class="pull-right">
              <button type="submit" class="btn btn-primary form-control">Save</button>
            </div>
          </div>
        </div>

      </fieldset>
    </form>
  </div><!-- /.col-lg-12 -->
</div><!-- /.row -->


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
