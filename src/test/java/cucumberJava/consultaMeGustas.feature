Feature: Consultar Me gusta

@Scenario1
Scenario: Usuario quiere saber quien le ha dado a me gusta
	Given Usuario conectado consulta usuarios que han dado me gusta
	When Activacion correcta la consulta
	Then Se muestra correctamente lo usuarios que han dado me gusta
