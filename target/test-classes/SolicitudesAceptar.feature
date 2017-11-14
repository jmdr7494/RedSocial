Feature: Aceptar solicitudes de amistad de otro usuario


@Scenario1
Scenario: Aceptar solicitud valido
	Given Usuario conectado para aceptar
	When Acepta solicitud de alguien que le ha mandado
	Then Aceptar como amigo
	
@Scenario2
Scenario: Aceptar solicitud invalido
	Given Usuario conectado para aceptar
	When Acepta solicitud de alguien que no le ha mandado
	Then Mensaje de error a la aceptacion
	