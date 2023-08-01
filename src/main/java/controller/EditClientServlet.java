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
import models.Usuario;

@WebServlet("/editar_cliente")
public class EditClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuario");
	    if (usuarioLogado == null) {
	        response.sendRedirect("login.jsp");
	    } else {
	        int clientId = Integer.parseInt(request.getParameter("id"));

	        try (Connection connection = ConnectionMySQL.getConnection()) {
	            ClienteDAO clientDAO = new ClienteDAO(connection);
	            Cliente cliente = clientDAO.getClienteById(clientId);

	            if (cliente != null) {
	                request.setAttribute("cliente", cliente);
	                request.getRequestDispatcher("painel/edit_client.jsp").forward(request, response);
	            } else {
	                response.sendRedirect("painel/list_clients.jsp");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.sendRedirect("painel/list_clients.jsp");
	        }
	    }
	}
	
	
	
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

        response.sendRedirect("lista");
    }
}
