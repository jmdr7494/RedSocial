package com.intravita.proyectointranet.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.intravita.proyectointranet.email.MailSender;
import com.intravita.proyectointranet.modelo.Administrador;
import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.AdministradorDAOImpl;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;




@Controller
@RequestMapping({"/usuario","/"})


/**
 * UsuarioServlet- Clase controlador para comunicar las vistas con el dominio y persistencia.
 *
 * @author Intravita
 * @since sprint 1
 */

public class UsuarioServlet {
 @Autowired
 UsuarioDAOImpl usuarioDao;
 AdministradorDAOImpl administradorDao=new AdministradorDAOImpl();
 PublicacionDAOImpl publicacionDao=new PublicacionDAOImpl();
 
 private final String welcome = "bienvenido";	
 private final String user_login = "usuario/login";
 private final String user = "usuario/";
 private final String ini_admin = "inicioAdmin";
 private final String admin_conect = "administradorConectado";
 private final String user_conect = "usuarioConectado";
 private final String alert = "alerta";

 
 private static final Logger logger = LoggerFactory.getLogger(UsuarioServlet.class);
 
 @RequestMapping(method = RequestMethod.GET)
 public String home(Locale locale, Model model) {
  logger.info("Welcome home! The client locale is {}.", locale);
  
  Date date = new Date();
  DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
  
  String formattedDate = dateFormat.format(date);
  
  model.addAttribute("serverTime", formattedDate );
  
  return user_login;
 }
 
 /***
  * @method metodos de navegacion entre jsp's
  */
 @RequestMapping(value="/irLogin",method = RequestMethod.GET)
 public ModelAndView irLogin(){
  return cambiarVista(user_login);
 }
 
 @RequestMapping(value="/irRegistrar",method = RequestMethod.GET)
 public ModelAndView irRegistrar(){  
  return cambiarVista("usuario/registrar");
 }
 
 @RequestMapping(value="/irVerPublicaciones",method = RequestMethod.GET)
 public ModelAndView irVerPublicaciones(){
  return cambiarVista("usuario/verPublicaciones");
 }
 @RequestMapping(value="/irBorradoCuenta",method = RequestMethod.GET)
 public ModelAndView irBorradoCuenta(){
  return cambiarVista("usuario/borradoCuenta");
 }
 @RequestMapping(value="/irBienvenido",method = RequestMethod.GET)
 public ModelAndView irBienvenido(){
  return cambiarVista("usuario/bienvenido");
 }
 
 /***
  * 
  *@method cambiar roles Admin->Usuario
  *
  */
 @RequestMapping(value="/changeToUser", method = RequestMethod.POST)
 public String changeToUser(HttpServletRequest request, Model model) {
	Administrador admin=(Administrador) request.getSession().getAttribute(admin_conect);
	String cadenaUrl=user;
	if(!admin.getNombre().equals("admin")) {
		Usuario usuario=usuarioDao.selectNombre(admin.getNombre());
		request.getSession().setAttribute("usuarioConectado", usuario);
		return cadenaUrl+=welcome;
	}
	model.addAttribute("alerta", "No tienes permisos de usuario" );
	cadenaUrl+=ini_admin;
	return cadenaUrl;
 }
 
 /***
  * 
  *@method cambiar roles Usuario->Admin
  *
  */
 @RequestMapping(value="/changeToAdmin", method = RequestMethod.POST)
 public String changeToAdmin(HttpServletRequest request, Model model) {
	Usuario usuario=(Usuario) request.getSession().getAttribute(user_conect);
	String cadenaUrl=user;
	try {
		Administrador admin=administradorDao.selectNombre(usuario.getNombre());
		if(admin.getNombre()!=null) {
			request.getSession().setAttribute(admin_conect, admin);
			cadenaUrl+=ini_admin;
			return cadenaUrl;
		}
	}catch(Exception e) {
		model.addAttribute(alert, "No tienes permisos de administrador" );
	}
	return cadenaUrl+=welcome;
 }
 
