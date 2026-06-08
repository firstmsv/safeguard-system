package safeguard.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("SafeGuard — Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Cores.FUNDO);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
            new LineBorder(Cores.BORDA, 1, true),
            new EmptyBorder(32, 36, 32, 36)
        ));
        card.setMaximumSize(new Dimension(340, 440));

        JPanel iconePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        iconePanel.setBackground(Color.WHITE);
        JLabel icone = new JLabel("\uD83D\uDEE1");
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        iconePanel.add(icone);

        JLabel titulo = new JLabel("SafeGuard", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(Cores.AZUL_PRIMARIO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Sistema de monitoramento", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitulo.setForeground(Cores.TEXTO_SECUNDARIO);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel camposPanel = new JPanel(new GridLayout(0, 1, 0, 6));
        camposPanel.setBackground(Color.WHITE);
        camposPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        camposPanel.add(Componentes.label("Usuário"));
        JTextField usuario = Componentes.campo("seu.usuario");
        camposPanel.add(usuario);

        camposPanel.add(Box.createVerticalStrut(4));
        camposPanel.add(Componentes.label("Senha"));
        JPasswordField senha = Componentes.campoSenha();
        camposPanel.add(senha);

        camposPanel.add(Box.createVerticalStrut(4));
        camposPanel.add(Componentes.label("Perfil de acesso"));
        JComboBox<String> perfil = Componentes.combo(new String[]{
            "Administrador", "Autoridade", "Operador"
        });
        camposPanel.add(perfil);

        JButton btnEntrar = Componentes.botaoPrimario("Entrar");
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel nota = new JLabel("Acesso restrito a usuários autorizados", SwingConstants.CENTER);
        nota.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        nota.setForeground(Cores.TEXTO_SECUNDARIO);
        nota.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnEntrar.addActionListener(e -> {
            String usr = usuario.getText().trim();
            String pwd = new String(senha.getPassword());
            if (usr.isEmpty() || usr.equals("seu.usuario") || pwd.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Preencha usuário e senha.", "Atenção",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            dispose();
            new TelaPrincipal(perfil.getSelectedItem().toString(), usr).setVisible(true);
        });

        card.add(iconePanel);
        card.add(titulo);
        card.add(subtitulo);
        card.add(camposPanel);
        card.add(Box.createVerticalStrut(16));
        card.add(btnEntrar);
        card.add(Box.createVerticalStrut(10));
        card.add(nota);

        add(card);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
