package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bson.BsonString;

import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;

public class compartirPublicacionesTest {

	private Usuario compartir, publicador;
	private Publicacion nocompartida, compartida;
	private PublicacionDAOImpl publiDao= new PublicacionDAOImpl();
	private UsuarioDAOImpl usuarioDao= new UsuarioDAOImpl();
	
	@Given("^Usuario conectado quiere compartir una publicacion$")
	public void Usuario_conectado_quiere_compartir_una_publicacion() {
		compartir=new Usuario("compartir.compartir", "1234qwer", "compartir@alu.uclm.es" , "compartir");
		publicador=new Usuario("publicador.publicador", "1234qwer", "publicador@alu.uclm.es" , "publicador");
		try {
			if(usuarioDao.selectNombre(compartir))usuarioDao.delete(compartir);
			if(usuarioDao.selectNombre(publicador))usuarioDao.delete(publicador);
			if(!usuarioDao.selectNombre(compartir))usuarioDao.insert(compartir);;
			if(!usuarioDao.selectNombre(publicador))usuarioDao.insert(publicador);
		}catch(Exception e) {
			assertFalse(true);
		}
	}

	@When("^No esta compartida por el usuario$")
	public void No_esta_compartida_por_el_usuario() {
		nocompartida=new Publicacion(publicador, "Para compartir");
		publiDao.insert(nocompartida);
		nocompartida=publiDao.selectOne(nocompartida);
		assertFalse(publiDao.obtenerCompartidos(nocompartida).contains(new BsonString(compartir.getNombre())));
	}

	@Then("^Compartir publicacion$")
	public void Compartir_publicacion() {
		try {
			utilidades.compartirPublicacion(compartir, nocompartida);
		}catch(Exception e){
			assertFalse(true);
		}
		publiDao.remove(nocompartida.getId());
		usuarioDao.delete(compartir);
		usuarioDao.delete(publicador);
	}

	@When("^Esta compartida por el usuario$")
	public void Esta_compartida_por_el_usuario() {
		compartida=new Publicacion(publicador, "Para compartir 2");
		publiDao.insert(compartida);
		compartida=publiDao.selectOne(compartida);
		try {
			utilidades.compartirPublicacion(compartir, compartida);
		}catch(Exception e){
			assertFalse(true);
		}
		assertTrue(publiDao.obtenerCompartidos(compartida).contains(new BsonString(compartir.getNombre())));
	}

	@Then("^No compartir publicacion$")
	public void No_compartir_publicacion() {
		try {
			utilidades.compartirPublicacion(compartir, compartida);
			assertFalse(true);
		}catch(Exception e){
			assertTrue(e.getMessage().equals("Ya estas compartiendo esta publicacion"));
		}
		publiDao.remove(compartida.getId());
		usuarioDao.delete(compartir);
		usuarioDao.delete(publicador);
	}

	@Given("^Usuario conectado quiere dejar de compartir una publicacion$")
	public void Usuario_conectado_quiere_dejar_de_compartir_una_publicacion() {
		compartir=new Usuario("compartir.compartir", "1234qwer", "compartir@alu.uclm.es" , "compartir");
		publicador=new Usuario("publicador.publicador", "1234qwer", "publicador@alu.uclm.es" , "publicador");
		try {
			if(usuarioDao.selectNombre(compartir))usuarioDao.delete(compartir);
			if(usuarioDao.selectNombre(publicador))usuarioDao.delete(publicador);
			if(!usuarioDao.selectNombre(compartir))usuarioDao.insert(compartir);;
			if(!usuarioDao.selectNombre(publicador))usuarioDao.insert(publicador);
		}catch(Exception e) {
			assertFalse(true);
		}
	}

	@Then("^Dejar de compartir publicacion$")
	public void Dejar_de_compartir_publicacion() {
		try {
			utilidades.dejarCompartirPublicacion(compartir, compartida);
		}catch(Exception e) {
			assertFalse(true);
		}
		publiDao.remove(compartida.getId());
		usuarioDao.delete(compartir);
		usuarioDao.delete(publicador);
	}

	@Then("^No dejar de compartir publicacion$")
	public void No_dejar_de_compartir_publicacion() {
		try {
			utilidades.dejarCompartirPublicacion(compartir, nocompartida);
			assertFalse(true);
		}catch(Exception e) {
			assertTrue(e.getMessage().equals("No estas compartiendo esta publicacion"));
		}
		publiDao.remove(nocompartida.getId());
		usuarioDao.delete(compartir);
		usuarioDao.delete(publicador);
	}
}
