package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Proprietario;
import service.ProprietarioService;

import java.io.IOException;

@WebServlet("/editarProprietario")
public class EditarProprietarioServlet extends HttpServlet {

    private ProprietarioService service =
            new ProprietarioService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("proprietario", service.buscarPorId(id));

        req.getRequestDispatcher("/WEB-INF/pages/editarProprietario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Proprietario p = new Proprietario(
                req.getParameter("nome"),
                req.getParameter("telefone"),
                req.getParameter("email"),
                req.getParameter("endereco")
        );

        p.setId(Integer.parseInt(req.getParameter("id")));

        service.atualizar(p);

        resp.sendRedirect("listarProprietarios");
    }
}
