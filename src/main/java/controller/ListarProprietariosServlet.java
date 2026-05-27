package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProprietarioService;

import java.io.IOException;

@WebServlet("/listarProprietarios")
public class ListarProprietariosServlet extends HttpServlet {

    private ProprietarioService service =
            new ProprietarioService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("proprietarios", service.listar());

        req.getRequestDispatcher("/WEB-INF/pages/listarProprietarios.jsp")
                .forward(req, resp);
    }
}
