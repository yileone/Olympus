package com.jayktec.persistencia;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import java.util.logging.*;

import com.jayktec.controlador.*;
import com.jayktec.controlador.Constantes.*;
import com.jayktec.controller.*;
import com.jayktec.xyzOlympus.models.*;
import com.jayktec.xyzOlympus.transitorio.*;

public class BdManager {
	static Conexion conexion = new Conexion();
	static Connection connection = conexion.getConexion();
	static ArrayList<Catalogo> listaCatalogos;
	static ArrayList<Tabla> listaTablas;

	public static void main(String[] args) throws SQLException {

		System.out.println("prueba bdmanager");
		Sensor sensor = buscarSensor("4028b8816153e69d016153eb61680005");

		BdManager.consultarRegistro(new Origen("ad87651f614d9b3701614d9d69b50000"), sensor, false);

	}

	public static ArrayList<Registro> consultarRegistro(String origen, Sensor sensor) throws SQLException {

		return consultarRegistro(new Origen(origen), sensor, false);

	}

	public static ArrayList<Registro> consultarRegistro(Origen origen, Sensor sensor, Boolean habil)
			throws SQLException {
		return consultarRegistro(origen, sensor, habil, Constantes.TablaBD.REGISTRO);

	}

	public static ArrayList<Registro> consultarRegistro(Origen origen, Sensor sensor, Boolean habil, TablaBD tabla)
			throws SQLException {

		String sql = "select * from  " + Constantes.BD + tabla.tabla() + " where sensor_id='" + sensor.getOid()
				+ "' and origen_id='" + origen.getOid() + "' ";

		if (habil) {
			Date horaApertura = new Date(origen.getHoraApertura().getTime());

			Date horaCierre = new Date(origen.getHoraCierre().getTime());
			DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
			sql = sql + " and " + Constantes.CampoRegistro.HORA1.campoBD() + " >= '" + hourFormat.format(horaApertura)
					+ "' and " + Constantes.CampoRegistro.HORA1.campoBD() + " <= '" + hourFormat.format(horaCierre)
					+ "'";
		}
		sql = sql + " order by " + Constantes.CampoRegistro.DATE1.campoBD() + ","
				+ Constantes.CampoRegistro.HORA1.campoBD();
		System.out.println(sql);
		return consultarRegistro(sql, sensor, origen);

	}

	public static ArrayList<Registro> consultarRegistro() throws SQLException {

		return consultarRegistro(Constantes.TablaBD.REGISTRO);

	}

	public static ArrayList<Registro> consultarRegistro(TablaBD tabla) throws SQLException {

		String sql = "select * from  " + Constantes.BD + tabla.tabla() + "  order by "
				+ Constantes.CampoRegistro.SENSOR.campoBD() + "," + Constantes.CampoRegistro.ORIGEN.campoBD() + ","
				+ Constantes.CampoRegistro.DATE1.campoBD() + "," + Constantes.CampoRegistro.HORA1.campoBD();
		System.out.println(sql);
		return consultarRegistro(sql);

	}

	public int save(Object object) throws SQLException {
		if (object instanceof Registro) {
			salvarRegistro((Registro) object);
		}
		return -1;
	}

	public int delete(Object object) throws SQLException {
		if (object instanceof Registro) {
			borrarRegistro((Registro) object);
		}

		return -1;
	}

	public static ArrayList<Mapa> consultarMapa(Registro registro) throws SQLException {

		String sql = "select a.* from  " + Constantes.BD + ".fateon_mapa a" + " fateon_sensor b  where  "
				+ "a.mapa_id= b.sensor_mapa and" + "a.mapa_id ='" + registro.getSensor().getMapa().getOid() + "'";
		System.out.println(sql);
		return consultarMapa(sql);
		// TODO Auto-generated method stub

	}

	public static ArrayList<Mapa> consultarMapa(Sensor sensor) throws SQLException {

		String sql = "select a.* from  " + Constantes.BD + ".fateon_mapa a where  " + "a.mapa_tabla =\""
				+ sensor.getMapa().getOid() + "\"";
		System.out.println(sql);
		return consultarMapa(sql);
		// TODO Auto-generated method stub

	}

	public static ArrayList<Mapa> consultarMapa(String sql) throws SQLException {

		PreparedStatement pst = connection.prepareStatement(sql);

		ResultSet rs = consultarSql(pst);

		ArrayList<Mapa> respuesta = new ArrayList<Mapa>();
		while (rs.next()) {
			Mapa temp = new Mapa();
			temp.setOid(rs.getString("mapa_id"));
			temp.setMapabd(buscarEnListaCatalogo(rs.getString("mapa_campo")));
			temp.setMapaTabla(buscarEnListaCatalogo(rs.getString("mapa_tabla")));
			temp.setMapaapp(rs.getString("mapa_nombre_campo"));
			respuesta.add(temp);

		}

		return respuesta;

	}

	/**
	 * @param id
	 * @return
	 */
	private static Catalogo buscarEnListaCatalogo(String id) {
		// TODO Auto-generated method stub
		if (listaCatalogos == null) {
			buscarCatalogos();
		}

		for (Catalogo catalogo : listaCatalogos) {
			if (catalogo.getOid().equals(id))
				return catalogo;

		}
		return null;
	}

