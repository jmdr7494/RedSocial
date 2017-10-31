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
public class UsuarioServlet {
 @Autowired
 UsuarioDAOImpl usuarioDao;
 AdministradorDAOImpl administradorDao=new AdministradorDAOImpl();
 PublicacionDAOImpl publicacionDao=new PublicacionDAOImpl();
 
 private static final Logger logger = LoggerFactory.getLogger(UsuarioServlet.class);
 
 @RequestMapping(method = RequestMethod.GET)
 public String home(Locale locale, Model model) {
  logger.info("Welcome home! The client locale is {}.", locale);
  
  Date date = new Date();
  DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
  
  String formattedDate = dateFormat.format(date);
  
  model.addAttribute("serverTime", formattedDate );
  
  return "usuario/login";
 }
 
 /***
  * @method metodos de navegacion entre jsp's
  */
 @RequestMapping(value="/irLogin",method = RequestMethod.GET)
 public ModelAndView irLogin(HttpServletResponse response,HttpServletRequest request){
  return cambiarVista("usuario/login");
 }
 
 @RequestMapping(value="/irRegistrar",method = RequestMethod.GET)
 public ModelAndView irRegistrar(HttpServletResponse response,HttpServletRequest request){
  
  return cambiarVista("usuario/registrar");
 }
 
 @RequestMapping(value="/irVerPublicaciones",method = RequestMethod.GET)
 public ModelAndView irVerPublicaciones(HttpServletResponse response,HttpServletRequest request){
  return cambiarVista("usuario/verPublicaciones");
 }
 @RequestMapping(value="/irBorradoCuenta",method = RequestMethod.GET)
 public ModelAndView irBorradoCuenta(HttpServletResponse response,HttpServletRequest request){
  return cambiarVista("usuario/borradoCuenta");
 }
 @RequestMapping(value="/irBienvenido",method = RequestMethod.GET)
 public ModelAndView irBienvenido(HttpServletResponse response,HttpServletRequest request){
  return cambiarVista("usuario/bienvenido");
 }
 
 /***
  * 
  *@method cambiar roles Admin->Usuario
  *
  */
 @RequestMapping(value="/changeToUser", method = RequestMethod.POST)
 public String changeToUser(HttpServletResponse response, HttpServletRequest request, Model model) {
	Administrador admin=(Administrador) request.getSession().getAttribute("administradorConectado");
	String cadenaUrl="usuario/";
	if(!admin.getNombre().equals("admin")) {
		Usuario usuario=usuarioDao.selectNombre(admin.getNombre());
		request.getSession().setAttribute("usuarioConectado", usuario);
		return cadenaUrl+="bienvenido";
	}
	model.addAttribute("alerta", "No tienes permisos de usuario" );
	return cadenaUrl+="inicioAdmin";
 }
 
 /***
  * 
  *@method cambiar roles Usuario->Admin
  *
  */
 @RequestMapping(value="/changeToAdmin", method = RequestMethod.POST)
 public String changeToAdmin(HttpServletResponse response, HttpServletRequest request, Model model) {
	Usuario usuario=(Usuario) request.getSession().getAttribute("usuarioConectado");
	String cadenaUrl="usuario/";
	try {
		Administrador admin=administradorDao.selectNombre(usuario.getNombre());
		if(admin.getNombre()!=null) {
			request.getSession().setAttribute("administradorConectado", admin);
			return cadenaUrl+="inicioAdmin";
		}
	}catch(Exception e) {
		model.addAttribute("alerta", "No tienes permisos de administrador" );
	}
	return cadenaUrl+="bienvenido";
 }
 
