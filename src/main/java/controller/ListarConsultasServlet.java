package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ConsultaService;

import java.io.IOException;

@WebServlet("/listarConsultas")
public class ListarConsultasServlet extends HttpServlet {

    private ConsultaService service = new ConsultaService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("consultas", service.listar());

        req.getRequestDispatcher("/WEB-INF/pages/listarConsultas.jsp")
                .forward(req, resp);
    }
}
