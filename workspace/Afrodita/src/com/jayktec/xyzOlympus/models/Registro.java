package com.jayktec.xyzOlympus.models;

import java.sql.*;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Entity
@Table(name = "fateon_registro")
@View(name = "Simple", members = "registroFloat1,registroFloat2,registroDate1")
public class Registro {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Hidden
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "registro_id", length = 32)
	private String oid;

	@Hidden
	@Column(name = "registro_fecha", length = 45)
	private String registroFecha;

	@Column(name = "registro_float_1", length = 45)
	private Float registroFloat1;

	@Column(name = "registro_float_2", length = 45)
	private Float registroFloat2;
	@Column(name = "registro_float_3", length = 45)
	private Float registroFloat3;

	@Column(name = "registro_float_4", length = 45)
	private Float registroFloat4;
	@Column(name = "registro_float_5", length = 45)
	private Float registroFloat5;

	@Column(name = "registro_int_1", length = 45)
	private int registroInt1;
	
	@Column(name = "registro_int_2", length = 45)
	private int registroInt2;
	@Column(name = "registro_int_3", length = 45)
	private int registroInt3;
	@Column(name = "registro_int_4", length = 45)
	private int registroInt4;
	@Column(name = "registro_int_5", length = 45)
	private int registroInt5;
	
	@Column(name = "registro_varchar_1", length = 45)
	private String registroVarchar1;

	@Column(name = "registro_varchar_2", length = 45)
	private String registroVarchar2;

	@Column(name = "registro_varchar_3", length = 45)
	private String registroVarchar3;

	@Column(name = "registro_varchar_4", length = 45)
	private String registroVarchar4;

	@Column(name = "registro_varchar_5", length = 45)
	private String registroVarchar5;
	
	@Column(name = "registro_date_1", length = 10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date registroDate1;

	@Column(name = "registro_date_2", length = 10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date registroDate2;
	@Column(name = "registro_date_3", length = 10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date registroDate3;
	@Column(name = "registro_date_4", length = 10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date registroDate4;
	@Column(name = "registro_date_5", length = 10)
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date registroDate5;

	@Column(name = "registro_time_1", length = 9)
	@Stereotype("HORA_MINUTO")
	private Time registrotime1;

	@Column(name = "registro_time_2", length = 9)
	@Stereotype("HORA_MINUTO")
	private Time registrotime2;

	@Column(name = "registro_time_3", length = 9)
	@Stereotype("HORA_MINUTO")
	private Time registrotime3;

	@Column(name = "registro_time_4", length = 9)
	@Stereotype("HORA_MINUTO")
	private Time registrotime4;

	@Column(name = "registro_time_5", length = 9)
	@Stereotype("HORA_MINUTO")
	private Time registrotime5;

	@NoModify
	@NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "origen_id", insertable = true, updatable = true)
	private Origen origen;

	@NoModify
	@NoCreate
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "sensor_id", insertable = true, updatable = true)
	private Sensor sensor;
    
	@Transient
	private boolean habil;
	@Transient
	private boolean reducido; 
	
	
	public boolean isReducido() {
		return reducido;
	}

	public void setReducido(boolean reducido) {
		this.reducido = reducido;
	}

	public boolean isHabil() {
		return habil;
	}

	public void setHabil(boolean habil) {
		this.habil = habil;
	}

	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;

	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getRegistroFecha() {
		return registroFecha;
	}

	public void setRegistroFecha(String registroFecha) {
		this.registroFecha = registroFecha;
	}

	public Float getRegistroFloat1() {
		return registroFloat1;
	}

	public void setRegistroFloat1(Float registroFloat1) {
		this.registroFloat1 = registroFloat1;
	}

	public Float getRegistroFloat2() {
		return registroFloat2;
	}

	public void setRegistroFloat2(Float registroFloat2) {
		this.registroFloat2 = registroFloat2;
	}

	/**
	 * @return the registroFloat3
	 */
	public Float getRegistroFloat3() {
		return registroFloat3;
	}

	/**
	 * @param registroFloat3
	 *            the registroFloat3 to set
	 */
	public void setRegistroFloat3(Float registroFloat3) {
		this.registroFloat3 = registroFloat3;
	}

	/**
	 * @return the registroFloat4
	 */
	public Float getRegistroFloat4() {
		return registroFloat4;
	}

	/**
	 * @param registroFloat4
	 *            the registroFloat4 to set
	 */
	public void setRegistroFloat4(Float registroFloat4) {
		this.registroFloat4 = registroFloat4;
	}

	/**
	 * @return the registroFloat5
	 */
	public Float getRegistroFloat5() {
		return registroFloat5;
	}

	/**
	 * @param registroFloat5
	 *            the registroFloat5 to set
	 */
	public void setRegistroFloat5(Float registroFloat5) {
		this.registroFloat5 = registroFloat5;
	}

	public Date getRegistroDate1() {
		return registroDate1;
	}

	public void setRegistroDate1(Date registroDate1) {
		this.registroDate1 = registroDate1;
	}

	/**
	 * @return the registroDate2
	 */
	public Date getRegistroDate2() {
		return registroDate2;
	}

	/**
	 * @param registroDate2 the registroDate2 to set
	 */
	public void setRegistroDate2(Date registroDate2) {
		this.registroDate2 = registroDate2;
	}

	/**
	 * @return the registroDate3
	 */
	public Date getRegistroDate3() {
		return registroDate3;
	}

	/**
	 * @param registroDate3 the registroDate3 to set
	 */
	public void setRegistroDate3(Date registroDate3) {
		this.registroDate3 = registroDate3;
	}

	/**
	 * @return the registroDate4
	 */
	public Date getRegistroDate4() {
		return registroDate4;
	}

	/**
	 * @param registroDate4 the registroDate4 to set
	 */
	public void setRegistroDate4(Date registroDate4) {
		this.registroDate4 = registroDate4;
	}

	/**
	 * @return the registroDate5
	 */
	public Date getRegistroDate5() {
		return registroDate5;
	}

	/**
	 * @param registroDate5 the registroDate5 to set
	 */
	public void setRegistroDate5(Date registroDate5) {
		this.registroDate5 = registroDate5;
	}

	public Time getRegistrotime1() {
		return registrotime1;
	}

	public void setRegistrotime1(Time timestamp) {
		this.registrotime1 = timestamp;
	}

	/**
	 * @return the registrotime2
	 */
	public Time getRegistrotime2() {
		return registrotime2;
	}

	/**
	 * @param timestamp the registrotime2 to set
	 */
	public void setRegistrotime2(Time timestamp) {
		this.registrotime2 = timestamp;
	}

	/**
	 * @return the registrotime3
	 */
	public Time getRegistrotime3() {
		return registrotime3;
	}

	/**
	 * @param registrotime3 the registrotime3 to set
	 */
	public void setRegistrotime3(Time registrotime3) {
		this.registrotime3 = registrotime3;
	}

	/**
	 * @return the registrotime4
	 */
	public Time getRegistrotime4() {
		return registrotime4;
	}

	/**
	 * @param registrotime4 the registrotime4 to set
	 */
	public void setRegistrotime4(Time registrotime4) {
		this.registrotime4 = registrotime4;
	}

	/**
	 * @return the registrotime5
	 */
	public Time getRegistrotime5() {
		return registrotime5;
	}

	/**
	 * @param registrotime5 the registrotime5 to set
	 */
	public void setRegistrotime5(Time registrotime5) {
		this.registrotime5 = registrotime5;
	}

	/**
	 * @return the registroInt1
	 */
	public int getRegistroInt1() {
		return registroInt1;
	}

	/**
	 * @param registroInt1 the registroInt1 to set
	 */
	public void setRegistroInt1(int registroInt1) {
		this.registroInt1 = registroInt1;
	}

	/**
	 * @return the registroInt2
	 */
	public int getRegistroInt2() {
		return registroInt2;
	}

	/**
	 * @param registroInt2 the registroInt2 to set
	 */
	public void setRegistroInt2(int registroInt2) {
		this.registroInt2 = registroInt2;
	}

	/**
	 * @return the registroInt3
	 */
	public int getRegistroInt3() {
		return registroInt3;
	}

	/**
	 * @param registroInt3 the registroInt3 to set
	 */
	public void setRegistroInt3(int registroInt3) {
		this.registroInt3 = registroInt3;
	}

	/**
	 * @return the registroInt4
	 */
	public int getRegistroInt4() {
		return registroInt4;
	}

	/**
	 * @param registroInt4 the registroInt4 to set
	 */
	public void setRegistroInt4(int registroInt4) {
		this.registroInt4 = registroInt4;
	}

	/**
	 * @return the registroInt5
	 */
	public int getRegistroInt5() {
		return registroInt5;
	}

	/**
	 * @param registroInt5 the registroInt5 to set
	 */
	public void setRegistroInt5(int registroInt5) {
		this.registroInt5 = registroInt5;
	}

	/**
	 * @return the registroVarchar1
	 */
	public String getRegistroVarchar1() {
		return registroVarchar1;
	}

	/**
	 * @param registroVarchar1 the registroVarchar1 to set
	 */
	public void setRegistroVarchar1(String registroVarchar1) {
		this.registroVarchar1 = registroVarchar1;
	}

	/**
	 * @return the registroVarchar2
	 */
	public String getRegistroVarchar2() {
		return registroVarchar2;
	}

	/**
	 * @param registroVarchar2 the registroVarchar2 to set
	 */
	public void setRegistroVarchar2(String registroVarchar2) {
		this.registroVarchar2 = registroVarchar2;
	}

	/**
	 * @return the registroVarchar3
	 */
	public String getRegistroVarchar3() {
		return registroVarchar3;
	}

	/**
	 * @param registroVarchar3 the registroVarchar3 to set
	 */
	public void setRegistroVarchar3(String registroVarchar3) {
		this.registroVarchar3 = registroVarchar3;
	}

	/**
	 * @return the registroVarchar4
	 */
	public String getRegistroVarchar4() {
		return registroVarchar4;
	}

	/**
	 * @param registroVarchar4 the registroVarchar4 to set
	 */
	public void setRegistroVarchar4(String registroVarchar4) {
		this.registroVarchar4 = registroVarchar4;
	}

	/**
	 * @return the registroVarchar5
	 */
	public String getRegistroVarchar5() {
		return registroVarchar5;
	}

	/**
	 * @param registroVarchar5 the registroVarchar5 to set
	 */
	public void setRegistroVarchar5(String registroVarchar5) {
		this.registroVarchar5 = registroVarchar5;
	}
	
	public int compare(Registro o1, Registro o2) {
				
		// TODO Auto-generated method stub
		return 0;
	}
	

	
	

}
