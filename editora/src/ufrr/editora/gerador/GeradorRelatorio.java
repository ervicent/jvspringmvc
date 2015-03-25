package ufrr.editora.gerador;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class GeradorRelatorio {

	private String nomeArquivo ;
	private Map<String, Object> parametros;
	private Connection connection;

	public GeradorRelatorio(String nomeArquivo, Map<String, Object> parametros, Connection connection) {
		this.nomeArquivo = nomeArquivo;
		this.parametros = parametros;
		this.connection = connection;
	}
	
	public void geraPDFParaOutPutStream(OutputStream outputStream) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(this.nomeArquivo, this.parametros, this.connection);

			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();
			
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

}

