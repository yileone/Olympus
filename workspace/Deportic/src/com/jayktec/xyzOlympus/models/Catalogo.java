package com.jayktec.xyzOlympus.models;

import java.util.Date;
import java.sql.Time;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
/*Table: catalogo
Columns:
catalogo_id int(11) AI PK 
tabla_id int(11) 
nombre varchar(45) 
valor_entero int(11) 
valor_float float 
valor_varchar varchar(45) 
valor_hora time 
valor_fecha date 
descripcion longtext*/
@Entity
@Views({
	 @View( members="tabla; nombre; descripcion; valorEntero;valorFloat;valorVarchar;valorHora;valorFecha" ),
	 @View( name="VEntero", members="valorEntero" ),
	 @View( name="VFloat", members="valorFloat" ),
	 @View( name="VVarchar", members="valorVarchar" ),
	 @View( name="VHora", members="valorHora" ),
	 @View( name="VFecha", members="valorFecha" )
	})
@Table(name="catalogo")
public class Catalogo {

	@Id
	@Hidden
	@Column(name="catalogo_id",length=11)
	private int oid;
	

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="tabla_id",insertable=true,updatable=true)
	private Tabla tabla;
	
	@Column(name="nombre",length=45)
	private String nombre;
	
	@Stereotype("TEXTO_GRANDE")
	@Column(name="descripcion",length=255)
	private String descripcion;
	
	
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	@Column(name="valor_entero",length=11)
	private int valorEntero;
	
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	@Column(name="valor_float",length=11)
	private Float valorFloat;
	
	@Column(name="valor_varchar",length=45)
	private String valorVarchar;
	
	
	@Column(name="valor_hora",length=12)
	private Time valorHora;
	
	
	@Column(name="valor_fecha",length=45)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date valorFecha;

	
	

	public Date getValorFecha() {
		return valorFecha;
	}


	public void setValorFecha(Date valorFecha) {
		this.valorFecha = valorFecha;
	}





	public Tabla getTabla() {
		return tabla;
	}


	public void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}


	public int getOid() {
		System.out.println("catalogo get oid:"+oid);
		return oid;
	}


	public void setOid(int oid) {
		this.oid = oid;
		System.out.println("catalogo set oid:"+oid);
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getValorEntero() {
		return valorEntero;
	}


	public void setValorEntero(int valorEntero) {
		this.valorEntero = valorEntero;
	}


	public Float getValorFloat() {
		return valorFloat;
	}


	public void setValorFloat(Float valorFloat) {
		this.valorFloat = valorFloat;
	}


	public String getValorVarchar() {
		return valorVarchar;
	}


	public void setValorVarchar(String valorVarchar) {
		this.valorVarchar = valorVarchar;
	}


	public Time getValorHora() {
		return valorHora;
	}


	public void setValorHora(Time valorHora) {
		this.valorHora = valorHora;
	}




	
	
	
}
