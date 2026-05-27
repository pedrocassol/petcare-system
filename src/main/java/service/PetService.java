package service;

import dao.PetDAO;
import model.Pet;

import java.util.List;

public class PetService {

    public boolean inserir(Pet p) {

        return new PetDAO().inserir(p);
    }

    public List<Pet> listar() {

        return new PetDAO().listar();
    }

    public int contar() {

        return new PetDAO().contar();
    }

    public Pet buscarPorId(int id) {

        return new PetDAO().buscarPorId(id);
    }

    public boolean atualizar(Pet p) {

        return new PetDAO().atualizar(p);
    }

    public boolean excluir(int id) {

        return new PetDAO().excluir(id);
    }
}
