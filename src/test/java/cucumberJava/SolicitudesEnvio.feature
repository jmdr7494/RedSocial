Feature: Enviar solicitudes de amistad a otro usuario


@Scenario1
Scenario: Envio de solicitud de amistad valido
	Given Usuario conectado para enviar solicitud
	When Selecciona otro usuario sin ser amigos ni tener solicitudes entre ellos
	Then Envio solicitud al segundo
	
@Scenario2
Scenario: Solicitud invalida porque son amigos
	Given Usuario conectado para enviar solicitud
	When Selecciona a otro usuario siendo su amigo
	Then No envio de solicitud
	
@Scenario3
Scenario: Solicitud invalida porque tienen solicitudes pendientes
	Given Usuario conectado para enviar solicitud
	When Selecciona a otro usuario teniendo solicitudes pendientes entre ellos
	Then Solicitud ya enviada