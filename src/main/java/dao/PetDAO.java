package dao;

import model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    public boolean inserir(Pet p) {

        String sql = "INSERT INTO pet " +
                "(nome, especie, raca, idade, sexo, observacoes, id_proprietario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEspecie());
            stmt.setString(3, p.getRaca());
            stmt.setInt(4, p.getIdade());
            stmt.setString(5, p.getSexo());
            stmt.setString(6, p.getObservacoes());

            stmt.setInt(7, p.getIdProprietario());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Pet> listar() {

        List<Pet> pets = new ArrayList<>();

        String sql = "SELECT p.id_pet, p.nome, p.especie, p.raca, p.idade, " +
                "p.sexo, p.observacoes, p.id_proprietario, pr.nome AS nome_proprietario " +
                "FROM pet p " +
                "INNER JOIN proprietario pr ON pr.id_proprietario = p.id_proprietario " +
                "ORDER BY p.nome";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Pet p = new Pet();

                p.setId(rs.getInt("id_pet"));
                p.setNome(rs.getString("nome"));
                p.setEspecie(rs.getString("especie"));
                p.setRaca(rs.getString("raca"));
                p.setIdade(rs.getInt("idade"));
                p.setSexo(rs.getString("sexo"));
                p.setObservacoes(rs.getString("observacoes"));
                p.setIdProprietario(rs.getInt("id_proprietario"));
                p.setNomeProprietario(rs.getString("nome_proprietario"));

                pets.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pets;
    }

    public int contar() {

        String sql = "SELECT COUNT(*) AS total FROM pet";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Pet buscarPorId(int id) {

        String sql = "SELECT p.id_pet, p.nome, p.especie, p.raca, p.idade, " +
                "p.sexo, p.observacoes, p.id_proprietario, pr.nome AS nome_proprietario " +
                "FROM pet p " +
                "INNER JOIN proprietario pr ON pr.id_proprietario = p.id_proprietario " +
                "WHERE p.id_pet = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Pet p = new Pet();

                p.setId(rs.getInt("id_pet"));
                p.setNome(rs.getString("nome"));
                p.setEspecie(rs.getString("especie"));
                p.setRaca(rs.getString("raca"));
                p.setIdade(rs.getInt("idade"));
                p.setSexo(rs.getString("sexo"));
                p.setObservacoes(rs.getString("observacoes"));
                p.setIdProprietario(rs.getInt("id_proprietario"));
                p.setNomeProprietario(rs.getString("nome_proprietario"));

                return p;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean atualizar(Pet p) {

        String sql = "UPDATE pet SET nome = ?, especie = ?, raca = ?, idade = ?, " +
                "sexo = ?, observacoes = ?, id_proprietario = ? WHERE id_pet = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEspecie());
            stmt.setString(3, p.getRaca());
            stmt.setInt(4, p.getIdade());
            stmt.setString(5, p.getSexo());
            stmt.setString(6, p.getObservacoes());
            stmt.setInt(7, p.getIdProprietario());
            stmt.setInt(8, p.getId());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean excluir(int id) {

        String sql = "DELETE FROM pet WHERE id_pet = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
