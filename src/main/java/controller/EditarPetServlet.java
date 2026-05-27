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

@WebServlet("/editarPet")
public class EditarPetServlet extends HttpServlet {

    private PetService service = new PetService();
    private ProprietarioService proprietarioService =
            new ProprietarioService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("pet", service.buscarPorId(id));
        req.setAttribute("proprietarios", proprietarioService.listar());

        req.getRequestDispatcher("/WEB-INF/pages/editarPet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Pet p = new Pet(
                req.getParameter("nome"),
                req.getParameter("especie"),
                req.getParameter("raca"),
                Integer.parseInt(req.getParameter("idade")),
                req.getParameter("sexo"),
                req.getParameter("observacoes"),
                Integer.parseInt(req.getParameter("idProprietario"))
        );

        p.setId(Integer.parseInt(req.getParameter("id")));

        service.atualizar(p);

        resp.sendRedirect("listarPets");
    }
}
