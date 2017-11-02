package com.intravita.proyectointranet.persistencia;

import com.intravita.proyectointranet.modelo.Publicacion;

/**
 * PublicacionDAO- Interfaz del DAO de las publicaciones de los usuarios
 *
 * @author Intravita
 * @since sprint 2
 */

public interface PublicacionDAO {
	public boolean insert (Publicacion pulicacion);
	public boolean existe(Publicacion publicacion) ;
	public void delete (Publicacion publicacion);	
}
