package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Usuario;

import java.io.IOException;
import dao.LoginDAO;

@WebServlet("/admincontrol")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginDAO loginDAO = new LoginDAO();
	
    public LoginServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate(); 
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		}else {
			doPost(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
		
			if (usuario != null && !usuario.isEmpty() && senha != null && !senha.isEmpty()) {
				Usuario user01 = new Usuario();
				user01.setUsuario(usuario);
				user01.setSenha(senha);
				
				if (loginDAO.validarLogin(user01)) {
					request.getSession().setAttribute("usuario", user01.getUsuario());
					if (url == null || url.equals("null")) {
						url = "painel/inicio.jsp";
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				}
				else {
					RequestDispatcher redireciona = request.getRequestDispatcher("/login.jsp");
					request.setAttribute("mensagem", "Usuário ou Senha incorretos!");
					redireciona.forward(request, response);
				}			
			}
			else {
				RequestDispatcher redireciona = request.getRequestDispatcher("/login.jsp");
				request.setAttribute("mensagem", "Informe o Usuário e Senha corretamente!");
				redireciona.forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}