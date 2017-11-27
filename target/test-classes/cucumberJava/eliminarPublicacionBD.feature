Feature: eliminar publicacion DB

@Scenario1
Scenario: Eliminacion correcta de publicacion
	Given Usuario quiere eliminar una publiacion
	When Eliminacion correcta
	Then Mensaje de exito en la eliminacion de publicacion


#nuevo test incluido en la fase de mantenimiento
@Scenario2
Scenario: Eliminacion incorrecta de publicacion
	Given Usuario quiere eliminar una publiacion
	When Eliminacion incorrecta
	Then Mensaje de fallo en la eliminacion de publicacion