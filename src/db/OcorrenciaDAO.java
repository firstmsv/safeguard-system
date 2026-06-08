package safeguard.db;

import java.sql.*;
import java.util.*;

public class OcorrenciaDAO {
    private Connection con;

    public OcorrenciaDAO() {
        this.con = ConexaoBD.getInstance().getConexao();
    }

    public boolean inserir(int idVitima, int idAgressor, String data, String hora,
                           String local, String tipo, String descricao, String status) {
        String sql = "INSERT INTO ocorrencias (id_vitima, id_agressor, data_ocorrencia, hora_ocorrencia, " +
                     "local_ocorrencia, tipo_violencia, descricao, status) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVitima);
            ps.setInt(2, idAgressor);
            ps.setString(3, data);
            ps.setString(4, hora.isEmpty() ? null : hora);
            ps.setString(5, local);
            ps.setString(6, tipo.toUpperCase());
            ps.setString(7, descricao);
            ps.setString(8, status.toUpperCase().replace(" ", "_"));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir ocorrencia: " + e.getMessage());
            return false;
        }
    }

    public List<Object[]> listarTodas() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT o.id_ocorrencia, v.nome AS vitima, o.data_ocorrencia, o.tipo_violencia, o.status " +
                     "FROM ocorrencias o JOIN vitimas v ON o.id_vitima = v.id_vitima ORDER BY o.data_ocorrencia DESC";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Object[]{
                    "#" + String.format("%03d", rs.getInt("id_ocorrencia")),
                    rs.getString("vitima"),
                    rs.getDate("data_ocorrencia").toString(),
                    rs.getString("tipo_violencia"),
                    rs.getString("status").replace("_"," ")
                });
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar ocorrencias: " + e.getMessage());
        }
        return lista;
    }
}
