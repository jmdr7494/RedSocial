package com.intravita.proyectointranet.utlidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.BsonString;
import org.bson.BsonValue;


import com.intravita.proyectointranet.modelo.Administrador;
import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.AdministradorDAOImpl;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;

/**
 * utilidades- Clase auxiliar con funcionalidades de comprobacion o de ayuda
 *
 * @author Intravita
 * @since sprint 2
 */

public class utilidades {
	static UsuarioDAOImpl usuarioDao= new UsuarioDAOImpl();
	static AdministradorDAOImpl administradorDao=new AdministradorDAOImpl();
	/**
	 * Extension de email permitida
	 */
	public static String extensionEmail1="@alu.uclm.es";
	public static String extensionEmail2="@uclm.es";
	/**
	 * @method comprobacion de credenciales validas para el registro
	 * @param nombre
	 * @param email
	 * @param pwd1
	 * @param pwd2
	 * @return 
	 * @throws Exception 
	 */
	static String [] diccionario = {"cabron", "cabronazo", "maricon", "gilipollas", "tonto", "capullo", "idiota", "fuck", "fucking", "huevon", "polla", "pollon", "coño"};
	 
	 public static void credencialesValidas(String nombre, String email, String pwd1, String pwd2) throws Exception {
	  if(nombre.equals("") ||email.equals("")|| pwd1.equals("")|| pwd2.equals(""))
	   throw new Exception ("Por favor rellene todos los campos");
	  comprobacionNombre(nombre);
	  
	  boolean emailInvalido=true;
	  
	  if (email.contains(extensionEmail1)) {
	  
		  if(email.length()<=extensionEmail1.length()) 
			  throw new Exception("Email invalido");
		  String extension1=email.substring(email.length()-extensionEmail1.length(), email.length());
		  palabrasMalas(email);
	  
		  if(!extension1.equals(extensionEmail1))
			   throw new Exception("Email invalido");
		  	
		  emailInvalido=false;
		  
	  }
	  
	  if (email.contains(extensionEmail2)) {
		  
		  if(email.length()<=extensionEmail2.length()) 
			  throw new Exception("Email invalido");
		  String extension2=email.substring(email.length()-extensionEmail2.length(), email.length());
		  palabrasMalas(email);
	  
		  if(!extension2.equals(extensionEmail2))
			  throw new Exception("Email invalido");
		  
		  emailInvalido=false;
	  
	  }
	  
	  if (emailInvalido) {
		  throw new Exception("Email invalido");
	  }
	  
	  if(!pwd1.equals(pwd2))
	   throw new Exception("No coinciden las password");
	  seguridadPassword(pwd1);
	  
	 }
	 public static void comprobacionNombre(String nombre) throws Exception{
	  if(!nombre.contains("."))
	   throw new Exception("Formato nombre invalido"); 
	  int posicion=nombre.indexOf('.');
	  int posicion2=nombre.lastIndexOf('.');
	  if(posicion!=posicion2)
	   throw new Exception("Formato nombre invalido");
	  palabrasMalas(nombre);
	 }
	 
	 public static void palabrasMalas(String nombre)throws Exception{
		 int size=diccionario.length;
		for(int i=0;i<size;i++) {
			if(nombre.contains(diccionario[i]))
				throw new Exception("Formato del nombre y/o email invalido, contiene palabras malsonantes");
		}
	}
	
	public static void seguridadPassword(String pwd) throws Exception{
		if(pwd.length()<8)
			throw new Exception("Password poco segura (minimo 8 caracteres, con numeros y letras)");
		boolean caracter=false;
		boolean numero=false;
		char c;
		int size=pwd.length();
		for(int i=0; i<size; i++) {
			c=pwd.charAt(i);
			if(Character.isDigit(c)) {
				numero=true;
			}
			if(Character.isLetter(c)) {
				caracter=true;
			}
		}
		if(!caracter || !numero)
			throw new Exception("Password poco segura (minimo 8 caracteres, con numeros y letras)");
	}
	
	public static void publicacionValida(String nombre, String texto) throws Exception {
		if(nombre.equals("") ||texto.equals(""))
			throw new Exception ("La publicación está vacía, escribe algo");
		
	}
	
