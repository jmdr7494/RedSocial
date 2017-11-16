<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IntraVita - Amigos</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="shortcut icon" href="https://thumb.ibb.co/h7RMiR/Intra_Vita_Favicon.png" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">

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
		background-color: #FAFAFA;
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
<body style="background-color: #FAFAFA;">

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
						<strong>
							<span class="glyphicon glyphicon-log-out"></span>Salir
						</strong>
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
	            En la columna de la izquierda debe introducir el nombre del amigo que quiera buscar y 
	            a continuación haga click en buscar. Si existe algún usuario con ese nombre, le 
	            aparecerá en la lista, de forma que tendrá que agregarlo como amigo en caso de que no 
	            lo tengas ya.
	            <br><br>
	            En la columna de la derecha, nos aparecerán las peticiones de amistad (en caso de que 
	            tengamos alguna), la cual la podremos aceptar o rechazar. Si no nos deja mandar la 
	            petición es porque ya sois amigos o porque alguno de los dos ya ha mandado la petición
	            al otro. 
	          </div>
	        </div>
	      </div>
	  </div>
	

		
		<div class="row">
			<div class="col-md-1 col-md-offset-10">
				<br>
				<form action="irBienvenido" method="GET">
					<button class="btn btn-danger btn-block btn-md login" type="submit">
						<strong><span class="glyphicon glyphicon-menu-left"></span>Volver</strong>
					</button>
				</form>	
						
				<br/>
			</div>
		</div>
			
		<div class="row">
			<div class="col-md-5 col-md-offset-1">
				<div class="panel panel-default">
					 <div class="panel-body">
					 
					    <form action="buscarAmigos" method="post" id="formlogin">
					    	<div class="row">
						 		<div class="col-md-12">
						 			<label for="usr" style="color:black; font-size:15px;">Buscar amigos:</label>
									<input name="txtUsuarioNombre" autofocus type="text" class="form-control" id="usr" placeholder="usuario" onFocus="if(this.value!='')this.value=''">
								</div>  
							</div>
							
							<div class="row">
								<div class="col-md-2 col-md-offset-10">
									<br/>
									<button class="btn btn-primary btn-block" type="submit">
										<strong><span class="glyphicon glyphicon-search"></span>&nbsp;Buscar</strong>
									</button>
									<br/>
								</div>	  
							</div>
						</form>	
						
						<br>
					
					
						<div class="row">
							<div class="col-md-12">
								<span><em>${amigos}</em></span>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-12">
								<span><em>${alerta}</em></span>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		
				
			<div class="col-md-5">
				<div class="panel panel-default">
					 <div class="panel-body">
						<div class="col-md-12">
							<form action="mostrarNotificaciones" method="get">
								<label for="usr" style="color:black; font-size:15px;">Peticiones de amistad:</label>
								<button class="btn btn-primary btn-block login" type="submit">
									<strong><span class="glyphicon glyphicon-bell"></span>&nbsp;Peticiones Pendientes</strong>
								</button>
							</form>
						</div>
					
						<div class="col-md-12">
							<span><em>${notificaciones}</em></span>
						</div>
					</div>
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