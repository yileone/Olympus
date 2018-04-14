package com.jayktec.xyzOlympus.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentDateCalculator;

/*
Table: extras
Columns:
extra_id int(11) AI PK 
nombre varchar(45) 
valor float*/
@Entity
@Table(name="extras")
@Views({
	@View(name="VExtrasenAdicional",members="nombre,valor")
	})
public class Extra {
	
	@Id
	@Hidden
	@Column(name="extra_id",length=11)
	private int oid;
	
	@Column(name="nombre",length=45)
	private String nombre;
	
	@Column(name="valor",length=10)
	private float valor;

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
	
	
	

}
