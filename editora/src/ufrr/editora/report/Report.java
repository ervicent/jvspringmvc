package ufrr.editora.report;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import ufrr.editora.connection.ConnectionFactory;

public class Report {

	HashMap<String, Object> parameters;
	FacesContext facesContext;
	String nome;
	Connection con;

	public Report(String nome, HashMap<String, Object> params) {
		facesContext = FacesContext.getCurrentInstance();
		this.nome = nome;
		parameters = new HashMap<String, Object>();
		parameters.putAll(params);
	}	

	public Report() {
		facesContext = FacesContext.getCurrentInstance();		
	}

	/**
	 * Devolve um relatorio PDF na response.
	 */
	public void pdfReport() {
		JRExporter exporter = null;
		exporter = new JRPdfExporter();
		JasperPrint jasperPrint = getJasperPrint();
		fillReport("PDF_MIME", exporter, jasperPrint);
	}

	/**
	 * A partir do to tipo do relatorio, do Exporter e do JasperPrint devolver o
	 * OutputStream do relatorio no OutPutStream da Response.
	 * 
	 * @param tipo MIME TYPE do relatorio. Sera utilizado para configurar o MIME TYPE da Response.
	 * @param exporter Exporter especifico para cada tipo de relatario.
	 * @param jasperPrint JasperPrint que ira gerar o relatario.
	 */
	private void fillReport(String tipo, JRExporter exporter, JasperPrint jasperPrint) {

		ExternalContext econtext = facesContext.getExternalContext();

		HttpServletResponse response = (HttpServletResponse) econtext.getResponse();
		response.setContentType("application/pdf");  
		response.setHeader("Content-disposition", "attachment;filename="+nome+".pdf"); 

		try {
			// tentando jogar direto para impressora
			//			JasperPrintManager.printReport("/WEB-INF/classes/ufrr/editora/report/" + nome + ".jasper", false);

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

			exporter.exportReport();			

		} catch (RuntimeException ex) {
			ex.printStackTrace();
		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// Tell JavaServer Faces that no output is required
		facesContext.responseComplete();
	}

	/**
	 * Obtem um objeto JasperPrint para ser utilizado na configuracao do
	 * Exporter de relatarios.
	 * 
	 * @return JasperPrint que sera utilizado na configuracao do Exporter.
	 */
	private JasperPrint getJasperPrint() {

		InputStream stream = null;
		Connection conn = null;
		JasperPrint jasperPrint = null;

		try {
			stream = findReport();
			conn = new ConnectionFactory().getConnection();
			jasperPrint = JasperFillManager.fillReport(stream, parameters, conn);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (JRException ex) {
			ex.printStackTrace();
			throw new FacesException(ex);
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return jasperPrint;
	}

	/**
	 * Localiza o arquivo .jasper atraves do nome do relatorio.
	 * 
	 * @return ImputStream contendo o arquivo .jasper
	 */
	@SuppressWarnings("unused")
	private InputStream findReport() {
		InputStream retValue = null;
		try {
			ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
			String realPath = servletContext.getRealPath("/WEB-INF/jasper/" + nome + ".jasper");
			retValue = new FileInputStream(realPath);

			if (retValue == null) {
				throw new IllegalArgumentException("O relatorio de nome "	+ nome + " nao foi encontrado.");
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
		}
		return retValue;
	}

	public String findSubReport(String nomeSub) {
		String realPath2 = "";
		try {
			ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
			realPath2 = servletContext.getRealPath("/WEB-INF/jasper/" + nomeSub + ".jasper");			
		} catch (Exception ex) {
			Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
		}
		return realPath2;
	}

}
