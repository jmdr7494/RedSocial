package com.intravita.proyectointranet.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.BsonArray;
import org.bson.BsonBinary;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.gridfs.GridFS;
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
	private final String contrasena = "pwd";
	private final String e_mail = "email";
	private final String resp = "respuesta";
	private final String amigos= "amigos";
	private final String solicitudes= "solicitudes";
	
	//Para imagenes
	private final String nameImage="nombreImagen";
	private final String image="imagen";
	
	
	PublicacionDAOImpl publicacionDao= new PublicacionDAOImpl();
	AdministradorDAOImpl administradorDao= new AdministradorDAOImpl();
	
	public UsuarioDAOImpl() {
		super();
	}
	
	public MongoCollection<BsonDocument> obtenerUsuarios() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		return usuarios;
	}
	
	/*public MongoCollection<BsonDocument> obtenerUsuariosImagen() {
		MongoBroker broker = MongoBroker.get();
		GridFS fs = new GridFS();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		return usuarios;*/
	/*}
	/**
	 * @method login
	 * @param usuario
	 * @return true si login es correcto, false en caso opuesto
	 */
	public boolean login(Usuario usuario) {
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(usuario.getNombre()));
		criterio.append(contrasena, new BsonString(DigestUtils.md5Hex(usuario.getClave())));
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
	
	
	//Para imagen
	public void insertConImagen (Usuario usuario) throws Exception{
		if(!selectNombre(usuario)) {
			BsonDocument bso = new BsonDocument();
			bso.append(name, new BsonString(usuario.getNombre()));
			bso.append(contrasena, new BsonString(DigestUtils.md5Hex(usuario.getClave())));
			bso.append(e_mail, new BsonString(usuario.getEmail()));
			bso.append(resp, new BsonString(usuario.getRespuesta()));
			bso.append(solicitudes, new BsonArray());
			bso.append(amigos, new BsonArray());
			bso.append(nameImage, new BsonString("avatar"));
			System.out.println("es por el binario?");
			//String base64Encoded = DatatypeConverter.printBase64Binary(usuario.getImagen());
			//bso.append(image, new BsonString(base64Encoded));
			
			bso.append(image, new BsonBinary(usuario.getImagen()));
			System.out.println("no es por el binario?");
			MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
			usuarios.insertOne(bso);
		}//else
			//throw new Exception("Cuenta existente");
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
			bso.append(contrasena, new BsonString(DigestUtils.md5Hex(usuario.getClave())));
			bso.append(e_mail, new BsonString(usuario.getEmail()));
			bso.append(resp, new BsonString(usuario.getRespuesta()));
			bso.append(solicitudes, new BsonArray());
			bso.append(amigos, new BsonArray());
			MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
			usuarios.insertOne(bso);
		}else
			throw new Exception("Cuenta existente");
	}
	public void insertSinEncrypt (Usuario usuario){
		BsonDocument bso = new BsonDocument();
		bso.append(name, new BsonString(usuario.getNombre()));
		bso.append(contrasena, new BsonString(usuario.getClave()));
		bso.append(e_mail, new BsonString(usuario.getEmail()));
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		FindIterable<BsonDocument> resultado=usuarios.find(bso);
		BsonDocument usuarioBso = resultado.first();
		if (usuarioBso==null) {
			usuarios.insertOne(bso);
		}
	}
	
	//para imagen
	
	/***
	 * @method select con nombre que devuelve todos los datos del usuario
	 * @param nombre
	 * @return usuario completo
	 */
	
