package com.jayktec.traductor;

import java.io.IOException;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

import org.hibernate.context.internal.*;

import com.google.gson.JsonIOException;
import com.jayktec.controlador.Constantes;
import com.jayktec.controlador.Constantes.*;
import com.jayktec.persistencia.*;
import com.jayktec.xyzOlympus.models.*;
import com.jayktec.xyzOlympus.transitorio.*;

public class ToJson {

	Sensor sensor;
	Registro registro;
	static ArrayList<Registro> listaRegistro;
	static ArrayList<Mapa> mapa;
	Origen origen;
	private String xName;
	private String yName;
	private int showValues = 0;
	private int legendborderalpha = 50;
	private int showborder = 0;
	private String numberPrefix = "S";
	private String bgColor = "FFFFFF,FFFFFF";
	protected String encabezado = "";
	private boolean refrescar;
	private String plotgradientcolor = " ";
	private String showalternatehgridcolor = "0";
	private String showplotborder = "0";
	private String labeldisplay = "WRAP";
	private String divlinecolor = "CCCCCC";
	private String showcanvasborder = "0";
	private String canvasborderalpha = "0";
	private String legendshadow = "0";
	private String linethickness = "3";
	private int valorPercentil = 95;

	/* amarrufo 20180307 Bangin */

	private String subCaption = "";
	private String theme = "fint";
	private String showhovereffect = "1";
	private String xAxisName = "";
	private String yAxisName = "";
	private String numbersuffix = "";
	private String drawCrossLine = "2";
	private String crossLineAlpha = "100";
	private String crossLineColor = "#876EA1"; // amarrufo se cambio el color
												// para homologar con el grafico
												// existente
	private String ajustDiv = "1";
	private String yAxisMaxvalue = "0";
	private String yAxisMinvalue = "10";
	private String numDivLines = "10";
	private String numVDivLines = "0";
	private String vDivLineColor = "0";
	private String VDivLineThickness = "0";// groso line vertical en px
	private String VDivLineAlpha = "50";// transparencia de la lineas verticales
										// 0 trasparente 100 opaco
	private String showAlternateVGridColor = "0";
	private String alternateVGridColor = "#00ffaa";
	private String alternateVGridAlpha = "0";
	private String drawAnchors = "0";
	private String logoURL = "";
	private String logoScale = "110";
	private String logoPosition = "TR";
	private String logoAlpha = "40";
	private String exportenabled = "1";
	private String exportatclient = "1";
	private String exporthandler = "http://export.api3.fusioncharts.com";
	private String html5exporthandler = "http://export.api3.fusioncharts.com";
	private ArrayList<Tendencia> listaTendencia;
	private ArrayList<MediaMovil> listaMedia;

	/* amarrufo 20180307 End */
	private static String dataset;
	private static String categorias;
	private static Tiempo periodo;
	private static Date fechaFinal = Calendar.getInstance().getTime();
	protected static CampoRegistro campoCategoriaFecha = Constantes.CampoRegistro.DATE1;
	public static CampoRegistro campoCategoriaHora = Constantes.CampoRegistro.HORA1;
	private static String preCategoria = "{ \"label\": ";

	private static String postCategoria = "}";// ", \"stepSkipped\": false,
	private static ArrayList<Umbral> listaUmbrales;
	private boolean habil = true;
	private boolean reducido = true;

	// \"appliedSmartLabel\": true
	// }";

	public static void main(String[] args) throws SQLException, JsonIOException, IOException {
		System.out.println("empezando json");

		ToJson temp = new ToJson("4028b8816206bd49016206be8a150000", new Sensor("4028b8816206bd49016206c440d50006"));
		System.out.println("finalizando json " + temp.crearJson());

	}

	public ToJson(ArrayList<Registro> registros, String origen, Sensor sensor) throws SQLException {
		this(origen, sensor);
		listaRegistro = registros;

	}

	public ToJson(String origen, String sensor, boolean habil) throws SQLException {
		this(origen, sensor);
		setHabil(habil);
	}

	public ToJson(String origen, String sensor, boolean habil, boolean reducido) throws SQLException {
		this(origen, sensor);
		setHabil(habil);
		setReducido(reducido);
	}

	public ToJson(String origen, String sensor) throws SQLException {
		this.origen = new Origen(origen);
		this.sensor = new Sensor(sensor);
		mapaParaSensor();
		refrescar = true;
	}

	public ToJson(String origen, Sensor sensor, boolean habil) throws SQLException {
		this(origen, sensor);
		setHabil(habil);
	}

	public ToJson(String origen, Sensor sensor) throws SQLException {
		this.origen = new Origen(origen);
		this.sensor = sensor;
		mapaParaSensor();
		refrescar = true;
	}

	public void crearEncabezado() {
		iniciarXaxisName();
		iniciarYaxisName();
		crearEncabezado(getxName(), getyName());
		System.out.println(encabezado);
	}

	/**
	 * @return
	 */
	private void iniciarXaxisName() {
		// TODO Auto-generated method stub

	}

	public void iniciarYaxisName() {
		for (Mapa temp : mapa) {
			// temp.getMapabd().getNombre().equals(arg0)
		}
	}

