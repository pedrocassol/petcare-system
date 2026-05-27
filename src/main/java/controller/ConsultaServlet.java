package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Consulta;
import service.ConsultaService;
import service.PetService;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet({"/consulta", "/consultas"})
public class ConsultaServlet extends HttpServlet {

    private ConsultaService service = new ConsultaService();
    private PetService petService = new PetService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("pets", petService.listar());

        req.getRequestDispatcher("/WEB-INF/pages/consultas.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        Consulta c = new Consulta(
                Integer.parseInt(req.getParameter("idPet")),
                req.getParameter("dataHora"),
                req.getParameter("veterinario"),
                req.getParameter("descricao"),
                new BigDecimal(req.getParameter("valorEstimado")),
                req.getParameter("status"),
                req.getParameter("observacoes")
        );

        boolean sucesso = service.inserir(c);

        if (sucesso) {

            resp.sendRedirect("listarConsultas");

        } else {

            req.setAttribute("erro",
                    "Erro ao cadastrar consulta");
            req.setAttribute("pets", petService.listar());

            req.getRequestDispatcher("/WEB-INF/pages/consultas.jsp")
                    .forward(req, resp);
        }
    }
}
