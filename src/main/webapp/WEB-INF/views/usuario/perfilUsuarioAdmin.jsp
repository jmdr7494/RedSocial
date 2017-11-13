<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Perfil Usuario</title>
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
						Este es el perfil del usuario seleccionado.
						<br><br>
						Puedes ver todos sus datos y modificarle las credenciales de login.
						<br><br>
						Puedes ver todas sus publicaciones y borrar las necesarias.
					</div>
				</div>
			</div>
		</div>
	
	
	<div class="row">
		<div class="col-md-1 col-md-offset-10">
			<br>
			<form action="irAdmin" method="GET">
				<button class="btn btn-danger btn-block btn-md login" type="submit">Volver</button>
			</form>	
					
		<br/>
		</div>
	</div>
	
	
	<div class="row">
		<div class="col-md-5 col-md-offset-1">
			<div class="panel panel-default">
				 <div class="panel-body">
					<form action="mostrarPerfilAdmin" method="get">
						<button class="btn btn-info btn-block login" type="submit">Mostrar Datos</button>
					</form>
				</div>
				${perfil}
			</div>	
		</div>
	
		<div class="col-md-5">
			<div class="panel panel-default">
				 <div class="panel-body">
					<form action="listarPublicacionUsuario" method="post">
						<button class="btn btn-info btn-block login" type="submit">Mostrar Publicaciones</button>
					</form>
				</div>
				${publicaciones}
			</div>	
		</div>
	</div>
	
	
	<br/>
	<div class="row">
		<div class="col-md-12">
			<spam><em>${alerta}</em></spam>
		</div>
	</div>
	
	
	
	<div class="col-md-12" style="position: fixed; bottom: 0;">
		<div class="panel-footer">
			© Copyright 2017 IntraVita. Todos los derechos reservados.
		</div>
	</div>

	

</body>
</html>