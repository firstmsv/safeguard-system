package safeguard.telas;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JComboBox<String> comboPerfil;

    public TelaLogin() {
        setTitle("SafeGuard — Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 480);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBackground(new Color(240, 242, 245));
        setContentPane(painelPrincipal);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(32, 36, 32, 36)
        ));
        card.setMaximumSize(new Dimension(340, 440));

        JLabel icone = new JLabel("🛡", SwingConstants.CENTER);
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        icone.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("SafeGuard", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(31, 78, 121));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Sistema de monitoramento", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitulo.setForeground(Color.GRAY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(icone);
        card.add(Box.createVerticalStrut(6));
        card.add(titulo);
        card.add(subtitulo);
        card.add(Box.createVerticalStrut(24));

        card.add(criarLabel("Usuário"));
        campoUsuario = criarCampo("seu.usuario");
        card.add(campoUsuario);
        card.add(Box.createVerticalStrut(12));

        card.add(criarLabel("Senha"));
        campoSenha = new JPasswordField();
        estilizarCampo(campoSenha);
        card.add(campoSenha);
        card.add(Box.createVerticalStrut(12));

        card.add(criarLabel("Perfil de acesso"));
        comboPerfil = new JComboBox<>(new String[]{"Administrador", "Autoridade", "Operador"});
        comboPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboPerfil.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        comboPerfil.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(comboPerfil);
        card.add(Box.createVerticalStrut(20));

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEntrar.setBackground(new Color(31, 78, 121));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBorderPainted(false);
        btnEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnEntrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEntrar.addActionListener(e -> realizarLogin());
        card.add(btnEntrar);
        card.add(Box.createVerticalStrut(12));

        JLabel aviso = new JLabel("Acesso restrito a usuários autorizados", SwingConstants.CENTER);
        aviso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        aviso.setForeground(Color.GRAY);
        aviso.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(aviso);

        painelPrincipal.add(card);
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(100, 100, 100));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField criarCampo(String placeholder) {
        JTextField campo = new JTextField(placeholder);
        estilizarCampo(campo);
        return campo;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
    }

    private void realizarLogin() {
        String usuario = campoUsuario.getText().trim();
        String perfil = (String) comboPerfil.getSelectedItem();
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o usuário.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        dispose();
        new TelaPrincipal(usuario, perfil).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