	public static ArrayList<Registro> consultarRegistro(String sql) throws SQLException {

		// PreparedStatement pst = connection.prepareStatement(sql);
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		Sensor sensor = new Sensor();
		Origen origen = new Origen();
		ArrayList<Registro> respuesta = new ArrayList<Registro>();
		while (rs.next()) {

			Registro temp = new Registro();
			temp.setRegistroDate1(rs.getDate(Constantes.CampoRegistro.DATE1.campoBD()));
			temp.setRegistroDate2(rs.getDate(Constantes.CampoRegistro.DATE2.campoBD()));
			temp.setRegistroDate3(rs.getDate(Constantes.CampoRegistro.DATE3.campoBD()));
			temp.setRegistroDate4(rs.getDate(Constantes.CampoRegistro.DATE4.campoBD()));
			temp.setRegistroDate5(rs.getDate(Constantes.CampoRegistro.DATE5.campoBD()));
			temp.setRegistroFloat1(rs.getFloat(Constantes.CampoRegistro.FLOAT1.campoBD()));
			temp.setRegistroFloat2(rs.getFloat(Constantes.CampoRegistro.FLOAT2.campoBD()));
			temp.setRegistroFloat3(rs.getFloat(Constantes.CampoRegistro.FLOAT3.campoBD()));
			temp.setRegistroFloat4(rs.getFloat(Constantes.CampoRegistro.FLOAT4.campoBD()));
			temp.setRegistroFloat5(rs.getFloat(Constantes.CampoRegistro.FLOAT5.campoBD()));
			temp.setRegistroInt1(rs.getInt(Constantes.CampoRegistro.INT1.campoBD()));
			temp.setRegistroInt2(rs.getInt(Constantes.CampoRegistro.INT2.campoBD()));
			temp.setRegistroInt3(rs.getInt(Constantes.CampoRegistro.INT3.campoBD()));
			temp.setRegistroInt4(rs.getInt(Constantes.CampoRegistro.INT4.campoBD()));
			temp.setRegistroInt5(rs.getInt(Constantes.CampoRegistro.INT5.campoBD()));
			temp.setRegistroVarchar1(rs.getString(Constantes.CampoRegistro.VARCHAR1.campoBD()));
			temp.setRegistroVarchar2(rs.getString(Constantes.CampoRegistro.VARCHAR2.campoBD()));
			temp.setRegistroVarchar3(rs.getString(Constantes.CampoRegistro.VARCHAR3.campoBD()));
			temp.setRegistroVarchar4(rs.getString(Constantes.CampoRegistro.VARCHAR4.campoBD()));
			temp.setRegistroVarchar5(rs.getString(Constantes.CampoRegistro.VARCHAR5.campoBD()));
			try {
				temp.setRegistrotime1(new Time(rs.getTimestamp(Constantes.CampoRegistro.HORA1.campoBD()).getTime()));
			} catch (Exception e) {
			}
			try {
				temp.setRegistrotime2(new Time(rs.getTimestamp(Constantes.CampoRegistro.HORA2.campoBD()).getTime()));
			} catch (Exception e) {
			}
			try {
				temp.setRegistrotime3(new Time(rs.getTimestamp(Constantes.CampoRegistro.HORA3.campoBD()).getTime()));
			} catch (Exception e) {
			}
			try {
				temp.setRegistrotime4(new Time(rs.getTimestamp(Constantes.CampoRegistro.HORA4.campoBD()).getTime()));
			} catch (Exception e) {
			}
			try {
				temp.setRegistrotime5(new Time(rs.getTimestamp(Constantes.CampoRegistro.HORA5.campoBD()).getTime()));
			} catch (Exception e) {
			}

			try {
				if (!sensor.getOid().equals(rs.getString(Constantes.CampoRegistro.SENSOR.campoBD()))) {

				}
			} catch (Exception e) {
				sensor = new Sensor(rs.getString(Constantes.CampoRegistro.SENSOR.campoBD()));

			}
			try {

				if (!origen.getOid().equals(rs.getString(Constantes.CampoRegistro.ORIGEN.campoBD()))) {
					origen = new Origen(rs.getString(Constantes.CampoRegistro.ORIGEN.campoBD()));

				}
			} catch (Exception e) {
				origen = new Origen(rs.getString(Constantes.CampoRegistro.ORIGEN.campoBD()));
			}
			temp.setSensor(sensor);
			temp.setOrigen(origen);
			temp.setOid(rs.getString(Constantes.CampoRegistro.ID_.campoBD()));
			temp.setRegistroFecha(rs.getString(Constantes.CampoRegistro.FECHA_REGISTRO.campoBD()));
			respuesta.add(temp);

		}

		return respuesta;

	}

	public static ArrayList<Umbral> consultarUmbral(Sensor sensor, Origen origen) throws SQLException {
		return consultarUmbral(sensor, origen, consultarMapa(sensor));
	}

	public static ArrayList<Umbral> consultarUmbral(Sensor sensor, Origen origen, ArrayList<Mapa> mapas)
			throws SQLException {

		connection.createStatement();
		String sql = "select * from " + Constantes.BD + ".fateon_umbral where sensor_id='" + sensor.getOid()
				+ "' and origen_id ='" + origen.getOid() + "'";
		System.out.println(sql);
		// ResultSet rs = stmt.executeQuery(sql);
		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = consultarSql(pst);

		ArrayList<Umbral> respuesta = new ArrayList<Umbral>();
		while (rs.next()) {

			try {
				Umbral temp = new Umbral();
				temp.setOrigen(origen);
				temp.setSensor(sensor);
				temp.setMapa(buscarMapaLista(rs.getString("mapa_id"), mapas));
				temp.setOid(rs.getString("umbral_id"));
				temp.setUmbralValor(rs.getFloat("umbral"));

				respuesta.add(temp);
			} catch (Exception e) {
			}
		}

		return respuesta;

	}

	/**
	 * @param idMapa
	 * @param mapas
	 * @return
	 */
	private static Mapa buscarMapaLista(String idMapa, ArrayList<Mapa> mapas) {
		// TODO Auto-generated method stub
		for (Mapa mapa : mapas) {
			if (mapa.getOid().equals(idMapa))
				return mapa;
		}
		return null;
	}

