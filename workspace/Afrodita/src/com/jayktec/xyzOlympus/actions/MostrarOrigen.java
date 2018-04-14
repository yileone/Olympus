package com.jayktec.xyzOlympus.actions;
import org.openxava.actions.*;

import javassist.tools.rmi.*;

public class MostrarOrigen extends ViewBaseAction implements INavigationAction{

	@Override
	public String[] getNextControllers() throws Exception {
		// TODO Auto-generated method stub
		return  new String [] {"VerChartxyLines"};
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
		
		try{
			
			/*System.out.println(" Exute-->"+getView().getValue("origen.oid"));
			System.out.println(" Exute-->"+getView().getValue("origen.name"));*/
			
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addError("SYSTEM_ERRO");
		}
	}

	@Override
	public String getCustomView() throws Exception {
		// TODO Auto-generated method stub
		return "jsp-propios/loadOrigenRegistroCharts.jsp";
	}
	
	
	
	public void setKeyProperty(String s){
		
		
	}

}
