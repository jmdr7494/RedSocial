Feature: Publicacion 


@Scenario1
Scenario: Publicacion exitosa
	Given Usuario en pagina principal
	When Publicacion correcta
	Then Mensaje de exito a la publicacion