	public static ArrayList<Registro> consultarRegistro(String sql, Sensor sensor, Origen origen) throws SQLException {

		// PreparedStatement pst = connection.prepareStatement(sql);
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		// ResultSet rs = consultarSql(pst);

		ArrayList<Registro> respuesta = new ArrayList<Registro>();
		while (rs.next()) {

			Registro temp = new Registro();
			temp.setRegistroDate1(rs.getDate(Constantes.CampoRegistro.DATE1.campoBD()));
			temp.setRegistroDate2(rs.getDate(Constantes.CampoRegistro.DATE2.campoBD()));
			temp.setRegistroDate3(rs.getDate(Constantes.CampoRegistro.DATE3.campoBD()));
			temp.setRegistroDate4(rs.getDate(Constantes.CampoRegistro.DATE4.campoBD()));
			temp.setRegistroDate5(rs.getDate(Constantes.CampoRegistro.DATE5.campoBD()));
			temp.setRegistroFloat1(rs.getFloat(Constantes.CampoRegistro.FLOAT1.campoBD()));
			temp.setRegistroFloat2(rs.getFloat(Constantes.CampoRegistro.FLOAT2.campoBD()));
			temp.setRegistroFloat3(rs.getFloat(Constantes.CampoRegistro.FLOAT3.campoBD()));
			temp.setRegistroFloat4(rs.getFloat(Constantes.CampoRegistro.FLOAT4.campoBD()));
			temp.setRegistroFloat5(rs.getFloat(Constantes.CampoRegistro.FLOAT5.campoBD()));
			temp.setRegistroInt1(rs.getInt(Constantes.CampoRegistro.INT1.campoBD()));
			temp.setRegistroInt2(rs.getInt(Constantes.CampoRegistro.INT2.campoBD()));
			temp.setRegistroInt3(rs.getInt(Constantes.CampoRegistro.INT3.campoBD()));
			temp.setRegistroInt4(rs.getInt(Constantes.CampoRegistro.INT4.campoBD()));
			temp.setRegistroInt5(rs.getInt(Constantes.CampoRegistro.INT5.campoBD()));
			temp.setRegistroVarchar1(rs.getString(Constantes.CampoRegistro.VARCHAR1.campoBD()));
			temp.setRegistroVarchar2(rs.getString(Constantes.CampoRegistro.VARCHAR2.campoBD()));
			temp.setRegistroVarchar3(rs.getString(Constantes.CampoRegistro.VARCHAR3.campoBD()));
			temp.setRegistroVarchar4(rs.getString(Constantes.CampoRegistro.VARCHAR4.campoBD()));
			temp.setRegistroVarchar5(rs.getString(Constantes.CampoRegistro.VARCHAR5.campoBD()));
			try {
				temp.setRegistrotime1(new Time(rs.getTimestamp(Constantes.CampoRegistro.HORA1.campoBD()).getTime()));
			} catch (Exception e) {
			}
			try {
				temp.setRegistrotime2(new Time(rs.getTimestamp(Constantes.CampoRegistro.HORA2.campoBD()).getTime()));
			} catch (Exception e) {
			}
			try {
				temp.setRegistrotime3(new Time(rs.getTimestamp(Constantes.CampoRegistro.HORA3.campoBD()).getTime()));
			} catch (Exception e) {
			}
			try {
				temp.setRegistrotime4(new Time(rs.getTimestamp(Constantes.CampoRegistro.HORA4.campoBD()).getTime()));
			} catch (Exception e) {
			}
			try {
				temp.setRegistrotime5(new Time(rs.getTimestamp(Constantes.CampoRegistro.HORA5.campoBD()).getTime()));
			} catch (Exception e) {
			}
			temp.setSensor(sensor);
			temp.setOrigen(origen);
			temp.setOid(rs.getString(Constantes.CampoRegistro.ID_.campoBD()));
			temp.setRegistroFecha(rs.getString(Constantes.CampoRegistro.FECHA_REGISTRO.campoBD()));
			respuesta.add(temp);

		}

		return respuesta;

	}

	public int borrarRegistro(Registro object) throws SQLException {
		// TODO Auto-generated method stub
		String sql = null;
		PreparedStatement pst = connection.prepareStatement(sql);
		return ejecutarSql(pst);
	}

	public int salvarRegistro(Registro object) throws SQLException {
		// TODO Auto-generated method stub
		String sql = null;
		PreparedStatement pst = connection.prepareStatement(sql);
		return ejecutarSql(pst);

	}

	public static ResultSet consultarSql(PreparedStatement pst) {
		try {
			ResultSet rs = null;

			// int registros = 0;
			rs = pst.executeQuery();
			return rs;

		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger("BdManager").log(Level.SEVERE, "Error in insert in the bd:" + e.getMessage());

			return null;

		}

	}

