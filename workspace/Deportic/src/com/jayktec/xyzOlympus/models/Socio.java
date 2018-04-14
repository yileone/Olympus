package com.jayktec.xyzOlympus.models;

import javax.persistence.*;

import org.openxava.annotations.*;

/*
Table: socio
Columns:
socio_id int(11) AI PK 
club_id int(11) PK 
persona_id int(11) PK
 */
@Entity
@Table(name="socio")
public class Socio {
	
	@Id
	@Hidden
	@Column(name="socio_id",length=11)
	private int oid;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="persona_id",insertable=true,updatable=true)
	private Persona persona;
	
	
	@Column(name="club_id",length=11)
	private Club club;


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


	public Club getClub() {
		return club;
	}


	public void setClub(Club club) {
		this.club = club;
	}




	

}
