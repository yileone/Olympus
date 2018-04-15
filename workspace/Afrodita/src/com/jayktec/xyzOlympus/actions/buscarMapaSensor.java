package com.jayktec.xyzOlympus.actions;

import org.openxava.actions.*;

public class buscarMapaSensor extends OnChangePropertyBaseAction {

	@Override
	public void execute() throws Exception {

		System.out.println("HEY");
		if (getNewValue() == null)
			/* System.out.println("buscarMapaSensor"); */
			return;
		/* System.out.println("ddddddddddddddddddd"); */
		getView().setValue("mapa", getNewValue());
		addMessage("transportista_cambiado");

	}

}
