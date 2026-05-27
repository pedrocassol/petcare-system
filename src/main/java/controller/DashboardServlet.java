package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Consulta;
import service.ConsultaService;
import service.PetService;
import service.ProprietarioService;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private PetService petService = new PetService();
    private ProprietarioService proprietarioService =
            new ProprietarioService();
    private ConsultaService consultaService =
            new ConsultaService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        List<Consulta> consultasRecentes =
                consultaService.listar();

        if (consultasRecentes.size() > 4) {
            consultasRecentes = consultasRecentes.subList(0, 4);
        }

        req.setAttribute("totalPets", petService.contar());
        req.setAttribute("totalProprietarios",
                proprietarioService.contar());
        req.setAttribute("totalConsultas",
                consultaService.contar());
        req.setAttribute("consultasRecentes",
                consultasRecentes);

        req.getRequestDispatcher("/WEB-INF/pages/dashboard.jsp")
                .forward(req, resp);
    }
}
