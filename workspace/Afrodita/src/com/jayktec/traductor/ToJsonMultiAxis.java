/**
 * 
 */
package com.jayktec.traductor;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.google.gson.*;
import com.jayktec.controlador.*;
import com.jayktec.controlador.Constantes.*;
import com.jayktec.xyzOlympus.models.*;

/**
 * @author {Yisheng León Espinoza} 27 feb. 2018 www.jayktec.com.ve
 */
public class ToJsonMultiAxis extends ToJson {

	
	public static void main(String[] args) throws SQLException, JsonIOException, IOException {
		System.out.println("empezando json");

		ToJsonMultiAxis temp = new ToJsonMultiAxis("ad87651f614d9b3701614d9d69b50000", new Sensor("ad87651f619cd4430161dd9ec4590029"));

		System.out.println("finalizando json " + temp.crearJson());

	}
	public ToJsonMultiAxis(ArrayList<Registro> registros, String origen, Sensor sensor) throws SQLException {
		super(registros, origen, sensor);
		// TODO Auto-generated constructor stub
	}

	public ToJsonMultiAxis(String origen, Sensor sensor) throws SQLException {
		super(origen, sensor);
		// TODO Auto-generated constructor stub
	}


	public ToJsonMultiAxis(String origen, String sensor) throws SQLException {

		super(origen, sensor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void creaDataset() {
		setDataset("\"axis\": [ ");
		setDataset(getDataset()+"{  \"title\": \"Revenue\", "
					+ "\"titlepos\": \"left\","
					+ "\"tickwidth\": \"10\","
					+ "\"numberPrefix\": \"$\","
					+ "\"divlineisdashed\": \"1\","
					+ "\"dataset\": [");
		boolean primeraVez = true;
		
		for (Mapa mapaItem : mapa) {
			
			
			String serie = "";
			
		
			String bd = mapaItem.getMapabd().getNombre();
			if (!(bd.equals(campoCategoriaFecha.campoBD()) || bd.equals(campoCategoriaHora.campoBD()))) {
				if (!primeraVez)	
					serie=",";
				primeraVez=true;
				serie = serie+"{\"seriesname\":  \"" + mapaItem.getMapaapp() + "\", \"data\": [";
				for (Registro registro : listaRegistro) {
					if (!primeraVez)
						serie = serie + ",";
					serie = serie + " { \"value\":\"";

					if (bd.equals(Constantes.CampoRegistro.FLOAT1.campoBD())) {
						serie = serie + registro.getRegistroFloat1();
					} else if (bd.equals(Constantes.CampoRegistro.FLOAT2.campoBD())) {
						serie = serie + registro.getRegistroFloat2();
					} else if (bd.equals(Constantes.CampoRegistro.FLOAT3.campoBD())) {
						serie = serie + registro.getRegistroFloat3();
					} else if (bd.equals(Constantes.CampoRegistro.FLOAT4.campoBD())) {
						serie = serie + registro.getRegistroFloat4();
					} else if (bd.equals(Constantes.CampoRegistro.FLOAT5.campoBD())) {
						serie = serie + registro.getRegistroFloat5();
					} else if (bd.equals(Constantes.CampoRegistro.INT1.campoBD())) {
						serie = serie + registro.getRegistroInt1();
					} else if (bd.equals(Constantes.CampoRegistro.INT2.campoBD())) {
						serie = serie + registro.getRegistroInt2();
					} else if (bd.equals(Constantes.CampoRegistro.INT3.campoBD())) {
						serie = serie + registro.getRegistroInt3();
					} else if (bd.equals(Constantes.CampoRegistro.INT4.campoBD())) {
						serie = serie + registro.getRegistroInt4();
					} else if (bd.equals(Constantes.CampoRegistro.INT5.campoBD())) {
						serie = serie + registro.getRegistroInt5();
					}
					serie = serie +"\" }";
					primeraVez = false;
				}

				setDataset(getDataset()+ serie + "]}");
			}
		}
		setDataset(getDataset()+ "]}]}");
		System.out.println(getDataset());

	}

	@Override
	public void crearEncabezado() {
		// TODO Auto-generated method stub
		super.crearEncabezado();
	}

	@Override
	public void iniciarYaxisName() {
		// TODO Auto-generated method stub
		super.iniciarYaxisName();
	}

	@Override
	public void crearEncabezado(String yAxisName, String xAxisName) {
		// TODO Auto-generated method stub
		super.crearEncabezado(yAxisName, xAxisName);
	}

	@Override
	public void crearCategorias() {
		// TODO Auto-generated method stub
		super.crearCategorias();
	}

	@Override
	public void refrescarJson(Object object) throws SQLException, JsonIOException, IOException {
		// TODO Auto-generated method stub
		super.refrescarJson(object);
	}

	@Override
	public int crearJson() throws SQLException, JsonIOException, IOException {
		// TODO Auto-generated method stub
		return super.crearJson();
	}

	@Override
	public String armarJson() throws SQLException {
		// TODO Auto-generated method stub
		return super.armarJson();
	}

	@Override
	public void mapaParaSensor() throws SQLException {
		// TODO Auto-generated method stub
		super.mapaParaSensor();
	}

	@Override
	public void mapaParaSensor(Sensor sensor) throws SQLException {
		// TODO Auto-generated method stub
		super.mapaParaSensor(sensor);
	}

	@Override
	public void mapaParaSensor(Registro registro) throws SQLException {
		// TODO Auto-generated method stub
		super.mapaParaSensor(registro);
	}

	@Override
	public Sensor getSensor() {
		// TODO Auto-generated method stub
		return super.getSensor();
	}

	@Override
	public void setSensor(Sensor sensor) {
		// TODO Auto-generated method stub
		super.setSensor(sensor);
	}

	@Override
	public Registro getRegistro() {
		// TODO Auto-generated method stub
		return super.getRegistro();
	}

	@Override
	public void setRegistro(Registro registro) {
		// TODO Auto-generated method stub
		super.setRegistro(registro);
	}

	@Override
	public ArrayList<Registro> getListaRegistro() {
		// TODO Auto-generated method stub
		return super.getListaRegistro();
	}

	@Override
	public void setListaRegistro(ArrayList<Registro> listaRegistro) {
		// TODO Auto-generated method stub
		super.setListaRegistro(listaRegistro);
	}

	@Override
	public ArrayList<Mapa> getMapa() {
		// TODO Auto-generated method stub
		return super.getMapa();
	}

	@Override
	public void setMapa(ArrayList<Mapa> mapas) {
		// TODO Auto-generated method stub
		super.setMapa(mapas);
	}

	@Override
	public Origen getOrigen() {
		// TODO Auto-generated method stub
		return super.getOrigen();
	}

	@Override
	public void setOrigen(Origen origen) {
		// TODO Auto-generated method stub
		super.setOrigen(origen);
	}

	@Override
	public String getxName() {
		// TODO Auto-generated method stub
		return super.getxName();
	}

	@Override
	public void setxName(String xName) {
		// TODO Auto-generated method stub
		super.setxName(xName);
	}

	@Override
	public String getyName() {
		// TODO Auto-generated method stub
		return super.getyName();
	}

	@Override
	public void setyName(String yName) {
		// TODO Auto-generated method stub
		super.setyName(yName);
	}

	@Override
	public int getShowValues() {
		// TODO Auto-generated method stub
		return super.getShowValues();
	}

	@Override
	public void setShowValues(int showValues) {
		// TODO Auto-generated method stub
		super.setShowValues(showValues);
	}

	@Override
	public int getLegendborderalpha() {
		// TODO Auto-generated method stub
		return super.getLegendborderalpha();
	}

	@Override
	public void setLegendborderalpha(int legendborderalpha) {
		// TODO Auto-generated method stub
		super.setLegendborderalpha(legendborderalpha);
	}

	@Override
	public int getShowborder() {
		// TODO Auto-generated method stub
		return super.getShowborder();
	}

	@Override
	public void setShowborder(int showborder) {
		// TODO Auto-generated method stub
		super.setShowborder(showborder);
	}

	@Override
	public String getNumberPrefix() {
		// TODO Auto-generated method stub
		return super.getNumberPrefix();
	}

	@Override
	public void setNumberPrefix(String numberPrefix) {
		// TODO Auto-generated method stub
		super.setNumberPrefix(numberPrefix);
	}

	@Override
	public String getBgColor() {
		// TODO Auto-generated method stub
		return super.getBgColor();
	}

	@Override
	public void setBgColor(String bgColor) {
		// TODO Auto-generated method stub
		super.setBgColor(bgColor);
	}

	@Override
	public String getEncabezado() {
		// TODO Auto-generated method stub
		return super.getEncabezado();
	}

	@Override
	public void setEncabezado(String encabezado) {
		// TODO Auto-generated method stub
		super.setEncabezado(encabezado);
	}

	@Override
	public boolean isRefrescar() {
		// TODO Auto-generated method stub
		return super.isRefrescar();
	}

	@Override
	public void setRefrescar(boolean refrescar) {
		// TODO Auto-generated method stub
		super.setRefrescar(refrescar);
	}

	@Override
	public String getPlotgradientcolor() {
		// TODO Auto-generated method stub
		return super.getPlotgradientcolor();
	}

	@Override
	public void setPlotgradientcolor(String plotgradientcolor) {
		// TODO Auto-generated method stub
		super.setPlotgradientcolor(plotgradientcolor);
	}

	@Override
	public String getShowalternatehgridcolor() {
		// TODO Auto-generated method stub
		return super.getShowalternatehgridcolor();
	}

	@Override
	public void setShowalternatehgridcolor(String showalternatehgridcolor) {
		// TODO Auto-generated method stub
		super.setShowalternatehgridcolor(showalternatehgridcolor);
	}

	@Override
	public String getShowplotborder() {
		// TODO Auto-generated method stub
		return super.getShowplotborder();
	}

	@Override
	public void setShowplotborder(String showplotborder) {
		// TODO Auto-generated method stub
		super.setShowplotborder(showplotborder);
	}

	@Override
	public String getLabeldisplay() {
		// TODO Auto-generated method stub
		return super.getLabeldisplay();
	}

	@Override
	public void setLabeldisplay(String labeldisplay) {
		// TODO Auto-generated method stub
		super.setLabeldisplay(labeldisplay);
	}

	@Override
	public String getDivlinecolor() {
		// TODO Auto-generated method stub
		return super.getDivlinecolor();
	}

	@Override
	public void setDivlinecolor(String divlinecolor) {
		// TODO Auto-generated method stub
		super.setDivlinecolor(divlinecolor);
	}

	@Override
	public String getShowcanvasborder() {
		// TODO Auto-generated method stub
		return super.getShowcanvasborder();
	}

	@Override
	public void setShowcanvasborder(String showcanvasborder) {
		// TODO Auto-generated method stub
		super.setShowcanvasborder(showcanvasborder);
	}

	@Override
	public String getCanvasborderalpha() {
		// TODO Auto-generated method stub
		return super.getCanvasborderalpha();
	}

	@Override
	public void setCanvasborderalpha(String canvasborderalpha) {
		// TODO Auto-generated method stub
		super.setCanvasborderalpha(canvasborderalpha);
	}

	@Override
	public String getLegendshadow() {
		// TODO Auto-generated method stub
		return super.getLegendshadow();
	}

	@Override
	public void setLegendshadow(String legendshadow) {
		// TODO Auto-generated method stub
		super.setLegendshadow(legendshadow);
	}

	@Override
	public String getLinethickness() {
		// TODO Auto-generated method stub
		return super.getLinethickness();
	}

	@Override
	public void setLinethickness(String linethickness) {
		// TODO Auto-generated method stub
		super.setLinethickness(linethickness);
	}

	@Override
	public String getCategorias() {
		// TODO Auto-generated method stub
		return super.getCategorias();
	}

	@Override
	public Tiempo getPeriodo() {
		// TODO Auto-generated method stub
		return super.getPeriodo();
	}

	@Override
	public void setPeriodo(Tiempo periodos) {
		// TODO Auto-generated method stub
		super.setPeriodo(periodos);
	}

	@Override
	public CampoRegistro getCampoCategoria() {
		// TODO Auto-generated method stub
		return super.getCampoCategoria();
	}

	@Override
	public void setCampoCategoria(CampoRegistro campoCategoria) {
		// TODO Auto-generated method stub
		super.setCampoCategoria(campoCategoria);
	}

	@Override
	public String getDataset() {
		// TODO Auto-generated method stub
		return super.getDataset();
	}

	
	
	
}
