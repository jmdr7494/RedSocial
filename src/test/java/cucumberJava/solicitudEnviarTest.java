package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;

public class solicitudEnviarTest {
	
	private Usuario solicitante, solicitado;
	
	private Usuario solicitado3;
	UsuarioDAOImpl usuarioDao= new UsuarioDAOImpl();
	
	
	@Given("^Usuario conectado$")
	public void Usuario_conectado() {
		solicitante=new Usuario("solicitante.solicitante", "1234qwer", "solicitante@alu.uclm.es", "solicitante");
		usuarioDao.delete(solicitante);
		try {
			usuarioDao.insert(solicitante);
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@When("^Selecciona otro usuario sin ser amigos ni tener solicitudes entre ellos$")
	public void Selecciona_otro_usuario_sin_ser_amigos_ni_tener_solicitudes_entre_ellos() {
		solicitante=new Usuario("solicitante.solicitante", "1234qwer", "solicitante@alu.uclm.es", "solicitante");
		solicitado=new Usuario("solicitado.solicitado", "1234qwer", "solicitado@alu.uclm.es", "solicitado");
		try {
			usuarioDao.insert(solicitado);
		} catch (Exception e) {
				assertFalse(true);
		}
		assertFalse(utilidades.comprobarAmistad(solicitante, solicitado));
		assertFalse(utilidades.comprobarAmistad(solicitado, solicitante));
		assertFalse(utilidades.comprobarSolicitudes(solicitante, solicitado));
		assertFalse(utilidades.comprobarSolicitudes(solicitado, solicitante));
	}

	@Then("^Envio solicitud al segundo$")
	public void Envio_solicitud_al_segundo() {
		usuarioDao.enviarSolicitud(solicitante, solicitado);

		assertTrue(utilidades.comprobarSolicitudes(solicitante, solicitado));
		usuarioDao.delete(solicitado);
	}

	@When("^Selecciona a otro usuario siendo su amigo$")
	public void Selecciona_a_otro_usuario_siendo_su_amigo() {
		throw new PendingException();
	}

	@Then("^No envio de solicitud$")
	public void No_envio_de_solicitud() {
	    throw new PendingException();
	}

	@When("^Selecciona a otro usuario teniendo solicitudes pendientes entre ellos$")
	public void Selecciona_a_otro_usuario_teniendo_solicitudes_pendientes_entre_ellos() {
		solicitante=new Usuario("solicitante.solicitante", "1234qwer", "solicitante@alu.uclm.es", "solicitante");
		solicitado3=new Usuario("solicitado3.solicitado3", "1234qwer", "solicitado3@alu.uclm.es", "solicitado3");
		try {
			usuarioDao.insert(solicitado3);
		} catch (Exception e) {
				assertFalse(true);
		}
		usuarioDao.enviarSolicitud(solicitante, solicitado3);
		assertFalse(utilidades.comprobarAmistad(solicitante, solicitado3));
		assertFalse(utilidades.comprobarAmistad(solicitado3, solicitante));
		assertFalse(utilidades.comprobarSolicitudes(solicitado3, solicitante));
	}

	@Then("^Solicitud ya enviada$")
	public void Solicitud_ya_enviada() {
		assertTrue(utilidades.comprobarSolicitudes(solicitante, solicitado3));
		usuarioDao.delete(solicitado3);
	}
}