 /***
  * 
  *@method ejecucion cuando pulsamos el boton login
  *
  */
 @RequestMapping(value="/login", method = RequestMethod.POST)
 public String iniciarSesion(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
  String cadenaUrl="usuario/";
  String nombre=request.getParameter("txtUsuarioNombre");
  String clave=request.getParameter("txtUsuarioClave");
  if(clave=="" || nombre=="") {
	  model.addAttribute("alerta", "Por favor rellene los campos" );
	  return cadenaUrl+="login";
  }
 
  Administrador administrador= new Administrador();
  administrador.setNombre(nombre);
  administrador.setClave(clave);
  if(administradorDao.login(administrador) && request.getSession().getAttribute("administradorConectado")==null) {
   request.getSession().setAttribute("administradorConectado", administrador);
   return cadenaUrl+="inicioAdmin";
  }
   
  
  Usuario usuario = new Usuario();
  usuario.setNombre(nombre);
  usuario.setClave(clave);
  if(usuarioDao.login(usuario) && request.getSession().getAttribute("usuarioConectado")==null) {
   request.getSession().setAttribute("usuarioConectado", usuario);
   return cadenaUrl+="bienvenido";
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
 public ModelAndView cerrarSesion(HttpServletResponse response, HttpServletRequest request) throws Exception {
  HttpSession sesion = request.getSession();
  System.out.println("-----------------------------");
  System.out.println("Sesion antes de invalidar: "+sesion);
  sesion.invalidate();
  System.out.println("Invalidamos la sesion: "+sesion);
  System.out.println("-----------------------------");
  return cambiarVista("usuario/login");
 }
 /***
  * 
  * @method borrado de una cuenta propia
  *
  */
 @RequestMapping(value="/borrarCuenta", method = RequestMethod.POST)
 public ModelAndView borrarCuenta(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
  Usuario usuario=(Usuario) request.getSession().getAttribute("usuarioConectado");
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
   System.out.println("-----------------------------");
   System.out.println("Sesion antes de invalidar: "+sesion);
   sesion.invalidate();
   System.out.println("Invalidamos la sesion: "+sesion);
   System.out.println("-----------------------------");
   return cambiarVista("usuario/login");
  }else {
   model.addAttribute("alerta", "Error en las credenciales");
  }
  return cambiarVista("usuario/borradoCuenta");
 }
 
 /***
  * 
  *@method ejecucion cuando pulsamos el boton de registro
  *
  */
 @RequestMapping(value="/registrar", method = RequestMethod.POST)
 public String registrar(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl="usuario/";
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
	   model.addAttribute("alerta", "Nombre de usuario no disponible");
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
  String cadenaUrl="usuario/";
  String nombre=request.getParameter("txtNombre");
  Usuario usuario;
  if(nombre.equals("admin")) {
   model.addAttribute("alerta", "No puedes borrar al superadmin");
  }else {
   usuario=usuarioDao.selectNombre(nombre);
   if(usuario==null) {
    model.addAttribute("alerta", "No existe el usuario "+nombre);
   }else {
    usuarioDao.delete(usuario);
    administradorDao.delete(new Administrador(nombre));
   }

  }
  listarUsuario(request, response, model);
  cadenaUrl+="inicioAdmin";  
  return cadenaUrl;
 }
 
 /***
  * 
  *@method funcion del administrador de promover un usuario a admin
  *
  */
 @RequestMapping(value="/promover", method = RequestMethod.POST)
 public String promover(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl="usuario/";
  String nombre=request.getParameter("txtNombre");
  Usuario usuario = new Usuario();
  usuario.setNombre(nombre); 
  usuario=usuarioDao.selectNombre(nombre);
  if(usuario!=null) {
   Administrador admin=new Administrador(usuario.getNombre(), usuario.getClave(), usuario.getEmail());
   administradorDao.insertSinEncrypt(admin);
  }else{
   model.addAttribute("alerta", "El usuario que intentas promover no existe");
  }
  listarUsuario(request, response, model);
  cadenaUrl+="inicioAdmin";  
  return cadenaUrl;
 }
 /***
  * 
  *@method funcion del administrador de degradar un admin a usuario
  *
  */
 @RequestMapping(value="/degradar", method = RequestMethod.POST)
 public String degradar(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl="usuario/";
  String nombre=request.getParameter("txtNombre");
  Administrador admin;
  if(nombre.equals("admin")) {
   model.addAttribute("alerta", "<t><h2><b>No puedes degradar al superadmin</b></h2>");
  }else {
   admin=administradorDao.selectNombre(nombre);
   if(admin==null)
    model.addAttribute("alerta", "El administrador que intentas degradar no existe");
   else {
    administradorDao.delete(admin);
   }
  }
  listarUsuario(request, response, model);
  cadenaUrl+="inicioAdmin";  
  return cadenaUrl;
 }
 
 //@RequestMapping(value="/eliminar", method = RequestMethod.GET)
 /***
  * 
  * @method actualiza la ventana de administrador para ver sus usuarios/administradores
  * 
  */
 @RequestMapping(value="/listarUsuario", method = RequestMethod.POST)
 public String listarUsuario(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl="usuario/";
  model.addAttribute("usuarios", usuarioDao.list());
  model.addAttribute("administradores", administradorDao.list());
  cadenaUrl+="inicioAdmin";  
  return cadenaUrl;
 }
 /**
  * 
  *@method borrar una publicacion dado un ID
  */
 @RequestMapping(value="/eliminarPubli", method = RequestMethod.POST)
 public String eliminarPubli(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl="usuario/";
  String id=request.getParameter("txtIdPublicacion");
  publicacionDao.remove(id);
  listarPublicacion(request, response, model);
  cadenaUrl+="bienvenido";  
  return cadenaUrl;
 }
 
 /**
  * 
  *@method editar una publicacion dado un ID
  */
 @RequestMapping(value="/editarPubli", method = RequestMethod.POST)
 public String editarPubli(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl="usuario/";
  String texto=request.getParameter("txtIntroducirTexto");
  String id=request.getParameter("txtIdPublicacion");
  publicacionDao.update(id, texto);
  listarPublicacion(request, response, model);
  cadenaUrl+="bienvenido";  
  return cadenaUrl;
 }
 /***
  * 
  * @method permite crear una publicaci�n por parte de un usuario
  * 
  */
 @RequestMapping(value="/crearPublicacion", method = RequestMethod.POST)
 public String crearPublicacion(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl="usuario/";
  Usuario usuario;
  usuario=(Usuario) request.getSession().getAttribute("usuarioConectado");
  
  
  String nombre=usuario.getNombre();
  
  model.addAttribute("usuario", usuarioDao.selectNombre(nombre));
  String texto=request.getParameter("txtIntroducirTexto");

  try {
   utilidades.publicacionValida(nombre, texto);
  }catch(Exception e) {
   model.addAttribute("alerta", e.getMessage());
   return cadenaUrl+="bienvenido";
  }
  
  Publicacion publicacion= new Publicacion(usuario, texto);
  
  if(publicacionDao.existe(publicacion)) {
   model.addAttribute("alerta", "Nombre de usuario no disponible");
   return cadenaUrl+="bienvenido";
  }
  publicacionDao.insert(publicacion);
  listarPublicacion(request, response, model);
  return cadenaUrl+="bienvenido";
 }
 @RequestMapping(value="/crearPublicacionPrivada", method = RequestMethod.POST)
 public String crearPublicacionPrivada(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl="usuario/";
  Usuario usuario;
  usuario=(Usuario) request.getSession().getAttribute("usuarioConectado");
  
  
  String nombre=usuario.getNombre();
  
  model.addAttribute("usuario", usuarioDao.selectNombre(nombre));
  String texto=request.getParameter("txtIntroducirTexto");

  try {
   utilidades.publicacionValida(nombre, texto);
  }catch(Exception e) {
   model.addAttribute("alerta", e.getMessage());
   return cadenaUrl+="bienvenido";
  }
  
  Publicacion publicacion= new Publicacion(usuario, texto, "Privada");
  
  if(publicacionDao.existe(publicacion)) {
   model.addAttribute("alerta", "Nombre de usuario no disponible");
   return cadenaUrl+="bienvenido";
  }
  publicacionDao.insert(publicacion);
  listarPublicacion(request, response, model);
  return cadenaUrl+="bienvenido";
 }
 /***
  * 
  * @method permite ver las publicac
 iones realizadas por un usuario(de momento, luego cambiar a seg�n la visibilidad y amigos)
  * 
  */
 @RequestMapping(value="/listarPublicacion", method = RequestMethod.POST)
 public String listarPublicacion(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
  String cadenaUrl="usuario/";
  Usuario usuario;
  usuario=(Usuario) request.getSession().getAttribute("usuarioConectado");
  
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
	  texto+="<div class=\"panel panel-default\">\r\n" + 
	  		"	<div class=\"panel-body\">\r\n" + 
	  		"		<form action=\"editarPubli\" method=\"POST\">	\r\n" + 
	  		"			<b> "+ todas[i].getUsuario().getNombre() +" </b> \r\n" + 
	  		"			<textarea name=\"txtIntroducirTexto\" placeholder=\"�Qu� tal el d�a?\" class=\"form-control\" rows=\"5\" id=\"comment\">"+ todas[i].getTexto()+"</textarea>\r\n" + 
	  		"			<input name=\"txtIdPublicacion\" type=\"hidden\" class=\"form-control\" value=\""+todas[i].getId()+"\" id=\"usr\" placeholder=\"usuario\">" + 
	  		"			<button class=\"btn btn-danger btn-block btn-md login\" type=\"submit\">Editar</button>\r\n" + 
	  		"			<button class=\"btn btn-danger btn-block btn-md login\" formaction=\"eliminarPubli\" type=\"submit\">Eliminar</button>\r\n" + 
	  		"		</form>\r\n" + 
	  		"	</div>\r\n" + 
	  		"</div>	";
  }
  System.out.print(texto);
  model.addAttribute("publicaciones", texto);
  
  cadenaUrl+="bienvenido";  
  return cadenaUrl;
 }
 
