package com.jayktec.xyzOlympus.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentDateCalculator;

/*Table: contrato_extras
Columns:
contratoExtra_id int(11) PK 
contrato_id int(11) 
fechaRegistro timestamp 
fechaInicio date 
fechaFin date 
funcionario_id int(11)*/

@Entity
@Table(name="contrato_extras")
@Views({
	@View(name="VAdicionalenContrato",members="extras;fechaInicio,fechaFin"),
	})
public class Adicional {
	
	@Id
	@Hidden
	@Column(name="contratoExtra_id",length=11)
	private int oid;
	

	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="contrato_id")
	@DescriptionsList(showReferenceView=true,descriptionProperties="atleta")
	@ReferenceView("VContratoenAdicional")
	private Contrato contrato;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="extra_id")
	private Extra extras;
	
	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="funcionario_id",insertable=true,updatable=true,table="contrato_extras")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Persona c where c.funcionario > 0)",
	showReferenceView=true,
	descriptionProperties="rut")  
	@ReferenceView("VPersonaenAtleta")
	private Persona funcionario;
	
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

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Persona getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Persona funcionario) {
		this.funcionario = funcionario;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Extra getExtras() {
		return extras;
	}

	public void setExtras(Extra extras) {
		this.extras = extras;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	

}