 /***
  * 
  *@method ejecucion cuando pulsamos el boton login
  *
  */
 @RequestMapping(value="/login", method = RequestMethod.POST)
 public String iniciarSesion(HttpServletRequest request, Model model) throws Exception {
  String cadenaUrl=user;
  String nombre=request.getParameter("txtUsuarioNombre");
  String clave=request.getParameter("txtUsuarioClave");
  if(clave.equals("") || nombre.equals("")) {
	  model.addAttribute(alert, "Por favor rellene los campos" );
	  return cadenaUrl+="login";
  }
 
  Administrador administrador= new Administrador();
  administrador.setNombre(nombre);
  administrador.setClave(clave);
  if(administradorDao.login(administrador) && request.getSession().getAttribute(admin_conect)==null) {
   request.getSession().setAttribute(admin_conect, administrador);
   return cadenaUrl+=ini_admin;
  }
   
  
  Usuario usuario = new Usuario();
  usuario.setNombre(nombre);
  usuario.setClave(clave);
  if(usuarioDao.login(usuario) && request.getSession().getAttribute(user_conect)==null) {
   request.getSession().setAttribute(user_conect, usuario);
   return cadenaUrl+=welcome;
  }
   
  model.addAttribute("alerta", "Error en las credenciales" );
  return cadenaUrl+="login";
 } 
 /***
  * 
  *@method ejecucion cuando pulsamos el boton logout
  *
  */
 @RequestMapping(value="/logout", method = RequestMethod.GET)
 public ModelAndView cerrarSesion(HttpServletRequest request) throws Exception {
  HttpSession sesion = request.getSession();
  
  System.out.println("Sesion antes de invalidar: "+sesion);
  sesion.invalidate();
  System.out.println("Invalidamos la sesion: "+sesion);
  
  return cambiarVista(user_login);
 }
 /***
  * 
  * @method borrado de una cuenta propia
  *
  */
 @RequestMapping(value="/borrarCuenta", method = RequestMethod.POST)
 public ModelAndView borrarCuenta(HttpServletRequest request, Model model) throws Exception {
  Usuario usuario=(Usuario) request.getSession().getAttribute(user_conect);
  String nombre=usuario.getNombre();
  Usuario aux=usuarioDao.selectNombre(nombre);
  
  model.addAttribute("usuario", aux);
  String pwd=request.getParameter("txtUsuarioPwd");
  pwd=DigestUtils.md5Hex(pwd);
  String respuesta=request.getParameter("txtRespuesta");
  if(pwd.equals(aux.getClave()) && respuesta.equals(aux.getRespuesta())){
   usuarioDao.delete(usuario);
   administradorDao.delete(new Administrador(nombre));
   HttpSession sesion = request.getSession();
   
   System.out.println("Sesion antes de invalidar: "+sesion);
   sesion.invalidate();
   System.out.println("Invalidamos la sesion: "+sesion);
   
   return cambiarVista(user_login);
  }else {
   model.addAttribute(alert, "Error en las credenciales");
  }
  return cambiarVista("usuario/borradoCuenta");
 }
 
 /***
  * 
  *@method ejecucion cuando pulsamos el boton de registro
  *
  */
 @RequestMapping(value="/registrar", method = RequestMethod.POST)
 public String registrar(HttpServletRequest request, Model model) throws Exception  {
  String cadenaUrl=user;
  String nombre=request.getParameter("txtUsuarioNombre");
  String email=request.getParameter("txtEmail");
  String pwd1=request.getParameter("txtUsuarioClave");
  String pwd2=request.getParameter("txtUsuarioClave1");
  String respuesta=request.getParameter("txtRespuesta");
  
  try {
   utilidades.credencialesValidas(nombre, email, pwd1, pwd2);
  }catch(Exception e) {
   model.addAttribute("alerta", e.getMessage());
   return cadenaUrl+="registrar";
  }
  
  Usuario usuario = new Usuario();
  usuario.setNombre(nombre);
  usuario.setEmail(email);
  usuario.setClave(pwd1);
  usuario.setRespuesta(respuesta);
  
  try {
	  usuarioDao.insert(usuario);
  }catch(Exception e) {
	   model.addAttribute(alert, "Nombre de usuario no disponible");
	   return cadenaUrl+="registrar";
  }
  return cadenaUrl+="login";
 }
 /***
  * 
  *@method metodo de borrado de un usuario desde el administrador
  *
  */
 @RequestMapping(value="/borrar", method = RequestMethod.POST)
 public String borrar(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl=user;
  String nombre=request.getParameter("txtNombre");
  Usuario usuario;
  Administrador administrador= (Administrador) request.getSession().getAttribute(admin_conect);
  if(nombre.equals(administrador.getNombre())) {
	  model.addAttribute(alert, "No puedes realizar acciones sobre ti");
  }else {
	  if(nombre.equals("admin")) {
	   model.addAttribute(alert, "No puedes borrar al superadmin");
	  }else {
	   usuario=usuarioDao.selectNombre(nombre);
	   if(usuario==null) {
	    model.addAttribute(alert, "No existe el usuario "+nombre);
	   }else {
	    usuarioDao.delete(usuario);
	    administradorDao.delete(new Administrador(nombre));
	   }
	  }
  }
  listarUsuario(request, model);
  cadenaUrl+=ini_admin;  
  return cadenaUrl;
 }
 
