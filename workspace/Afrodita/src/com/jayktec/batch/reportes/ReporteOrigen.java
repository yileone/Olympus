/**
 *
 */
package com.jayktec.batch.reportes;

import java.io.InputStream;
import java.text.ParseException;

import com.jayktec.controlador.Constantes;
import com.jayktec.controlador.Utilidades;

import net.sf.jasperreports.engine.JRException;

/**
 * @author yisheng leon espinoza
 *
 */
public class ReporteOrigen extends EjecucionReportes {

	/**
	 * @param args
	 * @throws JRException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws JRException, ParseException {
		// TODO Auto-generated method stub

		final ReporteOrigen rOrigen = new ReporteOrigen();
		rOrigen.setTipoReporte(Constantes.Reporte.Pdf);
		rOrigen.run();
	}

	/**
	 * @throws JRException
	 *
	 */
	public ReporteOrigen() throws JRException {

		super();

		final InputStream iStream = Utilidades.class.getResourceAsStream("/Origen.jrxml");
		compilarJRxml(iStream);

	}

}
