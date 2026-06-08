package safeguard.db;

import java.sql.*;
import java.util.*;

public class AgressorDAO {
    private Connection con;

    public AgressorDAO() {
        this.con = ConexaoBD.getInstance().getConexao();
    }

    public boolean inserir(String nome, String cpf, String telefone, String endereco,
                           int idVitima, String tipoVinculo, String descricao) {
        String sql = "INSERT INTO agressores (nome, cpf, telefone, endereco, id_vitima, tipo_vinculo, descricao) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, cpf);
            ps.setString(3, telefone.isEmpty() ? null : telefone);
            ps.setString(4, endereco.isEmpty() ? null : endereco);
            ps.setInt(5, idVitima);
            ps.setString(6, tipoVinculo.toUpperCase().replace("-","_").replace("(","").replace(")","").replace(" ","_"));
            ps.setString(7, descricao.isEmpty() ? null : descricao);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir agressor: " + e.getMessage());
            return false;
        }
    }

    public List<Object[]> listarTodos() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT a.id_agressor, a.nome, a.cpf, v.nome AS vitima FROM agressores a " +
                     "JOIN vitimas v ON a.id_vitima = v.id_vitima ORDER BY a.nome";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id_agressor"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("vitima")
                });
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar agressores: " + e.getMessage());
        }
        return lista;
    }
}
