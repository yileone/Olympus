package com.jayktec.xyzOlympus.models;

import javax.persistence.*;

import org.openxava.annotations.*;

/**
 * @author Anthonny
Table: inscripcion
Columns:
inscripcion_id int(11) PK 
equipo_id int(11) 
torneo_id int(11)
 */
@Entity
@Table(name="inscripcion")
@Views({
	@View(name="VInscripcionenPartido",members="torneo")
	})
public class Inscripcion {
	
	
	
	@Id
	@Hidden
	@Column(name="inscripcion_id",length=11)
	private int oid;
	

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="equipo_id",insertable=true,updatable=true)
	@DescriptionsList(showReferenceView=true,descriptionProperties="nombreEquipo")
	@ReferenceView("VEquipoenIncripcion")
	private Equipo equipo;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="torneo_id",insertable=true,updatable=true)
	private Torneo torneo;


	public Equipo getEquipo() {
		return equipo;
	}


	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}


	public Torneo getTorneo() {
		return torneo;
	}


	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}


	public int getOid() {
		return oid;
	}


	public void setOid(int oid) {
		this.oid = oid;
	}



	
	

}