 /***
  * 
  *@method cambiar a la ventana "bienvenido" desde la vista de usuario
  *
  */
 @RequestMapping(value="/bienvenido", method = RequestMethod.GET)
 public String bienvenido(HttpServletRequest request, HttpServletResponse response) throws Exception  {
  return "usuario/bienvenido";
 }
 
 //By JA
  @RequestMapping(value="/irRecuperarCredenciales", method = RequestMethod.GET)
  public ModelAndView irRecuperarCredenciales(HttpServletRequest request, HttpServletResponse response) throws Exception  {
   return cambiarVista("usuario/recuperarCredenciales");
  }
  
  //By JA
  @RequestMapping(value="/recuperarCredenciales", method = RequestMethod.POST)
  public String recuperarCredenciales(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception  {
   String nombre=request.getParameter("txtUsuarioNombre");
   String respuesta=request.getParameter("txtRespuesta");
   
   
   Usuario usuario = usuarioDao.selectNombre(nombre);
   int pin = (int)(Math.random() * (9999-1000+1)+1000);
   String pinEmail = "intravita"+String.valueOf(pin);
   
   try {
    utilidades.comprobacionNombre(nombre);
   }
   
   catch (Exception e) {
    
    model.addAttribute("alerta", e.getMessage());
    return "usuario/recuperarCredenciales";
    
   }
   
    
   if (usuario==null || (!respuesta.equals(usuario.getRespuesta()))) {
    System.out.println("M");
    //System.out.println(nombre+","+usuario.getNombre()+","+respuesta+","+usuario.getRespuesta());
    return "usuario/recuperarCredenciales";
   }
   
   else {
    MailSender mailSender= new MailSender();
    System.out.println("Estamos para mandar el correo");
    
    mailSender.sendMailRecoverPwd(usuario.getEmail() , pinEmail);
    usuario.setClave(pinEmail);
    usuarioDao.updatePwdEmail(usuario);
   }
   
   return "usuario/login";
   
  }
 
 
 
 
 
 
 /***
  * 
  *@method Esta funci�n sirve para controlar los cambios de vista por nombre(string)
  *
  */
 public ModelAndView cambiarVista(String nombreVista) {
  ModelAndView vista =new ModelAndView(nombreVista);
  return vista;
 }
}

