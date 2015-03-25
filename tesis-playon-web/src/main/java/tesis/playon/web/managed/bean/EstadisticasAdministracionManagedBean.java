/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.ILiquidacionService;
import tesis.playon.web.service.IPublicidadService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.HeaderFooterPDF;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "estadisticaAdministracionMB")
@ViewScoped
public class EstadisticasAdministracionManagedBean implements Serializable {

    private static final long serialVersionUID = -5157703369335370457L;

    @ManagedProperty(value = "#{LiquidacionService}")
    ILiquidacionService liquidacionService;

    @ManagedProperty(value = "#{PublicidadService}")
    IPublicidadService publicidadService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    private List<String[]> comisionesList, comisionesPublicidadList;

    private Date fechaDesde, fechaHasta;

    private CartesianChartModel lModelComision;

    private StreamedContent chart;

    private DefaultCategoryDataset dataset;

    private String datatipFormat;

    private int maximoMonto;

    @PostConstruct
    public void init() {
	comisionesList = new ArrayList<String[]>();
	comisionesPublicidadList = new ArrayList<String[]>();
    }

    public void calcularComisionPorPeriodo() {
	crearGraficoComision();
    }

    private void crearGraficoComision() {
	lModelComision = new CartesianChartModel();
	maximoMonto = 0;
	LineChartSeries serieComisionTransacciones = new LineChartSeries();
	serieComisionTransacciones.setLabel("Recaudación de transacciones en $");

	comisionesList = getLiquidacionService().getEstadisticasComisiones(fechaDesde, fechaHasta);

	dataset = new DefaultCategoryDataset();

	for (int i = 0; i < comisionesList.size(); i++) {
	    String[] vComision = (String[]) comisionesList.get(i);
	    float comision = Float.parseFloat(vComision[3]);
	    maximoMonto = (int) comision > maximoMonto ? (int) comision : maximoMonto;
	    serieComisionTransacciones.set(String.format("%02d", Integer.parseInt(vComision[1])) + "/" + vComision[0],
		    comision);
	    dataset.addValue(comision, "Recaudación de transacciones en $",
		    String.format("%02d", Integer.parseInt(vComision[1])) + "/" + vComision[0]);

	}
	lModelComision.addSeries(serieComisionTransacciones);

	if (maximoMonto > 0)
	    maximoMonto += 20;
    }

