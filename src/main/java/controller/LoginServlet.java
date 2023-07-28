package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Usuario;

@WebServlet("/admincontrol")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuario");
		if (usuarioLogado == null) {
			response.sendRedirect("login.jsp");
		} else {
			request.getRequestDispatcher("painel/inicio.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		if (usuario != null && !usuario.isEmpty() && senha != null && !senha.isEmpty()) {
			Usuario user01 = new Usuario();
			user01.setUsuario(usuario);
			user01.setSenha(senha);

			if (user01.getUsuario().equals("admin") && user01.getSenha().equals("admin")) {
				request.getSession().setAttribute("usuario", user01);
				if (url == null || url.equals("null")) {
					url = "painel/inicio.jsp";
				}
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);
			} else {
				RequestDispatcher redireciona = request.getRequestDispatcher("/login.jsp");
				request.setAttribute("mensagem", "Usuário ou Senha incorretos!");
				redireciona.forward(request, response);
			}
		} else {
			RequestDispatcher redireciona = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("mensagem", "Informe o Usuário e Senha corretamente!");
			redireciona.forward(request, response);
		}
	}
}
