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
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet("/relatorios")
public class RelatoriosServlet extends HttpServlet {

    private PetService petService = new PetService();
    private ProprietarioService proprietarioService = new ProprietarioService();
    private ConsultaService consultaService = new ConsultaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String dataInicio = req.getParameter("dataInicio");
        String dataFim = req.getParameter("dataFim");

        List<Consulta> consultas = filtrarPorPeriodo(
                consultaService.listar(),
                dataInicio,
                dataFim
        );

        req.setAttribute("totalPets", petService.contar());
        req.setAttribute("totalProprietarios", proprietarioService.contar());
        req.setAttribute("totalConsultas", consultas.size());
        req.setAttribute("valorTotal", calcularValorTotal(consultas));
        req.setAttribute("consultas", consultas);
        req.setAttribute("dataInicio", dataInicio == null ? "" : dataInicio);
        req.setAttribute("dataFim", dataFim == null ? "" : dataFim);

        req.getRequestDispatcher("/WEB-INF/pages/relatorios.jsp").forward(req, resp);
    }

    private List<Consulta> filtrarPorPeriodo(List<Consulta> consultas, String dataInicio, String dataFim) {

        if (estaVazio(dataInicio) && estaVazio(dataFim)) {
            return consultas;
        }

        LocalDate inicio = estaVazio(dataInicio) ? null : LocalDate.parse(dataInicio);
        LocalDate fim = estaVazio(dataFim) ? null : LocalDate.parse(dataFim);
        List<Consulta> filtradas = new ArrayList<>();

        for (Consulta c : consultas) {

            LocalDate dataConsulta = LocalDateTime.parse(c.getDataHora()).toLocalDate();

            boolean depoisDoInicio =
                    inicio == null || !dataConsulta.isBefore(inicio);
            boolean antesDoFim =
                    fim == null || !dataConsulta.isAfter(fim);

            if (depoisDoInicio && antesDoFim) {
                filtradas.add(c);
            }
        }

        return filtradas;
    }

    private boolean estaVazio(String valor) {

        return valor == null || valor.trim().isEmpty();
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
