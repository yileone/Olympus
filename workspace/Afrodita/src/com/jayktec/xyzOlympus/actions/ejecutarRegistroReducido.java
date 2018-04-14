package com.jayktec.xyzOlympus.actions;

import org.openxava.actions.*;
import com.jayktec.batch.RegistroReducidoBatch;

 

public class ejecutarRegistroReducido extends ViewBaseAction implements INavigationAction{

	@Override
	public String[] getNextControllers() throws Exception {
		// TODO Auto-generated method stub
		return  new String [] {"atras"};
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
		RegistroReducidoBatch eje = new RegistroReducidoBatch();
		eje.run();
	}

	@Override
	public String getCustomView() throws Exception {
		// TODO Auto-generated method stub
		return "jsp-propios/loadOrigenRegistroCharts.jsp";
	}
	public void setKeyProperty(String s){
		
		
	}
}



