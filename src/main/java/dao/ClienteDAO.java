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
        String query = "INSERT INTO cliente (nome, endereco, modalidade) VALUES (?, ?, ?)";
        try ( Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getModalidade());
            System.out.print(stmt + "");

            stmt.executeUpdate();
            System.out.print("DAO adicionado ");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("não adicionado");
        }
    }

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

    public void deleteClient(int matricula) {
        String query = "DELETE FROM cliente WHERE matricula = ?";
        try (Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public Cliente getClienteById(int matricula) {
        String query = "SELECT * FROM cliente WHERE matricula = ?";
        try (Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, matricula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String modalidade = rs.getString("modalidade");

                Cliente cliente = new Cliente();
                cliente.setMatricula(matricula);
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
}
