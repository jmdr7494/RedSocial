package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class solicitudEnviarTest {
	
	private Usuario solicitante, solicitado, solicitado2, solicitado3;
	UsuarioDAOImpl usuarioDao= new UsuarioDAOImpl();
	
	
	@Given("^Usuario conectado para enviar solicitud$")
	public void Usuario_conectado_para_enviar_solicitud() {
		solicitante=new Usuario("solicitante.solicitante", "1234qwer", "solicitante@alu.uclm.es", "solicitante");
		if(usuarioDao.selectNombre(solicitante))usuarioDao.delete(solicitante);
		try {
			usuarioDao.insert(solicitante);
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@When("^Selecciona otro usuario sin ser amigos ni tener solicitudes entre ellos$")
	public void Selecciona_otro_usuario_sin_ser_amigos_ni_tener_solicitudes_entre_ellos() {
		solicitante=new Usuario("solicitante.solicitante");
		solicitado=new Usuario("solicitado.solicitado", "1234qwer", "solicitado@alu.uclm.es", "solicitado");
		try {
			if(usuarioDao.selectNombre(solicitado))usuarioDao.delete(solicitado);
			usuarioDao.insert(solicitado);
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@Then("^Envio solicitud al segundo$")
	public void Envio_solicitud_al_segundo() {
		try {
			utilidades.enviarSolicitud(solicitante, solicitado);
		} catch (Exception e) {
			assertFalse(true);
		}

		assertTrue(utilidades.comprobarSolicitudes(solicitante, solicitado));
		usuarioDao.delete(solicitado);
	}

	@When("^Selecciona a otro usuario siendo su amigo$")
	public void Selecciona_a_otro_usuario_siendo_su_amigo() {
		solicitante=new Usuario("solicitante.solicitante");
		solicitado2=new Usuario("solicitado2.solicitado2", "1234qwer", "solicitado2@alu.uclm.es", "solicitado2");
		try {
			if(usuarioDao.selectNombre(solicitado2))usuarioDao.delete(solicitado2);
			usuarioDao.insert(solicitado2);
			utilidades.enviarSolicitud(solicitante, solicitado2);
			utilidades.aceptarSolicitud(solicitante, solicitado2);
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@Then("^No envio de solicitud$")
	public void No_envio_de_solicitud() {
		try {
			utilidades.enviarSolicitud(solicitante, solicitado2);
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Ya sois amigos"));
		}
	    usuarioDao.delete(solicitado2);
	}

	@When("^Selecciona a otro usuario teniendo solicitudes pendientes entre ellos$")
	public void Selecciona_a_otro_usuario_teniendo_solicitudes_pendientes_entre_ellos() {
		solicitante=new Usuario("solicitante.solicitante");
		solicitado3=new Usuario("solicitado3.solicitado3", "1234qwer", "solicitado3@alu.uclm.es", "solicitado3");
		try {
			if(usuarioDao.selectNombre(solicitado3))usuarioDao.delete(solicitado3);
			usuarioDao.insert(solicitado3);
			utilidades.enviarSolicitud(solicitante, solicitado3);
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@Then("^Solicitud ya enviada$")
	public void Solicitud_ya_enviada() {
		try {
			utilidades.enviarSolicitud(solicitante, solicitado3);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Ya has enviado una solicitud a ese usuario."));
		}
		usuarioDao.delete(solicitado3);
	}
}
