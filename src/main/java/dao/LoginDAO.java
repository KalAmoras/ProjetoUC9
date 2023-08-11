package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionMySQL;
import models.Usuario;

public class LoginDAO {
	private Connection conn;
	
	public LoginDAO() {
		conn = ConnectionMySQL.getConnection();
	}
	
	public boolean validarLogin(Usuario usuario01) throws Exception {
		String sql = "SELECT * FROM user WHERE login = ? and senha = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, usuario01.getUsuario());
		stmt.setString(2, usuario01.getSenha());
		
		ResultSet rst = stmt.executeQuery();
		if (rst.next()) {
			return true;
		}
		return false;
	}
}