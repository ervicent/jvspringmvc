package ufrr.editora.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufrr.editora.connection.ConnectionFactory;
import ufrr.editora.gerador.GeradorRelatorio;

@WebServlet("/pages/relatorio/relatorioClientePorCategoria")
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			String nome = request.getServletContext().getRealPath("/WEB-INF/jasper/Relatorio-Clientes-Categoria.jasper");
			Connection connection = new ConnectionFactory().getConnection();
			
			String paramCategoria = request.getParameter("param_categoria");
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("CATEGORIA", paramCategoria);
			
			GeradorRelatorio gerador = new GeradorRelatorio(nome, parametros, connection);
			gerador.geraPDFParaOutPutStream(response.getOutputStream());	

			connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
