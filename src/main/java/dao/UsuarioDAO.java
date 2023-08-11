package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionMySQL;
import models.Usuario;

public class UsuarioDAO {
	private Connection conn;
	
	public UsuarioDAO() {
		conn = ConnectionMySQL.getConnection();
	}
	
	public Usuario insereUsuario(Usuario objeto) throws Exception{
		if (objeto.ehNovo()) {
			String sql = "INSERT INTO user(login, senha) VALUES(?, ?);";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, objeto.getUsuario());
			stmt.setString(2, objeto.getSenha());
			
			stmt.execute();
			
		}else {
			String sql = "UPDATE user SET login=?, senha=? WHERE id = "+objeto.getId()+";";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, objeto.getUsuario());
			stmt.setString(2, objeto.getSenha());
			
			stmt.executeUpdate();
			
			conn.commit();
		}
		return this.consultarUsuario(objeto.getUsuario());
	}
	
	public Usuario consultarUsuario(String usuario) throws Exception{
		Usuario user01 = new Usuario();
		
		
		return user01;
	}
	
	public boolean vericaUsuario(String usuario) throws Exception{
		String sql = "SELECT COUNT(1) > 0 AS EXISTE FROM user where login = '"+usuario+"';";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet res = stmt.executeQuery();
		
		res.next();
		return res.getBoolean("existe");
	}
}