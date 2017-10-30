package cucumberJava;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;

public class editarPublicacionBDTest {
	PublicacionDAOImpl publiDao= new PublicacionDAOImpl();
	Usuario usuario;
	Publicacion publicacion;
	String id="59ef6b52495cf514643075fb";
	
	@Given("^El usuario quiere editar una publicacion$")
	public void El_usuario_quiere_editar_una_publicacion() {
		
	}

	@When("^Edicion correcta$")
	public void Edicion_correcta() {
		assertTrue(publiDao.selectOne(id).getTexto().equals("Texto a publicar"));
	    publiDao.update(id,"Texto EDICION MODIFICADO");   
	}

	@Then("^Mensaje de exito en la edicion de la publicacion$")
	public void Mensaje_de_exito_en_la_edicion_de_la_publicacion() {
		assertTrue(publiDao.selectOne(id).getTexto().equals("Texto EDICION MODIFICADO"));
	}
}