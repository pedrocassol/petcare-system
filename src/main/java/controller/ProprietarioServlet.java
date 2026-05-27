package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Proprietario;
import service.ProprietarioService;

import java.io.IOException;

@WebServlet({"/proprietario", "/proprietarios"})
public class ProprietarioServlet extends HttpServlet {

    private ProprietarioService service = new ProprietarioService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/pages/proprietarios.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nome = req.getParameter("nome");

        String telefone = req.getParameter("telefone");

        String email = req.getParameter("email");

        String endereco = req.getParameter("endereco");

        Proprietario p = new Proprietario(
                nome,
                telefone,
                email,
                endereco
        );

        boolean sucesso =
                service.inserir(p);

        if (sucesso) {
            resp.sendRedirect("proprietarios");

        } else {
            req.setAttribute("erro", "Erro ao cadastrar proprietário");

            req.getRequestDispatcher("/WEB-INF/pages/proprietarios.jsp").forward(req, resp);
        }
    }
}
