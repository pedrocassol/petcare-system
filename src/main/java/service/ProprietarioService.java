package service;

import dao.ProprietarioDAO;
import model.Proprietario;

import java.util.List;

public class ProprietarioService {

    public boolean inserir(Proprietario p) {

        return new ProprietarioDAO().inserir(p);
    }

    public List<Proprietario> listar() {

        return new ProprietarioDAO().listar();
    }

    public int contar() {

        return new ProprietarioDAO().contar();
    }

    public Proprietario buscarPorId(int id) {

        return new ProprietarioDAO().buscarPorId(id);
    }

    public boolean atualizar(Proprietario p) {

        return new ProprietarioDAO().atualizar(p);
    }

    public boolean excluir(int id) {

        return new ProprietarioDAO().excluir(id);
    }
}
