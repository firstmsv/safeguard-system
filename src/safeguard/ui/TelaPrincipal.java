package safeguard.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import safeguard.telas.TelaCadastroAgressor;
import safeguard.telas.TelaOcorrencia;
import safeguard.telas.TelaAlerta;

public class TelaPrincipal extends JFrame {

    private JPanel painelConteudo;
    private CardLayout cardLayout;
    private String perfilUsuario;
    private String nomeUsuario;

    private JButton[] botoesMenu;
    private String[] telas = {"inicio", "vitima", "agressor", "ocorrencia", "alerta"};

    public TelaPrincipal(String perfil, String nome) {
        this.perfilUsuario = perfil;
        this.nomeUsuario = nome;

        setTitle("SafeGuard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 620);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(criarTopBar(), BorderLayout.NORTH);
        add(criarSidebar(), BorderLayout.WEST);

        cardLayout = new CardLayout();
        painelConteudo = new JPanel(cardLayout);
        painelConteudo.setBackground(Cores.FUNDO);

        painelConteudo.add(new PainelInicio(), "inicio");
        painelConteudo.add(new PainelCadastroVitima(), "vitima");
       painelConteudo.add(new TelaCadastroAgressor(), "agressor");
        painelConteudo.add(new TelaOcorrencia(), "ocorrencia");
        painelConteudo.add(new TelaAlerta(), "alerta");

        add(painelConteudo, BorderLayout.CENTER);
    }

    private JPanel criarTopBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(Cores.AZUL_PRIMARIO);
        bar.setPreferredSize(new Dimension(0, 44));
        bar.setBorder(new EmptyBorder(0, 16, 0, 16));

        JLabel logo = new JLabel("\uD83D\uDEE1  SafeGuard");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        logo.setForeground(Color.WHITE);

        JLabel usuario = new JLabel(perfilUsuario + " — " + nomeUsuario);
        usuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        usuario.setForeground(new Color(200, 220, 240));

        bar.add(logo, BorderLayout.WEST);
        bar.add(usuario, BorderLayout.EAST);
        return bar;
    }

    private JPanel criarSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Cores.FUNDO_SIDEBAR);
        sidebar.setBorder(new MatteBorder(0, 0, 0, 1, Cores.BORDA));
        sidebar.setPreferredSize(new Dimension(200, 0));

        String[][] itens = {
            {"inicio",      "\u2302  Início"},
            {"vitima",      "\u2665  Cadastro de vítima"},
            {"agressor",    "\u2716  Cadastro de agressor"},
            {"ocorrencia",  "\u2261  Ocorrências"},
            {"alerta",      "\u26A0  Alertas"}
        };

        botoesMenu = new JButton[itens.length];
        sidebar.add(Box.createVerticalStrut(10));

        for (int i = 0; i < itens.length; i++) {
            final String tela = itens[i][0];
            JButton btn = new JButton(itens[i][1]);
            btn.setFont(Cores.FONTE_SIDEBAR);
            btn.setForeground(Cores.TEXTO_SECUNDARIO);
            btn.setBackground(Cores.FUNDO_SIDEBAR);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setBorder(new EmptyBorder(10, 16, 10, 16));
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            botoesMenu[i] = btn;

            final int idx = i;
            btn.addActionListener(e -> {
                navegarPara(tela, idx);
            });
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    if (!btn.getBackground().equals(Cores.AZUL_CLARO))
                        btn.setBackground(new Color(240, 244, 248));
                }
                public void mouseExited(MouseEvent e) {
                    if (!btn.getBackground().equals(Cores.AZUL_CLARO))
                        btn.setBackground(Cores.FUNDO_SIDEBAR);
                }
            });
            sidebar.add(btn);
        }

        sidebar.add(Box.createVerticalGlue());

        JButton btnSair = new JButton("↩  Sair");
        btnSair.setFont(Cores.FONTE_SIDEBAR);
        btnSair.setForeground(Cores.VERMELHO);
        btnSair.setBackground(Cores.FUNDO_SIDEBAR);
        btnSair.setBorderPainted(false);
        btnSair.setFocusPainted(false);
        btnSair.setHorizontalAlignment(SwingConstants.LEFT);
        btnSair.setBorder(new EmptyBorder(10, 16, 10, 16));
        btnSair.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSair.addActionListener(e -> {
            dispose();
            new TelaLogin().setVisible(true);
        });
        sidebar.add(btnSair);
        sidebar.add(Box.createVerticalStrut(10));

        navegarPara("inicio", 0);
        return sidebar;
    }

    public void navegarPara(String tela, int idx) {
        cardLayout.show(painelConteudo, tela);
        for (JButton b : botoesMenu) {
            b.setBackground(Cores.FUNDO_SIDEBAR);
            b.setForeground(Cores.TEXTO_SECUNDARIO);
        }
        botoesMenu[idx].setBackground(Cores.AZUL_CLARO);
        botoesMenu[idx].setForeground(Cores.AZUL_PRIMARIO);
        botoesMenu[idx].setFont(new Font("Segoe UI", Font.BOLD, 13));
    }
}
