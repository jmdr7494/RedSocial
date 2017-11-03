$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('cucumberJava\SolicitudesAceptar.feature');
formatter.feature({
  "line": 1,
  "name": "Aceptar solicitudes de amistad de otro usuario",
  "description": "",
  "id": "aceptar-solicitudes-de-amistad-de-otro-usuario",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Aceptar solicitud valido",
  "description": "",
  "id": "aceptar-solicitudes-de-amistad-de-otro-usuario;aceptar-solicitud-valido",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Usuario conectado para aceptar",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Acepta solicitud de alguien que le ha mandado",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Aceptar como amigo",
  "keyword": "Then "
});
formatter.match({
  "location": "solicitudAceptarTest.Usuario_conectado_para_aceptar()"
});
formatter.result({
  "duration": 3133140400,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Acepta_solicitud_de_alguien_que_le_ha_mandado()"
});
formatter.result({
  "duration": 791197000,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Aceptar_como_amigo()"
});
formatter.result({
  "duration": 231563900,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Aceptar solicitud invalido",
  "description": "",
  "id": "aceptar-solicitudes-de-amistad-de-otro-usuario;aceptar-solicitud-invalido",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 10,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 12,
  "name": "Usuario conectado para aceptar",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "Acepta solicitud de alguien que no le ha mandado",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "Mensaje de error a la aceptacion",
  "keyword": "Then "
});
formatter.match({
  "location": "solicitudAceptarTest.Usuario_conectado_para_aceptar()"
});
formatter.result({
  "duration": 499586100,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Acepta_solicitud_de_alguien_que_no_le_ha_mandado()"
});
formatter.result({
  "duration": 83666100,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Mensaje_de_error_a_la_aceptacion()"
});
formatter.result({
  "duration": 172987300,
  "status": "passed"
});
formatter.uri('cucumberJava\SolicitudesEnvio.feature');
formatter.feature({
  "line": 1,
  "name": "Enviar solicitudes de amistad entre usuarios",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-entre-usuarios",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Envio de solicitud valido",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-entre-usuarios;envio-de-solicitud-valido",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Usuario conectado para enviar solicitud",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Selecciona otro usuario sin ser amigos ni tener solicitudes entre ellos",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Envio solicitud al segundo",
  "keyword": "Then "
});
formatter.match({
  "location": "solicitudEnviarTest.Usuario_conectado_para_enviar_solicitud()"
});
formatter.result({
  "duration": 170611300,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Selecciona_otro_usuario_sin_ser_amigos_ni_tener_solicitudes_entre_ellos()"
});
formatter.result({
  "duration": 184785600,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Envio_solicitud_al_segundo()"
});
formatter.result({
  "duration": 436287500,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Envio de solicitud invalido",
  "description": "Given: Usuario conectado para enviar solicitud",
  "id": "enviar-solicitudes-de-amistad-entre-usuarios;envio-de-solicitud-invalido",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 10,
      "name": "@Scenario2"
    }
  ]
});
formatter.step({
  "line": 13,
  "name": "Selecciona a otro usuario siendo su amigo",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "No envio de solicitud",
  "keyword": "Then "
});
formatter.match({
  "location": "solicitudEnviarTest.Selecciona_a_otro_usuario_siendo_su_amigo()"
});
formatter.result({
  "duration": 1039182600,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.No_envio_de_solicitud()"
});
formatter.result({
  "duration": 175892000,
  "status": "passed"
});
formatter.scenario({
  "line": 17,
  "name": "Envio de solicitud invalido",
  "description": "Given: Usuario conectado para enviar solicitud",
  "id": "enviar-solicitudes-de-amistad-entre-usuarios;envio-de-solicitud-invalido",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 16,
      "name": "@Scenario3"
    }
  ]
});
formatter.step({
  "line": 19,
  "name": "Selecciona a otro usuario teniendo solicitudes pendientes entre ellos",
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "Solicitud ya enviada",
  "keyword": "Then "
});
formatter.match({
  "location": "solicitudEnviarTest.Selecciona_a_otro_usuario_teniendo_solicitudes_pendientes_entre_ellos()"
});
formatter.result({
  "duration": 440392200,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Solicitud_ya_enviada()"
});
formatter.result({
  "duration": 142589000,
  "status": "passed"
});
});