package safeguard.db;

import java.sql.*;
import java.util.*;

public class VitimaDAO {
    private Connection con;

    public VitimaDAO() {
        this.con = ConexaoBD.getInstance().getConexao();
    }

    public boolean inserir(String nome, String cpf, String telefone, String endereco,
                           String numMedida, String statusMedida, String observacoes) {
        String sql = "INSERT INTO vitimas (nome, cpf, telefone, endereco, num_medida, status_medida, observacoes) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, cpf.isEmpty() ? null : cpf);
            ps.setString(3, telefone);
            ps.setString(4, endereco);
            ps.setString(5, numMedida.isEmpty() ? null : numMedida);
            ps.setString(6, statusMedida.toUpperCase());
            ps.setString(7, observacoes.isEmpty() ? null : observacoes);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir vitima: " + e.getMessage());
            return false;
        }
    }

    public List<Object[]> listarTodas() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT id_vitima, nome, telefone, num_medida, status_medida FROM vitimas ORDER BY nome";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id_vitima"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("num_medida") != null ? rs.getString("num_medida") : "—",
                    rs.getString("status_medida")
                });
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar vitimas: " + e.getMessage());
        }
        return lista;
    }

    public boolean excluir(int id) {
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM vitimas WHERE id_vitima = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir vitima: " + e.getMessage());
            return false;
        }
    }
}
