package controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionMySQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.ClienteDAO;
import models.Cliente;


public class ClienteController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClienteDAO clienteDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        clienteDAO = new ClienteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listClients(request, response);
                break;
            case "edit":
                editClient(request, response);
                break;
            case "delete":
                deleteClient(request, response);
                break;
            default:
                listClients(request, response);
        }
    }

    

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
					try {
						addClient(request, response);			
					} catch (SQLException e) {
						e.printStackTrace();
					}
                    break;
                case "update":
                	try {
                    updateClient(request, response);
                	} catch (IOException e) {
                		e.printStackTrace();
					}
                    break;
                default:
                    listClients(request, response);
            }
        }
    }
    
    
    

    private void  listClients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clientes = clienteDAO.getAll();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/jsp/listAlunos.jsp").forward(request, response);
    }
    
    
    private void addClient(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException {
        int matricula = Integer.parseInt(request.getParameter("matricula"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String modalidade = request.getParameter("modalidade");

        Cliente newClient = new Cliente();
        newClient.setMatricula(matricula);
        newClient.setNome(nome);
        newClient.setEndereco(endereco);
        newClient.setModalidade(modalidade);

        clienteDAO.insertClient(newClient);

        response.sendRedirect("ClienteController?action=list");
    }

   

    private void editClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int matricula = Integer.parseInt(request.getParameter("matricula"));
        Cliente cliente = clienteDAO.getById(matricula);

        request.setAttribute("cliente", cliente);
        request.getRequestDispatcher("/jsp/editCliente.jsp").forward(request, response);
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int matricula = Integer.parseInt(request.getParameter("matricula"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String modalidade = request.getParameter("modalidade");
        ClienteDAO clientDAO = new ClienteDAO();

        Cliente updatedClient = new Cliente();
        updatedClient.setMatricula(matricula);
        updatedClient.setNome(nome);
        updatedClient.setEndereco(endereco);
        updatedClient.setModalidade(modalidade);

        clientDAO.updateClient(updatedClient);

        response.sendRedirect("ClienteController");
    }

	
	private void deleteClient(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			int matricula = Integer.parseInt(request.getParameter("matricula"));
		
	        Connection connection = ConnectionMySQL.iniciarConexao();

	        // Create a UserDAO instance with the connection
	        ClienteDAO clientDAO = new ClienteDAO();

	        // Delete the user from the database
	        clientDAO.deleteClient(matricula);

	        // Close the database connection after use (You can use a connection pool to manage this)
	        ConnectionMySQL.encerrarConexao(connection);

	        // Redirect the user back to the delete page after deleting the user
	        response.sendRedirect("delete.jsp");
	}
	
	/* private void deleteAluno(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int matricula = Integer.parseInt(request.getParameter("matricula"));
        alunoDAO.deleteAluno(matricula);
        response.sendRedirect("AlunoController");
    }*/

	
	
    
    
}