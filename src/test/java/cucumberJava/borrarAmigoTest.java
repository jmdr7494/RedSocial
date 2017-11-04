package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class borrarAmigoTest {

	private Usuario borrador, borrado1, borrado2;
	UsuarioDAOImpl usuarioDao= new UsuarioDAOImpl();
	
	@Given("^Usuario conectado para borrar a otro usuario$")
	public void Usuario_conectado_para_borrar_a_otro_usuario() {
		borrador=new Usuario("borrador.borrador", "1234qwer", "borrador@alu.uclm.es", "borrador");
		usuarioDao.delete(borrador);
		try {		
			usuarioDao.insert(borrador);
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@When("^Eliminar un usuario que es tu amigo$")
	public void Eliminar_un_usuario_que_es_tu_amigo() {
		borrador=new Usuario("borrador.borrador");
		borrado1=new Usuario("borrado1.borrado1", "1234qwer", "borrado1@alu.uclm.es", "borrado1");
		usuarioDao.delete(borrado1);
		try {		
			usuarioDao.insert(borrado1);
			utilidades.enviarSolicitud(borrador, borrado1);
			utilidades.aceptarSolicitud(borrador, borrado1);
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@Then("^Borrar amigo de ambos usuarios$")
	public void Borrar_amigo_de_ambos_usuarios() {
		try {
			utilidades.borrarAmistad(borrador, borrado1);
			assertFalse(utilidades.comprobarAmistad(borrador, borrado1));
			assertFalse(utilidades.comprobarAmistad(borrado1, borrador));
		}catch(Exception e) {
			assertFalse(true);
		}
		usuarioDao.delete(borrado1);
	}

	@When("^Eliminar un usuario que no es tu amigo$")
	public void Eliminar_un_usuario_que_no_es_tu_amigo() {
		borrador=new Usuario("borrador.borrador");
		borrado2=new Usuario("borrado2.borrado2", "1234qwer", "borrado2@alu.uclm.es", "borrado2");
		usuarioDao.delete(borrado2);
		try {		
			usuarioDao.insert(borrado2);
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@Then("^Mensaje de error a la revocacion de usuario$")
	public void Mensaje_de_error_a_la_revocacion_de_usuario() {
		try {
			utilidades.borrarAmistad(borrador, borrado2);
		}catch(Exception e) {
			assertTrue(e.getMessage().equals("No puedes eliminar a alguien que no es tu amigo"));
		}
		usuarioDao.delete(borrado2);
	}

}
