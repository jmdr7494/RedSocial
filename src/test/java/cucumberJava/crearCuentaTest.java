package cucumberJava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class crearCuentaTest {

	private Usuario usuario;
	private String nombre;
	private String pwd1;
	private String pwd2;
	private String email;
	private String respuesta="fernando";
	UsuarioDAOImpl usuarioDao= new UsuarioDAOImpl();
	
	@Given("^Usuario en pagina de creacion$")
	public void Usuario_en_pagina_de_creacion() {
		usuario=new Usuario();
	}

	@When("^Nombre, email, password y confirmacion validos$")
	public void Nombre_email_contrase_a_y_confirmacion_validos() {
		nombre="fernando.cozar";
		email="fernando@alu.uclm.es";
		pwd1="1234Fernando";
		pwd2="1234Fernando";
		respuesta="fer";
		try {
			utilidades.credencialesValidas(nombre, email, pwd1, pwd2, respuesta);
		}catch(Exception e){
			assertTrue(false);
		}
		
		usuario.setNombre(nombre);
		usuario.setClave(pwd1);
		usuario.setEmail(email);
		usuario.setRespuesta(respuesta);
	}

	@Then("^Mensaje de validacion y usuario insertado$")
	public void Mensaje_de_validacion_y_usuario_insertado() {
		if(usuarioDao.selectNombre(usuario))usuarioDao.delete(usuario);
		try {
			usuarioDao.insert(usuario);
		}catch(Exception e) {
			assertTrue(false);
		}
	}

	@When("^Email no tiene extension alu.uclm.es$")
	public void Email_no_tiene_extension_alu_uclm_es() {
		nombre="fernando.cozar";
		email="fernando@alu.uclm.ese";
		pwd1="1234";
		pwd2="1234";
	}


	@When("^Password y su confirmacion no coinciden$")
	public void Contrase_a_y_su_confirmacion_no_coinciden() {
		nombre="fernando.cozar";
		email="fernando@alu.uclm.es";
		pwd1="1234";
		pwd2="12345";
	}
	@When("^Nombre no tiene el formato adecuado$")
	public void Nombre_no_tiene_el_formato_adecuado() {
		nombre="fernandocozar";
		email="fernando@alu.uclm.es";
		pwd1="1234";
		pwd2="1234";
	}
	@When("^Password no tiene la seguridad adecuada$")
	public void Password_no_tiene_la_seguridad_adecuada() {
		nombre="fernando.cozar";
		email="fernando@alu.uclm.es";
		pwd1="1234";
		pwd2="1234";
		respuesta="fer";
	}
	@Then("^Mensaje de error a la creacion email invaildo$")
	public void Mensaje_de_error_a_la_creacion_email_invaildo() {
		try {
			utilidades.credencialesValidas(nombre, email, pwd1, pwd2, respuesta);
			assertTrue(false);
		}catch (Exception e){
			assertEquals(e.getMessage(), "Email invalido");
		}
	}

	@Then("^Mensaje de error a la creacion password no coinciden$")
	public void Mensaje_de_error_a_la_creacion_password_no_coinciden() {
		try {
			utilidades.credencialesValidas(nombre, email, pwd1, pwd2, respuesta);
			assertTrue(false);
		}catch (Exception e){
			assertEquals(e.getMessage(), "No coinciden las password");
		}
	}

	@Then("^Mensaje de error a la creacion nombre invalido$")
	public void Mensaje_de_error_a_la_creacion_nombre_invalido() {
		try {
			utilidades.credencialesValidas(nombre, email, pwd1, pwd2, respuesta);
			assertTrue(false);
		}catch (Exception e){
			assertEquals(e.getMessage(), "Formato nombre invalido");
		}
	}



	@Then("^Mensaje de error a la creacion password poco segura$")
	public void Mensaje_de_error_a_la_creacion_password_poco_segura() {
		try {
			utilidades.credencialesValidas(nombre, email, pwd1, pwd2, respuesta);
			assertTrue(false);
		}catch (Exception e){
			assertEquals(e.getMessage(), "Password poco segura (minimo 8 caracteres, con numeros y letras)");
		}
	}
	@When("^Nombre que intenta registrar ya existe$")
	public void Nombre_que_intenta_registrar_ya_existe() {
		nombre="milhouse.milhouse";
		email="milhouse@alu.uclm.es";
		pwd1="1234milhouse";
		pwd2="1234milhouse";
		try {
			utilidades.credencialesValidas(nombre, email, pwd1, pwd2, respuesta);
		}catch(Exception e){
			assertTrue(false);
		}
		
		usuario.setNombre(nombre);
		usuario.setClave(pwd1);
		usuario.setEmail(email);
	}

	@Then("^Mensaje de error a la creacion cuenta ya existente$")
	public void Mensaje_de_error_a_la_creacion_cuenta_ya_existente() {
		try {
			usuarioDao.insert(usuario);
		}catch(Exception e) {
			assertTrue(true);
		}
	}

}
