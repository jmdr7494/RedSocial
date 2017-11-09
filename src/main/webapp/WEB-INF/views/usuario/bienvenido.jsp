<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="estilo.css" />
<style>	
	spam {
    padding: 20px;
    color: #DF0101;
    margin-left: 12px;
	font-weight: bold;
	font-size: 15px;
}



* {
   margin:0;
   padding:0
}
html,
body,
#wrap {
   height: 100%;
}
body > #wrap {
   height: auto;
   min-height: 100%;

}

body{
   background-color: #FAFAFA;
}
#content {
   padding-bottom: 50px; /* Mis altura que el footer */
} 
#footer {
   position: relative;
   margin-top: -50px; /* Misma altura que el footer en negativo */
   height: 50px; /* Altura del footer */
   clear: both;
}

.boton{
        font-size:16px;
        font-weight:bold;
        color:white;
        border:0px;
        background:#337ab7;
        width:100%;
        height:100%;
        text-align: left;
      }

</style>
	
	
</head>
<body>
 	<jsp:useBean id="usuarioConectado" scope="session" class="com.intravita.proyectointranet.modelo.Usuario"></jsp:useBean>
 
 	<!--  
	 	<p>El nombre es: <jsp:getProperty name="usuarioConectado" property="nombre"/></p>
	 	<p>El email es: <jsp:getProperty name="usuarioConectado" property="email"/></p>
	-->
		<div class="row bg-primary">
			<div class="col-md-5 col-md-offset-1">
				<img src="http://i65.tinypic.com/2dvizyh.png" style="width:30%;">
			</div>
			
			<div class="col-md-1 col-md-offset-3">
					<button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#miModal" style="border-width: 10px;"><strong>?</strong></button>
			</div>
			
			<div class="col-md-1 fixed-top" style="position: relative; top: 8px">
				<form action="logout" method="GET"> <button class="btn btn-danger" type="submit"><strong><span class="glyphicon glyphicon-log-out"></span>Salir</strong></button></form>
			</div>
		</div>
	
	<div class="modal fade" id="miModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Ayuda</h4>
					</div>
					<div class="modal-body">
						Para realizar una publicación tendrá que escribir en el cuadro de texto "Realizar publicación" su 
						correspondiente publicación y a continuación hacer click sobre el botón Enviar para que dicha 
						publicación sea pública o en Borrador para que sea privada.
						<br><br>
						Para que se muestren todas las publicaciones tenemos que hacer click en el botón de Mostrar Publicaciones, 
						el cual nos mostrará todas las publicaciones que tengamos en nuestro tablón. Tenemos los botones de Editar y 
						Eliminar que harán las funciones correspondientes de editar la publicación y de eliminarla.
						<br><br>
						Para cambiar al rol de administrador, dentro del botón de ajustes tiene la opción de Cambiar Rol, la cual
						le cambiará el rol a administrador. Dentro de este mismo botón (Ajustes) tiene la opción de borrar su propia
						cuenta.
					</div>
				</div>
			</div>
		</div>
	
	
	<div class="row">
		<div class="col-md-1 col-md-offset-1">
			<h3>
			<jsp:getProperty name="usuarioConectado" property="nombre"/>
				
			</h3>

		<!--  <div class="col-md-2 col-md-offset-1">
 			<h3><em><strong>
 				<span class="glyphicon glyphicon-user"></span>&nbsp;<jsp:getProperty name="usuarioConectado" property="nombre"/>
 			</strong></em></h3>-->

		</div>
		<div class="col-md-1">
				<img src=<jsp:getProperty name="usuarioConectado" property="imagen"/>>
				<img src="data:image/gif;base64,${imagen}">
				<img src=>
		</div>

		<div class="col-md-1 col-md-offset-5">
  		</div>
  				
		<br/>
			
			<div class="btn-group col-md-1 col-md-offset-7">
				 <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
				    <span class="glyphicon glyphicon-cog"></span>&nbsp;<strong>&nbsp;Ajustes&nbsp;</strong><span class="caret"></span>
				  </button>
					<ul class="dropdown-menu" style="background:#337ab7;">
						<li>
							<form action="changeToAdmin" method="POST">
								<button type="submit" class="boton btn-default"><span class="glyphicon glyphicon-arrow-up"></span>&nbsp;Modo Admin.</button>
							</form>
						</li>
						<li>
							<form action="irPerfilUsuario" method="GET">
								<button type="submit" class="boton btn-default">
									<strong>
										<span class="glyphicon glyphicon-wrench"></span> Editar Perfil 
									</strong>
								</button>
							</form>						
						</li>
						<li>
							<form action="irBorradoCuenta" method="GET">
								<button type="submit" class="boton btn-default"><span class="glyphicon glyphicon-remove-circle"></span>&nbsp;Borrar Cuenta</button>
							</form>
						</li>
						<li>
							<form action="irVistaAmigos" method="GET">
								<button type="submit" class="boton btn-default"><span class="glyphicon glyphicon-user"></span>&nbsp;Amigos</button>
							</form>
						</li>
						
				</ul>
			</div>					
	</div>
	
	
	 <form action="crearPublicacion" method="post" id="formlogin">
    	<div class="row">
	 		<div class="col-md-8 col-md-offset-2">
			  <label for="comment">Realizar publicación</label>
			  <textarea name="txtIntroducirTexto" autofocus placeholder="¿Qué tal el día?" class="form-control" rows="5" id="comment"></textarea>
			</div>  
		</div>
		
		<br/>
		
		<div class="row">
			<div class="col-md-1 col-md-offset-8">
 				<button class="btn btn-primary btn-block login" formaction="crearPublicacionPrivada" type="submit" title="Publicación Privada"><strong><span class="glyphicon glyphicon-tag"></span>&nbsp;Borrador</strong></button>
 			</div>	
 			<div class="col-md-1">
 				<button class="btn btn-primary btn-block login" type="submit" title="Publicación Pública"><strong><span class="glyphicon glyphicon-share-alt"></span>&nbsp;Publicar</strong></button>
 			</div>	  
		</div>
	</form>
	
	<br/>
	<div class="row">
		<div class="col-md-3 col-md-offset-8">
			<spam><em>${alerta}</em></spam>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-default">
				 <div class="panel-body">
					<form action="listarPublicacion" method="post">
						<button class="btn btn-info btn-block login" type="submit" title="Actualizar Muro"><strong><span class="glyphicon glyphicon-list"></span>&nbsp;Mostrar Publicaciones</strong></button>
					</form>
				</div>
				${publicaciones}
			</div>	
		</div>
	</div>
	
	<div class="col-md-12" style="position: fixed; bottom: 0;">
		<div class="panel-footer">
			© Copyright 2017 IntraVita. Todos los derechos reservados.
		</div>
	</div>

	

</body>
</html>