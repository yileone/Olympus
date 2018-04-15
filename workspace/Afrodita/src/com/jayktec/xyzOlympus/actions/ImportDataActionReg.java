package com.jayktec.xyzOlympus.actions;

import javax.inject.*;

import org.openxava.actions.*;

import com.jayktec.controlador.*;
import com.jayktec.persistencia.*;

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
		System.out.println("TRUNCATE TABLE " + Constantes.TablaBD.REGISTRO);

		BdManager.truncarTabla(Constantes.TablaBD.REGISTRO);

		showDialog();

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
	public boolean isLoadFile() {
		return true;
	}

	public String getOrigenOid() {
		return origenOid;
	}

	public void setOrigenOid(String origenOid) {
		this.origenOid = origenOid;
	}

	public String getSensorOid() {
		return sensorOid;
	}

	public void setSensorOid(String sensorOid) {
		this.sensorOid = sensorOid;
	}

}