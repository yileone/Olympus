package com.jayktec.xyzOlympus.actions;


import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;


public class ImportRegistro { 
	
	@Hidden
	private String data;
	
	@Hidden
	private String modelName;
	
	@ElementCollection 
	@EditOnly
	private Collection<ImportColumnRegistro> columns;
	
	public Collection<ImportColumnRegistro> getColumns() {
		return columns;
	}

	public void setColumns(Collection<ImportColumnRegistro> columns) {
		this.columns = columns;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}


