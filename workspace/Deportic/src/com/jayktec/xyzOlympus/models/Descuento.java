package com.jayktec.xyzOlympus.models;
import javax.persistence.*;


import java.io.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Entity
/*Table: contrato_descuento
Columns:
contratoDescuento_id int(11) AI PK 
contrato_id int(11) 
fechaRegistro timestamp 
funcionario_id int(11) PK 
valor int(11) 
porcentaje*/
@Table(name="contrato_descuento")
public class Descuento {
	
	
	@Id
	@Hidden
	@Column(name="contratoDescuento_id",length=11)
	private int oid;

	@Stereotype("MEMO")
	@Column(name="descripcion",length=255)
	private String descripcion;
	
	
	@Column(name="valor",length=12)
	private float valor;
	
	
	@Column(name="porcentaje",length=3)
	private float porcentaje;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="contrato_id",insertable=true,updatable=true)
	private Contrato contrato;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="funcionario_id",insertable=true,updatable=true)
	private Persona funcionario;


	public int getOid() {
		return oid;
	}


	public void setOid(int oid) {
		this.oid = oid;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}


	public float getPorcentaje() {
		return porcentaje;
	}


	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
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



	
	
	
	
	

}
