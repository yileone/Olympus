package com.jayktec.xyzOlympus.models;

import javax.persistence.*;
import org.openxava.annotations.*;
/*
Table: contacto
Columns:
contacto_id int(11) AI PK 
persona_id int(11) 
tipoContacto int(11) 
valor longtext
*/
@Entity
@Views({
	@View(name="VContactoenClub",members="tipoContacto,valor"),
	@View(name="VContactoenPersona",members="tipoContacto,valor")})
@Table(name="contacto")
public class Contacto {
	
	@Id
	@Hidden
	@Column(name="contacto_id",length=11)
	private int oid;

	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="persona_id")
	@DescriptionsList(showReferenceView=true,descriptionProperties="rut")
	@ReferenceView("VPersonaenContacto") 
	private Persona persona;
	
	//TODO LLENAR EL CATALOGO PARA TIPO DE CONTACTO
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="tipoContacto",insertable=true,updatable=true,table="contacto")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, "
			+ "Tabla t where c.tabla = t.oid and t.nombre ='TipoContactos')",
	showReferenceView=false,
	descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")
	private Catalogo tipoContacto;
	
	@Required
	@Column(name="valor",length=255)
	private String valor;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}
	
	public Catalogo getTipoContacto() {
		return tipoContacto;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setTipoContacto(Catalogo tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
