package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PetService;

import java.io.IOException;

@WebServlet("/excluirPet")
public class ExcluirPetServlet extends HttpServlet {

    private PetService service = new PetService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        service.excluir(id);

        resp.sendRedirect("listarPets");
    }
}
