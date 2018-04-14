package com.jayktec.xyzOlympus.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

/*
 
Table: atletarutina
Columns:
atletaRutina_id int(11) PK 
rutina_id int(11) 
atleta_id int(11) 
fechaRegistro timestamp 
fechaInicio date 
fechaFin date 
observacion longtext
 */
@Entity
@Table(name="atletarutina")
public class AtletaRutina {
	@Id
	@Hidden
	@Column(name="atletaRutina_id",length=11)
	private int oid;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="atleta_id",insertable=true,updatable=true)
	@DescriptionsList(showReferenceView=true,descriptionProperties="persona.rut")
	@ReferenceView("VAtletaenRutina")
	private Atleta atleta;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="rutina_id",insertable=true,updatable=true)
	private Rutina rutina;
	
	@Column(name="fechaInicio",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaInicio;	
	
	@Column(name="fechaFin",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaFin;	
	
	@Stereotype("TEXTO_GRANDE")
	@Column(name="observacion",length=255)
	private String observacion;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Rutina getRutina() {
		return rutina;
	}

	public void setRutina(Rutina rutina) {
		this.rutina = rutina;
	}

	public Atleta getAtleta() {
		return atleta;
	}

	public void setAtleta(Atleta atleta) {
		this.atleta = atleta;
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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
