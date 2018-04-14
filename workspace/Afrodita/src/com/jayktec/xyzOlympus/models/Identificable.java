package com.jayktec.xyzOlympus.models;

import java.io.*;

import javax.persistence.*;

import org.openxava.annotations.*;


@MappedSuperclass
public class Identificable  implements Serializable{
	
	@Id
	@Hidden
	@Column(length=11)
	private int oid;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}
	
	

}
