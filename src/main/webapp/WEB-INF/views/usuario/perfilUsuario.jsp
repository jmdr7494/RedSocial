<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- JSP para que el usuario pueda modificar su perfil --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mi perfil</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		
<style type="text/css">
	
	body {
		background-color: #2CCBE7;
		width: 100%;
		heigth: 100%;
	}

	.boton {
		  padding: 15px 35px;
		  font-size: 18px;
		  cursor: pointer;
		  outline: none;
		  color: #fff;
		  background-color: #166EE7;
		  border: 2px;
		  border-color: #FFFFFF;
		  border-radius: 10px;
		  box-shadow: 0 3px #999;
	}
	
	.boton:hover {
		 background: rgba(41, 128, 185, 0);
		 color: #0058A3;
		 box-shadow: inset 0 0 0 3px #0058A3;
	}
	
	.boton:active {
		 background-color: #FFFFFF;
		 color: #0058A3;
		 transform: translateY(4px);
	}
	
	spam {
		padding: 20px;
		color: #DF0101;
		margin-left: 12px;
		font-weight: bold;
		font-size: 15px;
	}

</style>		
		
		
		
		
		
</head>
<body>

	<div class="row bg-primary">
		<div class="col-md-5 col-md-offset-1">
			<img src="http://i65.tinypic.com/2dvizyh.png" style="width:30%;">
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
					<div class="modal-body"><%-- Poner ayuda para modificar el perfil  By JA--%>
						Para poder modificar la contrase&ntilde;a de su perfil, introduzca una nueva contrase&ntilde;a personal
						y confirme dicha contrase&ntilde;a
					</div>
				</div>
			</div>
		</div>
	<%-- volver al muro By JA--%>
	<div class="row">
		<div class="col-md-1 col-md-offset-10">
			<br>
			<form action="irBienvenido" method="GET">
				<button class="btn btn-danger btn-block btn-md login" type="submit" title="atr&aacute;s">
					<strong><span class="glyphicon glyphicon-menu-left"></span>&nbsp;Volver</strong>
				</button>
			</form>		
			<br/>
		</div>
	</div>

	<%-- action modificar perfil By JA--%>
	<form action="modificarPerfilUsuario" method="POST">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<label for="usr" style="color:white; font-size:15px;">Usuario:</label>
				<input type="text" class="form-control" id="usr" name="txtUsuarioNombre" disabled value=<%=request.getAttribute("usuarioNombre")%>>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<label for="email" style="color:white; font-size:15px;">Email:</label>
				<input type="text" class="form-control" id="email" name="txtEmail" disabled value=<%=request.getAttribute("usuarioEmail")%>>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<label for="pwd" style="color:white; font-size:15px">Modificar contrase&ntilde;a:</label>
		  		<input type="password" class="form-control" id="pwd" name="txtPwdNueva1" placeholder="password">
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
			  	<label for="pwd1" style="color:white; font-size:15px">Confirmar Contrase&ntilde;a:</label>
			  	<input type="password" class="form-control" id="pwd1" name="txtPwdNueva2" placeholder="confirmaci&oacute;n password">	
			</div>
		</div>
		
		<br/>
		
		<div class="row">
			<div class="col-md-2 col-md-offset-5">
				<button type="submit" class="boton login" value="Modificar" title="Grabar cambios">
					<center>
						<strong><span class="glyphicon glyphicon-floppy-save"></span> &nbsp;Guardar Cambios</strong>
					</center>
				</button>	
			</div>
		</div>		
	</form>

	<br>
	
	<div class="row">
		<div class="col-md-12 col-md-offset-4">
			<t><spam><em>${alerta}</em></spam></t>
		</div>
	</div>

	
	<div class="col-md-12 " style="position: fixed; bottom: 0;">
		<div class="panel-footer">
			<h5>© Copyright 2017 IntraVita. Todos los derechos reservados.</h5>
		</div>
	</div>

</body>

</html>