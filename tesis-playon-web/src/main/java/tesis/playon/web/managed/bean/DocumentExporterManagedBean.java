package tesis.playon.web.managed.bean;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

/**
 * 
 * @author alejandro
 * 
 */
@ManagedBean(name = "documentExporterMB")
@RequestScoped
public class DocumentExporterManagedBean implements Serializable {

	private static final long serialVersionUID = 1962213188760415313L;

	public void preProcessPDF(Object document) throws IOException,
			BadElementException, DocumentException {
		String sep = File.separator;
		Document pdf = (Document) document;
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "LogoEncabezadoLiquidacion2.png");
		String titulo = params.get("titulo");

		pdf.open();
		pdf.setPageSize(PageSize.A4);
		pdf.addTitle(titulo);
		pdf.add(Image.getInstance(logo));
	}

	public void listadoDenunciasPendientesPDF(Object document)
			throws IOException, BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de denuncias pendientes emitido el: "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado denuncias pendientes");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoDenunciasProcesoPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de denuncias en proceso emitido el: "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de denuncias en proceso");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoDenunciasFinalizadasPDF(Object document)
			throws IOException, BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph(
				"Listado de denuncias finalizadas emitido el: " + fechaActual(),
				fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado denuncias finalizadas");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) {
		for (int i = 0; i < nLineas; i++)
			parrafo.add(new Paragraph(" "));
	}

	private String fechaActual() {
		Date hoy = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formato.format(hoy);
		return fecha;

	}

	public void listadoPublicidadesPendientesPDF(Object document)
			throws IOException, BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph(
				"Listado de publicidades pendientes emitido el: "
						+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de publicidades pendientes");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoPlayasPendientesPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de Playas pendientes emitido el: "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de playas pendientes");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoPlayasAprobadasPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de Playas aprobadas emitido el: "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de playas Aprobadas");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoPlayasRechazadasPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de Playas Rechazadas emitido el: "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de playas Rechazadas");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoPublicidadesAprobadasPDF(Object document)
			throws IOException, BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph(
				"Listado de publicidades aprobadas emitido el: "
						+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de publicidades aprobadas");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoPublicidadesRechazadasPDF(Object document)
			throws IOException, BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph(
				"Listado de publicidades rechazadas emitido el: "
						+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de publicidades rechazadas");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoPromocionesPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de promociones emitido el: "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Promociones");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoClientesPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de clientes emitido el: "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Clientes");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoUsuariosPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de Usuarios del Sistema emitido el: "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Usuarios");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoModelosPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph(
				"Listado de Modelos de vehiculos en el Sistema ", fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Usuarios");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoMarcasPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph(
				"Listado de Marcas de vehiculos en el Sistema ", fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Usuarios");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

}
