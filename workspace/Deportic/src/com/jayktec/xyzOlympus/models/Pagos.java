package com.jayktec.xyzOlympus.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

/*
Table: pagos
Columns:
pago_id int(11) PK 
contrato_id int(11) AI 
fechaRegistro timestamp 
monto float 
numeroCheque varchar(45) 
banco varchar(45) 
tdc varchar(45) 
efectivo float 
numerotransaccion int(11) 
transferencia int(11)*/
@Entity
@Table(name="pagos")
public class Pagos {
	
	
	@Id
	@Hidden
	@Column(name="pago_id",length=11)
	private int oid;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="contrato_id",insertable=true,updatable=true)
	private Contrato contrato;
	
	
	@Column(name="Monto",length=45)
	private float monto;
	
	
	@Column(name="pagos",length=11)
	private int pago;
	

	@Column(name="numeroCheque",length=45)
	private String numeroCheque;	
	
	//TODO ESTO ES UN CATALOGO ?
	@Column(name="banco",length=45)
	private String 	banco;
	
	//TODO Ver como funciona este estereotipo tarjeta de credito
	@Stereotype("TARJETA_CREDITO")
	@Column(name="tdc",length=45)
	private String numeroTarjeta;
	
	//TODO Ver el tipo de datos creo mejor colocarlo Float  efectivo
	@Column(name="efectivo",length=12)
	private float efectivo;
	
	
	@Column(name="numerotransaccion",length=12)
	private int numerotransaccion;
	
	@Column(name="transferencia",length=12)
	private int numerotransferencia;

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

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public int getPago() {
		return pago;
	}

	public void setPago(int pago) {
		this.pago = pago;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public float getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(float efectivo) {
		this.efectivo = efectivo;
	}

	public int getNumerotransaccion() {
		return numerotransaccion;
	}

	public void setNumerotransaccion(int numerotransaccion) {
		this.numerotransaccion = numerotransaccion;
	}

	public int getNumerotransferencia() {
		return numerotransferencia;
	}

	public void setNumerotransferencia(int numerotransferencia) {
		this.numerotransferencia = numerotransferencia;
	}

	

}
