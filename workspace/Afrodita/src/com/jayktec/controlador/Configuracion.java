package com.jayktec.controlador;

import java.io.*;
import java.util.*;
import java.util.Map.*;
import java.util.logging.*;

public class Configuracion {
	Properties archivo = null;

	ArrayList<Parametro> parametros = new ArrayList<Parametro>();

	/**
	 * @return the archivo
	 */
	public Properties getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo
	 *            the archivo to set
	 */
	public void setArchivo(Properties archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the parametros
	 */
	public ArrayList<Parametro> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros
	 *            the parametros to set
	 */
	public void setParametros(ArrayList<Parametro> parametros) {
		this.parametros = parametros;
	}

	public Configuracion() {

		if (archivo == null) {

			archivo = Utilidades.loadProperties();
		}
		carga();

	}

	public Configuracion(InputStream archivoPropiedades) {
		System.out.println(archivoPropiedades);
		archivo = Utilidades.loadProperties(archivoPropiedades);
		carga();
	}

	private void carga() {

		String nombre = "";
		String campo = "";
		String valor = "";
		parametros = new ArrayList<Parametro>();

		try {

			for (Entry<Object, Object> entrada : archivo.entrySet()) {
				nombre = nombrePropiedad(entrada.getKey().toString());
				campo = nombreCampo(entrada.getKey().toString());
				valor = (String) entrada.getValue();
				Parametro temp = new Parametro(campo, valor, nombre);
				parametros.add(temp);
			}
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
					"Error fallo  Propiedades: " + e.getMessage());

		}
	}

	/**
	 * devuelve el valor del nombre de una propiedad
	 * 
	 * @param campoPropiedad
	 * @return
	 */
	private String nombrePropiedad(String campoPropiedad) {
		String nombre = "";
		for (int i = 0; i < campoPropiedad.length(); i++) {
			if (campoPropiedad.charAt(i) == '.')
				return nombre;
			nombre = nombre + campoPropiedad.charAt(i);

		}
		return nombre;

	}

	/**
	 * devuelve el valor del nombre de una propiedad
	 * 
	 * @param campoPropiedad
	 * @return
	 */
	private String nombreCampo(String campoPropiedad) {
		String nombre = "";
		for (int i = 0; i < campoPropiedad.length(); i++) {
			if (campoPropiedad.charAt(i) == '.')
				return campoPropiedad.substring(i + 1);

		}
		return nombre;

	}
}