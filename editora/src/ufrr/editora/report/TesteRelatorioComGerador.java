package ufrr.editora.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import ufrr.editora.connection.ConnectionFactory;
import ufrr.editora.gerador.GeradorRelatorio;


//classe com main para gerar o jasper
public class TesteRelatorioComGerador {

	public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {
		
		String nome = "Relatorio-Clientes-Categoria";
		Map<String, Object> parametros = new HashMap<String, Object>();
		Connection connection = new ConnectionFactory().getConnection();
		
		GeradorRelatorio geradorRelatorio = new GeradorRelatorio(nome + ".jasper", parametros, connection);
		geradorRelatorio.geraPDFParaOutPutStream(new FileOutputStream(nome + ".pdf"));
		
		connection.close();
	}

}
