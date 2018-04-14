package com.jayktec.xyzOlympus.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

/*
Table: contrato
Columns:
contrato_id int(11) PK 
atleta_id int(11) 
convenio_id int(11) 
fechaRegistro timestamp 
fechaInicio date 
fechaFin date 
formaPago int(11) 
valorCuota float 
cantidadCuotas int(11) 
equipo_id int(11)
*/
@Entity
@Table(name="contrato")
@Views({
	@View(name="VContratoenAtleta",members="convenio,equipo;fechaInicio,fechaFin"),
	@View(name="VContratoenAdicional",members="atleta;fechaInicio,fechaFin,formaPago,valorCuota,cantidadCuotas"),
	@View(members="datosContrato {#atleta;fechaInicio,fechaFin,formaPago,valorCuota,cantidadCuotas;equipo;convenio}"
	           + "adicional {#adicional}"
	           + "descuento {#descuento}"
	)
	})

public class Contrato {
	
	
	@Id
	@Hidden
	@Column(name="contrato_id",length=11)
	private int oid;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="atleta_id",insertable=false,updatable=false)
	@DescriptionsList(showReferenceView=true,descriptionProperties="persona.rut")
	@ReferenceView("VAtletaenContrato")
	private Atleta atleta;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="convenio_id",insertable=true,updatable=true)
	private Convenio convenio;
	
	
	@OneToMany(mappedBy = "contrato")
	private Collection<Adicional> adicional;
	
	
	@OneToMany(mappedBy = "contrato")
	private Collection<Descuento> descuento;

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="equipo_id",insertable=true,updatable=true)
	private Equipo equipo;
	
	@Column(name="fechaInicio",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaInicio;
	
	@Column(name="FechaFin",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaFin;
	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="formaPago",insertable=true,updatable=true,table="contrato")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='FormaPago')",
			showReferenceView=false,
			descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")
	private Catalogo formaPago;

	
	@Column(name="valorCuota",length=11)
	private float valorCuota;
	
	@Column(name="cantidadCuotas",length=11)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int cantidadCuotas;

	
	public Atleta getAtleta() {
		return atleta;
	}

	public void setAtleta(Atleta atleta) {
		this.atleta = atleta;
	}
	
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}


	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
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

	public Catalogo getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Catalogo formaPago) {
		this.formaPago = formaPago;
	}

	public float getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(float valorCuota) {
		this.valorCuota = valorCuota;
	}

	public int getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}


	public Collection<Adicional> getAdicional() {
		return adicional;
	}

	public void setAdicional(Collection<Adicional> adicional) {
		this.adicional = adicional;
	}

	public Collection<Descuento> getDescuento() {
		return descuento;
	}

	public void setDescuento(Collection<Descuento> descuento) {
		this.descuento = descuento;
	}



}
