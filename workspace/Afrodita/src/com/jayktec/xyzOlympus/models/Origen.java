package com.jayktec.xyzOlympus.models;

import java.sql.*;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

import com.gargoylesoftware.htmlunit.WebConsole.*;
import com.jayktec.persistencia.*;

@Entity
@View(name = "VOrigenenUmbral", members = "Orige {#nombre,modelo;mac,ip; sistema,tipo;} DIRECCION {#pais,municipio,sucursal,oficina,ciudad}")
@Table(name = "fateon_origen")
public class Origen {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Hidden
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "origen_id", length = 32)
	private String oid;

	@Required
	@Column(name = "origen_nombre", length = 45)
	private String nombre;

	// @Required
	@Column(name = "origen_modelo", length = 45)
	private String modelo;

	// @Required
	@Column(name = "origen_mac", length = 100)
	private String mac;

	// @Required
	@Column(name = "hora_apertura", length = 9)
	@Stereotype("HORA_MINUTO")
	private Time horaApertura;

	// @Required
	@Column(name = "hora_cierre", length = 9)
	@Stereotype("HORA_MINUTO")
	private Time horaCierre;

	// @Required
	@Column(name = "origen_ip", length = 15, nullable = true)
	@Pattern(regexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + // Se valida la direccion IP
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$", message = "Direccion IP Ivalida")
	private String ip;

	// @NoModify
	// @NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "origen_id_tipo", insertable = true, updatable = true, table = "fateon_origen")
	@DescriptionsList(condition = "e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Tipo_Sistemas')", showReferenceView = false, descriptionProperties = "valorCadena")
	@ReferenceView("VVarchar")
	private Catalogo tipo;

	// @NoModify
	// @NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "origen_id_sistema", insertable = true, updatable = true, table = "fateon_origen")
	@DescriptionsList(condition = "e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Sistemas')", showReferenceView = false, descriptionProperties = "valorCadena")
	@ReferenceView("VVarchar")
	private Catalogo sistema;

	// @NoModify
	// @NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "origen_id_pais", insertable = true, updatable = true)
	@DescriptionsList(condition = "e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Paises')", showReferenceView = false, descriptionProperties = "valorCadena")
	@ReferenceView("VVarchar")
	private Catalogo pais;

	// @NoModify
	// @NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "origen_id_provincia", insertable = true, updatable = true)
	@DescriptionsList(condition = "e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Provincias' )", showReferenceView = false, descriptionProperties = "valorCadena")
	@ReferenceView("VVarchar")
	private Catalogo provincia;

	// @NoModify
	// @NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "origen_id_sucursal", insertable = true, updatable = true)
	@DescriptionsList(condition = "e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Sucursales')", showReferenceView = false, descriptionProperties = "valorCadena")
	@ReferenceView("VVarchar")
	private Catalogo sucursal;

	// @NoModify
	// @NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "origen_id_oficina", insertable = true, updatable = true)
	@DescriptionsList(condition = "e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Oficinas')", showReferenceView = false, descriptionProperties = "valorCadena")
	@ReferenceView("VVarchar")
	private Catalogo oficina;

	// @NoModify
	// @NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "origen_id_municipio", insertable = true, updatable = true)
	@DescriptionsList(condition = "e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Municipios')", showReferenceView = false, descriptionProperties = "valorCadena")
	@ReferenceView("VVarchar")
	private Catalogo municipio;

	// @NoModify
	// @NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "origen_id_ciudad", insertable = true, updatable = true)
	@DescriptionsList(condition = "e.oid in( SELECT c.oid FROM Catalogo c, Tabla t where c.tabla = t.oid and t.nombre ='Ciudades')", showReferenceView = false, descriptionProperties = "valorCadena")
	@ReferenceView("VVarchar")
	private Catalogo ciudad;

	@NoModify
	@NoCreate
	@OneToMany(mappedBy = "origen")
	@CollectionView("VUmbralenOrigen")
	private Collection<Umbral> umbral;

	public Origen(String string) {
		// TODO Auto-generated constructor stub
		buscarOrigen(string);
	}

	public Origen() {
	}

	/**
	 * genera todo un origen busncandolo de bd a partir del id
	 * 
	 * @param id2
	 */
	private void buscarOrigen(String id) {
		// TODO Generar busqueda
		Origen temp = BdManager.BuscarOrigen(id);
		if (temp != null) {
			this.setOid(temp.getOid());
			this.setCiudad(temp.getCiudad());
			this.setIp(temp.getIp());
			this.setMac(temp.getMac());
			this.setModelo(temp.getModelo());
			this.setMunicipio(temp.municipio);
			this.setNombre(temp.getNombre());
			this.setOficina(temp.oficina);
			this.setPais(temp.getPais());
			this.setSistema(temp.getSistema());
			this.setSucursal(temp.getSucursal());
			this.setTipo(temp.getTipo());
			this.setHoraApertura(temp.getHoraApertura());
			this.setHoraCierre(temp.getHoraCierre());

		}
	}

	public Catalogo getCiudad() {
		return ciudad;
	}

	public void setCiudad(Catalogo ciudad) {
		this.ciudad = ciudad;
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

	/**
	 * @return the horaApertura
	 */
	public Time getHoraApertura() {
		return horaApertura;
	}

	/**
	 * @param horaApertura
	 *            the horaApertura to set
	 */
	public void setHoraApertura(Time horaApertura) {
		this.horaApertura = horaApertura;
	}

	/**
	 * @return the horaCierre
	 */
	public Time getHoraCierre() {
		return horaCierre;
	}

	/**
	 * @param horaCierre
	 *            the horaCierre to set
	 */
	public void setHoraCierre(Time horaCierre) {
		this.horaCierre = horaCierre;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Catalogo getSistema() {
		return sistema;
	}

	public void setSistema(Catalogo sistema) {
		this.sistema = sistema;
	}

	public Catalogo getTipo() {
		return tipo;
	}

	public void setTipo(Catalogo tipo) {
		this.tipo = tipo;
	}

	public Catalogo getSucursal() {
		return sucursal;
	}

	public void setSucursal(Catalogo sucursal) {
		this.sucursal = sucursal;
	}

	public Catalogo getOficina() {
		return oficina;
	}

	public void setOficina(Catalogo oficina) {
		this.oficina = oficina;
	}

	public Catalogo getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Catalogo municipio) {
		this.municipio = municipio;
	}

	public Catalogo getPais() {
		return pais;
	}

	public void setPais(Catalogo pais) {
		this.pais = pais;
	}

	public Catalogo getProvincia() {
		return provincia;
	}

	public void setProvincia(Catalogo provincia) {
		this.provincia = provincia;
	}

	public Collection<Umbral> getUmbral() {
		return umbral;
	}

	public void setUmbral(Collection<Umbral> umbral) {
		this.umbral = umbral;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Origen) {
			Origen temp = (Origen) obj;

			try {

				if (this.getOid().equals(temp.getOid()) && this.getCiudad().equals(temp.getCiudad())
						&& this.getIp().equals(temp.getIp()) &&

						this.getMac().equals(temp.getMac()) && this.getModelo().equals(temp.getModelo())
						&& this.getMunicipio().equals(temp.municipio) && this.getNombre().equals(temp.getNombre())
						&& this.getOficina().equals(temp.oficina) && this.getPais().equals(temp.getPais())
						&& this.getSistema().equals(temp.getSistema()) && this.getSucursal().equals(temp.getSucursal())
						&& this.getTipo().equals(temp.getTipo())
						&& this.getHoraApertura().equals(temp.getHoraApertura())
						&& this.getHoraCierre().equals(temp.getHoraCierre()))
					return true;

			} catch (Exception e) {

				return false;// TODO: handle exception
			}
		}
		return false;
	}

}
