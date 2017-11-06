package com.intravita.proyectointranet.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;

import com.intravita.proyectointranet.modelo.Administrador;
import com.intravita.proyectointranet.modelo.Usuario;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

/**
 * AdministradorDAOImpl- Implementacion del DAO de administradores, para realizar funciones CRUD de los administradores
 *
 * @author Intravita
 * @since sprint 1
 */

public class AdministradorDAOImpl {
	
	private final String name = "nombre";	
	private final String contrasena = "pwd";
	private final String e_mail = "email";	
	
	public AdministradorDAOImpl() {
		super();
	}
	public MongoCollection<BsonDocument> obtenerAdministradores() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> administradores = broker.getCollection("Administradores");
		return administradores;
	}
	/***
	 * @method select que devuelve todos los administradores
	 * @return texto con la lista de administradores
	 */
	public List<Administrador> list() {
		MongoCollection<BsonDocument> administradores = obtenerAdministradores();
		FindIterable<BsonDocument> resultado=administradores.find();
		String nombre;
		BsonDocument admin;
		Iterator<BsonDocument> lista=resultado.iterator();
		List<Administrador> retorno=new ArrayList<Administrador>();
		while(lista.hasNext()) {
			admin=lista.next();
			nombre=admin.getString(name).getValue();
			if(!nombre.equals("admin"))retorno.add(new Administrador(nombre));
		}
		return retorno;	
	}
	/**
	 * @method login
	 * @param administrador
	 * @return true si login es correcto, false en caso opuesto
	 */
	public boolean login(Administrador administrador) {
		
		MongoCollection<BsonDocument> administradores = obtenerAdministradores();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(administrador.getNombre()));
		criterio.append(contrasena, new BsonString(DigestUtils.md5Hex(administrador.getClave())));
		FindIterable<BsonDocument> resultado=administradores.find(criterio);
		BsonDocument administradorBson = resultado.first();
		if (administradorBson==null) {
			return false;
		}
		return true;
	}
	
	/***
	 * 
	 * 
	 * @param administrador
	 * @method metodos de insercion con y sin encriptar
	 */
	 public void insert (Administrador administrador) {
		  BsonDocument bso = new BsonDocument();
		  bso.append(name, new BsonString(administrador.getNombre()));
		  bso.append(contrasena, new BsonString(DigestUtils.md5Hex(administrador.getClave())));
		  bso.append(e_mail, new BsonString(administrador.getEmail()));
		  
		  MongoCollection<BsonDocument> administradores = obtenerAdministradores();
		  FindIterable<BsonDocument> resultado=administradores.find(bso);
		  BsonDocument usuarioBso = resultado.first();
		  if (usuarioBso==null) {
		   administradores.insertOne(bso);
		  }
	 }
	
	 public void insertSinEncrypt (Administrador administrador) {
		  BsonDocument bso = new BsonDocument();
		  bso.append(name, new BsonString(administrador.getNombre()));
		  bso.append(contrasena, new BsonString(administrador.getClave()));
		  bso.append(e_mail, new BsonString(administrador.getEmail()));
		  
		  MongoCollection<BsonDocument> administradores = obtenerAdministradores();
		  FindIterable<BsonDocument> resultado=administradores.find(bso);
		  BsonDocument usuarioBso = resultado.first();
		  if (usuarioBso==null) {
		   administradores.insertOne(bso);
		  }
	 }
	 /***
		 * @method select con nombre que devuelve todos los datos del administrador
		 * @param nombre
		 * @return admin completo
		 */
		public Administrador selectNombre(String nombreParam) {
			
			MongoCollection<BsonDocument> administradores = obtenerAdministradores();
			BsonDocument criterio = new BsonDocument();
			criterio.append(name, new BsonString(nombreParam));
			FindIterable<BsonDocument> resultado=administradores.find(criterio);
			BsonDocument administrador = resultado.first();
			Administrador result;
			if (administrador==null) {
				return null;
			}
			else {
				BsonValue nombre=administrador.get(name);
				BsonString name=nombre.asString();
				String nombreFinal=name.getValue();
				
				BsonValue pwd=administrador.get(contrasena);
				BsonString password=pwd.asString();
				String pwdFinal=password.getValue();
				
				BsonValue email=administrador.get(e_mail);
				BsonString correo=email.asString();
				String emailFinal=correo.getValue();
				result = new Administrador(nombreFinal, pwdFinal, emailFinal);
			}
			return result;
		}
	 public Administrador select(Administrador generico) {
		  
		  MongoCollection<BsonDocument> administradores = obtenerAdministradores();
		  BsonDocument criterio = new BsonDocument();
		  criterio.append(name, new BsonString(generico.getNombre()));
		  criterio.append(contrasena, new BsonString(DigestUtils.md5Hex(generico.getClave())));
		  FindIterable<BsonDocument> resultado=administradores.find(criterio);
		  BsonDocument usuario = resultado.first();
		  Administrador result;
		  if (usuario==null) {
			  result=new Administrador("-","-");
		  }
		  else {
			  result = new Administrador(generico.getNombre(),generico.getClave());
		  }
		  return result;
	 }
	 public void delete (Administrador administrador) {
		  BsonDocument bso = new BsonDocument();
		  bso.append(name, new BsonString(administrador.getNombre()));
		  
		  MongoCollection<BsonDocument> administradores = obtenerAdministradores();
		  administradores.deleteOne(bso);
	 }
	 public void update(String nombre, String pwdAntigua, String pwdNueva) {
		  
		  MongoCollection<BsonDocument> administradores = obtenerAdministradores();
		  BsonDocument criterio = new BsonDocument();
		  criterio.append(name, new BsonString(nombre));
		  criterio.append(contrasena, new BsonString(pwdAntigua));
		  FindIterable<BsonDocument> resultado=administradores.find(criterio);
		  BsonDocument administrador = resultado.first();
		  BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(contrasena, new BsonString(pwdNueva)));
		  administradores.findOneAndUpdate(administrador, actualizacion);
	 }
	 



}