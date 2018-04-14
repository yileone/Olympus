/**
 * 
 */
package com.jayktec.xyzOlympus.transitorio;

import java.sql.*;
import java.util.*;
import java.util.Date;

import com.jayktec.xyzOlympus.models.Origen;
import com.jayktec.xyzOlympus.models.Sensor;

/**
 * @author {Yisheng León Espinoza}
 * 8 mar. 2018
 * www.jayktec.com.ve
 */

public class MediaMovil {

	/**
	 * 
	 */
	
	int int1;
	int int2;
	int int3;
	int int4;
	int int5;
	float float1;
	float float2;
	float float3;
	float float4;
	float float5;
	Time hora;
	Date dia;
	Sensor sensor;
	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	Origen origen;
	public MediaMovil() {
		// TODO Auto-generated constructor stub
	}

	public int getInt1() {
		return int1;
	}

	public void setInt1(int int1) {
		this.int1 = int1;
	}

	public int getInt2() {
		return int2;
	}

	public void setInt2(int int2) {
		this.int2 = int2;
	}

	public int getInt3() {
		return int3;
	}

	public void setInt3(int int3) {
		this.int3 = int3;
	}

	public int getInt4() {
		return int4;
	}

	public void setInt4(int int4) {
		this.int4 = int4;
	}

	public int getInt5() {
		return int5;
	}

	public void setInt5(int int5) {
		this.int5 = int5;
	}

	public float getFloat1() {
		return float1;
	}

	public void setFloat1(float float1) {
		this.float1 = float1;
	}

	public float getFloat2() {
		return float2;
	}

	public void setFloat2(float float2) {
		this.float2 = float2;
	}

	public float getFloat3() {
		return float3;
	}

	public void setFloat3(float float3) {
		this.float3 = float3;
	}

	public float getFloat4() {
		return float4;
	}

	public void setFloat4(float float4) {
		this.float4 = float4;
	}

	public float getFloat5() {
		return float5;
	}

	public void setFloat5(float float5) {
		this.float5 = float5;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(java.util.Date date) {
		this.dia = date;
	}

	
}
