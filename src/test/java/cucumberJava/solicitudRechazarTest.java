package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class solicitudRechazarTest {

	private Usuario solicitante, rechaza, rechaza2;
	UsuarioDAOImpl usuarioDao= new UsuarioDAOImpl();
	
	@Given("^Usuario conectado para rechazar$")
	public void Usuario_conectado_para_rechazar() {
		solicitante=new Usuario("solicitante.solicitante", "1234qwer", "solicitante@alu.uclm.es", "solicitante");
		usuarioDao.delete(solicitante);
		try {		
			usuarioDao.insert(solicitante);
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@When("^Rechazar solicitud de alguien que le ha mandado$")
	public void Rechazar_solicitud_de_alguien_que_le_ha_mandado() {
		rechaza=new Usuario("rechaza.rechaza", "1234qwer", "rechaza@alu.uclm.es", "rechaza");
		usuarioDao.delete(rechaza);
		try {
			usuarioDao.insert(rechaza);
		} catch (Exception e) {
			assertFalse(true);
		}
		solicitante=new Usuario("solicitante.solicitante");
		
		try {
			utilidades.enviarSolicitud(solicitante, rechaza);
			utilidades.rechazarSolicitud(solicitante, rechaza);
		} catch (Exception e) {
			assertFalse(true);
		}
		
	}

	@Then("^Rechazar peticion de amistad$")
	public void Rechazar_peticion_de_amistad() {
		assertFalse(utilidades.comprobarAmistad(solicitante, rechaza));
		assertFalse(utilidades.comprobarAmistad(rechaza,solicitante));
		assertFalse(utilidades.comprobarSolicitudes(solicitante, rechaza));
		usuarioDao.delete(rechaza);
	}

	@When("^Rechazar solicitud de alguien que no le ha mandado$")
	public void Rechazar_solicitud_de_alguien_que_no_le_ha_mandado() {
		rechaza2=new Usuario("rechaza2.rechaza2", "1234qwer", "rechaza2@alu.uclm.es", "rechaza2");
		usuarioDao.delete(rechaza2);
		try {
			usuarioDao.insert(rechaza2);
		} catch (Exception e) {
			assertFalse(true);
		}
		solicitante=new Usuario("solicitante.solicitante");
		
	}

	@Then("^Mensaje de error al rechazo de la peticion$")
	public void Mensaje_de_error_al_rechazo_de_la_peticion() {
		try {
			utilidades.rechazarSolicitud(solicitante, rechaza2);
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("No te ha mandado solicitud"));
		}
		usuarioDao.delete(rechaza2);
	}
}
