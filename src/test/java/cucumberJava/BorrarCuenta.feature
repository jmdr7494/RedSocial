Feature: Eliminar cuenta BD

@Scenario1
Scenario: Eliminacion correcta de la cuenta de un usuario
	Given Usuario conectado quiere borrar su cuenta
	When Datos correctos
	Then Borrar cuenta y cambiar publicaciones de propietario

#nuevo test incluido en la fase de mantenimiento
@Scenario2
Scenario: Eliminacion incorrecta de la cuenta de un usuario
	Given Usuario conectado quiere borrar su cuenta
	When Datos incorrectos
	Then No se borra cuenta