package safeguard.db;

import java.sql.*;
import java.util.*;

public class AlertaDAO {
    private Connection con;

    public AlertaDAO() {
        this.con = ConexaoBD.getInstance().getConexao();
    }

    public boolean inserir(int idOcorrencia, String tipo, String mensagem) {
        String sql = "INSERT INTO alertas (id_ocorrencia, tipo, mensagem) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idOcorrencia);
            ps.setString(2, tipo.toUpperCase());
            ps.setString(3, mensagem);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir alerta: " + e.getMessage());
            return false;
        }
    }

    public List<Object[]> listarTodos() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT al.id_alerta, al.tipo, v.nome AS vitima, al.emitido_em, al.mensagem, al.status " +
                     "FROM alertas al " +
                     "JOIN ocorrencias o ON al.id_ocorrencia = o.id_ocorrencia " +
                     "JOIN vitimas v ON o.id_vitima = v.id_vitima " +
                     "ORDER BY al.emitido_em DESC";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id_alerta"),
                    rs.getString("tipo"),
                    rs.getString("vitima"),
                    rs.getTimestamp("emitido_em").toString(),
                    rs.getString("mensagem"),
                    rs.getString("status")
                });
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar alertas: " + e.getMessage());
        }
        return lista;
    }
}
