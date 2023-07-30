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

@WebServlet("/lista")
public class NewClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuario");
        if (usuarioLogado == null) {
            response.sendRedirect("login.jsp");
        } else {
            request.getRequestDispatcher("painel/list_clients.jsp").forward(request, response);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /*int matricula = Integer.parseInt(request.getParameter("matricula"));*/
		String nome = request.getParameter("nome");
	    String endereco = request.getParameter("endereco");
	    String modalidade = request.getParameter("modalidade");

	    Cliente newClient = new Cliente();
	    newClient.setNome(nome);
	    newClient.setEndereco(endereco);
	    newClient.setModalidade(modalidade);
	    System.out.print("NewServletComeço ");

	    System.out.print(nome + endereco + modalidade);

	    try (Connection connection = ConnectionMySQL.getConnection()) {
	        ClienteDAO clientDAO = new ClienteDAO(connection);
	        clientDAO.insertClient(newClient);
		    System.out.print("NewServletFim ");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    response.sendRedirect("painel/list_clients.jsp");
	}
	
	/*
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String modalidade = request.getParameter("modalidade");

        Cliente newClient = new Cliente();
        newClient.setNome(nome);
        newClient.setEndereco(endereco);
        newClient.setModalidade(modalidade);

        Connection conn = ConnectionMySQL.getConnection();

        ClienteDAO clientDAO = new ClienteDAO(conn);

        clientDAO.insertClient(newClient);

        ConnectionMySQL.encerrarConexao();
       /* response.sendRedirect("ListClientServlet");*
        response.sendRedirect("painel/list_clients.jsp");


        request.getRequestDispatcher("painel/list_clients.jsp").forward(request, response);

    }*/

}
