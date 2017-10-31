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

</style>

</head>
<body style="background-color: #FAFAFA;">
 	<jsp:useBean id="administradorConectado" scope="session" class="com.intravita.proyectointranet.modelo.Administrador"></jsp:useBean>
	
		
		<div class="row bg-primary">
			<div class="col-md-5 col-md-offset-1">
				<strong></strong><h1 style="color: white; font-family: Verdana; font-size:60px;"><em><strong>  <img src="http://www.emprender-facil.com/wp-content/uploads/2014/09/La-red-social-LinkedIn-para-tu-negocio-texto1.png" style="width:64px; height:64px;" class="img-circle"> IntraVita</strong></em></h1></strong>
			</div>
			
			<div class="col-md-2 col-md-offset-4">
					<button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#miModal" style="border-width: 10px;"><strong>?</strong></button>
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
						Ayuda de la página del administrador
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
			<div class="col-md-1 col-md-offset-4">
				<br>
				<form action="logout" method="GET">
					<button class="btn btn-danger btn-block btn-md login" type="submit"><strong>Salir</strong></button>
				</form>	
				
				<br/>
				
				<div class="btn-group">
					<button type="button" class="btn btn-info btn-block btn-md"><strong>Ajustes</strong></button>
					<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><form action="changeToUser" method="post">
								<button class="btn btn-primary btn-block btn-md login" type="submit"><strong>Cambiar Rol</strong></button>
							</form>
					</ul>
				</div>
				
			</div>
		</div>


	
	<form action="promover" method="post">
	   <div class="row">
	 		<div class="col-md-8 col-md-offset-2 ">
	 			<label for="usr">Usuario:</label>
				<input name="txtNombre" type="text" class="form-control" id="usr" placeholder="usuario">
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
	
	
	<div class="panel-footer">
		<h5>© Copyright 2017 IntraVita. Todos los derechos reservados.</h5>
	</div>
	
</body>
</html>