package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import models.Usuario;




@WebFilter(urlPatterns = {"/painel/*"})
public class FilterAdmin extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;

    public FilterAdmin() {
    }

    @Override
    public void destroy() {
        // Encerrar processos quando o servidor for parado
        // Encerra uma conexão com banco
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Interceptar requisições e respostas do sistema
        // Validar a autenticação
        // Commit & Roolback
        // Validar e Redirecionar páginas da aplicação
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession sessao = req.getSession();

        // Retrieve the User object from the session
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuario");
        String urlAutenticar = req.getServletPath();

        if (usuarioLogado == null && !urlAutenticar.equalsIgnoreCase("/painel/admincontrol")) {
            // Correção da Linha de redirecionamento
            RequestDispatcher redireciona = request.getRequestDispatcher("/login.jsp");
            request.setAttribute("mensagem", "Por favor efetue o Login!");
            redireciona.forward(request, response);
            return;
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // Iniciar a conexão com o Banco
    }
}

/*@WebFilter(urlPatterns = {"/painel/*"})
public class FilterAdmin extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public FilterAdmin() {
    }

	@Override
	public void destroy() {
		// Encerrar processos quando o servidor for parado
		// Encerra uma conexão com banco
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Interceptar requisições e respostas do sistema
		// Validar a autenticação
		// Commit & Roolback
		// Validar e Redirecionar páginas da aplicação
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession sessao = req.getSession();

		String usuarioLogado = (String) sessao.getAttribute("usuario");
		String urlAutenticar = req.getServletPath();

		if (usuarioLogado == null && !urlAutenticar.equalsIgnoreCase("/painel/admincontrol")) {
			//Correção da Linha de redirecionamento
			RequestDispatcher redireciona = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("mensagem", "Por favor efetue o Login!");
			redireciona.forward(request, response);
			return;
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// Iniciar a conexão com o Banco
	}

}*/