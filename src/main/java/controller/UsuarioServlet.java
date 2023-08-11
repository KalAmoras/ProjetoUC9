package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Usuario;

import java.io.IOException;

import dao.UsuarioDAO;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();	
	
    public UsuarioServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String mensagem = "Cadastro Realizado com Sucesso!!";
			String mensagemerro = "Algo deu errado!!";

			String usuario = request.getParameter("usuario");
			String senha = request.getParameter("senha");
			
			Usuario user01 = new Usuario();
			
			user01.setUsuario(usuario);
			user01.setSenha(senha);
			
			if (usuarioDAO.vericaUsuario(user01.getUsuario()) && user01.getId() == null) {
				mensagem = "Usuário já cadastrado, informe outro usuário!!!";
			}else {
				if (user01.ehNovo()) {
					mensagem = "Gravado com Sucesso!!";
				}else {
					mensagem = "Atualizado com Sucesso!!";
				}
				user01 = usuarioDAO.insereUsuario(user01);
			}
			request.setAttribute("mensagem", mensagem);
			request.setAttribute("mensagemerro", mensagem);
			request.setAttribute("user01", user01);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("index.jsp");
			request.setAttribute("mensagemerro", e.getMessage());
			redireciona.forward(request, response);
		}
	}

}