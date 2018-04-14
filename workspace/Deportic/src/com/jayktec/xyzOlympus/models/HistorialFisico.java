package com.jayktec.xyzOlympus.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

/*
Table: historialfisico
Columns:
historialFisico_id int(11) PK 
atleta_id int(11) 
fechaRegistro timestamp 
altura float 
peso float 
tallaCamisa int(11) 
tallaZapato float 
tallaPantalon int(11) 
porcGrasa int(11) 
porcMusculo float 
imagen blob 
funcionario_id int(11)*/
@Entity
@Table(name="historialFisico")
public class HistorialFisico {
	@Id
	@Hidden
	@Column(name="historialFisico_id",length=11)
	private int oid;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="atleta_id",insertable=true,updatable=true)
	@DescriptionsList(showReferenceView=true,descriptionProperties="persona.rut")
	@ReferenceView("VAtletaenHistorialFisico")
	private Atleta atleta;
	
	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="funcionario_id",insertable=true,updatable=true,table="historialFisico")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Persona c where c.funcionario > 0)",
	showReferenceView=true,
	descriptionProperties="rut")  
	@ReferenceView("VPersonaenAtleta")
	private Persona funcionario;
	
	@Column(name="altura",length=11)
	private float altura;
	
	@Column(name="peso",length=11)
	private float peso;
	

	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="tallaCamisa",insertable=true,updatable=true,table="historialFisico")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='TallaCamisa')",
			showReferenceView=false,
			descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")
	private Catalogo tallaCamisa;
	
	@Column(name="tallaZapato",length=11)
	private float tallaZapato;

	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="tallaPantalon",insertable=true,updatable=true,table="historialFisico")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='TallaPantalon')",
			showReferenceView=false,
			descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")
	private Catalogo tallaPantalon;
	
	@Column(name="porcGrasa",length=11)
	private float porcGrasa;
	
	@Column(name="porcMusculo",length=11)
	private float porcMusculo;

	@Column(name="imagen",length=255)
	@Stereotype("IMAGEN")
	private byte[] imagen;

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

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Catalogo getTallaCamisa() {
		return tallaCamisa;
	}

	public void setTallaCamisa(Catalogo tallaCamisa) {
		this.tallaCamisa = tallaCamisa;
	}

	public float getTallaZapato() {
		return tallaZapato;
	}

	public void setTallaZapato(float tallaZapato) {
		this.tallaZapato = tallaZapato;
	}


	public Catalogo getTallaPantalon() {
		return tallaPantalon;
	}

	public void setTallaPantalon(Catalogo tallaPantalon) {
		this.tallaPantalon = tallaPantalon;
	}

	public float getPorcGrasa() {
		return porcGrasa;
	}

	public void setPorcGrasa(float porcGrasa) {
		this.porcGrasa = porcGrasa;
	}

	public float getPorcMusculo() {
		return porcMusculo;
	}

	public void setPorcMusculo(float porcMusculo) {
		this.porcMusculo = porcMusculo;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	

}
