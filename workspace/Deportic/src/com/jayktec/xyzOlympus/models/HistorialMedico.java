package com.jayktec.xyzOlympus.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

/*
Table: historialmedico
Columns:
historialMedico_id int(11) PK 
atleta_id int(11) 
fechaRegistro timestamp 
medicoTratante varchar(45) 
centroClinico varchar(45) 
tratamiento longtext 
operacion longtext 
reposoDesde date 
reposoHasta date 
fractura varchar(45) 
funcionario_id int(11)

*/
@Entity
@Table(name="historialmedico")
public class HistorialMedico {
	
	@Id
	@Hidden
	@Column(name="historialMedico_id",length=11)
	private int oid;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="atleta_id",insertable=true,updatable=true)
	@DescriptionsList(showReferenceView=true,descriptionProperties="persona.rut")
	@ReferenceView("VAtletaenHistorialMedico")
	private Atleta atleta;
	
	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="funcionario_id",insertable=true,updatable=true,table="historialmedico")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Persona c where c.funcionario > 0)",
	showReferenceView=true,
	descriptionProperties="rut")  
	@ReferenceView("VPersonaenAtleta")
	private Persona funcionario;
	
	
	@Column(name="medicoTratante",length=45)
	private String medicoTratante;	
	
	
	//TODO Esto es un catalogo ?
	@Column(name="centroClinico",length=45)
	private String centroClinico;	
	
	@Stereotype("TEXTO_GRANDE")
	@Column(name="tratamiento",length=255)
	private String tratamiento;
	
	
	@Column(name="reposoDesde",length=10)
	private Date reposoDesde;
	
	@Column(name="reposoHasta",length=10)
	private Date reposoHasta;
	
	
	@Stereotype("TEXTO_GRANDE")
	@Column(name="fractura",length=255)
	private String fractura;


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

	public String getMedicoTratante() {
		return medicoTratante;
	}


	public void setMedicoTratante(String medicoTratante) {
		this.medicoTratante = medicoTratante;
	}


	public String getCentroClinico() {
		return centroClinico;
	}


	public void setCentroClinico(String centroClinico) {
		this.centroClinico = centroClinico;
	}


	public String getTratamiento() {
		return tratamiento;
	}


	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}


	public Date getReposoDesde() {
		return reposoDesde;
	}


	public void setReposoDesde(Date reposoDesde) {
		this.reposoDesde = reposoDesde;
	}


	public Date getReposoHasta() {
		return reposoHasta;
	}


	public void setReposoHasta(Date reposoHasta) {
		this.reposoHasta = reposoHasta;
	}


	public String getFractura() {
		return fractura;
	}


	public void setFractura(String fractura) {
		this.fractura = fractura;
	}

	public Persona getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Persona funcionario) {
		this.funcionario = funcionario;
	}
	
	

}
