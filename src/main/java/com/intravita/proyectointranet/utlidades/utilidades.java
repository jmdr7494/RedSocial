package com.intravita.proyectointranet.utlidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import org.apache.commons.codec.digest.DigestUtils;
import org.bson.BsonString;
import org.bson.BsonValue;

import com.intravita.proyectointranet.modelo.Administrador;
import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;

/**
 * utilidades- Clase auxiliar con funcionalidades de comprobacion o de ayuda
 *
 * @author Intravita
 * @since sprint 2
 */

public class utilidades {
	static UsuarioDAOImpl usuarioDao= new UsuarioDAOImpl();
	/**
	 * Extension de email permitida
	 */
	public static String extensionEmail="@alu.uclm.es";
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

	  if(email.length()<=extensionEmail.length())
		  throw new Exception("Email invalido");
	  String extension=email.substring(email.length()-extensionEmail.length(), email.length());
	  palabrasMalas(email);
	  
	  if(!extension.equals(extensionEmail))
	   throw new Exception("Email invalido");
	  
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
			throw new Exception ("Por favor rellene texto para guardar la publicación");
		
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
		if(comprobarSolicitudes(emisor,receptor)) throw new Exception("Ya tienes una solicitud de ese usuario");
		
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
}
