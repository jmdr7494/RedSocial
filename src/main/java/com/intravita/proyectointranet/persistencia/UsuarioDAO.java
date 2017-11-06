package com.intravita.proyectointranet.persistencia;

import java.util.List;

import com.intravita.proyectointranet.modelo.Usuario;

/**
 * UsuarioDAO- Interfaz del DAO de los usuarios
 *
 * @author Intravita
 * @since sprint 1
 */

public interface UsuarioDAO {
	
	public void insert (Usuario usuario) throws Exception;
	public List<Usuario> list() ;
	public void delete (Usuario usuario);
	public void update(String nombre, String pwdAntigua, String pwdNueva);
	
	
}