<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- JSP para el borrado de una cuenta --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Borrar cuenta</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="   crossorigin="anonymous"></script>

<link rel="shortcut icon" href="https://thumb.ibb.co/h7RMiR/Intra_Vita_Favicon.png" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">


<style type="text/css">
	
	body {
		background-color: #FAFAFA;
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
			
			<div class="col-md-1 col-md-offset-3">
					<button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#miModal" style="border-width: 10px;"><strong>?</strong></button>
			</div>
			
			<div class="col-md-1 fixed-top" style="position: relative; top: 8px">
				<form action="logout" method="GET"> 
					<button class="btn btn-danger" type="submit">
						<strong>
							<span class="glyphicon glyphicon-log-out"></span>
								Salir
							</strong>
					</button>
				</form>
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
						Para borrar su cuenta debe introducir su contrase&ntilde;a y la respuesta a la pregunta
						que se le formul&oacute; durante su registro. A continuaci&oacute;n debe hacer click en el bot&oacute;n Borrar.
						<br>
						Deber&aacute; confirmar que est&aacute; seguro que desea borrar su cuenta
					</div>
				</div>
			</div>
		</div>
	

	<div class="row">
		<div class="col-md-1 col-md-offset-9">
			<br>
			<form action="irBienvenido" method="GET">
				<button class="btn btn-danger btn-block btn-md login" type="submit">
					<strong><span class="glyphicon glyphicon-menu-left"></span>&nbsp;Volver</strong>
				</button>
			</form>	
					
		<br/>
		</div>
	</div>

	
		<div class="row">
		 	<div class="col-md-8 col-md-offset-2">
		 		<label for="usr">Password:</label>
				<input " name="txtUsuarioPwd" autofocus type="password" class="form-control" id="inputPwd" placeholder="password">
				<br>
			</div>  
		</div>
		
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<label for="usr">¿Cu&aacute;l es tu mejor amigo/a de la infancia?</label>
				<input name="txtRespuesta" type="text" class="form-control" id="inputRes" placeholder="respuesta">
				<br>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-1 col-md-offset-9">
				<button id="borrarCuenta" class="btn btn-danger btn-block" type="submit" data-toggle="modal" data-target="#miModales" value="borrar">
					<strong><span class="glyphicon glyphicon-trash"></span>&nbsp;Borrar</strong>
				</button>
			</div>	  
		</div>
	<br>

	
	
	<div class="modal fade" id="miModales" tabindex="-1" role="dialog" aria-labelledby="myModalLabeles" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabeles">Borrar cuenta</h4>
					</div>
					<div class="modal-body">
						¿Est&aacute;s seguro de que deseas eliminar tu cuenta?
						<br><br>
						<form action="borrarCuenta" method="post">
							<input name="txtUsuarioPwd" autofocus type="hidden" class="form-control" id="modalPwd" placeholder="password">
							<input name="txtRespuesta" type="hidden" class="form-control" id="modalRes" placeholder="respuesta"> 
							<button class="btn btn-success btn-md btn-block" type="submit">
								<strong><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;Si</strong>
							</button>
							<br>
						</form>
						<form action="irBorradoCuenta" method="get">
							<button class="btn btn-danger btn-md btn-block" type="submit">
								<strong><span class="glyphicon glyphicon-remove-circle"></span>&nbsp;No</strong>
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	
		<div class="row">
			<div class="col-md-3 col-md-offset-8">
				<t><span><em>${alerta}</em></span></t>
			</div>
		</div>
		
	
	
	<div class="col-md-12" style="position: fixed; bottom: 0;">
		<div class="panel-footer">
			&copy; Copyright 2017 IntraVita. Todos los derechos reservados.
		</div>
	</div>
	
	<!-- Script para obtener los datos de los input en el modal de confirmación de borrar cuenta -->
	<script>
	$('#borrarCuenta').click(function(){
		var pass=$('#inputPwd').val();
		var res=$('#inputRes').val();
		$('#modalPwd').val(pass);
		$('#modalRes').val(res);
		
	})
	</script>
</body>
</html>