 /***
  * 
  *@method funcion del administrador de promover un usuario a admin
  *
  */
 @RequestMapping(value="/promover", method = RequestMethod.POST)
 public String promover(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl=user;
  String nombre=request.getParameter("txtNombre");
  Usuario usuario = new Usuario();
  usuario.setNombre(nombre); 
  usuario=usuarioDao.selectNombre(nombre);
  Administrador administrador= (Administrador) request.getSession().getAttribute(admin_conect);
  if(nombre.equals(administrador.getNombre())) {
	  model.addAttribute(alert, "No puedes realizar acciones sobre ti");
  }else{
	  if(usuario!=null) {
	   Administrador admin=new Administrador(usuario.getNombre(), usuario.getClave(), usuario.getEmail());
	   administradorDao.insertSinEncrypt(admin);
	  }else{
	   model.addAttribute(alert, "El usuario que intentas promover no existe");
	  }
  }
  listarUsuario(request, model);
  cadenaUrl+=ini_admin;  
  return cadenaUrl;
 }
 /***
  * 
  *@method funcion del administrador de degradar un admin a usuario
  *
  */
 @RequestMapping(value="/degradar", method = RequestMethod.POST)
 public String degradar(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl=user;
  String nombre=request.getParameter("txtNombre");
  Administrador admin;
  Administrador administrador= (Administrador) request.getSession().getAttribute(admin_conect);
  if(nombre.equals(administrador.getNombre())) {
	  model.addAttribute(alert, "No puedes realizar acciones sobre ti");
  }else {
	  if(nombre.equals("admin")) {
	   model.addAttribute(alert, "<t><h2><b>No puedes degradar al superadmin</b></h2>");
	  }else {
	   admin=administradorDao.selectNombre(nombre);
	   if(admin==null)
	    model.addAttribute(alert, "El administrador que intentas degradar no existe");
	   else {
	    administradorDao.delete(admin);
	   }
	  }
  }
  listarUsuario(request, model);
  cadenaUrl+=ini_admin;  
  return cadenaUrl;
 }
 
 //@RequestMapping(value="/eliminar", method = RequestMethod.GET)
 /***
  * 
  * @method actualiza la ventana de administrador para ver sus usuarios/administradores
  * 
  */
 @RequestMapping(value="/listarUsuario", method = RequestMethod.POST)
 public String listarUsuario(HttpServletRequest request, Model model) throws Exception  {
  String cadenaUrl=user;
  model.addAttribute("usuarios", usuarioDao.list());
  model.addAttribute("administradores", administradorDao.list());
  cadenaUrl+=ini_admin;  
  return cadenaUrl;
 }
 /**
  * 
  *@method borrar una publicacion dado un ID
  */
 @RequestMapping(value="/eliminarPubli", method = RequestMethod.POST)
 public String eliminarPubli(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl=user;
  String id=request.getParameter("txtIdPublicacion");
  publicacionDao.remove(id);
  listarPublicacion(request, model);
  cadenaUrl+=welcome;  
  return cadenaUrl;
 }
 
