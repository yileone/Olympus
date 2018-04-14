/**
 * 
 */
package com.jayktec.controller;

import java.sql.*;
import java.util.logging.*;

import com.jayktec.controlador.*;

/**
 * @author arte
 *
 */
public class Conexion {

	Connection conexion;
	//private String url = "jdbc:mysql://localhost:3306/"+Constantes.BD;
	private String url = "jdbc:mysql://45.7.229.159:3306/"+Constantes.BD;
	private String username = "mysql";
	private String password = "afrodita";

	/**
	 * 
	 */

	public Conexion() {
		System.out.println("url:"+url);

		System.out.println("username:"+username);

		System.out.println("password:"+ password);
	
		try {

			conexion = DriverManager.getConnection(url, username, password);

			System.out.println("Database connected!");
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
					"Cannot connect the database!" + e.getMessage());

		}
	}

	/**
	 * @return the conexion
	 */
	public Connection getConexion() {
		return conexion;
	}

	/**
	 * @param conexion the conexion to set
	 */
	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	
	
	
	
}
