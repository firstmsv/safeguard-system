package safeguard.db;

import java.sql.*;

public class UsuarioDAO {
    private Connection con;

    public UsuarioDAO() {
        this.con = ConexaoBD.getInstance().getConexao();
    }

    public String[] autenticar(String email, String senha) {
        String sql = "SELECT nome, perfil FROM usuarios WHERE email = ? AND senha = ? AND ativo = 1";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new String[]{ rs.getString("nome"), rs.getString("perfil") };
            }
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar: " + e.getMessage());
        }
        return null;
    }
}
