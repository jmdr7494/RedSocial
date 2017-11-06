Feature: Creacion de un nuevo usuario

@Scenario1
Scenario: Creacion correcta de cuenta
	Given Usuario en pagina de creacion
	When Nombre, email, password y confirmacion validos
	Then Mensaje de validacion y usuario insertado
	

@Scenario2
Scenario: Creacion incorrecta de cuenta por email invalido
	Given Usuario en pagina de creacion
	When Email no tiene extension alu.uclm.es
	Then Mensaje de error a la creacion email invaildo
	
@Scenario3
Scenario: Creacion incorrecta de cuenta por password que no coinciden
	Given Usuario en pagina de creacion
	When Password y su confirmacion no coinciden
	Then Mensaje de error a la creacion password no coinciden
	
@Scenario4
Scenario: Creacion incorrecta de cuenta por nombre invalido
	Given Usuario en pagina de creacion
	When Nombre no tiene el formato adecuado
	Then Mensaje de error a la creacion nombre invalido

@Scenario5
Scenario: Creacion incorrecta de cuenta por password poco segura
	Given Usuario en pagina de creacion
	When Password no tiene la seguridad adecuada
	Then Mensaje de error a la creacion password poco segura
	
@Scenario6
Scenario: Creacion incorrecta de cuenta cuenta  ya existente
	Given Usuario en pagina de creacion
	When Nombre que intenta registrar ya existe
	Then Mensaje de error a la creacion cuenta ya existente