package com.jayktec.xyzOlympus.actions;

import javax.inject.Inject;
import javax.inject.Named;

import org.openxava.actions.ILoadFileAction;
import org.openxava.actions.ViewBaseAction;

/**
 *
 *
 * @author Javier Paniza
 */
public class ImportDataActionReg extends ViewBaseAction implements ILoadFileAction {
	@Inject
	@Named("origenOid")
	String origenOid;
	@Inject
	@Named("sensorOid")
	String sensorOid;

	@Override
	public void execute() throws Exception {

		/*
		 * System.out.
		 * println("**************ImportDataActionReg - execute ***********************"
		 * ); System.out.println("");
		 * System.out.println(" EJECUTANDO ACTION ---> ImportDataActionReg" );
		 * System.out.println("    ACTION : ImportDataActionReg" );
		 * System.out.println("NEXTACTION : ConfigureImportReg" );
		 */
		System.out.println("    MODELO : " + getView().getModelName());
		setSensorOid((String) getView().getValue("sensor.oid"));
		setOrigenOid((String) getView().getValue("origen.oid"));
		assert origenOid == getOrigenOid();
		assert sensorOid == getSensorOid();
		/*
		 * System.out.println("    SENSOR : "+ sensorOid );
		 * System.out.println("    ORIGEN : "+ origenOid); System.out.println("");
		 * System.out.println("*************************************");
		 * System.out.println("");
		 * System.out.println("*************************************");
		 */

		showDialog();

	}

	@Override
	public String getCustomView() {
		/*
		 * System.out.println("*************************************");
		 * System.out.println(""); System.out.
		 * println("INVOCANDO EL JSP: chooseFile.jsp en el action ---> ImportDataActionReg"
		 * ); System.out.println("");
		 * System.out.println("*************************************");
		 */
		return "xava/editors/chooseFile.jsp?accept=.csv, .xlsx, .xls";
	}

	@Override
	public String[] getNextControllers() {
		/*
		 * System.out.
		 * println("************* getNextControllers ************************");
		 * System.out.println(""); System.out.println("    ACTION : ImportDataActionReg"
		 * ); System.out.println("NEXTACTION : ConfigureImportReg" );
		 * System.out.println("    SENSOR : "+ this.getSensorOid());
		 * System.out.println("    ORIGEN : "+ this.getOrigenOid());
		 * System.out.println("");
		 * System.out.println("*************************************");
		 */

		return new String[] { "ConfigureImportReg" };
	}

	public String getOrigenOid() {
		return origenOid;
	}

	public String getSensorOid() {
		return sensorOid;
	}

	@Override
	public boolean isLoadFile() {
		return true;
	}

	public void setOrigenOid(String origenOid) {
		this.origenOid = origenOid;
	}

	public void setSensorOid(String sensorOid) {
		this.sensorOid = sensorOid;
	}

}