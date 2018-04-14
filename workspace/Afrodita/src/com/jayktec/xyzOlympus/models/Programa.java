package com.jayktec.xyzOlympus.models;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.openxava.annotations.*;
/*SELECT `fateon_accion`.`programa_id`,
    `fateon_accion`.`programa_nombre`,
    `fateon_accion`.`programa_ubicacion`,
    `fateon_accion`.`programa_nemonico`
FROM `fateon`.`fateon_accion`;
*/

@Entity
@Table(name="fateon_accion")
public class Programa {
	
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@Hidden
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name="programa_id",length=32)
	private String oid; 	
	
	
	@Column(name="programa_nombre",length=45)
	private String programaNombre;
	
	// debe ser stereotype Memo
	@Column(name="programa_ubicacion",length=45)
	private String programaUbicacion;
	
	@Column(name="programa_nemonico",length=45)
	private String programaNemonico;

	/*@SuppressWarnings("deprecation")
	@OneToMany
	@JoinColumn(name="regla_programa",insertable=true,updatable=true)
	@IndexColumn(name="fk_regla_programa_idx")
	private Collection<Regla> regla;
	public Collection<Regla> getRegla() {
		return regla;
	}

	public void setRegla(Collection<Regla> regla) {
		this.regla = regla;
	}*/


	public Programa() {
		// TODO Auto-generated constructor stub
	}
	public Programa(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getProgramaNombre() {
		return programaNombre;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public void setProgramaNombre(String programaNombre) {
		this.programaNombre = programaNombre;
	}

	public String getProgramaUbicacion() {
		return programaUbicacion;
	}

	public void setProgramaUbicacion(String programaUbicacion) {
		this.programaUbicacion = programaUbicacion;
	}

	public String getProgramaNemonico() {
		return programaNemonico;
	}

	public void setProgramaNemonico(String programaNemonico) {
		this.programaNemonico = programaNemonico;
	}



	
	
	

}
