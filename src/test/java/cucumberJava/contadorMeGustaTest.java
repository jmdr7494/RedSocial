package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class contadorMeGustaTest {
	
	PublicacionDAOImpl publiDao= new PublicacionDAOImpl();
	Usuario usuario, usuario2, usuario3;
	Publicacion publicacion, aux;
	
	
	@Given("^Usuario conectado quiere dar me gusta a una publicacion$")
	public void Usuario_conectado_quiere_dar_me_gusta_a_una_publicacion() {
		usuario=new Usuario("milhouse.milhouse");
		usuario2=new Usuario("miguel.miguel");
		usuario3=new Usuario("daniel.gomez");
		publicacion = new Publicacion(usuario2, "Probando me gusta 0");
		publiDao.insert(publicacion);
	}

	@When("^Activacion correcta me gusta$")
	public void Activacion_correcta_me_gusta() {		
		aux=publiDao.selectOne(publicacion);
		
		publiDao.megusta(aux.getId(), usuario);
		publiDao.megusta(aux.getId(), usuario2);
		publiDao.megusta(aux.getId(), usuario3);		
		
	}

	@Then("^Publicacion pasa a tener un me gusta mas$")
	public void Publicacion_pasa_a_tener_un_me_gusta_mas() {	
		
		assertTrue(publiDao.selectOne(aux).getMeGustaCont()==3);
		
		publiDao.remove(aux.getId());
	    
	}

	@Given("^usuario conectado quiere eliminar me gusta de una publicacion$")
	public void usuario_conectado_quiere_eliminar_me_gusta_de_una_publicacion() {
		usuario=new Usuario("milhouse.milhouse");
		usuario2=new Usuario("miguel.miguel");
		publicacion = new Publicacion(usuario2, "Probando me gusta y ya no me gusta 0");
		publiDao.insert(publicacion);
	}

	@When("^Desactivacion correcta me gusta$")
	public void Desactivacion_correcta_me_gusta() {
		aux=publiDao.selectOne(publicacion);	
		
		publiDao.megusta(aux.getId(), usuario);
		publiDao.megusta(aux.getId(), usuario2);
		
		publiDao.yanomegusta(aux.getId(), usuario2);
	}

	@Then("^Publicacion pasa a tener un me gusta menos$")
	public void Publicacion_pasa_a_tener_un_me_gusta_menos() {
		
		assertTrue(publiDao.selectOne(aux).getMeGustaCont()==1);
		
		publiDao.remove(aux.getId());
	}

}
