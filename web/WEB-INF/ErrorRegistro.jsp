<!DOCTYPE HTML>
<!--
Eventually by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html lang="es">
<head>
    <title>Jaus</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
    <!--[if lte IE 8]>
    <script src="assets/js/ie/html5shiv.js"></script><![endif]-->
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ie8.css"/><![endif]-->
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ie9.css"/><![endif]-->

</head>
<body>
<button type="button" class="regBut2" data-toggle="modal" data-target="#myModalIS">
    Iniciar Sesión
</button>

<button type="button" class="regBut" data-toggle="modal" data-target="#myModal">
    Registrarse
</button>
<!-- Header -->
<header id="header">
    <h1 align="center">Jaus</h1>
    <p>Tu hogar al alcance de tu mano. Tu hogar a dos clics.</p>
</header>

<!-- Signup Form -->
<form id="signup-form" method="post" action="<%=request.getContextPath()%>/resultadoBusqueda.jsp">
    <fieldset>
        <legend style="color:white">Me interesa:</legend>
        <input type="radio" name="Selector" id="idComprar" value="seVenden"/><label for="idComprar">Comprar</label>
        <input type="radio" name="Selector" id="idAlquilar" value="seAlquilan"/><label for="idAlquilar">Alquilar</label>
    </fieldset>
    <input type="text" name="Buscar" placeholder="Ciudad, calle, municipio..." />
    <input type="submit" value="&#xf002;" />
</form>
<!--<form action="A:\Mis Documentos\Escritorio\Sync\Universidad\SIstemas de Información\Website\Página registro\HTML1.html">
        <input type="submit" id="regBut" value="Registrarse" />
</form>-->

<!-- Modal -->
<div class="modal fade"  id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="background-color:black">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Formulario de Registro</h4>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Registro.do" method="POST">
                    <%
                        if(null!=request.getAttribute("CampoVacio")){
                            out.println(request.getAttribute("CampoVacio"));
                        }
                        if(null!=request.getAttribute("usuarioExiste"))
                        {
                            out.println(request.getAttribute("usuarioExiste"));
                        }
                    %>
                    <label for="IDUsuarioR">Nombre de usuario:</label><input type="text" name="user" id="IDUsuarioR"/><br/>
                    <label for="IDNombre">Nombre:</label><input type="text" name="nombre" id="IDNombre"/><br/>
                    <label for="IDApellido">Apellido:</label><input type="text" name="apellido" id="IDApellido"/><br/>

                    <label for="IDmail">E-mail:</label><input type="email" name="Mail" id="IDMail"/><br/>
                    <%
                        if(null!=request.getAttribute("mismatchM"))
                        {
                            out.println(request.getAttribute("mismatchM"));
                        }
                    %>
                    <label for="IDREmail">Repite E-mail:</label><input type="email" name="REMail" id="IDREMail"/><br/>

                    <label for="IDPass">Contraseña:</label><input type="password" name="Pass" id="IDPass"/><br/>
                    <%
                        if(null!=request.getAttribute("mismatchP"))
                        {
                            out.println(request.getAttribute("mismatchP"));
                        }
                    %>
                    <label for="IDREPass">Repite contraseña:</label><input type="password" name="REPass" id="IDREPass"/><br/>

                    <p>Nota: todos los campos son obligatorios</p>
                    <div class="modal-footer">
                        <button type="button" class="cancelReg" data-dismiss="modal">Cancelar</button>
                        <input type="submit" class="form-control acceptReg" ></input>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<!-- Modal iniciar sesion -->
<div class="modal fade"  id="myModalIS" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="background-color:black">
            <div class="modal-header">
                <h4 class="modal-title" id="ModalIS">Iniciar sesión:</h4>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/Login.do" method="POST">
                    <label for="IDUsuario">Usuario</label><input type="text" name="user" id="IDUsuario"/><br/>
                    <label for="IDPassword">Contraseña</label><input type="password" name="password" id="IDPassword"/><br/>
                    <div class="modal-footer">
                        <button type="button" class="cancelReg" data-dismiss="modal">Cancelar</button>
                        <input type="button" class="form-control acceptReg"></input>
                    </div>
                </form>

            </div>

        </div>
    </div>
</div>




<!-- Footer -->
<footer id="footer">
    <!--<ul class="icons">
        <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
        <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
        <li><a href="#" class="icon fa-github"><span class="label">GitHub</span></a></li>
        <li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
    </ul>-->
    <ul class="copyright">
        <li>&copy; Untitled.</li><li>Credits: <a href="http://html5up.net">HTML5 UP</a></li>
    </ul>
</footer>

<!-- Scripts -->
<!--[if lte IE 8]>
<script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="assets/js/main.js"></script>

</body>
</html>