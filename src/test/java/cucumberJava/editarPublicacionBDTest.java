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
	
	@Given("^El usuario quiere editar una publicacion$")
	public void El_usuario_quiere_editar_una_publicacion() {
		usuario = new Usuario("jose.maria");
	}

	@When("^Edicion correcta$")
	public void Edicion_correcta() {
		publicacion = new Publicacion(usuario, "Publicacion EDICION");
		publiDao.insert(publicacion);
	    publiDao.update(publicacion,"Texto EDICION MODIFICADO");
	    publicacion = new Publicacion(usuario, "Texto EDICION MODIFICADO");
	    
	}

	@Then("^Mensaje de exito en la edicion de la publicacion$")
	public void Mensaje_de_exito_en_la_edicion_de_la_publicacion() {
		assertTrue(publiDao.existe(publicacion));
	}
}