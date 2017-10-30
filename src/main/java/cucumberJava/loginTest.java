package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.intravita.proyectointranet.modelo.Administrador;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.AdministradorDAOImpl;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class loginTest {
	private Usuario usuario;
	private Administrador administrador;
	private String nombre;
	private String pwd;
	UsuarioDAOImpl usuarioDao= new UsuarioDAOImpl();
	AdministradorDAOImpl administradorDao= new AdministradorDAOImpl();
	
	@Given("^Usuario en pagina de login$")
	public void Usuario_en_pagina_de_login() {
		usuario=new Usuario();
		administrador=new Administrador();
	}

	@When("^Credenciales correctas$")
	public void Credenciales_correctas() {
		nombre="milhouse.milhouse";
		pwd="1234milhouse";
		usuario.setNombre(nombre);
		usuario.setClave(pwd);
		
		administrador.setNombre(nombre);
		administrador.setClave(pwd);
	}

	@Then("^Mensaje de bienvenida al login$")
	public void Mensaje_de_bienvenida_al_login() {
		assertTrue(usuarioDao.login(usuario) || administradorDao.login(administrador));
	}

	@When("^Nombre de usuario incorrecto$")
	public void Nombre_de_usuario_incorrecto() {
		nombre="noexiste";
		pwd="1234";
		usuario.setNombre(nombre);
		usuario.setClave(pwd);
		
		administrador.setNombre(nombre);
		administrador.setClave(pwd);
	}
	
	@When("^Password incorrecta$")
	public void Contrase_a_incorrecta() {
		nombre="milhouse.milhouse";
		pwd="12345";
		usuario.setNombre(nombre);
		usuario.setClave(pwd);
		
		administrador.setNombre(nombre);
		administrador.setClave(pwd);
	}
	@Then("^Mensaje de error al login$")
	public void Mensaje_de_error_al_login() {
		assertFalse(usuarioDao.login(usuario) && administradorDao.login(administrador));
	}


}
