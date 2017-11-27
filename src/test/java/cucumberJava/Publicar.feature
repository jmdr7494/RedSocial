Feature: Publicacion 


@Scenario1
Scenario: Publicacion exitosa
	Given Usuario en pagina principal
	When Publicacion correcta
	Then Mensaje de exito a la publicacion

# nuevo test incluido en la fase de mantenimiento
@Scenario2
Scenario: Publicacion fallida
	Given Usuario en pagina principal
	When Publicacion incorrecta
	Then Mensaje de fallo a la publicacion