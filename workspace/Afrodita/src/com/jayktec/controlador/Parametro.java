/**
 * 
 */
package com.jayktec.controlador;

/**
 * @author yisheng
 *
 */
public class Parametro {

	/**
	 * Parametros de un archivo de configuracion 
	 */
	
	String campo="";
	String valor="";
	String nombreUnidad="";
	
	/**
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the nombreUnidad
	 */
	public String getNombreUnidad() {
		return nombreUnidad;
	}

	/**
	 * @param nombreUnidad the nombreUnidad to set
	 */
	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public Parametro() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param campo
	 * @param valor
	 * @param nombreUnidad
	 */
	public Parametro(String campo, String valor, String nombreUnidad) {
		this.campo = campo;
		this.valor = valor;
		this.nombreUnidad = nombreUnidad;
	}

}
