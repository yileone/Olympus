
// File generated by OpenXava: Fri Oct 06 12:00:54 CEST 2017
// Archivo generado por OpenXava: Fri Oct 06 12:00:54 CEST 2017

// WARNING: NO EDIT
// OJO: NO EDITAR
// Component: State		Entity/Entidad

package org.openxava.test.model;

import java.util.*;
import java.math.*;
import java.rmi.RemoteException;
import org.openxava.component.MetaComponent;
import org.openxava.model.meta.MetaModel;
import org.openxava.util.*;

/**
 * 
 * @author MCarmen Gimeno
 */
public class State implements java.io.Serializable, org.openxava.test.model.IState {	

	// Constructor
	public State() {
		initMembers();
	} 

	private void initMembers() { 
		setId(null); 
		setName(null); 
		setFullNameWithFormula(null); 	
	} 
	
	// Properties/Propiedades 
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String newId) {
		this.id = newId;
	} 
	private static org.openxava.converters.IConverter nameConverter;
	private org.openxava.converters.IConverter getNameConverter() {
		if (nameConverter == null) {
			try {
				nameConverter = (org.openxava.converters.IConverter) 
					getMetaModel().getMapping().getConverter("name");
			}
			catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(XavaResources.getString("generator.create_converter_error", "name"));
			}
			
		}	
		return nameConverter;
	} 
	private java.lang.String name;
	private java.lang.String get_Name() {
		return name;
	}
	private void set_Name(java.lang.String newName) {
		this.name = newName;
	} 	
	
	/**
	 * 
	 * 
	 */
	public String getName() {
		try {
			return (String) getNameConverter().toJava(get_Name());
		}
		catch (org.openxava.converters.ConversionException ex) {
			ex.printStackTrace();
			throw new RuntimeException(XavaResources.getString("generator.conversion_error", "Name", "State", "String"));
		}
	}
	
	/**
	 * 
	 */
	public void setName(String newName) {
		try { 
			set_Name((java.lang.String) getNameConverter().toDB(newName));
		}
		catch (org.openxava.converters.ConversionException ex) {
			ex.printStackTrace();
			throw new RuntimeException(XavaResources.getString("generator.conversion_error", "Name", "State", "String"));
		}		
	} 
	private static org.openxava.converters.IConverter fullNameWithFormulaConverter;
	private org.openxava.converters.IConverter getFullNameWithFormulaConverter() {
		if (fullNameWithFormulaConverter == null) {
			try {
				fullNameWithFormulaConverter = (org.openxava.converters.IConverter) 
					getMetaModel().getMapping().getConverter("fullNameWithFormula");
			}
			catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(XavaResources.getString("generator.create_converter_error", "fullNameWithFormula"));
			}
			
		}	
		return fullNameWithFormulaConverter;
	} 
	private java.lang.String fullNameWithFormula;
	private java.lang.String get_FullNameWithFormula() {
		return fullNameWithFormula;
	}
	private void set_FullNameWithFormula(java.lang.String newFullNameWithFormula) {
		this.fullNameWithFormula = newFullNameWithFormula;
	} 	
	
	/**
	 * 
	 * 
	 */
	public String getFullNameWithFormula() {
		try {
			return (String) getFullNameWithFormulaConverter().toJava(get_FullNameWithFormula());
		}
		catch (org.openxava.converters.ConversionException ex) {
			ex.printStackTrace();
			throw new RuntimeException(XavaResources.getString("generator.conversion_error", "FullNameWithFormula", "State", "String"));
		}
	}
	
	/**
	 * 
	 */
	public void setFullNameWithFormula(String newFullNameWithFormula) {
		try { 
			set_FullNameWithFormula((java.lang.String) getFullNameWithFormulaConverter().toDB(newFullNameWithFormula));
		}
		catch (org.openxava.converters.ConversionException ex) {
			ex.printStackTrace();
			throw new RuntimeException(XavaResources.getString("generator.conversion_error", "FullNameWithFormula", "State", "String"));
		}		
	} 	
	/**
	 * 
	 * 
	 */
	public String getFullName() { 		
		try {			
			org.openxava.calculators.ConcatCalculator fullNameCalculator= (org.openxava.calculators.ConcatCalculator)
				getMetaModel().getMetaProperty("fullName").getMetaCalculator().createCalculator();  	
			
			fullNameCalculator.setString1(getId());  	
			
			fullNameCalculator.setString2(getName()); 
			return (String) fullNameCalculator.calculate();
		}
		catch (NullPointerException ex) {
			// Usually for multilevel property access with null references 
			return null; 			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(XavaResources.getString("generator.calculate_value_error", "FullName", "State", ex.getLocalizedMessage()));
		} 		
	}
	public void setFullName(String newFullName) {
		// for it is in value object
		// para que aparezca en los value objects
	} 

	// References/Referencias 

	// Colecciones/Collections 

	// Methods/Metodos 	

	// User defined finders/Buscadores definidos por el usuario 	
 	public static State findById(java.lang.String id) throws javax.ejb.ObjectNotFoundException {
		org.hibernate.Query query = org.openxava.hibernate.XHibernate.getSession().createQuery("from State as o where o.id = :arg0"); 
		query.setParameter("arg0", id); 
		State r = (State) query.uniqueResult();
		if (r == null) {
			throw new javax.ejb.ObjectNotFoundException(XavaResources.getString("object_not_found", "State"));
		}
		return r; 
 	} 


	private static MetaModel metaModel;
	public MetaModel getMetaModel() throws XavaException {
		if (metaModel == null) {
			metaModel = MetaComponent.get("State").getMetaEntity(); 	
		}
		return metaModel;
	}
	
	public String toString() {		
		try {
			return getMetaModel().toString(this);
		}
		catch (XavaException ex) {
			System.err.println(XavaResources.getString("toString_warning", "State"));
			return super.toString();
		}		
	}

	public boolean equals(Object other) {		
		if (other == null) return false;
		return toString().equals(other.toString());
	}
	
	public int hashCode() {		
		return toString().hashCode();
	}
	
}