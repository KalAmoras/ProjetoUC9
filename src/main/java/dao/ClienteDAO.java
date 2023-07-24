package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionMySQL;
import models.Cliente;

public class ClienteDAO extends Cliente { // Assume you have a connection object, you can get it from your database connection pool.
    
	
	private static final String INSERT_SQL = "INSERT INTO Cliente (matricula, nome, endereco, modalidade) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE Cliente SET matricula=?, nome=?, endereco=?, modalidade=? WHERE matricula=?";
    private static final String DELETE_SQL = "DELETE FROM Cliente WHERE matricula=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM Cliente";


    public void insertClient(Cliente cliente) throws SQLException {
    	
    	Connection conn = ConnectionMySQL.iniciarConexao();
        
    	PreparedStatement stmt = conn.prepareStatement(INSERT_SQL);
    	
    	try {
		        stmt.setInt(1, cliente.getMatricula());
		        stmt.setString(2, cliente.getNome());
		        stmt.setString(3, cliente.getEndereco());
		        stmt.setString(4, cliente.getModalidade());
		        stmt.executeUpdate();
		    } catch (SQLException e) {
            e.printStackTrace();
        } finally {        	
        	ConnectionMySQL.encerrarConexao(conn, stmt);
        }
    }
    
	public void updateClient(Cliente cliente) {
	    
		
    	Connection conn = ConnectionMySQL.iniciarConexao();
        
    	PreparedStatement stmt = null;
    	
    	try {
    			stmt = conn.prepareStatement(UPDATE_SQL);
		        stmt.setInt(1, cliente.getMatricula());
		        stmt.setString(2, cliente.getNome());
		        stmt.setString(3, cliente.getEndereco());
		        stmt.setString(4, cliente.getModalidade());
		        stmt.setInt(5, cliente.getMatricula());
		        stmt.executeUpdate();
		    } catch (SQLException e) {
            e.printStackTrace();
        } finally {        	
        	ConnectionMySQL.encerrarConexao(conn, stmt);
        }
	 }
	
	public void deleteClient(Cliente cliente) {
    	Connection conn = ConnectionMySQL.iniciarConexao();
    	
    	PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(DELETE_SQL);
			stmt.setInt(1, getMatricula());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
		
			ConnectionMySQL.encerrarConexao(conn, stmt);
		}
	}
	
	public ArrayList<Cliente> read()
	{
		Connection conn = ConnectionMySQL.iniciarConexao();
    	
    	PreparedStatement stmt = null;
		ResultSet rs = null;
	
		ArrayList<Cliente> listaclientes = new ArrayList<>();
		try {
				stmt = conn.prepareStatement(SELECT_ALL_SQL);
				rs = stmt.executeQuery();
				while(rs.next())
				{
					Cliente cliente = new Cliente();
					/*int matricula = resultSet.getInt("matricula");*/

					cliente.setMatricula(rs.getInt("matricula"));
					cliente.setNome(rs.getString("nome"));
					cliente.setEndereco(rs.getString("endereco"));
					cliente.setModalidade(rs.getString("modalidade"));
					listaclientes.add(cliente);
				}
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConnectionMySQL.encerrarConexao(conn, stmt, rs);
		}
		return listaclientes;
	}
	
}
