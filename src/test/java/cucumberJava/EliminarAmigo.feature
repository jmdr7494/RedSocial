Feature: Eliminar de la lista de amigos a otro usuario


@Scenario1
Scenario: Revocacion de amistad valido
	Given Usuario conectado para borrar a otro usuario
	When Eliminar un usuario que es tu amigo
	Then Borrar amigo de ambos usuarios
	
@Scenario2
Scenario: Revocacion de amistad invalido
	Given Usuario conectado para borrar a otro usuario
	When Eliminar un usuario que no es tu amigo
	Then Mensaje de error a la revocacion de usuario