package com.jayktec.xyzOlympus.models;

import javax.persistence.*;

import org.openxava.annotations.*;

/*
 * 
Columns:
torneo_id int(11) AI PK 
nombre varchar(45) 
descripcion longtext
 */
@Entity
@Table(name="torneo")
public class Torneo {
	
	@Id
	@Hidden
	@Column(name="torneo_id",length=11)
	private int oid;
	
	@Column(name="nombre",length=45)
	private String nombre;
	
	@Stereotype("TEXTOGRANDE")
	@Column(name="descripcion",length=255)
	private String descripcion;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
