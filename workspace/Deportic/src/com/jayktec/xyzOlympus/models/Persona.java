package com.jayktec.xyzOlympus.models;

import java.io.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

/*
Table: persona
Columns:
persona_id int(11) AI PK 
rut int(11) 
digitoVerificador varchar(1) 
primerNombre varchar(45) 
segundoNombre varchar(45) 
primerApellido varchar(45) 
segundoApellido varchar(45) 
fechaNac date 
estadoCivil int(11) 
lugarNacimiento varchar(45) 
nacionalidad varchar(45) 
fechaRegistro timestamp 
sexo int(11)

*/
@Entity
@Table(name="persona")
@Views({
	@View(name="VPersonaenContacto",members="primerNombre,primerApellido"),
	@View(name="VPersonaenAtleta",members="primerNombre,segundoNombre;primerApellido,segundoApellido"),
	@View(members="datosGenerales {#rut,digitoVerificador,funcionario;primerNombre,segundoNombre;"
			   + "primerApellido,segundoApellido;fechaNac,lugarNacimiento;sexo,nacionalidad}"
	           + "DatosContacto {#listacontactos}"),
	@View(name="VPersonaenRepresentante",members="primerNombre;primerApellido")
	})
public class Persona implements Serializable  {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Hidden
	@Column(name="persona_id",length=11)
	private int oid;
	
	// ver el tamanio del rut 
	@Required
	@Column(name="rut",length=11)
	private String rut;
	
	@Required
	@Column(name="digitoVerificador",length=1)
	private String digitoVerificador;
	
	@Required
	@Column(name="primerNombre",length=45)
	private String primerNombre;
	
	
	@Column(name="segundoNombre",length=45)
	private String segundoNombre;
	
	@Required
	@Column(name="primerApellido",length=45)
	private String primerApellido;
	
	@Column(name="segundoApellido",length=45)
	private String segundoApellido;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	@Column(name="fechaNac",length=12)
	private Date fechaNac;
	
	@Column(name="lugarNacimiento",length=45)
	private String lugarNacimiento;
	

	//TODO CARGA DATA PARA EL CATALOGO Nacionalidades
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="nacionalidad",insertable=true,updatable=true,table="persona")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Nacionalidades')",
	showReferenceView=false,
	descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")
	private Catalogo nacionalidad;
	
	
	//TODO CARGA DATA PARA EL CATALOGO SEXO
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="sexo",insertable=true,updatable=true,table="persona")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Sexo')",
	showReferenceView=false,
	descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")
	private Catalogo sexo;
	
	
	@CollectionView("VContactoenPersona")
	@OneToMany(mappedBy="persona")
	private Collection<Contacto> listacontactos;
	
	
	@Column(name="funcionario",length=45)
	private boolean funcionario;
	
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}



	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public Catalogo getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Catalogo nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Catalogo getSexo() {
		return sexo;
	}

	public void setSexo(Catalogo sexo) {
		this.sexo = sexo;
	}
	
	public Collection<Contacto> getListacontactos() {
	return listacontactos;
	}

	public void setListacontactos(Collection<Contacto> listacontactos) {
	this.listacontactos = listacontactos;
	}

	public boolean isFuncionario() {
		return funcionario;
	}

	public void setFuncionario(boolean funcionario) {
		this.funcionario = funcionario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


	
	
	
	
	
}
