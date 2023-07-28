package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cliente;
import models.Usuario;

@WebServlet("/cadastro")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuario");
        if (usuarioLogado == null) {
            response.sendRedirect("login.jsp");
        } else {
            request.getRequestDispatcher("painel/signup.jsp").forward(request, response);
        }
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String modalidade = request.getParameter("modalidade");

        Cliente novoUsuario = new Cliente();
        novoUsuario.setNome(nome);
        novoUsuario.setEndereco(endereco);
        novoUsuario.setModalidade(modalidade);

        request.setAttribute("nome", novoUsuario.getNome());
        request.setAttribute("email", novoUsuario.getEndereco());
        request.setAttribute("modalidade", novoUsuario.getModalidade());

        //request.getRequestDispatcher("/cadastro-confirmacao.jsp").forward(request, response);
    }
}
