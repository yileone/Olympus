package com.jayktec.xyzOlympus.models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.groovy.transform.*;
import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import java.util.Date;

@Entity
@Table(name = "fateon_mapa")
@View(name="VMapaenUmbral",members="mapabd" )
public class Mapa {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Hidden
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "mapa_id", length = 32)
	
	private String oid;

	/*
	 * @ManyToOne(fetch=FetchType.LAZY,optional=false)
	 * 
	 * @JoinColumn(name="mapa_tabla",insertable=true,updatable=true) private
	 * Tablamapa nombre;
	 */

	// @NoModify
	// @NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "mapa_tabla", insertable = true, updatable = true)
	@DescriptionsList(condition = "e.oid in(SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='MapaTabla')",
	showReferenceView = true, descriptionProperties = "valorCadena")
	@ReferenceView("VUmbral")
	private Catalogo mapaTabla;

	/*
	 * @Column(name="mapa_campo",insertable=true,updatable=true) private String
	 * mapabd;
	 */

	@NoModify
	@NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "mapa_campo", insertable = true, updatable = true)
	@DescriptionsList(condition = "e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='MapaCampo')", showReferenceView = false, descriptionProperties = "valorCadena")
	@ReferenceView("VVarchar")
	private Catalogo mapabd;

	@Required
	@Column(name = "mapa_nombre_campo", insertable = true, updatable = true)
	private String mapaapp;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Catalogo getMapaTabla() {
		return mapaTabla;
	}

	public void setMapaTabla(Catalogo mapaTabla) {
		this.mapaTabla = mapaTabla;
	}

	public Catalogo getMapabd() {
		return mapabd;
	}

	public void setMapabd(Catalogo mapabd) {
		this.mapabd = mapabd;
	}

	public String getMapaapp() {
		return mapaapp;
	}

	public void setMapaapp(String mapaapp) {
		this.mapaapp = mapaapp;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub

		if (obj instanceof Mapa) {
			Mapa temp = (Mapa) obj;
			if (temp.getOid() .equals( this.getOid()) 
					&& temp.getMapaapp().equals( this.getMapaapp())
					&& temp.getMapabd().equals( this.getMapabd()) 
					&& temp.getMapaTabla().equals( this.getMapaTabla())) {
				return true;
			}
		}
		return false;
	}

}
