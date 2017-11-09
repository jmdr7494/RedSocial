package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class consultaMeGustaTest {
	
	PublicacionDAOImpl publiDao= new PublicacionDAOImpl();
	Usuario usuario, usuario2, usuario3;
	Publicacion publicacion, aux;
	
	@Given("^Usuario conectado consulta usuarios que han dado me gusta$")
	public void Usuario_conectado_consulta_usuarios_que_han_dado_me_gusta() {
		usuario=new Usuario("milhouse.milhouse");
		usuario2=new Usuario("miguel.miguel");
		usuario3=new Usuario("daniel.gomez");
		publicacion = new Publicacion(usuario2, "Probando me gusta 0");
		publiDao.insert(publicacion);
	}

	@When("^Activacion correcta la consulta$")
	public void Activacion_correcta_la_consulta() {
		aux=publiDao.selectOne(publicacion);
		
		publiDao.megusta(aux.getId(), usuario);
		publiDao.megusta(aux.getId(), usuario2);
		publiDao.megusta(aux.getId(), usuario3);
		
		ArrayList<String> datos = publiDao.usuariosMeGusta(aux);
		System.out.println(datos);
		
	}

	@Then("^Se muestra correctamente lo usuarios que han dado me gusta$")
	public void Se_muestra_correctamente_lo_usuarios_que_han_dado_me_gusta() {
		assertNotNull(publiDao.usuariosMeGusta(aux));
		
		publiDao.remove(aux.getId());
	}

}
