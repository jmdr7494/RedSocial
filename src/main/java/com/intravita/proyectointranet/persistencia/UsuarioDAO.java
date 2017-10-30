package com.intravita.proyectointranet.persistencia;

import com.intravita.proyectointranet.modelo.Usuario;

public interface UsuarioDAO {
	
	public void insert (Usuario usuario) throws Exception;
	public String list() ;
	public void delete (Usuario usuario);
	public void update(String nombre, String pwdAntigua, String pwdNueva);
	
	
}