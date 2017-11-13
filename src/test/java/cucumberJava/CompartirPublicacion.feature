Feature: Compartir publicaciones

@Scenario1
Scenario: Compartir correctamente una publicacion
	Given Usuario conectado quiere compartir una publicacion
	When No esta compartida por el usuario
	Then Compartir publicacion
	
@Scenario1
Scenario: Compartir erroneamente una publicacion
	Given Usuario conectado quiere compartir una publicacion
	When Esta compartida por el usuario
	Then No compartir publicacion

@Scenario1
Scenario: Dejar de compartir una publicacion
	Given Usuario conectado quiere dejar de compartir una publicacion
	When Esta compartida por el usuario
	Then Dejar de compartir publicacion
	
@Scenario1
Scenario: Dejar de compartir una publicacion erroneamente
	Given Usuario conectado quiere dejar de compartir una publicacion
	When No esta compartida por el usuario
	Then No dejar de compartir publicacion