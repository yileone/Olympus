/**
 * 
 */
package com.jayktec.batch;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import com.jayktec.controlador.*;

/**
 * @author {Yisheng León Espinoza} 19 mar. 2018 www.jayktec.com.ve
 */
public class Ejecucion {

	private String[] argumentos;
	Configuracion configuracion;
	private ArrayList<Parametro> parametros;

	
	/**
	 * 
	 */
	public Ejecucion() {

		configuracion = new Configuracion(Ejecucion.class.getResourceAsStream(Constantes.ARCHIVO_PROPERTIES));
		this.setParametros(configuracion.getParametros());
		getParametros().size();
	}

	public Ejecucion(String[] argumentos) {
		this();
		this.setArgumentos(argumentos);

	}


	/**
	 * @return the argumentos
	 */
	public String[] getArgumentos() {
		return argumentos;
	}

	/**
	 * @param argumentos
	 *            the argumentos to set
	 */
	public void setArgumentos(String[] argumentos) {
		this.argumentos = argumentos;
	}

	/**
	 * @return the parametros
	 */
	public ArrayList<Parametro> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(ArrayList<Parametro> parametros) {
		this.parametros = parametros;
	}
	
	public String valorParametro(String nombreUnidad)
	{
		for (Parametro parametro : parametros) {
			if (parametro.getNombreUnidad().equals(nombreUnidad))
				return parametro.getValor();
		
		}
		
		return null;
	}	
	public void run() throws ParseException
	{
	//TODO LOG DE EJECUCION
	}
	
	public void stop()
	{
		
	}
}
