Feature: Enviar solicitudes de amistad entre usuarios


@Scenario1
Scenario: Envio de solicitud valido
	Given Usuario conectado
	When Selecciona otro usuario sin ser amigos ni tener solicitudes entre ellos
	Then Envio solicitud al segundo
	
@Scenario2
Scenario: Envio de solicitud invalido
	Given: Usuario conectado
	When Selecciona a otro usuario siendo su amigo
	Then No envio de solicitud
	
@Scenario3
Scenario: Envio de solicitud invalido
	Given: Usuario conectado
	When Selecciona a otro usuario teniendo solicitudes pendientes entre ellos
	Then Solicitud ya enviada