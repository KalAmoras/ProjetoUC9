package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMySQL_Example {
	// Declaração de Constante
	private static String DRIVER = "com.mysql.cj.jdbc.Driver"; // indica o nome da classe dentro do JDBC
	private static String URL = "jdbc:mysql://localhost:3306/SEU_BANCO"; // indicar o endereço do banco
	private static String USER = "SEU_USUARIO"; // usuário do BD
	private static String PASS = "SUA_SENHA"; // senha do BD

	public static Connection iniciarConexao() {
		try { // Tentativa de conexão ao BD
			Class.forName(DRIVER); // será realizada uma busca pela classe
			return DriverManager.getConnection(URL, USER, PASS); // retorna a conexão
		} catch (ClassNotFoundException | SQLException erro) { // Caso não encontre a classe ou não consiga autenticar a
																// conexão
			throw new RuntimeException("Erro na conexão: " + erro);// Impressão do erro
		}
	}

	// Encerrar a conexão
	public static void encerrarConexao(Connection conexao) {
		if (conexao != null) { // Se conexão for diferente de nulo
			try {
				conexao.close(); // encerra a conexão
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
	}

	// Encerrar a conexão e o PreparedStatement
	public static void encerrarConexao(Connection conexao, PreparedStatement stmt) {
		encerrarConexao(conexao);
		try {
			stmt.close(); // encerra o Statatement
		} catch (SQLException erro) {
			erro.printStackTrace();
		}
	}
	// Encerrar a conexão, o PreparedStatement e o ResultSet
		public static void encerrarConexao(Connection conexao, PreparedStatement stmt, ResultSet rs) {
			encerrarConexao(conexao, stmt);
			try {
				rs.close(); // encerra o ResultSet
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
}