 /**
  * 
  *@method editar una publicacion dado un ID
  */
 @RequestMapping(value="/editarPubli", method = RequestMethod.POST)
 public String editarPubli(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl=user;
  String texto=request.getParameter("txtIntroducirTexto");
  String id=request.getParameter("txtIdPublicacion");
  publicacionDao.update(id, texto);
  listarPublicacion(request, model);
  cadenaUrl+=welcome;  
  return cadenaUrl;
 }
 /***
  * 
  * @method permite crear una publicaciï¿½n por parte de un usuario
  * 
  */
 @RequestMapping(value="/crearPublicacion", method = RequestMethod.POST)
 public String crearPublicacion(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl=user;
  Usuario usuario;
  usuario=(Usuario) request.getSession().getAttribute(user_conect);
  
  
  String nombre=usuario.getNombre();
  
  model.addAttribute("usuario", usuarioDao.selectNombre(nombre));
  String texto=request.getParameter("txtIntroducirTexto");

  try {
   utilidades.publicacionValida(nombre, texto);
  }catch(Exception e) {
   model.addAttribute(alert, e.getMessage());
   cadenaUrl+=welcome; 
   return cadenaUrl;
  }
  
  Publicacion publicacion= new Publicacion(usuario, texto);
  
  if(publicacionDao.existe(publicacion)) {
   model.addAttribute(alert, "Nombre de usuario no disponible");
   return cadenaUrl+=welcome;
  }
  publicacionDao.insert(publicacion);
  listarPublicacion(request, model);
  cadenaUrl+=welcome; 
  return cadenaUrl;
 }
 @RequestMapping(value="/crearPublicacionPrivada", method = RequestMethod.POST)
 public String crearPublicacionPrivada(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl=user;
  Usuario usuario;
  usuario=(Usuario) request.getSession().getAttribute("usuarioConectado");
  
  
  String nombre=usuario.getNombre();
  
  model.addAttribute("usuario", usuarioDao.selectNombre(nombre));
  String texto=request.getParameter("txtIntroducirTexto");

  try {
   utilidades.publicacionValida(nombre, texto);
  }catch(Exception e) {
   model.addAttribute(alert, e.getMessage());
   cadenaUrl+=welcome; 
   return cadenaUrl;
  }
  
  Publicacion publicacion= new Publicacion(usuario, texto, "Privada");
  
  if(publicacionDao.existe(publicacion)) {
   model.addAttribute(alert, "Nombre de usuario no disponible");
   cadenaUrl+=welcome; 
   return cadenaUrl;
  }
  publicacionDao.insert(publicacion);
  listarPublicacion(request, model);
  cadenaUrl+=welcome; 
  return cadenaUrl;
 }
 /***
  * 
  * @method permite ver las publicac
 iones realizadas por un usuario(de momento, luego cambiar a segï¿½n la visibilidad y amigos)
  * 
  */
 @RequestMapping(value="/listarPublicacion", method = RequestMethod.POST)
 public String listarPublicacion(HttpServletRequest request, Model model) throws Exception  {
  String cadenaUrl=user;
  Usuario usuario;
  usuario=(Usuario) request.getSession().getAttribute(user_conect);
  
  ArrayList<Publicacion> publicas=publicacionDao.selectPublicas(usuario);
  ArrayList<Publicacion> privadas=publicacionDao.selectPrivadas(usuario);
  
  Publicacion[] todas=utilidades.mostrarPublicaciones(publicas, privadas);
  String texto="";
  for(int i=0; i<todas.length; i++) {
	  /*
	  texto+="		<div class=\"panel panel-default\">\r\n" + 
	  				"			 <div class=\"panel-body\">";
	  texto+=todas[i].toString();
	  texto+="<br>";
	  texto+="		</div>	\r\n" + 
	  			"	</div>";*/
	  texto = texto+"<div class=\"panel panel-default\">\r\n" + 
	  		"	<div class=\"panel-body\">\r\n" + 
	  		"			<b> "+ todas[i].getUsuario().getNombre() +" </b> \r\n" + 
	  		"			<textarea name=\"txtIntroducirTexto\" placeholder=\"ï¿½Quï¿½ tal el dï¿½a?\" class=\"form-control\" rows=\"5\" id=\"comment\" disabled>"+ todas[i].getTexto()+"</textarea>\r\n" + 
	  		"			<input name=\"txtIdPublicacion\" type=\"hidden\" class=\"form-control\" value=\""+todas[i].getId()+"\" id=\"usr\" placeholder=\"usuario\">" + 
	  		"			<button class=\"btn btn-primary btn-block btn-md login\" type=\"submit\" data-toggle=\"modal\" data-target=\"#miModals\">Editar</button>\r\n" + 
	  		"<div class=\"modal fade\" id=\"miModals\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalsLabel\" aria-hidden=\"true\">\r\n" + 
	  		"			<div class=\"modal-dialog\" role=\"document\">\r\n" + 
	  		"				<div class=\"modal-content\">\r\n" + 
	  		"					<div class=\"modal-header\">\r\n" + 
	  		"						<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\r\n" + 
	  		"							<span aria-hidden=\"true\">&times;</span>\r\n" + 
	  		"						</button>\r\n" + 
	  		"						<h4 class=\"modal-title\" id=\"myModalsLabel\">Editar</h4>\r\n" + 
	  		"					</div>\r\n" + 
	  		"					<div class=\"modal-body\">\r\n" + 
	  		"						¿Está seguro que desea editar la publicación?\r\n" + 
	  		"						<br>\r\n" + 
	  		"						<form action=\"editarPubli\" method=\"POST\">\r\n" + 
	  		"							<textarea name=\"txtIntroducirTexto\" placeholder=\"ï¿½Quï¿½ tal el dï¿½a?\" class=\"form-control\" rows=\"5\" id=\"comment\">"+ todas[i].getTexto()+"</textarea>\r\n" + 
	  		"							<input name=\"txtIdPublicacion\" type=\"hidden\" class=\"form-control\" value=\""+todas[i].getId()+"\" id=\"usr\" placeholder=\"usuario\">" + 
	  		"							<br>" + 
	  		"							<button class=\"btn btn-success btn-block btn-md login\" type=\"submit\">Si</button>\r\n" + 
	  		"						</form>\r\n" + 
	  		"						<form action=\"listarPublicacion\" method=\"POST\">\r\n" + 
	  		"							<br>\r\n" + 
	  		"							<button class=\"btn btn-danger btn-block btn-md login\" type=\"submit\">No</button>\r\n" + 
	  		"						</form>\r\n" + 
	  		"					</div>\r\n" + 
	  		"				</div>\r\n" + 
	  		"			</div>\r\n" + 
	  		"		</div>" + 
	  		
	"<button class=\"btn btn-danger btn-block btn-md login\" type=\"submit\" data-toggle=\"modal\" data-target=\"#miModalss\">Eliminar</button>\r\n" + 
	"<div class=\"modal fade\" id=\"miModalss\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalssLabel\" aria-hidden=\"true\">\r\n" + 
	"			<div class=\"modal-dialog\" role=\"document\">\r\n" + 
	"				<div class=\"modal-content\">\r\n" + 
	"					<div class=\"modal-header\">\r\n" + 
	"						<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\r\n" + 
	"							<span aria-hidden=\"true\">&times;</span>\r\n" + 
	"						</button>\r\n" + 
	"						<h4 class=\"modal-title\" id=\"myModalssLabel\">Editar</h4>\r\n" + 
	"					</div>\r\n" + 
	"					<div class=\"modal-body\">\r\n" + 
	"						¿Está seguro que desea eliminar la publicación?\r\n" + 
	"						<br>\r\n" + 
	"						<form action=\"eliminarPubli\" method=\"POST\">\r\n" + 
	"							<input name=\"txtIdPublicacion\" type=\"hidden\" class=\"form-control\" value=\""+todas[i].getId()+"\" id=\"usr\" placeholder=\"usuario\">" + 
	"							<br>" + 
	"							<button class=\"btn btn-success btn-block btn-md login\" type=\"submit\">Si</button>\r\n" + 
	"						</form>\r\n" + 
	"						<form action=\"listarPublicacion\" method=\"POST\">\r\n" + 
	"							<br>\r\n" + 
	"							<button class=\"btn btn-danger btn-block btn-md login\" type=\"submit\">No</button>\r\n" + 
	"						</form>\r\n" + 
	"					</div>\r\n" + 
	"				</div>\r\n" + 
	"			</div>\r\n" + 
	"		</div>" + 
	  		"	</div>\r\n" + 
	  		"</div>	";
  }
  System.out.print(texto);
  model.addAttribute("publicaciones", texto);
  
  cadenaUrl+=welcome;  
  return cadenaUrl;
 }
 
