package com.jayktec.xyzOlympus.actions;
import org.openxava.actions.*;

public class MostrarGraficoBuld extends BaseAction implements INavigationAction{


	@Override
	public String[] getNextControllers() throws Exception {
		// TODO Auto-generated method stub
	
		return  new String [] {"Void"};
	}

	@Override
	public void execute() throws Exception {

		/*System.out.println("Aqui Voy ");*/
	}

	@Override
	public String getCustomView() throws Exception {
		// TODO Auto-generated method stub
		return "jsp-propios/bulbJSON.jsp";
	}
	
	public void setKeyProperty(String s){
		
		
	}

}
