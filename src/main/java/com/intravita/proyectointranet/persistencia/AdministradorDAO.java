package com.intravita.proyectointranet.persistencia;

import java.util.List;

import com.intravita.proyectointranet.modelo.Administrador;

/**
 * AdministradorDAO- Interfaz para el DAO de los administradores
 *
 * @author Intravita
 * @since sprint 1
 */

public interface AdministradorDAO {
	 public List<Administrador> list() ;
	 public void insert (Administrador administrador);
	 public Administrador select(Administrador generico) ;
	 public void delete (Administrador administrador);
	 public void update(String nombre, String pwdAntigua, String pwdNueva);
}