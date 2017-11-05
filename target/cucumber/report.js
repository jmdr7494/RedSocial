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
  "duration": 3005471600,
  "status": "passed"
});
formatter.match({
  "location": "borrarCuentaTest.Datos_correctos()"
});
formatter.result({
  "duration": 115100,
  "status": "passed"
});
formatter.match({
  "location": "borrarCuentaTest.Borrar_cuenta_y_cambiar_publicaciones_de_propietario()"
});
formatter.result({
  "duration": 355674100,
  "status": "passed"
});
});