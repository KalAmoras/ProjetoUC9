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
import models.Usuario;

@WebServlet("/deletar_cliente")
public class DeleteClientServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuario");
        if (usuarioLogado == null) {
            response.sendRedirect("login.jsp");
        } else {
            request.getRequestDispatcher("list_clients.jsp").forward(request, response);
        }
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int matricula = Integer.parseInt(request.getParameter("matricula"));

        try (Connection connection = ConnectionMySQL.getConnection()) {
            ClienteDAO clientDAO = new ClienteDAO(connection);
            clientDAO.deleteClient(matricula);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("lista");
    }

}
