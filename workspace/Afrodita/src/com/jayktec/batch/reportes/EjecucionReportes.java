/**
 *
 */
package com.jayktec.batch.reportes;

import java.text.*;
import java.util.*;

import com.jayktec.batch.*;
import com.jayktec.controlador.*;
import com.jayktec.controlador.Constantes.*;
import com.jayktec.controller.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.export.*;

/**
 * @author {Yisheng León Espinoza} 12 abr. 2018 www.jayktec.com.ve
 */
public class EjecucionReportes extends Ejecucion {

	private JasperReport reporte;
	private Conexion conexion = new Conexion();
	private Reporte tipoReporte = Constantes.Reporte.Pdf;
	private final java.util.Date ahora = new java.util.Date();
	private String destFile = this.getClass().toString() + ahora.getTime();
	private Map<String, Object> parametroReporte = null;

	/**
	 *
	 */
	public EjecucionReportes() {
		// TODO Auto-generated constructor stub
		super();

	}

	public void compilarJRxml(String jrxml, String jasper) throws JRException {
		JasperCompileManager.compileReportToFile(jrxml, jasper);
	}

	/**
	 * @return the conexion
	 */
	public Conexion getConexion() {
		return conexion;
	}

	/**
	 * @return the destFile
	 */
	public String getDestFile() {
		return destFile;
	}

	/**
	 * @return the parametroReporte
	 */
	public Map<String, Object> getParametroReporte() {
		return parametroReporte;
	}

	/**
	 * @return the tipoReporte
	 */
	public Reporte getTipoReporte() {
		return tipoReporte;
	}

	public void pdfExporter(JasperPrint jasperPrint) throws JRException {
		final JRPdfExporter exporter = new JRPdfExporter();

		// exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		// exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile +
		// ".xls"));
		final SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(true);
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		// exporter.setConfiguration(configuration);

		exporter.exportReport();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.jayktec.batch.Ejecucion#run()
	 */
	@Override
	public void run() throws ParseException {
		// TODO Auto-generated method stub
		super.run();
		try {
			final JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, getParametroReporte(),
					conexion.getConexion());
			if (getTipoReporte().equals(Constantes.Reporte.Pdf)) {
				pdfExporter(jasperPrint);
			} else {
				xlsExporter(jasperPrint);
			}

		} catch (final JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param conexion
	 *            the conexion to set
	 */
	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	/**
	 * @param destFile
	 *            the destFile to set
	 */
	public void setDestFile(String destFile) {
		this.destFile = destFile;
	}

	/**
	 * @param parametroReporte
	 *            the parametroReporte to set
	 */
	public void setParametroReporte(Map<String, Object> parametroReporte) {
		this.parametroReporte = parametroReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(Reporte tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public void xlsExporter(JasperPrint jasperPrint) throws JRException {
		final JRXlsExporter exporter = new JRXlsExporter();

		// exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		// exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile +
		// ".xls"));
		final SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(true);
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		// exporter.setConfiguration(configuration);

		exporter.exportReport();
	}

}
