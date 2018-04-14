package com.jayktec.xyzOlympus.models;

import javax.persistence.*;

import org.openxava.annotations.*;
/*
 Table: Atleta
Columns:
idatleta int(11) AI PK 
idpersona int(11) 
tipoSangre varchar(45) 
alergia varchar(45) 
enfermedades varchar(45) 
idclub int(11)
*/
@Entity
@Table(name="tabla")
public class Tabla {
	
	@Id
	@Hidden
	@Column(name="tabla_id",length=11)
	private int oid;
	
	@Column(name="nombre",length=45)
	private String nombre;
	
	
	@Stereotype("TEXTO_GRANDE")
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