    public void crearPDF() {
	ExternalContext extContext = null;
	String sep = File.separator;
	extContext = FacesContext.getCurrentInstance().getExternalContext();
	String pathResource = extContext.getRealPath("resources");

	Date hoy = new Date();
	SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

	Document documento = new Document(new Rectangle(PageSize.A4.rotate()));
	FileOutputStream archivoPdf;

	Font fuente = FontFactory.getFont("arial", 12, Font.BOLDITALIC);

	String pathPDF = "";

	try {
	    pathPDF = sep + "tmp" + sep + "informe_" + formato.format(hoy) + ".pdf";
	    archivoPdf = new FileOutputStream(pathResource + pathPDF);
	    PdfWriter writer = PdfWriter.getInstance(documento, archivoPdf);
	    documento.setMargins(72.0f, 72.0f, 180.0f, 60.0f);
	    HeaderFooterPDF event = new HeaderFooterPDF();
	    Usuario usuario = getUsuario();
	    event.usuario = usuario.getApellido() + ", " + usuario.getNombre();
	    writer.setPageEvent(event);
	    documento.open();

	    PdfPTable tabla = new PdfPTable(4);
	    tabla.setWidthPercentage(100);

	    PdfPCell celdaMes = new PdfPCell(new Paragraph("Mes", fuente));
	    tabla.addCell(celdaMes);

	    PdfPCell celdaAnio = new PdfPCell(new Paragraph("Año", fuente));
	    tabla.addCell(celdaAnio);

	    PdfPCell celdaTransacciones = new PdfPCell(new Paragraph("Cantidad de transacciones", fuente));
	    tabla.addCell(celdaTransacciones);

	    PdfPCell celdaComision = new PdfPCell(new Paragraph("Comisión por el servicio", fuente));
	    tabla.addCell(celdaComision);

	    for (int i = 0; i < comisionesList.size(); i++) {
		String[] vComision = comisionesList.get(i);
		tabla.addCell(convertirMes(vComision[1]));
		tabla.addCell(vComision[0]);
		tabla.addCell(vComision[2]);
		tabla.addCell("$ " + vComision[3]);

	    }

	    documento.add(tabla);

	    JFreeChart jfreechart = ChartFactory
		    .createLineChart("Recaudación de transacciones por período mensual", "Período agrupado por mes",
			    "Recaudación en $", dataset, PlotOrientation.VERTICAL, true, true, true);
	    File chartFile = new File("/tmp/dynamichart");
	    ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 700, 300);
	    Image grafica = Image.getInstance(chartFile.getAbsolutePath());

	    documento.add(grafica);
	    documento.close();
	    writer.close();

	    download(new File(pathResource + pathPDF), "application/pdf");

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void crearExcel() {

	Date hoy = new Date();
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	int indexRow = 0;

	HSSFWorkbook libro = new HSSFWorkbook();
	HSSFSheet hoja = libro.createSheet();

	// Obtenemos la imagen
	ExternalContext extContext = null;
	extContext = FacesContext.getCurrentInstance().getExternalContext();
	String sep = File.separator;
	String path = extContext.getRealPath("resources" + sep + "images") + sep + "logo_playon_admin.png";
	File file = new File(path);

	try {
	    HSSFPatriarch patriarch = hoja.createDrawingPatriarch();
	    HSSFClientAnchor anchor = new HSSFClientAnchor(400, 100, 600, 200, (short) 0, 0, (short) 6, 5);
	    anchor.setAnchorType(5);
	    patriarch.createPicture(anchor, loadPicture(file, libro));

	    /**** Configuración del estilo de la celda encabezado. ******/
	    CellStyle style = libro.createCellStyle();
	    style.setFillPattern(CellStyle.NO_FILL);
	    org.apache.poi.ss.usermodel.Font font = libro.createFont();
	    font.setFontName("Courier New");
	    font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
	    font.setColor(IndexedColors.BLACK.getIndex());
	    style.setFont(font);
	    /**** Fin configuración del estilo de la celda. ******/

	    Usuario usuario = getUsuario();
	    String encabezadoFecha = formato.format(hoy);
	    HSSFRow fila0 = hoja.createRow(3);
	    createCell(fila0, 8, encabezadoFecha, style);

	    String encabezadoUsuario = usuario.getApellido() + ", " + usuario.getNombre();
	    HSSFRow fila1 = hoja.createRow(4);
	    createCell(fila1, 8, encabezadoUsuario, style);

	    /**** Configuración del estilo de la celda header de la tabla. ******/
	    CellStyle styleHeaderTable = libro.createCellStyle();
	    styleHeaderTable.setFillPattern(CellStyle.NO_FILL);
	    org.apache.poi.ss.usermodel.Font fontHeaderTable = libro.createFont();
	    fontHeaderTable.setFontName("Times Roman");
	    fontHeaderTable.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
	    fontHeaderTable.setColor(IndexedColors.BLACK.getIndex());
	    styleHeaderTable.setFont(fontHeaderTable);
	    /**** Fin configuración del estilo de la celda. ******/

	    HSSFRow fila8 = hoja.createRow(8);
	    indexRow = 8;
	    createCell(fila8, 2, "Mes", style);
	    createCell(fila8, 3, "Año", styleHeaderTable);
	    createCell(fila8, 4, "Cantidad de transacciones", styleHeaderTable);
	    createCell(fila8, 5, "Comisión por el servicio", styleHeaderTable);
	    indexRow = indexRow + 3;

	    for (String[] vComision : comisionesList) {
		HSSFRow row = hoja.createRow(indexRow);
		createCell(row, 2, convertirMes(vComision[1]), null);
		createCell(row, 3, vComision[0], null);
		createCell(row, 4, vComision[2], null);
		createCell(row, 5, "$ " + vComision[3], null);
		indexRow++;
	    }

	    for (int i = 0; i < 20; i++) {
		hoja.autoSizeColumn((short) i);
	    }

	    JFreeChart jfreechart = ChartFactory
		    .createLineChart("Recaudación de transacciones por período mensual", "Período agrupado por mes",
			    "Recaudación en $", dataset, PlotOrientation.VERTICAL, true, true, true);
	    File chartFile = new File("/tmp/dynamichart");
	    ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 700, 300);

	    indexRow = indexRow + 2;

	    anchor = new HSSFClientAnchor(400, 100, 600, 200, (short) 0, indexRow, (short) 10, indexRow + 23);
	    anchor.setAnchorType(5);
	    patriarch.createPicture(anchor, loadPicture(chartFile, libro));

	    // Se salva el libro.
	    try {
		formato = new SimpleDateFormat("yyyyMMdd");
		FileOutputStream elFichero = new FileOutputStream("/tmp/informe_" + formato.format(hoy) + ".xls");
		libro.write(elFichero);
		elFichero.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    File excelFile = new File("/tmp/informe_" + formato.format(hoy) + ".xls");

	    download(excelFile, "application/vnd.ms-excel");

	} catch (Exception ex) {
	    ex.printStackTrace();
	}

    }

    // Metodo que permite insertar imagenes a el excel
    private static int loadPicture(File path, HSSFWorkbook wb) throws IOException {
	int pictureIndex;
	FileInputStream fis = null;
	ByteArrayOutputStream bos = null;
	try {
	    // read in the image file
	    fis = new FileInputStream(path);
	    bos = new ByteArrayOutputStream();
	    int c;
	    // copy the image bytes into the ByteArrayOutputStream
	    while ((c = fis.read()) != -1)
		bos.write(c);

	    // add the image bytes to the workbook
	    pictureIndex = wb.addPicture(bos.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG);

	} finally {
	    if (fis != null)
		fis.close();
	    if (bos != null)
		bos.close();
	}
	return pictureIndex;
    }

    public static void createCell(Row row, int i, String value, CellStyle style) {
	Cell cell = row.createCell(i);
	value = value + " ";
	cell.setCellValue(value);
	// si no hay estilo, no se aplica
	if (style != null)
	    cell.setCellStyle(style);
    }

    public void download(File pdfFile, String contentType) throws IOException {
	FacesContext facesContex = FacesContext.getCurrentInstance();
	ExternalContext externalContex = facesContex.getExternalContext();

	InputStream input = null;
	byte[] buffer = new byte[(int) (pdfFile.length())];
	int offset = 0;
	int numRead = 0;

	try {
	    input = new FileInputStream(pdfFile);

	    // Now you can write the InputStream of the file to the above OutputStream the usual way.
	    // ...
	    while ((offset < buffer.length) && ((numRead = input.read(buffer, offset, buffer.length - offset)) >= 0)) {

		offset += numRead;

	    }
	    input.close();

	    HttpServletResponse response = (HttpServletResponse) externalContex.getResponse();

	    response.setContentType(contentType);
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + pdfFile.getName() + "\"");
	    response.getOutputStream().write(buffer);
	    response.getOutputStream().flush();
	    response.getOutputStream().close();

	    facesContex.responseComplete();

	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    private Usuario getUsuario() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	return user;
    }

    private String convertirMes(String mesEnNumero) {
	String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
		"Octubre", "Noviembre", "Diciembre" };
	return meses[Integer.parseInt(mesEnNumero) - 1];
    }

