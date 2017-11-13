package com.intravita.proyectointranet.modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.BsonValue;

/**
 * Publicacion- Modelo de las publicaciones que pueden realizar los usuarios
 *
 * @author Intravita
 * @since sprint 2
 */

public class Publicacion implements Comparable<Publicacion>{

	private Usuario usuario;
	private String texto;
	//Publica o Privada
	private String privacidad;
	private long fecha;
	private String id;
	final DateFormat formato=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	private ArrayList<String> megustaUsuarios;
	private int megustaCont;
	private List<BsonValue> compartidopor;
	
	public Publicacion(Usuario usuario, String texto) {
		this.usuario=usuario;
		this.texto=texto;
		this.privacidad="Publica";
		this.fecha=new Date().getTime();
		this.megustaCont=0;
		this.megustaUsuarios=null;
	}
	public Publicacion(Usuario usuario, String texto, String privacidad) {
		this.usuario = usuario;
		this.texto = texto;
		this.privacidad=privacidad;
		this.fecha=new Date().getTime();
		this.megustaCont=0;
		this.megustaUsuarios=null;
	}
	public Publicacion(Usuario usuario, String texto, String privacidad, long fecha, int mg) {
		this.usuario = usuario;
		this.texto = texto;
		this.privacidad=privacidad;
		this.fecha=fecha;
		this.megustaCont=mg;
	}
	public Publicacion(Usuario usuario, String texto, String privacidad, long fecha) {
		this.usuario = usuario;
		this.texto = texto;
		this.privacidad=privacidad;
		this.fecha=fecha;	
		
	}
	public Publicacion() {
		super();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getPrivacidad() {
		return privacidad;
	}
	public void setPrivacidad(String privacidad) {
		this.privacidad = privacidad;
	}
	public long getFecha() {
		return fecha;
	}
	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMeGustaCont() {
		megustaCont++;
	}
	public int getMeGustaCont() {
		return megustaCont;
	}
	public ArrayList<String> getMegustaUsuarios() {
		return megustaUsuarios;
	}
	/*public void setMeGustaUsuarios(Usuario usuario) {
		for(int i = 0; i<megustaUsers.length;i++)
			if(megustaUsuarios[i]==null)
				megustaUsuarios[i] = usuario.getNombre();
	}*/
	@Override
	public int compareTo (Publicacion p2) {
		if(this.getFecha()>p2.getFecha())
			return -1;
		if(this.getFecha()<p2.getFecha())
			return 1;
		return 0;
	}
	/*public boolean equals(Publicacion p) {
		return this.getId().equals(p.getId());
	}*/
	public String toString() {
		return "<b>"+usuario.getNombre()+" </b>: <br>"+ this.texto+ " id:"+this.id; 
	}
	public List<BsonValue> getCompartidopor() {
		return compartidopor;
	}
	public void setCompartidopor(List<BsonValue> compartidopor) {
		this.compartidopor = compartidopor;
	}
}
