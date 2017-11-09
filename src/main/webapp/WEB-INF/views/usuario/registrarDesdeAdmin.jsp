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
	
	
	body {
		background-color: #2CC8E7;
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


 	<jsp:useBean id="administradorConectado" scope="session" class="com.intravita.proyectointranet.modelo.Administrador"></jsp:useBean>
	
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
						<center>
							<strong><span class="glyphicon glyphicon-log-out"></span>Salir</strong>
						</center>
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
						Para poder usar IntraVita tendr&aacute; que rellenar el cuestionario con sus datos personales, 
						respetando las pol&iacute;ticas de seguridad y estructuras que son necesarias.<br><br>
						- Para el nombre de usuario: nombre.apellido<br>
						- Para el mail: ser&aacute; el corporativo de la empresa, con la extensi&oacute;n @alu.uclm.es<br>
						- Para la contrase&ntilde;a: longitud m&iacute;nima de 8 caracteres, utilizando letras y n&uacute;meros<br>
						- Para la confirmaci&oacute;n: tiene que ser igual que la contrase&ntilde;a anterior<br>
						- Para la respuesta: utilice una respuesta de la que se acordar&aacute; en el futuro<br>
						- Foto de perfil: introduzca una foto suya preferentemente con fondo blanco
						
					</div>
				</div>
			</div>
		</div>

	<div class="row">
		<div class="col-md-1 col-md-offset-10">
			<br>
			<form action="irAdmin" method="GET">
				<button class="btn btn-danger btn-block btn-md login" type="submit">
						<strong><span class="glyphicon glyphicon-menu-left"></span>Volver</strong>
				</button>
			</form>	
			<br/>
		</div>
	</div>


	<form action="registrar" method="POST"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<label for="usr" style="color:white; font-size:15px;">Usuario:</label>
				<input type="text" class="form-control" id="usr" name="txtUsuarioNombre" autofocus placeholder="usuario" onFocus="if(this.value!='')this.value=''"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<label for="email" style="color:white; font-size:15px">Email:</label>
				<input type="text" class="form-control" id="email" name="txtEmail" placeholder="@alu.uclm.es""/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<label for="pwd" style="color:white; font-size:15px">Contrase&ntilde;a:</label>
		  		<input type="password" class="form-control" id="pwd" name="txtUsuarioClave" placeholder="password">
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
			  	<label for="pwd1" style="color:white; font-size:15px">Confirmar Contrase&ntilde;a:</label>
			  	<input type="password" class="form-control" id="pwd1" name="txtUsuarioClave1" placeholder="confirmacion password">	
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<label for="usr" style="color:white; font-size:15px">¿Cu&aacute;l es tu mejor amigo/a de la infancia?</label>
				<input name="txtRespuesta" type="text" class="form-control" id="usr" placeholder="respuesta" onFocus="if(this.value!='')this.value=''">
			</div>
		</div>
		

		<br>
		
		<div class="row">
			<div class="col-md-2 col-md-offset-5">
				<center>
					<button type="submit" class="boton login" value="Registrar">Registrar</button>	
				</center>
			</div>
			<div class="col-md-3">
				<br/>
				<t><spam><em>${alerta}</em></spam></t>
			</div>
		</div>		
	</form>

</body>
</html>