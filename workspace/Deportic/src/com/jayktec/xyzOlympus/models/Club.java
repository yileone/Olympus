package com.jayktec.xyzOlympus.models;

import java.io.*;
import java.util.*;
import javax.persistence.*;

import org.openxava.annotations.*;

/*
Table: club
Columns:
club_id int(11) AI PK 
razonSocial varchar(45) 
logo blob
*/


@Entity
@Table(name="club")
@Views({
	@View(name="VClubenEquipo",members="razonSocial,nombre"),
	@View(name="VClubenRepresentante",members="razonSocial;nombre"),
	@View(members="razonSocial,nombre;representante;logo")})
public class Club implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Hidden
	@Column(name="club_id",length=11)
	private int oid;

	@Required
	@Column(name="razonSocial",length=45)
	private String razonSocial;
	
	
	@Required
	@Column(name="nombre",length=45)
	private String nombre;

	@Column(name="logo",length=255)
	@Stereotype("FOTO")
	private byte[] logo;
	
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="representante_id",insertable=true,updatable=true)
	@DescriptionsList(showReferenceView=true,
	descriptionProperties="persona.rut")  
	@ReferenceView("VRepresentanteenClub")
	private Representante representante;
	
	

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}


	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
