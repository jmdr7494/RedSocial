function cargarArchivo(elemento){
	var file=elemento.files[0];
	var objHidden=document.formulario.nombre;
	objHidden.value=file.name;
	document.formulario.target = "cosa";
	document.formulario.action = "procesoArchivo";
	document.formulario.submit();
}