	public void crearEncabezado(String yAxisName, String xAxisName) {

		this.encabezado = " { \"chart\": {" + " \"caption\": " + " \"" + origen.getNombre() + "-" + sensor.getNombre()
				+ " \"" + "," + "\"xaxisname\":" + " \"" + this.getxAxisName() + "\"" + "," + "\"yaxisname\":" + " \""
				+ this.getyAxisName() + "\"" + "," + "\"showvalues\":" + " \"" + this.getShowValues() + "\"" + ","
				+ "\"numberprefix\":" + " \"" + this.getNumberPrefix() + "\"" + "," + "\"legendborderalpha\":" + " \""
				+ this.getLegendborderalpha() + "\"" + "," + "\"showborder\":" + " \"" + this.getShowborder() + "\""
				+ "," + "\"bgcolor\":" + " \"" + this.getBgColor() + "\"" + "," + "\"plotgradientcolor\":" + " \""
				+ this.getPlotgradientcolor() + "\"" + "," + "\"showalternatehgridcolor\":" + " \""
				+ this.getShowalternatehgridcolor() + "\"" + "," + "\"showplotborder\":" + " \""
				+ this.getShowplotborder() + "\"" + "," + "\"labeldisplay\":" + " \"" + this.getLabeldisplay() + "\""
				+ "," + "\"divlinecolor\":" + " \"" + this.getDivlinecolor() + "\"" + "," + "\"showcanvasborder\":"
				+ " \"" + this.getShowcanvasborder() + "\"" + "," + "\"canvasborderalpha\":" + " \""
				+ this.getCanvasborderalpha() + "\"" + "," + "\"legendshadow\":" + " \"" + this.getLegendshadow() + "\""
				+ "," + "\"theme\": \"" + this.getTheme() + "\"" + "," + "\"showhovereffect\": \""
				+ this.getShowhovereffect() + "\"" + "," + "\"numbersuffix\": \"" + this.getNumbersuffix() + "\"" + ","
				+ "\"drawCrossLine\": \"" + this.getDrawCrossLine() + "\"" + "," + "\"crossLineAlpha\": \""
				+ this.getCrossLineAlpha() + "\"" + "," + "\"crossLineColor\": \"" + this.getCrossLineColor() + "\""
				+ "," + "\"ajustDiv\": \"" + this.getAjustDiv() + "\"" + "," + "\"yAxisMaxvalue\": \""
				+ this.getyAxisMaxvalue() + "\"" + "," + "\"yAxisMinvalue\": \"" + this.getyAxisMinvalue() + "\"" + ","
				+ "\"numDivLines\": \"" + this.getNumDivLines() + "\"" + "," + "\"numVDivLines\": \""
				+ this.getNumVDivLines() + "\"" + "," + "\"vDivLineColor\": \"" + this.getvDivLineColor() + "\"" + ","
				+ "\"VDivLineThickness\": \"" + this.getVDivLineThickness() + "\"" + "," + "\"VDivLineAlpha\": \""
				+ this.getVDivLineAlpha() + "\"" + "," + "\"showAlternateVGridColor\": \""
				+ this.getShowAlternateVGridColor() + "\"" + "," + "\"alternateVGridColor\": \""
				+ this.getAlternateVGridColor() + "\"" + "," + "\"alternateVGridAlpha\": \""
				+ this.getAlternateVGridAlpha() + "\"" + "," + "\"drawAnchors\": \"" + this.getDrawAnchors() + "\""
				+ "," + "\"logoURL\": \"" + this.getLogoURL() + "\"" + "," + "\"logoScale\": \"" + this.getLogoScale()
				+ "\"" + "," + "\"logoPosition\": \"" + this.getLogoPosition() + "\"" + "," + "\"logoAlpha\": \""
				+ this.getLogoAlpha() + "\"" + "," + "\"exportenabled\": \"" + this.getExportenabled() + "\"" + ","
				+ "\"exportatclient\": \"" + this.getExportatclient() + "\"" + "," + "\"exporthandler\": \""
				+ this.getExporthandler() + "\"" + "," + "\"html5exporthandler\": \"" + this.getHtml5exporthandler()
				+ "\"},";

		System.out.println(encabezado);
	}

	public void crearCategorias()

	{
		setCategorias("");
		periodo = Constantes.Tiempo.SEMANAL;
		setFechaFinal(Calendar.getInstance().getTime());

		crearCategorias(periodo, getFechaFinal());

	}

	/**
	 * @param periodo
	 * @param fechaFinal
	 */
	private void crearCategorias(Tiempo periodo, Date fechaFinal) {
		// TODO Auto-generated method stub
		Date fechaInicio = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaFinal); // Configuramos la fecha que se recibe

		if (periodo.equals(Constantes.Tiempo.DIA)) {
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			fechaInicio = calendar.getTime();
		} else if (periodo.equals(Constantes.Tiempo.SEMANAL)) {
			calendar.add(Calendar.DAY_OF_YEAR, -7);
			fechaInicio = calendar.getTime();
		} else if (periodo.equals(Constantes.Tiempo.MES)) {
			calendar.add(Calendar.MONTH, -1);
			fechaInicio = calendar.getTime();
		} else if (periodo.equals(Constantes.Tiempo.ANUAL)) {
			calendar.add(Calendar.YEAR, -1);
			fechaInicio = calendar.getTime();
		}

