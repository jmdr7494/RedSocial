<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IntraVita - Login</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style>
.btn {
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

.btn:hover {
 background: rgba(41, 128, 185, 0);
 color: #0058A3;
 box-shadow: inset 0 0 0 3px #0058A3;
}

.btn:active {
	 background-color: #FFFFFF;
	 color: #0058A3;
	 transform: translateY(4px);
}

span {
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
   margin-top: -50px; /* Misma altura que el footer en negativo */
   height: 50px; /* Altura del footer */
   clear: both;
}

body{
	background-color: #2CC8E7;
}
</style>


</head>



<body>


<div class="container">

   <center>
   <br/>
    <img src="http://i65.tinypic.com/2dvizyh.png" style="width:65%;">
   <br/>
   </center>

  
    
    <form action="login" method="post" id="formlogin">
     <div class="row">
    <div class="col-md-8 col-md-offset-2">
     <br/>
     <label for="usr" style="color:white; font-size:15px;">Usuario:</label>
    <input name="txtUsuarioNombre" autofocus type="text" class="form-control" id="usr" placeholder="usuario" onFocus="if(this.value!='')this.value=''">
   </div>  
  </div>
  <div class="row">
   <div class="col-md-8 col-md-offset-2">
    <label for="pwd" style="color:white; font-size:15px;">Contrase&ntilde;a:</label>
    <input type="password" name="txtUsuarioClave" class="form-control" id="pwd" placeholder="password">
    <a href="irRecuperarCredenciales" style="color: white"><em>¿Olvidó su contrase&ntilde;a?</em></a>
   </div>
 </div>   
 </div>
 
 <div class="row">
 	<div class="col-md-7 col-md-offset-5">
		<br/>
			<button class="btn login" type="submit">
				Iniciar Sesi&oacute;n
			</button> 
   			<span><em>${alerta}</em></span>
   		<br/>
  	</div>   
 </div>
 </form> 
 
 <br>
 <div class="row">
  <div class="col-md-1 col-md-offset-5">
	<form action="irRegistrar" method="get">
   		<button class="btn login" type="submit">
   			&nbsp;&nbsp;   Reg&iacute;strate   &nbsp;&nbsp;
   		</button>
	</form>
  </div>
 </div>
 <br>
 
</div>

 <div class="col-md-12" style="position: fixed; bottom: 0;">
  <div class="panel-footer">
   &copy; Copyright 2017 IntraVita. Todos los derechos reservados.
  </div>
 </div>


</body>
</html>