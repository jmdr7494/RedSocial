package com.intravita.proyectointranet.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.intravita.proyectointranet.email.MailSender;
import com.intravita.proyectointranet.modelo.Administrador;
import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.intravita.proyectointranet.persistencia.AdministradorDAOImpl;
import com.intravita.proyectointranet.persistencia.PublicacionDAOImpl;
import com.intravita.proyectointranet.persistencia.UsuarioDAOImpl;
import com.intravita.proyectointranet.utlidades.utilidades;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.BsonValue;
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
	AdministradorDAOImpl administradorDao = new AdministradorDAOImpl();
	PublicacionDAOImpl publicacionDao = new PublicacionDAOImpl();

	private final String welcome = "bienvenido";
	private final String usuario_login = "usuario/login";
	private final String usuarioServ = "usuario/";
	private final String ini_admin = "inicioAdmin";
	private final String admin_conect = "administradorConectado";
	private final String usuario_conect = "usuarioConectado";
	private final String alert = "alerta";
	private final String usuario_edit = "usuarioParaEditar";
 
 private static final Logger logger = LoggerFactory.getLogger(UsuarioServlet.class);
 
 /*@Bean
 public CommonsMultipartResolver multipartResolver() {

     CommonsMultipartResolver cmr = new CommonsMultipartResolver();
     cmr.setMaxUploadSize(maxUploadSizeInMb * 2);
     cmr.setMaxUploadSizePerFile(maxUploadSizeInMb); //bytes
     return cmr;

 }*/
 
 /*@Bean(name = "multipartResolver")
 public CommonsMultipartResolver multipartResolver() {
     CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
     multipartResolver.setMaxUploadSize(100000);
     return new CommonsMultipartResolver();
 }*/
 
 
 
	@RequestMapping(method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return usuario_login;
	}
 
	
	
 /***
  * @method metodos de navegacion entre jsp's
  */
	@RequestMapping(value = "/irLogin", method = RequestMethod.GET)
	public ModelAndView irLogin() {
		return cambiarVista(usuario_login);
	}

	
	
	@RequestMapping(value = "/irRegistrar", method = RequestMethod.GET)
	public ModelAndView irRegistrar(HttpServletRequest request) {
		request.setAttribute("usuarioRegistro", "");
		request.setAttribute("emailRegistro", "");
		return cambiarVista("usuario/registrar");
	}

	
	
	@RequestMapping(value = "/irAdmin", method = RequestMethod.GET)
	public ModelAndView irAdmin() {
		return cambiarVista("usuario/inicioAdmin");
	}

	
	
	@RequestMapping(value = "/irVerPublicaciones", method = RequestMethod.GET)
	public ModelAndView irVerPublicaciones() {
		return cambiarVista("usuario/verPublicaciones");
	}

	
	
	@RequestMapping(value = "/irBorradoCuenta", method = RequestMethod.GET)
	public ModelAndView irBorradoCuenta() {
		return cambiarVista("usuario/borradoCuenta");
	}

	
	
	@RequestMapping(value = "/irBienvenido", method = RequestMethod.GET)
	public ModelAndView irBienvenido() {
		return cambiarVista("usuario/bienvenido");
	}

	
	
	@RequestMapping(value = "/irVistaAmigos", method = RequestMethod.GET)
	public ModelAndView irVistaAmigos() {
		return cambiarVista("usuario/vistaAmigos");
	}

	
	
	@RequestMapping(value = "/irCrearCuentaDesdeAdmin", method = RequestMethod.GET)
	public ModelAndView irCrearCuentaDesdeAdmin() {
		return cambiarVista("usuario/registrarDesdeAdmin");
	}

	
	
	@RequestMapping(value = "/irPerfilUsuarioAdmin", method = RequestMethod.POST)
	public ModelAndView irPerfilUsuarioAdmin(HttpServletRequest request, Model model) {
		String nombre = request.getParameter("txtNombre");
		request.getSession().setAttribute(usuario_edit, new Usuario(nombre));
		return cambiarVista("usuario/perfilUsuarioAdmin");
	}
 
	
	
 /***
  * 
  *@method cambiar roles Admin->Usuario
  *
  */
 @RequestMapping(value="/changeToUser", method = RequestMethod.POST)
 public String changeToUser(HttpServletRequest request, Model model) {
	Administrador admin=(Administrador) request.getSession().getAttribute(admin_conect);
	String cadenaUrl=usuarioServ;
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
	@RequestMapping(value = "/changeToAdmin", method = RequestMethod.POST)
	public String changeToAdmin(HttpServletRequest request, Model model) {
		Usuario usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		String cadenaUrl = usuarioServ;
		try {
			Administrador admin = administradorDao.selectNombre(usuario.getNombre());
			if (admin.getNombre() != null) {
				request.getSession().setAttribute(admin_conect, admin);
				cadenaUrl += ini_admin;
				return cadenaUrl;
			}
		} catch (Exception e) {
			model.addAttribute(alert, "No tienes permisos de administrador");
		}
		return cadenaUrl += welcome;
	}
 
	
	
	/***
	 * 
	 * @method ejecucion cuando pulsamos el boton login
	 *
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String iniciarSesion(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		String nombre = request.getParameter("txtUsuarioNombre");
		String clave = request.getParameter("txtUsuarioClave");
		if (clave.equals("") || nombre.equals("")) {
			model.addAttribute(alert, "Por favor rellene los campos");
			return cadenaUrl += "login";
		}

		Administrador administrador = new Administrador();
		administrador.setNombre(nombre);
		administrador.setClave(clave);
		if (administradorDao.login(administrador) && request.getSession().getAttribute(admin_conect) == null) {
			request.getSession().setAttribute(admin_conect, administrador);
			return cadenaUrl += ini_admin;
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setClave(clave);

		if (usuarioDao.login(usuario) && request.getSession().getAttribute(usuario_conect) == null) {
			usuario = usuarioDao.selectNombreImagen(nombre);
			request.getSession().setAttribute(usuario_conect, usuario);
			String base64Encoded = DatatypeConverter.printBase64Binary(usuario.getImagen());
			model.addAttribute("imagen", base64Encoded);

			return cadenaUrl += welcome;
		}

		model.addAttribute("alerta", "Usuario y/o clave incorrectos");
		return cadenaUrl += "login";
	}

	
	
	/***
	 * 
	 * @method ejecucion cuando pulsamos el boton logout
	 *
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView cerrarSesion(HttpServletRequest request) throws Exception {
		HttpSession sesion = request.getSession();

		System.out.println("Sesion antes de invalidar: " + sesion);
		sesion.invalidate();
		System.out.println("Invalidamos la sesion: " + sesion);

		return cambiarVista(usuario_login);
	}

	
	
	/***
	 * 
	 * @method borrado de una cuenta propia
	 *
	 */
	@RequestMapping(value = "/borrarCuenta", method = RequestMethod.POST)
	public ModelAndView borrarCuenta(HttpServletRequest request, Model model) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		String nombre = usuario.getNombre();
		Usuario aux = usuarioDao.selectNombre(nombre);

		model.addAttribute("usuario", aux);
		String pwd = request.getParameter("txtUsuarioPwd");
		pwd = DigestUtils.md5Hex(pwd);
		String respuesta = request.getParameter("txtRespuesta");
		if (pwd.equals(aux.getClave()) && respuesta.equals(aux.getRespuesta())) {
			usuarioDao.delete(usuario);
			administradorDao.delete(new Administrador(nombre));
			HttpSession sesion = request.getSession();

			System.out.println("Sesion antes de invalidar: " + sesion);
			sesion.invalidate();
			System.out.println("Invalidamos la sesion: " + sesion);

			return cambiarVista(usuario_login);
		} else {
			model.addAttribute(alert, "Error en las credenciales");
		}
		return cambiarVista("usuario/borradoCuenta");
	}
 
	
	
	/***
	 * 
	 * @method crear cuenta de un usuario desde la ventana de administrador
	 *
	 */

	@RequestMapping(value = "/registrarDesdeAdmin", method = RequestMethod.POST)
	public String registrarDesdeAdmin(HttpServletRequest request, Model model) throws Exception {
		String registrar = "registrarDesdeAdmin", volver = "inicioAdmin";
		String cadenaUrl = usuarioServ;
		String nombre = request.getParameter("txtUsuarioNombre");
		String email = request.getParameter("txtEmail");
		String pwd1 = request.getParameter("txtUsuarioClave");
		String pwd2 = request.getParameter("txtUsuarioClave1");
		String respuesta = request.getParameter("txtRespuesta");

		try {
			utilidades.credencialesValidas(nombre, email, pwd1, pwd2);
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
			return cadenaUrl += registrar;
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setClave(pwd1);
		usuario.setRespuesta(respuesta);
		usuario.setNombreImagen("nombreImagen");

		try {
			usuarioDao.insertConImagen(usuario);
		} catch (Exception e) {
			model.addAttribute(alert, "Nombre de usuario no disponible");
			return cadenaUrl += registrar;
		}

		return cadenaUrl += volver;
	}

	
	
	/***
	 * 
	 * @method crear cuenta de un usuario
	 *
	 */
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public String registrar(HttpServletRequest request, Model model) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// MultipartFile multipartFile = multipartRequest.getFile("file");
		CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile("fichero");
		String cadenaUrl = usuarioServ;
		String nombre = request.getParameter("txtUsuarioNombre");
		String email = request.getParameter("txtEmail");
		String pwd1 = request.getParameter("txtUsuarioClave");
		String pwd2 = request.getParameter("txtUsuarioClave1");
		String respuesta = request.getParameter("txtRespuesta");

		String nombreImagen = request.getParameter("file");
		System.out.println("Nombre de la imagen:" + nombreImagen);
		// byte[] imagen=new byte[multipartFile.getBytes().length];
		// imagen=multipartFile.getBytes();
		byte[] imagen = multipartFile.getBytes();
		// File imagen=model.addAttribute("file", imagen).;

		System.out.println("Esta es la imagen: " + imagen.toString());

		try {
			utilidades.credencialesValidas(nombre, email, pwd1, pwd2);
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
			if (e.getMessage().equals("Email invalido")) {
				request.setAttribute("usuarioRegistro", nombre);
				request.setAttribute("emailRegistro", "");
			}
			if (e.getMessage().equals("No coinciden las password")) {
				request.setAttribute("usuarioRegistro", nombre);
				request.setAttribute("emailRegistro", email);
			}
			if (e.getMessage().equals("Por favor rellene todos los campos")) {
				request.setAttribute("usuarioRegistro", "");
				request.setAttribute("emailRegistro", "");
			}
		
			
			
			return cadenaUrl += "registrar";
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setClave(pwd1);
		usuario.setRespuesta(respuesta);
		usuario.setNombreImagen("nombreImagen");
		usuario.setImagen(imagen);
		System.out.println("llega abajode");
		try {
			usuarioDao.insertConImagen(usuario);
		} catch (Exception e) {
			// model.addAttribute(alert, "Nombre de usuario no disponible");
			model.addAttribute(alert, e);
			request.setAttribute("emailRegistro", email);
			return cadenaUrl += "registrar";
		}
		System.out.println("llega a la victoria");
		HttpSession session = request.getSession();
		session.setAttribute("alertaRegistro", "Mandando alerta registro");
		
		return cadenaUrl+="registrar";

	}
	
	
	
	/***
	 * 
	 * @method metodo de borrado de un usuario desde el administrador
	 *
	 */
	@RequestMapping(value = "/borrar", method = RequestMethod.POST)
	public String borrar(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		String nombre = request.getParameter("txtNombre");
		Usuario usuario;
		Administrador administrador = (Administrador) request.getSession().getAttribute(admin_conect);
		if (nombre.equals(administrador.getNombre())) {
			model.addAttribute(alert, "No puedes realizar acciones sobre ti");
		} else {
			if (nombre.equals("admin")) {
				model.addAttribute(alert, "No puedes borrar al superadmin");
			} else {
				usuario = usuarioDao.selectNombre(nombre);
				if (usuario == null) {
					model.addAttribute(alert, "No existe el usuario " + nombre);
				} else {
					usuarioDao.delete(usuario);
					administradorDao.delete(new Administrador(nombre));
				}
			}
		}
		listarUsuario(model);
		cadenaUrl += ini_admin;
		return cadenaUrl;
	}

	
	
	/***
	 * 
	 * @method funcion del administrador de promover un usuario a admin
	 *
	 */
	@RequestMapping(value = "/promover", method = RequestMethod.POST)
	public String promover(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		String nombre = request.getParameter("txtNombre");
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario = usuarioDao.selectNombre(nombre);
		Administrador administrador = (Administrador) request.getSession().getAttribute(admin_conect);
		if (nombre.equals(administrador.getNombre())) {
			model.addAttribute(alert, "No puedes realizar acciones sobre ti");
		} else {
			if (usuario != null) {
				Administrador admin = new Administrador(usuario.getNombre(), usuario.getClave(), usuario.getEmail());
				administradorDao.insertSinEncrypt(admin);
			} else {
				model.addAttribute(alert, "El usuario que intentas promover no existe");
			}
		}
		listarUsuario(model);
		cadenaUrl += ini_admin;
		return cadenaUrl;
	}

	
	
	/***
	 * 
	 * @method funcion del administrador de degradar un admin a usuario
	 *
	 */
	@RequestMapping(value = "/degradar", method = RequestMethod.POST)
	public String degradar(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		String nombre = request.getParameter("txtNombre");
		Administrador admin;
		Administrador administrador = (Administrador) request.getSession().getAttribute(admin_conect);
		if (nombre.equals(administrador.getNombre())) {
			model.addAttribute(alert, "No puedes realizar acciones sobre ti");
		} else {
			if (nombre.equals("admin")) {
				model.addAttribute(alert, "<t><h2><b>No puedes degradar al superadmin</b></h2>");
			} else {
				admin = administradorDao.selectNombre(nombre);
				if (admin == null)
					model.addAttribute(alert, "El administrador que intentas degradar no existe");
				else {
					administradorDao.delete(admin);
				}
			}
		}
		listarUsuario(model);
		cadenaUrl += ini_admin;
		return cadenaUrl;
	}

	
	
	/***
	 * 
	 * @method actualiza la ventana de administrador para ver sus
	 *         usuarios/administradores
	 * 
	 */
	@RequestMapping(value = "/listarUsuario", method = RequestMethod.POST)
	public String listarUsuario(Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		model.addAttribute("usuarios", utilidades.listarUsuarios());
		model.addAttribute("administradores", utilidades.listarAdministradores());
		cadenaUrl += ini_admin;
		return cadenaUrl;
	}

	
	
	/**
	 * 
	 * @method borrar una publicacion dado un ID
	 */
	@RequestMapping(value = "/eliminarPubli", method = RequestMethod.POST)
	public String eliminarPubli(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		String id = request.getParameter("txtIdPublicacion");
		publicacionDao.remove(id);
		listarPublicacion(request, model);
		cadenaUrl += welcome;
		return cadenaUrl;
	}

	
	
	/**
	 * 
	 * @method editar una publicacion dado un ID
	 */
	@RequestMapping(value = "/editarPubli", method = RequestMethod.POST)
	public String editarPubli(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		String texto = request.getParameter("txtIntroducirTexto");
		String id = request.getParameter("txtIdPublicacion");
		publicacionDao.update(id, texto);
		listarPublicacion(request, model);
		cadenaUrl += welcome;
		return cadenaUrl;
	}

	
	
	/**
	 * 
	 * @method borrar una publicacion dado un ID desde el administrador
	 */
	@RequestMapping(value = "/eliminarPubliAdmin", method = RequestMethod.POST)
	public String eliminarPubliAdmin(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		String id = request.getParameter("txtIdPublicacion");
		publicacionDao.remove(id);
		listarPublicacionUsuario(request, model);
		cadenaUrl += "perfilUsuarioAdmin";
		return cadenaUrl;
	}

	
	
	/**
	 * 
	 * @method editar una publicacion dado un ID desde el administrador
	 */
	@RequestMapping(value = "/editarPubliAdmin", method = RequestMethod.POST)
	public String editarPubliAdmin(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		String texto = request.getParameter("txtIntroducirTexto");
		String id = request.getParameter("txtIdPublicacion");
		publicacionDao.update(id, texto);
		listarPublicacionUsuario(request, model);
		cadenaUrl += "perfilUsuarioAdmin";
		return cadenaUrl;
	}
	
	
	
	/***
	 * 
	 * @method permite crear una publicacion por parte de un usuario
	 * 
	 */
	@RequestMapping(value = "/crearPublicacion", method = RequestMethod.POST)
	public String crearPublicacion(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);

		String nombre = usuario.getNombre();

		model.addAttribute("usuario", usuarioDao.selectNombre(nombre));
		String texto = request.getParameter("txtIntroducirTexto");

		try {
			utilidades.publicacionValida(nombre, texto);
		} catch (Exception e) {
			model.addAttribute(alert, e.getMessage());
			cadenaUrl += welcome;
			return cadenaUrl;
		}

		Publicacion publicacion = new Publicacion(usuario, texto);

		if (publicacionDao.existe(publicacion)) {
			model.addAttribute(alert, "Nombre de usuario no disponible");
			return cadenaUrl += welcome;
		}
		publicacionDao.insert(publicacion);
		listarPublicacion(request, model);
		cadenaUrl += welcome;
		return cadenaUrl;
	}
 
	
	
	
	@RequestMapping(value = "/crearPublicacionPrivada", method = RequestMethod.POST)
	public String crearPublicacionPrivada(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute("usuarioConectado");

		String nombre = usuario.getNombre();

		model.addAttribute("usuario", usuarioDao.selectNombre(nombre));
		String texto = request.getParameter("txtIntroducirTexto");

		try {
			utilidades.publicacionValida(nombre, texto);
		} catch (Exception e) {
			model.addAttribute(alert, e.getMessage());
			cadenaUrl += welcome;
			return cadenaUrl;
		}

		Publicacion publicacion = new Publicacion(usuario, texto, "Privada");

		if (publicacionDao.existe(publicacion)) {
			model.addAttribute(alert, "Nombre de usuario no disponible");
			cadenaUrl += welcome;
			return cadenaUrl;
		}
		publicacionDao.insert(publicacion);
		listarPublicacion(request, model);
		cadenaUrl += welcome;
		return cadenaUrl;
	}
 
	
	
	
 /***
 /***
  * 
  * @method permite ver las publicaciones realizadas por un usuario y borrarlas para que un admin las borre
  * 
  */
	@RequestMapping(value = "/listarPublicacionUsuario", method = RequestMethod.POST)
	public String listarPublicacionUsuario(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		Usuario usuario = (Usuario) request.getSession().getAttribute(usuario_edit);
		ArrayList<Publicacion> publicas = publicacionDao.selectPublicas(usuario);
		ArrayList<Publicacion> privadas = publicacionDao.selectPrivadas(usuario);
		Publicacion[] todas = utilidades.mostrarPublicaciones(publicas, privadas);
		String texto = "";
		String nombre = "";
		for (int i = 0; i < todas.length; i++) {
			nombre = todas[i].getUsuario().getNombre();
	  texto = texto+"<div class=\"panel panel-default\">\r\n" + 
		  		"		<div class=\"panel-body\">\r\n" + 
		  		"			<b> "+ nombre +" </b> \r\n" + 
		  		"			<textarea name=\"txtIntroducirTexto\" placeholder=\"¿Qué tal el día?\" class=\"form-control\" rows=\"5\" id=\"comment\" disabled>"+ todas[i].getTexto()+"</textarea>\r\n" + 
		  		"			<input name=\"txtIdPublicacion\" type=\"hidden\" class=\"form-control\" value=\""+todas[i].getId()+"\" id=\"usr\" placeholder=\"usuario\">" + 
		  		
				"			<div class=\"col-md-3 col-md-offset-9\">"+
				"				<br>\r\n" +
				"				<button class=\"btn btn-danger btn-block btn-md login\" type=\"submit\" data-toggle=\"modal\" data-target=\"#miModalss\">Eliminar</button>\r\n" +
				"			</div>\r\n" +
				"			<div class=\"modal fade\" id=\"miModalss\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalssLabel\" aria-hidden=\"true\">\r\n" + 
				"				<div class=\"modal-dialog\" role=\"document\">\r\n" + 
				"					<div class=\"modal-content\">\r\n" + 
				"						<div class=\"modal-header\">\r\n" + 
				"							<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\r\n" + 
				"								<span aria-hidden=\"true\">&times;</span>\r\n" + 
				"							</button>\r\n" + 
				"							<h4 class=\"modal-title\" id=\"myModalssLabel\">Eliminar</h4>\r\n" + 
				"						</div>\r\n" + 
				"						<div class=\"modal-body\">\r\n" + 
				"							¿Está seguro que desea eliminar la publicación?\r\n" + 
				"							<br>\r\n" + 
				"							<form action=\"eliminarPubliAdmin\" method=\"POST\">\r\n" + 
				"								<input name=\"txtIdPublicacion\" type=\"hidden\" class=\"form-control\" value=\""+todas[i].getId()+"\" id=\"usr\" placeholder=\"usuario\">" + 
				"								<br>" + 
				"								<button class=\"btn btn-success btn-block btn-md login\" type=\"submit\">Si</button>\r\n" + 
				"							</form>\r\n" + 
				"							<form action=\"listarPublicacionUsuario\" method=\"POST\">\r\n" + 
				"								<br>\r\n" + 
				"								<button class=\"btn btn-danger btn-block btn-md login\" type=\"submit\">No</button>\r\n" + 
				"							</form>\r\n" + 
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"			</div>" + 
				"		</div>\r\n" + 
				"	</div>	";		  
		
		  }
		  model.addAttribute("publicaciones", texto);
		  mostrarPerfilAdmin(request, model);
		  cadenaUrl+="perfilUsuarioAdmin";  
		  return cadenaUrl;
		 }
 
	
	
	
	/***
	 * 
	 * @method permite ver las publicaciones realizadas por un usuario, sus amigos y
	 *         lo que comparten y editar/borrar las propias
	 * 
	 */
	@RequestMapping(value = "/listarPublicacion", method = RequestMethod.POST)
	public String listarPublicacion(HttpServletRequest request, Model model) {
		String cadenaUrl = usuarioServ;
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);

		ArrayList<Publicacion> publicas = publicacionDao.selectPublicas(usuario);
		ArrayList<Publicacion> privadas = publicacionDao.selectPrivadas(usuario);
		List<BsonValue> amigos = usuarioDao.obtenerAmigos(usuario);
		ArrayList<Publicacion> publicasAmigos = new ArrayList<Publicacion>();
		ArrayList<Publicacion> aux;
		ArrayList<Publicacion> compartidasAmigos = new ArrayList<Publicacion>();
		BsonValue element;

		Iterator<BsonValue> it = amigos.iterator();
		while (it.hasNext()) {
			element = it.next();
			aux = publicacionDao.selectPublicas(new Usuario(element.asString().getValue()));
			publicasAmigos.addAll(aux);
			aux = utilidades.obtenerCompartidos(element.asString().getValue());
			compartidasAmigos.addAll(aux);
		}
		publicas.addAll(publicasAmigos);
		publicas.addAll(compartidasAmigos);
		Publicacion[] todas = utilidades.mostrarPublicaciones(publicas, privadas);
		String texto = "";
		String nombre = "";
		for (int i = 0; i < todas.length; i++) {
			nombre = todas[i].getUsuario().getNombre();
			if (nombre.equals(usuario.getNombre())) {
				texto = texto + "<div class=\"panel panel-default\">\r\n" + 
				  		"	<div class=\"panel-body\">\r\n" + 
				  		"			<b> "+ nombre +" </b> \r\n" + 
				  		"			<textarea name=\"txtIntroducirTexto\" placeholder=\"ÃƒÂ¯Ã‚Â¿Ã‚Â½QuÃƒÂ¯Ã‚Â¿Ã‚Â½ tal el dÃƒÂ¯Ã‚Â¿Ã‚Â½a?\" class=\"form-control\" rows=\"5\" id=\"comment\" disabled>"+ todas[i].getTexto()+"</textarea>\r\n" + 
				  		"			<input name=\"txtIdPublicacion\" type=\"hidden\" class=\"form-control\" value=\""+todas[i].getId()+"\" id=\"usr\" placeholder=\"usuario\">" + 
				  		"<br>"+
				  		"<div class=\"row\">\r\n" + 
				  		"	<div class=\"col-md-1 col-md-offset-9\">\r\n" + 
				  		"		<button class=\"btn btn-primary\" type=\"submit\" data-toggle=\"modal\" data-target=\"#miModals\" title=\"Editar Publicación\"><strong><span class=\"glyphicon glyphicon-edit\"></span>&nbsp;Editar</strong></button>\r\n" + 
				  		"	</div>	 \r\n" +
				  		"		<div class=\"modal fade\" id=\"miModals\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalsLabel\" aria-hidden=\"true\">\r\n" + 
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
				  		"							<textarea name=\"txtIntroducirTexto\" placeholder=\"¿Qué tal el día?\" class=\"form-control\" rows=\"5\" id=\"comment\">"+ todas[i].getTexto()+"</textarea>\r\n" + 
				  		"							<input name=\"txtIdPublicacion\" type=\"hidden\" class=\"form-control\" value=\""+todas[i].getId()+"\" id=\"usr\" placeholder=\"usuario\">" + 
				  		"							<br>		"	+
				  		"							<div class=\"row\">	"+
				  		"								<div class=\"col-md-1 col-md-offset-8\">"+		
				  		"								<button class=\"btn btn-success\" type=\"submit\"><strong><span class=\"glyphicon glyphicon-ok\"></span>&nbsp;Si</strong></button>\r\n" + 
				  		"							</div>"+	
				  		"						</form>\r\n" + 
				  		"						<div class=\"col-md-1 col-md-offset-1\">"+
				  		"						<form action=\"listarPublicacion\" method=\"POST\">\r\n" +  
				  		"							<button class=\"btn btn-danger\" type=\"submit\"><strong><span class=\"glyphicon glyphicon-remove\"></span>&nbsp;No</strong></button>\r\n" + 
				  		"						</form>\r\n" + 
				  		"						</div></div>"+	
				  		"					</div>\r\n" + 
				  		"				</div>\r\n" + 
				  		"			</div>\r\n" + 
				  		"		</div>" +
				  		
				  		
						"	<div class=\"col-md-1\">"+
						"		<button type=\"submit\" class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#miModalss\" title=\"Eliminar Publicación\"><strong><span class=\"glyphicon glyphicon-trash\"></span>&nbsp;Eliminar</strong></button>\r\n" +
						"  	</div>"+	 
						"</div>"+
						"<div class=\"modal fade\" id=\"miModalss\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalssLabel\" aria-hidden=\"true\">\r\n" + 
						"			<div class=\"modal-dialog\" role=\"document\">\r\n" + 
						"				<div class=\"modal-content\">\r\n" + 
						"					<div class=\"modal-header\">\r\n" + 
						"						<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\r\n" + 
						"							<span aria-hidden=\"true\">&times;</span>\r\n" + 
						"						</button>\r\n" + 
						"						<h4 class=\"modal-title\" id=\"myModalssLabel\">Eliminar</h4>\r\n" + 
						"					</div>\r\n" + 
						"					<div class=\"modal-body\">\r\n" + 
						"						¿Está seguro que desea eliminar la publicación?\r\n" + 
						"						<br>\r\n" + 
						"						<form action=\"eliminarPubli\" method=\"POST\">\r\n" + 
						"							<input name=\"txtIdPublicacion\" type=\"hidden\" class=\"form-control\" value=\""+todas[i].getId()+"\" id=\"usr\" placeholder=\"usuario\">" + 
						"							<br>" + 
						"							<div class=\"row\">	"+
				  		"								<div class=\"col-md-1 col-md-offset-8\">"+		
				  		"								<button class=\"btn btn-success\" type=\"submit\"><strong><span class=\"glyphicon glyphicon-ok\"></span>&nbsp;Si</strong></button>\r\n" + 
				  		"							</div>		"+ 
						"						</form>\r\n" + 
				  		"						<div class=\"col-md-1 col-md-offset-1\">"+	  		
						"						<form action=\"listarPublicacion\" method=\"POST\">\r\n" + 
				  		"							<button class=\"btn btn-danger\" type=\"submit\"><strong><span class=\"glyphicon glyphicon-remove\"></span>&nbsp;No</strong></button>\r\n" + 
				  		"						</form>\r\n" + 
				  		"						</div></div>"+	
						"					</div>\r\n" + 
						"				</div>\r\n" + 
						"			</div>\r\n" + 
						"		</div>" + 
						  		"	</div>\r\n" + 
						  		"</div>	";		  
			  }else {
				  
				  texto+="<div class=\"panel panel-default\">\r\n" + 
					  		"	<div class=\"panel-body\">\r\n" + 
					  		"		<b> "+nombre+"</b>\r\n" + 
					  		"		<textarea name=\"txtIntroducirTexto\" class=\"form-control\" rows=\"5\" id=\"comment\" disabled>"+ todas[i].getTexto()+"</textarea>\r\n" + 
					  		"		<br>\r\n" + 
					  		"			<div class=\"row\">	"+
					  		"				<div class=\"col-md-2 col-md-offset-10\">"+
					  		"					<form action=\"compartir\" method=\"post\">\r\n" + 
					  		"						<input name=\"txtIdPublicacion\" type=\"hidden\" class=\"form-control\" value=\""+todas[i].getId()+"\" id=\"ID\">\r\n" + 
					  		"						<button type=\"submit\" class=\"btn btn-primary\" title=\""+todas[i].textoCompartido()+"\"><strong><center><span class=\"glyphicon glyphicon-retweet\"></span>&nbsp; Compartir</center></strong></button>\r\n" + 
					  		"					</form>\r\n" +
					  		"				</div>				" +
					  		"			</div>					" +	
					  		"	</div>\r\n" + 
					  		"</div>";
			  }

				  
				  
			
				  }
				  model.addAttribute("publicaciones", texto);
				  
				  cadenaUrl+=welcome;  
				  return cadenaUrl;
	}
 
	
	
	/***
	 * 
	 * @method cambiar a la ventana "bienvenido" desde la vista de usuario
	 *
	 */
	@RequestMapping(value = "/bienvenido", method = RequestMethod.GET)
	public String bienvenido() throws Exception {
		return "usuario/bienvenido";
	}

	// By JA
	@RequestMapping(value = "/irRecuperarCredenciales", method = RequestMethod.GET)
	public ModelAndView irRecuperarCredenciales() throws Exception {
		return cambiarVista("usuario/recuperarCredenciales");
	}

	
	
	// By JA
	@RequestMapping(value = "/recuperarCredenciales", method = RequestMethod.POST)
	public String recuperarCredenciales(HttpServletRequest request, Model model) throws Exception {
		String nombre = request.getParameter("txtUsuarioNombre");
		String respuesta = request.getParameter("txtRespuesta");

		Usuario usuario = usuarioDao.selectNombre(nombre);
		int pin = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
		String pinEmail = "intravita" + String.valueOf(pin);

		try {
			utilidades.comprobacionNombre(nombre);
		} catch (Exception e) {
			model.addAttribute(alert, e.getMessage());
			return "usuario/recuperarCredenciales";
		}

		if (usuario == null || (!respuesta.equals(usuario.getRespuesta()))) {
			model.addAttribute(alert, "Datos incorrectos");
			return "usuario/recuperarCredenciales";
		} else {
			MailSender mailSender = new MailSender();
			System.out.println("Estamos para mandar el correo");

			mailSender.sendMailRecoverPwd(usuario.getEmail(), pinEmail);
			usuario.setClave(pinEmail);
			usuarioDao.updatePwd(usuario);
		}

		return "usuario/reestablecerPwd";

	}

	
	
	// By JA
	@RequestMapping(value = "/reestablecerPwd", method = RequestMethod.POST)
	public String reestablecerPwd(HttpServletRequest request, Model model) throws Exception {
		String pwdTemporal = DigestUtils.md5Hex(request.getParameter("txtPwdTemporal"));
		String pwdNueva1 = request.getParameter("txtPwdNueva1");
		String pwdNueva2 = request.getParameter("txtPwdNueva2");

		Usuario usuario = usuarioDao.selectPwd(pwdTemporal);// buscar encriptada

		if (usuario == null || !(pwdNueva1.equals(pwdNueva2))) {
			model.addAttribute(alert, "Datos incorrectos");
			return "usuario/reestablecerPwd";

		}

		try {
			utilidades.seguridadPassword(pwdNueva1);
		} catch (Exception e) {
			model.addAttribute(alert, e.getMessage());
			return "usuario/reestablecerPwd";
		}

		usuario.setClave(pwdNueva1);
		usuarioDao.updatePwd(usuario);

		return "usuario/login";

	}

	
	
	/**
	 * 
	 * @return dado un filtro busca todas las coincidencias
	 * @throws Exception
	 */
	@RequestMapping(value = "/buscarAmigos", method = RequestMethod.POST)
	public String buscarAmigos(HttpServletRequest request, Model model) throws Exception {
		String filtro = request.getParameter("txtUsuarioNombre");
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		model.addAttribute("amigos", utilidades.buscadorUsuario(usuario, filtro));
		mostrarNotificaciones(request, model);
		return "usuario/vistaAmigos";

	}

	
	
	/**
	 * 
	 * @return enviar solicitud a la persona seleccionado
	 * @throws Exception
	 */
	@RequestMapping(value = "/enviarSolicitud", method = RequestMethod.POST)
	public String enviarSolicitud(HttpServletRequest request, Model model) throws Exception {
		String receptor = request.getParameter("txtNombreEnviar");
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		try {
			utilidades.enviarSolicitud(usuario, new Usuario(receptor));
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
		}
		mostrarNotificaciones(request, model);
		return "usuario/vistaAmigos";
	}

	
	
	/**
	 * 
	 * @return eliminar el amigo seleccionado
	 * @throws Exception
	 */
	@RequestMapping(value = "/eliminarAmigo", method = RequestMethod.POST)
	public String eliminarAmigo(HttpServletRequest request, Model model) throws Exception {
		String receptor = request.getParameter("txtNombreEliminar");
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		try {
			utilidades.borrarAmistad(usuario, new Usuario(receptor));
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
		}
		mostrarNotificaciones(request, model);
		return "usuario/vistaAmigos";
	}

	
	
	/**
	 * 
	 * @return aceptar solicitud
	 * @throws Exception
	 */
	@RequestMapping(value = "/aceptarSolicitud", method = RequestMethod.POST)
	public String aceptarSolicitud(HttpServletRequest request, Model model) throws Exception {
		String emisor = request.getParameter("txtNombre");
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		try {
			utilidades.aceptarSolicitud(new Usuario(emisor), usuario);
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
		}
		mostrarNotificaciones(request, model);
		return "usuario/vistaAmigos";
	}

	
	
	/**
	 * 
	 * @return rechazar solicitud
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechazarSolicitud", method = RequestMethod.POST)
	public String rechazarSolicitud(HttpServletRequest request, Model model) throws Exception {
		String emisor = request.getParameter("txtNombre");
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		try {
			utilidades.rechazarSolicitud(new Usuario(emisor), usuario);
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
		}
		mostrarNotificaciones(request, model);
		return "usuario/vistaAmigos";
	}

	
	
	@RequestMapping(value = "/mostrarNotificaciones", method = RequestMethod.GET)
	public String mostrarNotificaciones(HttpServletRequest request, Model model) throws Exception {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		model.addAttribute("notificaciones", utilidades.mostrarNotificaciones(usuario));
		return "usuario/vistaAmigos";
	}
	
	

	/**
	 * @return muestra los datos de un usuario en la vista de admin y permite editar
	 *         nombre y pwd
	 */
	@RequestMapping(value = "/mostrarPerfilAdmin", method = RequestMethod.GET)
	public String mostrarPerfilAdmin(HttpServletRequest request, Model model) throws Exception {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_edit);
		model.addAttribute("perfil", utilidades.mostrarPerfilAdmin(usuario));
		return "usuario/perfilUsuarioAdmin";
	}

	
	
	/**
	 * @return editar Pwd de un usuario desde la vista de admin
	 */
	@RequestMapping(value = "/editarPwd", method = RequestMethod.GET)
	public String editarPwd(HttpServletRequest request, Model model) throws Exception {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_edit);
		usuario.setClave(request.getParameter("txtPWD"));
		usuarioDao.updatePwd(usuario);
		model.addAttribute("perfil", utilidades.mostrarPerfilAdmin(usuario));
		return "usuario/perfilUsuarioAdmin";
	}

	
	
	@RequestMapping(value = "/editarNombre", method = RequestMethod.GET)
	public String editarNombre(HttpServletRequest request, Model model) {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_edit);
		String nuevoNombre = request.getParameter("txtNombre");
		try {
			utilidades.comprobacionNombre(nuevoNombre);
			usuarioDao.updateNombre(usuario.getNombre(), nuevoNombre);
			publicacionDao.updateNombre(usuario.getNombre(), nuevoNombre);
			usuario.setNombre(nuevoNombre);
			request.getSession().setAttribute(usuario_edit, usuario);
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
		}
		model.addAttribute("perfil", utilidades.mostrarPerfilAdmin(usuario));
		return "usuario/perfilUsuarioAdmin";
	}

	
	
	@RequestMapping(value = "/compartir", method = RequestMethod.POST)
	public String compartir(HttpServletRequest request, Model model) {
		String cadenaUrl = usuarioServ;

		Usuario usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		String id = request.getParameter("txtIdPublicacion");
		Publicacion publicacion = new Publicacion(new Usuario("autor"), "texto");
		publicacion.setId(id);
		publicacion = publicacionDao.selectOne(publicacion);
		try {
			utilidades.compartirPublicacion(usuario, publicacion);
		} catch (Exception e) {
			try {
				utilidades.dejarCompartirPublicacion(usuario, publicacion);
			} catch (Exception e1) {
				model.addAttribute("alerta", e1.getMessage());
			}
		}
		listarPublicacion(request, model);
		cadenaUrl += welcome;
		return cadenaUrl;
	}

	
	
	/***
	 * 
	 * @method Esta funcion sirve para controlar los cambios de vista por
	 *         nombre(string)
	 *
	 */

	// By JA
	@RequestMapping(value = "/irPerfilUsuario", method = RequestMethod.GET)
	public ModelAndView irPerfilUsuario(HttpServletRequest request) throws Exception {
		Usuario usuarioLigero = (Usuario) request.getSession().getAttribute(usuario_conect);
		Usuario usuario = usuarioDao.selectNombre(usuarioLigero.getNombre());
		System.out.println(usuario.getNombre());
		System.out.println(usuario.getEmail());
		request.setAttribute("usuarioNombre", usuario.getNombre());
		request.setAttribute("usuarioEmail", usuario.getEmail());
		return cambiarVista("usuario/perfilUsuario");
	}

	
	
	// By JA
	@RequestMapping(value = "/modificarPerfilUsuario", method = RequestMethod.POST)
	public String modificarPerfilUsuario(HttpServletRequest request, Model model) throws Exception {

		String pwdNueva1 = request.getParameter("txtPwdNueva1");
		String pwdNueva2 = request.getParameter("txtPwdNueva2");

		Usuario usuarioLigero = (Usuario) request.getSession().getAttribute(usuario_conect);
		Usuario usuario = usuarioDao.selectNombre(usuarioLigero.getNombre());
		if (usuario == null || !(pwdNueva1.equals(pwdNueva2))) {
			request.setAttribute("usuarioNombre", usuario.getNombre());
			request.setAttribute("usuarioEmail", usuario.getEmail());
			model.addAttribute(alert, "Datos incorrectos");
			return "usuario/perfilUsuario";

		}

		try {
			utilidades.seguridadPassword(pwdNueva1);
		} catch (Exception e) {
			model.addAttribute(alert, e.getMessage());
			request.setAttribute("usuarioNombre", usuario.getNombre());
			request.setAttribute("usuarioEmail", usuario.getEmail());
			return "usuario/perfilUsuario";
		}

		usuario.setClave(pwdNueva1);
		usuarioDao.updatePwd(usuario);

		return "usuario/bienvenido";

	}

	public ModelAndView cambiarVista(String nombreVista) {
		ModelAndView vista = new ModelAndView(nombreVista);
		return vista;
	}

}

