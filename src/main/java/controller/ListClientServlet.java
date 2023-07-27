package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import connection.ConnectionMySQL;
import dao.ClienteDAO;
import models.Cliente;

@WebServlet("/ListClientServlet")
public class ListClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	Connection connection = ConnectionMySQL.iniciarConexao();

        ClienteDAO clientDAO = new ClienteDAO();

        List<Cliente> clients = clientDAO.getAll();
        
        ConnectionMySQL.encerrarConexao(connection);

        request.setAttribute("clientes", clients);

        request.getRequestDispatcher("/list_clients.jsp").forward(request, response);
    }

}
