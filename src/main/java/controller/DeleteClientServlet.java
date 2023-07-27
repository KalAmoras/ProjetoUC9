package controller;

import java.io.IOException;
import java.sql.Connection;

import connection.ConnectionMySQL;
import dao.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteClientServlet")
public class DeleteClientServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matricula = Integer.parseInt(request.getParameter("matricula"));

        Connection connection = ConnectionMySQL.iniciarConexao();

        ClienteDAO clientDAO = new ClienteDAO();

        clientDAO.deleteClient(matricula);

        ConnectionMySQL.encerrarConexao(connection);

        response.sendRedirect("ListClientServlet");
    }

}
