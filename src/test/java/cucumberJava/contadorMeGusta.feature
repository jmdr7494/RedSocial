Feature: Me gusta

@Scenario1
Scenario: Activacion correcta del boton me gusta
	Given Usuario conectado quiere dar me gusta a una publicacion
	When Activacion correcta me gusta
	Then Publicacion pasa a tener un me gusta mas


@Scenario2
Scenario: Desactivacion correcta del boton me gusta
	Given usuario conectado quiere eliminar me gusta de una publicacion
	When Desactivacion correcta me gusta
	Then Publicacion pasa a tener un me gusta menos
