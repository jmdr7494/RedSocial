package com.intravita.proyectointranet.persistencia;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.BsonArray;
import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;

/**
 * PublicacionDAOImpl- Implementacion del DAO de las publicaciones con los metodos CRUD
 *
 * @author Intravita
 * @since sprint 2
 */

public class PublicacionDAOImpl {	
	
	private final String ID = "_id";
	private final String text = "texto";
	private final String author = "autor";
	private final String date = "fecha";
	private final String privacy = "privacidad";
	private final String megustaCont = "megustaCont";
	private final String megustaUsuarios = "megustaUsuarios";
	
	public MongoCollection<BsonDocument> obtenerPublicaciones() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> publicaciones = broker.getCollection("Publicaciones");
		return publicaciones;
	}


	public boolean existe(Publicacion publicacion) {
		
		MongoCollection<BsonDocument> usuarios = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		if(publicacion.getId()!=null)
			criterio.append(ID, new BsonObjectId(new ObjectId(publicacion.getId())));
		else {
			criterio.append(author, new BsonString(publicacion.getUsuario().getNombre()));
			criterio.append(text, new BsonString(publicacion.getTexto()));
		}
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuarioBson = resultado.first();
		if (usuarioBson==null) {
			return false;
		}
		return true;
	}
	/***
	 * 
	 * @method insertar una publicacion en la base de datos
	 * 
	 */
	public void insert(Publicacion publicacion) {
		BsonDocument bso = new BsonDocument();
		bso.append(author, new BsonString(publicacion.getUsuario().getNombre()));
		bso.append(text, new BsonString(publicacion.getTexto()));
		bso.append(privacy, new BsonString(publicacion.getPrivacidad()));
		bso.append(date, new BsonDateTime(publicacion.getFecha()));
		bso.append(megustaCont, new BsonInt32(publicacion.getMeGustaCont()));
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		publicaciones.insertOne(bso);
	}
	/***
	 * 
	 * @method incrementar "me gusta" en publicacion
	 * 
	 */
	public void megusta(String id, Usuario usuario) {
		BsonDocument bso = new BsonDocument();
		bso.append(ID, new BsonObjectId(new ObjectId(id)));
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument actualizacion= new BsonDocument("$inc", new BsonDocument("megustaCont", new BsonInt32(1)));
		BsonDocument actualizacion2 = new BsonDocument("$addToSet",new BsonDocument(megustaUsuarios, new BsonString(usuario.getNombre())));
		publicaciones.updateOne(bso,actualizacion);
		publicaciones.updateOne(bso,actualizacion2);
	}
	/***
	 * 
	 * @method decrementar el "me gusta" en publicacion
	 * 
	 */
	public void yanomegusta(String id, Usuario usuario) {
		BsonDocument bso = new BsonDocument();
		bso.append(ID, new BsonObjectId(new ObjectId(id)));
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument actualizacion= new BsonDocument("$inc", new BsonDocument("megustaCont", new BsonInt32(-1)));
		BsonDocument actualizacion2 = new BsonDocument("$pull",new BsonDocument(megustaUsuarios, new BsonString(usuario.getNombre())));
		publicaciones.updateOne(bso,actualizacion);
		publicaciones.updateOne(bso,actualizacion2);
	}
	/***
	 * 
	 * @method consulta de los usuarios que han dado me gusta
	 * 
	 */
	public ArrayList<String> usuariosMeGusta(Publicacion publi){
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		if(publi.getId()!=null)
			criterio.append(ID, new BsonObjectId(new ObjectId(publi.getId())));
		else {
			criterio.append(author, new BsonString(publi.getUsuario().getNombre()));
			criterio.append(text, new BsonString(publi.getTexto()));			
		}
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio);
		BsonDocument aux = resultado.first();
		
		//System.out.println("Usuarios: "+aux);		
		//System.out.println("course name = " + aux.getArray(megustaUsuarios));		
		
		BsonArray mgUsuariosBson=aux.getArray(megustaUsuarios);		
		ArrayList<String> mgUsuarios = new ArrayList<String>();
		for(BsonValue usuarioBson : mgUsuariosBson) {			
			BsonString usuarioString=usuarioBson.asString();
			String usuario=usuarioString.getValue();
			mgUsuarios.add(usuario);
        }
		
		
		return mgUsuarios;
		
	}
	/***
	 * 
	 * @method actualizar una publicacion en la base de datos
	 * 
	 */
	public void update(String id, String textoNuevo){		
		BsonDocument bso = new BsonDocument();
		bso.append(ID, new BsonObjectId(new ObjectId(id)));
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(text, new BsonString(textoNuevo)));
		publicaciones.updateOne(bso,actualizacion);
	}
	/***
	 * 
	 * @method eliminar una publicacion en la base de datos
	 * 
	 */
	public void remove(String id){
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument bso = new BsonDocument();
		bso.append(ID, new BsonObjectId(new ObjectId(id)));
		FindIterable<BsonDocument> resultado=publicaciones.find(bso);
		BsonDocument publicacionBson = resultado.first();		
		publicaciones.deleteOne(publicacionBson);
	}
	/**
	 * 
	 * @param usuario del que queremos obtener publicaciones publicas
	 * @return lista de publicaciones
	 */
	public Publicacion selectOne(Publicacion publi) {
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		if(publi.getId()!=null)
			criterio.append(ID, new BsonObjectId(new ObjectId(publi.getId())));
		else {
			criterio.append(author, new BsonString(publi.getUsuario().getNombre()));
			criterio.append(text, new BsonString(publi.getTexto()));			
		}
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio);
		BsonDocument aux = resultado.first();
		String autor=aux.getString(author).getValue();
		String texto=aux.getString(text).getValue();
		String privacidad=aux.getString(privacy).getValue();
		long fecha=aux.getDateTime(date).getValue();
		int mg=aux.getInt32(megustaCont).getValue();

		
		Publicacion publicacion=new Publicacion(new Usuario(autor), texto, privacidad, fecha, mg);
		publicacion.setId(aux.getObjectId(ID).getValue().toString());
		return publicacion;
	}
	
	/**
	 * 
	 * @param usuario del que queremos obtener publicaciones publicas
	 * @return lista de publicaciones
	 */
	public ArrayList<Publicacion> selectPublicas(Usuario usuario) {
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		criterio.append(author, new BsonString(usuario.getNombre()));
		criterio.append(privacy,  new BsonString("Publica"));
		Bson sort = Sorts.descending(date);
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio).sort(sort);
		Iterator <BsonDocument> bucle= resultado.iterator();
		ArrayList<Publicacion> lista= new ArrayList<Publicacion>();
		BsonDocument aux;
		String autor;
		String texto;
		String privacidad;
		long fecha;
		Publicacion publicacion;
		while(bucle.hasNext()) {
			aux=bucle.next();
			autor=aux.getString(author).getValue();
			texto=aux.getString(text).getValue();
			privacidad=aux.getString(privacy).getValue();
			fecha=aux.getDateTime(date).getValue();
			publicacion=new Publicacion(new Usuario(autor), texto, privacidad, fecha);
			publicacion.setId(aux.getObjectId(ID).getValue().toString());
			lista.add(publicacion);
		}
		return lista;
	}
	/**
	 * 
	 * @param usuario del que queremos obtener publicaciones privadas
	 * @return lista de publicaciones
	 */
	public ArrayList<Publicacion> selectPrivadas(Usuario usuario) {
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		criterio.append(author, new BsonString(usuario.getNombre()));
		criterio.append(privacy,  new BsonString("Privada"));
		Bson sort = Sorts.descending(date);
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio).sort(sort);
		Iterator <BsonDocument> bucle= resultado.iterator();
		ArrayList<Publicacion> lista= new ArrayList<Publicacion>();
		BsonDocument aux;
		String autor;
		String texto;
		String privacidad;
		long fecha;
		Publicacion publicacion;
		while(bucle.hasNext()) {
			aux=bucle.next();
			autor=aux.getString(author).getValue();
			texto=aux.getString(text).getValue();
			privacidad=aux.getString(privacy).getValue();
			fecha=aux.getDateTime(date).getValue();
			publicacion=new Publicacion(new Usuario(autor), texto, privacidad, fecha);
			publicacion.setId(aux.getObjectId(ID).getValue().toString());
			lista.add(publicacion);
		}
		return lista;
	}
	/**
	 * 
	 * @param usuario
	 * @result todas las publicaciones del usuario a borrar se iran a un usuario llamado Papelera
	 */
	public void borradoUsuario(Usuario usuario) {
		BsonDocument bso = new BsonDocument();
		bso.append(author, new BsonString(usuario.getNombre()));
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(author, new BsonString("Papelera")));
		publicaciones.updateMany(bso,actualizacion);		
	}
}