    /**
     * @return the usuarioService
     */
    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    /**
     * @param usuarioService
     *            the usuarioService to set
     */
    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    /**
     * @return the liquidacionService
     */
    public ILiquidacionService getLiquidacionService() {
	return liquidacionService;
    }

    /**
     * @param liquidacionService
     *            the liquidacionService to set
     */
    public void setLiquidacionService(ILiquidacionService liquidacionService) {
	this.liquidacionService = liquidacionService;
    }

    /**
     * @return the publicidadService
     */
    public IPublicidadService getPublicidadService() {
	return publicidadService;
    }

    /**
     * @param publicidadService
     *            the publicidadService to set
     */
    public void setPublicidadService(IPublicidadService publicidadService) {
	this.publicidadService = publicidadService;
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
	return fechaDesde;
    }

    /**
     * @param fechaDesde
     *            the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
	this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
	return fechaHasta;
    }

    /**
     * @param fechaHasta
     *            the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
	this.fechaHasta = fechaHasta;
    }

    /**
     * @return the datatipFormat
     */
    public String getDatatipFormat() {
	datatipFormat = "<span style=\"display:none;\">$%.2f</span><span>$%.2f</span>";
	return datatipFormat;
    }

    /**
     * @param datatipFormat
     *            the datatipFormat to set
     */
    public void setDatatipFormat(String datatipFormat) {
	this.datatipFormat = datatipFormat;
    }

    /**
     * @return the maximaComision
     */
    public int getMaximoMonto() {
	return maximoMonto;
    }

    /**
     * @param maximaComision
     *            the maximaComision to set
     */
    public void setMaximoMonto(int maximaComision) {
	this.maximoMonto = maximaComision;
    }

    /**
     * @return the lModelComision
     */
    public CartesianChartModel getlModelComision() {
	return lModelComision;
    }

    /**
     * @param lModelComision
     *            the lModelComision to set
     */
    public void setlModelComision(CartesianChartModel lModelComision) {
	this.lModelComision = lModelComision;
    }

    /**
     * @return the comisionesList
     */
    public List<String[]> getComisionesList() {
	return comisionesList;
    }

    /**
     * @param comisionesList
     *            the comisionesList to set
     */
    public void setComisionesList(List<String[]> comisionesList) {
	this.comisionesList = comisionesList;
    }

    /**
     * @return the comisionesPublicidadList
     */
    public List<String[]> getComisionesPublicidadList() {
	return comisionesPublicidadList;
    }

    /**
     * @param comisionesPublicidadList
     *            the comisionesPublicidadList to set
     */
    public void setComisionesPublicidadList(List<String[]> comisionesPublicidadList) {
	this.comisionesPublicidadList = comisionesPublicidadList;
    }

    /**
     * @return the chart
     */
    public StreamedContent getChart() {
	return chart;
    }

    /**
     * @param chart
     *            the chart to set
     */
    public void setChart(StreamedContent chart) {
	this.chart = chart;
    }

}
