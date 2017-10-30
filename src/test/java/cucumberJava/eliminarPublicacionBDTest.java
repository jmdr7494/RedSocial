package cucumberJava;

import static org.junit.Assert.*;

//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertFalse;

import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;

public class eliminarPublicacionBDTest {
	PublicacionDAOImpl publiDao= new PublicacionDAOImpl();
	Usuario usuario;
	Publicacion publicacion;
	
	@Given("^Usuario quiere eliminar una publiacion$")
	public void Usuario_quiere_eliminar_una_publiacion() {
	    usuario = new Usuario("milhouse.milhouse");
	}

	@When("^Eliminacion correcta$")
	public void Eliminacion_correcta() {
	    publicacion = new Publicacion(usuario, "Publicacion ELIMINAR");
	    publiDao.insert(publicacion);
	    publiDao.remove(publicacion);
	}

	@Then("^Mensaje de exito en la eliminacion de publicacion$")
	public void Mensaje_de_exito_en_la_eliminacion_de_publicacion() {
		assertFalse(publiDao.existe(publicacion));
	}
}