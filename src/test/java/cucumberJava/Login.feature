Feature: Logeo al sistema


@Scenario1
Scenario: Login exitoso con credenciales validas
	Given Usuario en pagina de login
	When Credenciales correctas
	Then Mensaje de bienvenida al login

@Scenario2
Scenario: Login fallido por nombre invalido
	Given Usuario en pagina de login
	When Nombre de usuario incorrecto
	Then Mensaje de error al login
	
@Scenario3
Scenario: Login fallido por passoword invalido
	Given Usuario en pagina de login
	When Password incorrecta
	Then Mensaje de error al login