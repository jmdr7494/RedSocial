package com.intravita.proyectointranet.modelo;

/**
 * Administrador- Modelo de los administradores del sistema
 *
 * @author Intravita
 * @since sprint 1
 */

public class Administrador {
	 private String nombre;
	 private String clave;
	 private String email;
 
 //Constructor de pass
	 public Administrador(String nombre, String clave) {
	  this.nombre=nombre;
	  this.clave=clave;
	 }
	 public Administrador(String nombre, String clave, String email) {
		 this.nombre=nombre;
		 this.clave=clave;
		 this.email=email;
	 }
 
	 //Constructor sin pass
	 public Administrador(String nombre) {
	  this.nombre=nombre;
	 }
	 
	 //Constructor vacio
	 public Administrador() {
	  super();
	 } 
	 
	 //Get atributos
	 public String getNombre() {
	  return this.nombre;
	 }
	 public String getClave() {
	  return this.clave;
	 }
	 public String getEmail() {
	  return this.email;
	 }
	 //Set atributos
	 public void setNombre(String nuevoNombre) {
	  this.nombre=nuevoNombre;
	 }
	 public void setClave(String nuevaClave) {
	  this.clave=nuevaClave;
	 }
	 public void setEmail(String nuevoEmail) {
	  this.email=nuevoEmail;
	 }
}