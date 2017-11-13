<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recuperar Credenciales - IntraVita</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link REL=StyleSheet HREF="css/login.css" TYPE="text/css" MEDIA=screen>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style>
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
				<button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#miModal" style="border-width: 10px;"><strong>&nbsp;?&nbsp;</strong></button>
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
	            Si has olvidado tus credenciales, tendr&aacute;s que introducir tu nombre de usuario y la respuesta clave
	            a la pregunta que rellenaste en el registro. A continuaci&oacute;n, le llegar&aacute; un correo a su direcci&oacute;n
	            con la nueva clave.
	          </div>
	        </div>
	      </div>
	  </div>
	

	<div class="row">
		<div class="col-md-1 col-md-offset-10">
			<br>
			<form action="irLogin" method="GET">
				<button class="btn btn-danger btn-block btn-md login" type="submit">
						<strong><span class="glyphicon glyphicon-menu-left"></span>&nbsp;Volver</strong>
				</button>
			</form>	
			<br/>
		</div>
	</div>
	</div>

	<form action="recuperarCredenciales" method="POST">
		<div class="row">
		 	<div class="col-md-6 col-md-offset-3">
		 		<label for="usr" style="color:white; font-size:15px;">Usuario:</label>
				<input name="txtUsuarioNombre" type="text" class="form-control" id="usr" placeholder="usuario">
				<br>
			</div>  
		</div>
		
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<label for="usr" style="color:white; font-size:15px;">¿Qui&eacute;n fue tu mejor amigo/a de la infancia?</label>
				<input name="txtRespuesta" type="text" class="form-control" id="usr" placeholder="respuesta">
				<br>
			</div>
		</div>	
		<div class="row">
			<div class="col-md-2 col-md-offset-7">
				<button class="boton login" type="submit" value="recuperar" title="Recuperar Contrase&ntilde;a mediante correo">
					<span class="glyphicon glyphicon-send"></span> &nbsp;Recuperar Constrase&ntilde;a
				</button>
			</div>	  
		
		
			<div class="col-md-3">
				<br/>
				<spam><em>${alerta}</em></spam>
			</div>
		</div>
	<br>
	</form>
	



	
	<div class="col-md-12" style="position: fixed; bottom: 0;">
		<div class="panel-footer">
			&copy; Copyright 2017 IntraVita. Todos los derechos reservados.
		</div>
	</div>
	
</body>
</html>