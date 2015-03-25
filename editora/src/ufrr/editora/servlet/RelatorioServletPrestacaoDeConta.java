package ufrr.editora.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufrr.editora.connection.ConnectionFactory;
import ufrr.editora.gerador.GeradorRelatorio;

@WebServlet("/pages/relatorio/relatorioPrestacaoDeConta")
public class RelatorioServletPrestacaoDeConta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			String nome = request.getServletContext().getRealPath("/WEB-INF/jasper/Prestacao-De-Conta.jasper");
			Connection connection = new ConnectionFactory().getConnection();

			String paramCategoria = request.getParameter("param_fornecedor");
			String dataIni = request.getParameter("data_ini");
			String dataFim = request.getParameter("data_fim");

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date dataInicial = sdf.parse(dataIni);
			Date dataFinal = sdf.parse(dataFim);

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("DATA_INI", dataInicial);
			parametros.put("DATA_FIM", dataFinal);
			parametros.put("FORNECEDOR", paramCategoria);

			GeradorRelatorio gerador = new GeradorRelatorio(nome, parametros, connection);
			gerador.geraPDFParaOutPutStream(response.getOutputStream());	

			connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
	}

}
