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
	
	private final String name = "nombre";
	private final String password = "pwd";
	private final String e_mail = "email";
	private final String resp = "respuesta";
	
	public UsuarioDAOImpl() {
		super();
	}
	
	public MongoCollection<BsonDocument> obtenerUsuarios() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		return usuarios;
	}
	/**
	 * @method login
	 * @param usuario
	 * @return true si login es correcto, false en caso opuesto
	 */
	public boolean login(Usuario usuario) {
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(usuario.getNombre()));
		criterio.append(password, new BsonString(DigestUtils.md5Hex(usuario.getClave())));
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
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(usuario.getNombre()));
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
			bso.append(name, new BsonString(usuario.getNombre()));
			bso.append(password, new BsonString(DigestUtils.md5Hex(usuario.getClave())));
			bso.append(e_mail, new BsonString(usuario.getEmail()));
			bso.append(resp, new BsonString(usuario.getRespuesta()));
			
			MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
			usuarios.insertOne(bso);
		}else
			throw new Exception("Cuenta existente");
	}
	public void insertSinEncrypt (Usuario usuario){
		BsonDocument bso = new BsonDocument();
		bso.append(name, new BsonString(usuario.getNombre()));
		bso.append(password, new BsonString(usuario.getClave()));
		bso.append(e_mail, new BsonString(usuario.getEmail()));
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
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
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(nombreParam));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		Usuario result;
		if (usuario==null) {
			return null;
		}
		else {
			BsonValue nombre=usuario.get(name);
			BsonString name=nombre.asString();
			String nombreFinal=name.getValue();
			
			BsonValue pwd=usuario.get(password);
			BsonString password=pwd.asString();
			String pwdFinal=password.getValue();
			
			BsonValue email=usuario.get(e_mail);
			BsonString correo=email.asString();
			String emailFinal=correo.getValue();
			
			BsonValue respuesta=usuario.get(resp);
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
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		FindIterable<BsonDocument> resultado=usuarios.find();
		String texto="";
		String nombre;
		BsonDocument usuario;
		Iterator<BsonDocument> lista=resultado.iterator();
		while(lista.hasNext()) {
			usuario=lista.next();
			nombre=usuario.getString(name).getValue();
			if(administradorDao.selectNombre(nombre)==null) {
				texto = texto +"<b>Usuario: </b>";
				texto = texto +nombre;
				texto = texto +" <b>Email: </b>";
				texto = texto +usuario.getString(e_mail).getValue();
				texto = texto +"<br>";
			}
		}
		return texto;
	}

	public void delete (Usuario usuario){
		BsonDocument bso = new BsonDocument();
		bso.append(name, new BsonString(usuario.getNombre()));

		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		usuarios.deleteOne(bso);

	}
	
	public void update(String nombre, String pwdAntigua, String pwdNueva){

		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(nombre));
		criterio.append(password, new BsonString(pwdAntigua));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(password, new BsonString(pwdNueva)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	
	public String selectPwd(String nombre){
		
		BsonValue pwd;
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(nombre));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		pwd=usuario.get(password);
		BsonString password=pwd.asString();
		String pwdFinal=password.getValue();
		return pwdFinal;
	}

	public void updatePwdEmail(Usuario usuario) throws Exception{//sera posible reutilizar este metodo para hacer updates
		//preguntar a JA

		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(usuario.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuarioBso = resultado.first();
		if (usuarioBso==null)
			throw new Exception("Fall� la actualizaci�n de los datos del usuario.");

		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(password, new BsonString(DigestUtils.md5Hex(usuario.getClave()))));
		usuarios.findOneAndUpdate(usuarioBso, actualizacion);
	}
	
	
	
}

