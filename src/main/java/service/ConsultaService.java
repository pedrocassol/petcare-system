package service;

import dao.ConsultaDAO;
import model.Consulta;

import java.util.List;

public class ConsultaService {

    public boolean inserir(Consulta c) {

        return new ConsultaDAO().inserir(c);
    }

    public List<Consulta> listar() {

        return new ConsultaDAO().listar();
    }

    public int contar() {

        return new ConsultaDAO().contar();
    }

    public Consulta buscarPorId(int id) {

        return new ConsultaDAO().buscarPorId(id);
    }

    public boolean atualizar(Consulta c) {

        return new ConsultaDAO().atualizar(c);
    }

    public boolean excluir(int id) {

        return new ConsultaDAO().excluir(id);
    }
}
