package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ConsultaService;

import java.io.IOException;

@WebServlet("/excluirConsulta")
public class ExcluirConsultaServlet extends HttpServlet {

    private ConsultaService service = new ConsultaService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        service.excluir(id);

        resp.sendRedirect("listarConsultas");
    }
}
