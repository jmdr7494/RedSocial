package cucumberJava;

import static org.junit.Assert.assertTrue;


import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;


import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;


public class editarPublicacionBDTest {
	private PublicacionDAOImpl publiDao= new PublicacionDAOImpl();
	private Usuario usuario;
	private Publicacion publicacion, aux;

	
	@Given("^El usuario quiere editar una publicacion$")
	public void El_usuario_quiere_editar_una_publicacion() {
		usuario=new Usuario("milhouse.milhouse");
		publicacion=new Publicacion(usuario, "Texto a editar");
		publiDao.insert(publicacion);
	}

	@When("^Edicion correcta$")
	public void Edicion_correcta() {
		aux=publiDao.selectOne(publicacion);
		assertTrue(aux.getTexto().equals("Texto a editar"));
		
	    publiDao.update(aux.getId(),"Texto EDICION MODIFICADO");   
	}

	@Then("^Mensaje de exito en la edicion de la publicacion$")
	public void Mensaje_de_exito_en_la_edicion_de_la_publicacion() {
		assertTrue(publiDao.selectOne(aux).getTexto().equals("Texto EDICION MODIFICADO"));
	}
}