package com.jayktec.xyzOlympus.models;


import java.sql.*;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

import com.jayktec.persistencia.*;
import com.sun.org.apache.bcel.internal.generic.*;


@Entity
@Table(name="fateon_catalogo")
@Views({
	 @View( members="tabla; nombre; valorEntero; valorFecha;valorDecimal;valorCadena;valorHora;catalogoTabla" ),
	 @View( name="VEntero", members="valorEntero" ),
	 @View( name="VFloat", members="valorDecimal" ),
	 @View( name="VVarchar", members="valorCadena" ),
	 @View( name="VHora", members="valorHora" ),
	 @View( name="VFecha", members="valorFecha" ),
	 @View( name="VTabla", members="nombre; valorEntero; valorFecha;valorDecimal;valorCadena;valorHora;catalogoTabla" ),
	 @View( name="VMapa", members="nombre;valorCadena" ),
	 @View( name="VUmbral", members="valorCadena" )
	})
public class Catalogo {



	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Catalogo) {
			Catalogo comparar = (Catalogo) obj;
			
			if(comparar.getOid().equals(getOid()))
				return true;
		}
		
		return false;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@Hidden
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name="catalogo_id",length=32)
	private String oid; 
	
	@Required
	@Column(name="catalogo_nombre",length=45)
	private String nombre;

    
	@Column(name="catalogo_valor_int",length=11)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private int valorEntero;
	

	@Column(name="catalogo_valor_date",length=10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date valorFecha;
	
	@Column(name="catalogo_valor_time",length=9)
	@Stereotype("HORA_MINUTO")
	//@DefaultValueCalculator(value=StringCalculator.class, properties=
	@PropertyValue(name="value", value="00:00")
	private Time valorHora;
	
	@Column(name="catalogo_descripcion_varchar",length=45)
	private String valorCadena;
	
	@Column(name="catalogo_valor_float",length=11)
	@DefaultValueCalculator(value=IntegerCalculator.class, properties=
	@PropertyValue(name="value", value="0"))
	private float valorDecimal;	
	
	@Column(name="catalogo_tabla",length=45)
	private String catalogoTabla;
	
	/*@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="catalogo_id_tabla",insertable=true,updatable=true)
	private Tabla tabla;*/
	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="catalogo_id_tabla",insertable=true,updatable=true)
	@DescriptionsList(
			showReferenceView=true)  
	@ReferenceView("VCatalogo") 
	private Tabla tabla;

	
	public Catalogo() {
		// TODO Auto-generated constructor stub
	}

	public Catalogo(String id) {
		buscarCatalogo(id);
	}

	private void buscarCatalogo(String id) {
		// TODO Auto-generated method stub
		Catalogo temp = BdManager.buscarCatalogo(id);
		this.setOid(temp.getOid());
		this.setNombre(temp.getNombre());
		this.setTabla(temp.getTabla());
		this.setCatalogoTabla(temp.getCatalogoTabla());
		this.setValorFecha(temp.getValorFecha());
		this.setValorDecimal(temp.getValorDecimal());
		this.setValorEntero(temp.getValorEntero());
		this.setValorCadena(temp.getValorCadena());
		this.setValorHora(temp.getValorHora());
	}
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValorEntero() {
		return valorEntero;
	}

	public void setValorEntero(int valorEntero) {
		this.valorEntero = valorEntero;
	}



	public Date getValorFecha() {
		return valorFecha;
	}

	public void setValorFecha(Date valorFecha) {
		this.valorFecha = valorFecha;
	}

	public Time getValorHora() {
		return valorHora;
	}

	public void setValorHora(Time valorHora) {
		this.valorHora = valorHora;
	}

	public String getValorCadena() {
		return valorCadena;
	}

	public void setValorCadena(String valorCadena) {
		this.valorCadena = valorCadena;
	}

	public float getValorDecimal() {
		return valorDecimal;
	}

	public void setValorDecimal(float valorDecimal) {
		this.valorDecimal = valorDecimal;
	}

	public String getCatalogoTabla() {
		return catalogoTabla;
	}

	public void setCatalogoTabla(String catalogoTabla) {
		this.catalogoTabla = catalogoTabla;
	}

	public Tabla getTabla() {
		return tabla;
	}

	public void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}

	
	

}

