package com.jayktec.xyzOlympus.actions;
import org.openxava.actions.*;

public class MostrarGrafricoZoomxyLine extends BaseAction implements INavigationAction{
	private String controller;
	@Override
	public String[] getNextControllers() throws Exception {
		// TODO Auto-generated method stub
		return  new String [] {"volver"};
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public String getCustomView() throws Exception {
		// TODO Auto-generated method stub
		return "jsp-propios/zoomxyLine.jsp";
	}
	public void setKeyProperty(String s){
		
		
	}
	
	
	   public void setController(String controller) {
	        this.controller = controller;
	    }

	    public String getController() {
	        return controller;
	    }
	    


}
