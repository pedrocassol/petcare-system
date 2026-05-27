package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PetService;

import java.io.IOException;

@WebServlet("/listarPets")
public class ListarPetsServlet extends HttpServlet {

    private PetService service = new PetService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("pets", service.listar());

        req.getRequestDispatcher("/WEB-INF/pages/listarPets.jsp").forward(req, resp);
    }
}
