package com.intravita.proyectointranet.persistencia;

import java.util.Iterator;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.MongoBroker;
import com.intravita.proyectointranet.persistencia.UsuarioDAO;

/**
 * UsuarioDAOImpl- Implementacion del DAO con los metodos CRUD para los usuarios
 *
 * @author Intravita
 * @since sprint 1
 */

@Component
public class UsuarioDAOImpl implements UsuarioDAO {
	
	public UsuarioDAOImpl() {
		super();
	}
	/**
	 * @method login
	 * @param usuario
	 * @return true si login es correcto, false en caso opuesto
	 */
	public boolean login(Usuario usuario) {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(usuario.getNombre()));
		criterio.append("pwd", new BsonString(DigestUtils.md5Hex(usuario.getClave())));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuarioBson = resultado.first();
		if (usuarioBson==null) {
			return false;
		}
		return true;
	}
	/**
	 * @method selectNombre
	 * @param usuario
	 * @return true si el nombre existe false si no existe
	 */
	public boolean selectNombre(Usuario usuario) {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(usuario.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuarioBson = resultado.first();
		if (usuarioBson==null) {
			return false;
		}
		return true;
	}
	/**
	 * @method insercion de usuarios con y sin encriptar clave
	 * @param usuario
	 * @return true si se ha insertado en la bbdd false en caso opuesto
	 */	
	public void insert (Usuario usuario) throws Exception{
		if(!selectNombre(usuario)) {
			BsonDocument bso = new BsonDocument();
			bso.append("nombre", new BsonString(usuario.getNombre()));
			bso.append("pwd", new BsonString(DigestUtils.md5Hex(usuario.getClave())));
			bso.append("email", new BsonString(usuario.getEmail()));
			bso.append("respuesta", new BsonString(usuario.getRespuesta()));
			MongoBroker broker = MongoBroker.get();
			MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
			usuarios.insertOne(bso);
		}else
			throw new Exception("Cuenta existente");
	}
	public void insertSinEncrypt (Usuario usuario){
		BsonDocument bso = new BsonDocument();
		bso.append("nombre", new BsonString(usuario.getNombre()));
		bso.append("pwd", new BsonString(usuario.getClave()));
		bso.append("email", new BsonString(usuario.getEmail()));
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		FindIterable<BsonDocument> resultado=usuarios.find(bso);
		BsonDocument usuarioBso = resultado.first();
		if (usuarioBso==null) {
			usuarios.insertOne(bso);
		}
	}
	/***
	 * @method select con nombre que devuelve todos los datos del usuario
	 * @param nombre
	 * @return usuario completo
	 */
	public Usuario selectNombre(String nombreParam) {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(nombreParam));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		Usuario result;
		if (usuario==null) {
			return null;
		}
		else {
			BsonValue nombre=usuario.get("nombre");
			BsonString name=nombre.asString();
			String nombreFinal=name.getValue();
			
			BsonValue pwd=usuario.get("pwd");
			BsonString password=pwd.asString();
			String pwdFinal=password.getValue();
			
			BsonValue email=usuario.get("email");
			BsonString correo=email.asString();
			String emailFinal=correo.getValue();
			
			BsonValue respuesta=usuario.get("respuesta");
			BsonString answer=respuesta.asString();
			String respuestaFinal=answer.getValue();
			
			result = new Usuario(nombreFinal, pwdFinal, emailFinal, respuestaFinal);
		}
		return result;
	}
	/***
	 * @method select que devuelve todos los usuarios
	 * @return texto con la lista de usuarios
	 */
	public String list() {
		AdministradorDAOImpl administradorDao= new AdministradorDAOImpl();
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		FindIterable<BsonDocument> resultado=usuarios.find();
		String texto="";
		String nombre;
		BsonDocument usuario;
		Iterator<BsonDocument> lista=resultado.iterator();
		while(lista.hasNext()) {
			usuario=lista.next();
			nombre=usuario.getString("nombre").getValue();
			if(administradorDao.selectNombre(nombre)==null) {
				texto+="<b>Usuario: </b>";
				texto+=nombre;
				texto+=" <b>Email: </b>";
				texto+=usuario.getString("email").getValue();
				texto+="<br>";
			}
		}
		return texto;
	}

	public void delete (Usuario usuario){
		BsonDocument bso = new BsonDocument();
		bso.append("nombre", new BsonString(usuario.getNombre()));

		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		usuarios.deleteOne(bso);

	}
	
	public void update(String nombre, String pwdAntigua, String pwdNueva){

		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(nombre));
		criterio.append("pwd", new BsonString(pwdAntigua));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument("pwd", new BsonString(pwdNueva)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	
	public String selectPwd(String nombre){
		
		BsonValue pwd;
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(nombre));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		pwd=usuario.get("pwd");
		BsonString password=pwd.asString();
		String pwdFinal=password.getValue();
		return pwdFinal;
	}

	public void updatePwdEmail(Usuario usuario) throws Exception{//sera posible reutilizar este metodo para hacer updates
		//preguntar a JA

		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(usuario.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuarioBso = resultado.first();
		if (usuarioBso==null)
			throw new Exception("Fall� la actualizaci�n de los datos del usuario.");

		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument("pwd", new BsonString(DigestUtils.md5Hex(usuario.getClave()))));
		usuarios.findOneAndUpdate(usuarioBso, actualizacion);
	}
	
	
	
}

