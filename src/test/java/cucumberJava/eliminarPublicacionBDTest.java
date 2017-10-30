package cucumberJava;

import static org.junit.Assert.*;

//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertFalse;

import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;


import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class eliminarPublicacionBDTest {
	private PublicacionDAOImpl publiDao= new PublicacionDAOImpl();
	private Publicacion publi, aux;
	private Usuario usuario;
	
	@Given("^Usuario quiere eliminar una publiacion$")
	public void Usuario_quiere_eliminar_una_publiacion() {
		usuario=new Usuario("milhouse.milhouse");
		publi=new Publicacion(usuario, "Texto a eliminar");
		publiDao.insert(publi);
	}

	@When("^Eliminacion correcta$")
	public void Eliminacion_correcta() {  
		assertTrue(publiDao.existe(publi));
	    aux=publiDao.selectOne(publi);
	    publiDao.remove(aux.getId());
	}

	@Then("^Mensaje de exito en la eliminacion de publicacion$")
	public void Mensaje_de_exito_en_la_eliminacion_de_publicacion() {
		assertFalse(publiDao.existe(aux));
	}
}