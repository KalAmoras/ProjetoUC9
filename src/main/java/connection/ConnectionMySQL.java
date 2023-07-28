package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMySQL {
	
	/*private static String DRIVER = "com.mysql.cj.jdbc.Driver"; // indica o nome da classe dentro do JDBC*/
	private static String URL = "jdbc:mysql://localhost:3306/uc9"; // indicar o endereço do banco
	private static String USER = "osquentes"; // usuário do BD
	private static String PASS = "TaHpegandof0g0bich0"; // senha do BD

	
	private static Connection conn = null;

    private ConnectionMySQL() {
    }

    public static Connection getConnection() {
        iniciarConexao();
        return conn;
    }

    private static void iniciarConexao() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASS);
                conn.setAutoCommit(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encerrarConexao() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	/*
	private static Connection conn = null;
	
	public static Connection getConnection() {
		return conn;
	}
	
	static {
		iniciarConexao();
	}
	
	public ConnectionMySQL() {
		iniciarConexao();
	}
	
	private static void iniciarConexao() {
		try {
			if (conn == null) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(URL, USER, PASS);
				conn.setAutoCommit(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
	
	
	/*
	public static Connection iniciarConexao() {
		try { 
			Class.forName(DRIVER);
			return DriverManager.getConnection(DRIVER, URL, USER, PASS); 
		} catch (ClassNotFoundException | SQLException erro) {	
			throw new RuntimeException("Erro na conexão: " + erro);
		}
	}

	public static void encerrarConexao(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
	}

	public static void encerrarConexao(Connection conexao, PreparedStatement stmt) {
		encerrarConexao(conexao);
		try {
			stmt.close(); 
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
		*/
}