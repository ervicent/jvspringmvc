package ufrr.editora.mb;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import ufrr.editora.report.Report;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

@ManagedBean
@SessionScoped
public class ReportBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// relatorio de clientes (todos)
	public void relatorioClientes() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		Report report = new Report("Relatorio-Clientes", params);
		report.pdfReport();
		System.out
				.println("...solicitacao do relatorio de todos os clientes cadastrados");
	}

	// relatorio de clientes por categoria
	public void relatorioCategoria() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		Report report = new Report("Relatorio-Categoria", params);
		params.put("categoria", 1);
		report.pdfReport();
		System.out
				.println("...solicitacao do relatorio dos clientes por categoria");
	}

	// relatorio de fornecedores (todos)
	public void relatorioFornecedores() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		Report report = new Report("Relatorio-Fornecedores", params);
		report.pdfReport();
		System.out
				.println("...solicitacao do relatorio de todos os fornecedores cadastrados");
	}

	// relatorio de produtos registrados (todos)
	public void relatorioProdutos() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		Report report = new Report("Relatorio-Produtos", params);
		report.pdfReport();
		System.out.println("...solicitacao do relatorio de todos os produtos cadastrados");
	}
	
	// teste de relatorio
	public Map<String, String> getMapComFiltroEConsulta(String filtroAplicado, String consulta) {
	    Map<String, String> map = new HashMap<String, String>();
	 
	    // Se o usuário não aplicou nada no filtro, retorno nulo para o relatório
	    if (filtroAplicado == null)
	        filtroAplicado = "";
	    else
	        filtroAplicado = "Você aplicou o seguinte filtro no relatório:\n" + filtroAplicado;
	 
	    map.put("Filtro", filtroAplicado);
	    map.put("categoria_id", consulta);
	 
	    return map;
	}
	
	// relatorio de clientes por categoria (parametro)
	public void relatorioClienteCategoria() {
		HashMap<String, Object> params = new HashMap<String, Object>();
//		params.put("categoria_id", Integer.parseInt("categoria_id"));
		Report report = new Report("Relatorio-Clientes-Categoria", params);
		report.pdfReport();
		System.out.println("...solicitacao do relatorio de clientes por categoria (parametro)");
	}

	// Gerar relatorio das vendas dos clientes pela consulta
	public void preProcessPDF(Object document) throws IOException,
			BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);

		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String logo = servletContext.getRealPath("") + File.separator
				+ "resources" + File.separator + "img" + File.separator
				+ "logoeditorarelatorio.png";

		pdf.add(Image.getInstance(logo));
	}
}
