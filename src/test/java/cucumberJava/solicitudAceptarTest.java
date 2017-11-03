package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class solicitudAceptarTest {
	private Usuario solicitante, acepta, acepta2;
	
	UsuarioDAOImpl usuarioDao= new UsuarioDAOImpl();
	
	@Given("^Usuario conectado para aceptar$")
	public void Usuario_conectado_para_aceptar() {
		acepta=new Usuario("acepta.acepta", "1234qwer", "acepta@alu.uclm.es", "acepta");
		acepta2=new Usuario("acepta2.acepta2", "1234qwer", "acepta2@alu.uclm.es", "acepta2");
		solicitante=new Usuario("solicitante.solicitante", "1234qwer", "solicitante@alu.uclm.es", "solicitante");
		usuarioDao.delete(solicitante);
		usuarioDao.delete(acepta);
		usuarioDao.delete(acepta2);
		try {
			usuarioDao.insert(acepta);
			usuarioDao.insert(acepta2);
			usuarioDao.insert(solicitante);
		} catch (Exception e) {
			assertFalse(true);
		}

	}
	@When("^Acepta solicitud de alguien que le ha mandado$")
	public void Acepta_solicitud_de_alguien_que_le_ha_mandado() {
		acepta=new Usuario("acepta.acepta");
		solicitante=new Usuario("solicitante.solicitante");
		
		usuarioDao.enviarSolicitud(solicitante, acepta);
		assertTrue(utilidades.comprobarSolicitudes(solicitante, acepta));
		usuarioDao.aceptarSolicitud(solicitante, acepta);
	}
	@Then("^Aceptar como amigo$")
	public void Aceptar_como_amigo() {
		assertTrue(utilidades.comprobarAmistad(solicitante, acepta));
		assertTrue(utilidades.comprobarAmistad(acepta,solicitante));
		assertFalse(utilidades.comprobarSolicitudes(solicitante, acepta));
		usuarioDao.delete(acepta);
	}
	
	
	@When("^Acepta solicitud de alguien que no le ha mandado$")
	public void Acepta_solicitud_de_alguien_que_no_le_ha_mandado() {
		acepta2=new Usuario("acepta2.acepta2");
		solicitante=new Usuario("solicitante.solicitante");
		assertFalse(utilidades.comprobarSolicitudes(solicitante, acepta2));
	}
	
	@Then("^Mensaje de error a la aceptacion$")
	public void Mensaje_de_error_a_la_aceptacion() {
		assertFalse(utilidades.comprobarAmistad(solicitante, acepta2));
		assertFalse(utilidades.comprobarAmistad(acepta2,solicitante));
		usuarioDao.delete(acepta2);
	}
}
