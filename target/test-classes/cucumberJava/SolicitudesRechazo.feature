Feature: Rechazar solicitudes de amistad de otro usuario


@Scenario1
Scenario: Rechazar solicitud valido
	Given Usuario conectado para rechazar
	When Rechazar solicitud de alguien que le ha mandado
	Then Rechazar peticion de amistad
	
@Scenario2
Scenario: Rechazar solicitud invalido
	Given Usuario conectado para rechazar
	When Rechazar solicitud de alguien que no le ha mandado
	Then Mensaje de error al rechazo de la peticion