package com.jayktec.xyzOlympus.models;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;
/*SELECT `fateon_regla`.`regla_id`,
    `fateon_regla`.`regla_programa`,
    `fateon_regla`.`regla_catalogo`
FROM `fateon`.`fateon_regla`;*/

@Entity
@Table(name="fateon_regla")
public class Regla {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@Hidden
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name="regla_id",length=32)
	private String oid; 	
	
	@NoCreate
	@NoModify
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="regla_catalogo",insertable=true,updatable=true)
	@DescriptionsList(condition="e.oid in(SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Reglas')",
	showReferenceView=true,
	descriptionProperties="valorCadena")  
	@ReferenceView("VMapa") 
	private Catalogo reglaCatalogo;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="regla_programa",insertable=true,updatable=true)
	private Programa reglaPrograma;

	public Catalogo getReglaCatalogo() {
		return reglaCatalogo;
	}


	public String getOid() {
		return oid;
	}


	public void setOid(String oid) {
		this.oid = oid;
	}


	public void setReglaCatalogo(Catalogo reglaCatalogo) {
		this.reglaCatalogo = reglaCatalogo;
	}


	public Programa getReglaPrograma() {
		return reglaPrograma;
	}


	public void setReglaPrograma(Programa reglaPrograma) {
		this.reglaPrograma = reglaPrograma;
	}



	
	

}
