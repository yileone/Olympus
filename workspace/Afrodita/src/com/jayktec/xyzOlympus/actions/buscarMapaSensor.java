package com.jayktec.xyzOlympus.actions;

import org.openxava.actions.OnChangePropertyBaseAction;

public class buscarMapaSensor extends OnChangePropertyBaseAction {
	
	 public void execute() throws Exception {
		 
		 System.out.println("HEY");
		 if (getNewValue() == null) {
			/* System.out.println("buscarMapaSensor");*/
		 return;
		 }
			/* System.out.println("ddddddddddddddddddd");*/
		 getView().setValue("mapa",  getNewValue());
		 addMessage("transportista_cambiado");
		 
		 
		 if(""==""){System.out.println("");}
		 
		 }

	 
	
	 
	 
}
