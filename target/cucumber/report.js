$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('cucumberJava\BorrarCuenta.feature');
formatter.feature({
  "line": 1,
  "name": "Eliminar cuenta BD",
  "description": "",
  "id": "eliminar-cuenta-bd",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Eliminacion correcta de la cuenta de un usuario",
  "description": "",
  "id": "eliminar-cuenta-bd;eliminacion-correcta-de-la-cuenta-de-un-usuario",
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
  "name": "Usuario conectado quiere borrar su cuenta",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Datos correctos",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Borrar cuenta y cambiar publicaciones de propietario",
  "keyword": "Then "
});
formatter.match({
  "location": "borrarCuentaTest.Usuario_conectado_quiere_borrar_su_cuenta()"
});
formatter.result({
  "duration": 10164078800,
  "status": "passed"
});
formatter.match({
  "location": "borrarCuentaTest.Datos_correctos()"
});
formatter.result({
  "duration": 23700,
  "status": "passed"
});
formatter.match({
  "location": "borrarCuentaTest.Borrar_cuenta_y_cambiar_publicaciones_de_propietario()"
});
formatter.result({
  "duration": 2523137700,
  "status": "passed"
});
formatter.uri('cucumberJava\CompartirPublicacion.feature');
formatter.feature({
  "line": 1,
  "name": "Compartir publicaciones",
  "description": "",
  "id": "compartir-publicaciones",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Compartir correctamente una publicacion",
  "description": "",
  "id": "compartir-publicaciones;compartir-correctamente-una-publicacion",
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
  "name": "Usuario conectado quiere compartir una publicacion",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "No esta compartida por el usuario",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Compartir publicacion",
  "keyword": "Then "
});
formatter.match({
  "location": "compartirPublicacionesTest.Usuario_conectado_quiere_compartir_una_publicacion()"
});
formatter.result({
  "duration": 468982900,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.No_esta_compartida_por_el_usuario()"
});
formatter.result({
  "duration": 272763300,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.Compartir_publicacion()"
});
formatter.result({
  "duration": 4847300400,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "Compartir erroneamente una publicacion",
  "description": "",
  "id": "compartir-publicaciones;compartir-erroneamente-una-publicacion",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 9,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 11,
  "name": "Usuario conectado quiere compartir una publicacion",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "Esta compartida por el usuario",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "No compartir publicacion",
  "keyword": "Then "
});
formatter.match({
  "location": "compartirPublicacionesTest.Usuario_conectado_quiere_compartir_una_publicacion()"
});
formatter.result({
  "duration": 423237600,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.Esta_compartida_por_el_usuario()"
});
formatter.result({
  "duration": 373526000,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.No_compartir_publicacion()"
});
formatter.result({
  "duration": 4884561500,
  "status": "passed"
});
formatter.scenario({
  "line": 16,
  "name": "Dejar de compartir una publicacion",
  "description": "",
  "id": "compartir-publicaciones;dejar-de-compartir-una-publicacion",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 15,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "Usuario conectado quiere dejar de compartir una publicacion",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "Esta compartida por el usuario",
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "Dejar de compartir publicacion",
  "keyword": "Then "
});
formatter.match({
  "location": "compartirPublicacionesTest.Usuario_conectado_quiere_dejar_de_compartir_una_publicacion()"
});
formatter.result({
  "duration": 448002200,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.Esta_compartida_por_el_usuario()"
});
formatter.result({
  "duration": 405834200,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.Dejar_de_compartir_publicacion()"
});
formatter.result({
  "duration": 5062428700,
  "status": "passed"
});
formatter.scenario({
  "line": 22,
  "name": "Dejar de compartir una publicacion erroneamente",
  "description": "",
  "id": "compartir-publicaciones;dejar-de-compartir-una-publicacion-erroneamente",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 21,
      "name": "@Scenario1"
    }
  ]
});
formatter.step({
  "line": 23,
  "name": "Usuario conectado quiere dejar de compartir una publicacion",
  "keyword": "Given "
});
formatter.step({
  "line": 24,
  "name": "No esta compartida por el usuario",
  "keyword": "When "
});
formatter.step({
  "line": 25,
  "name": "No dejar de compartir publicacion",
  "keyword": "Then "
});
formatter.match({
  "location": "compartirPublicacionesTest.Usuario_conectado_quiere_dejar_de_compartir_una_publicacion()"
});
formatter.result({
  "duration": 416190100,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.No_esta_compartida_por_el_usuario()"
});
formatter.result({
  "duration": 378569200,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.No_dejar_de_compartir_publicacion()"
});
formatter.result({
  "duration": 4767875000,
  "status": "passed"
});
formatter.uri('cucumberJava\CreacionUsuario.feature');
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
  "duration": 6172800,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Nombre_email_contrase_a_y_confirmacion_validos()"
});
formatter.result({
  "duration": 215300,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_validacion_y_usuario_insertado()"
});
formatter.result({
  "duration": 2508298600,
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
  "duration": 24800,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Email_no_tiene_extension_alu_uclm_es()"
});
formatter.result({
  "duration": 54900,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_error_a_la_creacion_email_invaildo()"
});
formatter.result({
  "duration": 88700,
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
  "duration": 23700,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Contrase_a_y_su_confirmacion_no_coinciden()"
});
formatter.result({
  "duration": 31100,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_error_a_la_creacion_password_no_coinciden()"
});
formatter.result({
  "duration": 55200,
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
  "duration": 33100,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Nombre_no_tiene_el_formato_adecuado()"
});
formatter.result({
  "duration": 91600,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_error_a_la_creacion_nombre_invalido()"
});
formatter.result({
  "duration": 89300,
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
  "duration": 30900,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Password_no_tiene_la_seguridad_adecuada()"
});
formatter.result({
  "duration": 37400,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_error_a_la_creacion_password_poco_segura()"
});
formatter.result({
  "duration": 569000,
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
  "duration": 29100,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Nombre_que_intenta_registrar_ya_existe()"
});
formatter.result({
  "duration": 481100,
  "status": "passed"
});
formatter.match({
  "location": "crearCuentaTest.Mensaje_de_error_a_la_creacion_cuenta_ya_existente()"
});
formatter.result({
  "duration": 56496100,
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
  "duration": 2341019300,
  "status": "passed"
});
formatter.match({
  "location": "borrarAmigoTest.Eliminar_un_usuario_que_es_tu_amigo()"
});
formatter.result({
  "duration": 1049363000,
  "status": "passed"
});
formatter.match({
  "location": "borrarAmigoTest.Borrar_amigo_de_ambos_usuarios()"
});
formatter.result({
  "duration": 2647090300,
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
  "duration": 2019161400,
  "status": "passed"
});
formatter.match({
  "location": "borrarAmigoTest.Eliminar_un_usuario_que_no_es_tu_amigo()"
});
formatter.result({
  "duration": 167798900,
  "status": "passed"
});
formatter.match({
  "location": "borrarAmigoTest.Mensaje_de_error_a_la_revocacion_de_usuario()"
});
formatter.result({
  "duration": 1939144800,
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
  "duration": 9295900,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Credenciales_correctas()"
});
formatter.result({
  "duration": 59600,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Mensaje_de_bienvenida_al_login()"
});
formatter.result({
  "duration": 55341500,
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
  "duration": 29200,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Nombre_de_usuario_incorrecto()"
});
formatter.result({
  "duration": 48500,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Mensaje_de_error_al_login()"
});
formatter.result({
  "duration": 82647200,
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
  "duration": 25000,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Contrase_a_incorrecta()"
});
formatter.result({
  "duration": 35600,
  "status": "passed"
});
formatter.match({
  "location": "loginTest.Mensaje_de_error_al_login()"
});
formatter.result({
  "duration": 94380200,
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
  "duration": 4554900,
  "status": "passed"
});
formatter.match({
  "location": "publicarTest.Publicacion_correcta()"
});
formatter.result({
  "duration": 54117600,
  "status": "passed"
});
formatter.match({
  "location": "publicarTest.Mensaje_de_exito_a_la_publicacion()"
});
formatter.result({
  "duration": 72476900,
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
  "duration": 1956618300,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Acepta_solicitud_de_alguien_que_le_ha_mandado()"
});
formatter.result({
  "duration": 1168634500,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Aceptar_como_amigo()"
});
formatter.result({
  "duration": 2428730900,
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
  "duration": 1986003800,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Acepta_solicitud_de_alguien_que_no_le_ha_mandado()"
});
formatter.result({
  "duration": 223032900,
  "status": "passed"
});
formatter.match({
  "location": "solicitudAceptarTest.Mensaje_de_error_a_la_aceptacion()"
});
formatter.result({
  "duration": 2100912300,
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
  "duration": 2106109600,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Selecciona_otro_usuario_sin_ser_amigos_ni_tener_solicitudes_entre_ellos()"
});
formatter.result({
  "duration": 204349500,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Envio_solicitud_al_segundo()"
});
formatter.result({
  "duration": 2502335400,
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
  "duration": 2112796700,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Selecciona_a_otro_usuario_siendo_su_amigo()"
});
formatter.result({
  "duration": 1105305100,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.No_envio_de_solicitud()"
});
formatter.result({
  "duration": 2418401100,
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
  "duration": 1969619200,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Selecciona_a_otro_usuario_teniendo_solicitudes_pendientes_entre_ellos()"
});
formatter.result({
  "duration": 548644000,
  "status": "passed"
});
formatter.match({
  "location": "solicitudEnviarTest.Solicitud_ya_enviada()"
});
formatter.result({
  "duration": 2035778000,
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
  "duration": 2314218100,
  "status": "passed"
});
formatter.match({
  "location": "solicitudRechazarTest.Rechazar_solicitud_de_alguien_que_le_ha_mandado()"
});
formatter.result({
  "duration": 759296300,
  "status": "passed"
});
formatter.match({
  "location": "solicitudRechazarTest.Rechazar_peticion_de_amistad()"
});
formatter.result({
  "duration": 2109397100,
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
  "duration": 1949206200,
  "status": "passed"
});
formatter.match({
  "location": "solicitudRechazarTest.Rechazar_solicitud_de_alguien_que_no_le_ha_mandado()"
});
formatter.result({
  "duration": 177187800,
  "status": "passed"
});
formatter.match({
  "location": "solicitudRechazarTest.Mensaje_de_error_al_rechazo_de_la_peticion()"
});
formatter.result({
  "duration": 2006728700,
  "status": "passed"
});
formatter.uri('cucumberJava\consultaMeGustas.feature');
formatter.feature({
  "line": 1,
  "name": "Consultar Me gusta",
  "description": "",
  "id": "consultar-me-gusta",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Usuario quiere saber quien le ha dado a me gusta",
  "description": "",
  "id": "consultar-me-gusta;usuario-quiere-saber-quien-le-ha-dado-a-me-gusta",
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
  "name": "Usuario conectado consulta usuarios que han dado me gusta",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Activacion correcta la consulta",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Se muestra correctamente lo usuarios que han dado me gusta",
  "keyword": "Then "
});
formatter.match({
  "location": "consultaMeGustaTest.Usuario_conectado_consulta_usuarios_que_han_dado_me_gusta()"
});
formatter.result({
  "duration": 80033400,
  "status": "passed"
});
formatter.match({
  "location": "consultaMeGustaTest.Activacion_correcta_la_consulta()"
});
formatter.result({
  "duration": 313274500,
  "status": "passed"
});
formatter.match({
  "location": "consultaMeGustaTest.Se_muestra_correctamente_lo_usuarios_que_han_dado_me_gusta()"
});
formatter.result({
  "duration": 166102700,
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
  "duration": 58135400,
  "status": "passed"
});
formatter.match({
  "location": "editarPublicacionBDTest.Edicion_correcta()"
});
formatter.result({
  "duration": 132606700,
  "status": "passed"
});
formatter.match({
  "location": "editarPublicacionBDTest.Mensaje_de_exito_en_la_edicion_de_la_publicacion()"
});
formatter.result({
  "duration": 51874200,
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
  "duration": 59833100,
  "status": "passed"
});
formatter.match({
  "location": "eliminarPublicacionBDTest.Eliminacion_correcta()"
});
formatter.result({
  "duration": 277326600,
  "status": "passed"
});
formatter.match({
  "location": "eliminarPublicacionBDTest.Mensaje_de_exito_en_la_eliminacion_de_publicacion()"
});
formatter.result({
  "duration": 50601100,
  "status": "passed"
});
});