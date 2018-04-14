package com.jayktec.xyzOlympus.models;


import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import javax.persistence.Column;
import org.openxava.annotations.Hidden;;


@MappedSuperclass
public class Identificable implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@Hidden
	@Column(length=11)
	private int oid;

	Identificable() {
		
	}
	
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}
	
	

}