/*public boolean selectNombreImagen(Usuario usuario) {
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(usuario.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuarioBson = resultado.first();
		if (usuarioBson==null) {
			return false;
		}
		return true;
	}*/
	
	public Usuario selectNombreImagen(String nombreParam) {
		
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
			
			BsonValue pwd=usuario.get(contrasena);
			BsonString password=pwd.asString();
			String pwdFinal=password.getValue();
			
			BsonValue email=usuario.get(e_mail);
			BsonString correo=email.asString();
			String emailFinal=correo.getValue();
			
			BsonValue respuesta=usuario.get(resp);
			BsonString answer=respuesta.asString();
			String respuestaFinal=answer.getValue();
			
			
			//CAMBIO
			BsonValue nombreImagen=usuario.get(nameImage);
			BsonString nameImage=nombreImagen.asString();
			String nombreImagenFinal=nameImage.getValue();
			
			BsonValue imagen=usuario.get(image);
			BsonBinary image=imagen.asBinary();
			byte[]imagenFinal=image.getData();
						
			result = new Usuario(nombreFinal, pwdFinal, emailFinal, respuestaFinal,nombreImagenFinal,imagenFinal);
		}
		return result;
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
			
			BsonValue pwd=usuario.get(contrasena);
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
	 * @method select que devuelve todos los usuarios que no son admins
	 * @return la lista de usuarios
	 */
	public List<Usuario> list() {
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		FindIterable<BsonDocument> resultado=usuarios.find();
		String nombre;
		BsonDocument usuario;
		Iterator<BsonDocument> lista=resultado.iterator();
		List<Usuario> retorno=new ArrayList<Usuario>();
		while(lista.hasNext()) {
			usuario=lista.next();
			nombre=usuario.getString(name).getValue();
			if(administradorDao.selectNombre(nombre)==null)retorno.add(new Usuario(nombre));
		}
		return retorno;
	}
	/***
	 * @method select que devuelve los nombres de todos los usuarios
	 * @return la lista de usuarios
	 */
	public List<Usuario> selectAll() {
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		FindIterable<BsonDocument> resultado=usuarios.find();
		String nombre;
		BsonDocument usuario;
		Iterator<BsonDocument> lista=resultado.iterator();
		List<Usuario> retorno=new ArrayList<Usuario>();
		while(lista.hasNext()) {
			usuario=lista.next();
			nombre=usuario.getString(name).getValue();
			retorno.add(new Usuario(nombre));
		}
		return retorno;
	}
	/**
	 * @method borra todas las relaciones de amistad, solicitudes enviadas, publicaciones y al propio usuario
	 * 
	 */
	public void delete (Usuario usuario){
		List<Usuario> todos=selectAll();
		Iterator <Usuario> it2=todos.iterator();
		Usuario aux;
		while(it2.hasNext()) {
			aux=it2.next();
			borrarAmistad(usuario,aux);
			rechazarSolicitud(usuario, aux);	
		}
		BsonDocument bso = new BsonDocument();
		bso.append(name, new BsonString(usuario.getNombre()));
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		usuarios.deleteOne(bso);
		publicacionDao.borradoUsuario(usuario);
	}
	public void updateNombre(String antiguo, String nuevo) {
		List <Usuario> todos=selectAll();
		BsonString nombreAnt=new BsonString(antiguo);
		BsonString nombreNue=new BsonString(nuevo);
		Iterator <Usuario> it=todos.iterator();
		Usuario aux;
		List <BsonValue> Listasolicitudes;
		List <BsonValue> Listaamigos;
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		FindIterable<BsonDocument> resultado;
		BsonDocument usuario;
		BsonDocument actualizacion;
		while(it.hasNext()) {
			aux=it.next();
			criterio.append(name, new BsonString(aux.getNombre()));
			resultado=usuarios.find(criterio);
			usuario = resultado.first();
			
			Listasolicitudes=obtenerSolicitudes(aux);
			if(Listasolicitudes.contains(nombreAnt)) {
				Listasolicitudes.remove(nombreAnt);
				Listasolicitudes.add(nombreNue);
				actualizacion= new BsonDocument("$set", new BsonDocument(solicitudes, new BsonArray(Listasolicitudes)));
				usuarios.findOneAndUpdate(usuario, actualizacion);
			}else {
				Listaamigos=obtenerAmigos(aux);
				if(Listaamigos.contains(nombreAnt)) {
					Listaamigos.remove(nombreAnt);
					Listaamigos.add(nombreNue);
					actualizacion= new BsonDocument("$set", new BsonDocument(amigos, new BsonArray(Listaamigos)));
					usuarios.findOneAndUpdate(usuario, actualizacion);
				}
			}
		}
		usuarios = obtenerUsuarios();
		criterio = new BsonDocument();
		criterio.append(name, nombreAnt);
		resultado=usuarios.find(criterio);
		usuario = resultado.first();
		actualizacion= new BsonDocument("$set", new BsonDocument(name, nombreNue));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	public void update(String nombre, String pwdAntigua, String pwdNueva){

		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(nombre));
		criterio.append(contrasena, new BsonString(pwdAntigua));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(contrasena, new BsonString(pwdNueva)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	

	public void updatePwd(Usuario usuario) throws Exception{//sera posible reutilizar este metodo para hacer updates
		//preguntar a JA
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(usuario.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuarioBso = resultado.first();
		if (usuarioBso==null)
			throw new Exception("Fallo la actualizacion de los datos del usuario.");

		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(contrasena, new BsonString(DigestUtils.md5Hex(usuario.getClave()))));
		usuarios.findOneAndUpdate(usuarioBso, actualizacion);
	}

	
	public Usuario selectPwd (String pwdA) {
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(contrasena, new BsonString(pwdA));
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
			
			BsonValue pwd=usuario.get(contrasena);
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
	
	/**
	 * 
	 * @param usuario (solo necesario el nombre)
	 * @return lista de usuarios amigos suyos
	 */
	public List<BsonValue> obtenerAmigos(Usuario user){
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(user.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		List <BsonValue> amigos= usuario.getArray(this.amigos);
		return amigos;
	}
	/**
	 * 
	 * @param usuario (solo necesario el nombre)
	 * @return lista de usuarios que le han solicitado amistad
	 */
	public List<BsonValue> obtenerSolicitudes(Usuario user){
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(user.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		List <BsonValue> solicitudes= usuario.getArray(this.solicitudes);
		return solicitudes;
	}
	/**
	 * 
	 * @param solicitante
	 * @param solicitado
	 * @result añadir a lista de solicitudes del solicitado, el nombre del solicitante
	 */
	public void enviarSolicitud(Usuario solicitante, Usuario solicitado) {
		List<BsonValue> lista=obtenerSolicitudes(solicitado);
		lista.add(new BsonString(solicitante.getNombre()));
		
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(solicitado.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(solicitudes, new BsonArray(lista)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	/**
	 * 
	 * @param solicitante
	 * @param acepta
	 * @result añadir a la lista de amistades de ambos y eliminar la solicitud
	 */
	public void aceptarSolicitud(Usuario solicitante, Usuario acepta) {
		List<BsonValue> lista=obtenerSolicitudes(acepta);
		List<BsonValue> listaAmigosSolicitante=obtenerAmigos(solicitante);
		List<BsonValue> listaAmigosAcepta=obtenerAmigos(acepta);
		
		
		lista.remove(new BsonString(solicitante.getNombre()));
		listaAmigosSolicitante.add(new BsonString(acepta.getNombre()));
		listaAmigosAcepta.add(new BsonString(solicitante.getNombre()));
		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(solicitante.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(amigos, new BsonArray(listaAmigosSolicitante)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
		
		criterio = new BsonDocument();
		criterio.append(name, new BsonString(acepta.getNombre()));
		resultado=usuarios.find(criterio);
		usuario = resultado.first();
		actualizacion= new BsonDocument("$set", new BsonDocument(amigos, new BsonArray(listaAmigosAcepta)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
		
		criterio = new BsonDocument();
		criterio.append(name, new BsonString(acepta.getNombre()));
		resultado=usuarios.find(criterio);
		usuario = resultado.first();
		actualizacion= new BsonDocument("$set", new BsonDocument(solicitudes, new BsonArray(lista)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	/**
	 * 
	 * @param solicitante
	 * @param rechaza
	 * @result eliminar la solicitud del que rechaza
	 */
	public void rechazarSolicitud(Usuario solicitante, Usuario rechaza) {
		List<BsonValue> lista=obtenerSolicitudes(rechaza);
		lista.remove(new BsonString(solicitante.getNombre()));

		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(rechaza.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(solicitudes, new BsonArray(lista)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	/**
	 * 
	 * @param borrador
	 * @param borrado
	 * @result elimina de ambos usuarios la amistad del otro
	 */
	public void borrarAmistad(Usuario borrador, Usuario borrado) {
		List<BsonValue> listaBorrador=obtenerAmigos(borrador);
		listaBorrador.remove(new BsonString(borrado.getNombre()));

		List<BsonValue> listaBorrado=obtenerAmigos(borrado);

		listaBorrado.remove(new BsonString(borrador.getNombre()));

		
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(borrador.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(amigos, new BsonArray(listaBorrador)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
		
		criterio = new BsonDocument();
		criterio.append(name, new BsonString(borrado.getNombre()));
		resultado=usuarios.find(criterio);
		usuario = resultado.first();
		actualizacion= new BsonDocument("$set", new BsonDocument(amigos, new BsonArray(listaBorrado)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	/**
	 * @result devuelve una lista de usuarios que tengan en su nombre el filtro indicado
	 */
	public List<Usuario> buscador(String filtro){
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		FindIterable<BsonDocument> resultado=usuarios.find();
		String nombre;
		BsonDocument usuario;
		Iterator<BsonDocument> lista=resultado.iterator();
		List<Usuario> retorno=new ArrayList<Usuario>();
		while(lista.hasNext()) {
			usuario=lista.next();
			nombre=usuario.getString(name).getValue();
			if(nombre.contains(filtro)) retorno.add(new Usuario(nombre));
		}
		return retorno;
	}

}

