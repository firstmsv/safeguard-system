package safeguard.telas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public static final Color COR_PRIMARIA   = new Color(31, 78, 121);
    public static final Color COR_SIDEBAR_BG = new Color(245, 247, 250);
    public static final Color COR_CONTEUDO   = new Color(240, 242, 245);
    public static final Color COR_BORDA      = new Color(220, 220, 220);
    public static final Color COR_TEXTO_SEC  = new Color(100, 100, 110);

    private JPanel painelConteudo;
    private String usuarioLogado;
    private String perfilLogado;

    public TelaPrincipal(String usuario, String perfil) {
        this.usuarioLogado = usuario;
        this.perfilLogado  = perfil;

        setTitle("SafeGuard — Sistema Desktop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 640);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(criarTopBar(), BorderLayout.NORTH);

        JPanel corpo = new JPanel(new BorderLayout());
        corpo.add(criarSidebar(), BorderLayout.WEST);

        painelConteudo = new JPanel(new BorderLayout());
        painelConteudo.setBackground(COR_CONTEUDO);
        corpo.add(painelConteudo, BorderLayout.CENTER);

        add(corpo, BorderLayout.CENTER);
        mostrarPainelInicio();
    }

    private JPanel criarTopBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(COR_PRIMARIA);
        bar.setPreferredSize(new Dimension(0, 46));
        bar.setBorder(new EmptyBorder(0, 16, 0, 16));

        JLabel logo = new JLabel("🛡  SafeGuard");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        logo.setForeground(Color.WHITE);
        bar.add(logo, BorderLayout.WEST);

        JLabel user = new JLabel(perfilLogado + " — " + usuarioLogado + "  👤");
        user.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        user.setForeground(new Color(200, 220, 240));
        bar.add(user, BorderLayout.EAST);
        return bar;
    }

    private JPanel criarSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(COR_SIDEBAR_BG);
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, COR_BORDA));
        sidebar.setPreferredSize(new Dimension(210, 0));

        sidebar.add(criarSecaoLabel("MENU"));
        sidebar.add(criarNavItem("🏠  Início",            () -> mostrarPainelInicio()));
        sidebar.add(criarNavItem("👩  Cadastro de vítima",   () -> abrirTela(new TelaCadastroVitima())));
        sidebar.add(criarNavItem("👤  Cadastro de agressor", () -> abrirTela(new TelaCadastroAgressor())));
        sidebar.add(criarNavItem("📄  Ocorrências",          () -> abrirTela(new TelaOcorrencia())));
        sidebar.add(criarNavItem("🔔  Alertas",              () -> abrirTela(new TelaAlerta())));
        sidebar.add(Box.createVerticalGlue());

        JButton btnSair = new JButton("Sair do sistema");
        btnSair.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnSair.setForeground(new Color(160, 40, 40));
        btnSair.setBorderPainted(false);
        btnSair.setBackground(COR_SIDEBAR_BG);
        btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSair.setBorder(new EmptyBorder(10, 16, 16, 16));
        btnSair.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnSair.addActionListener(e -> {
            dispose();
            new TelaLogin().setVisible(true);
        });
        sidebar.add(btnSair);
        return sidebar;
    }

    private JLabel criarSecaoLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lbl.setForeground(COR_TEXTO_SEC);
        lbl.setBorder(new EmptyBorder(14, 16, 6, 16));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JButton criarNavItem(String texto, Runnable acao) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(COR_TEXTO_SEC);
        btn.setBackground(COR_SIDEBAR_BG);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(9, 16, 9, 16));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(230, 235, 245));
                btn.setForeground(COR_PRIMARIA);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(COR_SIDEBAR_BG);
                btn.setForeground(COR_TEXTO_SEC);
            }
        });
        btn.addActionListener(e -> acao.run());
        return btn;
    }

    private void mostrarPainelInicio() {
        painelConteudo.removeAll();

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(COR_CONTEUDO);
        p.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Painel principal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(50, 50, 60));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(titulo);
        p.add(Box.createVerticalStrut(16));

        JPanel stats = new JPanel(new GridLayout(1, 4, 10, 0));
        stats.setBackground(COR_CONTEUDO);
        stats.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        stats.setAlignmentX(Component.LEFT_ALIGNMENT);
        stats.add(criarStatCard("12", "Vítimas cadastradas", new Color(12, 68, 124)));
        stats.add(criarStatCard("8",  "Agressores cadastrados", new Color(163, 45, 45)));
        stats.add(criarStatCard("5",  "Ocorrências abertas", new Color(99, 56, 6)));
        stats.add(criarStatCard("2",  "Alertas ativos", new Color(121, 31, 31)));
        p.add(stats);
        p.add(Box.createVerticalStrut(14));

        JPanel card = criarCard("Ocorrências recentes");
        String[] colunas = {"ID", "Vítima", "Data", "Status"};
        Object[][] dados = {
            {"#005", "Ana Paula S.", "04/06/2025", "Em aberto"},
            {"#004", "Carla M.",     "02/06/2025", "Em análise"},
            {"#003", "Fernanda R.",  "28/05/2025", "Encerrada"},
        };
        JTable tabela = new JTable(dados, colunas);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabela.setRowHeight(28);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabela.setEnabled(false);
        card.add(new JScrollPane(tabela), BorderLayout.CENTER);
        p.add(card);
        p.add(Box.createVerticalStrut(14));

        JPanel acesso = criarCard("Acesso rápido");
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        botoes.setBackground(Color.WHITE);
        botoes.add(criarBotaoPrimario("+ Nova vítima",    () -> abrirTela(new TelaCadastroVitima())));
        botoes.add(criarBotaoSecundario("+ Novo agressor",  () -> abrirTela(new TelaCadastroAgressor())));
        botoes.add(criarBotaoSecundario("+ Nova ocorrência",() -> abrirTela(new TelaOcorrencia())));
        botoes.add(criarBotaoAlerta("🔔 Emitir alerta",   () -> abrirTela(new TelaAlerta())));
        acesso.add(botoes, BorderLayout.CENTER);
        p.add(acesso);

        painelConteudo.add(new JScrollPane(p), BorderLayout.CENTER);
        painelConteudo.revalidate();
        painelConteudo.repaint();
    }

    private void abrirTela(JFrame tela) {
        tela.setVisible(true);
    }

    private JPanel criarStatCard(String numero, String label, Color corNum) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(235, 238, 245));
        card.setBorder(new EmptyBorder(14, 14, 14, 14));

        JLabel num = new JLabel(numero, SwingConstants.CENTER);
        num.setFont(new Font("Segoe UI", Font.BOLD, 24));
        num.setForeground(corNum);
        num.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl = new JLabel("<html><center>" + label + "</center></html>", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(COR_TEXTO_SEC);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(num);
        card.add(Box.createVerticalStrut(4));
        card.add(lbl);
        return card;
    }

    public static JPanel criarCard(String tituloSecao) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 1),
            new EmptyBorder(14, 16, 14, 16)
        ));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        if (tituloSecao != null && !tituloSecao.isEmpty()) {
            JLabel sec = new JLabel(tituloSecao.toUpperCase());
            sec.setFont(new Font("Segoe UI", Font.BOLD, 11));
            sec.setForeground(COR_TEXTO_SEC);
            sec.setBorder(new EmptyBorder(0, 0, 10, 0));
            card.add(sec, BorderLayout.NORTH);
        }
        return card;
    }

    public static JButton criarBotaoPrimario(String texto, Runnable acao) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(COR_PRIMARIA);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> acao.run());
        return btn;
    }

    public static JButton criarBotaoSecundario(String texto, Runnable acao) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(50, 50, 60));
        btn.setBorder(BorderFactory.createLineBorder(COR_BORDA));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> acao.run());
        return btn;
    }

    public static JButton criarBotaoAlerta(String texto, Runnable acao) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(new Color(252, 235, 235));
        btn.setForeground(new Color(163, 45, 45));
        btn.setBorder(BorderFactory.createLineBorder(new Color(240, 149, 149)));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> acao.run());
        return btn;
    }
}
