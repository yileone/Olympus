package com.jayktec.xyzOlympus.models;

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
import org.openxava.annotations.PropertyValue;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentDateCalculator;
import org.openxava.calculators.IntegerCalculator;

import java.util.Date;
import java.sql.Time;
/*
Table: partido
Columns:
partido_id int(11) PK 
inscripcionLocal_id int(11) 
inscripcionVisitante_id int(11) 
fecha date 
hora time 
lugar varchar(45) 
set1Local int(11) 
set2Local int(11) 
set3Local int(11) 
set4Local int(11) 
set5Local int(11) 
set1Visitante int(11) 
set2Visitante int(11) 
set3Visitante int(11) 
set4Visitante int(11) 
set5Visitante int(11) 
ganador tinyint(1)*/
@Entity
@Table(name="partido")
@Views({
	@View(members="inscripcionLocal,"
				+ "inscripcionVisitante;"
			    + "lugar,fecha,hora;"
			    + "set1Local,set1Visitante;"
			    + "set2Local,set2Visitante;"
			    + "set3Local,set3Visitante;"
			    + "set4Local,set4Visitante;"
			    + "set5Local,set5Visitante;"
			    + "ganador"
		   )
	})
public class Partido {
	
	@Id
	@Hidden
	@Column(name="partido_id",length=11)
	private int oid;
	
	@DescriptionsList(showReferenceView=true,descriptionProperties="equipo.nombreEquipo")
	@ReferenceView("VInscripcionenPartido")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="inscripcionLocal_id",insertable=true,updatable=true)
	private Inscripcion inscripcionLocal;
	
	
	@DescriptionsList(showReferenceView=true,descriptionProperties="equipo.nombreEquipo")
	@ReferenceView("VInscripcionenPartido")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="inscripcionVisitante_id",insertable=true,updatable=true)
	private Inscripcion inscripcionVisitante;
	
	@Column(name="lugar",length=45)
	private String lugar;
	
	@Column(name="fecha",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fecha;
	
	@Column(name="hora",length=8)
	private Time hora;
	
	@Column(name="set1Local",length=2)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int set1Local;
	
	@Column(name="set2Local",length=2)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int set2Local;
	
	@Column(name="set3Local",length=2)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int set3Local;
	
	@Column(name="set4Local",length=2)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int set4Local;
	
	@Column(name="set5Local",length=2)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int set5Local;

	@Column(name="set1Visitante",length=2)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int set1Visitante;
	
	@Column(name="set2Visitante",length=2)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int set2Visitante;
	
	@Column(name="set3Visitante",length=2)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int set3Visitante;
	
	@Column(name="set4Visitante",length=2)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int set4Visitante;
	
	@Column(name="set5Visitante",length=2)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int set5Visitante;
	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="ganador",insertable=true,updatable=true,table="partido")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Ganador')",
			showReferenceView=false,
			descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")private Catalogo ganador;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}



	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public int getSet1Local() {
		return set1Local;
	}

	public void setSet1Local(int set1Local) {
		this.set1Local = set1Local;
	}

	public int getSet2Local() {
		return set2Local;
	}

	public void setSet2Local(int set2Local) {
		this.set2Local = set2Local;
	}

	public int getSet3Local() {
		return set3Local;
	}

	public void setSet3Local(int set3Local) {
		this.set3Local = set3Local;
	}

	public int getSet4Local() {
		return set4Local;
	}

	public void setSet4Local(int set4Local) {
		this.set4Local = set4Local;
	}

	public int getSet5Local() {
		return set5Local;
	}

	public void setSet5Local(int set5Local) {
		this.set5Local = set5Local;
	}

	public int getSet1Visitante() {
		return set1Visitante;
	}

	public void setSet1Visitante(int set1Visitante) {
		this.set1Visitante = set1Visitante;
	}

	public int getSet2Visitante() {
		return set2Visitante;
	}

	public void setSet2Visitante(int set2Visitante) {
		this.set2Visitante = set2Visitante;
	}

	public int getSet3Visitante() {
		return set3Visitante;
	}

	public void setSet3Visitante(int set3Visitante) {
		this.set3Visitante = set3Visitante;
	}

	public int getSet4Visitante() {
		return set4Visitante;
	}

	public void setSet4Visitante(int set4Visitante) {
		this.set4Visitante = set4Visitante;
	}

	public int getSet5Visitante() {
		return set5Visitante;
	}

	public void setSet5Visitante(int set5Visitante) {
		this.set5Visitante = set5Visitante;
	}

	public Catalogo getGanador() {
		return ganador;
	}

	public void setGanador(Catalogo ganador) {
		this.ganador = ganador;
	}

	public Inscripcion getInscripcionLocal() {
		return inscripcionLocal;
	}

	public void setInscripcionLocal(Inscripcion inscripcionLocal) {
		this.inscripcionLocal = inscripcionLocal;
	}

	public Inscripcion getInscripcionVisitante() {
		return inscripcionVisitante;
	}

	public void setInscripcionVisitante(Inscripcion inscripcionVisitante) {
		this.inscripcionVisitante = inscripcionVisitante;
	}



}
