package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionMySQL;
import dao.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cliente;

@WebServlet("/ListClientServlet")
public class ListClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int matricula = Integer.parseInt(request.getParameter("matricula"));
	        String nome = request.getParameter("nome");
	        String endereco = request.getParameter("endereco");
	        String modalidade = request.getParameter("modalidade");

	        Cliente updatedClient = new Cliente();
	        updatedClient.setMatricula(matricula);
	        updatedClient.setNome(nome);
	        updatedClient.setEndereco(endereco);
	        updatedClient.setModalidade(modalidade);

	        try (Connection connection = ConnectionMySQL.getConnection()) {
	            ClienteDAO clientDAO = new ClienteDAO(connection);
	            clientDAO.updateClient(updatedClient);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        response.sendRedirect("painel/list_clients.jsp");
	    }
}