		crearCategorias(fechaFinal, fechaInicio);
	}

	@SuppressWarnings("unchecked")
	public float percentil(int valor, Catalogo mapa) {
		if (valor < 100) {
			float resp = 0f;
			int count = ((int) Math.round(listaRegistro.size() * (valor * 0.01))) - 1;

			final String bdOrder = mapa.getNombre();

			ArrayList<Registro> temp = (ArrayList<Registro>) listaRegistro.clone();
			Comparator<Registro> comparador = new Comparator<Registro>() {
				@Override
				public int compare(Registro o1, Registro o2) {
					Registro r1 = (Registro) o1;
					Registro r2 = (Registro) o2;
					if (bdOrder.equals(Constantes.CampoRegistro.FLOAT1.campoBD()))
						return Float.compare(r1.getRegistroFloat1(), r2.getRegistroFloat1());
					if (bdOrder.equals(Constantes.CampoRegistro.FLOAT2.campoBD()))
						return Float.compare(r1.getRegistroFloat2(), r2.getRegistroFloat2());
					if (bdOrder.equals(Constantes.CampoRegistro.FLOAT3.campoBD()))
						return Float.compare(r1.getRegistroFloat3(), r2.getRegistroFloat3());
					if (bdOrder.equals(Constantes.CampoRegistro.FLOAT4.campoBD()))
						return Float.compare(r1.getRegistroFloat4(), r2.getRegistroFloat4());
					if (bdOrder.equals(Constantes.CampoRegistro.FLOAT5.campoBD()))
						return Float.compare(r1.getRegistroFloat5(), r2.getRegistroFloat5());
					if (bdOrder.equals(Constantes.CampoRegistro.INT1.campoBD()))
						return Float.compare(r1.getRegistroInt1(), r2.getRegistroInt1());
					if (bdOrder.equals(Constantes.CampoRegistro.INT2.campoBD()))
						return Float.compare(r1.getRegistroInt2(), r2.getRegistroInt2());
					if (bdOrder.equals(Constantes.CampoRegistro.INT3.campoBD()))
						return Float.compare(r1.getRegistroInt3(), r2.getRegistroInt3());
					if (bdOrder.equals(Constantes.CampoRegistro.INT4.campoBD()))
						return Float.compare(r1.getRegistroInt4(), r2.getRegistroInt4());
					if (bdOrder.equals(Constantes.CampoRegistro.INT5.campoBD()))
						return Float.compare(r1.getRegistroInt5(), r2.getRegistroInt5());

					return 0;
				}

			};
			Collections.sort(temp, comparador);

			if (bdOrder.equals(Constantes.CampoRegistro.FLOAT1.campoBD()))
				return temp.get(count).getRegistroFloat1();
			if (bdOrder.equals(Constantes.CampoRegistro.FLOAT2.campoBD()))
				return temp.get(count).getRegistroFloat2();
			if (bdOrder.equals(Constantes.CampoRegistro.FLOAT3.campoBD()))
				return temp.get(count).getRegistroFloat3();
			if (bdOrder.equals(Constantes.CampoRegistro.FLOAT4.campoBD()))
				return temp.get(count).getRegistroFloat4();
			if (bdOrder.equals(Constantes.CampoRegistro.FLOAT5.campoBD()))
				return temp.get(count).getRegistroFloat5();
			if (bdOrder.equals(Constantes.CampoRegistro.INT1.campoBD()))
				return temp.get(count).getRegistroInt1();
			if (bdOrder.equals(Constantes.CampoRegistro.INT2.campoBD()))
				return temp.get(count).getRegistroInt2();
			if (bdOrder.equals(Constantes.CampoRegistro.INT3.campoBD()))
				return temp.get(count).getRegistroInt3();
			if (bdOrder.equals(Constantes.CampoRegistro.INT4.campoBD()))
				return temp.get(count).getRegistroInt4();
			if (bdOrder.equals(Constantes.CampoRegistro.INT5.campoBD()))
				return temp.get(count).getRegistroInt5();
		}
		return 0;
	}

	/**
	 * @param periodo2
	 * @param fechaFinal2
	 * @param fechaInicio
	 */
	private void crearCategorias(Date fechaFinal2, Date fechaInicio) {
		// TODO Auto-generated method stub
		categorias = "\"categories\": [ {   \"category\": [";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		boolean primeraVez = true;
		for (Registro registro : listaRegistro) {

			if (!primeraVez) {
				categorias = categorias + ",";
			}
			categorias = categorias + preCategoria + "\"";

			if (campoCategoriaFecha.equals(Constantes.CampoRegistro.DATE1)) {
				categorias = categorias + registro.getRegistroDate1();
			} else if (campoCategoriaFecha.equals(Constantes.CampoRegistro.DATE2)) {
				categorias = categorias + registro.getRegistroDate2();
			} else if (campoCategoriaFecha.equals(Constantes.CampoRegistro.DATE3)) {
				categorias = categorias + registro.getRegistroDate3();
			} else if (campoCategoriaFecha.equals(Constantes.CampoRegistro.DATE4)) {
				categorias = categorias + registro.getRegistroDate4();
			} else if (campoCategoriaFecha.equals(Constantes.CampoRegistro.DATE5)) {
				categorias = categorias + registro.getRegistroDate5();
			}

			categorias = categorias + " ";

			if (campoCategoriaHora.equals(Constantes.CampoRegistro.HORA1)) {
				categorias = categorias + sdf.format(registro.getRegistrotime1());
			} else if (campoCategoriaHora.equals(Constantes.CampoRegistro.HORA2)) {
				categorias = categorias + sdf.format(registro.getRegistrotime2());
			} else if (campoCategoriaHora.equals(Constantes.CampoRegistro.HORA3)) {
				categorias = categorias + sdf.format(registro.getRegistrotime3());
			} else if (campoCategoriaHora.equals(Constantes.CampoRegistro.HORA4)) {
				categorias = categorias + sdf.format(registro.getRegistrotime4());
			} else if (campoCategoriaHora.equals(Constantes.CampoRegistro.HORA5)) {
				categorias = categorias + sdf.format(registro.getRegistrotime5());
			}
			categorias = categorias + "\"" + postCategoria;
			primeraVez = false;

		}
		categorias = categorias + "] } ],";
		System.out.println(categorias);
	}

	public void creaDataset() throws SQLException

	{
		creaDataset(valorPercentil);
	}

	public void creaDataset(int percentil) throws SQLException

	{
		setDataset("\"dataset\": [");
		boolean primeraVez = true;

		for (Mapa mapaItem : mapa) {

			String serie = "";
			int countLista = listaRegistro.size();
			String bd = mapaItem.getMapabd().getNombre();
			if (!(bd.equals(campoCategoriaFecha.campoBD()) || bd.equals(campoCategoriaHora.campoBD()))) {
				if (!primeraVez)
					serie = ",";
				primeraVez = true;
				serie = serie + "{\"seriesname\":  \"" + mapaItem.getMapaapp() + "\", \"data\": [";
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
					serie = serie + "\" }";

					primeraVez = false;
				}
				serie = serie + "]}" + crearSeriePercentil(countLista, percentil, mapaItem);
				serie = serie + crearSerieUmbral(mapaItem);
			//	serie = serie + crearSerieTendencia(mapaItem);
				serie = serie + crearSerieMediaMovil(mapaItem);

				setDataset(getDataset() + serie);

			}
		}
		setDataset(getDataset() + "]}");
		System.out.println(getDataset());

	}

	/**
	 * @param mapaItem
	 * @return
	 * @throws SQLException
	 */
	private String crearSerieTendencia(Mapa mapaItem) throws SQLException {
		// TODO Auto-generated method stubnd
		int countLista = listaRegistro.size();
		int countTendencia = listaTendencia.size();
		String serie = "";
		boolean pVez = true;
		boolean haytendencia = false;
		float tendencia = 0f;
		listaRegistro.get(0).getRegistroDate1();
		Calendar inicio = Calendar.getInstance();

		inicio.setTime(listaRegistro.get(0).getRegistroDate1());

		int dayOfWeek = diaMysql(inicio.get(Calendar.DAY_OF_WEEK));
		inicio.setTime(listaRegistro.get(0).getRegistrotime1());

		int hourOfWeek = inicio.get(Calendar.HOUR);

		Calendar fin = Calendar.getInstance();

		fin.setTime(listaRegistro.get(countLista - 1).getRegistroDate1());

		ArrayList<Tendencia> temp = (ArrayList<Tendencia>) listaTendencia.clone();
		ArrayList<Tendencia> tendenciaFinal = new ArrayList<Tendencia>();

		for (Tendencia tenTemp : temp) {
			if (haytendencia) {
				tendenciaFinal.add(tenTemp);
			} else

			if (tenTemp.getDia() == dayOfWeek && tenTemp.getHora() == hourOfWeek) {
				haytendencia = true;
				tendenciaFinal.add(tenTemp);
			}
		}
		int falta = countLista - tendenciaFinal.size();
		if (falta > 0) {
			int veces = falta / countTendencia;
			for (int i = 0; i < veces; i++) {
				tendenciaFinal.addAll(listaTendencia);
			}
			veces = falta % countTendencia;
			for (int i = 0; i < veces; i++) {
				tendenciaFinal.add(listaTendencia.get(i));
			}
		}

		if (haytendencia) {

			serie = serie + ",{\"seriesname\":  \"" + "Tendencia-" + mapaItem.getMapaapp() + "\", \"data\": [";

			for (Tendencia tempTendencia : tendenciaFinal) {

				if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.FLOAT1.campoBD()))
					tendencia = tempTendencia.getFloat1();
				else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.FLOAT2.campoBD()))
					tendencia = tempTendencia.getFloat2();
				else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.FLOAT3.campoBD()))
					tendencia = tempTendencia.getFloat3();
				else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.FLOAT4.campoBD()))
					tendencia = tempTendencia.getFloat4();
				else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.FLOAT5.campoBD()))
					tendencia = tempTendencia.getFloat5();
				else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.INT1.campoBD()))
					tendencia = tempTendencia.getInt1();
				else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.INT1.campoBD()))
					tendencia = tempTendencia.getInt2();
				else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.INT1.campoBD()))
					tendencia = tempTendencia.getInt3();
				else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.INT1.campoBD()))
					tendencia = tempTendencia.getInt4();
				else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.INT1.campoBD()))
					tendencia = tempTendencia.getInt5();

				if (!pVez) {
					serie = serie + ",";
				} else
					pVez = false;
				serie = serie + " { \"value\":\"" + tendencia + "\"}";

			}

			serie = serie + "]}";
		}
		System.out.println(serie);
		return serie;
	}

	/**
	 * @param mapaItem
	 * @return
	 * @throws SQLException
	 */
	private String crearSerieMediaMovil(Mapa mapaItem) throws SQLException {
		// TODO Auto-generated method stubnd
		boolean pVez = true;
		String serie = "";
		serie = serie + ",{\"seriesname\":  \"" + "MediaMovil-" + mapaItem.getMapaapp() + "\", \"data\": [";

		for (MediaMovil mediaMovil : listaMedia) {
			float tendencia = 0f;

			if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.FLOAT1.campoBD()))
				tendencia = mediaMovil.getFloat1();
			else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.FLOAT2.campoBD()))
				tendencia = mediaMovil.getFloat2();
			else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.FLOAT3.campoBD()))
				tendencia = mediaMovil.getFloat3();
			else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.FLOAT4.campoBD()))
				tendencia = mediaMovil.getFloat4();
			else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.FLOAT5.campoBD()))
				tendencia = mediaMovil.getFloat5();
			else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.INT1.campoBD()))
				tendencia = mediaMovil.getInt1();
			else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.INT1.campoBD()))
				tendencia = mediaMovil.getInt2();
			else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.INT1.campoBD()))
				tendencia = mediaMovil.getInt3();
			else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.INT1.campoBD()))
				tendencia = mediaMovil.getInt4();
			else if (mapaItem.getMapabd().getNombre().equals(Constantes.CampoRegistro.INT1.campoBD()))
				tendencia = mediaMovil.getInt5();

			if (!pVez) {
				serie = serie + ",";
			} else
				pVez = false;
			serie = serie + " { \"value\":\"" + tendencia + "\"}";

		}
		serie = serie + "]}";

		System.out.println(serie);
		return serie;
	}

	/**
	 * @param i
	 * @return
	 */
	private int diaMysql(int i) {
		// TODO Auto-generated method stub
		int respuesta = 0;
		respuesta = i - 2;
		if (respuesta == -1)
			respuesta = 0;

		return respuesta;
	}

	/**
	 * @param countLista
	 * @param mapaItem
	 * @return
	 */
	private String crearSeriePercentil(int countLista, int valorPercentil, Mapa mapaItem) {
		// TODO Auto-generated method stub

		String serie = "";
		boolean pVez = true;
		serie = serie + ",{\"seriesname\":  \"" + "Percentil-" + mapaItem.getMapaapp() + "\", \"data\": [";
		Float percentil = percentil(valorPercentil, mapaItem.getMapabd());
		for (int i = 0; i < countLista; i++) {

			if (!pVez) {
				serie = serie + ",";
			} else
				pVez = false;
			serie = serie + " { \"value\":\"" + percentil + "\"}";

		}
		serie = serie + "]}";
		return serie;
	}

	private String crearSerieUmbral(Mapa mapaItem) {
		// TODO Auto-generated method stub

		String serie = "";
		int countLista = listaRegistro.size();
		boolean pVez = true;
		if (existeUmbral(mapaItem)) {
			float umbral = umbral(mapaItem.getMapabd());

			serie = serie + ",{\"seriesname\":  \"" + "Umbral-" + mapaItem.getMapaapp() + "\", \"data\": [";
			for (int i = 0; i < countLista; i++) {
				if (pVez)
					pVez = false;
				else
					serie = serie + ",";

				serie = serie + " { \"value\":\"" + umbral + "\"}";

			}
			serie = serie + "]}";
		}
		return serie;
	}

	/**
	 * @param mapaItem
	 * @return
	 */
	private boolean existeUmbral(Mapa mapaItem) {
		// TODO Auto-generated method stub

		for (Umbral umbral : listaUmbrales) {
			if (umbral.getMapa().equals(mapaItem))
				return true;
		}
		return false;
	}

	/**
	 * @param mapabd
	 * @return
	 */
	private Float umbral(Catalogo mapabd) {
		for (Umbral umbral : listaUmbrales) {
			if (umbral.getMapa().getMapabd().equals(mapabd))
				return (umbral.getUmbralValor());
		}
		return 0f;
	}

	public void refrescarJson(Object object) throws SQLException, JsonIOException, IOException {

		if (origen != null) {
			refrescar = true;

			if (object instanceof Sensor) {
				Sensor sensor = (Sensor) object;
				mapaParaSensor(sensor);
				crearJson();

			}
			if (object instanceof Registro) {
				Registro registro = (Registro) object;
				mapaParaSensor(registro);
				crearJson();

			}
		}
	}

	/**
	 * crea Json
	 * 
	 * @return el id guardado en la base de datos en la tabla fateon_Json
	 * @throws SQLException
	 * @throws JsonIOException
	 * @throws IOException
	 */
	public int crearJson() throws SQLException, JsonIOException, IOException {
		// TODO Auto-generated method stub
		if (refrescar) {
			mapaParaSensor();
			buscarRegistros();
			buscarUmbrales();
			buscarTendencia();
			buscarMediaMovil();
		}

		;
		return BdManager.saveJson(armarJson());

	}

	/**
	 * @throws SQLException
	 * 
	 */
	private void buscarMediaMovil() throws SQLException {
		listaMedia = null;
		if (reducido)
			listaMedia = BdManager.buscarMediaMovil(sensor, origen, habil,reducido);

		else
		listaMedia = BdManager.buscarMediaMovil(sensor, origen, habil);

	}

	/**
	 * @throws SQLException
	 * 
	 */
	private void buscarTendencia() throws SQLException {
		listaTendencia = null;
		listaTendencia = BdManager.buscarTendencia(sensor, origen);

	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public String armarJson() throws SQLException {
		crearEncabezado();
		crearCategorias();
		creaDataset();
		return encabezado + categorias + dataset;
	}

	private void buscarRegistros() throws SQLException {
		// TODO Auto-generated method stub
		ToJson.listaRegistro = null;
		if (reducido)
			ToJson.listaRegistro = BdManager.consultarRegistro(origen, sensor, habil, TablaBD.REGISTRO_REDUCIDO);

		else
			ToJson.listaRegistro = BdManager.consultarRegistro(origen, sensor, habil);

	}

	private void buscarUmbrales() throws SQLException {
		// TODO Auto-generated method stub
		ToJson.setListaUmbrales(null);
		ToJson.setListaUmbrales(BdManager.consultarUmbral(sensor, origen));

	}

	public void mapaParaSensor() throws SQLException {
		mapa = BdManager.consultarMapa(sensor);

	}

	public void mapaParaSensor(Sensor sensor) throws SQLException, NullPointerException {
		mapa = BdManager.consultarMapa(sensor);

	}

	public void mapaParaSensor(Registro registro) throws SQLException {
		mapa = BdManager.consultarMapa(registro);

	}

	/**
	 * @return the sensor
	 */
	public Sensor getSensor() {
		return sensor;
	}

	/**
	 * @param sensor
	 *            the sensor to set
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	/**
	 * @return the registro
	 */
	public Registro getRegistro() {
		return registro;
	}

	/**
	 * @param registro
	 *            the registro to set
	 */
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	/**
	 * @return the listaRegistro
	 */
	public ArrayList<Registro> getListaRegistro() {
		return listaRegistro;
	}

	/**
	 * @param listaRegistro
	 *            the listaRegistro to set
	 */
	public void setListaRegistro(ArrayList<Registro> listaRegistro) {
		ToJson.listaRegistro = listaRegistro;
	}

	/**
	 * @return the mapa
	 */
	public ArrayList<Mapa> getMapa() {
		return mapa;
	}

	/**
	 * @param mapa
	 *            the mapa to set
	 */
	public void setMapa(ArrayList<Mapa> mapas) {
		mapa = mapas;
	}

	/**
	 * @return the origen
	 */
	public Origen getOrigen() {
		return origen;
	}

	/**
	 * @param origen
	 *            the origen to set
	 */
	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	/**
	 * @return the xName
	 */
	public String getxName() {
		return xName;
	}

	/**
	 * @param xName
	 *            the xName to set
	 */
	public void setxName(String xName) {
		this.xName = xName;
	}

	/**
	 * @return the yName
	 */
	public String getyName() {
		return yName;
	}

	/**
	 * @param yName
	 *            the yName to set
	 */
	public void setyName(String yName) {
		this.yName = yName;
	}

	/**
	 * @return the showValues
	 */
	public int getShowValues() {
		return showValues;
	}

	/**
	 * @param showValues
	 *            the showValues to set
	 */
	public void setShowValues(int showValues) {
		this.showValues = showValues;
	}

	/**
	 * @return the legendborderalpha
	 */
	public int getLegendborderalpha() {
		return legendborderalpha;
	}

	/**
	 * @param legendborderalpha
	 *            the legendborderalpha to set
	 */
	public void setLegendborderalpha(int legendborderalpha) {
		this.legendborderalpha = legendborderalpha;
	}

	/**
	 * @return the showborder
	 */
	public int getShowborder() {
		return showborder;
	}

	/**
	 * @param showborder
	 *            the showborder to set
	 */
	public void setShowborder(int showborder) {
		this.showborder = showborder;
	}

	/**
	 * @return the numberPrefix
	 */
	public String getNumberPrefix() {
		return numberPrefix;
	}

	/**
	 * @param numberPrefix
	 *            the numberPrefix to set
	 */
	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}

	/**
	 * @return the bgColor
	 */
	public String getBgColor() {
		return bgColor;
	}

	/**
	 * @param bgColor
	 *            the bgColor to set
	 */
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * @return the encabezado
	 */
	public String getEncabezado() {
		return encabezado;
	}

	/**
	 * @param encabezado
	 *            the encabezado to set
	 */
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	/**
	 * @return the refrescar
	 */
	public boolean isRefrescar() {
		return refrescar;
	}

	/**
	 * @param refrescar
	 *            the refrescar to set
	 */
	public void setRefrescar(boolean refrescar) {
		this.refrescar = refrescar;
	}

	/**
	 * @return the plotgradientcolor
	 */
	public String getPlotgradientcolor() {
		return plotgradientcolor;
	}

	/**
	 * @param plotgradientcolor
	 *            the plotgradientcolor to set
	 */
	public void setPlotgradientcolor(String plotgradientcolor) {
		this.plotgradientcolor = plotgradientcolor;
	}

	/**
	 * @return the showalternatehgridcolor
	 */
	public String getShowalternatehgridcolor() {
		return showalternatehgridcolor;
	}

	/**
	 * @param showalternatehgridcolor
	 *            the showalternatehgridcolor to set
	 */
	public void setShowalternatehgridcolor(String showalternatehgridcolor) {
		this.showalternatehgridcolor = showalternatehgridcolor;
	}

	/**
	 * @return the showplotborder
	 */
	public String getShowplotborder() {
		return showplotborder;
	}

	/**
	 * @param showplotborder
	 *            the showplotborder to set
	 */
	public void setShowplotborder(String showplotborder) {
		this.showplotborder = showplotborder;
	}

	/**
	 * @return the labeldisplay
	 */
	public String getLabeldisplay() {
		return labeldisplay;
	}

	/**
	 * @param labeldisplay
	 *            the labeldisplay to set
	 */
	public void setLabeldisplay(String labeldisplay) {
		this.labeldisplay = labeldisplay;
	}

	/**
	 * @return the divlinecolor
	 */
	public String getDivlinecolor() {
		return divlinecolor;
	}

	/**
	 * @param divlinecolor
	 *            the divlinecolor to set
	 */
	public void setDivlinecolor(String divlinecolor) {
		this.divlinecolor = divlinecolor;
	}

	/**
	 * @return the showcanvasborder
	 */
	public String getShowcanvasborder() {
		return showcanvasborder;
	}

	/**
	 * @param showcanvasborder
	 *            the showcanvasborder to set
	 */
	public void setShowcanvasborder(String showcanvasborder) {
		this.showcanvasborder = showcanvasborder;
	}

	/**
	 * @return the canvasborderalpha
	 */
	public String getCanvasborderalpha() {
		return canvasborderalpha;
	}

	/**
	 * @param canvasborderalpha
	 *            the canvasborderalpha to set
	 */
	public void setCanvasborderalpha(String canvasborderalpha) {
		this.canvasborderalpha = canvasborderalpha;
	}

	/**
	 * @return the legendshadow
	 */
	public String getLegendshadow() {
		return legendshadow;
	}

	/**
	 * @param legendshadow
	 *            the legendshadow to set
	 */
	public void setLegendshadow(String legendshadow) {
		this.legendshadow = legendshadow;
	}

	/**
	 * @return the linethickness
	 */
	public String getLinethickness() {
		return linethickness;
	}

	/**
	 * @param linethickness
	 *            the linethickness to set
	 */
	public void setLinethickness(String linethickness) {
		this.linethickness = linethickness;
	}

	/**
	 * @return the categorias
	 */
	public String getCategorias() {
		return categorias;
	}

	/**
	 * @param categorias
	 *            the categorias to set
	 */
	public static void setCategorias(String categoria) {
		categorias = categoria;
	}

	/**
	 * @return the periodo
	 */
	public Tiempo getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo
	 *            the periodo to set
	 */
	public void setPeriodo(Tiempo periodos) {
		periodo = periodos;
	}

	/**
	 * @return the fechaFinal
	 */
	public static Date getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal
	 *            the fechaFinal to set
	 */
	public static void setFechaFinal(Date fechaFina) {
		fechaFinal = fechaFina;
	}

	/**
	 * @return the campoCategoriaFecha
	 */
	public CampoRegistro getCampoCategoria() {
		return campoCategoriaFecha;
	}

	/**
	 * @param campoCategoriaFecha
	 *            the campoCategoriaFecha to set
	 */
	public void setCampoCategoria(CampoRegistro campoCategoria) {
		ToJson.campoCategoriaFecha = campoCategoria;
	}

	/**
	 * @return the dataset
	 */
	public String getDataset() {
		return dataset;
	}

	/**
	 * @param dataset
	 *            the dataset to set
	 */
	public void setDataset(String dataSet) {
		dataset = dataSet;
	}

	/**
	 * @return the valorPercentil
	 */
	public int getValorPercentil() {
		return valorPercentil;
	}

	/**
	 * @param valorPercentil
	 *            the valorPercentil to set
	 */
	public void setValorPercentil(int valorPercentil) {
		this.valorPercentil = valorPercentil;
	}

	/**
	 * @return the campoCategoriaFecha
	 */
	public String getSubCaption() {
		return subCaption;
	}

	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getShowhovereffect() {
		return showhovereffect;
	}

	public void setShowhovereffect(String showhovereffect) {
		this.showhovereffect = showhovereffect;
	}

	public String getxAxisName() {
		return xAxisName;
	}

	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}

	public String getNumbersuffix() {
		return numbersuffix;
	}

	public void setNumbersuffix(String numbersuffix) {
		this.numbersuffix = numbersuffix;
	}

	public String getDrawCrossLine() {
		return drawCrossLine;
	}

	public void setDrawCrossLine(String drawCrossLine) {
		this.drawCrossLine = drawCrossLine;
	}

	public String getCrossLineAlpha() {
		return crossLineAlpha;
	}

	public void setCrossLineAlpha(String crossLineAlpha) {
		this.crossLineAlpha = crossLineAlpha;
	}

	public String getCrossLineColor() {
		return crossLineColor;
	}

	public void setCrossLineColor(String crossLineColor) {
		this.crossLineColor = crossLineColor;
	}

	public String getAjustDiv() {
		return ajustDiv;
	}

	public void setAjustDiv(String ajustDiv) {
		this.ajustDiv = ajustDiv;
	}

	public String getyAxisMaxvalue() {
		return yAxisMaxvalue;
	}

	public void setyAxisMaxvalue(String yAxisMaxvalue) {
		this.yAxisMaxvalue = yAxisMaxvalue;
	}

	public String getyAxisMinvalue() {
		return yAxisMinvalue;
	}

	public void setyAxisMinvalue(String yAxisMinvalue) {
		this.yAxisMinvalue = yAxisMinvalue;
	}

	public String getNumDivLines() {
		return numDivLines;
	}

	public void setNumDivLines(String numDivLines) {
		this.numDivLines = numDivLines;
	}

	public String getNumVDivLines() {
		return numVDivLines;
	}

	public void setNumVDivLines(String numVDivLines) {
		this.numVDivLines = numVDivLines;
	}

	public String getvDivLineColor() {
		return vDivLineColor;
	}

	public void setvDivLineColor(String vDivLineColor) {
		this.vDivLineColor = vDivLineColor;
	}

	public String getVDivLineThickness() {
		return VDivLineThickness;
	}

	public void setVDivLineThickness(String vDivLineThickness) {
		VDivLineThickness = vDivLineThickness;
	}

	public String getVDivLineAlpha() {
		return VDivLineAlpha;
	}

	public void setVDivLineAlpha(String vDivLineAlpha) {
		VDivLineAlpha = vDivLineAlpha;
	}

	public String getShowAlternateVGridColor() {
		return showAlternateVGridColor;
	}

	public void setShowAlternateVGridColor(String showAlternateVGridColor) {
		this.showAlternateVGridColor = showAlternateVGridColor;
	}

	public String getAlternateVGridColor() {
		return alternateVGridColor;
	}

	public void setAlternateVGridColor(String alternateVGridColor) {
		this.alternateVGridColor = alternateVGridColor;
	}

	public String getAlternateVGridAlpha() {
		return alternateVGridAlpha;
	}

	public void setAlternateVGridAlpha(String alternateVGridAlpha) {
		this.alternateVGridAlpha = alternateVGridAlpha;
	}

	public String getDrawAnchors() {
		return drawAnchors;
	}

	public void setDrawAnchors(String drawAnchors) {
		this.drawAnchors = drawAnchors;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public String getLogoScale() {
		return logoScale;
	}

	public void setLogoScale(String logoScale) {
		this.logoScale = logoScale;
	}

	public String getLogoPosition() {
		return logoPosition;
	}

	public void setLogoPosition(String logoPosition) {
		this.logoPosition = logoPosition;
	}

	public String getLogoAlpha() {
		return logoAlpha;
	}

	public void setLogoAlpha(String logoAlpha) {
		this.logoAlpha = logoAlpha;
	}

	public String getExportenabled() {
		return exportenabled;
	}

	public void setExportenabled(String exportenabled) {
		this.exportenabled = exportenabled;
	}

	public String getExportatclient() {
		return exportatclient;
	}

	public void setExportatclient(String exportatclient) {
		this.exportatclient = exportatclient;
	}

	public String getExporthandler() {
		return exporthandler;
	}

	public void setExporthandler(String exporthandler) {
		this.exporthandler = exporthandler;
	}

	public String getHtml5exporthandler() {
		return html5exporthandler;
	}

	public void setHtml5exporthandler(String html5exporthandler) {
		this.html5exporthandler = html5exporthandler;
	}

	public static CampoRegistro getCampoCategoriaFecha() {
		return campoCategoriaFecha;
	}

	/**
	 * @param campoCategoriaFecha
	 *            the campoCategoriaFecha to set
	 */
	public static void setCampoCategoriaFecha(CampoRegistro campoCategoriaFecha) {
		ToJson.campoCategoriaFecha = campoCategoriaFecha;
	}

	/**
	 * @return the campoCategoriaHora
	 */
	public static CampoRegistro getCampoCategoriaHora() {
		return campoCategoriaHora;
	}

	/**
	 * @param campoCategoriaHora
	 *            the campoCategoriaHora to set
	 */
	public static void setCampoCategoriaHora(CampoRegistro campoCategoriaHora) {
		ToJson.campoCategoriaHora = campoCategoriaHora;
	}

	/**
	 * @return the preCategoria
	 */
	public static String getPreCategoria() {
		return preCategoria;
	}

	/**
	 * @param preCategoria
	 *            the preCategoria to set
	 */
	public static void setPreCategoria(String preCategoria) {
		ToJson.preCategoria = preCategoria;
	}

	/**
	 * @return the postCategoria
	 */
	public static String getPostCategoria() {
		return postCategoria;
	}

	/**
	 * @param postCategoria
	 *            the postCategoria to set
	 */
	public static void setPostCategoria(String postCategoria) {
		ToJson.postCategoria = postCategoria;
	}

	public String getyAxisName() {
		return yAxisName;
	}

	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}

	/**
	 * @return the listaUmbrales
	 */
	public static ArrayList<Umbral> getListaUmbrales() {
		return listaUmbrales;
	}

	/**
	 * @param listaUmbrales
	 *            the listaUmbrales to set
	 */
	public static void setListaUmbrales(ArrayList<Umbral> listaUmbrales) {
		ToJson.listaUmbrales = listaUmbrales;
	}

	/**
	 * @return the listaTendencia
	 */
	public ArrayList<Tendencia> getListaTendencia() {
		return listaTendencia;
	}

	/**
	 * @param listaTendencia
	 *            the listaTendencia to set
	 */
	public void setListaTendencia(ArrayList<Tendencia> listaTendencia) {
		this.listaTendencia = listaTendencia;
	}

	/**
	 * @return the habil
	 */
	public boolean isHabil() {
		return habil;
	}

	/**
	 * @param habil
	 *            the habil to set
	 */
	public void setHabil(boolean habil) {
		this.habil = habil;
	}

	/**
	 * @return the listaMedia
	 */
	public ArrayList<MediaMovil> getListaMedia() {
		return listaMedia;
	}

	/**
	 * @param listaMedia
	 *            the listaMedia to set
	 */
	public void setListaMedia(ArrayList<MediaMovil> listaMedia) {
		this.listaMedia = listaMedia;
	}

	/**
	 * @return the reducido
	 */
	public boolean isReducido() {
		return reducido;
	}

	/**
	 * @param reducido
	 *            the reducido to set
	 */
	public void setReducido(boolean reducido) {
		this.reducido = reducido;
	}

}
