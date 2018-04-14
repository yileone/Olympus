package com.jayktec.xyzOlympus.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

/*Columns:
Table: entrenador
Columns:
entrenador_id int(11) AI PK 
desde date 
hasta date 
persona_id int(11) PK 
equipo_id int(11) PK 
estado int(11)*/
@Entity
@Table(name="entrenador")
public class Entrenador {

	@Id
	@Hidden
	@Column(name="entrenador_id",length=11)
	private int oid;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="persona_id",insertable=true,updatable=true)
	private Persona persona;
	

	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="equipo_id",insertable=true,updatable=true)
	private Equipo equipo;
	
	@Column(name="desde",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaInicio;
	
	@Column(name="hasta",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaFin;
	
	

	

	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="estado",insertable=true,updatable=true,table="entrenador")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='EstadoEntrenador')",
			showReferenceView=false,
			descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")
	private Catalogo estado;


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




	public Equipo getEquipo() {
		return equipo;
	}




	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
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




	public Catalogo getEstado() {
		return estado;
	}




	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}



	
}
