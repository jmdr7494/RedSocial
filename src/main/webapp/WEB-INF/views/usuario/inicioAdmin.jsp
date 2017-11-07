<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel administrador</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link REL=StyleSheet HREF="css/login.css" TYPE="text/css" MEDIA=screen>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



<style>
p {
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
#content {
   padding-bottom: 50px; /* Mis altura que el footer */
} 
#footer {
   position: relative;
   margin-top: -50px; /* Misma altura que el footer en negativo */
   height: 50px; /* Altura del footer */
   clear: both;
}

</style>

</head>
<body style="background-color: #FAFAFA;">
 	<jsp:useBean id="administradorConectado" scope="session" class="com.intravita.proyectointranet.modelo.Administrador"></jsp:useBean>
	
		
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
						Introducir el nombre del usuario sobre el que queramos realizar una acci�n en el cuadro de texto
						correspondiente para ello. Si quiere darle privilegios de administrador, tendr� que hacer click 
						sobre el bot�n de Promover. Si lo que desea es quitarlo de la colecci�n de administradores, tendr�
						que hacer click sobre el bot�n Degradar. Y si lo que quiere es eliminar a un usuario o administrador
						del sistema, tendr� que hacer click sobre el bot�n Borrar.<br><br>
						Para consultar los usuarios y administradores del sistema tendr� que hacer click sobre el bot�n 
						Actualizar Lista, de modo que aparecer�n por separado la lista de usuarios y la de administradores.
						<br><br>
						Para cambiar al rol de usuario, dentro del bot�n de ajustes tiene la opci�n de Cambiar Rol, la cual
						le cambiar� el rol a usuario.
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="row">
			<div class="col-md-5 col-md-offset-1">
				<h3>
					<jsp:getProperty name="administradorConectado" property="nombre"/>
				</h3>
			</div>
			
			<div class="col-md-4">

			</div>
			<div class="col-md-1">
				<br>
				<form action="changeToUser" method="post"><button type="submit" class="btn btn-success"><strong><span class="glyphicon glyphicon-user"></span> Cambiar Rol </strong></button></form>
			</div>
			<%--
			<div class="col-md-1">
				<br>
				<form action="logout" method="GET"> <button class="btn btn-danger" type="submit">Salir</button></form>	
			</div>
			 --%>
		</div>

	
	<form action="promover" method="post">
	   <div class="row">
	 		<div class="col-md-8 col-md-offset-2 ">
	 			<label for="usr">Usuario:</label>
				<input name="txtNombre" autofocus type="text" class="form-control" id="usr" placeholder="usuario">
				<br>
			</div>  
		</div>
		<div class="row">
			<div class="col-md-2 col-md-offset-2">
				<button class="btn btn-success btn-block login" type="submit"><strong>Promover</strong></button>
			</div>	  

			<div class="col-md-2 col-md-offset-1">
				<button class="btn btn-danger btn-block login" name="degradar" type="submit" formaction="degradar"><strong>Degradar</strong></button>
			</div>	  

			<div class="col-md-2 col-md-offset-1">
				<button class="btn btn-danger btn-block login" type="submit" formaction="borrar"><strong>Borrar</strong></button>
			</div>	  
		</div>
	</form>	
	<br>
	<div class="row">
		<div class="col-md-4 col-md-offset-2">
			<p><em>${alerta}</em></p>
		</div>
	</div>
	<br>
    <form action="listarUsuario" method="post">
		<div class="row">
	 		<div class="col-md-8 col-md-offset-2 ">
				<div class="panel panel-default">
					<div class="panel-body">
						<button class="btn btn-info btn-block login" type="submit"><strong>Actualizar Lista</strong></button>
						<br/>
						<div class="row">
							<div class="col-md-6">
								<div class="col-md-12">
									<div class="panel panel-default">
										<div class="panel-body">
											<h3><strong><em>Usuarios</em></strong></h3>
											${usuarios}
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="col-md-12">
									<div class="panel panel-default">
										<div class="panel-body">
											<h3><strong><em>Administradores</em></strong></h3>
											${administradores}
										</div>
									</div>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>	  
		</div>
	</form>
	
	
	<div class="col-md-12" style="position: fixed; bottom: 0;">
		<div class="panel-footer">
			� Copyright 2017 IntraVita. Todos los derechos reservados.
		</div>
	</div>
	
</body>
</html>