<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- JSP para la creacion de una publicacion en nuestra red social. --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear Publicación</title>
</head>
<body>
<p>Estamos en la ventana de crear publicaciones</p>
<form action="crearPublicacion" method="POST"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<label for="usr">Usuario:</label>
				<p>El nombre es: ${usuario}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<label for="email">Texto:</label>
				<input type="text" class="form-control" id="texto" name="txtIntroducirTexto" placeholder="Introduzca texto"/>
			</div>
		</div>
				
		<br>
		<br>
		
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<button type="submit" class="btn btn-info btn-block" value="CrearPublicacion">Crear Publicacion</button>	
			</div>
		</div>		
	</form>

</body>
</html>