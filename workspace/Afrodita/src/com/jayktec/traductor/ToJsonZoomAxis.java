/**
 * 
 */
package com.jayktec.traductor;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.google.gson.*;
import com.jayktec.controlador.Constantes.*;
import com.jayktec.xyzOlympus.models.*;

/**
 * @author {Yisheng León Espinoza}
 * 7 mar. 2018
 * www.jayktec.com.ve
 */
public class ToJsonZoomAxis extends ToJson{

	

	public ToJsonZoomAxis(String origen, Sensor sensor) throws SQLException {
		super(origen, sensor);
		// TODO Auto-generated constructor stub
	}

	public ToJsonZoomAxis(String origen, String sensor) throws SQLException {
		super(origen, sensor);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param registros
	 * @param origen
	 * @param sensor
	 * @throws SQLException
	 */
	
	public ToJsonZoomAxis(ArrayList<Registro> registros, String origen, Sensor sensor) throws SQLException {
		super(registros, origen, sensor);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#crearEncabezado()
	 */
	@Override
	public void crearEncabezado() {
		// TODO Auto-generated method stub
		super.crearEncabezado();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#iniciarYaxisName()
	 */
	@Override
	public void iniciarYaxisName() {
		// TODO Auto-generated method stub
		super.iniciarYaxisName();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#crearEncabezado(java.lang.String, java.lang.String)
	 */
	@Override
	public void crearEncabezado(String yAxisName, String xAxisName) {
		// TODO Auto-generated method stub
		super.crearEncabezado(yAxisName, xAxisName);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#crearCategorias()
	 */
	@Override
	public void crearCategorias() {
		// TODO Auto-generated method stub
		super.crearCategorias();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#creaDataset()
	 */
	@Override
	public void creaDataset() throws SQLException {
		// TODO Auto-generated method stub
		super.creaDataset();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#refrescarJson(java.lang.Object)
	 */
	@Override
	public void refrescarJson(Object object) throws SQLException, JsonIOException, IOException {
		// TODO Auto-generated method stub
		super.refrescarJson(object);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#crearJson()
	 */
	@Override
	public int crearJson() throws SQLException, JsonIOException, IOException {
		// TODO Auto-generated method stub
		return super.crearJson();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#armarJson()
	 */
	@Override
	public String armarJson() throws SQLException {
		// TODO Auto-generated method stub
		return super.armarJson();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#mapaParaSensor()
	 */
	@Override
	public void mapaParaSensor() throws SQLException {
		// TODO Auto-generated method stub
		super.mapaParaSensor();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#mapaParaSensor(com.jayktec.xyzOlympus.models.Sensor)
	 */
	@Override
	public void mapaParaSensor(Sensor sensor) throws SQLException {
		// TODO Auto-generated method stub
		super.mapaParaSensor(sensor);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#mapaParaSensor(com.jayktec.xyzOlympus.models.Registro)
	 */
	@Override
	public void mapaParaSensor(Registro registro) throws SQLException {
		// TODO Auto-generated method stub
		super.mapaParaSensor(registro);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getSensor()
	 */
	@Override
	public Sensor getSensor() {
		// TODO Auto-generated method stub
		return super.getSensor();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setSensor(com.jayktec.xyzOlympus.models.Sensor)
	 */
	@Override
	public void setSensor(Sensor sensor) {
		// TODO Auto-generated method stub
		super.setSensor(sensor);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getRegistro()
	 */
	@Override
	public Registro getRegistro() {
		// TODO Auto-generated method stub
		return super.getRegistro();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setRegistro(com.jayktec.xyzOlympus.models.Registro)
	 */
	@Override
	public void setRegistro(Registro registro) {
		// TODO Auto-generated method stub
		super.setRegistro(registro);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getListaRegistro()
	 */
	@Override
	public ArrayList<Registro> getListaRegistro() {
		// TODO Auto-generated method stub
		return super.getListaRegistro();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setListaRegistro(java.util.ArrayList)
	 */
	@Override
	public void setListaRegistro(ArrayList<Registro> listaRegistro) {
		// TODO Auto-generated method stub
		super.setListaRegistro(listaRegistro);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getMapa()
	 */
	@Override
	public ArrayList<Mapa> getMapa() {
		// TODO Auto-generated method stub
		return super.getMapa();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setMapa(java.util.ArrayList)
	 */
	@Override
	public void setMapa(ArrayList<Mapa> mapas) {
		// TODO Auto-generated method stub
		super.setMapa(mapas);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getOrigen()
	 */
	@Override
	public Origen getOrigen() {
		// TODO Auto-generated method stub
		return super.getOrigen();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setOrigen(com.jayktec.xyzOlympus.models.Origen)
	 */
	@Override
	public void setOrigen(Origen origen) {
		// TODO Auto-generated method stub
		super.setOrigen(origen);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getxName()
	 */
	@Override
	public String getxName() {
		// TODO Auto-generated method stub
		return super.getxName();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setxName(java.lang.String)
	 */
	@Override
	public void setxName(String xName) {
		// TODO Auto-generated method stub
		super.setxName(xName);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getyName()
	 */
	@Override
	public String getyName() {
		// TODO Auto-generated method stub
		return super.getyName();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setyName(java.lang.String)
	 */
	@Override
	public void setyName(String yName) {
		// TODO Auto-generated method stub
		super.setyName(yName);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getShowValues()
	 */
	@Override
	public int getShowValues() {
		// TODO Auto-generated method stub
		return super.getShowValues();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setShowValues(int)
	 */
	@Override
	public void setShowValues(int showValues) {
		// TODO Auto-generated method stub
		super.setShowValues(showValues);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getLegendborderalpha()
	 */
	@Override
	public int getLegendborderalpha() {
		// TODO Auto-generated method stub
		return super.getLegendborderalpha();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setLegendborderalpha(int)
	 */
	@Override
	public void setLegendborderalpha(int legendborderalpha) {
		// TODO Auto-generated method stub
		super.setLegendborderalpha(legendborderalpha);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getShowborder()
	 */
	@Override
	public int getShowborder() {
		// TODO Auto-generated method stub
		return super.getShowborder();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setShowborder(int)
	 */
	@Override
	public void setShowborder(int showborder) {
		// TODO Auto-generated method stub
		super.setShowborder(showborder);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getNumberPrefix()
	 */
	@Override
	public String getNumberPrefix() {
		// TODO Auto-generated method stub
		return super.getNumberPrefix();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setNumberPrefix(java.lang.String)
	 */
	@Override
	public void setNumberPrefix(String numberPrefix) {
		// TODO Auto-generated method stub
		super.setNumberPrefix(numberPrefix);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getBgColor()
	 */
	@Override
	public String getBgColor() {
		// TODO Auto-generated method stub
		return super.getBgColor();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setBgColor(java.lang.String)
	 */
	@Override
	public void setBgColor(String bgColor) {
		// TODO Auto-generated method stub
		super.setBgColor(bgColor);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getEncabezado()
	 */
	@Override
	public String getEncabezado() {
		// TODO Auto-generated method stub
		return super.getEncabezado();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setEncabezado(java.lang.String)
	 */
	@Override
	public void setEncabezado(String encabezado) {
		// TODO Auto-generated method stub
		super.setEncabezado(encabezado);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#isRefrescar()
	 */
	@Override
	public boolean isRefrescar() {
		// TODO Auto-generated method stub
		return super.isRefrescar();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setRefrescar(boolean)
	 */
	@Override
	public void setRefrescar(boolean refrescar) {
		// TODO Auto-generated method stub
		super.setRefrescar(refrescar);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getPlotgradientcolor()
	 */
	@Override
	public String getPlotgradientcolor() {
		// TODO Auto-generated method stub
		return super.getPlotgradientcolor();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setPlotgradientcolor(java.lang.String)
	 */
	@Override
	public void setPlotgradientcolor(String plotgradientcolor) {
		// TODO Auto-generated method stub
		super.setPlotgradientcolor(plotgradientcolor);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getShowalternatehgridcolor()
	 */
	@Override
	public String getShowalternatehgridcolor() {
		// TODO Auto-generated method stub
		return super.getShowalternatehgridcolor();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setShowalternatehgridcolor(java.lang.String)
	 */
	@Override
	public void setShowalternatehgridcolor(String showalternatehgridcolor) {
		// TODO Auto-generated method stub
		super.setShowalternatehgridcolor(showalternatehgridcolor);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getShowplotborder()
	 */
	@Override
	public String getShowplotborder() {
		// TODO Auto-generated method stub
		return super.getShowplotborder();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setShowplotborder(java.lang.String)
	 */
	@Override
	public void setShowplotborder(String showplotborder) {
		// TODO Auto-generated method stub
		super.setShowplotborder(showplotborder);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getLabeldisplay()
	 */
	@Override
	public String getLabeldisplay() {
		// TODO Auto-generated method stub
		return super.getLabeldisplay();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setLabeldisplay(java.lang.String)
	 */
	@Override
	public void setLabeldisplay(String labeldisplay) {
		// TODO Auto-generated method stub
		super.setLabeldisplay(labeldisplay);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getDivlinecolor()
	 */
	@Override
	public String getDivlinecolor() {
		// TODO Auto-generated method stub
		return super.getDivlinecolor();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setDivlinecolor(java.lang.String)
	 */
	@Override
	public void setDivlinecolor(String divlinecolor) {
		// TODO Auto-generated method stub
		super.setDivlinecolor(divlinecolor);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getShowcanvasborder()
	 */
	@Override
	public String getShowcanvasborder() {
		// TODO Auto-generated method stub
		return super.getShowcanvasborder();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setShowcanvasborder(java.lang.String)
	 */
	@Override
	public void setShowcanvasborder(String showcanvasborder) {
		// TODO Auto-generated method stub
		super.setShowcanvasborder(showcanvasborder);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getCanvasborderalpha()
	 */
	@Override
	public String getCanvasborderalpha() {
		// TODO Auto-generated method stub
		return super.getCanvasborderalpha();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setCanvasborderalpha(java.lang.String)
	 */
	@Override
	public void setCanvasborderalpha(String canvasborderalpha) {
		// TODO Auto-generated method stub
		super.setCanvasborderalpha(canvasborderalpha);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getLegendshadow()
	 */
	@Override
	public String getLegendshadow() {
		// TODO Auto-generated method stub
		return super.getLegendshadow();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setLegendshadow(java.lang.String)
	 */
	@Override
	public void setLegendshadow(String legendshadow) {
		// TODO Auto-generated method stub
		super.setLegendshadow(legendshadow);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getLinethickness()
	 */
	@Override
	public String getLinethickness() {
		// TODO Auto-generated method stub
		return super.getLinethickness();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setLinethickness(java.lang.String)
	 */
	@Override
	public void setLinethickness(String linethickness) {
		// TODO Auto-generated method stub
		super.setLinethickness(linethickness);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getCategorias()
	 */
	@Override
	public String getCategorias() {
		// TODO Auto-generated method stub
		return super.getCategorias();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getPeriodo()
	 */
	@Override
	public Tiempo getPeriodo() {
		// TODO Auto-generated method stub
		return super.getPeriodo();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setPeriodo(com.jayktec.controlador.Constantes.Tiempo)
	 */
	@Override
	public void setPeriodo(Tiempo periodos) {
		// TODO Auto-generated method stub
		super.setPeriodo(periodos);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getCampoCategoria()
	 */
	@Override
	public CampoRegistro getCampoCategoria() {
		// TODO Auto-generated method stub
		return super.getCampoCategoria();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setCampoCategoria(com.jayktec.controlador.Constantes.CampoRegistro)
	 */
	@Override
	public void setCampoCategoria(CampoRegistro campoCategoria) {
		// TODO Auto-generated method stub
		super.setCampoCategoria(campoCategoria);
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#getDataset()
	 */
	@Override
	public String getDataset() {
		// TODO Auto-generated method stub
		return super.getDataset();
	}

	/* (non-Javadoc)
	 * @see com.jayktec.traductor.ToJson#setDataset(java.lang.String)
	 */
	@Override
	public void setDataset(String dataSet) {
		// TODO Auto-generated method stub
		super.setDataset(dataSet);
	}

	
	
}
