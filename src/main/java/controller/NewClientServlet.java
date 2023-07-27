package controller;

import java.io.IOException;
import java.sql.Connection;

import connection.ConnectionMySQL;
import dao.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cliente;

@WebServlet("/NewClientServlet")
public class NewClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String modalidade = request.getParameter("modalidade");

        Cliente newClient = new Cliente();
        newClient.setNome(nome);
        newClient.setEndereco(endereco);
        newClient.setModalidade(modalidade);

        Connection connection = ConnectionMySQL.iniciarConexao();

        ClienteDAO clientDAO = new ClienteDAO();

        clientDAO.insertClient(newClient);

        ConnectionMySQL.encerrarConexao(connection);

        response.sendRedirect("ListClientServlet");
    }
	
}
