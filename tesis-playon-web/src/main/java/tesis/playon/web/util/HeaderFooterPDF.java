/**
 * 
 */
package tesis.playon.web.util;

import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author pablo
 * 
 */
public class HeaderFooterPDF extends PdfPageEventHelper {

    /** An Image that goes in the header. */
    public Image headerImage;

    /** The headertable. */
    public PdfPTable table;

    /** A template that will hold the total number of pages. */
    public static PdfTemplate tpl;

    /** The font that will be used. */
    public BaseFont helv;

    public String usuario;

    private ExternalContext extContext = null;

    private String sep = File.separator;

    public static PdfContentByte cb;

    public void onStartPage(PdfWriter writer, Document document) {
	extContext = FacesContext.getCurrentInstance().getExternalContext();
	Date hoy = new Date();
	String pathLogo = extContext.getRealPath("resources" + sep + "images");

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	try {
	    // Cabecera
	    headerImage = Image.getInstance(pathLogo + sep + "logo_playon_admin.png");
	    headerImage.scaleToFit(280, 110);
	    table = new PdfPTable(2);

	    table.getDefaultCell().setBackgroundColor(Color.WHITE);
	    table.getDefaultCell().setBorderWidth(0);
	    table.addCell(new Phrase(new Chunk(headerImage, 0, 0)));
	    String textoEncabezado = "\n" + "\n" + usuario + "\n" + formato.format(hoy);
	    table.addCell(textoEncabezado);
	    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

	    tpl = writer.getDirectContent().createTemplate(100, 100);
	    tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));

	    cb = writer.getDirectContent();

	    // write the headertable
	    table.setTotalWidth(document.right() - document.left());
	    table.writeSelectedRows(0, -1, document.left(), document.getPageSize().getHeight() - 50, cb);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void onEndPage(PdfWriter writer, Document document) {

	try {

	    helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
	    // compose the footer
	    String text = "Página " + writer.getPageNumber() + " de " + document.getPageNumber();
	    float textSize = helv.getWidthPoint(text, 12);
	    float textBase = document.bottom() - 20;
	    cb.beginText();
	    cb.setFontAndSize(helv, 12);

	    float adjust = helv.getWidthPoint("0", 12);
	    cb.setTextMatrix(document.right() - textSize - adjust, textBase);
	    cb.showText(text);
	    cb.endText();
	    cb.addTemplate(tpl, document.right() - adjust, textBase);

	    /**
	     * // for odd pagenumbers, show the footer at the left if ((writer.getPageNumber() & 1) == 1) {
	     * cb.setTextMatrix(document.left(), textBase); cb.showText(text); cb.endText(); cb.addTemplate(tpl,
	     * document.left() + textSize, textBase); } // for even numbers, show the footer at the right else {
	     * 
	     * } /** // draw a Rectangle around the page cb.setColorStroke(Color.orange); cb.setLineWidth(2);
	     * cb.rectangle(20, 20, document.getPageSize().getWidth() - 40, document.getPageSize().getHeight() - 40);
	     * cb.stroke();
	     */

	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}
