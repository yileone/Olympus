package com.jayktec.xyzOlympus.models;

import java.util.Collection;

import javax.persistence.*;
import org.openxava.annotations.*;



/*
atleta_id int(11) AI PK 
persona_id int(11) 
tipoSangre varchar(45) 
manoHabil varchar(45*/
@Entity
@Table(name="atleta")

@Views({
	@View(members="datosGenerales {#persona;tipoSangre,manoHabil;}"
			   + "atletaRutina {#atletaRutina}"
	           + "contrato {#contrato}"
	           + "historialFisico {#historialFisico}"
	           + "historialLogros {#historialLogros}"
	           + "historialMedico {#historialMedico}"
	),
	@View(name="VAtletaenHistorialFisico",members="datosGenerales {#persona;tipoSangre,manoHabil;}"
	           + "historialLogros {#historialLogros}"
	           + "historialMedico {#historialMedico}"
	),
	@View(name="VAtletaenHistorialLogros",members="datosGenerales {#persona;tipoSangre,manoHabil;}"
	           + "historialFisico {#historialFisico}"
	           + "historialMedico {#historialMedico}"
	),
	@View(name="VAtletaenHistorialMedico",members="datosGenerales {#persona;tipoSangre,manoHabil;}"
	           + "historialFisico {#historialFisico}"
	           + "historialLogros {#historialLogros}"
	),	
	@View(name="VAtletaenContrato",members="persona;tipoSangre,manoHabil;"
	),
	@View(name="VAtletaenRutina",members="datosGenerales {#persona;tipoSangre,manoHabil;}"
	)
	
})
public class Atleta{
	


	
	@Id
	@Hidden
	@Column(name="atleta_id",length=11)
	private int oid;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="persona_id",insertable=true,updatable=true)
	@DescriptionsList(showReferenceView=true,descriptionProperties="rut")
	@ReferenceView("VPersonaenAtleta")
	private Persona persona;
	
	
	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="tipoSangre",insertable=true,updatable=true,table="atleta")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='TipoSangre')",
			showReferenceView=false,
			descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")private Catalogo tipoSangre;

	
	
	//TODO CARGA DATA PARA EL CATALOGO Lateralidad 
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="manoHabil",insertable=true,updatable=true,table="atleta")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Lateralidad')",
	showReferenceView=false,
	descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")
	private Catalogo manoHabil;
	
	
	
	//@NoCreate
	//@NoModify
	@CollectionView("VContratoenAtleta")
	@OneToMany(mappedBy = "atleta")
	private Collection<Contrato> contrato;
	
	@OneToMany(mappedBy = "atleta")
	private Collection<HistorialFisico> historialFisico;

	
	@OneToMany(mappedBy = "atleta")
	private Collection<HistorialLogros> historialLogros;

	
	@OneToMany(mappedBy = "atleta")
	private Collection<HistorialMedico> historialMedico;
	
	@OneToMany(mappedBy = "atleta")
	private Collection<AtletaRutina> atletaRutina;



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



	public Catalogo getTipoSangre() {
		return tipoSangre;
	}



	public void setTipoSangre(Catalogo tipoSangre) {
		this.tipoSangre = tipoSangre;
	}



	public Catalogo getManoHabil() {
		return manoHabil;
	}



	public void setManoHabil(Catalogo manoHabil) {
		this.manoHabil = manoHabil;
	}



	public Collection<Contrato> getContrato() {
		return contrato;
	}



	public void setContrato(Collection<Contrato> contrato) {
		this.contrato = contrato;
	}



	public Collection<HistorialFisico> getHistorialFisico() {
		return historialFisico;
	}



	public void setHistorialFisico(Collection<HistorialFisico> historialFisico) {
		this.historialFisico = historialFisico;
	}



	public Collection<HistorialLogros> getHistorialLogros() {
		return historialLogros;
	}



	public void setHistorialLogros(Collection<HistorialLogros> historialLogros) {
		this.historialLogros = historialLogros;
	}



	public Collection<HistorialMedico> getHistorialMedico() {
		return historialMedico;
	}



	public void setHistorialMedico(Collection<HistorialMedico> historialMedico) {
		this.historialMedico = historialMedico;
	}



	public Collection<AtletaRutina> getAtletaRutina() {
		return atletaRutina;
	}



	public void setAtletaRutina(Collection<AtletaRutina> atletaRutina) {
		this.atletaRutina = atletaRutina;
	}



	


}