	public static Publicacion[] mostrarPublicaciones(ArrayList <Publicacion> publicas, ArrayList<Publicacion> privadas){
		ArrayList<Publicacion> retorno=new ArrayList<Publicacion>();
		retorno=publicas;
		int longitud=privadas.size();
		for(int i=0; i<longitud; i++) {
			retorno.add(privadas.get(i));
		}
		Publicacion[] ordenadas=new Publicacion[retorno.size()];
		retorno.toArray(ordenadas);
		
		Arrays.sort(ordenadas);
		return ordenadas;
	}
	
	/**
	 * 
	 * @param usuarioA
	 * @param usuarioB
	 * @return true si son amigos, false si no
	 */
	public static boolean comprobarAmistad(Usuario usuarioA, Usuario usuarioB) {
		List <BsonValue> amigosA=usuarioDao.obtenerAmigos(usuarioA);
		Iterator <BsonValue> it=amigosA.iterator();
		BsonValue aux;
		BsonString nombre;
		String nombreB=usuarioB.getNombre();
		while(it.hasNext()) {
			aux=it.next();
			nombre=aux.asString();
			if(nombreB.equals(nombre.getValue()))
				return true;
		}	
		return false;
	}
	/**
	 * 
	 * @param emisor
	 * @param receptor
	 * @return true si receptor tiene una solicitud de emisor
	 */
	public static boolean comprobarSolicitudes(Usuario emisor, Usuario receptor) {
		List <BsonValue> solicitudesReceptor=usuarioDao.obtenerSolicitudes(receptor);
		Iterator <BsonValue> it=solicitudesReceptor.iterator();
		BsonValue aux;
		BsonString nombre;
		String nombreEmisor=emisor.getNombre();
		while(it.hasNext()) {
			aux=it.next();
			nombre=aux.asString();
			if(nombreEmisor.equals(nombre.getValue()))
				return true;
		}	
		return false;
	}
	
	/**
	 * 
	 * @param emisor de la solicitud
	 * @param receptor de la solicitud
	 * @return excepcion si algo falla, si no, envia la solicitud emisor->receptor
	 */
	public static void enviarSolicitud(Usuario emisor, Usuario receptor) throws Exception{
		if(comprobarSolicitudes(emisor,receptor)) throw new Exception("Ya has enviado una solicitud a ese usuario.");
		if(comprobarSolicitudes(receptor,emisor)) throw new Exception("Ya tienes una solicitud de ese usuario.");
		if(comprobarAmistad(emisor,receptor)) throw new Exception("Ya sois amigos");
		if(comprobarAmistad(receptor,emisor)) throw new Exception("Ya sois amigos");
		
		usuarioDao.enviarSolicitud(emisor, receptor);
	}
	
	/**
	 * 
	 * @param emisor (usuario que envio la solicitud)
	 * @param receptor (usuario que recibio la solicitud)
	 * @return excepcion si algo falla, si no, acepta la amistad
	 */
	public static void aceptarSolicitud(Usuario emisor, Usuario receptor) throws Exception{
		if(!comprobarSolicitudes(emisor,receptor)) throw new Exception("No te ha mandado solicitud");	
		usuarioDao.aceptarSolicitud(emisor, receptor);
	}
	
	/**
	 * 
	 * @param emisor (usuario que envio la solicitud)
	 * @param receptor (usuario que recibio la solicitud)
	 * @return excepcion si algo falla, si no, rechaza la amistad
	 */
	public static void rechazarSolicitud(Usuario emisor, Usuario receptor) throws Exception{
		if(!comprobarSolicitudes(emisor,receptor)) throw new Exception("No te ha mandado solicitud");	
		usuarioDao.rechazarSolicitud(emisor, receptor);
	}
	/**
	 * 
	 * @param borrador (persona que inicia el borrado de amigos)
	 * @param borrado (persona que va a ser borrada de amigos)
	 * @throws Exception
	 */
	public static void borrarAmistad(Usuario borrador, Usuario borrado) throws Exception {
		if(!comprobarAmistad(borrador,borrado)) throw new Exception("No puedes eliminar a alguien que no es tu amigo");
		if(!comprobarAmistad(borrado,borrador)) throw new Exception("No puedes eliminar a alguien que no es tu amigo");
		usuarioDao.borrarAmistad(borrador, borrado);
		
	}
	
