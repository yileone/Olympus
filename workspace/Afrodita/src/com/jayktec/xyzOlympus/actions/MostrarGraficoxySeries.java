package com.jayktec.xyzOlympus.actions;
import org.openxava.actions.*;

public class MostrarGraficoxySeries extends ViewBaseAction implements INavigationAction{

	@Override
	public String[] getNextControllers() throws Exception {
		// TODO Auto-generated method stub
		return  new String [] {"atras"};
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
		//getView().reset();
	}

	@Override
	public String getCustomView() throws Exception {
		// TODO Auto-generated method stub
		return "jsp-propios/xySeries.jsp";
	}
	public void setKeyProperty(String s){
		
		
	}
}
