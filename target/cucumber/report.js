$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('cucumberJava\CreacionUsuario.feature');
formatter.feature({
  "line": 1,
  "name": "Creacion de un nuevo usuario",
  "description": "",
  "id": "creacion-de-un-nuevo-usuario",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Creacion correcta de cuenta",
  "description": "",
  "id": "creacion-de-un-nuevo-usuario;creacion-correcta-de-cuenta",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "Usuario en pagina de creacion",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Nombre, email, password y confirmacion validos",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Mensaje de validacion y usuario insertado",
  "keyword": "Then "
});
formatter.match({
  "location": "crearCuentaTest.Usuario_en_pagina_de_creacion()"
});
formatter.result({
  "duration": 227232400,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Nombre_email_contrase_a_y_confirmacion_validos()"
});
formatter.result({
  "duration": 540600,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_validacion_y_usuario_insertado()"
});
formatter.result({
  "duration": 2809418000,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Creacion incorrecta de cuenta por email invalido",
  "description": "",
  "id": "creacion-de-un-nuevo-usuario;creacion-incorrecta-de-cuenta-por-email-invalido",
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
  "name": "Usuario en pagina de creacion",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "Email no tiene extension alu.uclm.es",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "Mensaje de error a la creacion email invaildo",
  "keyword": "Then "
});
formatter.match({
  "location": "crearCuentaTest.Usuario_en_pagina_de_creacion()"
});
formatter.result({
  "duration": 29900,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Email_no_tiene_extension_alu_uclm_es()"
});
formatter.result({
  "duration": 37900,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_error_a_la_creacion_email_invaildo()"
});
formatter.result({
  "duration": 2961700,
  "status": "passed"
});
formatter.scenario({
  "line": 17,
  "name": "Creacion incorrecta de cuenta por password que no coinciden",
  "description": "",
  "id": "creacion-de-un-nuevo-usuario;creacion-incorrecta-de-cuenta-por-password-que-no-coinciden",
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
  "line": 18,
  "name": "Usuario en pagina de creacion",
  "keyword": "Given "
});
formatter.step({
  "line": 19,
  "name": "Password y su confirmacion no coinciden",
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "Mensaje de error a la creacion password no coinciden",
  "keyword": "Then "
});
formatter.match({
  "location": "crearCuentaTest.Usuario_en_pagina_de_creacion()"
});
formatter.result({
  "duration": 15900,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Contrase_a_y_su_confirmacion_no_coinciden()"
});
formatter.result({
  "duration": 21900,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_error_a_la_creacion_password_no_coinciden()"
});
formatter.result({
  "duration": 47600,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Creacion incorrecta de cuenta por nombre invalido",
  "description": "",
  "id": "creacion-de-un-nuevo-usuario;creacion-incorrecta-de-cuenta-por-nombre-invalido",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 22,
      "name": "@Scenario4"
    }
  ]
});
formatter.step({
  "line": 24,
  "name": "Usuario en pagina de creacion",
  "keyword": "Given "
});
formatter.step({
  "line": 25,
  "name": "Nombre no tiene el formato adecuado",
  "keyword": "When "
});
formatter.step({
  "line": 26,
  "name": "Mensaje de error a la creacion nombre invalido",
  "keyword": "Then "
});
formatter.match({
  "location": "crearCuentaTest.Usuario_en_pagina_de_creacion()"
});
formatter.result({
  "duration": 26000,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Nombre_no_tiene_el_formato_adecuado()"
});
formatter.result({
  "duration": 56400,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_error_a_la_creacion_nombre_invalido()"
});
formatter.result({
  "duration": 39800,
  "status": "passed"
});
formatter.scenario({
  "line": 29,
  "name": "Creacion incorrecta de cuenta por password poco segura",
  "description": "",
  "id": "creacion-de-un-nuevo-usuario;creacion-incorrecta-de-cuenta-por-password-poco-segura",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 28,
      "name": "@Scenario5"
    }
  ]
});
formatter.step({
  "line": 30,
  "name": "Usuario en pagina de creacion",
  "keyword": "Given "
});
formatter.step({
  "line": 31,
  "name": "Password no tiene la seguridad adecuada",
  "keyword": "When "
});
formatter.step({
  "line": 32,
  "name": "Mensaje de error a la creacion password poco segura",
  "keyword": "Then "
});
formatter.match({
  "location": "crearCuentaTest.Usuario_en_pagina_de_creacion()"
});
formatter.result({
  "duration": 15000,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Password_no_tiene_la_seguridad_adecuada()"
});
formatter.result({
  "duration": 16300,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_error_a_la_creacion_password_poco_segura()"
});
formatter.result({
  "duration": 62800,
  "status": "passed"
});
formatter.scenario({
  "line": 35,
  "name": "Creacion incorrecta de cuenta cuenta  ya existente",
  "description": "",
  "id": "creacion-de-un-nuevo-usuario;creacion-incorrecta-de-cuenta-cuenta--ya-existente",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 34,
      "name": "@Scenario6"
    }
  ]
});
formatter.step({
  "line": 36,
  "name": "Usuario en pagina de creacion",
  "keyword": "Given "
});
formatter.step({
  "line": 37,
  "name": "Nombre que intenta registrar ya existe",
  "keyword": "When "
});
formatter.step({
  "line": 38,
  "name": "Mensaje de error a la creacion cuenta ya existente",
  "keyword": "Then "
});
formatter.match({
  "location": "crearCuentaTest.Usuario_en_pagina_de_creacion()"
});
formatter.result({
  "duration": 14700,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Nombre_que_intenta_registrar_ya_existe()"
});
formatter.result({
  "duration": 272300,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_error_a_la_creacion_cuenta_ya_existente()"
});
formatter.result({
  "duration": 60148600,
  "status": "passed"
});
formatter.uri('cucumberJava\EliminarAmigo.feature');
formatter.feature({
  "line": 1,
  "name": "Eliminar de la lista de amigos a otro usuario",
  "description": "",
  "id": "eliminar-de-la-lista-de-amigos-a-otro-usuario",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Revocacion de amistad valido",
  "description": "",
  "id": "eliminar-de-la-lista-de-amigos-a-otro-usuario;revocacion-de-amistad-valido",
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
  "name": "Usuario conectado para borrar a otro usuario",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Eliminar un usuario que es tu amigo",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Borrar amigo de ambos usuarios",
  "keyword": "Then "
});
formatter.match({
  "location": "borrarAmigoTest.Usuario_conectado_para_borrar_a_otro_usuario()"
});
formatter.result({
  "duration": 215506200,
  "status": "passed"
});
formatter.match({
  "location": "borrarAmigoTest.Eliminar_un_usuario_que_es_tu_amigo()"
});
formatter.result({
  "duration": 1284700100,
  "status": "passed"
});
formatter.match({
  "location": "borrarAmigoTest.Borrar_amigo_de_ambos_usuarios()"
});
formatter.result({
  "duration": 730983000,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Revocacion de amistad invalido",
  "description": "",
  "id": "eliminar-de-la-lista-de-amigos-a-otro-usuario;revocacion-de-amistad-invalido",
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
  "name": "Usuario conectado para borrar a otro usuario",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "Eliminar un usuario que no es tu amigo",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "Mensaje de error a la revocacion de usuario",
  "keyword": "Then "
});
formatter.match({
  "location": "borrarAmigoTest.Usuario_conectado_para_borrar_a_otro_usuario()"
});
formatter.result({
  "duration": 233833200,
  "status": "passed"
});
formatter.match({
  "location": "borrarAmigoTest.Eliminar_un_usuario_que_no_es_tu_amigo()"
});
formatter.result({
  "duration": 205933900,
  "status": "passed"
});
formatter.match({
  "location": "borrarAmigoTest.Mensaje_de_error_a_la_revocacion_de_usuario()"
});
formatter.result({
  "duration": 121350600,
  "status": "passed"
});
formatter.uri('cucumberJava\Login.feature');
formatter.feature({
  "line": 1,
  "name": "Logeo al sistema",
  "description": "",
  "id": "logeo-al-sistema",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Login exitoso con credenciales validas",
  "description": "",
  "id": "logeo-al-sistema;login-exitoso-con-credenciales-validas",
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
  "name": "Usuario en pagina de login",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Credenciales correctas",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Mensaje de bienvenida al login",
  "keyword": "Then "
});
formatter.match({
  "location": "loginTest.Usuario_en_pagina_de_login()"
});
formatter.result({
  "duration": 5399900,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Credenciales_correctas()"
});
formatter.result({
  "duration": 35900,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Mensaje_de_bienvenida_al_login()"
});
formatter.result({
  "duration": 59534900,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Login fallido por nombre invalido",
  "description": "",
  "id": "logeo-al-sistema;login-fallido-por-nombre-invalido",
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
  "name": "Usuario en pagina de login",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "Nombre de usuario incorrecto",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "Mensaje de error al login",
  "keyword": "Then "
});
formatter.match({
  "location": "loginTest.Usuario_en_pagina_de_login()"
});
formatter.result({
  "duration": 19200,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Nombre_de_usuario_incorrecto()"
});
formatter.result({
  "duration": 25700,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Mensaje_de_error_al_login()"
});
formatter.result({
  "duration": 57113000,
  "status": "passed"
});
formatter.scenario({
  "line": 17,
  "name": "Login fallido por passoword invalido",
  "description": "",
  "id": "logeo-al-sistema;login-fallido-por-passoword-invalido",
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
  "line": 18,
  "name": "Usuario en pagina de login",
  "keyword": "Given "
});
formatter.step({
  "line": 19,
  "name": "Password incorrecta",
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "Mensaje de error al login",
  "keyword": "Then "
});
formatter.match({
  "location": "loginTest.Usuario_en_pagina_de_login()"
});
formatter.result({
  "duration": 15100,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Contrase_a_incorrecta()"
});
formatter.result({
  "duration": 33500,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Mensaje_de_error_al_login()"
});
formatter.result({
  "duration": 62803800,
  "status": "passed"
});
formatter.uri('cucumberJava\Publicar.feature');
formatter.feature({
  "line": 1,
  "name": "Publicacion",
  "description": "",
  "id": "publicacion",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Publicacion exitosa",
  "description": "",
  "id": "publicacion;publicacion-exitosa",
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
  "name": "Usuario en pagina principal",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Publicacion correcta",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Mensaje de exito a la publicacion",
  "keyword": "Then "
});
formatter.match({
  "location": "publicarTest.Usuario_en_pagina_principal()"
});
formatter.result({
  "duration": 4562900,
  "status": "passed"
});
formatter.match({
  "location": "publicarTest.Publicacion_correcta()"
});
formatter.result({
  "duration": 59427600,
  "status": "passed"
});
formatter.match({
  "location": "publicarTest.Mensaje_de_exito_a_la_publicacion()"
});
formatter.result({
  "duration": 59103600,
  "status": "passed"
});
formatter.uri('cucumberJava\SolicitudesAceptar.feature');
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
  "duration": 177263300,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Acepta_solicitud_de_alguien_que_le_ha_mandado()"
});
formatter.result({
  "duration": 1009401800,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Aceptar_como_amigo()"
});
formatter.result({
  "duration": 304524900,
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
  "duration": 178026500,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Acepta_solicitud_de_alguien_que_no_le_ha_mandado()"
});
formatter.result({
  "duration": 263762200,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Mensaje_de_error_a_la_aceptacion()"
});
formatter.result({
  "duration": 236127700,
  "status": "passed"
});
formatter.uri('cucumberJava\SolicitudesEnvio.feature');
formatter.feature({
  "line": 1,
  "name": "Enviar solicitudes de amistad a otro usuario",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Envio de solicitud de amistad valido",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario;envio-de-solicitud-de-amistad-valido",
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
  "duration": 245591900,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Selecciona_otro_usuario_sin_ser_amigos_ni_tener_solicitudes_entre_ellos()"
});
formatter.result({
  "duration": 242284400,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Envio_solicitud_al_segundo()"
});
formatter.result({
  "duration": 506925300,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Solicitud invalida porque son amigos",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario;solicitud-invalida-porque-son-amigos",
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
  "name": "Usuario conectado para enviar solicitud",
  "keyword": "Given "
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
  "location": "solicitudEnviarTest.Usuario_conectado_para_enviar_solicitud()"
});
formatter.result({
  "duration": 214109400,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Selecciona_a_otro_usuario_siendo_su_amigo()"
});
formatter.result({
  "duration": 1136601100,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.No_envio_de_solicitud()"
});
formatter.result({
  "duration": 216871200,
  "status": "passed"
});
formatter.scenario({
  "line": 17,
  "name": "Solicitud invalida porque tienen solicitudes pendientes",
  "description": "",
  "id": "enviar-solicitudes-de-amistad-a-otro-usuario;solicitud-invalida-porque-tienen-solicitudes-pendientes",
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
  "line": 18,
  "name": "Usuario conectado para enviar solicitud",
  "keyword": "Given "
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
  "location": "solicitudEnviarTest.Usuario_conectado_para_enviar_solicitud()"
});
formatter.result({
  "duration": 182816100,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Selecciona_a_otro_usuario_teniendo_solicitudes_pendientes_entre_ellos()"
});
formatter.result({
  "duration": 526049900,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Solicitud_ya_enviada()"
});
formatter.result({
  "duration": 166293500,
  "status": "passed"
});
formatter.uri('cucumberJava\SolicitudesRechazo.feature');
formatter.feature({
  "line": 1,
  "name": "Rechazar solicitudes de amistad de otro usuario",
  "description": "",
  "id": "rechazar-solicitudes-de-amistad-de-otro-usuario",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Rechazar solicitud valido",
  "description": "",
  "id": "rechazar-solicitudes-de-amistad-de-otro-usuario;rechazar-solicitud-valido",
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
  "name": "Usuario conectado para rechazar",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Rechazar solicitud de alguien que le ha mandado",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Rechazar peticion de amistad",
  "keyword": "Then "
});
formatter.match({
  "location": "solicitudRechazarTest.Usuario_conectado_para_rechazar()"
});
formatter.result({
  "duration": 207543800,
  "status": "passed"
});
formatter.match({
  "location": "solicitudRechazarTest.Rechazar_solicitud_de_alguien_que_le_ha_mandado()"
});
formatter.result({
  "duration": 859335600,
  "status": "passed"
});
formatter.match({
  "location": "solicitudRechazarTest.Rechazar_peticion_de_amistad()"
});
formatter.result({
  "duration": 252563900,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Rechazar solicitud invalido",
  "description": "",
  "id": "rechazar-solicitudes-de-amistad-de-otro-usuario;rechazar-solicitud-invalido",
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
  "name": "Usuario conectado para rechazar",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "Rechazar solicitud de alguien que no le ha mandado",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "Mensaje de error al rechazo de la peticion",
  "keyword": "Then "
});
formatter.match({
  "location": "solicitudRechazarTest.Usuario_conectado_para_rechazar()"
});
formatter.result({
  "duration": 231279800,
  "status": "passed"
});
formatter.match({
  "location": "solicitudRechazarTest.Rechazar_solicitud_de_alguien_que_no_le_ha_mandado()"
});
formatter.result({
  "duration": 177466300,
  "status": "passed"
});
formatter.match({
  "location": "solicitudRechazarTest.Mensaje_de_error_al_rechazo_de_la_peticion()"
});
formatter.result({
  "duration": 164645300,
  "status": "passed"
});
formatter.uri('cucumberJava\editarPublicacionBD.feature');
formatter.feature({
  "line": 1,
  "name": "Edicion de una publicacion en la BD",
  "description": "",
  "id": "edicion-de-una-publicacion-en-la-bd",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Edicion correcta de publicacion",
  "description": "",
  "id": "edicion-de-una-publicacion-en-la-bd;edicion-correcta-de-publicacion",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "El usuario quiere editar una publicacion",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Edicion correcta",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Mensaje de exito en la edicion de la publicacion",
  "keyword": "Then "
});
formatter.match({
  "location": "editarPublicacionBDTest.El_usuario_quiere_editar_una_publicacion()"
});
formatter.result({
  "duration": 77465000,
  "status": "passed"
});
formatter.match({
  "location": "editarPublicacionBDTest.Edicion_correcta()"
});
formatter.result({
  "duration": 157615700,
  "status": "passed"
});
formatter.match({
  "location": "editarPublicacionBDTest.Mensaje_de_exito_en_la_edicion_de_la_publicacion()"
});
formatter.result({
  "duration": 58047500,
  "status": "passed"
});
formatter.uri('cucumberJava\eliminarPublicacionBD.feature');
formatter.feature({
  "line": 1,
  "name": "eliminar publicacion DB",
  "description": "",
  "id": "eliminar-publicacion-db",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Eliminacion correcta de publicacion",
  "description": "",
  "id": "eliminar-publicacion-db;eliminacion-correcta-de-publicacion",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "Usuario quiere eliminar una publiacion",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Eliminacion correcta",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Mensaje de exito en la eliminacion de publicacion",
  "keyword": "Then "
});
formatter.match({
  "location": "eliminarPublicacionBDTest.Usuario_quiere_eliminar_una_publiacion()"
});
formatter.result({
  "duration": 64044600,
  "status": "passed"
});
formatter.match({
  "location": "eliminarPublicacionBDTest.Eliminacion_correcta()"
});
formatter.result({
  "duration": 263682800,
  "status": "passed"
});
formatter.match({
  "location": "eliminarPublicacionBDTest.Mensaje_de_exito_en_la_eliminacion_de_publicacion()"
});
formatter.result({
  "duration": 57701800,
  "status": "passed"
});
});