Feature: Edicion de una publicacion en la BD
	
@Scenario1
Scenario: Edicion correcta de publicacion
Given El usuario quiere editar una publicacion
When Edicion correcta
Then Mensaje de exito en la edicion de la publicacion


#nuevo test incluido en la fase de mantenimiento
@Scenario2
Scenario: Edicion incorrecta de publicacion
	Given El usuario quiere editar una publicacion
	When Edicion incorrecto
	Then Mensaje de error en la edicion de la publicacion