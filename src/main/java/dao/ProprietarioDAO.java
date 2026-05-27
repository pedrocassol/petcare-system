package dao;

import model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAO {

    public boolean inserir(Proprietario p) {

        String sql = "INSERT INTO proprietario " +
                "(nome, telefone, email, endereco) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTelefone());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getEndereco());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Proprietario> listar() {

        List<Proprietario> proprietarios = new ArrayList<>();

        String sql = "SELECT id_proprietario, nome, telefone, email, endereco " +
                "FROM proprietario ORDER BY nome";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Proprietario p = new Proprietario();

                p.setId(rs.getInt("id_proprietario"));
                p.setNome(rs.getString("nome"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setEndereco(rs.getString("endereco"));

                proprietarios.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return proprietarios;
    }

    public int contar() {

        String sql = "SELECT COUNT(*) AS total FROM proprietario";

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

    public Proprietario buscarPorId(int id) {

        String sql = "SELECT id_proprietario, nome, telefone, email, endereco " +
                "FROM proprietario WHERE id_proprietario = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Proprietario p = new Proprietario();

                p.setId(rs.getInt("id_proprietario"));
                p.setNome(rs.getString("nome"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setEndereco(rs.getString("endereco"));

                return p;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean atualizar(Proprietario p) {

        String sql = "UPDATE proprietario SET nome = ?, telefone = ?, " +
                "email = ?, endereco = ? WHERE id_proprietario = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTelefone());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getEndereco());
            stmt.setInt(5, p.getId());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean excluir(int id) {

        String sql = "DELETE FROM proprietario WHERE id_proprietario = ?";

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
