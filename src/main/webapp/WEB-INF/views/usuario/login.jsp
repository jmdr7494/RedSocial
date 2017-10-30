
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IntraVita - Login</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>





<style>
.btn {
  padding: 15px 35px;
  font-size: 18px;
  text-align: center;
  cursor: pointer;
  outline: none;
  color: #fff;
  background-color: #166EE7;
  border: none;
  border-radius: 10px;
  box-shadow: 0 3px #999;
}

.btn:hover {background-color: #21DD27}

.b:active {
  background-color: #21DD27;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}

spam {
    padding: 20px;
    color: #3629D3;
    margin-left: 12px;
	font-weight: bold;
	font-size: 20px;
}

</style>


</head>
<body style="background-color: #2CC8E7;">


<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-2">
			<strong></strong><h1 style="color: white; font-family: Verdana; font-size:60px;"><em><strong>  
				<img src="http://www.emprender-facil.com/wp-content/uploads/2014/09/La-red-social-LinkedIn-para-tu-negocio-texto1.png" style="width:64px; height:64px;" class="img-circle";> 
				IntraVita
			</strong></em></h1></strong>
		</div>
	</div>
  
    
    <form action="login" method="post" id="formlogin">
    	<div class="row">
	 		<div class="col-md-8 col-md-offset-2">
	 			<label for="usr" style="color:white; font-size:15px;">Usuario:</label>
				<input name="txtUsuarioNombre" type="text" class="form-control" id="usr" placeholder="usuario" onFocus="if(this.value!='')this.value=''">
			</div>  
		</div>
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<label for="pwd" style="color:white; font-size:15px;">Contraseña:</label>
				<input type="password" name="txtUsuarioClave" class="form-control" id="pwd" placeholder="password">
				<a href="irRecuperarCredenciales" style="color: white";><em>¿Olvidó su contraseña?</em></a>
			</div>	  
		</div>
	<div class="row">
		<div class="col-md-1 col-md-offset-5">
			<button class="btn login" type="submit">Iniciar Sesión</button>
			<br/>

		</div>	  
	</div>
	</form>	
	
	<br>
	<div class="row">
		<div class="col-md-1 col-md-offset-5">
			<form action="irRegistrar" method="get"><button class="btn login" type="submit">&nbsp;&nbsp;   Regístrate   &nbsp;&nbsp;</button></form>
		</div>
	</div>
	<br>
	
	<%--
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<form action="irRecuperarCredenciales" method="get"><button class="btn btn-info btn-block login" type="submit">¿Olvido su contraseña?</button></form>
		</div>
	</div>
	--%>
	


	<div class="row">
		<div class="col-md-12">
			<t><spam><em>${alerta}</em></spam></t>
		</div>
	</div>

	
</div>

	<div class="panel-footer">
		<h5>© Copyright 2017 IntraVita. Todos los derechos reservados.</h5>
	</div>




</body>
</html>