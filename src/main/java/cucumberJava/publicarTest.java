package cucumberJava;

import static org.junit.Assert.assertTrue;

import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class publicarTest {
	PublicacionDAOImpl publiDao= new PublicacionDAOImpl();
	Usuario usuario;
	Publicacion publicacion;
	
	@Given("^Usuario en pagina principal$")
	public void Usuario_en_pagina_principal() {
		usuario=new Usuario("milhouse.milhouse");
	}

	@When("^Publicacion correcta$")
	public void Publicacion_correcta() {
		publicacion = new Publicacion(usuario, "Texto a publicar");
		publiDao.insert(publicacion);
	}

	@Then("^Mensaje de exito a la publicacion$")
	public void Mensaje_de_exito_a_la_publicacion() {
		assertTrue(publiDao.existe(publicacion));
	}
}
