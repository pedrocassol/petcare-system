package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Pet;
import service.PetService;
import service.ProprietarioService;

import java.io.IOException;

@WebServlet({"/pet", "/pets"})
public class PetServlet extends HttpServlet {

    private PetService service = new PetService();
    private ProprietarioService proprietarioService = new ProprietarioService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("proprietarios", proprietarioService.listar());

        req.getRequestDispatcher("/WEB-INF/pages/pets.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nome = req.getParameter("nome");

        String especie = req.getParameter("especie");

        String raca = req.getParameter("raca");

        int idade = Integer.parseInt(
                req.getParameter("idade")
        );

        String sexo = req.getParameter("sexo");

        String observacoes =
                req.getParameter("observacoes");

        int idProprietario = Integer.parseInt(
                req.getParameter("idProprietario")
        );

        Pet p = new Pet(
                nome,
                especie,
                raca,
                idade,
                sexo,
                observacoes,
                idProprietario
        );

        boolean sucesso = service.inserir(p);

        if (sucesso) {

            resp.sendRedirect("pets");

        } else {

            req.setAttribute("erro", "Erro ao cadastrar pet");

            req.setAttribute("proprietarios", proprietarioService.listar());

            req.getRequestDispatcher("/WEB-INF/pages/pets.jsp").forward(req, resp);
        }
    }
}
