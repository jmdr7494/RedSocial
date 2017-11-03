package com.intravita.proyectointranet.modelo;

import java.util.List;

/**
 * Usuario- Modelo de los usuarios del sistema
 *
 * @author Intravita
 * @since sprint 1
 */

public class Usuario {
	private String nombre;
	private String clave;
	private String email;
	private String respuesta;
	private List<Usuario> solicitudes;
	private List<Usuario> amigos;
	
	public Usuario(String nombre, String clave, String email, String respuesta) {
		this.nombre=nombre;
		this.clave=clave;
		this.email=email;
		this.respuesta=respuesta;
	}
	//Constructor de pass
	public Usuario(String nombre, String clave) {
		this.nombre=nombre;
		this.clave=clave;
	}
	
	//Constructor sin pass
	public Usuario(String nombre) {
		this.nombre=nombre;
	}
	
	//Constructor vacio
	public Usuario() {
		super();
	}	
	
	//Get atributos
	public String getNombre() {
		return this.nombre;
	}
	public String getClave() {
		return this.clave;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public String getEmail() {
		return email;
	}
	//Set atributos
	public void setNombre(String nuevoNombre) {
		this.nombre=nuevoNombre;
	}
	public void setClave(String nuevaClave) {
		this.clave=nuevaClave;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}