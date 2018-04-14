package com.jayktec.xyzOlympus.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

/*
Table: historiallogros
Columns:
historial_Logro_id int(11) PK 
atleta_id int(11) 
fechaRegistro timestamp 
logro longtext 
lugar varchar(45) 
competencia varchar(45) 
funcionario_id int(11) 
torneo_id int(11)
*/
@Entity
@Table(name="historialLogros")
public class HistorialLogros {
	
	//TODO No tiene id para la tabla historialLogros esta bien ?
	@Id
	@Hidden
	@Column(name="historial_Logro_id",length=11)
	private int oid;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="atleta_id",insertable=true,updatable=true)
	@DescriptionsList(showReferenceView=true,descriptionProperties="persona.rut")
	@ReferenceView("VAtletaenHistorialLogros")
	private Atleta atleta;
	
	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="funcionario_id",insertable=true,updatable=true,table="historialLogros")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Persona c where c.funcionario > 0)",
	showReferenceView=true,
	descriptionProperties="rut")  
	@ReferenceView("VPersonaenAtleta")
	private Persona funcionario;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="torneo_id",insertable=true,updatable=true)
	private Torneo torneo;

	@Stereotype("TEXTO_GRANDE")
	@Column(name="logro",length=10)
	private String logro;	

	@Column(name="lugar",length=10)
	private String lugar;	
	
	//TODO Esto es un catalogo ?
	@Column(name="competencia",length=45)
	private String competencia;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Atleta getAtleta() {
		return atleta;
	}

	public void setAtleta(Atleta atleta) {
		this.atleta = atleta;
	}



	public Persona getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Persona funcionario) {
		this.funcionario = funcionario;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	public String getLogro() {
		return logro;
	}

	public void setLogro(String logro) {
		this.logro = logro;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

}
