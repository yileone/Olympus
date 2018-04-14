package com.jayktec.xyzOlympus.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;


/*
Table: convenio
Columns:
convenio_id int(11) AI PK 
nombre varchar(45) 
valor float 
descripcion longtext 
fechaRegistro timestamp 
fechaInicio date 
FechaFin date 
*/
@Entity
@Table(name="convenio")
public class Convenio {
	
	@Id
	@Hidden
	@Column(name="convenio_id",length=11)
	private int oid;

	@Column(name="nombre",length=45)
	private String nombre;
	
	@Column(name="valor",length=11)
	private float valor;
	
	
	@Stereotype("TEXTOGRANDE")
	@Column(name="descripcion",length=255)
	private String descripcion;
	
	
	@Column(name="fechaInicio",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaInicio;

	@Column(name="FechaFin",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaFin;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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



}
