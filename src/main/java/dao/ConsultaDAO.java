package dao;

import model.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public boolean inserir(Consulta c) {

        String sql = "INSERT INTO consulta " +
                "(id_pet, data_hora, veterinario, descricao, valor_estimado, status, observacoes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, c.getIdPet());
            stmt.setTimestamp(2, converterDataHora(c.getDataHora()));
            stmt.setString(3, c.getVeterinario());
            stmt.setString(4, c.getDescricao());
            stmt.setBigDecimal(5, c.getValorEstimado());
            stmt.setString(6, c.getStatus());
            stmt.setString(7, c.getObservacoes());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Consulta> listar() {

        List<Consulta> consultas = new ArrayList<>();

        String sql = "SELECT c.id_consulta, c.id_pet, c.data_hora, c.veterinario, " +
                "c.descricao, c.valor_estimado, c.status, c.observacoes, " +
                "p.nome AS nome_pet, pr.nome AS nome_proprietario " +
                "FROM consulta c " +
                "INNER JOIN pet p ON p.id_pet = c.id_pet " +
                "INNER JOIN proprietario pr ON pr.id_proprietario = p.id_proprietario " +
                "ORDER BY c.data_hora DESC";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Consulta c = new Consulta();

                c.setId(rs.getInt("id_consulta"));
                c.setIdPet(rs.getInt("id_pet"));
                c.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime().toString());
                c.setVeterinario(rs.getString("veterinario"));
                c.setDescricao(rs.getString("descricao"));
                c.setValorEstimado(rs.getBigDecimal("valor_estimado"));
                c.setStatus(rs.getString("status"));
                c.setObservacoes(rs.getString("observacoes"));
                c.setNomePet(rs.getString("nome_pet"));
                c.setNomeProprietario(rs.getString("nome_proprietario"));

                consultas.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return consultas;
    }

    public int contar() {

        String sql = "SELECT COUNT(*) AS total FROM consulta";

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

    public Consulta buscarPorId(int id) {

        String sql = "SELECT c.id_consulta, c.id_pet, c.data_hora, c.veterinario, " +
                "c.descricao, c.valor_estimado, c.status, c.observacoes, " +
                "p.nome AS nome_pet, pr.nome AS nome_proprietario " +
                "FROM consulta c " +
                "INNER JOIN pet p ON p.id_pet = c.id_pet " +
                "INNER JOIN proprietario pr ON pr.id_proprietario = p.id_proprietario " +
                "WHERE c.id_consulta = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Consulta c = new Consulta();

                c.setId(rs.getInt("id_consulta"));
                c.setIdPet(rs.getInt("id_pet"));
                c.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime().toString());
                c.setVeterinario(rs.getString("veterinario"));
                c.setDescricao(rs.getString("descricao"));
                c.setValorEstimado(rs.getBigDecimal("valor_estimado"));
                c.setStatus(rs.getString("status"));
                c.setObservacoes(rs.getString("observacoes"));
                c.setNomePet(rs.getString("nome_pet"));
                c.setNomeProprietario(rs.getString("nome_proprietario"));

                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean atualizar(Consulta c) {

        String sql = "UPDATE consulta SET id_pet = ?, data_hora = ?, veterinario = ?, " +
                "descricao = ?, valor_estimado = ?, status = ?, observacoes = ? " +
                "WHERE id_consulta = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, c.getIdPet());
            stmt.setTimestamp(2, converterDataHora(c.getDataHora()));
            stmt.setString(3, c.getVeterinario());
            stmt.setString(4, c.getDescricao());
            stmt.setBigDecimal(5, c.getValorEstimado());
            stmt.setString(6, c.getStatus());
            stmt.setString(7, c.getObservacoes());
            stmt.setInt(8, c.getId());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean excluir(int id) {

        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

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

    private Timestamp converterDataHora(String dataHora) {

        String valor = dataHora.replace("T", " ");

        if (valor.length() == 16) {
            valor += ":00";
        }

        return Timestamp.valueOf(valor);
    }
}
