package cucumberJava;

import static org.junit.Assert.*;

//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertFalse;

import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;


import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class eliminarPublicacionBDTest {
	PublicacionDAOImpl publiDao= new PublicacionDAOImpl();
	private String id="59efa8c911646802bc1cb5f4";
	private Publicacion publi;
	@Given("^Usuario quiere eliminar una publiacion$")
	public void Usuario_quiere_eliminar_una_publiacion() {
		publi=new Publicacion();
		publi.setId(id);
	}

	@When("^Eliminacion correcta$")
	public void Eliminacion_correcta() {  
		assertTrue(publiDao.existe(publi));
	    publiDao.remove(id);
	}

	@Then("^Mensaje de exito en la eliminacion de publicacion$")
	public void Mensaje_de_exito_en_la_eliminacion_de_publicacion() {
		assertFalse(publiDao.existe(publi));
	}
}