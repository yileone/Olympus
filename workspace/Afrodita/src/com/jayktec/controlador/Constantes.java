package com.jayktec.controlador;

public class Constantes {

	public static final String ARCHIVO_PROPERTIES = "/olympus.properties";
	public static final String IP ="IP";
	public static final String PUERTO ="PORT";
	public static final String BD="fateon_new";
	
	public enum TablaBD {
		REGISTRO(".fateon_registro "),
		REGISTRO_REDUCIDO(".fateon_registro_reducido "),
		TENDENCIA(".fateon_tendencia"),
		MEDIA_MOVIL (".fateon_mediaMovil "),
		MEDIA_MOVIL_REDUCIDO (".fateon_mediaMovil_reducido ");
		
		private String tabla;
		
		private TablaBD(String tabla) {
			this.tabla= tabla;
			// TODO Auto-generated constructor stub
		}
		
		public String tabla()
		{
			return tabla;
		}
	}
	
	
	public enum Reporte {
		Pdf("PDF"),Xls("excel");
        private String tipo;
		
		private Reporte(String tipo) {
			this.tipo= tipo;
			// TODO Auto-generated constructor stub
		}
		
		public String tipo()
		{
			return tipo;
		}
	}
	public enum TipoParametro {
		MesTendencia("mesTendencia"),SerieMedia("serieMedia");
        private String tipo;
		
		private TipoParametro(String tipo) {
			this.tipo= tipo;
			// TODO Auto-generated constructor stub
		}
		
		public String tipo()
		{
			return tipo;
		}
	}
	
	
	public enum Tiempo {
		DIA("dia"),
		MES("mes"),
		ANUAL("anual"),
		SEMANAL("semanal");
        private String periodo;
		
		private Tiempo(String periodo) {
			this.periodo= periodo;
			// TODO Auto-generated constructor stub
		}
		
		public String periodo()
		{
			return periodo;
		}
	}
	
	
	public enum CampoRegistro {

		SENSOR("sensor_id"),
		FECHA_REGISTRO("registro_fecha"),
		ORIGEN("origen_id"),
		ID_("registro_id"),
				
		HORA1("registro_time_1"),
		HORA2("registro_time_2"),
		HORA3("registro_time_3"),
		HORA4("registro_time_4"),
		HORA5("registro_time_5"),
			
		INT1("registro_int_1"),
		INT2("registro_int_2"),
		INT3("registro_int_3"),
		INT4("registro_int_4"),
		INT5("registro_int_5"),
		
	    FLOAT1("registro_float_1"),
	    FLOAT2("registro_float_2"),
	    FLOAT3("registro_float_3"),
	    FLOAT4("registro_float_4"),
	    FLOAT5("registro_float_5"),
			
	    VARCHAR1("registro_varchar_1"),
	    VARCHAR2("registro_varchar_2"),
	    VARCHAR3("registro_varchar_3"),
	    VARCHAR4("registro_varchar_4"),
	    VARCHAR5("registro_varchar_5"),
	    
	    DATE1("registro_date_1"),
	    DATE2("registro_date_2"),
	    DATE3("registro_date_3"),
	    DATE4("registro_date_4"),
	    DATE5("registro_date_5");
		
		private String campoBD;
		
		private CampoRegistro(String campo) {
			this.campoBD= campo;
			// TODO Auto-generated constructor stub
		}
		
		public String campoBD()
		{
			return campoBD;
		}
	}
}