 /***
  * 
  *@method cambiar a la ventana "bienvenido" desde la vista de usuario
  *
  */
 @RequestMapping(value="/bienvenido", method = RequestMethod.GET)
 public String bienvenido(HttpServletRequest request) throws Exception  {
  return "usuario/bienvenido";
 }
 
 //By JA
  @RequestMapping(value="/irRecuperarCredenciales", method = RequestMethod.GET)
  public ModelAndView irRecuperarCredenciales(HttpServletRequest request) throws Exception  {
   return cambiarVista("usuario/recuperarCredenciales");
  }
  
  //By JA
  @RequestMapping(value="/recuperarCredenciales", method = RequestMethod.POST)
  public String recuperarCredenciales(HttpServletRequest request, Model model) throws Exception  {
   String nombre=request.getParameter("txtUsuarioNombre");
   String respuesta=request.getParameter("txtRespuesta");
   
   
   Usuario usuario = usuarioDao.selectNombre(nombre);
   int pin = (int)(Math.random() * (9999-1000+1)+1000);
   String pinEmail = "intravita"+String.valueOf(pin);
   
   try {
    utilidades.comprobacionNombre(nombre);
   }catch (Exception e) {
	   model.addAttribute(alert, e.getMessage());
	   return "usuario/recuperarCredenciales"; 
   }
   
    
   if (usuario==null || (!respuesta.equals(usuario.getRespuesta()))) {
	   model.addAttribute(alert, "Datos incorrectos");
	   return "usuario/recuperarCredenciales";
   }else {
    MailSender mailSender= new MailSender();
    System.out.println("Estamos para mandar el correo");
    
    mailSender.sendMailRecoverPwd(usuario.getEmail() , pinEmail);
    usuario.setClave(pinEmail);
    usuarioDao.updatePwd(usuario);
   }
   
   return "usuario/reestablecerPwd";
   
  }
 
 
  //By JA
  @RequestMapping(value="/reestablecerPwd", method = RequestMethod.POST)
  public String reestablecerPwd(HttpServletRequest request, Model model) throws Exception  {
   String pwdTemporal=DigestUtils.md5Hex(request.getParameter("txtPwdTemporal"));
   String pwdNueva1=request.getParameter("txtPwdNueva1");
   String pwdNueva2=request.getParameter("txtPwdNueva2");
   
   System.out.println ("Hola voy a buscar el usuario");
   Usuario usuario = usuarioDao.selectPwd(pwdTemporal);// buscar encriptada
   if (usuario==null) {
	   System.out.println ("hola soy nulo");
   }
    
    
   if (usuario==null || !(pwdNueva1.equals(pwdNueva2))) {
	   model.addAttribute(alert, "Datos incorrectos");
	   return "usuario/reestablecerPwd";
			   
   }
   
	  try {
		   utilidades.seguridadPassword(pwdNueva1);
	  }
	   catch (Exception e) {
		   model.addAttribute(alert, e.getMessage());
		   return "usuario/reestablecerPwd"; 
	   }
   
	   usuario.setClave(pwdNueva1); 
	   usuarioDao.updatePwd(usuario);
	   
   return "usuario/login";
   
  } 
 
 
 
 
 /***
  * 
  *@method Esta funciï¿½n sirve para controlar los cambios de vista por nombre(string)
  *
  */
 public ModelAndView cambiarVista(String nombreVista) {
  ModelAndView vista =new ModelAndView(nombreVista);
  return vista;
 }
}

