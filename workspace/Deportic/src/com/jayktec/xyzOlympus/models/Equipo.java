package com.jayktec.xyzOlympus.models;

import javax.persistence.*;

import org.openxava.annotations.*;

/*
 * 
Table: equipo
Columns:
equipo_id int(11) AI PK 
nombre varchar(45) 
categoria int(11) 
club_id int(11)*/
@Entity
@Table(name="equipo")
@Views({
	@View(name="VEquipoenIncripcion",members="nombreEquipo;categoriaEquipo")
	})
public class Equipo {
	

	@Id
	@Hidden
	@Column(name="equipo_id",length=11)
	private int oid;
	
	
	@Column(name="nombre",length=45)
	private String nombreEquipo;
	
	//TODO Esto es un catalogo hay que crear la tabla y llenar el catalogo desde sus modulos CategoriaEquipo
	@NoModify
	@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="categoria",insertable=true,updatable=true,table="equipo")
	@DescriptionsList(condition="e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='CategoriaEquipo')",
	showReferenceView=false,
	descriptionProperties="valorVarchar")  
	@ReferenceView("VVarchar")
	private Catalogo categoriaEquipo;
	
	//@NoModify
	//@NoCreate
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="club_id")
	@DescriptionsList(showReferenceView=true,descriptionProperties="nombre")
	@ReferenceView("VClubenEquipo")
	private Club club;


	public int getOid() {
		return oid;
	}


	public void setOid(int oid) {
		this.oid = oid;
	}


	public String getNombreEquipo() {
		return nombreEquipo;
	}


	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}


	public Catalogo getCategoriaEquipo() {
		return categoriaEquipo;
	}


	public void setCategoriaEquipo(Catalogo categoriaEquipo) {
		this.categoriaEquipo = categoriaEquipo;
	}


	public Club getClub() {
		return club;
	}


	public void setClub(Club club) {
		this.club = club;
	}

}
