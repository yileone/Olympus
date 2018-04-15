package com.jayktec.xyzOlympus.actions;

import java.util.*;

import javax.inject.*;

import org.openxava.actions.*;
import org.openxava.model.*;
import org.openxava.model.meta.*;
import org.openxava.model.transients.*;
import org.openxava.util.*;
import org.openxava.view.*;
import org.openxava.web.*;

import com.jayktec.xyzOlympus.models.*;;

/**
 *
 * @author Javier Paniza
 */

public class ImportActionReg extends ViewBaseAction implements IChangeControllersAction {

	private String[] nextControllers = DEFAULT_CONTROLLERS;

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
		 * println("************** ImportActionReg - execute ***********************");
		 * System.out.println("");
		 * System.out.println(" EJECUTANDO ACTION ---> ImportDataActionReg" );
		 * System.out.println("    ACTION : ConfigureImportActionReg" );
		 * System.out.println("NEXTACTION : NO LO SE AUN" );
		 */
		setSensorOid(sensorOid);
		setOrigenOid(origenOid);
		assert origenOid == getOrigenOid();
		assert sensorOid == getSensorOid();

		System.out.println("    SENSOR : " + getSensorOid());
		System.out.println("    ORIGEN : " + getOrigenOid());
		System.out.println("");
		/*
		 * System.out.println("*************************************");
		 * System.out.println("");
		 * System.out.println("******************* Antes ******************");
		 */

		getView().updateModelFromView();
		/* System.out.println("*******************  "+getView().getModel()); */
		Import imp = (Import) getView().getModel();
		/* System.out.println("******************* Antes 1******************"); */

		// ImportColumn columOrigen= new ImportColumn();
		// imp.getColumns().add(e)
		if (!validateRequired(imp)) {
			System.out.println("ERROR..!");
			return;

		} else {
			System.out.println("PASO");
		}
		/* System.out.println("******************* Despues ******************"); */
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(imp.getData());
		/* System.out.println("******************* Antes ******************"); */
		if (scanner.hasNextLine()) {
			scanner.nextLine();
		} else {
			System.out.println("ERROR2..!");
		} // We skip first line, the header
		/* System.out.println("******************* Despues ******************"); */
		String separator = XavaPreferences.getInstance().getCSVSeparator();
		int count = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (Is.emptyString(line)) {
				continue;
			}
			Map values = new HashMap();
			StringTokenizer fields = new StringTokenizer(line, separator);
			for (ImportColumn column : imp.getColumns()) {
				if (!fields.hasMoreElements()) {
					break;
				}
				String rawValue = fields.nextToken();
				System.out.println("rawvalue:" + rawValue);
				if (!Is.empty(column.getNameInApp())) {
					View moduleView = getPreviousView();
					MetaProperty p = moduleView.getMetaModel().getMetaProperty(column.getNameInApp());
					/* System.out.println("MetaProperty:"+p); */
					Object value = WebEditors.parse(getRequest(), p, Strings.unquote(rawValue), getErrors(),
							moduleView.getViewName());
					values.put(column.getNameInApp(), value);
					/* System.out.println("clave=" + column.getNameInApp() + ", valor=" + value); */
				}
			}

			Origen origen = new Origen();
			origen.setOid(getOrigenOid());
			Sensor sensor = new Sensor();
			sensor.setOid(getSensorOid());
			/* System.out.println("fallo 1"); */
			View moduleView = getPreviousView();
			/* System.out.println("fallo 2"); */
			MetaProperty p = moduleView.getMetaModel().getMetaProperty("origen.oid");
			WebEditors.parse(getRequest(), p, Strings.unquote(getOrigenOid()), getErrors(), moduleView.getViewName());

			values.put("origen", origen);
			moduleView = getPreviousView();
			p = moduleView.getMetaModel().getMetaProperty("sensor.oid");
			WebEditors.parse(getRequest(), p, Strings.unquote(getSensorOid()), getErrors(), moduleView.getViewName());

			values.put("sensor", sensor);

			/*
			 * System.out.println("clave=" + "sensor.oid" + ", valor=" +
			 * values.get("sensor")); System.out.println("clave=" + "origen.oid" +
			 * ", valor=" + values.get("origen"));
			 */

			try {

				/* System.out.println("***************** Model: "+imp.getModelName()); */
				MapFacade.create(imp.getModelName(), values);
				count++;
			} catch (org.openxava.validators.ValidationException ex) {
				Messages errors = ex.getErrors();
				for (Object message : errors.getStrings(getRequest())) {
					addError("import_error", values, message);
					/* System.out.println("*******  For del primer catch"); */
				}
			} catch (Exception ex) {
				addError("import_error", values, ex.getMessage());
				/* System.out.println("*******  import_error"); */
			}
		}
		if (count > 0) {
			addMessage("records_imported", count);
		} else {
			addError("no_records_imported");
		}
		closeDialog();
	}

	private boolean validateRequired(Import imp) {
		MetaModel metaModel = MetaModel.get(imp.getModelName());
		Collection<String> requiredMembers = new ArrayList(metaModel.getRequiredMemberNames());
		/*
		 * System.out.println("===== requiredMembers==");
		 * System.out.println(requiredMembers.size());
		 */
		// se annaden en la vista
		requiredMembers.remove("origen");
		requiredMembers.remove("sensor");
		for (ImportColumn column : imp.getColumns()) {
			requiredMembers.remove(column.getNameInApp());

		}
		if (requiredMembers.isEmpty())
			return true;
		StringBuffer members = new StringBuffer();
		for (String member : requiredMembers) {
			if (members.length() > 0) {
				members.append(", ");
			}
			members.append(metaModel.getMetaMember(member).getLabel());
		}
		/* System.out.println("import_required_members:"+ members.toString()); */

		addError("import_required_members", members.toString());
		nextControllers = SAME_CONTROLLERS;
		return false;
	}

	@Override
	public String[] getNextControllers() throws Exception {
		return nextControllers;
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
