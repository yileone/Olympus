package com.jayktec.xyzOlympus.models;


import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import java.io.*;
import java.util.Date;

/*Table: representante
Columns:
representante_id int(11) AI PK 
fechaInicio date 
fechaFin date 
fechaRegistro timestamp 
persona_id int(11) PK 
club_id*/

@Entity
@Table(name="representante")
@Views({
	@View(name="VRepresentanteenClub",members="persona;fechaInicio,fechaFin"),
	@View(members="fechaInicio,fechaFin;persona,club;")
	})
public class Representante implements Serializable{
	

	private static final long serialVersionUID = 1L;

	
	@Id
	@Hidden
	@Column(name="representante_id",length=11)
	private int oid;
	
	@NoModify
	//@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="persona_id")
	@DescriptionsList(showReferenceView=true,descriptionProperties="rut")
	@ReferenceView("VPersonaenRepresentante") 
	private Persona persona;
	
	//@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="club_id")
	@DescriptionsList(showReferenceView=true,descriptionProperties="nombre")
	@ReferenceView("VClubenRepresentante")
	private Club club;
	
	@Column(name="fechaInicio",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaInicio;
	
	@Column(name="fechaFin",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaFin;
	
	




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



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Date getFechaInicio() {
		return fechaInicio;
	}



	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public Date getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}



	public Club getClub() {
		return club;
	}



	public void setClub(Club club) {
		this.club = club;
	}


	
}
