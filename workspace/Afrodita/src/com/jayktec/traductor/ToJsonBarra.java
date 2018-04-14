/**
 * 
 */
package com.jayktec.traductor;

import java.sql.*;
import java.util.*;

import org.hibernate.hql.internal.ast.tree.*;

import com.jayktec.persistencia.*;
import com.jayktec.xyzOlympus.models.*;
import com.sun.org.apache.bcel.internal.generic.*;

/**
 * @author {Yisheng León Espinoza} 5 mar. 2018 www.jayktec.com.ve
 */
public class ToJsonBarra extends ToJson {

	private int yaxismaxvalue;
	private String rotatevalues = "0";
	private String theme = "zune";
	private String palettecolors = "#0075c2";

	public ToJsonBarra(ArrayList<Registro> registros, String origen, Sensor sensor) throws SQLException {
		super(registros, origen, sensor);
		// TODO Auto-generated constructor stub
	}

	public ToJsonBarra(String origen, Sensor sensor) throws SQLException {
		super(origen, sensor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crearEncabezado() {
		// TODO Auto-generated method stub

		crearEncabezado(true);
	}

	public void crearEncabezado(boolean actualizarYaxismaxValue) {
		// TODO Auto-generated method stub
		if (actualizarYaxismaxValue)
			actualizarYaxismaxValue();
		this.encabezado = "chart\": {" + " \"caption\": " + " \"" + origen.getNombre() + "-" + sensor.getNombre() + ","
				+ "\"xaxisname\":" + " \"" + getxName() + "\"" + "," + "\"yaxisname\":" + " \"" + getyName() + "\""
				+ "," + "\"" + "," + "\"numberprefix\":" + " \"" + getNumberPrefix() + "\"" + "," + "\"yaxismaxvalue\":"
				+ " \"" + getYaxismaxvalue() + "\"" + "," + "\"rotatevalues\":" + " \"" + getRotatevalues() + "\"" + ","
				+ "\"theme\":" + " \"" + getTheme() + "\"" + "," + "\"palettecolors\":" + " \"" + getPalettecolors()

				+ "\"" + "," + "},";

		System.out.println(encabezado);
	}

	/**
	 * 
	 */
	private void actualizarYaxismaxValue() {
		// TODO Auto-generated method stub
		float temp = 0;

		for (Registro registro : listaRegistro) {


			float temp2 = 0;
			if (registro.getRegistroFloat1() != null)
				if (temp2 < registro.getRegistroFloat1())
					temp2 = registro.getRegistroFloat1();
			if (temp2 < registro.getRegistroFloat2())
				temp2 = registro.getRegistroFloat2();
			if (temp2 < registro.getRegistroFloat3())
				temp2 = registro.getRegistroFloat3();
			if (temp2 < registro.getRegistroFloat4())
				temp2 = registro.getRegistroFloat4();

			if (temp2 < registro.getRegistroFloat5())
				temp2 = registro.getRegistroFloat5();

			if (temp2 < registro.getRegistroInt1())
				temp2 = registro.getRegistroInt1();
			if (temp2 < registro.getRegistroInt2())
				temp2 = registro.getRegistroInt2();
			if (temp2 < registro.getRegistroInt3())
				temp2 = registro.getRegistroInt3();
			if (temp2 < registro.getRegistroInt4())
				temp2 = registro.getRegistroInt4();
			if (temp2 < registro.getRegistroInt5())
				temp2 = registro.getRegistroInt5();

			if (temp2>temp)temp=temp2;
			temp2=0;
		}
		
		setYaxismaxvalue((int)(temp*0.05));
	}

	/**
	 * @return the yaxismaxvalue
	 */
	public int getYaxismaxvalue() {
		return yaxismaxvalue;
	}

	/**
	 * @param yaxismaxvalue
	 *            the yaxismaxvalue to set
	 */
	public void setYaxismaxvalue(int yaxismaxvalue) {
		this.yaxismaxvalue = yaxismaxvalue;
	}

	/**
	 * @return the rotatevalues
	 */
	public String getRotatevalues() {
		return rotatevalues;
	}

	/**
	 * @param rotatevalues
	 *            the rotatevalues to set
	 */
	public void setRotatevalues(String rotatevalues) {
		this.rotatevalues = rotatevalues;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme
	 *            the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the palettecolors
	 */
	public String getPalettecolors() {
		return palettecolors;
	}

	/**
	 * @param palettecolors
	 *            the palettecolors to set
	 */
	public void setPalettecolors(String palettecolors) {
		this.palettecolors = palettecolors;
	}

}
