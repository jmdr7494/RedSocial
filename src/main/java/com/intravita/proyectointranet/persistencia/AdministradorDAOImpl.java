package com.intravita.proyectointranet.persistencia;

import java.util.Iterator;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;

import com.intravita.proyectointranet.modelo.Administrador;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

/**
 * AdministradorDAOImpl- Implementacion del DAO de administradores, para realizar funciones CRUD de los administradores
 *
 * @author Intravita
 * @since sprint 1
 */

public class AdministradorDAOImpl {
	
	public AdministradorDAOImpl() {
		super();
	}
	/***
	 * @method select que devuelve todos los administradores
	 * @return texto con la lista de administradores
	 */
	public String list() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> administradores = broker.getCollection("Administradores");
		FindIterable<BsonDocument> resultado=administradores.find();
		String texto="";
		BsonDocument administrador;
		Iterator<BsonDocument> lista=resultado.iterator();
		while(lista.hasNext()) {
			texto+="<b>Usuario:</b> ";
			administrador=lista.next();
			texto+=administrador.getString("nombre").getValue();
			texto+=" <b>Email: </b>";
			texto+=administrador.getString("email").getValue();
			texto+="<br>";
		}
		return texto;
	}
	/**
	 * @method login
	 * @param administrador
	 * @return true si login es correcto, false en caso opuesto
	 */
	public boolean login(Administrador administrador) {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> administradores = broker.getCollection("Administradores");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(administrador.getNombre()));
		criterio.append("pwd", new BsonString(DigestUtils.md5Hex(administrador.getClave())));
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
		  bso.append("nombre", new BsonString(administrador.getNombre()));
		  bso.append("pwd", new BsonString(DigestUtils.md5Hex(administrador.getClave())));
		  bso.append("email", new BsonString(administrador.getEmail()));
		  MongoBroker broker = MongoBroker.get();
		  MongoCollection<BsonDocument> administradores = broker.getCollection("Administradores");
		  FindIterable<BsonDocument> resultado=administradores.find(bso);
		  BsonDocument usuarioBso = resultado.first();
		  if (usuarioBso==null) {
		   administradores.insertOne(bso);
		  }
	 }
	
	 public void insertSinEncrypt (Administrador administrador) {
		  BsonDocument bso = new BsonDocument();
		  bso.append("nombre", new BsonString(administrador.getNombre()));
		  bso.append("pwd", new BsonString(administrador.getClave()));
		  bso.append("email", new BsonString(administrador.getEmail()));
		  MongoBroker broker = MongoBroker.get();
		  MongoCollection<BsonDocument> administradores = broker.getCollection("Administradores");
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
			MongoBroker broker = MongoBroker.get();
			MongoCollection<BsonDocument> administradores = broker.getCollection("Administradores");
			BsonDocument criterio = new BsonDocument();
			criterio.append("nombre", new BsonString(nombreParam));
			FindIterable<BsonDocument> resultado=administradores.find(criterio);
			BsonDocument administrador = resultado.first();
			Administrador result;
			if (administrador==null) {
				return null;
			}
			else {
				BsonValue nombre=administrador.get("nombre");
				BsonString name=nombre.asString();
				String nombreFinal=name.getValue();
				
				BsonValue pwd=administrador.get("pwd");
				BsonString password=pwd.asString();
				String pwdFinal=password.getValue();
				
				BsonValue email=administrador.get("email");
				BsonString correo=email.asString();
				String emailFinal=correo.getValue();
				result = new Administrador(nombreFinal, pwdFinal, emailFinal);
			}
			return result;
		}
	 public Administrador select(Administrador generico) {
		  MongoBroker broker = MongoBroker.get();
		  MongoCollection<BsonDocument> administradores = broker.getCollection("Administradores");
		  BsonDocument criterio = new BsonDocument();
		  criterio.append("nombre", new BsonString(generico.getNombre()));
		  criterio.append("pwd", new BsonString(DigestUtils.md5Hex(generico.getClave())));
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
		  bso.append("nombre", new BsonString(administrador.getNombre()));
		  MongoBroker broker = MongoBroker.get();
		  MongoCollection<BsonDocument> administradores = broker.getCollection("Administradores");
		  administradores.deleteOne(bso);
	 }
	 public void update(String nombre, String pwdAntigua, String pwdNueva) {
		  MongoBroker broker = MongoBroker.get();
		  MongoCollection<BsonDocument> administradores = broker.getCollection("Administradores");
		  BsonDocument criterio = new BsonDocument();
		  criterio.append("nombre", new BsonString(nombre));
		  criterio.append("pwd", new BsonString(pwdAntigua));
		  FindIterable<BsonDocument> resultado=administradores.find(criterio);
		  BsonDocument administrador = resultado.first();
		  BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument("pwd", new BsonString(pwdNueva)));
		  administradores.findOneAndUpdate(administrador, actualizacion);
	 }
	 



}