package com.jayktec.xyzOlympus.models;

import javax.persistence.*;

import org.openxava.annotations.*;

/*
Table: rol
Columns:
rol_id int(11) PK 
descripcion longtext 
funcionario_id int(11) 
persona_id */
@Entity
@Table(name="roles")
public class Roles {
	
	@Id
	@Hidden
	@Column(name="rol_id",length=11)
	private int oid;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="persona_id",insertable=true,updatable=true)
	private Persona persona;
	

	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="funcionario_id",insertable=true,updatable=true)
	private Persona funcionario;
	
	@Stereotype("TEXTOGRANDE")
	@Column(name="descripcion",length=255)
	private int descripcion;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}



	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}



	public Persona getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Persona funcionario) {
		this.funcionario = funcionario;
	}

	public int getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(int descripcion) {
		this.descripcion = descripcion;
	}

	
	
}