	public int ejecutarSql(PreparedStatement pst) {
		try {
			ResultSet rs = null;

			// int registros = 0;
			rs = pst.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();

			while (rs.next()) {
				for (int i = 1; i <= rsm.getColumnCount(); i++) {

				}
				// registros++;
			}

			rs.close();
			pst.close();

		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
					"Error in insert in the bd:" + e.getMessage());

			return -1;

		}
		return 0;

	}

	/**
	 * guarda un json y devuelve el numero del id guardado en la bd
	 *
	 * @param jSon
	 * @return
	 */
	public static int saveJson(String jSon) {
		int autoGeneratedID = 0;
		try {
			String sql = "insert into " + Constantes.BD + ".fateon_Json (json) values (?)";
			PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, jSon);
			pst.executeUpdate();

			ResultSet tableKeys = pst.getGeneratedKeys();
			tableKeys.next();
			autoGeneratedID = tableKeys.getInt(1);

		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger("BdManager").log(Level.SEVERE, "Error insert Json in the BD:" + e.getMessage());

		}

		return autoGeneratedID;

	}

	/**
	 * busqueda de Sensor a traves de un id
	 *
	 * @param id
	 */
	public static String buscarJson(int id) {
		String respuesta = "";
		try {
			String sql = "select * from " + Constantes.BD + ".fateon_Json where idfateon_Json='" + id + "'";
			PreparedStatement pst = connection.prepareStatement(sql);

			ResultSet rs = consultarSql(pst);
			System.out.println(sql);

			while (rs.next()) {
				respuesta = (rs.getString("json"));

			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger("BdManager").log(Level.SEVERE, "Error in search of Sensor in the bd:" + e.getMessage());

		}

		return respuesta;
		// TODO Auto-generated method stub

	}

	/**
	 * busqueda de Sensor a traves de un id
	 *
	 * @param id
	 */
	public static Sensor buscarSensor(String id) {
		Sensor respuesta = null;
		try {
			String sql = "select * from " + Constantes.BD + ".fateon_sensor where sensor_id='" + id + "'";
			PreparedStatement pst = connection.prepareStatement(sql);

			ResultSet rs = consultarSql(pst);
			System.out.println(sql);

			while (rs.next()) {
				respuesta = new Sensor();
				respuesta.setOid(rs.getString("sensor_id"));
				respuesta.setNombre(rs.getString("sensor_nombre"));
				respuesta.setMapa(buscarEnListaCatalogo(rs.getString("sensor_tabla")));
				respuesta.setPrograma(new Programa(rs.getString("sensor_programa")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger("BdManager").log(Level.SEVERE, "Error in search of Sensor in the bd:" + e.getMessage());

		}

		return respuesta;
		// TODO Auto-generated method stub

	}

	/**
	 * buscar un Origen a partir de un mapa
	 *
	 * @param id
	 * @return
	 */

	public static Origen BuscarOrigen(String id) {
		Origen respuesta = null;
		try {
			String sql = "select * from  " + Constantes.BD + ".fateon_origen where origen_id='" + id + "'";
			// System.out.println(sql);

			PreparedStatement pst = connection.prepareStatement(sql);

			ResultSet rs = consultarSql(pst);

			while (rs.next()) {
				respuesta = new Origen();
				respuesta.setOid(rs.getString("origen_id"));
				respuesta.setNombre(rs.getString("origen_nombre"));
				respuesta.setModelo(rs.getString("origen_modelo"));
				respuesta.setMac(rs.getString("origen_mac"));
				respuesta.setTipo(buscarEnListaCatalogo(rs.getString("origen_id_tipo")));
				respuesta.setSistema(buscarEnListaCatalogo(rs.getString("origen_id_sistema")));
				respuesta.setSucursal(buscarEnListaCatalogo(rs.getString("origen_id_sucursal")));
				respuesta.setOficina(buscarEnListaCatalogo(rs.getString("origen_id_oficina")));
				respuesta.setMunicipio(buscarEnListaCatalogo(rs.getString("origen_id_municipio")));
				respuesta.setCiudad(buscarEnListaCatalogo(rs.getString("origen_id_ciudad")));
				respuesta.setPais(buscarEnListaCatalogo(rs.getString("origen_id_pais")));
				respuesta.setIp(rs.getString("origen_ip"));
				respuesta.setHoraApertura(rs.getTime("hora_apertura"));
				respuesta.setHoraCierre(rs.getTime("hora_cierre"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger("BdManager").log(Level.SEVERE,
					"Error in search of BuscarOrigen in the bd:" + e.getMessage());

		}

		return respuesta;
	}

	/**
	 * buscar una tabla a partir de un mapa
	 *
	 * @param id
	 * @return //
	 */
	// public static Tabla_Mapa buscarTablaMapa(String id) {
	// Tabla_Mapa respuesta = null;
	// try {
	// String sql = "select * from fateon.fateon_tabla_mapa where
	// tabla_mapa_id='" + id + "'";
	// PreparedStatement pst = connection.prepareStatement(sql);
	//
	// ResultSet rs = consultarSql(pst);
	//
	// while (rs.next()) {
	// respuesta = new Tabla_Mapa();
	// respuesta.setId(rs.getString("tabla_mapa_id"));
	// respuesta.setNombre(rs.getString("tabla_mapa_nombre"));
	// respuesta.setMapas(buscarMapas(respuesta.getId()));
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// Logger.getLogger("BdManager").log(Level.SEVERE,
	// "Error in search of Tabla_mapa in the bd:" + e.getMessage());
	//
	// }
	//
	// return respuesta;
	// }

	/**
	 * buscar una tabla a partir de un mapa
	 *
	 * @param id
	 * @return
	 */
	public static Tabla buscarTabla(String id) {

		Tabla respuesta = null;

		if (listaTablas == null) {
			try {
				listaTablas = new ArrayList<Tabla>();
				String sql = "select * from  " + Constantes.BD + ".fateon_tabla";
				System.out.println(sql);
				PreparedStatement pst = connection.prepareStatement(sql);

				ResultSet rs = consultarSql(pst);

				while (rs.next()) {
					respuesta = new Tabla();
					respuesta.setOid(rs.getString("tabla_id"));
					respuesta.setNombre(rs.getString("tabla_nombre"));
					respuesta.setCatalogos(buscarCatalogos(respuesta.getOid(), null));
					respuesta.setTablaNemonico(rs.getString("tabla_nemonico"));
					respuesta.setPrograma(rs.getString("tabla_programa"));
					respuesta.setUbicacion(rs.getString("tabla_ubicacion_programa"));
					listaTablas.add(respuesta);
				}
			} catch (Exception e) {
				// TODO: handle exception
				Logger.getLogger("BdManager").log(Level.SEVERE,
						"Error in search of Tabla_mapa in the bd:" + e.getMessage());

			}
		}

		for (Tabla tabla : listaTablas) {

			if (tabla.getOid().equals(id))
				return tabla;
		}

		return null;
	}

	private static void buscarCatalogos() {
		listaCatalogos = null;
		try {
			String sql = "SELECT * FROM  " + Constantes.BD + ".fateon_catalogo";
			System.out.println(sql);
			PreparedStatement pst = connection.prepareStatement(sql);

			ResultSet rs = consultarSql(pst);
			listaCatalogos = new ArrayList<Catalogo>();
			while (rs.next()) {
				Catalogo temp = new Catalogo();
				temp = new Catalogo();
				temp.setOid(rs.getString("catalogo_id"));
				temp.setNombre(rs.getString("catalogo_nombre"));
				temp.setTabla(buscarTabla(rs.getString("catalogo_id_tabla")));
				temp.setValorFecha(rs.getDate("catalogo_valor_time"));
				temp.setValorDecimal(rs.getFloat("catalogo_valor_float"));
				temp.setValorHora(rs.getTime("catalogo_valor_time"));
				temp.setValorEntero(rs.getInt("catalogo_valor_int"));
				temp.setValorCadena(rs.getString("catalogo_descripcion_varchar"));
				listaCatalogos.add(temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger("BdManager").log(Level.SEVERE,
					"Error in search of lista de Catalogos in the bd:" + e.getMessage());

		}

	}

	public static ArrayList<Tendencia> buscarTendencia(Sensor sensor, Origen origen) throws SQLException {
		String sql = "SELECT  * " + "FROM " + "fateon_new.fateon_tendencia " + "where " + "sensor_id='"
				+ sensor.getOid() + "'" + " and origen_id='" + origen.getOid() + "'" + "  order by dia, hora, minuto";
		Statement stmt = connection.createStatement();
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);

		// ResultSet rs = consultarSql(pst);
		ArrayList<Tendencia> respuesta = new ArrayList<Tendencia>();
		while (rs.next()) {

			Tendencia temp = new Tendencia();
			temp.setFloat1(rs.getFloat("float1"));
			temp.setFloat2(rs.getFloat("float2"));
			temp.setFloat3(rs.getFloat("float3"));
			temp.setFloat4(rs.getFloat("float4"));
			temp.setFloat5(rs.getFloat("float5"));
			temp.setInt1(rs.getInt("int1"));
			temp.setInt2(rs.getInt("int2"));
			temp.setInt3(rs.getInt("int3"));
			temp.setInt4(rs.getInt("int4"));
			temp.setInt5(rs.getInt("int5"));
			temp.setDia(rs.getInt("dia"));
			temp.setHora(rs.getInt("hora"));
			temp.setMinuto(rs.getInt("minuto"));
			temp.setOrigen(origen);
			temp.setSensor(sensor);

			respuesta.add(temp);

		}

		return respuesta;

	}

	public static ArrayList<MediaMovil> buscarMediaMovil(Sensor sensor, Origen origen, boolean habil)
			throws SQLException

	{
		return buscarMediaMovil(sensor, origen, habil, false);
	}

	public static ArrayList<MediaMovil> buscarMediaMovil(Sensor sensor, Origen origen, boolean habil, boolean reducido)
			throws SQLException {
		String sql = "SELECT  * " + "FROM " + Constantes.BD;

		if (reducido) {
			sql = sql + TablaBD.MEDIA_MOVIL_REDUCIDO.tabla();
		} else {
			sql = sql + TablaBD.MEDIA_MOVIL.tabla();
		}

		sql = sql + " where " + "sensor_id='" + sensor.getOid() + "'" + " and origen_id='" + origen.getOid() + "'";
		if (habil) {
			Date horaApertura = new Date(origen.getHoraApertura().getTime());

			Date horaCierre = new Date(origen.getHoraCierre().getTime());
			DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
			sql = sql + " and hora >= '" + hourFormat.format(horaApertura) + "' and hora<= '"
					+ hourFormat.format(horaCierre) + "'";
		}

		sql = sql + "  order by dia, hora";
		Statement stmt = connection.createStatement();
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);

		// ResultSet rs = consultarSql(pst);
		ArrayList<MediaMovil> respuesta = new ArrayList<MediaMovil>();
		while (rs.next()) {

			MediaMovil temp = new MediaMovil();
			temp.setFloat1(rs.getFloat("float1"));
			temp.setFloat2(rs.getFloat("float2"));
			temp.setFloat3(rs.getFloat("float3"));
			temp.setFloat4(rs.getFloat("float4"));
			temp.setFloat5(rs.getFloat("float5"));
			temp.setInt1(rs.getInt("int1"));
			temp.setInt2(rs.getInt("int2"));
			temp.setInt3(rs.getInt("int3"));
			temp.setInt4(rs.getInt("int4"));
			temp.setInt5(rs.getInt("int5"));
			temp.setDia(rs.getDate("dia"));
			temp.setHora(rs.getTime("hora"));
			temp.setOrigen(origen);
			temp.setSensor(sensor);

			respuesta.add(temp);

		}

		return respuesta;

	}

	public static int crearTendencias(int mes) throws SQLException {
		truncarTabla(TablaBD.TENDENCIA);
		String sql = "insert into fateon_tendencia" + " SELECT  avg(ifnull(registro_int_1,0)) as 'int1',"
				+ "	avg(ifnull(registro_int_2,0)) as 'int2'," + " avg(ifnull(registro_int_3,0)) as 'int3',"
				+ " avg(ifnull(registro_int_4,0)) as 'int4'," + " avg(ifnull(registro_int_5,0)) as 'int5',"
				+ " avg(ifnull(registro_float_1,0)) as 'float1'," + " avg(ifnull(registro_float_2,0)) as 'float2',"
				+ " avg(ifnull(registro_float_3,0)) as 'float3'," + " avg(ifnull(registro_float_4,0)) as 'float4',"
				+ " avg(ifnull(registro_float_5,0)) as 'float5',"
				+ " sensor_id,origen_id,hour(registro_time_1) as 'hora'," + " minute(registro_time_1) as 'minuto',"
				+ " weekday(registro_date_1) as 'dia' " + "FROM " + "fateon_new.fateon_registro " + "where "
				+ "registro_date_1 BETWEEN  date_sub(now(), interval " + mes + " month) and now()" + "group by "
				+ "origen_id," + "sensor_id, " + "hour(registro_time_1), " + "minute(registro_time_1) , "
				+ "weekday(registro_date_1)" + "        order by origen_id," + "        sensor_id,"
				+ "        weekday(registro_date_1)," + "        hour(registro_time_1),"
				+ "        minute(registro_time_1)";
		Statement stmt = connection.createStatement();
		System.out.println(sql);
		return stmt.executeUpdate(sql);

	}

	private static ArrayList<Catalogo> buscarCatalogos(String tabla, ArrayList<Catalogo> listaDeCatalogos) {

		if (listaDeCatalogos == null) {
			if (listaCatalogos == null) {
				buscarCatalogos();
			}
			listaDeCatalogos = listaCatalogos;
		}

		ArrayList<Catalogo> respuesta = new ArrayList<Catalogo>();
		for (Catalogo catalogo : listaDeCatalogos) {

			if (catalogo.getTabla().getOid().equals(tabla)) {
				respuesta.add(catalogo);
			}
		}

		return respuesta;
	}

	public static Catalogo buscarCatalogo(String id) {
		Catalogo respuesta = null;
		try {
			String sql = "select * from  " + Constantes.BD + ".fateon_catalogo where catalogo_id='" + id + "'";
			PreparedStatement pst = connection.prepareStatement(sql);

			ResultSet rs = consultarSql(pst);

			while (rs.next()) {
				respuesta = new Catalogo();
				respuesta.setOid(rs.getString("catalogo_id"));
				respuesta.setNombre(rs.getString("catalogo_nombre"));
				respuesta.setTabla(buscarTabla(rs.getString("catalogo_id_tabla")));

				respuesta.setValorFecha(rs.getDate("catalogo_valor_time"));
				respuesta.setValorDecimal(rs.getFloat("catalogo_valor_float"));
				respuesta.setValorEntero(rs.getInt("catalogo_valor_int"));
				respuesta.setValorCadena(rs.getString("catalogo_descripcion_varchar"));
				respuesta.setValorHora(rs.getTime("catalogo_valor_time"));

			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger("BdManager").log(Level.SEVERE,
					"Error in search of Tabla_mapa in the bd:" + e.getMessage());

		}

		return respuesta;
	}

	/**
	 * @param mes
	 * @return
	 * @throws SQLException
	 */
	public static int crearMediaMovil() throws SQLException {
		return crearMediaMovil(3, Constantes.TablaBD.REGISTRO);

	}

	/**
	 * @param i
	 * @param registro
	 * @return
	 * @throws SQLException
	 */
	private static int crearMediaMovil(int serie, TablaBD tablaBD) throws SQLException {
		// TODO Auto-generated method stub

		int cont = 0;

		ArrayList<Registro> temp = consultarRegistro(tablaBD);
		String sql = "insert into " + Constantes.BD;
		if (tablaBD.equals(Constantes.TablaBD.REGISTRO)) {
			truncarTabla(TablaBD.MEDIA_MOVIL);
			sql = sql + TablaBD.MEDIA_MOVIL.tabla();
		} else {
			truncarTabla(TablaBD.MEDIA_MOVIL_REDUCIDO);
			sql = sql + TablaBD.MEDIA_MOVIL_REDUCIDO.tabla();

		}
		sql = sql + "values (?,?,?,?,?," + "?,?,?,?,?," + "?,?,?,?)";
		System.out.println(sql);

		for (Registro registro : temp) {

			MediaMovil temporal = new MediaMovil();

			if (cont == 0 || (temp.size() - cont < serie)) {
				temporal.setInt1(registro.getRegistroInt1());
				temporal.setInt2(registro.getRegistroInt2());
				temporal.setInt3(registro.getRegistroInt3());
				temporal.setInt4(registro.getRegistroInt4());
				temporal.setInt5(registro.getRegistroInt5());
				temporal.setFloat1(registro.getRegistroFloat1());
				temporal.setFloat2(registro.getRegistroFloat2());
				temporal.setFloat3(registro.getRegistroFloat3());
				temporal.setFloat4(registro.getRegistroFloat4());
				temporal.setFloat5(registro.getRegistroFloat5());
				temporal.setDia(registro.getRegistroDate1());
				temporal.setHora(registro.getRegistrotime1());
				temporal.setOrigen(registro.getOrigen());
				temporal.setSensor(registro.getSensor());

			} else {
				int tempInt = 0;
				float tempFloat = 0;
				for (int i = 0; i < serie; i++) {
					tempInt = tempInt + temp.get(cont + i).getRegistroInt1();
				}

				temporal.setInt1(tempInt / serie);
				for (int i = 0; i < serie; i++) {
					tempInt = tempInt + temp.get(cont + i).getRegistroInt2();
				}

				temporal.setInt2(tempInt / serie);
				for (int i = 0; i < serie; i++) {
					tempInt = tempInt + temp.get(cont + i).getRegistroInt3();
				}

				temporal.setInt3(tempInt / serie);
				for (int i = 0; i < serie; i++) {
					tempInt = tempInt + temp.get(cont + i).getRegistroInt4();
				}

				temporal.setInt4(tempInt / serie);
				for (int i = 0; i < serie; i++) {
					tempInt = tempInt + temp.get(cont + i).getRegistroInt5();
				}

				temporal.setInt5(tempInt / serie);

				for (int i = 0; i < serie; i++) {
					tempFloat = tempFloat + temp.get(cont + i).getRegistroFloat1();
				}

				temporal.setFloat1(tempFloat / serie);
				for (int i = 0; i < serie; i++) {
					tempFloat = tempFloat + temp.get(cont + i).getRegistroFloat2();
				}

				temporal.setFloat2(tempFloat / serie);
				for (int i = 0; i < serie; i++) {
					tempFloat = tempFloat + temp.get(cont + i).getRegistroFloat3();
				}

				temporal.setFloat3(tempFloat / serie);
				for (int i = 0; i < serie; i++) {
					tempFloat = tempFloat + temp.get(cont + i).getRegistroFloat4();
				}

				temporal.setFloat4(tempFloat / serie);
				for (int i = 0; i < serie; i++) {
					tempFloat = tempFloat + temp.get(cont + i).getRegistroFloat5();
				}

				temporal.setFloat5(tempFloat / serie);

				temporal.setDia(registro.getRegistroDate1());
				temporal.setHora(registro.getRegistrotime1());
				temporal.setOrigen(registro.getOrigen());
				temporal.setSensor(registro.getSensor());

			}

			PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, temporal.getInt1());
			pst.setInt(2, temporal.getInt2());
			pst.setInt(3, temporal.getInt3());
			pst.setInt(4, temporal.getInt4());
			pst.setInt(5, temporal.getInt5());
			pst.setFloat(6, temporal.getFloat1());
			pst.setFloat(7, temporal.getFloat2());
			pst.setFloat(8, temporal.getFloat3());
			pst.setFloat(9, temporal.getFloat4());
			pst.setFloat(10, temporal.getFloat5());
			pst.setString(11, temporal.getSensor().getOid());
			pst.setString(12, temporal.getOrigen().getOid());
			pst.setTime(13, temporal.getHora());
			pst.setDate(14, (java.sql.Date) temporal.getDia());
			pst.executeUpdate();

			cont++;

		}

		return cont;
	}

	public static int truncarTabla(TablaBD tabla) throws SQLException {

		String sql = "truncate table " + Constantes.BD + tabla.tabla();
		Statement stmt = connection.createStatement();
		System.out.println(sql);
		return stmt.executeUpdate(sql);

	}

	/**
	 * @throws SQLException
	 * @throws ParseException
	 *
	 */
	public static int reducirRegistros(int serie) throws SQLException, ParseException {
		int cont = 0;
		truncarTabla(TablaBD.REGISTRO_REDUCIDO);

		ArrayList<ArrayList<Registro>> listaTemp = dividirSensorOrigen(consultarRegistro());
		String sql = "insert into " + Constantes.BD + ".fateon_registro_reducido " + "values " + "(?,?,?,?,?,"
				+ "?,?,?,?,?," + "?,?,?,?,?," + "?,?,?,?,?," + "?,?,?,?,?," + "?,?,?,?)";
		System.out.println(sql);

		for (int j = 0; j < listaTemp.size(); j++) {

			ArrayList<Registro> temp = listaTemp.get(j);
			for (int z = 0; z < temp.size(); z = z + 3) {
				Registro temporal = new Registro();
				temporal = temp.get(z);

				System.out.println(
						"sensor origen:" + temporal.getSensor().getNombre() + temporal.getOrigen().getNombre());
				if (cont == 0 || (temp.size() - cont < serie)) {
					temporal = temp.get(z);
					;
				} else {
					int tempInt = 0;
					float tempFloat = 0;
					for (int i = 0; i < serie; i++) {
						tempInt = tempInt + temp.get(cont + i).getRegistroInt1();
					}

					temporal.setRegistroInt1(tempInt / serie);
					tempInt = 0;
					for (int i = 0; i < serie; i++) {
						tempInt = tempInt + temp.get(cont + i).getRegistroInt2();
					}

					temporal.setRegistroInt2(tempInt / serie);
					tempInt = 0;
					for (int i = 0; i < serie; i++) {
						tempInt = tempInt + temp.get(cont + i).getRegistroInt3();
					}

					temporal.setRegistroInt3(tempInt / serie);
					tempInt = 0;
					for (int i = 0; i < serie; i++) {
						tempInt = tempInt + temp.get(cont + i).getRegistroInt4();
					}

					temporal.setRegistroInt4(tempInt / serie);
					tempInt = 0;
					for (int i = 0; i < serie; i++) {
						tempInt = tempInt + temp.get(cont + i).getRegistroInt5();
					}

					temporal.setRegistroInt5(tempInt / serie);
					tempInt = 0;
					for (int i = 0; i < serie; i++) {
						tempFloat = tempFloat + temp.get(cont + i).getRegistroFloat1();
					}

					temporal.setRegistroFloat1(tempFloat / serie);
					tempFloat = 0;
					for (int i = 0; i < serie; i++) {
						tempFloat = tempFloat + temp.get(cont + i).getRegistroFloat2();
					}

					temporal.setRegistroFloat2(tempFloat / serie);
					tempFloat = 0;
					for (int i = 0; i < serie; i++) {
						tempFloat = tempFloat + temp.get(cont + i).getRegistroFloat3();
					}

					temporal.setRegistroFloat3(tempFloat / serie);
					tempFloat = 0;
					for (int i = 0; i < serie; i++) {
						tempFloat = tempFloat + temp.get(cont + i).getRegistroFloat4();
					}

					temporal.setRegistroFloat4(tempFloat / serie);
					tempFloat = 0;
					for (int i = 0; i < serie; i++) {
						tempFloat = tempFloat + temp.get(cont + i).getRegistroFloat5();
					}

					temporal.setRegistroFloat5(tempFloat / serie);

				}

				PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				pst.setString(1, temporal.getOid());
				DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
				pst.setTimestamp(2, new Timestamp((dFormat.parse(temporal.getRegistroFecha())).getTime()));
				pst.setString(3, temporal.getRegistroVarchar1());
				pst.setString(4, temporal.getRegistroVarchar2());
				pst.setString(5, temporal.getRegistroVarchar3());
				pst.setString(6, temporal.getRegistroVarchar4());
				pst.setString(7, temporal.getRegistroVarchar5());

				pst.setInt(8, temporal.getRegistroInt1());
				pst.setInt(9, temporal.getRegistroInt2());
				pst.setInt(10, temporal.getRegistroInt3());
				pst.setInt(11, temporal.getRegistroInt4());
				pst.setInt(12, temporal.getRegistroInt5());
				try {

					pst.setFloat(13, temporal.getRegistroFloat1());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {

					pst.setFloat(14, temporal.getRegistroFloat2());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {

					pst.setFloat(15, temporal.getRegistroFloat3());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {

					pst.setFloat(16, temporal.getRegistroFloat4());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {

					pst.setFloat(17, temporal.getRegistroFloat5());
				} catch (Exception e) {
					// TODO: handle exception
				}
				pst.setDate(18, new java.sql.Date(temporal.getRegistroDate1().getTime()));

				try {
					pst.setDate(19, new java.sql.Date(temporal.getRegistroDate2().getTime()));
				} catch (Exception e) {
					// TODO: handle exception
					pst.setDate(19, null);
				}
				try {

					pst.setDate(20, new java.sql.Date(temporal.getRegistroDate3().getTime()));
				} catch (Exception e) {
					// TODO: handle exception

					pst.setDate(20, null);
				}
				try {

					pst.setDate(21, new java.sql.Date(temporal.getRegistroDate4().getTime()));
				} catch (Exception e) {
					// TODO: handle exception

					pst.setDate(21, null);
				}
				try {

					pst.setDate(22, new java.sql.Date(temporal.getRegistroDate5().getTime()));
				} catch (Exception e) {
					// TODO: handle exception

					pst.setDate(22, null);
				}
				try {

					pst.setTime(23, temporal.getRegistrotime1());
				} catch (Exception e) {
					// TODO: handle exception

					pst.setDate(23, null);
				}
				try {
					pst.setTime(24, temporal.getRegistrotime2());
				} catch (Exception e) {
					// TODO: handle exception

					pst.setDate(24, null);
				}
				try {
					pst.setTime(25, temporal.getRegistrotime3());
				} catch (Exception e) {
					// TODO: handle exception

					pst.setDate(25, null);
				}
				try {
					pst.setTime(26, temporal.getRegistrotime4());
				} catch (Exception e) {
					// TODO: handle exception

					pst.setDate(26, null);
				}
				try {
					pst.setTime(27, temporal.getRegistrotime5());
				} catch (Exception e) {
					// TODO: handle exception

					pst.setDate(27, null);
				}

				pst.setString(28, temporal.getSensor().getOid());
				pst.setString(29, temporal.getOrigen().getOid());
				pst.executeUpdate();

				cont++;

			}
		}

		return cont;

	}

	/**
	 * @param consultarRegistro
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static ArrayList<ArrayList<Registro>> dividirSensorOrigen(ArrayList<Registro> registros) {

		ArrayList<ArrayList<Registro>> respuesta = new ArrayList<ArrayList<Registro>>();
		ArrayList<Tupla> tuplas = new ArrayList<Tupla>();
		for (Registro registro : registros) {
			Sensor sensor = registro.getSensor();
			Origen origen = registro.getOrigen();

			if (!buscarSensorOrigen(tuplas, registro)) {
				tuplas.add(new Tupla(sensor, origen));
				ArrayList<Registro> resp = new ArrayList<Registro>();
				ArrayList<Registro> temp = (ArrayList<Registro>) registros.clone();
				temp.remove(0);
				resp.add(registro);
				for (Registro registro2 : temp) {

					if (registro2.getSensor().equals(sensor) && registro2.getOrigen().equals(origen)) {
						resp.add(registro2);
						// registros.remove(registro2);

					}

				}

				respuesta.add(resp);
			}
		}

		return respuesta;
	}

	/**
	 * @param tuplas
	 * @param registro
	 * @return
	 */
	private static boolean buscarSensorOrigen(ArrayList<Tupla> tuplas, Registro registro) {
		// TODO Auto-generated method stub
		Tupla<Sensor, Origen> tupla = new Tupla<Sensor, Origen>(registro.getSensor(), registro.getOrigen());

		for (Tupla tuplaTemp : tuplas) {
			if (tuplaTemp.equals(tupla))
				return true;
		}

		return false;
	}

	/**
	 * @param registroReducido
	 * @throws SQLException
	 */
	public static void crearMediaMovil(TablaBD tablaBD) throws SQLException {
		// TODO Auto-generated method stub
		crearMediaMovil(3, tablaBD);
	}

}
