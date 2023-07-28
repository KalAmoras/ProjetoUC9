package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionMySQL;
import models.Cliente;

public class ClienteDAO extends Cliente {

    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }
    
    public ClienteDAO() {
    	super();
    }

	public void insertClient(Cliente cliente) {
        String query = "INSERT INTO cliente (matricula, nome, endereco, modalidade) VALUES (?, ?, ?, ?)";
        try ( Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

        	stmt.setInt(1, cliente.getMatricula());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getModalidade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing client in the database
    public void updateClient(Cliente cliente) {
        String query = "UPDATE cliente SET nome = ?, endereco = ?, modalidade = ? WHERE matricula = ?";
        try (Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getModalidade());
            stmt.setInt(4, cliente.getMatricula());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a client from the database based on their ID
    public void deleteClient(int clientId) {
        String query = "DELETE FROM cliente WHERE matricula = ?";
        try (Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, clientId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all clients from the database
    public List<Cliente> getAllClients() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try (Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int matricula = rs.getInt("matricula");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String modalidade = rs.getString("modalidade");

                Cliente cliente = new Cliente();
                cliente.setMatricula(matricula);
                cliente.setNome(nome);
                cliente.setEndereco(endereco);
                cliente.setModalidade(modalidade);

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Retrieve a single client by their ID
    public Cliente getClienteById(int clientId) {
        String query = "SELECT * FROM cliente WHERE id = ?";
        try (Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String modalidade = rs.getString("modalidade");

                Cliente cliente = new Cliente();
                cliente.setMatricula(clientId);
                cliente.setNome(nome);
                cliente.setEndereco(endereco);
                cliente.setModalidade(modalidade);

                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



	/*
	private static final String INSERT_SQL = "INSERT INTO cliente (matricula, nome, endereco, modalidade) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE cliente SET matricula=?, nome=?, endereco=?, modalidade=? WHERE matricula=?";
    private static final String DELETE_SQL = "DELETE FROM cliente WHERE matricula=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM cliente";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM cliente WHERE matricula=?";


    public void insertClient(Cliente cliente) {

    	Connection connection = ConnectionMySQL.iniciarConexao();

    	PreparedStatement stmt = null;

    	try {
    			stmt = connection.prepareStatement(INSERT_SQL);
		        stmt.setInt(1, cliente.getMatricula());
		        stmt.setString(2, cliente.getNome());
		        stmt.setString(3, cliente.getEndereco());
		        stmt.setString(4, cliente.getModalidade());
		        stmt.executeUpdate();
		    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	ConnectionMySQL.encerrarConexao(connection, stmt);
        }
    }



	public void updateClient(Cliente cliente) {

    	Connection connection = ConnectionMySQL.iniciarConexao();

    	PreparedStatement stmt = null;

    	try {
    			stmt = connection.prepareStatement(UPDATE_SQL);
		        stmt.setInt(1, cliente.getMatricula());
		        stmt.setString(2, cliente.getNome());
		        stmt.setString(3, cliente.getEndereco());
		        stmt.setString(4, cliente.getModalidade());
		        stmt.setInt(5, cliente.getMatricula());
		        stmt.executeUpdate();
		    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	ConnectionMySQL.encerrarConexao(connection, stmt);
        }
	 }

	public void deleteClient(int matricula) {
    	Connection connection = ConnectionMySQL.iniciarConexao();

    	PreparedStatement stmt = null;

		try {
			stmt = connection.prepareStatement(DELETE_SQL);
			stmt.setInt(1, getMatricula());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConnectionMySQL.encerrarConexao(connection, stmt);
		}
	}

	public List<Cliente> getAll()
	{
		List<Cliente> listaclientes = new ArrayList<>();

		Connection connection = ConnectionMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
				stmt = connection.prepareStatement(SELECT_ALL_SQL);
				rs = stmt.executeQuery();
				while(rs.next())
				{
					int matricula = rs.getInt("matricula");
					String nome = rs.getString("nome");
					String endereco = rs.getString("endereco");
					String modalidade = rs.getString("modalidade");

					Cliente cliente = new Cliente();
					cliente.setMatricula(matricula);
					cliente.setNome(nome);
					cliente.setEndereco(endereco);
					cliente.setModalidade(modalidade);
					listaclientes.add(cliente);
				}
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConnectionMySQL.encerrarConexao(connection, stmt, rs);
		}
		return listaclientes;
	}



	public Cliente getById(int matricula) {
	    Connection connection = ConnectionMySQL.iniciarConexao();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Cliente cliente = null;

	    try {
	        stmt = connection.prepareStatement(SELECT_BY_ID_SQL); //
	        stmt.setInt(1, matricula);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            String nome = rs.getString("nome");
	            String endereco = rs.getString("endereco");
	            String modalidade = rs.getString("modalidade");

	            cliente = new Cliente();
	            cliente.setMatricula(matricula);
	            cliente.setNome(nome);
	            cliente.setEndereco(endereco);
	            cliente.setModalidade(modalidade);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionMySQL.encerrarConexao(connection, stmt, rs);
	    }

	    return cliente;
	}*/
}
