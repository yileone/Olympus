package com.jayktec.xyzOlympus.models;

import java.util.Collection;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.openxava.annotations.*;

import com.jayktec.persistencia.*;
import com.sun.org.apache.bcel.internal.generic.*;

@Entity
@Table(name = "fateon_sensor")
@View(name = "VSensorenUmbral", members = "nombre,mapa")
public class Sensor {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Hidden
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "sensor_id", length = 32)
	private String oid;

	@Column(name = "sensor_nombre", length = 45)
	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sensor_programa", insertable = true, updatable = true)
	private Programa programa;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sensor_tabla", insertable = true, updatable = true)
	@DescriptionsList(condition = "e.oid in(SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='MapaTabla')")
	@ReferenceView("VVarchar")
	private Catalogo mapa;

	@NoModify
	@NoCreate
	@OneToMany(mappedBy = "sensor")
	@CollectionView("VUmbralenSensor")
	private Collection<Umbral> umbral;

	public Sensor(String id) {
		buscarSensor(id);

	}

	public Sensor() {
		// TODO Auto-generated constructor stub
	}

	private void buscarSensor(String id) {
		// TODO Auto-generated method stub

		Sensor sensor = BdManager.buscarSensor(id);
		if (sensor != null) {
			this.setOid(sensor.getOid());
			this.setPrograma(sensor.getPrograma());
			this.setMapa(sensor.getMapa());
			this.setNombre(sensor.getNombre());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Sensor) {
			Sensor temp = (Sensor) obj;
			// TODO Auto-generated method stub
			if (this.getOid().equals(temp.getOid()) && this.getPrograma().equals(temp.getPrograma())
					&& this.getMapa().equals(temp.getMapa()) && this.getNombre().equals(temp.getNombre()))
				return true;
		}
		return false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Programa getPrograma() {
		return programa;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Catalogo getMapa() {
		return mapa;
	}

	public void setMapa(Catalogo mapa) {
		this.mapa = mapa;
	}

	public Collection<Umbral> getUmbral() {
		return umbral;
	}

	public void setUmbral(Collection<Umbral> umbral) {
		this.umbral = umbral;
	}

}
