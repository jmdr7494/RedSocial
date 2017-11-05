Feature: Eliminar cuenta BD

@Scenario1
Scenario: Eliminacion correcta de la cuenta de un usuario
	Given Usuario conectado quiere borrar su cuenta
	When Datos correctos
	Then Borrar cuenta y cambiar publicaciones de propietario
