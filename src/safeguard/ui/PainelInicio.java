package safeguard.ui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class PainelInicio extends JPanel {

    public PainelInicio() {
        setLayout(new BorderLayout());
        setBackground(Cores.FUNDO);

        JScrollPane scroll = new JScrollPane(criarConteudo());
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Cores.FUNDO);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel criarConteudo() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Cores.FUNDO);
        p.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Painel principal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        titulo.setForeground(Cores.TEXTO_PRINCIPAL);
        p.add(titulo);
        p.add(Box.createVerticalStrut(14));

        JPanel stats = new JPanel(new GridLayout(1, 4, 10, 0));
        stats.setBackground(Cores.FUNDO);
        stats.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        stats.add(Componentes.statCard("12", "Vítimas cadastradas",  Cores.AZUL_PRIMARIO));
        stats.add(Componentes.statCard("8",  "Agressores cadastrados", Cores.VERMELHO));
        stats.add(Componentes.statCard("5",  "Ocorrências abertas",  new Color(186, 117, 23)));
        stats.add(Componentes.statCard("2",  "Alertas ativos",        Cores.VERMELHO));
        p.add(stats);
        p.add(Box.createVerticalStrut(14));

        JPanel cardOcorr = Componentes.card();
        cardOcorr.setLayout(new BorderLayout(0, 8));
        cardOcorr.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        JLabel lblOcorr = new JLabel("Ocorrências recentes");
        lblOcorr.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblOcorr.setForeground(Cores.TEXTO_SECUNDARIO);

        String[] colunas = {"ID", "Vítima", "Data", "Status"};
        Object[][] dados = {
            {"#005", "Ana Paula S.", "04/06/2025", "Em aberto"},
            {"#004", "Carla M.",     "02/06/2025", "Em análise"},
            {"#003", "Fernanda R.",  "28/05/2025", "Encerrada"},
        };
        JTable tabela = new JTable(dados, colunas);
        tabela.setFont(Cores.FONTE_CAMPO);
        tabela.setRowHeight(28);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabela.getTableHeader().setBackground(new Color(245, 247, 250));
        tabela.setGridColor(Cores.BORDA);
        tabela.setSelectionBackground(Cores.AZUL_CLARO);
        tabela.setFillsViewportHeight(true);

        cardOcorr.add(lblOcorr, BorderLayout.NORTH);
        cardOcorr.add(new JScrollPane(tabela), BorderLayout.CENTER);
        p.add(cardOcorr);
        p.add(Box.createVerticalStrut(14));

        JPanel cardAcesso = Componentes.card();
        cardAcesso.setLayout(new BorderLayout(0, 10));
        cardAcesso.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel lblAcesso = new JLabel("Acesso rápido");
        lblAcesso.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblAcesso.setForeground(Cores.TEXTO_SECUNDARIO);

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btns.setBackground(Color.WHITE);
        btns.add(Componentes.botaoPrimario("+ Nova vítima"));
        btns.add(Componentes.botaoSecundario("+ Novo agressor"));
        btns.add(Componentes.botaoSecundario("+ Nova ocorrência"));
        btns.add(Componentes.botaoPerigo("⚠ Emitir alerta"));

        cardAcesso.add(lblAcesso, BorderLayout.NORTH);
        cardAcesso.add(btns, BorderLayout.CENTER);
        p.add(cardAcesso);

        return p;
    }
}
