package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reportes")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().write("<!DOCTYPE html>"
                + "<html lang=\"pt-br\">"
                + "<head>"
                + "  <meta charset=\"UTF-8\">"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "  <title>Relatórios</title>"
                + "</head>"
                + "<body>"
                + "  <h1>Relatórios</h1>"
                + "  <!-- Aqui você pode adicionar os relatórios ou outras informações de relatórios -->"
                + "</body>"
                + "</html>");
    }
}
