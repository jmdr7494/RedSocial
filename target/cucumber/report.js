$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('cucumberJava\CompartirPublicacion.feature');
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
  "duration": 3047173900,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.No_esta_compartida_por_el_usuario()"
});
formatter.result({
  "duration": 142804600,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.Compartir_publicacion()"
});
formatter.result({
  "duration": 69056628300,
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
  "duration": 892748400,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.Esta_compartida_por_el_usuario()"
});
formatter.result({
  "duration": 371784400,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.No_compartir_publicacion()"
});
formatter.result({
  "duration": 39646975600,
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
  "duration": 330595300,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.Esta_compartida_por_el_usuario()"
});
formatter.result({
  "duration": 297488900,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.Dejar_de_compartir_publicacion()"
});
formatter.result({
  "duration": 29836786100,
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
  "duration": 374885500,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.No_esta_compartida_por_el_usuario()"
});
formatter.result({
  "duration": 179340700,
  "status": "passed"
});
formatter.match({
  "location": "compartirPublicacionesTest.No_dejar_de_compartir_publicacion()"
});
formatter.result({
  "duration": 26752349900,
  "status": "passed"
});
});