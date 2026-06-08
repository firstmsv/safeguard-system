package safeguard.db;

import java.sql.*;

public class ConexaoBD {
    private static final String URL     = "jdbc:mysql://localhost:3306/safeguard_db?useSSL=false&serverTimezone=America/Sao_Paulo";
    private static final String USUARIO = "root";
    private static final String SENHA   = "1234";

    private static ConexaoBD instancia;
    private Connection conexao;

    private ConexaoBD() {}

    public static ConexaoBD getInstance() {
        if (instancia == null) instancia = new ConexaoBD();
        return instancia;
    }

    public Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL nao encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de conexao: " + e.getMessage());
        }
        return conexao;
    }

    public void fechar() {
        try {
            if (conexao != null && !conexao.isClosed()) conexao.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar: " + e.getMessage());
        }
    }
}
