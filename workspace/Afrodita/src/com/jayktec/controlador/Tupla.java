/**
 * 
 */
package com.jayktec.controlador;

/**
 * @author {Yisheng León Espinoza} 10 abr. 2018 www.jayktec.com.ve
 */

public class Tupla<X, Y> {
	public Object x;
	public Object y;

	public Tupla(Object x, Object y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public Object getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public Object getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Tupla) {
			Tupla tupla = (Tupla) arg0;

			if (tupla.getX().equals(getX()) && tupla.getY().equals(getY()))
				return true;
		}
		return false;
	}

}