	public static String buscadorUsuario(Usuario busca, String filtro) {
		List <Usuario> coincidencias=usuarioDao.buscador(filtro);
		String retorno="";
		Iterator <Usuario> it=coincidencias.iterator();
		if(!it.hasNext()) return "No se encontraron resultados";
		Usuario aux;
		while(it.hasNext()) {
			aux=it.next();
			if(!aux.getNombre().equals(busca.getNombre())) {
				if(!comprobarAmistad(busca, aux) && !comprobarAmistad(busca,aux)) {
					retorno+="		<form action=\"enviarSolicitud\" method=\"POST\">	\r\n" + 
							"			<div class=\"row\">\r\n" + 
							"        		<div class=\"col-md-6\">\r\n" +
							"					<input name=\"noSirve\" class=\"form-control\" value=\""+aux.getNombre()+"\" id=\"usr\" placeholder=\"usuario\" disabled>"+ 
							"					<input name=\"txtNombreEnviar\" type=\"hidden\" class=\"form-control\" value=\""+aux.getNombre()+"\" id=\"usr\" placeholder=\"usuario\">"+
							"				</div>\r\n" + 
							"				<div class=\"col-md-3\">\r\n" + 
							"					<button class=\"btn btn-success btn-block btn-md login\" type=\"submit\">Agregar</button>\r\n" +
							"					<br>\r\n" + 
							"				</div>\r\n" +
							"			</div>\r\n" +
							"		</form>";
				}else {
					retorno+="		<form action=\"eliminarAmigo\" method=\"POST\">	\r\n" + 
							"			<div class=\"row\">\r\n" + 
							"        		<div class=\"col-md-6\">\r\n" +
							"					<input name=\"noSirve\" class=\"form-control\" value=\""+aux.getNombre()+"\" id=\"usr\" placeholder=\"usuario\" disabled>"+ 
							"					<input name=\"txtNombreEliminar\" type=\"hidden\" class=\"form-control\" value=\""+aux.getNombre()+"\" id=\"usr\" placeholder=\"usuario\">"+
							"				</div>\r\n" + 
							"				<div class=\"col-md-3\">\r\n" +
							"					<button class=\"btn btn-danger btn-block btn-md login\"  type=\"submit\">Eliminar</button>\r\n" +
							"					<br>\r\n" + 
							"				</div>\r\n" +
							"			</div>\r\n" +
							"		</form>";
				}
			}
		}
		return retorno;
	}
	/**
	 * 
	 * @param usuario (solo el nombre)
	 * @return devuelve las peticiones de amistad pendientes
	 */
	public static String mostrarNotificaciones(Usuario usuario) {
		List<BsonValue> notificacionesPendientes=usuarioDao.obtenerSolicitudes(usuario);
		Iterator<BsonValue> it=notificacionesPendientes.iterator();
		BsonString aux;
		String retorno="";
		if(!it.hasNext()) return "No tienes notificaciones pendientes";
		while(it.hasNext()) {
			aux=it.next().asString();
			retorno+="    <form action=\"aceptarSolicitud\" method=\"POST\">\r\n" +
			          "      <div class=\"row\">\r\n" + 
			          "        <div class=\"col-md-6\">\r\n" +
			          "          <input name=\"noSirve\" type=\"text\" class=\"form-control\" value=\""+aux.getValue()+"\" id=\"usr\" placeholder=\"usuario\" disabled>\r\n" + 
			          "          <input name=\"txtNombre\" type=\"hidden\" class=\"form-control\" value=\""+aux.getValue()+"\" id=\"usr\" placeholder=\"usuario\" >\r\n" +
			          "        </div>\r\n" + 
			          "        <div class=\"col-md-3\">\r\n" + 
			          "          <button class=\"btn btn-success btn-block btn-md login\"  type=\"submit\">Aceptar</button>\r\n" +
			          "        </div>\r\n" + 
			          "        <div class=\"col-md-3\">\r\n" +
			          "          <button class=\"btn btn-danger btn-block btn-md login\"  formaction=\"rechazarSolicitud\" type=\"submit\">Rechazar</button>\r\n" +
			          "			<br>\r\n" +
			          "      </div></div>\r\n" +  
			          "    </form>";
		}
		return retorno;
	}
	/**
	 * 
	 * @return String html para mostrar a los usuarios/administradores con sus botones
	 */
	public static String listarUsuarios() {
		List<Usuario> usuarios=usuarioDao.list();
		Iterator<Usuario> it=usuarios.iterator();
		Usuario aux;
		String texto="";
		while(it.hasNext()) {
			aux=it.next();
			texto+="<form action=\"borrar\" method=\"POST\">\r\n" + 
					"	<div class=\"row\">\r\n" + 
					"		<div class=\"col-md-6\">\r\n" + 
					"			<input name=\"noSirve\" type=\"text\" class=\"form-control\" value=\""+ aux.getNombre()+"\" id=\"usr\" placeholder=\"usuario\" disabled>\r\n" + 
					"			<input name=\"txtNombre\" type=\"hidden\" class=\"form-control\" value=\""+aux.getNombre() +"\" id=\"usr\" placeholder=\"usuario\" >\r\n" + 
					"		</div>\r\n" + 
					"		<div class=\"col-md-2\">\r\n" + 
					"			<button class=\"btn btn-success btn-block login\" formaction=\"promover\" type=\"submit\" title=\"Promover Usuario\"><strong><span class=\"glyphicon glyphicon-thumbs-up\"></span></strong></button>\r\n" +
					"		</div>\r\n" + 
					"		<div class=\"col-md-2\">\r\n" + 
					"			<button class=\"btn btn-primary btn-block login\" formaction=\"irPerfilUsuarioAdmin\" type=\"submit\" title=\"Modificar Perfil\"><strong><span class=\"glyphicon glyphicon-user\"></span></strong></button>\r\n" + 
					"		</div>\r\n" + 
					"		<div class=\"col-md-2\">\r\n" + 
					"			<button class=\"btn btn-danger btn-block login\" type=\"submit\" title=\"Eliminar Usuario\"><strong><span class=\"glyphicon glyphicon-trash\"></span></strong></button>\r\n" +  
					"		</div></div>\r\n" + 
					"</form>	";
		}
		return texto;
	}
	public static String listarAdministradores() {
		List<Administrador> administradores=administradorDao.list();
		Iterator<Administrador> it=administradores.iterator();
		Administrador aux;
		String texto="";
		while(it.hasNext()) {
			aux=it.next();
			texto+="<form action=\"borrar\" method=\"POST\">\r\n" + 
					"	<div class=\"row\">\r\n" + 
					"		<div class=\"col-md-6\">\r\n" + 
					"			<input name=\"noSirve\" type=\"text\" class=\"form-control\" value=\""+ aux.getNombre()+"\" id=\"usr\" placeholder=\"usuario\" disabled>\r\n" + 
					"			<input name=\"txtNombre\" type=\"hidden\" class=\"form-control\" value=\""+aux.getNombre() +"\" id=\"usr\" placeholder=\"usuario\" >\r\n" + 
					"		</div>\r\n" + 
					"		<div class=\"col-md-3\">\r\n" + 
					"			<button class=\"btn btn-warning btn-block login\" formaction=\"degradar\" type=\"submit\" title=\"Degradar Administrador\"><span class=\"glyphicon glyphicon-thumbs-down\"></span><strong></strong></button>\r\n" + 
					"		</div>\r\n" + 
					"		<div class=\"col-md-3\">\r\n" + 
					"			<button class=\"btn btn-danger btn-block login\" type=\"submit\" title=\"Eliminar Administrador\"><strong><span class=\"glyphicon glyphicon-trash\"></span></strong></button>\r\n" +  
					"		</div></div>\r\n" + 
					"</form>	";
		}
		return texto;		
	}
	/**
	 * 
	 * @param usuario del que queremos mostrar el perfil
	 * @return la vista del perfil que queremos editar desde administrador
	 */
	public static String mostrarPerfilAdmin(Usuario usuario) {
		Usuario user=usuarioDao.selectNombre(usuario.getNombre());
		String texto="<form action=\"editarNombre\" method=\"GET\">\r\n" + 
				"		<label for=\"usr\">Nombre</label>"+
				"		<input name=\"txtNombre\" class=\"form-control\" value=\""+user.getNombre()+"\" id=\"usr\" placeholder=\"usuario\" >\r\n" + 
				"		<button class=\"btn btn-danger btn-block btn-md login\"  type=\"submit\">Editar</button>\r\n" + 
				"</form>\r\n" + 
				"<form action=\"editarPwd\" method=\"GET\">\r\n" + 
				"		<label for=\"pwd\">Password</label>"+
				"		<input name=\"txtPWD\" class=\"form-control\" value=\"Nueva password\" id=\"pwd\" placeholder=\"pwd\" >\r\n" + 
				"		<button class=\"btn btn-danger btn-block btn-md login\"  type=\"submit\">Editar</button>\r\n" + 
				"</form>\r\n" +
				"<label for=\"email\">Email</label>"+
				"<input name=\"txtEMAIL\" class=\"form-control\" value=\""+user.getEmail()+"\" id=\"email\" placeholder=\"email\" disabled>";
		return texto;
	}
}
