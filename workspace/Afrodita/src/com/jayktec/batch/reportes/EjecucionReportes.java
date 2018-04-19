/**
 *
 */
package com.jayktec.batch.reportes;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Map;

import com.jayktec.batch.Ejecucion;
import com.jayktec.controlador.Constantes;
import com.jayktec.controlador.Constantes.Reporte;
import com.jayktec.controller.Conexion;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.type.PdfVersionEnum;

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
		final String treporte = valorParametro(Constantes.TipoParametro.TipoReporte.tipo());
		if (treporte.toUpperCase().equals(Constantes.Reporte.Pdf.tipo())) {
			setTipoReporte(Constantes.Reporte.Pdf);
		} else if (treporte.toUpperCase().equals(Constantes.Reporte.Xls.tipo())) {
			setTipoReporte(Constantes.Reporte.Xls);
		} else if (treporte.toUpperCase().equals(Constantes.Reporte.Docx.tipo())) {
			setTipoReporte(Constantes.Reporte.Docx);
		} else {
			setTipoReporte(Constantes.Reporte.Pdf);
		}

	}

	public void compilarJRxml(InputStream jrxml) throws JRException {
		setReporte(JasperCompileManager.compileReport(jrxml));
	}

	public void compilarJRxml(String jrxml) throws JRException {
		setReporte(JasperCompileManager.compileReport(jrxml));
	}

	public void docExporter(JasperPrint jasperPrint) throws JRException {
		final JRDocxExporter exporter = new JRDocxExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile + ".docx"));
		final SimpleDocxReportConfiguration configuration = new SimpleDocxReportConfiguration();

		exporter.setConfiguration(configuration);

		exporter.exportReport();
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
	 * @return the reporte
	 */
	public JasperReport getReporte() {
		return reporte;
	}

	/**
	 * @return the tipoReporte
	 */
	public Reporte getTipoReporte() {
		return tipoReporte;
	}

	public void pdfExporter(JasperPrint jasperPrint) throws JRException {
		final JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile + ".pdf"));
		final SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setTagged(false);
		configuration.setPdfVersion(PdfVersionEnum.VERSION_1_7);
		exporter.setConfiguration(configuration);

		// exporter.exportReport();
		JasperExportManager.exportReportToPdfFile(jasperPrint, destFile + ".pdf");
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
			} else if (getTipoReporte().equals(Constantes.Reporte.Xls)) {
				xlsExporter(jasperPrint);
			} else {
				docExporter(jasperPrint);
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
	 * @param reporte
	 *            the reporte to set
	 */
	public void setReporte(JasperReport reporte) {
		this.reporte = reporte;
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

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile + ".xls"));
		final SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(true);
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		exporter.setConfiguration(configuration);

		exporter.exportReport();
	}

}
