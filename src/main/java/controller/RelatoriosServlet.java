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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/relatorios")
public class RelatoriosServlet extends HttpServlet {

    private PetService petService = new PetService();
    private ProprietarioService proprietarioService =
            new ProprietarioService();
    private ConsultaService consultaService =
            new ConsultaService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String status = req.getParameter("status");
        List<Consulta> consultas = filtrarPorStatus(
                consultaService.listar(),
                status
        );

        req.setAttribute("totalPets", petService.contar());
        req.setAttribute("totalProprietarios",
                proprietarioService.contar());
        req.setAttribute("totalConsultas",
                consultaService.contar());
        req.setAttribute("valorTotal",
                calcularValorTotal(consultas));
        req.setAttribute("consultas",
                consultas);
        req.setAttribute("statusSelecionado",
                status == null ? "" : status);

        req.getRequestDispatcher("/WEB-INF/pages/relatorios.jsp")
                .forward(req, resp);
    }

    private List<Consulta> filtrarPorStatus(List<Consulta> consultas,
                                            String status) {

        if (status == null || status.trim().isEmpty()) {
            return consultas;
        }

        List<Consulta> filtradas = new ArrayList<>();

        for (Consulta c : consultas) {
            if (status.equals(c.getStatus())) {
                filtradas.add(c);
            }
        }

        return filtradas;
    }

    private BigDecimal calcularValorTotal(List<Consulta> consultas) {

        BigDecimal total = BigDecimal.ZERO;

        for (Consulta c : consultas) {
            if (c.getValorEstimado() != null) {
                total = total.add(c.getValorEstimado());
            }
        }

        return total;
    }
}
